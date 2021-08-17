
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 * 
	 * @return
	 */
	public int k = 0;
	private static int n = 1;
	private ArrayList<NameSurferEntry> database = new ArrayList<NameSurferEntry>();

	public NameSurferGraph() {
		addComponentListener(this);
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		database.clear();
		update();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		if (entry == null)
			return;
		database.add(entry);
		update();
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		
			removeAll();
			paintHorizontalLines();
			paintVerticalLines();
			writeDecades();
			paintDiagrams();
			writeNames();
		

	}

	/*
	 * This program writes appropriate name on the lines.
	 */
	private void writeNames() {
		for (int j = 0; j < database.size(); j++) {
			NameSurferEntry obj = database.get(j);
			for (int i = 0; i < 11; i++) {
				if (obj.getRank(i) != 0) {
					double x1 = getWidth() / 11 * i;
					double y1 = (getHeight() - 2. * GRAPH_MARGIN_SIZE) / 1000. * obj.getRank(i) + GRAPH_MARGIN_SIZE;
					GLabel name = new GLabel(obj.getName() + obj.getRank(i));
					int fontSize = (int) getWidth() * 15 / APPLICATION_WIDTH;
					name.setFont(new Font("Sanserif", Font.PLAIN, fontSize));
					add(name, x1, y1);
				} else {
					double x1 = getWidth() / 11 * i;
					double y1 = getHeight() - GRAPH_MARGIN_SIZE;
					GLabel name = new GLabel(obj.getName() + obj.getRank(i));
					int fontSize = (int) getWidth() * 15 / APPLICATION_WIDTH;
					name.setFont(new Font("Sanserif", Font.PLAIN, fontSize));
					add(name, x1, y1);
				}
			}
		}
	}

	/*
	 * This code defines the colour of the lines.
	 */
	private Color colore() {
		if (n % 5 == 1) {
			return Color.RED;
		} else if (n % 5 == 2) {
			return Color.GREEN;
		} else if (n % 5 == 3) {
			return Color.YELLOW;
		} else if (n % 5 == 4) {
			return Color.CYAN;
		} else {
			return Color.ORANGE;
		}
	}

	/*
	 * This program paints the diagram of the appropriate name which you entered and
	 * it's font size will be changed with respect to screen size.
	 */
	private void paintDiagrams() {
		for (int j = 0; j < database.size(); j++) {
			NameSurferEntry obj = database.get(j);
			n++;
			for (int i = 0; i < NDECADES - 1; i++) {
				if (obj.getRank(i) == 0 && obj.getRank(i + 1) != 0) {
					paintFirstTypeLines(i,obj);
				} else if (obj.getRank(i) == 0 && obj.getRank(i + 1) == 0) {
					paintSecondTypeLines(i,obj);
				} else if (obj.getRank(i) != 0 &&obj.getRank(i+1)!=0) {
					paintThirdTypeLines(i,obj);
				}else {
				paintFourthTypeLines(i,obj);
					
				}
			}
		}
	}
/*
 * This code paints fourth type of lines.
 */
	private void paintFourthTypeLines(int i, NameSurferEntry obj) {
		double x1 = getWidth() / 11 * i;
		double y1 = (double) (getHeight() - 2. * GRAPH_MARGIN_SIZE) / MAX_RANK * obj.getRank(i)
				+ GRAPH_MARGIN_SIZE;
		double x2 = getWidth() / 11 * (i + 1);
		double y2 = getHeight()- GRAPH_MARGIN_SIZE;
		GLine line = new GLine(x1, y1, x2, y2);
		line.setColor(colore());
		add(line);		
	}
/*
 * This code paints third type of lines.
 */
	private void paintThirdTypeLines(int i, NameSurferEntry obj) {
		double x1 = getWidth() / 11 * i;
		double y1 = (double) (getHeight() - 2. * GRAPH_MARGIN_SIZE) / MAX_RANK * obj.getRank(i)
				+ GRAPH_MARGIN_SIZE;
		double x2 = getWidth() / 11 * (i + 1);
		double y2 = (double) (getHeight() - 2. * GRAPH_MARGIN_SIZE) / MAX_RANK * obj.getRank(i + 1)
				+ GRAPH_MARGIN_SIZE;
		GLine line = new GLine(x1, y1, x2, y2);
		line.setColor(colore());
		add(line);		
	}
/*
 * This code paints second type of lines.
 */
	private void paintSecondTypeLines(int i, NameSurferEntry obj) {
		double x1 = getWidth() / 11 * i;
		double y1 = getHeight() - GRAPH_MARGIN_SIZE;
		double x2 = getWidth() / 11 * (i + 1);
		GLine line = new GLine(x1, y1, x2, y1);
		line.setColor(colore());
		add(line);		
	}
/*
 * This code paints first type of lines.
 */
	private void paintFirstTypeLines(int i, NameSurferEntry obj) {
		double x1 = getWidth() / 11 * i;
		double y1 = getHeight() - GRAPH_MARGIN_SIZE;
		double x2 = getWidth() / 11 * (i + 1);
		double y2 = (double) (getHeight() - 2. * GRAPH_MARGIN_SIZE) / MAX_RANK * obj.getRank(i + 1)
				+ GRAPH_MARGIN_SIZE;
		GLine line = new GLine(x1, y1, x2, y2);
		line.setColor(colore());
		add(line);		
	}

	/*
	 * This program writes decades on the canvas and its font size will be changed with respect to screen size.
	 */
	private void writeDecades() {
		for (int i = 0; i < NDECADES; i++) {
			int dec = START_DECADE + 10 * i;
			String str = String.valueOf(dec);
			double x1 = 2 + getWidth() / 11 * i;
			double y1 = getHeight() - 2;
			GLabel decade = new GLabel(str);
			int fontSize = (int) getWidth() * 15 / APPLICATION_WIDTH;
			decade.setFont(new Font("Sanserif", Font.PLAIN, fontSize));
			add(decade, x1, y1);
		}
	}

	/*
	 * This program paints two vertical lines on the canvas.
	 */
	private void paintVerticalLines() {
		double x1 = 0;
		double x2 = getWidth();
		double y1 = GRAPH_MARGIN_SIZE;
		GLine upperLine = new GLine(x1, y1, x2, y1);
		GLine lowerLine = new GLine(x1, getHeight() - y1, x2, getHeight() - y1);
		add(lowerLine);
		add(upperLine);

	}

	/*
	 * This program paints horizontal lines on the canvas.
	 */
	private void paintHorizontalLines() {
		for (int i = 1; i < 11; i++) {
			double x1 = getWidth() / 11 * i;
			double y1 = 0;
			double y2 = getHeight();
			GLine horizontal = new GLine(x1, y1, x1, y2);
			add(horizontal);
		}
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
