import json
import requests

expression = raw_input("Enter Expression: ")
data = {}
data['expression'] = expression
jsonData = json.dumps(data)
r = requests.post("http://localhost:9010/expr4j/evaluate",jsonData)
print r
