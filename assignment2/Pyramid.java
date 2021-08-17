/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	/*
	 *pre-condition:There is nothing on the screen.
	 *post-condition:There is pyramid on the centre of the screen with bricks in base 14.
	 */
	public void run() {
		for(int i=0; i<BRICKS_IN_BASE+1; i++) {
			for(int j=0; j<BRICKS_IN_BASE-i+1;j++) {
				double x =j * BRICK_WIDTH;
				double y = i * BRICK_HEIGHT;
				GRect giza = new GRect (getWidth()/2+x-(BRICKS_IN_BASE-i) * BRICK_WIDTH/2,getHeight()-y,BRICK_WIDTH,BRICK_HEIGHT);
				add(giza);
			}
		}
		
	}
}

