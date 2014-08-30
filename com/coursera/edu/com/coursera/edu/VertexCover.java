package com.coursera.edu;

import java.io.*;
import java.util.*;

public class VertexCover {
static Graph graph;
static int N;
	public static void main(String[] args) throws IOException {
		fill();
			List<V> list =getCover(3,graph);
			if(list==null)
					{
					System.out.println("NULL");
					return;
					}
			for(V v:list)
				System.out.println(v);
	}
	

	private static List<V> getCover(int i, Graph graph2) {
		List<V> list  = new LinkedList<V>();
		//if(i==0) return list;
		E e=null;
		if(i==0 && graph2.edgeMap.size()!=0)
				return null;
		if(i==0 && graph2.edgeMap.size()==0)
				return list;
		try {
			e = graph2.getRandomEdge();
		} catch (GraphException e1) {
			e1.printStackTrace();
			return list;
			
		}
		Graph graph1 = (Graph) graph2.clone();
		graph1.removeVertex(e.getU().key);
		List<V> list1 = getCover(i-1, graph1);
		if(list1!=null && list1.size()==i-1)
		{
			list1.add(e.getU());
			return list1;
		}
		System.out.println("hi");
		 graph1 = (Graph) graph2.clone();
		graph1.removeVertex(e.getV().key);
		 list1 = getCover(i-1, graph1);
		if(list1 !=null && list1.size()==i-1)
		{
			list1.add(e.getV());
			return list1;
		}
				
		return null;
	}

	private static void fill() throws IOException  {
		Scanner scan = new Scanner(new File("in.txt"));	
		N = scan.nextInt();
		int E = scan.nextInt();
			graph = new Graph(N);
			
			
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
