package com.coursera.edu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class Johnsons {
static int N;
	static Graph graph = null;
	public static void main(String[] args) throws IOException {
		fill();
		try {
			graph.getShortestBellFord(N);


			graph.vertices.remove(graph.N-1);
			graph.N-=1;
			graph.edges.remove(graph.N);
			List<String> list = new LinkedList<String>();
			for(String e:graph.edgeMap.keySet())
			{
				if(graph.edgeMap.get(e).u.key==N-1)
					list.add(e);
			}
			for(String e:list)
				graph.edgeMap.remove(e);

			for(E e:graph.edgeMap.values())
			{
				e.weight+=e.u.val-e.v.val;
				//System.out.println(e);
			}
			List<V> h = new LinkedList<V>();
			for(V v:graph.vertices)
				h.add(v.clone());
			int ans=Integer.MAX_VALUE;
			int[][] arr =graph.getAllPairShortestDjik();
			for(int i=0;i<graph.N;i++){
				for(int i1=0;i1<graph.N;i1++)
				{
					//System.out.print((arr[i][i1]<=graph.MAX_VALUE/2?arr[i][i1]:"@")+" ");
					arr[i][i1]-=h.get(i).val-h.get(i1).val;
					if(ans>arr[i][i1])
							ans=arr[i][i1];

				}
				System.out.println();

			}

		} catch (NegativeCycleException e1) {
			e1.printStackTrace();
		}

		/*		for(V e:graph.vertices)
			System.out.println(e);
		 */		
	}

	private static void fill() throws IOException {
		
		Scanner scan = new Scanner(new File("in.txt"));
		N = scan.nextInt();
		int E=scan.nextInt();
		graph = new Graph(N+1);
		while(scan.hasNext()){
			fill(scan.nextInt()-1,scan.nextInt()-1,scan.nextInt());
		}
		for(int i=0;i<N;i++){
			fill(6,i,0);
		}
	}

	private static void fill(int i, int j, int k) {
		graph.addEdge(new E(graph.getVertex(i),graph.getVertex(j),k));
	}

}
