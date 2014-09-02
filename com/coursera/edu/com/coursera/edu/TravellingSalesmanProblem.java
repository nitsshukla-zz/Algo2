package com.coursera.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TravellingSalesmanProblem {
		static Graph graph;
		static List<Set> sets,sets11;
		static int N;
	public static void main(String[] args) throws Exception {
			fill();
			HashMap<Set, HashMap<Integer, Integer>> map = new HashMap<Set, HashMap<Integer,Integer>>();
			sets = new LinkedList<Set>();
			for(V v:graph.vertices)
				{
				HashMap<Integer, Integer> map1 = new HashMap<>();
				Set set =(new Set(v));
				if(v.getKey()==0)
					{
					map1.put(0, 0);
					sets.add(set);
					}
				else
					map1.put(0, Integer.MAX_VALUE);
				map.put(set, map1);
				
				}
			sets11 = new LinkedList<Set>();
			for(Set s: sets)
				sets11.add(s.clone());
			
			
															for(int i=0;i<N-1;i++){
				sets = getSets();
				for(Set set:sets){
					HashMap<Integer, Integer> mapS = new HashMap<Integer, Integer>();
					for(V v:set.vertices){
						if(v.getKey()==0)
								continue;
					Set setI = set.getOne(v);
					HashMap<Integer, Integer> mapI = map.get(setI);
					List<V> vI = (graph.getAdjVertices(v));
					int min = Integer.MAX_VALUE;
					for(V vv:vI)
						if(set.vertices.contains(vv) && vv!=v && min>graph.edgeMap.get(vv.key+"_"+v.key).weight)
							min=mapI.get(vv.getKey())+graph.edgeMap.get(vv.key+"_"+v.key).weight;
					
						mapS.put(v.getKey(), min);
					}
					map.put(set, mapS);
					System.out.println(set+"~~~");
			}
				

}
	}

	private static List<Set> getSets() {
		List<Set> sets1 = new LinkedList<Set>();
		for(Set set:sets){
			int i=set.getLast().key;
			for(int i1=i+1;i1<N;i1++)
					{
					Set set1 = set.clone();	
					set1.vertices.add(graph.getVertex(i1));
					sets1.add(set1);
					}
		}
		return sets1;
	}

	private static void fill() throws Exception {
		Scanner scan = new Scanner(new File("in.txt"));
		N = scan.nextInt();
		graph = new Graph(N);
		int E = scan.nextInt();
		for(int i=0;i<E;i++)
			fill(scan.nextInt()-1,scan.nextInt()-1,scan.nextInt());
	}

	private static void fill(int n, int n2, int n3) {
		graph.addEdge(new E(graph.getVertex(n),graph.getVertex(n2),n3));
		graph.addEdge(new E(graph.getVertex(n2),graph.getVertex(n),n3));
	}

}

class Set{
	List<V> vertices;
	Set(){
		vertices = new LinkedList<V>();
	}
	public Set getOne(V v) {
		Set set = this.clone();
		set.vertices.remove(v);
		return set;
	}
	public V getLast() {
		
		return vertices.get(vertices.size()-1);
	}
	Set(V v){
		vertices = new LinkedList<V>();
		vertices.add(v);
	}
	
	protected Set clone() {
		Set set = new Set();
		set.vertices = new LinkedList<V>(this.vertices);
		return set;
	}
	@Override
	public String toString() {
		String str="[";
		for(V v:vertices)
			str+=v.key+" ";
		return str+"]";
	}
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		Set set = ((Set)obj);
		if(this.vertices.size()!=set.vertices.size())
			return false;
		for(int i=0;i<set.vertices.size();i++)
			if(set.vertices.get(i)!=this.vertices.get(i))
				return false;
		return true;
	}
	
	
}