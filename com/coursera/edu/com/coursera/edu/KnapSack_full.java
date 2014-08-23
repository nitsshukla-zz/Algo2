package com.coursera.edu;

import java.io.FileReader;
import java.util.*;;

public class KnapSack_full {
	static HashMap<String, Integer> map = new HashMap<String, Integer>();
	static List<Integer[]> arr;
	public static void main(String[] args) throws Exception{
			Scanner scan = new Scanner(new FileReader("in.txt"));
			arr = new LinkedList<Integer[]>();
			int W0 = scan.nextInt();
			int N = scan.nextInt();
			for(int i=0;i<N;i++)
				arr.add(new Integer[]{scan.nextInt(),scan.nextInt()});
			
				Collections.sort(arr, new Comparator<Integer[]>() {

					@Override
					public int compare(Integer[] arg0, Integer[] arg1) {
						Float f = (float)arg0[0]/arg0[1]; 
						Float f1 = (float)arg1[0]/arg1[1];
						return f1.compareTo(f);
					}
				});
				Stack<Integer[]> stack = new Stack<Integer[]>();
				int W =0,V=0;
				for(Integer[] i:arr){
					if(W+i[1]<W0){
						stack.add(i);
						W+=i[1];
						V+=i[0];
					}
					else{
						List<Integer[]> arr1 = new LinkedList<Integer[]>();
						if(i[1]>W0) continue; int v=0;
						while(W+i[1]>W0){
							Integer[] i1 = stack.pop();
							W-=i1[1];
							V-=i1[0];
							v+=i1[0];
							arr1.add(i1);
						}
					if(v<i[0]){
						stack.push(i);
						W+=i[1];
						V+=i[0];
					}else{
						for(Integer[] ii:arr1){
							W+=ii[1];
							V+=ii[0];
							stack.push(ii);
						}
					}
					}
				if(W==W0) break;
				}
	System.out.println(V);
	}
	private static int gets(int n, int w) {
		//System.out.println(n+" "+w);
		if(n<=0 || w<=0) return 0;
		if(map.containsKey(n+" "+w))
			 return map.get(n+" "+w);
			int prev = gets(n-1,w-arr.get(n-1)[1]);
			int side = gets(n-1,w);
			int ans=0;
			if(prev+arr.get(n-1)[0]>side)
				ans = prev+arr.get(n-1)[0]; 
			else
				ans=side;
			map.put(n+" "+ w, ans);
			return ans;
	}

}
