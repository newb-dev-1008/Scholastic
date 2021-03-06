from .utils import *
from flask import Flask, request

app = Flask(__name__)


@app.route("/similarity", methods=["POST"])
def getSimilarity():
    document1= request.form.get("document1", default=None)
    document2 = request.form.get("document2", default=None)

    model = createEmbeddingModel(document1 + ". " + document2)
    vector1 = getSentenceEmbedding(document1, model)
    vector2 = getSentenceEmbedding(document2, model)
    similarity = cosine_similarity(vector1, vector2)

    del model
    del vector1
    del vector2
    del document1
    del document2
    
    return str(similarity), 200


if __name__ == "__main__":
    app.run()