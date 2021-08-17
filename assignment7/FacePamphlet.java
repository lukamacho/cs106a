
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonListener;

public class FacePamphlet extends Program implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the interactors in the
	 * application, and taking care of any other initialization that needs to be
	 * performed.
	 */
	
	private FacePamphletDatabase data = new FacePamphletDatabase();
	private FacePamphletProfile profile;
	private JButton addFriend;
	private JButton changePicture;
	private JTextField statusText;
	private JTextField friendText;
	private JTextField pictureText;
	private JTextField nametext;
	private JButton add;
	private JButton delete;
	private JButton lookup;
	private JButton changeStatus;
	private FacePamphletCanvas canvas;
	public void init() {
		nameLabel();
		nametext();
		addButton();
		deleteButton();
		lookupButton();
		statusText();
		statusButton();
		emptylabel();
		pictureText();
		pictureButton();
		emptylabel();
		friendText();
		friendButton();
		canvas=new FacePamphletCanvas();
		add(canvas);
		addActionListeners();
	}
/*
 * These codes add JButtons on the screen.
 */
	private void friendButton() {
		addFriend = new JButton("Add friend");
		add(addFriend, WEST);		
	}

	private void friendText() {
		friendText = new JTextField(TEXT_FIELD_SIZE);
		add(friendText, WEST);
		friendText.addActionListener(this);		
	}

	private void emptylabel() {
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
	}

	private void pictureButton() {
		changePicture = new JButton("Change Picture");
		add(changePicture, WEST);		
	}

	private void pictureText() {
		pictureText = new JTextField(TEXT_FIELD_SIZE);
		add(pictureText, WEST);
		pictureText.addActionListener(this);		
	}

	private void statusButton() {
		changeStatus = new JButton("change status");
		add(changeStatus, WEST);		
	}

	private void statusText() {
		statusText = new JTextField(TEXT_FIELD_SIZE);
		add(statusText, WEST);
		statusText.addActionListener(this);		
	}

	private void lookupButton() {
		lookup = new JButton("Lookup");
		add(lookup, NORTH);		
	}

	private void deleteButton() {
		delete = new JButton("Delete");
		add(delete, NORTH);		
	}

	private void addButton() {
		add = new JButton("Add");
		add(add, NORTH);		
	}

	private void nameLabel() {
		JLabel name = new JLabel("Name");
		add(name, NORTH);		
	}

	private void nametext() {
		nametext = new JTextField(TEXT_FIELD_SIZE);
		add(nametext, NORTH);
		nametext.addActionListener(this);		
	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == add) {
			profileAdd();
			}
		if (e.getSource() == delete) {
			deleteProfile();
		}
		if (e.getSource() == lookup) {
			lookUpProfile();
		}
		if (e.getSource() == changeStatus||e.getSource()==statusText) {
			statusprofile();
		}
		if (e.getSource() == changePicture|| e.getSource()==pictureText) {
			pictureProfile();
		}
		if (e.getSource() == friendText||e.getSource() == addFriend) {
			friendAddition();
		}
	}
/*
 * This program adds friend on the screen.
 */
	private void friendAddition() {
		canvas.clearAll();
		profile= data.getProfile(nametext.getText());
		if(data.containsProfile(nametext.getText())) {
		if(data.containsProfile(friendText.getText())) {
			profile.addFriend(friendText.getText());
			data.getProfile(friendText.getText()).addFriend(profile.getName());
			canvas.displayProfile(profile);
			canvas.showMessage("<"+friendText.getText()+"> added as a friend");
		}else {
			canvas.showMessage("<"+friendText.getText()+"> doesn't exist");
		}
			
	}else{
		canvas.showMessage("Please select a profile to add friend");
		}
			
	}
/*
 * This program adds picture of the profile.
 */
	private void pictureProfile() {
		canvas.displayProfile(profile);
		if (data.containsProfile(nametext.getText())) {
			GImage image;
			try {
				image = new GImage(pictureText.getText());
				profile.setImage(image);
				canvas.displayProfile(profile);
				canvas.showMessage("Picture updated");
			} catch (ErrorException ex) {
				canvas.showMessage("Unable to open file: <"+pictureText.getText()+">");
			}
		} else {
			canvas.showMessage("Please select a profile to change picture");
		}		
	}
/*
 * This program adds the status to the profile.
 */
	private void statusprofile() {
		if (data.containsProfile(nametext.getText())) {
			profile.setStatus(statusText.getText());
			canvas.displayProfile(profile);
			canvas.showMessage("profile updated to <"+statusText.getText()+">");

		} else {
			canvas.showMessage("Please selecet a profile to change a status");
		}		
	}
/*
 * This program looks up the profile whose name is written on the nametext.
 */
	private void lookUpProfile() {
		profile= data.getProfile(nametext.getText());
		canvas.displayProfile(profile);
		if (data.containsProfile(nametext.getText())) {
			canvas.showMessage("Displaying <"+nametext.getText()+">");
			println(profile);
		} else {

			canvas.showMessage("A profile with the name <"+nametext.getText()+"> already exists");
		}	
	}
	/*
	 * This program deletes the program of the profile.
	 */
	private void deleteProfile() {
	canvas.displayProfile(profile);
		if (data.containsProfile(nametext.getText())) {
			canvas.clearAll();
			data.deleteProfile(nametext.getText());
			profile.removeFriend(nametext.getText());
			canvas.showMessage("Profile of <"+nametext.getText()+"> deleted");
		} else {
			canvas.showMessage("A profile with the name <"+nametext.getText()+"> already exists");
		}		
	}
/*
 *This code add the profile to the base.
 */
	private void profileAdd() {
		String name = nametext.getText();
		profile = new FacePamphletProfile(name);
		canvas.displayProfile(profile);
		if (data.containsProfile(nametext.getText())) {
			canvas.showMessage("A profile with the name <"+nametext.getText()+">  exists");
		} else {
			canvas.showMessage("New profile created");
		}
		data.addProfile(profile);
	}
}
