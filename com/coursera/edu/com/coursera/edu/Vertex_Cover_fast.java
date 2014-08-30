package com.coursera.edu;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Vertex_Cover_fast {
	static int N;
	static Graph graph = null;
	public static void main(String[] args) throws IOException {
		fill();
		getVertexCover(graph);
	}
	private static void getVertexCover(Graph graph2) {
	/*	for(V v: graph2.vertices)
		System.out.println(((V1)v).features);
*/	
	while(graph2.edgeMap.size()!=0)
	{
		List<V> vs = new LinkedList<V>(graph2.vertices);
		Collections.sort(vs,new Comparator<V>() {

			@Override
			public int compare(V o1, V o2) {
				return ((V1)o2).features.compareTo(((V1)o1).features);
			}
			
		});
		graph2.removeVertex(vs.get(0).key);
		System.out.println(vs.get(0));
	}
	}
	private static void fill() throws IOException  {
		Scanner scan = new Scanner(new File("in.txt"));	
		N = scan.nextInt();
		int E = scan.nextInt();
			graph = new Graph();
			for(int i=0;i<N;i++){
				graph.vertices.add(new V1(i));
				graph.edges.add(new LinkedList<E>());	
			}
			for(int i=0;i<E;i++){
				fill(scan.nextInt()-1,scan.nextInt()-1,1);
			}
			scan.close();
		}
	private static void fill(int i, int j, int k) {
			graph.addEdge(new E(graph.getVertex(i),graph.getVertex(j),k));
			graph.addEdge(new E(graph.getVertex(j),graph.getVertex(i),k));
	}
}

class V1 extends V{
	public V1(int i) {
		super(i);
	}

	public Integer features=0;
}