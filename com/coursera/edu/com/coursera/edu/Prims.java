package com.coursera.edu;

import java.util.LinkedList;
import java.util.Map;

public class Prims {
			static LinkedList<LinkedList<Integer>> edge;
			static int[][] weight;
			static int N = 6;
	public static void main(String[] args) {
			//buildGraph();
		try{
			String str1 = null;
		
		str1=str1.toString();
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void buildGraph() {
			edge = new LinkedList<LinkedList<Integer>>();
			for(int i=0;i<N;i++) edge.add(new LinkedList<Integer>());
			put(1,4);put(1,2);put(1,3);
			put(2,3);put(2,5);
			put(4,3);put(2,5);
			
	}
	private static void put(int i, int j) {
			edge.get(i-1).add(j-1);
			edge.get(j-1).add(i-1);
	}

}
