import sys
from pathlib import Path
import requests

document1_path = Path(sys.argv[1]).resolve()
document2_path = Path(sys.argv[2]).resolve()

with open(document1_path) as f:
    content1 = f.read().strip()

with open(document2_path) as f:
    content2 = f.read().strip()

data = {
    "document1": content1,
    "document2": content2
}

files = {
    "file": open(document1_path, "rb")
}

headers = {
'Content-Type': "multipart/form-data",
}

response = requests.post("https://scholastic-bcmc.herokuapp.com/similarity", data=data)
print(str(response.content))