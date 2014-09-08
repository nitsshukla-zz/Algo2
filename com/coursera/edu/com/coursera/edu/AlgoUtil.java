package com.coursera.edu;

public class AlgoUtil {
	public static String repeat0(int i) {
		if(i<=0) return"";
		return "0"+repeat0(i-1);
	}

}
