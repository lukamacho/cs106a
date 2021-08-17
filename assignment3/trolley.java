import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class trolley extends GCompound {
	private static final double APPLICATION_HEIGHT = 600;
	private static final double WIDTH = 400;
	private static final int PADDLE_WIDTH = 90;
	private static final int PADDLE_HEIGHT = 10;
	private static final int PADDLE_Y_OFFSET = 30;
	private GRect paddle;
	public GOval  wheel= new GOval(PADDLE_WIDTH / 10 + 2 * PADDLE_WIDTH / 3,
			APPLICATION_HEIGHT - 2 * PADDLE_Y_OFFSET + PADDLE_HEIGHT,10,10);;

	public trolley() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFillColor(Color.black);
		paddle.setFilled(true);
		add(paddle, 0, APPLICATION_HEIGHT - 2 * PADDLE_Y_OFFSET);
		this.setLocation( (WIDTH - PADDLE_WIDTH) / 2, 0);
		paintWheels();
	}

	private void paintWheels() {
		for (int i = 0; i < 3; i++) {
			double y1 = APPLICATION_HEIGHT - 2 * PADDLE_Y_OFFSET + PADDLE_HEIGHT;
			double x1 = PADDLE_WIDTH / 10 + i * PADDLE_WIDTH / 3;
			double x2 = 10;
			double y2 = 10;
			GOval wheels = new GOval(x1, y1, x2, y2);
			wheels.setColor(Color.black);
			wheels.setFilled(true);
			add(wheels);
		}

	}

}
