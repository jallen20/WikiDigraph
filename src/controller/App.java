package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import model.DirectedGraph;
import view.WikipediaTUI;

public class App {

	public static void main(String[] args) throws IOException {
		// var tui = new WikipediaTUI("WikipediaLinks.txt");
		var graph = yes();
		System.out.println(graph.findPath("12", "802"));
	}

	private static DirectedGraph<String> yes() throws FileNotFoundException {
		var graph = new DirectedGraph<String>();
		var scan = new Scanner(new File("WikipediaLinks.txt"));
		while(scan.hasNextLine()) {
			var line = scan.nextLine().split(" ");
			
			var node = line[0];
			
			for (var index = 1; index < line.length; index++) {
				graph.addEdge(node, line[index]);
			}
		}
		scan.close();
		return graph;
	}

}