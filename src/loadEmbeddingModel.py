import sys
import embedding

model_path = sys.argv[1]
model_type = sys.argv[2]
document1_path = sys.argv[3]
document2_path = sys.argv[4]

model = embedding.loadEmbeddingModel(model_path, model_type)

with open(document1_path) as f:
    content1 = f.read().strip()

with open(document2_path) as f:
    content2 = f.read().strip()

print(f"Similarity = {embedding.getSimilarity(model, content1, content2)*100}%")