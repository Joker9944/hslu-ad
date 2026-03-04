package online.vonarx.hslu.ad.d1.maze.model;

import java.util.List;

public record Point(int x, int y) {
	public Point north() {
		return new Point(x, y + 1);
	}

	public Point east() {
		return new Point(x + 1, y);
	}

	public Point south() {
		return new Point(x, y - 1);
	}

	public Point west() {
		return new Point(x - 1, y);
	}

	public List<Point> directions() {
		return List.of(north(), east(), south(), west());
	}
}
