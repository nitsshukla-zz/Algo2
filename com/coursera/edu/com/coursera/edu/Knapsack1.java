package com.coursera.edu;

import java.util.*;

public class Knapsack1 {
	static int[][] arr1 ; 
	public static void main(String[] args) {
		List<Integer[]> arr = new ArrayList<Integer[]>();
		arr.add(new Integer[]{1,2});arr.add(new Integer[]{2,3});
		arr.add(new Integer[]{4,4});arr.add(new Integer[]{2,1});
		int W = 5;
		arr1 = new int[arr.size()+1][W+1];
		for(int i=0;i<=W;i++) 
			arr1[0][i]=0;
		for(int i=1;i<=arr.size();i++){
			Integer[] ar = arr.get(i-1);
			for(int i1=0;i1<=W;i1++){
				int ans=arr1[i-1][i1];
				if(i1>=ar[1]){
					int last = arr1[i-1][i1-ar[1]];
					if(last+ar[0]>arr1[i-1][i1]) ans = last+ar[0];

				}
				arr1[i][i1]=ans;
				System.out.print(ans+" ");
			}
			System.out.println();
		}
		
	}



}
