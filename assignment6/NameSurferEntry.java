/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.program.ConsoleProgram;
import acm.util.*;
import acmx.export.java.io.FileReader;
import acmx.export.java.util.ArrayList;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
public class NameSurferEntry  implements NameSurferConstants {
	
	
/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	private String personName;
	private int[] arr= new int[11];
	
	public NameSurferEntry(String line) {
		StringTokenizer tk= new StringTokenizer(line);
		personName=tk.nextToken();
		for(int i=0;i<11;i++) {
			int number =Integer.parseInt(tk.nextToken());
			arr[i]=number;
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return personName;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return arr[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String name= personName+getRank(START_DECADE);
		return name;
	}
}

