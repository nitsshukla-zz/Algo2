package com.coursera.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
	final Integer MAX_VALUE=Integer.MAX_VALUE/2;
	List<LinkedList<E>> edges = new LinkedList<LinkedList<E>>();
	List<V> vertices = new LinkedList<V>();
	Map<String, E> edgeMap = new HashMap<String,E>();
	Boolean negativeEdge = false;
	public Graph(){}
	public Graph(int N){
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
}
