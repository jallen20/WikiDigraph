package io;

import java.io.FileReader;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.DirectedGraph;

public class DigraphGraphBuilder {
	
	@SuppressWarnings("rawtypes")
	public static DirectedGraph build(String fileName) {
		var parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(new FileReader(fileName));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		var map = new TreeMap<String, Object>(json);
		return new DirectedGraph(map);	
	}

}
