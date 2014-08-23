package com.coursera.edu;

import java.util.HashMap;
import java.util.Map;

public class SequenceAlignment {
static int n=0;
static Map<String, String> map = new HashMap<String, String>();
	public static void main(String[] args) {
				String X = "TGSPPTGGGGGGGGGGGGGGPKJPJJJJPJJPJPJJGGGGGGGGGGGG";
				String Y = "GGSTPGGGGGGGGGPPPPPPGGGGGG";
				System.out.println(seqAlign(X,Y));
	System.out.println(n +" "+ X.length()*Y.length());
	}

	private static String seqAlign(String x, String y) {
		n++;
		if(x.isEmpty() || y.isEmpty()) return "";
		String a1 = encache1(x,y);
		if(a1!=null)
			return a1;
		if(x.charAt(x.length()-1)==y.charAt(y.length()-1)){
			
			String a= seqAlign(x.substring(0, x.length()-1), y.substring(0, y.length()-1))+x.charAt(x.length()-1);
			cache(x,y,a);
			return a;
		}
		String a = seqAlign(x.substring(0, x.length()-1), y);
		String b = seqAlign(x, y.substring(0, y.length()-1));
		return a.length()>b.length()?a:b;
	}

	private static String encache(String x, String y) {
if(map.containsKey(x+" "+y))
					return map.get(x+" "+y);
if(map.containsKey(y+" "+x))
	return map.get(y+" "+x);
return null;
	}
	private static String encache1(String x, String y) {
		String a = encache(x, y);
		if(a!=null) return a;
		a = encache(x.substring(0, x.length()-1),y);
		if(a!=null) return a;
		a = encache(y.substring(0, y.length()-1),x);
		if(a!=null) return a;
		else return null;		
	}
	private static void cache(String x, String y, String a) {
			map.put(x+" "+y, a);
	}

}
