package controller;

import io.CSVWriter;
import io.DigraphBuilder;
import io.PageNameMapBuilder;
import util.IndegreeCalculator;

public class Top100 {

	public static void main(String[] args) throws Exception {
		var graph = DigraphBuilder.build("WikipediaLinks.json");
		var pages = PageNameMapBuilder.build("WikipediaIDs.json");
		var degree = new IndegreeCalculator(graph);
		var writer = new CSVWriter(degree);
		writer.write("Top100.csv", pages);

	}

}
