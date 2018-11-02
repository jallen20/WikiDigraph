package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import io.DigraphBuilder;
import model.DirectedGraph;


public class WikipediaTUI {
	
	private DirectedGraph graph;
	private Scanner input;
	
	public WikipediaTUI(String filename) {
		this.graph = DigraphBuilder.build(filename);
		this.input = new Scanner(System.in);
		
	}
	
	public void start() {
		
	}
	
	

}
