/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		getToTheCorner();
		findTheMidpoint();
	}
	
	/*
	 * pre-condition:karel is in the beginning of the board.
	 * post-condition:karel is at the above left corner of the board.
	 */
	private void getToTheCorner(){
		turnLeft();
		while(frontIsClear()) {
			move();
		}
		if(frontIsBlocked()) {
			turnRight();
		}
		if(frontIsBlocked()) {
			putBeeper();
		}
		
	}
	/*
	 * pre-condition:karel is at  the above left corner of the board.
	 * post-condition:karel finds the midpoint of the side.
	 */
	private void findTheMidpoint() {
		while(frontIsClear()) {
			move();
			turnRight();
			move();
			if(frontIsClear()) {
				move(); 
			}else {
				putBeeper();
				pickBeeper();
				}
			if(frontIsClear()) {
				turnLeft();
			}
			else {
				putBeeper();
			}
		}
	}
}
