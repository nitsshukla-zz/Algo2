

import java.util.LinkedList;
import java.util.List;

public class V implements Comparable<V>{
	public int name;
	public Integer key=Integer.MAX_VALUE;
	List<V> adj;
	public V(int n){
		adj = new LinkedList<V>();
		name=n;
	}
	public V(int n, int key){
		adj = new LinkedList<V>();
		name=n;
		this.key=key;
	}
	@Override
	public String toString() {
		return name+":"+key ;
	}
	@Override
	public int compareTo(V o) {
		return key.compareTo(o.key);
		
	}

}
