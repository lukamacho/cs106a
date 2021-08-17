
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private static int mcdelobebisRaodenoba = 8;
	private HangmanCanvas Canvas;
	private HangmanLexicon chamoxrchoba;
	private String gamosacnobiSityva="";
	public void init() {
		Canvas = new HangmanCanvas();
		add(Canvas);
		
	}
	/*
	 *This program controls the working of the game.
	 */

	public void run() {
		RandomGenerator rgen=RandomGenerator.getInstance();
		HangmanLexicon chamoxrchoba = new HangmanLexicon();
		println("Welcome to Hangman");
		chamoxrchoba.readLexicon();
		println(HangmanLexicon.number);
		while(true) {
		String word = chamoxrchoba.getWord(rgen.nextInt(0,chamoxrchoba.number-1));
		for (int i = 0; i < word.length(); i++) {
			gamosacnobiSityva += "-";
		}
		while (true) {
			Canvas.reset();
			println("The word now looks like this " + gamosacnobiSityva);
			println("You have " + mcdelobebisRaodenoba + " guesses left");
			char aso = readLine("Your guess: ").charAt(0);
			if (aso < 65 || aso > 122) {
				println("You didn't enter a letter ,try again");
			} else {
				aso = Character.toUpperCase(aso);
				if (isAlready(gamosacnobiSityva, aso)) {
					println("You entered already known letter " + aso);
				} else {
					if (asosArseboba(word, aso) == false) {
						mcdaricda(aso, word);

					} else {
						for (int i = 0; i < word.length(); i++) {
							if (aso == word.charAt(i)) {
								gamosacnobiSityva = gamosacnobiSityva.substring(0, i) + aso
										+ gamosacnobiSityva.substring(i + 1);
								Canvas.displayWord(gamosacnobiSityva);
								println("Your guess is correct");
							}
						}
					}
				}
				if (gamosacnobiSityva.equalsIgnoreCase(word)) {
					println("You guessed the word " + word);
					println("You won");
					mcdelobebisRaodenoba=8;
				}
			}
		}
	}}

	/*
	 * This order will decrease your number of guesses if your letter is not right
	 * and writes how many try you have left.
	 */
	private int mcdaricda(char aso, String word) {
		println("There is no " + aso + "'s in the word");
		mcdelobebisRaodenoba--;
		Canvas.count++;
		Canvas.noteIncorrectGuess(aso);
		if (mcdelobebisRaodenoba == 0) {
			println("you are completely hung");
			println("The word was " + word);
			println("You lost");
			mcdelobebisRaodenoba=8;
		}
		return mcdelobebisRaodenoba;
	}

	/*
	 * This order checks if the letter which you entered is the right letter which
	 * you already entered or not.
	 */
	private boolean isAlready(String gamosacnobiSityva, char aso) {
		boolean rightLetter = false;
		for (int i = 0; i < gamosacnobiSityva.length(); i++) {
			if (aso == gamosacnobiSityva.charAt(i)) {
				rightLetter = true;
				break;
			}
		}
		return rightLetter;
	}

	/*
	 * This program checks if the letter which you entered is in the word or not.
	 */
	private boolean asosArseboba(String word, char aso) {
		Boolean check = true;
		if (word.indexOf(aso) == -1) {
			check = false;
		}
		return check;
	}

}
