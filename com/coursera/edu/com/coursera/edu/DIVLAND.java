package com.coursera.edu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class DIVLAND {
	static List<V> grp1 = new ArrayList<V>();
	static List<V> grp2 = new ArrayList<V>();
	static Queue<Cont> q = null;
	public static void main(String[] args) {
		Graph graph = null;
		
		int ans=setGraph(graph);
		System.out.println(q);
		
		fiddle(q.peek());
		System.gc();
		
		 
	}

	private static void fiddle(Cont peek) {
		
	}

	private static int setGraph(Graph graph) {
		Scanner scan = new Scanner(System.in);int ans=0;
		int n=scan.nextInt(),m=scan.nextInt();
		graph = new Graph(n);q = new PriorityQueue<>(n/2, new Comparator<Cont>() {	@Override public int compare(Cont arg0, Cont arg1) {				return arg0.weight.compareTo(arg1.weight);			}		});
		for(int i=0;i<m;i++){
			graph.addDblEdge(new E(graph.getVertex(scan.nextInt()),graph.getVertex(scan.nextInt()),scan.nextInt()) );
		}

		for(int i=0;i<n;i++)
			if(i<n/2)
				grp1.add(graph.getVertex(i));
			else {
				grp2.add(graph.getVertex(i));
				for(V v:graph.getAdjVertices(graph.getVertex(i)))
					if(grp1.contains(v)){
						q.add(new Cont(v, graph.getVertex(i), graph.edgeMap.get(v.key+"_"+i).weight));
						ans+=graph.edgeMap.get(v.key+"_"+i).weight;
					}
			}
		scan.close();
	return ans;
	}

}

class Cont{
	V left,right;
	Integer weight;
	public Cont(V left, V right, Integer weight) {
		this.left = left;
		this.right = right;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Cont [left=" + left + ", right=" + right + ", weight=" + weight
				+ "]";
	}
	
}