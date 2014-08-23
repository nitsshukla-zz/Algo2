package com.coursera.edu;

import java.util.*;

public class bellmanford1 {
					//shailesh-8858141313
	static int N=5,S=3;
	static Map<Integer,nodes> dict  = new HashMap<Integer, nodes>();
	static Map<String,Integer> vals = new HashMap<String,Integer>();
		public static void main(String[] args){
			fill();
			List<Integer> first=new LinkedList<Integer>(),second;
			for(int i=0;i<N;i++)
				if(i!=S)
						first.add(Integer.MAX_VALUE/2);
				else
					first.add(0);
			second  = new LinkedList<Integer>(first);
			first.clear();
			for(int i=1;i<=N;i++){
					for(int i1=0;i1<N;i1++){
						if(i1==0){
							first.add(0);
							continue;
						}
						nodes b = dict.get(i1);int min=second.get(i1);
						for(nodes a:b.source){
							if(vals.get(a.index+"_"+i1)+second.get(a.index)<min)
								min=vals.get(a.index+"_"+i1)+second.get(a.index);
						}
					}
			}
		}

		private static void fill() {
			fill(0,1,1);fill(0,2,2);fill(1,3,4);
			fill(2,4,3);fill(3,4,2);fill(2,1,1);//fill(3,2,-6);
			
		}

		private static void fill(int i, int j, int k) {
			vals.put(i+"_"+j, k);
			nodes a,b;
			if(dict.containsKey(i))
				a=dict.get(i);
			else {
				a = new nodes(i);
			dict.put(i, a);
			}
		
			if(dict.containsKey(j))
				b=dict.get(j);
			else {
				b = new nodes(j);
				dict.put(j, b);
			}
		
		b.source.add(a);a.dest.add(b);
		}
}

class nodes{
	public List<nodes> source;
	public List<nodes> dest;
	public int index=0;
	public nodes parent=null;
	public nodes(){}
	public nodes(int index){
		this.index=index;
		
	}
	
}