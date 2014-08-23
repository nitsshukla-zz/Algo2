package com.coursera.edu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.*;

public class Knapsack_fast {
		public static void main(String[] args) throws FileNotFoundException{
			Scanner scan= new Scanner(new FileReader("in.txt")); 
			Map<Long,Long> map = new HashMap<Long, Long>();
			int W0 = scan.nextInt();
			int N = scan.nextInt();
			map.put(0L, 0L);
			for(int i=0;i<N;i++){
				int v = scan.nextInt();
				int w = scan.nextInt();
				System.out.println(i);
				//if(w<=W0)
					//map.put(w, v);
					Set<Long> set = new HashSet<>(map.keySet());
					for(Long s:set ){
						if(s+w<=W0)
							{
							if(!map.containsKey(s+w))
									map.put(s+w, v+map.get(s));
							else if(map.get(s+w)<v+map.get(s)){
								map.put(s+w, v+map.get(s));
							}
							}
					}
			}
			Long max=0L;
		for(Long i: map.values())
		{
			if(max==0L)
				max=i;
			else{
				if(max<i)
					max=i;
			}
		}
		
		System.out.println(max);
		}
}
