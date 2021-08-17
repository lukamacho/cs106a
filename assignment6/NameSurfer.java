/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */ 
	private String databaseName;
	private  NameSurferGraph graphi= new NameSurferGraph();;
	private NameSurferDataBase basa= new NameSurferDataBase("names-data.txt");
	public void init() {
		JLabel name = new JLabel("Name");
		add(name,SOUTH);
		text = new JTextField(20);
		text.addActionListener(this);
		add(text,SOUTH);
		graph = new JButton("Graph");
		add(graph,SOUTH);
		add(new JButton ("Clear"),SOUTH);
		
		add(graphi);
		
	    addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Clear")) {
			graphi.clear();
		}
		if(e.getSource()== graph) {
			databaseName=text.getText();
			NameSurferEntry entry = basa.findEntry(databaseName);
			graphi.addEntry(entry);
		}
	}
	private JTextField text;
	private JButton graph;
	private JButton delete;
	private JButton table;
}
