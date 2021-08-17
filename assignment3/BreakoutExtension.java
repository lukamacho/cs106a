
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

public class BreakoutExtension extends GraphicsProgram {

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
	private static final int NTURNS = 1;
	/** Number of rectangles */
	private static final int NRECTS = 300;

	private static int PAUSE_TIME = 15;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int turns = NTURNS;
	private int nrects = NRECTS;
	double vy = 3;
	double vx;
	private GOval ball = null;
	private trolley paddle = null;
	private static final double BOMB_WIDTH = 40;
	private static final double BOMB_HEIGHT = 48;
	private bomb TNT = null;

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
			bombIsComing();
			if (turns == 0 || nrects == 0) {
				break;
			}
			pause(PAUSE_TIME);
		}
		if (turns == 0) {
			losingWords();
		}
		if (nrects == 0) {
			winningWords();
		}

	}

	/*
	 * Order which creates bomb and controls its movement.
	 */
	private void bombIsComing() {
		if (nrects % 3 == 1 && TNT == null) {
			TNT = madeBomb();
			paddle.sendToFront();
		}
		if (TNT != null) {
			bombMovement();
		}

	}

	/*
	 * Bomb making order.
	 */
	private bomb madeBomb() {
		TNT = new bomb();
		add(TNT);
		return TNT;
	}

	/*
	 * If the ball is in the borders of the application it will move until it wont
	 * crash the paddle and if will go out of the borders it will be removed.
	 */
	private void bombMovement() {
		if (TNT.getY() < APPLICATION_HEIGHT) {
			double dx = 0;
			double dy = 4;
			GObject crash = getElementAt(TNT.getX(), TNT.getY() + BOMB_HEIGHT);
			if (crash == paddle) {
				remove(paddle);
				remove(TNT);
				remove(ball);
				losingWords();
				return;
			} else {
				TNT.move(dx, dy);
				pause(5);
			}
		}
		if (TNT.getY() > APPLICATION_HEIGHT - BOMB_HEIGHT && TNT != null) {
			remove(TNT);
			TNT = null;
		}

	}

	/*
	 * If you lost the game you will see this words.
	 */
	private void losingWords() {
		GLabel lose = new GLabel("You lost");
		lose.setLocation(APPLICATION_WIDTH / 2 - lose.getWidth(), APPLICATION_HEIGHT * 3 / 5);
		lose.setFont("London-36");
		lose.setColor(Color.GREEN);
		add(lose);
	}

	/*
	 * If you won the game you will see this words.
	 */
	private void winningWords() {
		GLabel win = new GLabel("You won");
		win.setLocation(APPLICATION_WIDTH / 2 - win.getWidth(), APPLICATION_HEIGHT * 3 / 5);
		win.setFont("London-36");
		win.setColor(Color.red);
		add(win);
	}

	/*
	 * This order paints rectangles.
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
	private trolley paintPaddle() {
		trolley paddle = new trolley();
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
	 * Controls ball movements on the screen.
	 */
	private void ballMove() {
		checkingWallCollision();
		collisonChecking();
		ball.move(vx, vy);
	}

	/*
	 * Controls ball collisions on the walls.
	 */
	private void checkingWallCollision() {
		if (ball.getX() < 0 || ball.getX() > APPLICATION_WIDTH - 2 * BALL_RADIUS) {
			vx = -vx;
		} else if (ball.getY() < 0) {
			vy = -vy;
		} else if (ball.getY() > APPLICATION_HEIGHT - PADDLE_Y_OFFSET && turns > 0) {
			remove(ball);
			turns--;
			ball = paintBall();
			waitForClick();
		}

	}

	/*
	 * Checks ball collisions on the GObjects.
	 */
	private void collisonChecking() {
		checkingSecondCollision();
		checkingThirdCollision();
		checkingFirstCollision();
		checkingFourthCollision();
	}
	/*
	 * Check if the upper middle point +1 collides something or not and reflected
	 * from it and removes it if it is necessary.
	 */

	private GObject checkingFirstCollision() {
		GObject collider = getElementAt(ball.getX() + BALL_RADIUS + 1, ball.getY());
		if (collider != null && collider != paddle && collider != TNT) {
			vy = -vy;
			change(collider);
			nrects--;
		}
		return collider;
	}

	/*
	 * If the collision object colour is cyan this will be removed,else if its
	 * colour green this will recoloured to cyan an increase its velocity,if its
	 * colour is yellow then it will become green and so on.
	 */
	private void change(GObject collider) {
		if (collider.getColor() == Color.red) {
			collider.setColor(Color.orange);
			PAUSE_TIME = 3;
		} else if (collider.getColor() == Color.orange) {
			collider.setColor(Color.yellow);
			PAUSE_TIME = 4;
		} else if (collider.getColor() == Color.yellow) {
			collider.setColor(Color.green);
			PAUSE_TIME = 6;
		} else if (collider.getColor() == Color.green) {
			collider.setColor(Color.cyan);
			PAUSE_TIME = 8;
		} else if (collider.getColor() == Color.cyan) {
			remove(collider);
		}

	}

	/*
	 * Check if the bottom middle point+1 collides something or not and reflected
	 * from it and removes it.
	 */
	private GObject checkingSecondCollision() {
		GObject colliderTwo = getElementAt(ball.getX() + BALL_RADIUS + 1, ball.getY() + 2 * BALL_RADIUS);
		if (colliderTwo == paddle) {
			if (vy > 0) {
				vy = -vy;
			}
		} else if (colliderTwo != null && colliderTwo != TNT) {
			vx = -vx;
			remove(colliderTwo);
			nrects--;
		}
		return colliderTwo;
	}

	/*
	 * Check if the right middle point +1 collides something or not and reflected
	 * from it and removes it.
	 */
	private GObject checkingThirdCollision() {
		GObject colliderThree = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + BALL_RADIUS + 1);
		if (colliderThree != null && colliderThree != paddle && colliderThree != TNT) {
			vx = -vx;
			remove(colliderThree);
			nrects--;
		}
		return colliderThree;
	}

	/*
	 * Check if the left middle point +1 collides something or not and reflected
	 * from it and removes it .
	 */
	private GObject checkingFourthCollision() {
		GObject colliderFour = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS + 1);
		if (colliderFour != null && colliderFour != paddle && colliderFour != TNT) {
			vx = -vx;
			remove(colliderFour);
			nrects--;
		}
		return colliderFour;
	}

	/*
	 * Mouse will control paddle movement until its coordinates are in the borders
	 * of the screen.
	 */
	public void mouseMoved(MouseEvent e) {
		if (e.getX() - PADDLE_WIDTH / 2 > 0 && e.getX() + PADDLE_WIDTH < APPLICATION_WIDTH) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, paddle.getY());
		}
	}
}
