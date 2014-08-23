package com.coursera.edu;

import java.util.*;

public class FloydWarshall {
	static List<LinkedList<Integer>> edges;
	static int N=5;
	static Map<String,Integer> vals = new HashMap<String, Integer>();
	public static void main(String[] args) {
			fill();
			for(int k=0;k<N;k++){
				int[][] D = new int[N][N];
				for(int i=0;i<N;i++){
					for(int i1=0;i1<N;i1++)
							{
							
							int arg0=vals.get(i+"_"+i1)==null?Integer.MAX_VALUE:vals.get(i+"_"+i1);
							int arg1=vals.get(i+"_"+k)==null?Integer.MAX_VALUE:vals.get(i+"_"+k)+
									(vals.get(k+"_"+i1)==null?Integer.MAX_VALUE:vals.get(k+"_"+i1));
							D[i][i1]=Math.min(arg0, arg1);
							vals.put(i+"_"+i1, D[i][i1]);
							///if(k==N-1)
										System.out.print(D[i][i1]+" ");
								}
				System.out.println();
				}
			}
	}
	private static void fill() {
		edges = new LinkedList<LinkedList<Integer>>();
		for(int i=0;i<N;i++)
						edges.add(new LinkedList<Integer>());
	fill(0,1,1);fill(0,2,2);fill(1,3,4);
	fill(2,4,3);fill(3,4,2);fill(2,1,1);////fill(3,2,-6);
	}
	private static void fill(int i, int j, int k) {
		edges.get(j).add(i);
		vals.put(i+"_"+j, k);
	}
}
