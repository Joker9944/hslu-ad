package online.vonarx.hslu.ad.d1.maze;

import online.vonarx.hslu.ad.d1.maze.model.Maze;
import online.vonarx.hslu.ad.d1.maze.model.Point;

import java.util.LinkedList;
import java.util.List;

import static online.vonarx.hslu.ad.d1.maze.MazeParser.parse;

public class MazeSolver {

	private final Maze maze;

	private List<Point> solution;

	@SuppressWarnings("unused")
	public MazeSolver(final String maze) {
		this(parse(maze));
	}

	public MazeSolver(final Maze maze) {
		this.maze = maze;
	}

	public List<Point> solveRec(final Point start) {
		if (!maze.isTraversable(start))
			throw new IllegalArgumentException(String.format("Given starting point %s is not valid", start));

		solution = new LinkedList<>();
		solveRecInternal(new Point(-1, -1), start);

		return List.copyOf(solution);
	}

	private boolean solveRecInternal(final Point origin, final Point point) {
		if (maze.isTarget(point)) {
			solution.addFirst(point);
			return true;
		}

		for (final var direction : point.directions())
			if (!direction.equals(origin) && maze.isTraversable(direction))
				if (solveRecInternal(point, direction)) {
					solution.addFirst(point);
					return true;
				}

		return false;
	}
}