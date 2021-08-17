/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	/**Width of each rectangle in  pixels*/
	private static final double rectWidth = 120;
	/**Height of each rectangle in pixels*/
	private static final double rectHeight = 50;
	public void run() {
		paintRects();
		paintLines();
		writeHierarchy();
	}
	/*
	 * This order will create three rectangles.
	 */
	private void paintRects() {
		paintMainRect();
		paintMiddleRect();
		paintLeftRect();
		paintRightRect();
	}
	/*
	 * This program creates lines which joins rectangles which I mentioned in previous comment.
	 */
	private void paintLines() {
		paintRightLine();
		paintMiddleLine();
		paintLeftLine();
	}
	/*
	 * This program writes appropriate words in appropriate rectangles.
	 */
	private void writeHierarchy() {
		writeProgram();
		writeGraphicsProgram();
		writeConsoleProgram();
		writeDialogProgram();
	}
	/*
	 * pre-condition:There is nothing on the screen.
	 * post-condition:There is only one rectangle on the screen.
	 */
	private void paintMainRect() {
		double x0 = getWidth()/2-rectWidth/2;
		double y0 = getHeight()/4;
		GRect mainRect = new GRect (x0,y0,rectWidth,rectHeight);
		add(mainRect);		
	}
	/*
	 * pre-condition:There is only one main rectangle on the screen.
	 * post-cpondition:There are two rectangles on the screen ,one main and  a middle.
	 */
	private void paintMiddleRect() {
		double x1=(getWidth()-rectWidth)/2;
		double y1=getHeight()/2;
		GRect middleRect = new GRect (x1,y1,rectWidth,rectHeight);
		add(middleRect);
	}
	/*
	 * pre-condition:There are two rectangles on the screen ,one main and  a middle.
	 * post-condition:There are three rectangle s on the screen one main,one middle, and one left.
	 */
	private void paintLeftRect() {
		double x2 = getWidth()/4-rectWidth/2;
		double y2 =getHeight()/2;
		GRect leftRect = new GRect (x2,y2,rectWidth,rectHeight);
		add(leftRect);
	}
	/*
	 * pre-condition:There are three rectangles on the screen one main,one middle, and one left.
	 * post-condition:There are four rectangles on the screen:main,middle,right and left.
	 */
	private void paintRightRect() {
		double x3 =getWidth()*3/4-rectWidth/2;
		double y3 =getHeight()/2;
		GRect rightRect = new GRect (x3, y3, rectWidth,rectHeight);
		add(rightRect);
	}
	/*
	 * pre-condition:There are only rectangles on the screen.
	 * post-conditoin:There is also right line which joins the right and main rectangles.
	 */
	private void paintRightLine() {
		double x4 =getWidth()/2;
		double y4 =getHeight()/4 +rectHeight;
		double x5 =getWidth()*3/4;
		double y5 =getHeight()/2;
		GLine rightLine =new GLine (x4,y4,x5,y5);
		add(rightLine);
		}
	/*
	 * pre-condition:There are rectangles and one right line on the screen.
	 * post-condition:There is also one middle line.
	 */
	private void paintMiddleLine() {
		double x4 =getWidth()/2;
		double y4 =getHeight()/4 +rectHeight;
		double x6 =getWidth()/2;
		double y6 =getHeight()/2;
		GLine middleLine = new GLine (x4,y4,x6,y6);
		add(middleLine);
	}
	/*
	 * pre-condition:There are rectangles and two, middle and right lines on the screen.
	 * post-condition:There is also left line edited to previous conditions.
	 */
	private void paintLeftLine() {
		double x4 =getWidth()/2;
		double y4 =getHeight()/4 +rectHeight;
		double x7 =getWidth()/4;
		double y7 =getHeight()/2;
		GLine leftLine =new GLine (x4,y4,x7,y7);
		add(leftLine);
	}
	/*
	 * pre-conditon:There is nothing written on the screen.
	 * post-condition:There is written "Program" on  the main rectangle.
	 */
	private void writeProgram() {
		GLabel program = new GLabel("Program");
		program.setLocation(getWidth()/2-program.getWidth()/2,getHeight()/4+program.getAscent()/2+rectHeight/2);
		add(program);
	}
	/*
	 * pre-conditoin:There is nothing written on the left rectangle.
	 * post-condition:There is written "ConsoleProgram" on the left rectangle.
	 */
	private void writeGraphicsProgram() {
		GLabel graphicsprogram = new GLabel("Graphics Program");
		graphicsprogram.setLocation(getWidth()/4-graphicsprogram.getWidth()/2,getHeight()/2+graphicsprogram.getAscent()/2+rectHeight/2);
		add(graphicsprogram);
	}
	/*
	 * pre-condition:There is nothing written on the middle rectangle.
	 * post-condition:There is written "ConsoleProgram" on the middle rectangle.
	 */
	private void writeConsoleProgram() {
		GLabel consoleprogram =new GLabel("Console Program");
		consoleprogram.setLocation(getWidth()/2-consoleprogram.getWidth()/2, getHeight()/2+consoleprogram.getAscent()/2+rectHeight/2);
		add(consoleprogram);
	}
	/*
	 * pre-condition:There is nothing written on the right rectangle.
	 * post-condition:There is written "DialogProgram" on the right rectangle.
	 */
	private void writeDialogProgram() {
		GLabel dialogprogram = new GLabel("Dialog Program");
		dialogprogram.setLocation(getWidth()*3/4-dialogprogram.getWidth()/2, getHeight()/2+rectHeight/2+dialogprogram.getAscent()/2);
		add(dialogprogram);
	}
}

