
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.Arrays;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	/*
	 * This code controls the well-functioning of the game.
	 */
	private void playGame() {
		int cxrili[][] = new int[18][nPlayers];
		for (int m = 0; m < 13; m++) {
			for (int i = 1; i <= nPlayers; i++) {
				randomDice();
				shetyobinebebi(i);
				diceChange();
				cxrilisShevseba(i, cxrili);
			}
		}
		for (int i = 0; i < nPlayers; i++) {
			if (max < cxrili[17][i]) {
				max = cxrili[17][i];
				index = i;
			}
		}
		display.printMessage("Congratulations " + playerNames[index] + " you're the winner of a game");
	}
/*
 * Defines how to function the board well.
 */
	private void cxrilisShevseba(int i, int[][] cxrili) {
		int category = display.waitForPlayerToSelectCategory();
		sameDices(category);
		if (category <= 6) {
			smallCategories(i, category, cxrili);
		}
		threeCombination(category, i, cxrili);
		fourCombination(category, i, cxrili);
		fullHouse(category, i, cxrili);
		smallStraight(category, i, cxrili);
		largeStraight(category, i, cxrili);
		yahtzeesCategory(category, i, cxrili);
		chanceCategory(category, i, cxrili);
		cxrilisAjamva(i, cxrili);
	}

	/*
	 * Checks if your chosen category is chance and writes sum if it is.
	 */
	private void chanceCategory(int category, int i, int[][] cxrili) {
		if (category == 15 && cxrili[15][i - 1] == 0) {
			cxrili[15][i - 1] = sum;
			display.updateScorecard(15, i, sum);
		}
	}

	/*
	 * Checks if your category is yahtzee and writes 50 if it is true.
	 */
	private void yahtzeesCategory(int category, int i, int[][] cxrili) {
		if (category == 14 && cxrili[14][i - 1] == 0 && yahtzees()) {
			cxrili[14][i - 1] = 50;
			display.updateScorecard(14, i, 50);
		} else if (category == 14 && cxrili[14][i - 1] == 0 && yahtzees() == false) {
			display.updateScorecard(14, i, 0);
		}
	}

	/*
	 * Checks if large straight is your chosen category or not and writes 40 if it
	 * is true.
	 */
	private void largeStraight(int category, int i, int[][] cxrili) {
		if (category == 13 && cxrili[13][i - 1] == 0 && largeStraight()) {
			cxrili[13][i - 1] = 40;
			display.updateScorecard(13, i, 40);
		} else if (category == 13 && cxrili[13][i - 1] == 0 && largeStraight() == false) {
			display.updateScorecard(13, i, 0);
		}
	}

	/*
	 * Checks if small straight is your chosen category or not and writes 30 if it
	 * is true.
	 */
	private void smallStraight(int category, int i, int[][] cxrili) {
		if (category == 12 && cxrili[12][i - 1] == 0 && smallStraight()) {
			cxrili[12][i - 1] = 30;
			display.updateScorecard(12, i, 30);
		} else if (category == 12 && cxrili[12][i - 1] == 0 && smallStraight() == false) {
			display.updateScorecard(12, i, 0);
		}
	}

	/*
	 * Checks if full house is your chosen category or not and writes 245 if it is
	 * true.
	 */
	private void fullHouse(int category, int i, int[][] cxrili) {
		if (category == 11 && cxrili[11][i - 1] == 0 && fullHouse()) {
			cxrili[11][i - 1] = 25;
			display.updateScorecard(11, i, 25);
		} else if (category == 11 && cxrili[11][i - 1] == 0 && fullHouse() == false) {
			display.updateScorecard(11, i, 0);
		}
	}

	/*
	 * Checks if your chosen combination is four of the a kind or not and writes sum
	 * of the dice if it is true.
	 */
	private void fourCombination(int category, int i, int[][] cxrili) {
		if (category == 10 && cxrili[10][i - 1] == 0 && fourOfAKind()) {
			cxrili[10][i - 1] = sum;
			display.updateScorecard(10, i, sum);
		} else if (category == 10 && cxrili[10][i - 1] == 0 && fourOfAKind() == false) {
			display.updateScorecard(category, i, 0);
		}
	}

	/*
	 * Checks if you chose three of a kind combination and writes sum of the dice if
	 * it is true.
	 */
	private void threeCombination(int category, int i, int[][] cxrili) {
		if (category == 9 && cxrili[9][i - 1] == 0 && threeOfAKind()) {
			display.updateScorecard(category, i, sum);
			cxrili[9][i - 1] = sum;
		} else if (category == 9 && cxrili[9][i - 1] == 0 && threeOfAKind() == false) {
			display.updateScorecard(category, i, 0);
		}
	}

	/*
	 * Checks if your chosen category is on the upper part of the board and writes
	 * appropriate scores.
	 */
	private void smallCategories(int i, int category, int[][] cxrili) {
		display.updateScorecard(category, i, category * numberity);
		cxrili[category][i - 1] = category * numberity;
		numberity = 0;
	}

	/*
	 * This program count sum of the numbers 0n the tablo and writes it in the
	 * appropriate places.
	 */
	private void cxrilisAjamva(int i, int[][] cxrili) {
		cxrili[7][i - 1] = 0;
		cxrili[8][i - 1] = 0;
		cxrili[16][i - 1] = 0;
		cxrili[17][i - 1] = 0;
		for (int b = 1; b < 7; b++) {
			cxrili[7][i - 1] += cxrili[b][i - 1];
		}
		for (int a = 9; a < 15; a++) {
			cxrili[16][i - 1] += cxrili[a][i - 1];
		}
		if (cxrili[7][i - 1] > 63) {
			display.updateScorecard(8, i, 35);
		} else {
			display.updateScorecard(8, i, 0);
		}
		cxrili[17][i - 1] = cxrili[16][i - 1] + cxrili[7][i - 1] + cxrili[8][i - 1];
		display.updateScorecard(7, i, cxrili[7][i - 1]);
		display.updateScorecard(16, i, cxrili[16][i - 1]);
		display.updateScorecard(17, i, cxrili[17][i - 1]);
	}

	/*
	 * This program counts the number of same dice in the combination.
	 */
	private void sameDices(int category) {
		for (int c = 0; c < N_DICE; c++) {
			if (dice[c] == category) {
				numberity++;
			}
		}
	}

	/*
	 * Defines appropriate messages on the screen.
	 */
	private void shetyobinebebi(int i) {
		display.printMessage(playerNames[i - 1] + "'s turn! Click Roll Dice button to roll dice ");
		display.waitForPlayerToClickRoll(i);
		display.displayDice(dice);
	}

	/*
	 * This program randomizes the dice .
	 */
	private void randomDice() {
		for (int l = 0; l < N_DICE; l++) {
			dice[l] = rgen.nextInt(1, 6);
		}
	}

	/*
	 * This program gives you an opportunity to change dice and prints the line
	 * which demands to select category.
	 */
	private void diceChange() {
		for (int k = 0; k < 2; k++) {
			display.printMessage("Select the dice you wish to re-roll and click Roll Again");
			display.waitForPlayerToSelectDice();
			sum = 0;
			for (int j = 0; j < N_DICE; j++) {
				if (display.isDieSelected(j)) {
					dice[j] = rgen.nextInt(1, 6);
				}
				sum += dice[j];
			}
			display.displayDice(dice);
		}
		display.printMessage("Select a category for this roll");
	}

	/*
	 * Checks if the dice combination is yahtzees or not and return appropriate
	 * boolean.
	 */
	private boolean yahtzees() {
		boolean q = false;
		int maxCount = 0;
		for (int i = 0; i < N_DICE; i++) {
			int count = 0;
			for (int j = 0; j < N_DICE; j++) {
				if (dice[i] == dice[j]) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
			}
		}
		if (maxCount == 5) {
			q = true;
		} else {
			q = false;
		}
		return q;
	}

	/*
	 * Checks if the dice combination is fourOfAKind or not and returns appropriate
	 * boolean.
	 */
	private boolean fourOfAKind() {
		boolean q = false;
		int maxCount = 0;
		for (int i = 0; i < N_DICE; i++) {
			int count = 0;
			for (int j = 0; j < N_DICE; j++) {
				if (dice[i] == dice[j]) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
			}
		}
		if (maxCount >= 4) {
			q = true;
		} else {
			q = false;
		}
		return q;
	}

	/*
	 * Checks if the appropriate combination is threeOfAKind or not and returns
	 * appropriate boolean.
	 */
	private boolean threeOfAKind() {
		boolean q = false;
		int maxCount = 0;
		for (int i = 0; i < N_DICE; i++) {
			int count = 0;
			for (int j = 0; j < N_DICE; j++) {
				if (dice[i] == dice[j]) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
			}
		}
		if (maxCount >= 3) {
			q = true;
		} else {
			q = false;
		}
		return q;
	}

	/*
	 * Checks if the dice combination is fullHouse or not and returns appropriate
	 * boolean.(aq ar vicodi yahtzees itvleboda tu ara fullHousad da me chavtvale ar
	 * damaklot amashi.)
	 */
	private boolean fullHouse() {
		boolean q = false;
		int maxCount = 0;
		Arrays.sort(dice);
		for (int i = 0; i < N_DICE - 1; i++) {
			if (dice[i] == dice[i + 1]) {
				maxCount++;
			}
		}
		if ((maxCount == 3 && dice[1] != dice[4]) || maxCount == 4) {
			q = true;
		} else {
			q = false;
		}
		return q;
	}

	/*
	 * Checks if the dice combination is smallStraight or not and returns
	 * appropriate boolean.
	 */
	private boolean smallStraight() {
		boolean q = false;
		int count = 0;
		Arrays.sort(dice);
		for (int i = 0; i < N_DICE - 1; i++) {
			if (dice[i] + 1 == dice[i + 1]) {
				count++;
			}
		}
		if (count >= 3) {
			q = true;
		} else {
			q = false;
		}
		return q;
	}

	/*
	 * Checks if the dice combination is largeStraight or not and returns
	 * appropriate boolean.
	 */
	private boolean largeStraight() {
		boolean q = false;
		int count = 0;
		Arrays.sort(dice);
		for (int i = 0; i < N_DICE - 1; i++) {
			if (dice[i] + 1 == dice[i + 1]) {
				count++;
			}
		}
		if (count == 4) {
			q = true;
		} else {
			q = false;
		}
		return q;
	}

	public int[][] getCxrili() {
		return cxrili;
	}

	public void setCxrili(int[][] cxrili) {
		this.cxrili = cxrili;
	}

	/* Private instance variables */
	private static int max = 0;
	private static int index = 0;
	private int[][] cxrili;
	private static int numberity = 0;
	private static int sum = 0;
	private int[] dice = new int[N_DICE];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
