package com.coursera.edu;

import java.io.FileReader;
import java.util.*;

public class PrimsAlgo {
		static Map<String,Integer> val = new HashMap<String, Integer>();
		static Map<Integer,V> map = new HashMap<Integer, V>();
		static int N=5;
		static BinaryHeap<V> heap;
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(new FileReader("in.txt"));
		N = scan.nextInt();
		int E = scan.nextInt();
		heap = new BinaryHeap<V>();		
		for(int i=1;i<=N;i++) {
			map.put(i, new V(i));
			heap.add(map.get(i));
		}
/*				puts(1,2,1);puts(1,3,2);
				puts(2,3,3);puts(2,4,2);
				puts(3,4,1);puts(3,5,3);
				puts(4,5,1);
*/
		for(int i=0;i<E;i++){
			int x = scan.nextInt();
			int y = scan.nextInt();
			int w = scan.nextInt();
			puts(x,y,w);
		}
				ArrayList<V> arr = new ArrayList<V>();
				map.get(1).key=0;
				doo(arr,1);
				while(arr.size()!=N){
					V v = heap.peek();
					doo(arr,v.name);
				}
				long cost = 0;
				for(V v: arr) cost+=v.key;
				System.out.println(cost);
				scan.close();
	}
	private static void doo(ArrayList<V> arr, int i) {
		V v1 = map.get(i);	
		heap.remove(v1);
		arr.add(v1);
		List<V> parent = v1.adj;
		for(V v: parent){
			if(!arr.contains(v) && v.key>val.get(v.name+"_"+v1.name)){
				v.key=val.get(v.name+"_"+v1.name);
				heap.remove(v);
				heap.add(v);
			}
		}
		
	}
	private static void puts(int i, int j, int k) {
				V v1 = map.get(i);
				V v2 = map.get(j);
				v1.adj.add(v2);
				v2.adj.add(v1);
				val.put(i+"_"+j, k);
				val.put(j+"_"+i, k);
	}
		
}

class V implements Comparable<V>{
	Integer key=Integer.MAX_VALUE;
	Integer name;
	List<V> adj;
	public V(int n){name=n;
	adj=new LinkedList<V>();} 
	
	@Override
	public int compareTo(V o) {
		return key.compareTo(o.key);
	}

	@Override
	public String toString() {
		return "V [key=" + key + ", name=" + name + "]";
	}
	
}
