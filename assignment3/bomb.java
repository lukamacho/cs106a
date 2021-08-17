import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class bomb extends GCompound {

	private static final double WIDTH = 400;
	private static final double APPLICATION_HEIGHT = 600;
	private static final int TNT_WIDTH = 20;
	private static final int TNT_HEIGHT = 20;
	private static final int WICK_WIDTH = 3;
	private static final int WICK_HEIGHT = 8;
	private GOval mainTNT;
	private GRect wick;
	private RandomGenerator rgen =RandomGenerator.getInstance();
	public bomb() {
		mainTNT = new GOval(TNT_WIDTH, TNT_HEIGHT);
		mainTNT.setColor(Color.black);
		mainTNT.setFilled(true);
		wick = new GRect(WICK_WIDTH, WICK_HEIGHT);
		wick.setColor(Color.red);
		wick.setFilled(true);
		double x1 =rgen.nextDouble(TNT_WIDTH/2,WIDTH-TNT_WIDTH/2);
		add(mainTNT, 0, APPLICATION_HEIGHT / 20);
		add(wick, TNT_WIDTH/2, APPLICATION_HEIGHT / 20 - WICK_HEIGHT);
		this.setLocation( x1, 0);
	}

}
