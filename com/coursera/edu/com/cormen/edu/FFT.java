package com.cormen.edu;

//Example of the FFT used to multiply two polynomials!

import java.util.*;

public class FFT {
	
	// This is the FFT - goes from polynomial form to point form.
	public static ComplexNumber[] polyToPoint(ComplexNumber[] coeff, ComplexNumber omega) {
		
		// Base case: should by poly of degree 0.
		if (omega.equalsUnity() || coeff.length == 1)
			return coeff;
			
		ComplexNumber[] even = new ComplexNumber[coeff.length/2];
		ComplexNumber[] odd = new ComplexNumber[coeff.length/2];
		
		// Copy references to even coefficients.
		for (int i=0; i<coeff.length; i+=2)
			even[i/2] = coeff[i];
			
		// Now odd.
		for (int i=1; i<coeff.length; i+=2)
			odd[i/2] = coeff[i];
		
		// Our two recursive calls, as stated in the Dasgupta text.
		ComplexNumber[] ptsEven = polyToPoint(even, omega.multiply(omega));
		ComplexNumber[] ptsOdd = polyToPoint(odd, omega.multiply(omega));

		ComplexNumber[] ourPoints = new ComplexNumber[coeff.length];
		
		// Combine our answer for each of the points.
		for (int i=0; i<coeff.length; i++) {
			ourPoints[i] = ptsEven[i%ptsEven.length].add(omega.unityExp(i).multiply(ptsOdd[i%ptsOdd.length]));
		}
		
		return ourPoints;
	}
	
	// Does the inverse conversion, from points to polynomial form.
	public static ComplexNumber[] pointToPoly(ComplexNumber[] points, ComplexNumber omega) {
		
		// Do the forward transformation with omega^-1
		ComplexNumber[] answer = polyToPoint(points, omega.unityExp(-1));
		
		// Divide each number by n, and you're done!
		for (int i=0; i<answer.length; i++)
			answer[i].multiplyBy(1.0/answer.length);
		return answer;
	}
	
	// Calculates product of two polynomials in point form.
	public static ComplexNumber[] multiply(ComplexNumber[] points1, ComplexNumber[] points2) {
		
		ComplexNumber[] answer = new ComplexNumber[points1.length];
		
		// This is easy, just multiply the y values.
		for (int i=0; i<answer.length; i++)
			answer[i] = points1[i].multiply(points2[i]);
			
		return answer;
	}
	
	public static double[] multiply(double[] poly1, double[] poly2) {
		
		int INPUTSIZE = 1;
		while (INPUTSIZE < poly1.length)
			INPUTSIZE = INPUTSIZE*2;
		
		// Fill these to be the identical polynomials.
		ComplexNumber[] test1 = new ComplexNumber[INPUTSIZE*2];
		ComplexNumber[] test2 = new ComplexNumber[INPUTSIZE*2];
		
		for (int i=0; i<poly1.length; i++) {
			test1[i] = new ComplexNumber(poly1[i]);
			test2[i] = new ComplexNumber(poly2[i]);
		}
		for (int i=poly1.length; i<test1.length; i++) {
			test1[i] = new ComplexNumber(0);
			test2[i] = new ComplexNumber(0);
		}
		
		// Convert to point form.
		ComplexNumber[] pts1 = polyToPoint(test1, new ComplexNumber(1,Math.PI/INPUTSIZE));
		ComplexNumber[] pts2 = polyToPoint(test2, new ComplexNumber(1,Math.PI/INPUTSIZE));
		
		// Multiply in point form.
		ComplexNumber[] result = multiply(pts1, pts2);
		
		// I have written a different function to do the inversion.
		ComplexNumber[] polyResult = pointToPoly(result, new ComplexNumber(1, Math.PI/INPUTSIZE));
		
		double[] ans = new double[polyResult.length];
		for (int i=0; i<polyResult.length; i++)
			ans[i] = polyResult[i].magnitude*Math.cos(polyResult[i].angle);
			
		return ans;
		
	}
	
	// The slower way, asymptotically, for a comparison point. 
	// There's less overhead here though, since I just multiply ints.
	public static int[] slowPolyMult(int[] f, int[] g) {
		
		// Initialize answer.
		int[] answer = new int[f.length + g.length - 1];
		for (int i=0; i<answer.length; i++)
			answer[i] = 0;
			
		// Add in all the terms the usual O(n^2) way.
		for (int i=0; i<f.length; i++)
			for (int j=0; j<g.length; j++)
				answer[i+j] += f[i]*g[j];
				
		return answer;
		
	}
	
	// Checks to see if poly1 and poly2 are storing the same thing.
	public static boolean equals(int[] poly1, ComplexNumber[] poly2) {
			
		// Check all terms that exist in both.
		for (int i=0; i<poly1.length && i<poly2.length; i++)
			if (!poly2[i].equals(poly1[i])) {
				System.out.println("IN FUNC: i is "+i+"reg: "+poly1[i]+"poly2: "+poly2[i]);
				return false;
			}
			
		// Check extra terms in poly1; make sure they are 0.
		if (poly1.length > poly2.length) {
			for (int i=poly2.length; i<poly1.length; i++)
				if (poly1[i] != 0)
					return false;
		}
		
		// Do the same for poly2.
		else if (poly2.length > poly1.length) {
			for (int i=poly1.length; i<poly2.length; i++)
				if (!poly2[i].equalsZero())
					return false;			
		}
				
		return true;
	}
	
	/*** THIS MAIN CREATES A RANDOM LARGE EXAMPLE TO SHOW THAT FFT IS FASTER TO MULTIPLY 
	 *   FOR LARGE POLYNOMIALS  ***/
	 
	/*** TRYING BOTH 65536 and 131072 shows that the slow method is really O(n^2) and that
	 *   the FFT is really nlgn
	 ***/
	final static int INPUTSIZE = 131072;
	/*
	public static void main(String[] args) {
		
		Random r = new Random();
		
		int[] poly1 = new int[INPUTSIZE];
		int[] poly2 = new int[INPUTSIZE];
		
		// Fills each with random coefficients from -10 to 10.
		for (int i=0; i<poly1.length; i++) {
			poly1[i] = r.nextInt(21)-10;
			poly2[i] = r.nextInt(21)-10;
		}
		
		long start = System.currentTimeMillis();
		int[] ans = slowPolyMult(poly1, poly2);
		long end = System.currentTimeMillis();
		System.out.println("done in "+(end-start)+" ms.");
		
		// Fill these to be the identical polynomials.
		ComplexNumber[] test1 = new ComplexNumber[INPUTSIZE*2];
		ComplexNumber[] test2 = new ComplexNumber[INPUTSIZE*2];
		
		for (int i=0; i<poly1.length; i++) {
			test1[i] = new ComplexNumber(poly1[i]);
			test2[i] = new ComplexNumber(poly2[i]);
		}
		for (int i=poly1.length; i<test1.length; i++) {
			test1[i] = new ComplexNumber(0);
			test2[i] = new ComplexNumber(0);
		}
		
		// Start timer here
		start = System.currentTimeMillis();
		
		// Convert to point form.
		ComplexNumber[] pts1 = polyToPoint(test1, new ComplexNumber(1,Math.PI/INPUTSIZE));
		ComplexNumber[] pts2 = polyToPoint(test2, new ComplexNumber(1,Math.PI/INPUTSIZE));
		
		// Multiply in point form.
		ComplexNumber[] result = multiply(pts1, pts2);
		
		// I have written a different function to do the inversion.
		ComplexNumber[] polyResult = pointToPoly(result, new ComplexNumber(1, Math.PI/INPUTSIZE));
		
		// End timer here
		end = System.currentTimeMillis();
		
		// Result
		if (equals(ans, polyResult)) 
			System.out.println("Both multiplications give the same product!");
		else 
			System.out.println("Oops, FFT didn't work.");
		System.out.println("The FFT took "+(end-start)+ "ms.");
		
	}
	*/
	
	public static void main(String[] args) {
		
		// Poly #1
		ComplexNumber[] test1 = new ComplexNumber[4];
		test1[0] = new ComplexNumber(-1);
		test1[1] = new ComplexNumber(1);
		test1[2] = new ComplexNumber(0);
		test1[3] = new ComplexNumber(0);
		
		// Poly #2
		ComplexNumber[] test2 = new ComplexNumber[4];
		test2[0] = new ComplexNumber(3);
		test2[1] = new ComplexNumber(2);
		test2[2] = new ComplexNumber(0);
		test2[3] = new ComplexNumber(0);
			
		// Convert to point form.
		ComplexNumber[] pts1 = polyToPoint(test1, new ComplexNumber(1,Math.PI/2));
		ComplexNumber[] pts2 = polyToPoint(test2, new ComplexNumber(1,Math.PI/2));
		
		System.out.print("First set of points: ");
		printPoints(pts1);
		System.out.print("Second set of points: ");
		printPoints(pts2);
		
		// Multiply in point form.
		ComplexNumber[] result = multiply(pts1, pts2);
		
		System.out.print("Product set of points: ");
		printPoints(result);		
		
		// I have written a different function to do the inversion.
		ComplexNumber[] polyResult = pointToPoly(result, new ComplexNumber(1, Math.PI/2));
		
		for (int i=0; i<polyResult.length; i++)
			System.out.println(polyResult[i]);
	}
	
	
	public static void printPoints(ComplexNumber[] pts) {

		ComplexNumber inc = new ComplexNumber(1, 2*Math.PI/pts.length);
		ComplexNumber base = new ComplexNumber(1, 0);
		for (int i=0; i<pts.length; i++) {
			System.out.print("("+base+", "+pts[i]+") " );
			base = base.multiply(inc);
		}
		System.out.println("\n");
	}
	
	
}