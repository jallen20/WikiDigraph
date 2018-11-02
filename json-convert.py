import json
import sys
	
def main():
	if len(sys.argv) == 3:
		infilename = sys.argv[1]
		outfilename = sys.argv[2]
		_json = makeDict(infilename)
		makeJsonFile(_json, outfilename)
	else:
		print("Did not provide necessary arguments 'infilename'.txt and 'outfilename'.json") 
	return
	
def makeDict(filepath):
	with open(filepath, "r", errors="replace") as infile:
		_json = {}
		lines = infile.readlines()
		for currentLine in lines:
			currentLine = currentLine.replace('\n', '')
			if ":" in currentLine:
				thisLine = currentLine.split(":")
				key = thisLine[1]
				_json[key] = thisLine[0]
			else:
				thisLine = currentLine.split(" ")
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