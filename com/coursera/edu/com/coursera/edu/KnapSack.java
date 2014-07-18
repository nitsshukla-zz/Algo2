package com.coursera.edu;

import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class KnapSack {
static Map<String, Integer> map = new HashMap<>(); 
	public static void main(String[] args) {
			List<Integer[]> arr = new ArrayList<Integer[]>();
			arr.add(new Integer[]{1,2});arr.add(new Integer[]{4,5});
			arr.add(new Integer[]{3,3});arr.add(new Integer[]{2,1});
	int W = 4;
/*	for(int i=2;i<arr.size();i++)
		for(int i1=1;i1<=W;i1++)
					map.put(i+" "+i1,Math.max(dynamic(new ArrayList<>(arr.subList(0, i-1)),i1),
							arr.get(i)[0]+dynamic(new ArrayList<Integer[]>(arr.subList(0, i-1)),i1-arr.get(i)[1])));
*/	///System.out.println(map.get(arr.size()-1+" "+W));
	System.out.println(dynamic(arr,W));
	}

	private static int dynamic(List<Integer[]> arr, int w) {
		if(arr.isEmpty() || w<=0) return 0;
		if(map.containsKey(arr.size()+" "+w)) 
					return map.get(arr.size()+" "+w);
		Integer[] num = arr.get(arr.size()-1);
		ArrayList<Integer[]> arr1 = new ArrayList<Integer[]>(arr);
		arr1.remove(arr1.size()-1);
		if(w>=num[1]){
			
			return Math.max(num[0]+dynamic(arr1, w-num[1]), dynamic(arr1,w));
		}
						return dynamic(arr1,w);
		
	}

}
