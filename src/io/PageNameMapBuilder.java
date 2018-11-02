package io;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PageNameMapBuilder {

	
	public static Map<String, String> build(String fileName) {
		var parser = new JSONParser();
		JSONObject json = null;
		try {
			System.out.println("Loading Page Names");
			json = (JSONObject) parser.parse(new FileReader(fileName));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		var map = new HashMap<String, String>(json);
		System.out.println("Complete");
		return map;
	}
}
