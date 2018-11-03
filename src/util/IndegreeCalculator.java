package util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.DirectedGraph;

public class IndegreeCalculator {

	private DirectedGraph<String> graph;

	public IndegreeCalculator(DirectedGraph<String> graph) {
		this.graph = graph;
	}

	private Map<String, Integer> populate() {
		var edges = this.graph.getEdges();
		var results = new HashMap<String, Integer>();

		for (var current : edges.keySet()) {
			var degree = this.graph.getInDegree(current);
			results.put(current, degree);
		}

		return results;
	}

	public Map<String, Integer> top(Map<String, String> pages) {
		var allDegrees = this.populate();
		String current = null;
		var top100 = new HashMap<String, Integer>();
		for (var index = 0; index <= 100; index++) {
			var max = Collections.max(allDegrees.values());
			for (var currentNode : allDegrees.keySet()) {
				if (allDegrees.get(currentNode) == max) {
					top100.put(pages.get(currentNode), max);
					allDegrees.remove(currentNode);
				}
			}
		}
		return top100;
	}

}
