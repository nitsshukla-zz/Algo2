package com.coursera.edu;

import java.util.LinkedList;
import java.util.List;

public class V implements Comparable<V>{
int key;
V pred;
V succ;
Integer val = Integer.MAX_VALUE;
public V(){}
public V(int a){key=a;}

public int getKey() {
	return key;
}
public void setKey(int key) {
	this.key = key;
}

public boolean equals(Object o) {
	return this.key==((V)o).getKey();
}
@Override
public int compareTo(V o) {
	return this.val.compareTo(o.val);
}
@Override
public String toString() {
	String pred1="",succ1="";
	try{
		pred1=""+pred.key;
	}
	catch(Exception e){
		
	}
	try{
		succ1=""+succ.key;
	}
	catch(Exception e){
		
	}
	return "V [key=" + key + ", pred=" + pred1 + ", succ=" + succ1 + ", val="
			+ val + "]";
}


}
