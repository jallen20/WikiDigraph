package model;

import java.util.ArrayList;
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

	public void addNode(T node) {
		if (edges.containsKey(node)) {
			throw new IllegalArgumentException("Can not add duplicate node to graph");
		}
		edges.put(node, new ArrayList<T>());
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
		for (var src : this.edges.values()) {
			if (src.contains(node)) {
				inDegree++;
			}
		}
		return inDegree;
	}

	/*
	 * public Map<T, T> dijkstra(T start) {
	 * 
	 * var dist = new TreeMap<T, Double>(); var prev = new TreeMap<T, T>(); var
	 * unchecked = new TreeSet<T>(super.nodeSet());
	 * 
	 * dist.put(start, 0.0); for (var current : this.nodeSet()) { if
	 * (!current.equals(start)) { dist.put(current, Double.POSITIVE_INFINITY); } }
	 * 
	 * while (!unchecked.isEmpty()) { var u = getShortestDistance(unchecked, dist);
	 * unchecked.remove(u); for (var v : getNeighbors(u)) { if
	 * (unchecked.contains(v)) { var distance = dist.get(u) + getWeight(u, v); if
	 * (distance < dist.get(v)) { dist.replace(v, distance); if
	 * (prev.containsKey(v)) { prev.replace(v, u); } else { prev.put(v, u); } } } }
	 * } return prev; }
	 */
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
				queue.addAll(this.getNeighbors(current));
			}
		}
		throw new IllegalStateException("Path Not found");
	}

	private T getShortestDistance(Set<T> unchecked, Map<T, Double> dist) {
		T shortest = null;
		if (unchecked.size() != 0) {
			for (var current : unchecked) {
				if (shortest == null) {
					shortest = current;
				} else if (dist.get(current) < dist.get(shortest))
					shortest = current;
			}
		}
		return shortest;
	}

}
