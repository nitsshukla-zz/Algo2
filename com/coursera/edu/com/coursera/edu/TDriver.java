package com.coursera.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TDriver {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for(int i2=0;i2<t;i2++){
		Grph g = new Grph(scan.nextInt(),scan.nextInt(),scan.nextInt());
		for(int i=0;i<g.N;i++){
			g.addNode(new Node1(scan.nextInt(), scan.nextInt()));
		}
		System.out.println(g.sum);
		}
		
	}

}


class Grph{
	int N; List<Node1> nodes = null;
	int sum=0,a,b;
	Grph(int N,int a,int b){this.a=a;this.b=b;		this.N=N;	nodes=new ArrayList<Node1>();}
	void addNode(Node1 n1){
		if(nodes.contains(n1))
			return;
		for(Node1 n:nodes){
			sum+=getD(n1,n);
		}
		nodes.add(n1);
	}
	private int getD(Node1 n1, Node1 n2) {
		return Math.max(a*Math.abs(n1.x-n2.x), b*Math.abs(n1.y-n2.y));
	}
}
class Node1{
	int x,y; Node1(int x,int y){this.x=x;this.y=y;}

	@Override
	public String toString() {
		return "Node1 [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node1 other = (Node1) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}