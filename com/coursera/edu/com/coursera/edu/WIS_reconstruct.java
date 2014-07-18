package com.coursera.edu;

import java.util.ArrayList;

public class WIS_reconstruct {
		public static void main(String[] args){
			int[] arr = new int[]{1,4,5,4};
			int[] arr1 = new int[arr.length];
			for(int i=0;i<arr.length;i++){
				if(i==0){
					arr1[0]=0;
					continue;
				}
				if(i==1){
					arr1[1]=arr[1];
					continue;
				}
				arr1[i] = Math.max(arr1[i-1], arr1[i-2]+arr[i]);
			}
		/*	ArrayList<Integer> arr2 = new ArrayList<Integer>();
			int i=arr.length-1,cost=0;
			while(i>=2){
				if(arr1[i-1]>(arr1[i-2]+arr[i])){
					i--;
				}
				else{
					arr2.add(arr[i]);
					i=i-2;
					cost+=arr[i];
				}
			}*/
		System.out.println(arr1[arr.length-1]);
		}

}
