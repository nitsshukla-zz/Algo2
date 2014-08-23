package com.coursera.edu;

public class SequenceAlgorithm {
			static S[][] arr;
			public static void main(String[] args){
				String Y = "ABC";
				String X = "BCA";
			arr = new S[X.length()+1][Y.length()+1];
			for(int i=0;i<=X.length();i++) 
					arr[i][0]=new S(i,X.substring(0, i));
			for(int i=0;i<=Y.length();i++) 
				arr[0][i]=new S(i,Y.substring(0, i));
			printAll();
		for(int i=1;i<=X.length();i++)
			for(int i1=1;i1<=Y.length();i1++){
				System.out.println(i+" "+i1);
				if(X.charAt(i-1)==Y.charAt(i1-1))
					arr[i][i1]=new S(arr[i-1][i1-1].v,arr[i-1][i1-1].s+X.charAt(i-1));
				else{
					///arr[i][i1] = Math.min(arr[i-1][i1].v, arr[i][i1-1].v)+1;
				if(arr[i-1][i1].v>arr[i][i1-1].v)
					arr[i][i1]=new S(1+arr[i][i1-1].v,arr[i][i1-1].s+arr[i-1][i1].s.charAt(arr[i-1][i1].s.length()-1));
				else
					arr[i][i1]=new S(1+arr[i-1][i1].v,arr[i-1][i1].s+arr[i][i1-1].s.charAt(arr[i][i1-1].s.length()-1));
				
				}
			}
		printAll();
			System.out.println(arr[X.length()][Y.length()]);
			}
			private static void printAll() {
					for(int i=0;i<arr.length;i++){
						S[] arr1 = arr[i];
						for(int i1=0;i1<arr1.length;i1++){
							System.out.print(arr1[i1]);
						}
						System.out.println();
					}
			}
}

class S{
	int v=0;
	@Override
	public String toString() {
		return "S [v=" + v + ", s=" + s + "]";
	}
	String s="";
	public S(int v, String s){
		this.v=v;
		this.s=s;
	}
	public S(S s, char c){
		this.v=s.v;
		this.s+=c;
	}
}