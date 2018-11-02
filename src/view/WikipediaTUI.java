package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.DigraphBuilder;
import io.PageNameMapBuilder;
import model.DirectedGraph;

public class WikipediaTUI {

	private DirectedGraph<String> graph;
	private Map<String, String> pageNames;
	private Scanner input;

	private static final String UWG = "595315";

	public WikipediaTUI(String filename) {
		this.graph = DigraphBuilder.build(filename);
		this.input = new Scanner(System.in);
		this.pageNames = PageNameMapBuilder.build("WikipediaIDs.json");
	}

	public void start() {
		while (true) {
			System.out.println("Enter the starting page id\\ Enter Q to quit");
			var id = this.input.next();
			
			if (id.equalsIgnoreCase("Q")) {
				System.out.println("GoodBye");
				this.input.close();
				break;
			}
			else if (!this.graph.containsKey(id)) {
				System.out.println("ID not found");
				continue;
			}
			var path = this.graph.findPath(id,UWG);
			
			System.out.println("Path: " + this.convertToPageName(path));
			System.out.println("Hops: " + this.convertToPageName(path).size());
		}
	}
	
	private List<String> convertToPageName(List<String> path) {
		var names = new ArrayList<String>();
		for (var current : path) {
			var currentName = this.pageNames.get(current);
			names.add(currentName);
		}
		return names;
	}

}
