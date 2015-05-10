package com.coursera.edu.model;

import java.util.LinkedList;
import java.util.List;

import com.coursera.edu.V;

public class Set{
	public List<V> vertices;
	public Set(){
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
	public Set(V v){
		vertices = new LinkedList<V>();
		vertices.add(v);
	}
	
	public  Set clone() {
		Set set = new Set();
		set.vertices = new LinkedList<V>(this.vertices);
		return set;
	}
	@Override
	public String toString() {
		String str="";
		for(V v:vertices)
			str+=v.getKey()+" ";
		return str;
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
	public int toString1() {
		int ans=0;
		for(V v:vertices)
			ans +=mul(v.getKey());
		return ans;
	}
	
	public static int mul(int i) {
		return (2<<(i-1));
	}
	
}