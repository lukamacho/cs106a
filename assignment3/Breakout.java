
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout<remove> extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	/** Number of rectangles */
	private static final int NRECTS = 100;

	private static final int PAUSE_TIME = 7;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int turns = NTURNS;
	private int nrects = NRECTS;
	double vy = 3;
	double vx;
	private GOval ball = null;
	private GRect paddle = null;

	/* Method: run() */
	/** Runs the Breakout program. */

	public void init() {
		addMouseListeners();
	}

	public void run() {
		paintRects();
		ball = paintBall();
		paddle = paintPaddle();
		waitForClick();
		while (nrects > 0) {
			ballMove();
			if (turns == 0 || nrects == 0) {
				break;
			}
		}
		if (turns == 0) {
			losingWords();
		}
		if (nrects == 0) {
			winningWords();
		}
	}

	/*
	 * This program will come out losing words if you lost the game.
	 */
	private void losingWords() {
		GLabel lose = new GLabel("You lost");
		lose.setLocation(APPLICATION_WIDTH / 2 - lose.getWidth(), APPLICATION_HEIGHT * 3 / 5);
		lose.setFont("London-36");
		lose.setColor(Color.red);
		add(lose);
	}

	/*
	 * This program will come out winning words if you won the game.
	 */
	private void winningWords() {
		GLabel win = new GLabel("You won");
		win.setLocation(APPLICATION_WIDTH / 2 - win.getWidth(), APPLICATION_HEIGHT * 3 / 5);
		win.setFont("London-36");
		win.setColor(Color.blue);
		add(win);
	}

	/*
	 * This order paints rects.
	 */
	private void paintRects() {
		for (int j = 0; j < NBRICK_ROWS; j++) {
			for (int i = 0; i < NBRICKS_PER_ROW; i++) {
				double x1 = i * (BRICK_WIDTH + BRICK_SEP) + BRICK_SEP / 2;
				double x2 = BRICK_WIDTH;
				double y1 = HEIGHT / 3 - j * (BRICK_HEIGHT + BRICK_SEP);
				double y2 = BRICK_HEIGHT;
				additionOfRects(x1, y1, x2, y2, j);
			}
		}
	}

	/*
	 * This program defines the colour of the rectangles in each row.
	 */
	private void additionOfRects(double x1, double y1, double x2, double y2, int j) {
		GRect playingRects = new GRect(x1, y1, x2, y2);
		if (j < 2) {
			playingRects.setColor(Color.cyan);
			playingRects.setFilled(true);
		} else if (j < 4) {
			playingRects.setColor(Color.green);
			playingRects.setFilled(true);
		} else if (j < 6) {
			playingRects.setColor(Color.yellow);
			playingRects.setFilled(true);
		} else if (j < 8) {
			playingRects.setColor(Color.orange);
			playingRects.setFilled(true);
		} else {
			playingRects.setColor(Color.red);
			playingRects.setFilled(true);
		}
		add(playingRects);
	}

	/*
	 * pre-condition:There is only rectangles on the screen. post-condition:There is
	 * also paddle on the screen.
	 */
	private GRect paintPaddle() {
		double x1 = (WIDTH - PADDLE_WIDTH) / 2;
		double y1 = APPLICATION_HEIGHT - 2 * PADDLE_Y_OFFSET;
		double x2 = PADDLE_WIDTH;
		double y2 = PADDLE_HEIGHT;
		GRect paddle = new GRect(x1, y1, x2, y2);
		paddle.setColor(Color.black);
		paddle.setFillColor(Color.black);
		paddle.setFilled(true);
		add(paddle);
		return paddle;

	}

	/*
	 * pre-condition:There is only rectangles and one paddle on the screen.
	 * post-condition:There is also ball on the screen which moves until it will not
	 * hit any object.
	 */
	private GOval paintBall() {
		double x1 = APPLICATION_WIDTH / 2 - BALL_RADIUS;
		double y1 = APPLICATION_HEIGHT / 2 - BALL_RADIUS;
		double x2 = BALL_RADIUS * 2;
		double y2 = BALL_RADIUS * 2;
		GOval ball = new GOval(x1, y1, x2, y2);
		ball.setColor(Color.black);
		ball.setFilled(true);
		add(ball);
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5))
			vx = -vx;
		return ball;

	}

	/*
	 * This program defines the movement of the ball on teh screen.
	 */
	private void ballMove() {
		checkingWallCollision();
		collisonChecking();
		ball.move(vx, vy);
		pause(PAUSE_TIME);
	}

	/*
	 * If ball collides any side of the screen this will be reflected from the wall
	 * if it is not bottom.
	 */
	private void checkingWallCollision() {
		if (ball.getX() < 0 || ball.getX() > APPLICATION_WIDTH - 2 * BALL_RADIUS) {
			vx = -vx;
		} else if (ball.getY() < 0) {
			double diff = ball.getY() - (getHeight() - BALL_RADIUS);
			ball.move(0, -diff);
			vy = -vy;
		} else if (ball.getY() > APPLICATION_HEIGHT - PADDLE_Y_OFFSET && turns > 0) {
			remove(ball);
			turns--;
			ball = paintBall();
			waitForClick();
		}

	}

	/*
	 * At first I am checking if the balls upper point +1 is collided to
	 * something,then ball bottom point +1, them right middle point +1, at left
	 * middle point +1.
	 */
	private void collisonChecking() {
		GObject collider = checkingFirstCollision();
		GObject colliderTwo = null;
		GObject colliderThree =null;
		if (collider == null) {
			colliderTwo = checkingSecondCollision();
		} else if (colliderTwo == null) {
			colliderThree = checkingThirdCollision();
		} else if (colliderThree == null) {
			checkingFourthCollision();
		}
	}

	/*
	 * I am checking balls upper point collision if this is something but null and
	 * paddle this will remove it. PS this point can't be the collision point of the
	 * paddle and the ball.
	 */
	private GObject checkingFirstCollision() {
		GObject collider = getElementAt(ball.getX() + BALL_RADIUS + 1, ball.getY());
		if (collider != null && collider != paddle) {
			vy = -vy;
			remove(collider);
			nrects--;
		}
		return collider;
	}

	/*
	 * Here if the ball's bottom point hit the paddle it will be reflected without
	 * removing it and else is same as above.
	 */
	private GObject checkingSecondCollision() {
		GObject colliderTwo = getElementAt(ball.getX() + BALL_RADIUS + 1, ball.getY() + 2 * BALL_RADIUS);
		if (colliderTwo == paddle) {
			if (vy > 0) {
				vy = -vy;
			}
		} else if (colliderTwo != null) {
			vy = -vy;
			remove(colliderTwo);
			nrects--;
		}
		return colliderTwo;
	}

	/*
	 * If we will get this point so the collision is from the edge so it will be
	 * reflected on X axis.
	 */
	private GObject checkingThirdCollision() {
		GObject colliderThree = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + BALL_RADIUS + 1);
		if (colliderThree != null && colliderThree != paddle) {
			vx = -vx;
			remove(colliderThree);
			nrects--;
		}
		return colliderThree;
	}

	/*
	 * Same as above.
	 */
	private void checkingFourthCollision() {
		GObject colliderFour = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS + 1);
		if (colliderFour != null && colliderFour != paddle) {
			vx = -vx;
			remove(colliderFour);
			nrects--;
		}
	}

	/*
	 * Mouse will control paddle movement if it is in the borders else nothing will gonna happen.
	 */
	public void mouseMoved(MouseEvent e) {
		if (e.getX() - PADDLE_WIDTH / 2 > 0 && e.getX() + PADDLE_WIDTH / 2 < APPLICATION_WIDTH) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, paddle.getY());
		}
	}
}