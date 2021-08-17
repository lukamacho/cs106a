import java.io.BufferedReader;
import java.lang.reflect.Array;

import acm.util.ErrorException;
import acmx.export.java.io.FileReader;
import java.util.*;
/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	

/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	
private NameSurferEntry entry;
private HashMap<String,NameSurferEntry> base= new HashMap<String,NameSurferEntry>();
	
	public NameSurferDataBase(String filename) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader(filename));
				while (true) {
					String line = rd.readLine();
					
					entry = new NameSurferEntry(line);
					if (line == null) {
						break;
					}
					base.put(entry.getName(),entry);
				}
			
				rd.close();
			} catch (Exception exception) {
				
			}
		}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		if(base.containsKey(name)) {
			entry= base.get(name);
		}else {
			entry=null;
		}
		return entry;
	}
	
}

