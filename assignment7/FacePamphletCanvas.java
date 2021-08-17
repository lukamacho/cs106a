/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {
	public double x;
	private double x3;
	private double x4;
	private FacePamphlet pam=new FacePamphlet();
	public void clearAll() {
		removeAll();
	}
	
	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	/**
	 * This method displays a message string near the bottom of the canvas. Every
	 * time this method is called, the previously displayed message (if any) is
	 * replaced by the new message text passed in.
	 */
	public void showMessage(String msg) {
		GLabel message = new GLabel(msg);
		double x1 = (getWidth()) / 2;
		double y1 = getHeight() - BOTTOM_MESSAGE_MARGIN;
		message.setFont(MESSAGE_FONT);
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
		removeAll();
		writeName(profile.getName());
		paintFrame();
		writeFriendsNames(profile.getFriends());
		writeStatus(profile.getStatus(), profile.getName());
		paintPicture(profile.getImage());
	}
	/*
	 * This code writes friend names to the friend list.
	 */
	private void writeFriendsNames(Iterator<String> friends) {
		writeFriends();int i=0;
		while(friends.hasNext()) {
			i++;
			double y2 = TOP_MARGIN + IMAGE_MARGIN+x;
			GLabel name= new GLabel (friends.next());
			name.setFont(PROFILE_FRIEND_FONT);
			add(name,getWidth()/2,y2+i*name.getHeight());
		}
	}
	/*
	 * This code writes friend on the screen.
	 */
	private void writeFriends() {
		double y2 = TOP_MARGIN + IMAGE_MARGIN+x;
		GLabel friend= new GLabel("Friends :");
		friend.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friend,getWidth()/2,y2);
	}
/*
 * This code paints picture on the screen.
 */
	private void paintPicture(GImage image) {
		double x2 = LEFT_MARGIN;
		double y2 = TOP_MARGIN + IMAGE_MARGIN+x;
		if (image != null) {
			image.scale(IMAGE_WIDTH/image.getWidth(),IMAGE_HEIGHT/image.getHeight());
			image.setLocation(x2, y2);
			add(image);
		} else {
			GLabel cur = new GLabel("No image");
			cur.setFont(PROFILE_IMAGE_FONT);
			x3 = x2 + (IMAGE_WIDTH - cur.getWidth()) / 2;
			x4 = y2 + (IMAGE_HEIGHT + cur.getHeight()) / 2;
			add(cur, x3, x4);
		}
	}
/*
 * This prorgam writes status of the string.
 */
	private void writeStatus(String status, String name) {
		double x1 = LEFT_MARGIN;
		double y1 = TOP_MARGIN + IMAGE_MARGIN + x + STATUS_MARGIN + IMAGE_HEIGHT;
		if (getElementAt(x1, y1) != null) {
			remove(getElementAt(x1, y1));
		}
		if (status == null) {
			GLabel statusi = new GLabel("No current status");
			statusi.setFont(PROFILE_STATUS_FONT);
			add(statusi, x1, y1);
		} else {
			GLabel statusi = new GLabel(name + " is " + status);
			statusi.setFont(PROFILE_STATUS_FONT);
			add(statusi, x1, y1);
		}
	}
/*
 * This program writes name of the profile.
 */
	private void writeName(String name) {
		double x1 = LEFT_MARGIN;
		double y1 = TOP_MARGIN;
		if (getElementAt(x1, y1) != null) {
			remove(getElementAt(x1, y1));
		}if(name!=null) {
		GLabel prof = new GLabel(name);
		prof.setFont(PROFILE_NAME_FONT);
		prof.setColor(Color.BLUE);
		x = prof.getHeight();
		prof.setLocation(x1, prof.getHeight() + y1);
		add(prof);}
	}
/*
 * This program paints frame on the screen.
 */
	private void paintFrame() {
		double x1 = IMAGE_WIDTH;
		double y1 = IMAGE_HEIGHT;
		double x2 = LEFT_MARGIN;
		double y2 = TOP_MARGIN + IMAGE_MARGIN + x;
		if (getElementAt(x2, y2) != null) {
			remove(getElementAt(x2, y2));
		}
		GRect frame = new GRect(x2, y2, x1, y1);
		add(frame);
	}

}
