
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	public int count;
	
	/** Resets the display so that only the scaffold appears */
	public void reset() {
		painthang();
		if(count%8==0) {
			removeEverything();
		}
	}

	/*
	 * This order checks if the this particle is painted on the screen or not and removes it.
	 */
	private void removeEverything() {
		removeHead();
		removeBody();
		renoveRightHand();
		removeLeftHand();
		removeRightLeg();
		removeLeftLeg();
		removeRightFoot();
		removeLeftFoot();
	}

	private void removeLeftFoot() {
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH;
		if(getElementAt(x2,y1)!=null) {
			remove(getElementAt(x2,y1));
		}
	}

	private void removeRightFoot() {
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH;
		if(getElementAt(x2,y1)!=null) {
			remove(getElementAt(x2,y1));
		}
	}

	private void removeLeftLeg() {
		double x1 = getWidth() / 6 + BEAM_LENGTH-1;
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH - HIP_WIDTH;
		double y2 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH-5;
		if(getElementAt(x1,y1)!=null) {
			remove(getElementAt(x2,y2));
			remove(getElementAt(x1,y1));
		}
	}

	private void removeRightLeg() {
		double x1 = getWidth() / 6 + BEAM_LENGTH;
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH + HIP_WIDTH;
		double y2 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH +10;
		if(getElementAt(x2,y2)!=null) {
			remove(getElementAt(x1,y1));
			remove(getElementAt(x2,y2));
		}
		
	}

	private void removeLeftHand() {
		double x4 = getWidth() / 6 + BEAM_LENGTH - UPPER_ARM_LENGTH;
		double y4 = getHeight() / 20 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH + ROPE_LENGTH;
		double x1 = getWidth() / 6 + BEAM_LENGTH;
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		if(getElementAt(x4,y4)!=null) {
			remove(getElementAt(x4,y4));
			remove(getElementAt(x1,y1));
		}
	}

	private void renoveRightHand() {
		double y3 = getHeight() / 20 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + ROPE_LENGTH;
		double x3 = getWidth() / 6 + BEAM_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH + UPPER_ARM_LENGTH;
		double y2 = getHeight() / 20 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH + ROPE_LENGTH;
		if(getElementAt(x3,y3)!=null) {
			remove(getElementAt(x3,y3));
			remove(getElementAt(x2,y2));
		}
	}

	private void removeBody() {
		double x2 = getWidth() / 6 + BEAM_LENGTH;
		double y2 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + 1;
		if(getElementAt(x2,y2)!=null) {
			remove(getElementAt(x2,y2));
		}
	}

	private void removeHead() {
		double x1 = getWidth() / 6 + BEAM_LENGTH;
		double y1 = getHeight() / 20 + ROPE_LENGTH+3;
		if(getElementAt(x1,y1)!=null) {
			remove(getElementAt(x1,y1));
		}
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		double x1=getWidth()/4;
		double y1=getHeight()*7/8;
		if(getElementAt(x1,y1)!=null) {
			remove(getElementAt(x1,y1));
		}
		GLabel gamosacnobiSityva= new GLabel(word);
		gamosacnobiSityva.setLocation(x1, y1);
		add(gamosacnobiSityva);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */
	public void noteIncorrectGuess(char letter) {
		paintman();
		falseLetters(letter);
	}
/*
 * This order paints false letters on the screen.
 */
	private void falseLetters(char letter) {
			double y1=getHeight()*9/10;
			double x1=getWidth()/10+count*8;
			GLabel mcdariAso= new GLabel(""+letter);
			mcdariAso.setLocation(x1, y1);
			add(mcdariAso);
	}

	/*
	 * This order paints hang on the screen.
	 */
	private void painthang() {
		paintScaffold();
		paintrope();
	}

	private void paintrope() {
		double x1 = getWidth() / 6;
		double y1 = getHeight() / 20;
		double x2 = getWidth() / 6 + BEAM_LENGTH;
		GLine beam = new GLine(x1, y1, x2, y1);
		add(beam);
		double y2 = getHeight() / 20 + ROPE_LENGTH;
		GLine rope = new GLine(x2, y1, x2, y2);
		add(rope);
	}

	private void paintScaffold() {
		double x1 = getWidth() / 6;
		double y1 = getHeight() / 20;
		double x2 = getWidth() / 6;
		double y2 = getHeight() / 20 + SCAFFOLD_HEIGHT;
		GLine scaffold = new GLine(x1, y1, x2, y2);
		add(scaffold);
	}

	private void paintman() {
		if (count == 1) {
			paintHead();
		}
		if (count == 2) {
			paintBody();
		}
		if (count == 3) {
			paintLefthand();
		}
		if (count == 4) {
			paintRightHand();
		}
		if (count == 5) {
			paintLeftLeg();
		}
		if (count == 6) {
			paintRightLeg();
		}
		if (count == 7) {
			paintLeftFoot();
		}
		if (count == 8) {
			paintRightFoot();
		}
	}

	private void paintRightFoot() {
		double x1 = getWidth() / 6 + BEAM_LENGTH + HIP_WIDTH;
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH;
		GLine foot = new GLine(x1, y1, x2, y1);
		add(foot);
	}

	private void paintLeftFoot() {
		double x1 = getWidth() / 6 + BEAM_LENGTH - HIP_WIDTH;
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH;
		GLine foot = new GLine(x1, y1, x2, y1);
		add(foot);
	}

	private void paintRightLeg() {
		double x1 = getWidth() / 6 + BEAM_LENGTH;
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH + HIP_WIDTH;
		GLine hip = new GLine(x1, y1, x2, y1);
		add(hip);
		double y2 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine leg = new GLine(x2, y1, x2, y2);
		add(leg);
	}

	private void paintLeftLeg() {
		double x1 = getWidth() / 6 + BEAM_LENGTH;
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH - HIP_WIDTH;
		GLine hip = new GLine(x1, y1, x2, y1);
		add(hip);
		double y2 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine leg = new GLine(x2, y1, x2, y2);
		add(leg);
	}

	private void paintRightHand() {
		double y1 = getHeight() / 20 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + ROPE_LENGTH;
		double x1 = getWidth() / 6 + BEAM_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH + UPPER_ARM_LENGTH;
		GLine upperHand = new GLine(x1, y1, x2, y1);
		add(upperHand);
		double y2 = getHeight() / 20 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH + ROPE_LENGTH;
		GLine lowerHand = new GLine(x2, y1, x2, y2);
		add(lowerHand);
	}

	private void paintLefthand() {
		double y1 = getHeight() / 20 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + ROPE_LENGTH;
		double x1 = getWidth() / 6 + BEAM_LENGTH;
		double x2 = getWidth() / 6 + BEAM_LENGTH - UPPER_ARM_LENGTH;
		GLine upperHand = new GLine(x1, y1, x2, y1);
		add(upperHand);
		double y2 = getHeight() / 20 + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH + ROPE_LENGTH;
		GLine lowerHand = new GLine(x2, y1, x2, y2);
		add(lowerHand);

	}

	private void paintBody() {
		double x1 = getWidth() / 6 + BEAM_LENGTH;
		double y1 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + 1;
		double y2 = getHeight() / 20 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		GLine body = new GLine(x1, y1, x1, y2);
		add(body);
	}

	private void paintHead() {
		double x1 = getWidth() / 6 + BEAM_LENGTH - HEAD_RADIUS;
		double y1 = getHeight() / 20 + ROPE_LENGTH;
		double x2 = 2 * HEAD_RADIUS;
		GOval head = new GOval(x1, y1, x2, x2);
		add(head);
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
