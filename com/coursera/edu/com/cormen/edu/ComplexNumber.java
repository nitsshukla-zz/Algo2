package com.cormen.edu;
//Arup Guha
//8/24/2010
//A short Complex Number class for the purpose of illustrating the FFT.
public class ComplexNumber {

	// We will store a number in rcis(theta) mode.	
	public double magnitude;
	public double angle;
	
	// For a real number.
	public ComplexNumber(double mag) {
		magnitude = mag;
		angle = 0;
	}
	
	// For a complex number already in rcis(theta) form.
	public ComplexNumber(double mag, double ang) {
		magnitude = mag;
		angle = ang;
	}
	
	// Multiply - we multiply magnitudes and add angles =)
	public ComplexNumber multiply(ComplexNumber other) {
		double newMag = this.magnitude*other.magnitude;
		double newAngle = this.angle + other.angle;
		return new ComplexNumber(newMag, newAngle); 
	}
	
	// Just multiplies this number by a real constant factor. 
	public void multiplyBy(double factor) {
		magnitude *= factor;
	}
	
	// Add - is harder in this form. We have to convert to a + bi, do the addition
	//       and convert back.
	public ComplexNumber add(ComplexNumber other) {
		
		// Calculate the real component of the answer, but extracting each real component.
		double real = this.magnitude*Math.cos(this.angle) + other.magnitude*Math.cos(other.angle);
		double img = this.magnitude*Math.sin(this.angle) + other.magnitude*Math.sin(other.angle);
		double newMag = Math.sqrt(real*real + img*img);
		
		// Catch the zero case, so we don't do any weird division.
		if (Math.abs(newMag) < 10e-8)
			return new ComplexNumber(0,0);
		
		// Figure out the angle - cosine works easiest...
		double newAngle;
		if (img >= 0)	newAngle = Math.acos(real/newMag);
		else			newAngle = 2*Math.PI - Math.acos(real/newMag);
			
		// Here's our addition.
		return new ComplexNumber(newMag, newAngle);
	}
	
	// Since this is an important check in the algorithm...
	public boolean equalsUnity() {
		return Math.abs(angle) < 10e-8 && Math.abs(magnitude-1) < 10e-8;
	}
	
	// We use this a bunch - it's O(1).
	public ComplexNumber unityExp(int exp) {
		return new ComplexNumber(1, angle*exp);
	}
	
	// Just check for 0.
	public boolean equalsZero() {
		return Math.abs(magnitude) < 10e-5;
	}
	
	// See if value equals this object. I eased the tolerances a bit here.
	public boolean equals(int value) {
		if (value == 0)
			return equalsZero();
		return Math.abs(magnitude*Math.cos(angle)-value) < 10e-5 && (Math.abs(Math.sin(angle)) <10e-5);
	}
	
	// So we can see our answers.
	public String toString() {
		
		if (Math.abs(this.magnitude*Math.sin(this.angle)) < 10e-8) {
			if (Math.abs(Math.PI - this.angle) < 10e-8)
				return "-"+magnitude;
			else
				return ""+magnitude;
		}
		else if (Math.abs(this.magnitude*Math.cos(this.angle)) < 10e-8) {
			return this.magnitude*Math.sin(this.angle) + "i";
		}
		else
			return this.magnitude*Math.cos(this.angle) + " + " + this.magnitude*Math.sin(this.angle) + "i";
	}
}