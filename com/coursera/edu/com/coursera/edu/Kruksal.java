package com.coursera.edu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Kruksal {

	public static void main(String[] args) throws Exception {
			
			
			Map<Integer[],Integer> map = new HashMap<Integer[], Integer>();
			Scanner scan = new Scanner(new FileReader("in.txt"));
			int N = scan.nextInt();
			int E = scan.nextInt();
			UF uf = new UF(N+1);
			for(int i10=0;i10<E;i10++){
				int a = scan.nextInt();
				int b = scan.nextInt();
				int c = scan.nextInt();
				Integer[] arr = new Integer[]{a,b};
				map.put(arr, c);
			}
			int cost=0;
			Map<Integer[], Integer> sortMap = sortByComparator(map);
			for(Integer[] a: sortMap.keySet()){
				
				if(!uf.connected(a[0], a[1]))
						{
						uf.union(a[0], a[1]);
						cost+=map.get(a);
						}
				
			}
			System.out.println(cost);
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
