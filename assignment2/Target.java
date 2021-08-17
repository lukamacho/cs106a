/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

public class Target extends GraphicsProgram {

	private static final int BIG_RADIUS=144;
	private static final int MEDIUM_RADIUS=92;
	private static final int SMALL_RADIUS=44;
	
	public void run() {
		paintTarget();
		}
	private void paintTarget() {
		paintBigOval();
		paintMediumOval();
		paintSmallOval();
		
	}
	
	/*
	 * pre-condition:There is nothing on the screen.
	 * post-condition:There is big oval painted on  the screen and it is red.
	 */
	private void paintBigOval() {
		double	x0 = (getWidth()  -BIG_RADIUS)/2;
		double  y0 = (getHeight() -BIG_RADIUS)/2;
		double  x1 = BIG_RADIUS;
		double  y1 = BIG_RADIUS;
		
		GOval oval1 = new GOval (x0, y0,x1,y1);
		add (oval1);
		oval1.setFillColor(Color.RED);
		oval1.setColor(Color.RED);
		oval1.setFilled(true);
	}
	
	/*
	 * pre-condition:There is only one big red  circle painted.
	 * post-condition:There is another medium circle inside this big circle and it is white.
	 */
	private void paintMediumOval() {
		double x2 = (getWidth() - MEDIUM_RADIUS)/2 ;
		double y2 = (getHeight()- MEDIUM_RADIUS)/2 ;
		double x3 = MEDIUM_RADIUS;
		double y3 = MEDIUM_RADIUS;
		
		GOval oval2 =new GOval(x2, y2, x3, y3);
		add(oval2);
		oval2.setFillColor(Color.WHITE);
		oval2.setColor(Color.WHITE);
		oval2.setFilled(true);
		}
	
	/*
	 * pre-condition:There are two big and medium circles on the screen.
	 * post-condition:There are three circles on the screen one big,one medium and one small.Big and small circles are red and the medium is white.
	 */
	private void paintSmallOval() {
		double x4 =(getWidth() - SMALL_RADIUS)/2;
		double y4 =(getHeight() - SMALL_RADIUS)/2;
		double x5 =SMALL_RADIUS;
		double y5 =SMALL_RADIUS;
		
		GOval oval3 = new GOval(x4,y4,x5,y5);
		add(oval3);
		oval3.setFillColor(Color.RED);
		oval3.setColor(Color.RED);
		oval3.setFilled(true);
	}

}
