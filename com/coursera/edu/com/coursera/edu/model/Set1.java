package com.coursera.edu.model;

import java.util.LinkedList;
import java.util.List;

import com.coursera.edu.V;

public class Set1{
	public List<Integer> vertices;
	public Set1(){
		vertices = new LinkedList<Integer>();
	}
	public Set1 getOne(Integer v) {
		Set1 set = this.clone();
		set.vertices.remove(v);
		return set;
	}
	public Integer getLast() {
		
		return vertices.get(vertices.size()-1);
	}
	public Set1(Integer v){
		vertices = new LinkedList<Integer>();
		vertices.add(v);
	}
	
	public  Set1 clone() {
		Set1 set = new Set1();
		set.vertices = new LinkedList<Integer>(this.vertices);
		return set;
	}
	@Override
	public String toString() {
		String str="";
		for(Integer v:vertices)
			str+=v+" ";
		return str;
	}
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		Set1 set = ((Set1)obj);
		if(this.vertices.size()!=set.vertices.size())
			return false;
		for(int i=0;i<set.vertices.size();i++)
			if(set.vertices.get(i)!=this.vertices.get(i))
				return false;
		return true;
	}
	public int toString1() {
		int ans=0;
		for(Integer v:vertices)
			ans +=mul(v);
		return ans;
	}
	
	public static int mul(int i) {
		return (2<<(i-1));
	}
	
}