import sys
import logging
import embedding



fpath = sys.argv[1]
model_type = sys.argv[2]
use_phraser = bool(sys.argv[3])
model_name = sys.argv[4]

with open(fpath) as f:
    corpus = f.read().strip()

if model_type.lower() == "fasttext":
    model = embedding.createEmbeddingModel(corpus, use_phraser, "fastText")
else:
    model = embedding.createEmbeddingModel(corpus, use_phraser, "word2vec")


model.save(f"../model/{model_name}.model")