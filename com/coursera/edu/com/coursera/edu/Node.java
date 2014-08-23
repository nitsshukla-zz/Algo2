package com.coursera.edu;

public class Node {
	private int root;
	private Node left;
	private Node right;
	
	
	public Node(int root){
		this.root=root;
	}
	
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	@Override
	public String toString() {
		return "Node [root=" + root + ", left=" + left + ", right=" + right
				+ "]";
	}

	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
}
