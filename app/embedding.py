import requests
from utils import *
from flask import Flask, request

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = 'uploads/'

@app.route("/upload", methods=["POST"])
def trainModel():
    print(request.files)
    print(request.files.getlist("file[]"))
    return "hi"

@app.route("/similarity", methods=["POST"])
def getSimilarity():
    document1= request.form.get("document1", default=None)
    document2 = request.form.get("document2", default=None)

    model = createEmbeddingModel(document1 + ". " + document2)
    vector1 = getSentenceEmbedding(document1, model)
    vector2 = getSentenceEmbedding(document2, model)
    similarity = cosine_similarity(vector1, vector2)
    return str(similarity), 200


if __name__ == "__main__":
    app.run()