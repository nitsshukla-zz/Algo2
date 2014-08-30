package com.coursera.edu;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AllPairShortest {
	static Graph graph ;
	//NULL,NULL,-19
	static int N=6;
	static	PriorityQueue<V> queue = new PriorityQueue<V>();	
	public static void main(String[] args) throws IOException {
					fill();
					try {
						int ans=Integer.MAX_VALUE;
						int[][] arr = graph.getAllPaitShortestFloydMarshall();
						for(int i=0;i<N;i++)
							for(int i1=0;i1<N;i1++){
							if(ans>arr[i][i1])
									ans=arr[i][i1];
						}
						System.out.println(ans);
					} catch (NegativeCycleException e) {
						e.printStackTrace();
					}

	}
	
	private static void fill() throws IOException  {
		Scanner scan = new Scanner(new File("in.txt"));	
		N = scan.nextInt();
		int E = scan.nextInt();
			graph = new Graph(N);
			
			
			for(int i=0;i<E;i++){
				fill(scan.nextInt()-1,scan.nextInt()-1,scan.nextInt());
			}
			scan.close();
		}
	private static void fill(int i, int j, int k) {
			graph.addEdge(new E(graph.getVertex(i),graph.getVertex(j),k));
		}

}
