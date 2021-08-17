/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run () {
		while (leftIsClear()) {
			fillFirstRowLikeCheckboard();
			turnBack();
			fillSecondRowLikeCheckboard();
			turnBack1();
		}
		fillFirstRowLikeCheckboard();
	}
	/*
	 * pre-condition:karel is in front of the left wall.
	 * post-condition:karel is on the next row .
	 */
	private void turnBack1() {
		while(frontIsClear()) {
			move();
			if(frontIsBlocked()) {
				turnRight();
				if(frontIsClear()) {
					move();
					turnRight();
					fillFirstRowLikeCheckboard();
				}else {
					turnRight();
					turnLeft();
					}
			}
		}
}
	/*
	 * pre-condition:karel is in front of the the left wall.
	 * post-condition:karel is on the next row.
	 */
	private void turnBack () {
		while(frontIsClear()) {
			move();
			if(frontIsBlocked()) {
				turnRight();
				if(frontIsClear()) {
					move();
					turnRight();
					fillSecondRowLikeCheckboard();
				}else {
					turnRight();
					turnLeft();
				}
			}
		}
			
	}
	
	/*pre-condition:karel is at the bottom of the row.
	 * post-condition:karel is in the top of the row and row is filled like checkerboard.
	 */
	private void fillFirstRowLikeCheckboard() {
		if(frontIsBlocked()) {
			turnLeft();
		}
		while(frontIsClear()) {
			putBeeper();
			move();
			if(frontIsClear()) {
			move();
			if(frontIsBlocked()) {
				putBeeper();
				turnAround();
				turnBack();
			    }
			}
			if(frontIsBlocked() ){
				turnAround();
				turnBack();
				}
		}
	}
	
	/*
	 * pre-condition:karel is in at the bottom of the row.
	 * post-condition:karel is in the top of the row and row is filled like checkerboard, but  first cell doesn't contain beeper.
	 */
	private void fillSecondRowLikeCheckboard() {
		while(frontIsClear()) {
			move();
			putBeeper();
			if(frontIsClear()) {
				move();
			}else {
				turnAround();
				turnBack1();
			}
		if(frontIsBlocked()) {
			turnAround();
			turnBack1();
		    }
		}
		
	}
	
}
