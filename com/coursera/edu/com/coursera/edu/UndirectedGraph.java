package com.coursera.edu;

import java.util.*;

public class UndirectedGraph {
public List<LinkedList<Integer>> edges;
public int N;
public UndirectedGraph(int N){
	this.N=N;
	edges = new LinkedList<>();
	for(int i=0;i<N;i++)
			edges.add(new LinkedList<Integer>());
}
public void addEdge(int i,int i1){
	edges.get(i).add(i1);
	edges.get(i1).add(i);
}
public UndirectedGraph clone(){
	UndirectedGraph ug = new UndirectedGraph(N);
	
			
	return ug;
}
}
