package com.coursera.edu;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AllPairShortest {
	static Graph graph ;
	static int N=6;
	static	PriorityQueue<V> queue = new PriorityQueue<V>();	
	public static void main(String[] args) throws IOException {
					fill();
					int[][] oldM=null;
				try {
					oldM=	graph.getAllPaitShortestFloydMarshall();
				} catch (NegativeCycleException e) {
					e.printStackTrace();
				}				
	for(int[] arr:oldM)
		System.out.println(Arrays.toString(arr));
	}
	
	private static void fill() throws IOException  {
			graph = new Graph(N);
			Scanner scan = new Scanner(new File("in.txt"));
			while(scan.hasNext()){
				fill(scan.nextInt(),scan.nextInt(),scan.nextInt());
			}
			scan.close();
		}
	private static void fill(int i, int j, int k) {
			graph.addEdge(new E(graph.getVertex(i),graph.getVertex(j),k));
		}

}
