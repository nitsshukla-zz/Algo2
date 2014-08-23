package com.coursera.edu;

import java.util.*;

public class OptimalBinarySearchTree {
	static Map<Integer, Double> map = new HashMap<Integer, Double>(); 
	static double[][] arr;
	public static void main(String[] args) {
			Node root = new Node(5);
			root.setLeft(new Node(3));
			root.getLeft().setLeft(new Node(2));
			root.getLeft().setRight(new Node(4));
			root.setRight(new Node(8));
			root.getRight().setLeft(new Node(7));
			root.getRight().setRight(new Node(10));
	map.put(3, 0.04);
	map.put(1, 0.4);map.put(0, 0.05);map.put(2, 0.08);
	map.put(5, 0.1);map.put(4, 0.1);map.put(6, 0.23);
	Object[] nodes =  inOrder(root).toArray();
	arr = new double[nodes.length][nodes.length];
	for(int i10=0;i10<nodes.length;i10++){
		int i=0,i1=i10;
		while(i<nodes.length-i10)
		{
			double min=0;
			double min1=0;
			for(int i2=i;i2<=i1;i2++){
				arr[i][i1]+=(double)map.get(i2);
				min1 = gets(i,i2-1)+gets(i2+1,i1);
			
				if(i2==i || min>min1)
						min=min1;
			}
			arr[i][i1]+=min;
		System.out.println(i+"  "+ i1+"   "+arr[i][i1]);
		i++;i1++;
		}
	}
	}
	private static double gets(int i, int j) {
if(i>j )
		return 0;
	return arr[i][j];
	}
	private static ArrayList<Node> inOrder(Node root) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		/*if(root.getLeft()==null && root.getRight()==null)
		{
			nodes.add(root);
			return nodes;
		}*/
		if(root.getLeft()!=null){
			nodes.addAll(inOrder(root.getLeft()));
		}
		nodes.add(root);
		if(root.getRight()!=null){
			nodes.addAll(inOrder(root.getRight()));
		}
		return nodes;
	}

}

