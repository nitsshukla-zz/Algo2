package com.coursera.edu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class DiffWeight {

	public static void main(String[] args) throws Exception {
			Scanner scan = new Scanner(new FileReader("in.txt"));
			ArrayList<Integer[]> arr  = new ArrayList<Integer[]>();
			int N = scan.nextInt();
			for(int i=0;i<N;i++){
				Integer[] arr1 = new Integer[2];
				arr1[0] = scan.nextInt();
				arr1[1] = scan.nextInt();
				arr.add(arr1);
			}
			Collections.sort(arr, new Comparator<Integer[]>() {

				@Override
				public int compare(Integer[] o1, Integer[] o2) {
					/*Integer diff = o1[0]-o1[1];
					Integer diff1 = o2[0]-o2[1];
					if(diff!=diff1) return diff1.compareTo(diff);
					return o2[0].compareTo(o1[0]);*/
				Float f1 = (float) o1[0]/o1[1];
				Float f2 = (float) o2[0]/o2[1];
				return f2.compareTo(f1);
				}
			});
			Long weightSum=0L, time=0L;
			for(Integer[] arr1: arr)
						{
							time+=arr1[1];
							weightSum += arr1[0]*time;
						}
			System.out.println(weightSum);
			
			
	scan.close();
	}

}
