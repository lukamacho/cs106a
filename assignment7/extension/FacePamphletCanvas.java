/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants,ComponentListener {
	public double x;
	private double x3;
	private double x4;
	private static GImage imag;
	private static String mess;
	private static String name;
	private static String status;
	private static Iterator<String>friends;
	private static Iterator<String>siblings;
	private static String gender;
	private FacePamphletProfile pamphlet= new FacePamphletProfile(name);
	public void clearAll() {
		removeAll();
	}
	
	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		addComponentListener(this);
}
	/**
	 * This method updates the screen when the window size changes.
	 */
	public void update() {
		removeAll();	
		writeMessage(mess);
		paintFrame();
		writeName(name,gender);
		writeStatus(status,name);
		paintPicture(imag);
		friends=pamphlet.getFriends();
		writeFriendsNames(friends);
		writeSiblingNames(siblings);
	}
	
	private void writeSiblingNames(Iterator<String> siblings) {
		writeSiblings();
		writeArraysOfSiblings(siblings);
	}
	/*
	 * This code writes word sibling.
	 */
	private void writeSiblings() {
		double y2 = (TOP_MARGIN + IMAGE_MARGIN+x)*getHeight()/APPLICATION_HEIGHT;
		GLabel friend= new GLabel("Siblings :");
		friend.setFont(new Font("Dialog",Font.BOLD,16*getWidth()/APPLICATION_WIDTH));
		add(friend,getWidth()/4*3,y2);
	}		
	/*
	 * This code writes siblings arrayList on the screen.
	 */
	private void writeArraysOfSiblings(Iterator<String> siblings) {
		int i=0;
		while(siblings.hasNext()) {
			i++;
			double y2 = (TOP_MARGIN + IMAGE_MARGIN+x)*getHeight()/APPLICATION_HEIGHT;
			GLabel name= new GLabel (siblings.next());
			name.setFont(new Font ("Dialog",Font.PLAIN,16*getWidth()/APPLICATION_WIDTH));
			add(name,getWidth()/4*3,y2+i*name.getHeight());
		}		
		
	}

	/**
	 * This method displays a message string near the bottom of the canvas. Every
	 * time this method is called, the previously displayed message (if any) is
	 * replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		mess=msg;
		writeMessage(mess);
	}

	private void writeMessage(String msg) {
		GLabel message = new GLabel(msg);
		double x1 = (getWidth()) / 2;
		double y1 = getHeight() - BOTTOM_MESSAGE_MARGIN;
		int fontSize=(int) (getWidth()*18/APPLICATION_WIDTH);
		message.setFont(new Font("Dialog",Font.PLAIN,fontSize));
		message.setLocation(x1 - message.getWidth() / 2, y1);
		if (getElementAt(x1 - message.getWidth() / 2, y1) != null) {
			remove(getElementAt(x1 - message.getWidth() / 2, y1));
		}
		add(message);		
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the bottom
	 * of the screen) and then the given profile is displayed. The profile display
	 * includes the name of the user from the profile, the corresponding image (or
	 * an indication that an image does not exist), the status of the user, and a
	 * list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		name=profile.getName();
		status=profile.getStatus();
		imag=profile.getImage();
		friends=profile.getFriends();
		siblings=profile.getSiblings();
		gender=profile.getGender();
		removeAll();
		writeName(name,gender);
		paintFrame();
		writeFriendsNames(friends);
		writeStatus(status,name);
		paintPicture(imag);
		writeSiblingNames(siblings);
	}


	/*
	 * This code writes friend names to the friend list.
	 */
	private void writeFriendsNames(Iterator<String> friends) {
		writeFriends();
		writeArrayofFriends(friends);
	}
	private void writeArrayofFriends(Iterator<String> friends) {
		int i=0;
		while(friends.hasNext()) {
			i++;
			double y2 = (TOP_MARGIN + IMAGE_MARGIN+x)*getHeight()/APPLICATION_HEIGHT;
			GLabel name= new GLabel (friends.next());
			name.setFont(new Font ("Dialog",Font.PLAIN,16*getWidth()/APPLICATION_WIDTH));
			add(name,getWidth()/2,y2+i*name.getHeight());
		}		
	}

	/*
	 * This code writes friend on the screen.
	 */
	private void writeFriends() {
		double y2 = (TOP_MARGIN + IMAGE_MARGIN+x)*getHeight()/APPLICATION_HEIGHT;
		GLabel friend= new GLabel("Friends :");
		friend.setFont(new Font("Dialog",Font.BOLD,16*getWidth()/APPLICATION_WIDTH));
		add(friend,getWidth()/2,y2);
	}
/*
 * This code paints picture on the screen.
 */
	private void paintPicture(GImage image) {
		double x2 = LEFT_MARGIN*getWidth()/APPLICATION_WIDTH;
		double y2 = (TOP_MARGIN + IMAGE_MARGIN+x)*getHeight()/APPLICATION_HEIGHT;
		if (image != null) {
			image.scale(IMAGE_WIDTH*getWidth()/image.getWidth()/APPLICATION_WIDTH,IMAGE_HEIGHT*getHeight()/image.getHeight()/APPLICATION_HEIGHT);
			image.setLocation(x2, y2);
			add(image);
		} else {
			GLabel cur = new GLabel("No image");
			cur.setFont(new Font("Dialog",Font.BOLD,16*getWidth()/APPLICATION_WIDTH));
			x3 = x2 + (IMAGE_WIDTH - cur.getWidth()) / 2*getWidth()/APPLICATION_WIDTH;
			x4 = y2 + (IMAGE_HEIGHT + cur.getHeight()) / 2*getHeight()/APPLICATION_HEIGHT;
			add(cur, x3, x4);
		}
	}
/*
 * This prorgam writes status of the string.
 */
	private void writeStatus(String status, String name) {
		double x1 = LEFT_MARGIN*getWidth()/APPLICATION_WIDTH;
		double y1 = (TOP_MARGIN + IMAGE_MARGIN + x + STATUS_MARGIN + IMAGE_HEIGHT)*getHeight()/APPLICATION_HEIGHT;
		if (getElementAt(x1, y1) != null) {
			remove(getElementAt(x1, y1));
		}
		if (status == null) {
			GLabel statusi = new GLabel("No current status");
			statusi.setFont(new Font("Dialog",Font.BOLD,16*getWidth()/APPLICATION_WIDTH));
			add(statusi, x1, y1);
		} else {
			GLabel statusi = new GLabel(name + " is " + status);
			statusi.setFont(new Font("Dialog",Font.BOLD,16*getWidth()/APPLICATION_WIDTH));
			add(statusi, x1, y1);
		}
	}
/*
 * This program writes name of the profile.
 */
	private void writeName(String name, String gender) {
		double x1 = LEFT_MARGIN*getWidth()/APPLICATION_WIDTH;
		double y1 = TOP_MARGIN*getHeight()/APPLICATION_HEIGHT;
		if (getElementAt(x1, y1) != null) {
			remove(getElementAt(x1, y1));
		}if(name!=null) {
		GLabel prof = new GLabel(name+"("+gender+")");
		int fontSize=(int) (getWidth()*24/APPLICATION_WIDTH);
		prof.setFont(new Font("Dialog",Font.PLAIN,fontSize));
		prof.setColor(Color.BLUE);
		x = prof.getHeight();
		prof.setLocation(x1, prof.getHeight() + y1);
		add(prof);}
	}
/*
 * This program paints frame on the screen.
 */
	private void paintFrame() {
		double x1 = IMAGE_WIDTH*getWidth()/APPLICATION_WIDTH;
		double y1 = IMAGE_HEIGHT*getHeight()/APPLICATION_HEIGHT;
		double x2 = LEFT_MARGIN*getWidth()/APPLICATION_WIDTH;
		double y2 = (TOP_MARGIN + IMAGE_MARGIN + x)*getHeight()/APPLICATION_HEIGHT;
		if (getElementAt(x2, y2) != null) {
			remove(getElementAt(x2, y2));
		}
		GRect frame = new GRect(x2, y2, x1, y1);
		add(frame);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		update();		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
