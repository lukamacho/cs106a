/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run () {
		reachTheBorder();
		getToTheDoor();
		getbeeper();
		returnBack();
	}
	/*
	 *  pre-condition:karel is at the bottom left corner of the room.
	 * post-condition:karel is at the above left corner facing down.
	 */
	private void reachTheBorder() {
		while(frontIsClear()) {
			move();
		}
		turnRight();
		
	}
	/*
	 * pre-condition:karel is at the above left corner facing down.
	 * post-condition:karel is in front of the newspaper.
	 */
	private void getToTheDoor() {
		move();
		turnLeft();
	}
	/*
	 * pre-condition:karel is in front of the newspaper.
	 * post-condition:karel picks the newspaper.
	 */
	private void getbeeper() {
		move();
		pickBeeper();
	}
	/*
	 * pre-condition:karel picks the newspaper.
	 * post-condition:karel returns to its initial position.
	 */
	private void returnBack () {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnRight();
		move();
		turnRight();
		}
}
