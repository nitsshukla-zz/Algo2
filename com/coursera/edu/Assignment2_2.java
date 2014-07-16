package com.coursera.edu;

import java.util.*;
import java.io.*;

public class Assignment2_2 {
	public static void main(String[] args)throws Exception{
		Scanner scan = new Scanner(new FileReader("in.txt"));
		int N = scan.nextInt();
		int b = scan.nextInt();
		HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for(int i=0;i<N;i++)
		{
			String str1 = scan.nextLine();
			List<Integer> list;
			if(!map.containsKey(str1))
			{
				list = new LinkedList<Integer>();list.add(i);	
				map.put(str1, list);
			}
			else{
				list = map.get(str1);
				list.add(i);
				map.put(str1, list);
			}
		}
		UF uf  = new UF(N);
		//0
		for(List<Integer> list1: map.values()){
			for(int i:list1){
				if(i!=list1.get(0))	uf.union(i, list1.get(0));
			}
		}
		//1
		for(String str2: map.keySet()){
			if(str2.length()==0) continue;
			StringBuilder str1 = new StringBuilder(str2);
			List<Integer> list1 = map.get(str2);
			List<Integer> list2=null;
			for(int i=0;i<str1.length();i++){
				list2=null;
				if(str1.charAt(i)=='1' && map.containsKey(subs(str1.toString(),i, i+1, "0")))
					list2 = map.get(subs(str1.toString(),i, i+1, "0"));
				if(str1.charAt(i)=='0' && map.containsKey(subs(str1.toString(),i, i+1, "1")))
					list2 = map.get(subs(str1.toString(),i, i+1, "1"));
				if(list2!=null){
					for(Integer ii:list2){
						uf.union(list1.get(0), ii);
					}
				}
			}
			
			for(int i=0;i<23;i++)
				for(int i1=i+1;i1<24;i1++){
					list2=null;
					String str22  = subs(str1.toString(),2*i);
					str22 = subs(str22,2*i1);
					list2 = map.get(str22);
					if(list2!=null){
						for(Integer ii:list2){
							uf.union(list1.get(0), ii);
						}
					}
				}
		}

		//2

		
		System.out.println(uf.count());
		scan.close();
	}
	public static String subs(String str1, int start, int end, String str3){
		StringBuilder str2 = new StringBuilder(str1);
		return str2.replace(start, end, str3).toString();
	}

	private static String subs(String string, int i) {
		//System.out.println(string +"~~~~"+i);
		if(string.charAt(i)=='1')
			return subs(string,i,i+1,"0");

		if(string.charAt(i)=='0')
			return subs(string,i,i+1,"1");
		return null;
	}
}
