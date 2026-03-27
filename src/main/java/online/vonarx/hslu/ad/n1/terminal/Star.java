package online.vonarx.hslu.ad.n1.terminal;

import lombok.Getter;
import online.vonarx.hslu.ad.d1.maze.model.Point;

import static java.lang.Thread.sleep;
import static online.vonarx.hslu.ad.d1.maze.model.Point.point;

public class Star {
	public static final int X_DEFAULT = 1;
	public static final int X_MAX = 80;
	public static final int COLOR_DEFAULT = 0;

	private final int delay;
	@Getter private Point previous = null;
	@Getter private Point current;
	@Getter private int color;

	public Star(final int delay, final int y) {
		this(delay, y, COLOR_DEFAULT);
	}

	public Star(final int delay, final int y, final int startingColor) {
		this(delay, X_DEFAULT, y, startingColor);
	}

	public Star(final int delay, final int x, final int y, final int startingColor) {
		this(delay, point(x, y), startingColor);
	}

	private Star(final int delay, final Point current, final int startingColor) {
		this.delay = delay;
		this.current = current;
		this.color = startingColor;
	}

	public void move() throws InterruptedException {
		if (current.x() >= X_MAX)
			cyclePoints(current.setX(X_DEFAULT));
		else cyclePoints(current.east());
		incrementColor();
		sleep(delay);
	}

	private void cyclePoints(final Point next) {
		previous = current;
		current = next;
	}

	private void incrementColor() {
		color = (color + 1) % 256;
	}
}
