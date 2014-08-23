package com.coursera.edu;

public class E {
V v,u;
Integer weight;
public E(){}
public E(V u,V v, int w){
	this.u=u;
	this.v=v;
	this.weight=w;
}
public E(V u,V v){
	this.u=u;
	this.v=v;
}

public int hashCode() {
	return (u.getKey()+"_"+v.getKey()).hashCode();
}
public String toString() {
	return "{\"u\":\""+u.getKey()+"\",\"v\":\""+v.getKey()+"\",\"w\":\""+weight+"\"}";
}
public V getV() {
	return v;
}
public void setV(V v) {
	this.v = v;
}
public V getU() {
	return u;
}
public void setU(V u) {
	this.u = u;
}
public Integer getWeight() {
	return weight;
}
public void setWeight(int weight) {
	this.weight = weight;
}

}
