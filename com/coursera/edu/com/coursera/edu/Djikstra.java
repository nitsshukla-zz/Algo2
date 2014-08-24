package com.coursera.edu;


import java.util.*;
import java.io.*;

public class Djikstra {
	
	static Graph graph ;
	static int N=6;
	static	PriorityQueue<V> queue = new PriorityQueue<V>();	
	public static void main(String[] args) throws Exception{
					fill();
					System.out.println(graph.getShortestDistance(2, 3));
	}
	private static void fill() throws Exception {
			graph = new Graph(N);
			/*fill(0,1,1);fill(0,2,5);fill(1,3,2);fill(1,2,1);
			fill(3,4,1);fill(2,4,6);*/
			Scanner scan = new Scanner(new File("in.txt"));
			while(scan.hasNext()){
				fill(scan.nextInt(),scan.nextInt(),scan.nextInt());
			}
			scan.close();
		}
	private static void fill(int i, int j, int k) {
//			System.out.println(i+" "+j+" "+k);
			graph.addEdge(new E(graph.getVertex(i),graph.getVertex(j),k));
		}
}
