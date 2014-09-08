package com.coursera.edu;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.coursera.edu.model.Set1;

public class TSP4 {
		static Graph graph;
		static LinkedList<Set1> sets,sets11;
		static Map<String, Integer> edgeV = new HashMap<String, Integer>();
		static int N,x;
		static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		static Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
	public static void main(String[] args) throws Exception {
		fill1();
		
		sets = new LinkedList<Set1>();
		for(V v:graph.vertices){
			Set1 set = new Set1(v.key);
			if(v.key==0)
					map.put(set.toString1(), 0);
			else map.put(set.toString1(), graph.MAX_VALUE);
		if(v.key==0)sets.add(set);
		}
		int x1=1;
		//N=5;
		sets.clear();
		for(int i=2;i<=N;i++){
			if(i!=2){
				map.clear();	
				map = new HashMap<Integer, Integer>(map1);
			}
			map1.clear();
			
			x=x1;
			int i5=0;
			String str1=Integer.toBinaryString(x);
			Set1 set11 = getASet("1"+AlgoUtil.repeat0((N-1)-str1.length())+str1);
			Set1 set12 = getASet("1"+AlgoUtil.repeat0((N-1)-str1.length())+str1);
			str1=Integer.toBinaryString(x);
			while(x<(1<<(N-1))){
				System.out.println(i5++);
				updateMap(set11);
				set11=getSet();
			}
			System.out.println();
		x1+=(1<<(i-1));
		System.out.println(x1);
		if(i==N){
			int min=graph.MAX_VALUE;
			for(Integer v:set12.vertices){
				if(v==0)
						continue;
				if (min>map1.get(set12.toString1()+33554432*v)+
						edgeV.get(v+"_"+0))
							min=map1.get(set12.toString1()+33554432*v)+edgeV.get(v+"_"+0);
			}
			System.out.println(min);
		}
		
		}
		
}

	private static Set1 getASet(String string) {
		Set1 set1 = new Set1();
		for(int i=0;i<string.length();i++)
				if(string.charAt(i)=='1')
					set1.vertices.add(i);
		return set1;
	}

	
	private static Set1 getSet() {
		 int u = x & -x;
		   int v = u + x;
		  
		  x = v +(((v^x)/u)>>2);
		  Set1 set1 = new Set1();
			String str1=Integer.toBinaryString(x);
			str1="1"+AlgoUtil.repeat0((N-1)-str1.length())+str1;
			for(int i=0;i<str1.length();i++)
					if(str1.charAt(i)=='1')
						set1.vertices.add(i);
			return set1;
	}

	private static void updateMap(Set1 set) {
		map1.put(set.toString1(), graph.MAX_VALUE);	
		for(Integer v:set.vertices){		
				Set1 set1 = set.getOne(v);
				if(v==0)
					continue;
				int min=graph.MAX_VALUE;
				for(Integer u:set1.vertices){
					int min1 = (map.get(set1.toString1()+33554432*u) + 
							edgeV.get(u+"_"+v));
				if(min>min1)min=min1;
				}
				map1.put(set.toString1()+33554432*v, min);
				
			}
	}

	private static void fill() throws IOException {
		Scanner scan = new Scanner(new File("in.txt"));	
		 N = scan.nextInt();
		graph = new Graph(N);
		int E = scan.nextInt();
		for(int i=0;i<E;i++)
				{
				fill(scan.nextInt()-1,scan.nextInt()-1,scan.nextInt());
				
				}
		scan.close();
	}

	
	private static void fill1() throws Exception {
		Scanner scan = new Scanner(new File("in.txt"));
		N = scan.nextInt();
		graph = new Graph(N);
		List<City> cities = new LinkedList<City>();
		int i=0;
		while(scan.hasNext())
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
		//graph.addEdge(new E(graph.getVertex(n),graph.getVertex(n2),n3));
		//graph.addEdge(new E(graph.getVertex(n2),graph.getVertex(n),n3));
		edgeV.put(n+"_"+n2, n3);
		edgeV.put(n2+"_"+n, n3);
	}

}
