package com.cormen.edu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ConvexHull {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("No. of points to enter:");int n=scan.nextInt();System.out.println();
		List<Point> list = new ArrayList<Point>();
		for(int i=0;i<n;i++)
			list.add(new Point(scan.nextInt(), scan.nextInt()));
		Collections.sort(list, new Comparator<Point>() {	@Override	public int compare(Point paramT1, Point paramT2) {	return paramT1.theta.compareTo(paramT2.theta)==0?paramT1.r.compareTo(paramT2.r):paramT1.theta.compareTo(paramT2.theta);			}		});
		removeCollinearPts(list);
		System.out.println((getCon(list)));
		scan.close();
	}

	private static void removeCollinearPts(List<Point> list) {
		List<Point> list1 = new ArrayList<Point>(list);
		for(int i=1;i<list1.size();i++){
			//System.out.println(list1.get(i).theta+" "+list1.get(i-1).theta);
			if(list1.get(i).theta.compareTo(list1.get(i-1).theta)==0)
				{
				//System.out.println("duplicate..."+list1.get(i-1));
				list.remove(list1.get(i-1));
				}
		}
	}

	private static Stack<Point> getCon(List<Point> list) {
		Stack<Point> st = new Stack<Point>();
		st.push(list.get(0));st.push(list.get(1));st.push(list.get(2));
		for(int i=3;i<list.size();i++){
			while(st.size()>=2&&getArea(st.get(st.size()-2),st.peek(),list.get(i)))
				st.pop();
			st.push(list.get(i));
		}
		return st;
	}

	private static boolean getArea(Point a, Point b, Point c) {
		//System.out.println(a+"  "+b+" "+c);
		double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
		//System.out.println(area2);
		if(area2<=0) return true;
		return false;
	}

}

class Point{
	int x,y;
	Double r,theta;
	Point (int x,int y){this.x=x;this.y=y;
	r =Math.sqrt(x*x+y*y);
	if(x!=0)
		theta = Math.tanh((double)y/x);
	else{
		if(y==0)
			theta=-12D;
		else theta = Math.abs(y)* Math.PI/(2*y);
	}

	if(theta<0 && x<0)
		theta=Math.abs(theta)+Math.PI/2;
	else	if(theta<0 && y<0)
		theta=Math.abs(theta)+3*Math.PI/2;
	else if(x<0 || y<0)
		theta+=Math.PI;
	}
	@Override
	public String toString() {
		return "" + x + ", " + y + ", " + r + " /_" + theta*180/Math.PI
				+ "\n";
	}

}