package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.DirectedGraph;
import view.WikipediaTUI;

public class App {


	public static void main(String[] args) throws IOException {
		// var tui = new WikipediaTUI("WikipediaLinks.txt");
		//var graph = yes();
		var parser = new JSONParser();
		Map<String, String[]> json = null;
		try {
			json = (JSONObject) parser.parse(new FileReader("WikipediaLinks.json"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		var me = new TreeMap<String, Object>(json);
		var y = me.get("25245886");
		System.out.println(y.toString());
		var graph = new DirectedGraph(me);
		System.out.println(graph.getInDegree("25245886"));
		System.out.println(graph.size());
	}
	
	private static String print(List<String> Node) {
		var result = "";
		for (var cur : Node) {
			result += cur + " ";
		}
		return result;
	}

	private static DirectedGraph<String> yes() throws FileNotFoundException {
		var graph = new DirectedGraph<String>();
		var scan = new Scanner(new File("WikipediaLinks.txt"));
		var count = 0;
		while(scan.hasNextLine()) {
			var line = scan.nextLine().split(" ");
			
			var node = line[0];
			var allExceptFirst = Arrays.copyOfRange(line, 1, line.length);
			//              System.out.println(node + ": " + print(Arrays.asList(allExceptFirst)));
			graph.addNode(node, Arrays.asList(allExceptFirst));
			count++;
			System.out.println(count);
		}
		scan.close();
		return graph;
	}

}
