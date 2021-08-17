/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while (frontIsClear()) {
			fillInThePillar();
			goDown();
			goToTheNextPillar();
			}
		fillInThePillar();
		
	}
	 /*
	  * pre-condition:karel is on the bottom of the pillar.
	  * post-condition:karel fills the pillar.
	  */
	private void fillInThePillar() {
		turnLeft();
		while(frontIsClear()) {
			putBeeperIfNecessary();
			move();
		}
		if(frontIsBlocked()) {
			putBeeperIfNecessary();
		}
}
	/*
	 * pre-condition:karel is in top of the pillar.
	 * post-condition:karel is in the bottom of the pillar and it is filled.
	 */
	private void goDown() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
		if(frontIsBlocked()) {
			turnRight();
			}
		
	}
	/*
	 * pre-condition:karel is in the bottom of the pillar.
	 * post-condirtion:karel goes to the next pillar. 
	 */
	private void goToTheNextPillar() {
		for(int i=0; i<4; i++) {
			if(frontIsClear())
			move();
			}
		
	}
	/*
	 * if no beeper present karel puts beeper on the cell. 
	 */
	private void putBeeperIfNecessary() {
		if(noBeepersPresent()) {
			putBeeper();
		}
	}
}



