package com.coursera.edu;

import java.util.*;

public class Bellman_Ford {
	
	static List<LinkedList<Integer>> edges ;
	static Map<String,Integer> vals = new HashMap<String,Integer>();
	static int N=5;
	public static void main(String[] args) {
		fill();
List<Integer> first = new LinkedList<Integer>();
List<Integer> second = new LinkedList<Integer>();
		for(int i=0;i<N;i++)
			  if(i==0)
				  		first.add(0);
			  else
				  		first.add(Integer.MAX_VALUE/2);
		boolean flag=false,flag1=false;
		second = new LinkedList<Integer>(first);
		first.clear();
		first.add(0);
		for(int i=1;i<=N;i++){
			
			flag1=false;
			for(int i1=1;i1<N;i1++){
					LinkedList<Integer> neigh = edges.get(i1);
					int min = second.get(i1);
					for(Integer n:neigh){
						if(second.get(n)+vals.get(n+"_"+i1) < min)
								min=second.get(n)+vals.get(n+"_"+i1);
					}
					if(min!=second.get(i1))
						flag1=true;
					first.add(min);
			System.out.print( min+"             ");
			}
			
			if(!flag1)
				break;
			if(i!=N){
				second = new LinkedList<Integer>(first);
				first.clear();
	first.add(0);

			}
			
			System.out.println();
		}
		for(int i1=0;i1<N;i1++)
			if(second.get(i1)!=first.get(i1)){
					System.out.println("Negative Cycle Detected");
					break;
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
