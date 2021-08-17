
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.program.ConsoleProgram;
import acm.util.*;
import acmx.export.java.io.FileReader;

public class HangmanLexicon {
	public static int number = 0;
	public   ArrayList<String> lexicon=new ArrayList <String>();
	/*
	 * This program read the hangmanlexicon.txt file.And adds lines to the arraylist.
	 */
	public void  readLexicon() {
				try {
					BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
					while (true) {
						String line = rd.readLine();
						if (line == null) {
							break;
						}
						lexicon.add(line);
					}
					number = lexicon.size();
					rd.close();
				} catch (Exception exception) {
					number=-1;
				}
			}
		
	

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return number-1;
	}

	/** Returns the word at the specified index. 
	 * @param lexicon 
	 * @return */
	public String getWord(int index) {
	readLexicon();
	return   lexicon.get(index);
	};
}
