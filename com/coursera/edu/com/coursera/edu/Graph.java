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
		if(e.getU() instanceof V1)
				((V1)(e.getU())).features++;
		if(e.getV() instanceof V1)
			((V1)(e.getV())).features++;
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
	public int getShortestDistance(int i, int j) throws NegativeCycleException {
		if(!negativeEdge)
			this.getShortestDjik(i);
		else
			this.getShortestBellFord(i);
		return vertices.get(j).val;
	}
	public Integer[] getShortestDistancePath(int i, int j) throws NegativeCycleException {
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
	public List<V> getShortestBellFord(int s1) throws NegativeCycleException {
		V s = vertices.get(s1);
		initializeSingleSource(s);
		for(int i=0;i<vertices.size()-1;i++){
			for(E e : edgeMap.values()){
				this.relax(e.u, e.v);
			}
		}
		List<V> h = new LinkedList<V>();
		for(V v:vertices)
				h.add(v.clone());
		
		for(E e:edgeMap.values())
			this.relax(e.u, e.v);
		for(int i=0;i<vertices.size();i++)
			if(vertices.get(i).val!=h.get(i).val)
					throw new NegativeCycleException();
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
public int[][] getAllPairShortestJohnson() throws NegativeCycleException{
	this.getShortestBellFord(N-1);

	this.vertices.remove(this.N-1);
	this.N-=1;
	this.edges.remove(this.N);
	List<String> list = new LinkedList<String>();
	for(String e:this.edgeMap.keySet())
		if(this.edgeMap.get(e).u.key==N)
					list.add(e);
	
	for(String e:list)
		this.edgeMap.remove(e);
	
	for(E e:this.edgeMap.values())
			e.weight+=e.u.val-e.v.val;

	List<V> h = new LinkedList<V>();
	
	for(V v:this.vertices)
		h.add(v.clone());
	int[][] arr =this.getAllPairShortestDjik();
	for(int i=0;i<this.N;i++)
		for(int i1=0;i1<this.N;i1++)
			arr[i][i1]-=h.get(i).val-h.get(i1).val;

	return arr;
	
}
public E getRandomEdge() throws GraphException{
	for(E e: edgeMap.values())
			return e;
throw new GraphException();
}

protected Graph clone()  {
	Graph graph = new Graph(N);
	graph.edges=new LinkedList<>();
	for(LinkedList<E> es:edges)
			graph.edges.add(new LinkedList<E>(es));
	graph.vertices = new LinkedList<V>(vertices);
	graph.edgeMap = new HashMap<String,E>(edgeMap);
	graph.negativeEdge=negativeEdge;
	return graph;
}

public void removeVertex(int i){
	
	for(LinkedList<E> es : edges){
		List<E> liss = new LinkedList<E>();
			for(E e: es)
				{if(e.u.key==i || e.v.key==i)
						{
						liss.add(e);
						removeEdgeFromMap(e);
						}
				else if(e.weight<0)
					negativeEdge=true;
				}
			for(E e:liss)
				{
				es.remove(e);
				if(e.getU() instanceof V1)
						((V1)e.getU()).features--;

				if(e.getV() instanceof V1)
						((V1)e.getV()).features--;
				}
	}
		for(E e:edges.get(i)){
			removeEdgeFromMap(e);
		}
		edges.get(i).clear();
		for(E e:edges.get(i)){
			if(e.getU() instanceof V1)
				((V1)e.getU()).features--;

		if(e.getV() instanceof V1)
				((V1)e.getV()).features--;
		}
		
		//vertices.remove(i);
		//N-=1;
}
public void removeEdgeFromMap(E e){
	///System.out.println(e);
	List<String> list= new LinkedList<String>();
	for(String s:edgeMap.keySet())
		if(edgeMap.get(s).equals(e))
					list.add(s);
	for(String s:list)
							edgeMap.remove(s);
}
}
