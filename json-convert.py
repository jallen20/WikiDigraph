import json
import sys
	
def main():
	if len(sys.argv) == 3:
		path = sys.argv[1]
		outfilename = sys.argv[2]
		_json = makeDict(path)
		makeJsonFile(_json, outfilename)
	else:
		print("Did not provide necessary arguments 'infile.txt' and 'outfile.json'") 
	return
	
def makeDict(filepath):
	with open(filepath, "r", errors="replace") as infile:
		_json = {}
		lines = infile.readlines()
		for currentLine in lines:
			currentLine = currentLine.replace('\n', '')
			thisLine = currentLine.split(":") if ":" in currentLine else currentLine.split(" ")
			key = thisLine[0]
			_json[key] = thisLine[1:]
	infile.close()
	print('Read Complete')
	return _json
		
def makeJsonFile(jsonDict, jsonOutFile):		
	with open(jsonOutFile, "w", errors="replace") as outfile:
		json.dump(jsonDict, outfile)
	outfile.close()
	return
		
if __name__ == "__main__":
	main()