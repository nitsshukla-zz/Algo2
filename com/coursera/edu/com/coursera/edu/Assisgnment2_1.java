package com.coursera.edu;

import java.util.*;
import java.io.*;
public class Assisgnment2_1 {
		public static void main(String[] args) throws Exception{
			Scanner scan = new Scanner(new FileReader("in.txt"));
			int N = scan.nextInt();
			Map<Integer[], Integer> map = new HashMap<Integer[], Integer>();
			while(scan.hasNext()){
				int x = scan.nextInt()-1;
				int y = scan.nextInt()-1;
				int w = scan.nextInt();
				Integer[] arr = new Integer[]{x,y};
				map.put(arr, w);
			}
			UF uf = new UF(N);
			
			Map<Integer[], Integer> sortMap = sortByComparator(map);
			for(Integer[] a: sortMap.keySet()){
			if(uf.count()==4 && !uf.connected(a[0], a[1])) {
				System.out.println(map.get(a));
				break;
			}
				if(!uf.connected(a[0], a[1]))
						{
									uf.union(a[0], a[1]);
			
						}
				
			}
			

		}
		private static Map sortByComparator(Map unsortMap) {
			 
			List list = new LinkedList(unsortMap.entrySet());
	 
			// sort list based on comparator
			Collections.sort(list, new Comparator() {
				public int compare(Object o1, Object o2) {
					return ((Comparable) ((Map.Entry) (o1)).getValue())
	                                       .compareTo(((Map.Entry) (o2)).getValue());
				}
			});
	 
			// put sorted list into map again
	                //LinkedHashMap make sure order in which keys were inserted
			Map sortedMap = new LinkedHashMap();
			for (Iterator it = list.iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				sortedMap.put(entry.getKey(), entry.getValue());
			}
			return sortedMap;
		}
}
