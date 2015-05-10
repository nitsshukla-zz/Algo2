package com.cormen.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FFTMultiplication {
	public static void main(String[] args) {
		int[] A1 = new int[]{1,2,3,4,5,6,7,8};
		int[] B1 = new int[]{1,2,3,4,5,6,7,8};
		Polynomial A = new Polynomial(A1);Polynomial B = new Polynomial(B1);
		Polynomial A2 = FFT(A);
	}

	private static Polynomial FFT(Polynomial a) {
			if(a.coef.size()==1)
				return a;
			Polynomial[] halves = a.returnHalves();
			Polynomial f_even = FFT(halves[0]);
			Polynomial f_odd = FFT(halves[1]);
			Polynomial ans = new Polynomial(a);
			int x=1;
			for(int i=0;i<a.coef.size()/2;i++){
				ans.coef.set(i, f_even.coef.get(i));
			}
			return null;
	}
}

class Polynomial{
	public Polynomial(int[] a1) {
		for(int a:a1)
			coef.add(new Point1(a,0));
	}
	public Polynomial(){}
	public Polynomial(Polynomial a) {
		this.coef= new ArrayList<Point1>(a.coef);
	}
	public Polynomial[] returnHalves(){
		Polynomial[] halves = new Polynomial[2];halves[0]=new Polynomial();halves[1]=new Polynomial();
		for(int i=0;i<coef.size();i++){System.out.println(i);
				halves[i%2].coef.
							add(coef.get(i));}
		return halves;
	}
	List<Point1> coef = new ArrayList<Point1>();
	@Override
	public String toString() {
		return "Polynomial [coef=" + coef + "]";
	}
	
}
class Point1{
	public Point1(int a, int i) {
		this.real=a;this.imaginary=i;
	}

	int imaginary, real;

	@Override
	public String toString() {
		return real+" i"+imaginary;
	}
	
}