package io;

import java.io.FileReader;
import java.util.List;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.DirectedGraph;

public class DigraphBuilder {
	
	@SuppressWarnings("rawtypes")
	public static DirectedGraph<String> build(String fileName) {
		var parser = new JSONParser();
		JSONObject json = null;
		try {
			System.out.println("Loading");
			json = (JSONObject) parser.parse(new FileReader(fileName));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		var map = new TreeMap<String, List<String>>(json);
		System.out.println("Complete");
		return new DirectedGraph<String>(map);	
	}

}
