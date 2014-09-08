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
					map1.put(0, graph.MAX_VALUE);
				map.put(set, map1);
				
				}
			
			HashMap<Set, HashMap<Integer, Integer>> map1 = new HashMap<Set, HashMap<Integer,Integer>>();
			int ii=0;
															for(int i=0;i<N-1;i++){
				
				sets = getSets();
				System.out.println(i+"  "+sets.size());
				//System.out.println(sets);
				for(Set set:sets){
					if(++ii % 10000==0)
						System.out.println(ii);
					HashMap<Integer, Integer> mapS = new HashMap<Integer, Integer>();
					mapS.clear();
					for(V v:set.vertices){
						if(v.key==0)
								{
									mapS.put(0, graph.MAX_VALUE);
									continue;
								}
						int min=graph.MAX_VALUE;
						Set setI = set.getOne(v);
						HashMap<Integer, Integer> mapI = map.get(setI);
						for(V v1:setI.vertices){
							if(graph.edgeMap.containsKey(v1.key+"_"+v.key)){
								//System.out.println(v1);
								int temp = graph.edgeMap.get(v1.key+"_"+v.key).weight + mapI.get(v1.getKey());
								if(temp<min)
									min = temp;
							}
						}
						mapS.put(v.key, min);
					}
					map1.put(set, mapS);
				//System.out.println(set+"!!!!"+mapS);
				if(i==N-2)	{
					HashMap<Integer, Integer> mapI = map1.get(set);
					int min = graph.MAX_VALUE; 
					for(V v:set.vertices)
					{
						
						if(v.key!=0 && graph.edgeMap.containsKey(v.key+"_"+"0")){
							int temp = mapI.get(v.key)+graph.edgeMap.get(v.key+"_"+"0").weight;
							if(temp<min)
								min = temp;
						}
					}
					System.out.println(set+"~~~"+min);
				}
			}
			map.clear();
			map = new HashMap<>(map1);	
			map1.clear();
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
		sets.clear();
		return sets1;
	}

	private static void fill() throws Exception {
		Scanner scan = new Scanner(new File("in.txt"));
		N = scan.nextInt();
		graph = new Graph(N);
		List<City> cities = new LinkedList<City>();
		int i=0;
//		int E = scan.nextInt();
		while(scan.hasNext())
			//fill(scan.nextInt()-1,scan.nextInt()-1,scan.nextInt());
			cities.add(new City(i++,scan.nextDouble(),scan.nextDouble()));
		for(City c:cities)
			for(City c1:cities)
				if(c.key!=c1.key)fill(c.key,c1.key,(int)dist(c,c1));
	
			}

	private static long dist(City c, City c1) {
		Double f = Math.pow(c.x-c1.x,2);
		Double f1 = Math.pow(c.y-c1.y,2);
		
		return Math.round(Math.sqrt(f+f1));
	}

	private static void fill(int n, int n2, int n3) {
		graph.addEdge(new E(graph.getVertex(n),graph.getVertex(n2),n3));
		graph.addEdge(new E(graph.getVertex(n2),graph.getVertex(n),n3));
	}

}

class City{
	int key;
	Double x,y;
	public City(int key,Double x,Double y){
		this.x=x;this.y=y;this.key=key;
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