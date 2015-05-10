package com.cormen.edu;

import java.util.Arrays;

public class MATRICES {
	final static int n =2;
	public static void main(String[] args) {
		MATRIX a = new MATRIX(n);MATRIX l = new MATRIX(n);MATRIX u = new MATRIX(n);
		a.matrix[0][0]=2;a.matrix[0][1]=1;
		a.matrix[1][0]=1;a.matrix[1][1]=3;
		for(int i=0;i<n;i++){
			u.matrix[i][i]=a.matrix[i][i];
			for(int i1=i+1;i1<n;i1++){
				l.matrix[i1][i]=a.matrix[i1][i]/u.matrix[i][i];
				u.matrix[i][i1]=a.matrix[i][i1];
			}
			for(int i1=i+1;i1<n;i1++)
				for(int i2=i+1;i2<n;i2++)
						a.matrix[i1][i2]=a.matrix[i1][i2]-l.matrix[i1][i]*u.matrix[i][i2];
		}
	System.out.println(l);System.out.println();
	System.out.println(u);System.out.println();
	System.out.println(a);
	}

}

 class MATRIX {
	int[][] matrix=null;
	public MATRIX(int n) {
	matrix = new int[n][n];
	}
	@Override
	public String toString() {
		String ans="";
		for(int i=0;i<matrix.length;i++)
			ans += Arrays.toString(matrix[i])+"\n";
		return ans;
	}
	
}