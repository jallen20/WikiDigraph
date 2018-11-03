package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DirectedGraph<T extends Comparable<T>> {

	private Map<T, List<T>> edges;

	public DirectedGraph() {
		this.edges = new TreeMap<T, List<T>>();
	}

	public DirectedGraph(Map<T, List<T>> edges) {
		this.edges = edges;
	}

	public void addNode(T node) {
		if (edges.containsKey(node)) {
			throw new IllegalArgumentException("Can not add duplicate node to graph");
		}
		edges.put(node, new ArrayList<T>());
	}

	public int size() {
		return this.edges.keySet().size();
	}

	public void addNode(T node, List<T> neighbors) {
		if (this.edges.containsKey(node)) {
			return;
		}
		this.edges.put(node, neighbors);
	}

	public void addEdge(T node1, T node2) {

		if (!this.edges.containsKey(node1)) {
			this.addNode(node1);
		}
		if (!this.edges.containsKey(node2)) {
			this.addNode(node2);
		}
		if (this.getNeighbors(node1).contains(node2)) {
			return;
		}
		this.edges.get(node1).add(node2);
	}

	public List<T> getNeighbors(T node) {
		return this.edges.get(node);
	}

	public int getOutDegree(T node) {
		return this.edges.get(node).size();
	}

	public int getInDegree(T node) {
		var inDegree = 0;
		for (var current : this.edges.keySet()) {
			var neighbor = this.edges.get(current);
			if (neighbor.contains(node)) {
				inDegree++;
			}
		}
		return inDegree;
	}

	public boolean containsKey(T node) {
		return this.edges.containsKey(node);
	}

	public List<T> findPath(T start, T end) {
		var path = new ArrayList<T>();
		var queue = new LinkedList<T>();
		queue.add(start);
		while (!queue.isEmpty()) {
			var current = queue.remove();
			path.add(current);
			if (current.equals(end)) {
				return path;
			} else {
				for (var currentNeighbor : this.getNeighbors(current)) {
					if (!path.contains(currentNeighbor)) {
						if (currentNeighbor.equals(end)) {
							path.add(currentNeighbor);
							return path;
						}
						queue.add(currentNeighbor);
					}
				}
			}
		}
		throw new IllegalStateException("Path Not found");
	}

	public Map<T, Integer> getTop100() {
		var top100 = new HashMap<T, Integer>();
		var max = -1;
		T maxNode = null;
		for (var current : this.edges.keySet()) {
			if (this.getInDegree(current) > max) {
				maxNode = current;
				max = this.getInDegree(current);
			}
		}
		top100.put(maxNode, max);
		return top100;
	}

	public Map<T, List<T>> getEdges() {
		return this.edges;
	}

}
