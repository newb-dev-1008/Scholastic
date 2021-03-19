import numpy as np
from gensim.models import FastText, Word2Vec
from gensim.models.phrases import Phrases, ENGLISH_CONNECTOR_WORDS
from nltk.tokenize import word_tokenize, sent_tokenize
from sklearn.decomposition import PCA


def phraseTransform(train):
    phrase_model = Phrases(train, min_count=3, threshold=1,
                           connector_words=ENGLISH_CONNECTOR_WORDS)
    converted_sentences = [phrase_model(sent) for sent in train]
    return converted_sentences


def createFastTextModel(corpus, phrase):
    sentences = sent_tokenize(corpus)
    train = list(map(word_tokenize, sentences))

    if phrase:
        train = phraseTransform(train)

    model = FastText(min_count=1, size=200)
    model.build_vocab(sentences=train)
    model.train(sentences=train,
                total_examples=model.corpus_count, epochs=5)
    return model


def createWord2VecModel(corpus, phrase):
    sentences = sent_tokenize(corpus)
    train = list(map(word_tokenize, sentences))

    if phrase:
        train = phraseTransform(train)

    model = Word2Vec(min_count=1, size=200)
    model.build_vocab(sentences=train)
    model.train(sentences=train,
                total_examples=model.corpus_count, epochs=5)
    return model


def createEmbeddingModel(corpus, phrase=False, model_type="fastText"):
    if model_type == "word2vec":
        model = createWord2VecModel(corpus, phrase)
    else:
        model = createFastTextModel(corpus, phrase)
    return model


def loadEmbeddingModel(fpath, model_type):  # fpath should be a .model file
    if model_type == "word2vec":
        model = Word2Vec.load(fpath)
    else:
        model = FastText.load(fpath)
    return model


def getSentenceEmbedding(document, model, mode="average"):
    embedding_size = model.vector_size
    if mode == "average":
        words = word_tokenize(document)
        avg_embedding = np.zeros(embedding_size)
        counter = 1
        for w in words:
            try:
                avg_embedding += model.wv.get_vector(w)
                counter += 1
            except:
                pass
        return avg_embedding/counter

    else:   # checking this part, don't use for now. Has a slight bug in logic
        sentence_set = list()
        sentence_list = sent_tokenize(document)
        for sentence in sentence_list:
            vs = np.zeros(embedding_size)
            sentence_length = 1
            for word in word_tokenize(sentence):
                try:
                    vs = np.add(vs, model.wv.get_vector(word))
                    sentence_length += 1
                except:
                    pass

            vs = np.divide(np.nan_to_num(vs), sentence_length)
            sentence_set.append(vs)

        pca = PCA()
        pca.fit(np.array(sentence_set))
        u = pca.components_[0]
        u = np.multiply(u, np.transpose(u))

        if len(u) < embedding_size:
            for i in range(embedding_size - len(u)):
                u = np.append(u, 0)

        sentence_vecs = []
        for vs in sentence_set:
            sub = np.multiply(u, vs)
            sentence_vecs.append(np.subtract(vs, sub))

        return sentence_vecs
