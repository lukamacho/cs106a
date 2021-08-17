/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int x =0;
	/*
	 *pre-condition:There is nothing on the screen.
	 *post-condition:User enters first number ,if it is stop number program will print appropriate sentence else start findMaxAndMin numbers.
	 */
	public void run() {
		println("This program find the largest and the smallest numbers");
		int value=readInt("?");
		if(value!=x) {
		int min =value;
		int max =value;
		findMaxAndMin(value,min,max);
		}
		if(value==x) {
			println("You entered stop symbol");
		}
	}
	
	/*
	 * This function finds maximum and minimum of the numbers, which will be written before the stop number x.
	 */
	private void findMaxAndMin(int value, int min, int max) {
		while(value!=x) {
		if(max>value) {
			max=max;
			}
			else {
				max=value;
			}	
		if(min<value) {
			min=min;
		}if(min>=value&&value!=x) {
			min=value;
			}
		value=readInt("?");
		}
		println("Largest: "+max+".");
		println("Smallest: " + min+ ".");
	}
}

