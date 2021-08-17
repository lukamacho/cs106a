/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	/*
	 * pre-condition:You enter the positive number.
	 * post-conditionIf the number which you entered is not positive program will show appropriate sentence. 
	 */
	private static final int x=1;
	public void run() {
		int a=readInt("Enter a :");
		if(a<=0) {
			println("You didn't enter positive integer. ");
			}else {
		getToTheStopInteger(a);
		}
	}
	
	/*
	 * pre-condition:The program have already checked whether you entered or not positive integer.
	 * post-condition::If the number is odd program will take 3n+1 and if it is even program will get half of it.
	 */
	private void getToTheStopInteger(int a) {
		if(a==x) {
			println("Your number is " + x + " and it didn't need any operation to reach it.");
		}
		for(int i=1; a!=x; i++) {
			if(a%2==0) {
				println( a+" is even so I take half "+a/2);
				a=a/2;
				}else {
				println(a+ " is odd so, I take 3n+1 "+ (3*a+1));
				a=3*a+1;
			}
		if(a==x) {
			println("This process needed " + i + " moves to reach 1.");
			}
		
		}
	}
}

