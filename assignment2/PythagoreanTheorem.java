/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	
	public void run() {
		println("Enter values to compute Pythagorean theorem");
		pythagoreanTheorem();
	}
	
	/*
	 * After inputing two integers  this program will evaluate the hypotenuse of the right triangle whose catetes lengths are these integers.
	 */
	private void pythagoreanTheorem() {
		int a =readInt("Enter a: ");
		int b =readInt("Enter b: ");
		if(a>0&&b>0) {
		double c = (double)Math.sqrt(a*a+b*b);
		println("The hypothenus is " + c +".");
		}else {
			println("One of the numbers is negative,try again. ");
		}
		
	}
}
