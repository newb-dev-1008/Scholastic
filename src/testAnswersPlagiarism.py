import sys
from pathlib import Path
import requests

document1_path = Path(sys.argv[1]).resolve()
document2_path = Path(sys.argv[2]).resolve()

# print(document1_path)

# with open(document1_path) as f:
#     content1 = f.read().strip()
# 
# with open(document2_path) as f:
#     content2 = f.read().strip()
# 
data = {
    "doc": open(document1_path, "rb")
}

files = {
    "file": open(document1_path, "rb")
}

headers = {
'Content-Type': "multipart/form-data",
}

response = requests.post("http://127.0.0.1:5000/upload", files=files, headers=headers)
print(str(response.content))