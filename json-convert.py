import json
	
def main():
	_json = makeDict()
	makeJsonFile(_json)
	return
	
def makeDict():
	with open("WikipediaLinks.txt", "r", errors="replace") as infile:
		_json = {}
		lines = infile.readlines()
		for currentLine in lines:
			currentLine = currentLine.replace('\n', '')
			thisLine = currentLine.split(' ')
			key = thisLine[0]
			_json[key] = thisLine[1:]
	infile.close()
	print('Read Complete')
	return _json
		
def makeJsonFile(jsonDict):		
	with open("WikipediaLinks.json", "w", errors="replace") as outfile:
		json.dump(jsonDict, outfile)
	outfile.close()
	return
		
if __name__ == "__main__":
	main()