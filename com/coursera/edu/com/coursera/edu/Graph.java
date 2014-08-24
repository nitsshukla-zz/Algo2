package com.coursera.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
	final Integer MAX_VALUE=Integer.MAX_VALUE/100;
	List<LinkedList<E>> edges = new LinkedList<LinkedList<E>>();
	List<V> vertices = new ArrayList<V>();
	Map<String, E> edgeMap = new HashMap<String,E>();
	Boolean negativeEdge = false;
	int N=0;
	public Graph(){}
	public Graph(int N){
		this.N=N;
		for(int i=0;i<N;i++){
			vertices.add(new V(i));
			edges.add(new LinkedList<E>());
		}
	}	
	public void addEdge(E e) {
		edges.get(e.getU().getKey()).add(e);
		edgeMap.put(e.u.key+"_"+e.v.key, e);
		if(e.weight<0)
			negativeEdge=true;
	}
	public V getVertex(int i) {
		return vertices.get(i);
	}
	public List<E> getGoingEdges(int i) {
		return edges.get(i);
	}
	public  List<V> getAdjVertices(V u) {
		List<E> adj = edges.get(u.getKey());
		List<V> vertices = new LinkedList<V>();
		for(E e:adj){
			vertices.add(e.getV());
		}
		return vertices;
	}
	public boolean relax(V u, V v) {
		E e = edgeMap.get(u.key+"_"+v.key);
		 if(v.val>u.val+e.weight)
			{
			 v.pred=u;
			 u.succ=v;
			 v.val=u.val+e.weight;
			return true;
			}
		 return false;
	}
	public int getShortestDistance(int i, int j) {
		if(!negativeEdge)
			this.getShortestDjik(i);
		else
			this.getShortestBellFord(i);
		return vertices.get(j).val;
	}
	public Integer[] getShortestDistancePath(int i, int j) {
		if(!negativeEdge)
			this.getShortestDjik(i);
		else
			this.getShortestBellFord(i);
		V v = vertices.get(j);
		List<Integer> res = new ArrayList<Integer>();
		while(!(v.pred==vertices.get(i) || v.pred==null))
			{
			res.add(v.key);
			v=v.pred;
			}
		if(v.pred==vertices.get(i))
				res.add(v.key);
		res.add(i);
		Collections.reverse(res);
		return res.toArray(new Integer[res.size()]);
	}
	public void initializeSingleSource(V s){
		for(V v:vertices){
			v.pred=null;
			v.val=MAX_VALUE;
	}
	s.val=0;
	}
	public List<V> getShortestBellFord(int s1) {
		V s = vertices.get(s1);
		initializeSingleSource(s);
		for(int i=0;i<vertices.size()-1;i++){
			for(E e : edgeMap.values()){
				this.relax(e.u, e.v);
			}
		}
		return vertices;
	}
	public List<V> getShortestDjik(int i) {
		V s = this.getVertex(i);
		PriorityQueue<V> queue = new PriorityQueue<V>();
		initializeSingleSource(s);
		queue.addAll(this.vertices);
		
		List<V> ans = new LinkedList<V>();
		
		while(!queue.isEmpty()){
			V u = (queue.poll());
		
		List<V> adj = this.getAdjVertices(u);
					ans.add(u);
		for(V v:adj){
			if(queue.contains(v)&&this.relax(u,v))
				{
					queue.remove(v);
					queue.add(v);
				}
		}
		}
		return ans;
	}
	public int[][] getWeightMatrix() {
		int[][] oldM = new int[vertices.size()][vertices.size()];
		int N=vertices.size();
		for(int i=0;i<N;i++)
			for(int i1=0;i1<N;i1++)
					if(i!=i1)oldM[i][i1]=this.MAX_VALUE;
					else oldM[i][i1]=0;
		for(E e:this.edgeMap.values()){
			oldM[e.getU().getKey()][e.getV().getKey()]=e.getWeight();
	}
		return oldM;
}
	public int[][] getAllPairShortestDjik() {
		int[][] pathMatrix = new int[N][N];
		for(int i=0;i<N;i++){
			this.getShortestDjik(i);
			for(int i1=0;i1<N;i1++){
				pathMatrix[i][i1]=this.getVertex(i1).val;
			}
		}
	return pathMatrix;
	}
	public int[][] getAllPaitShortestFloydMarshall() throws NegativeCycleException{
		int[][] oldM = null;
		oldM=this.getWeightMatrix();
		for(int i=0;i<N;i++){
			int[][] newM = new int[N][N];
			for(int i1=0;i1<N;i1++){
				for(int i2=0;i2<N;i2++){
					newM[i1][i2]=Math.min(oldM[i1][i2], oldM[i1][i]+oldM[i][i2]);
					if(newM[i1][i2]<oldM[i1][i2])
							vertices.get(i2).pred=vertices.get(i);
				}
			}
		oldM=newM;
		}
	for(int i=0;i<N;i++)
			if(oldM[i][i]<0)
					throw new NegativeCycleException();
		return oldM;
	}
}
