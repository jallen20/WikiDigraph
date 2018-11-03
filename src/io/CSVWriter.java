package io;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import util.IndegreeCalculator;

public class CSVWriter {
	private IndegreeCalculator degreeCalc;
	
	public CSVWriter(IndegreeCalculator indegree) {
		this.degreeCalc = indegree;
	}
	
	public void write(String outfilename, Map<String, String> pages) throws Exception {
		var outfile = new File(outfilename);
		var writer = new FileWriter(outfile);
		var sb = new StringBuilder();
		var top = this.degreeCalc.top(pages);
		for (var current : top.keySet()) {
			var value = top.get(current);
			sb.append(current);
			sb.append(",");
			sb.append(value);
			sb.append("\n");
		}
		writer.write(sb.toString());
		writer.flush();
		writer.close();
	}
}
