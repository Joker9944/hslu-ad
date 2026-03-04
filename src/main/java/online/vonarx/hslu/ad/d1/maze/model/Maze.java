package online.vonarx.hslu.ad.d1.maze.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Maze {

	private final Cell[][] maze;

	public static Builder builder(final int sizeX, final int sizeY) {
		return new Builder(sizeX, sizeY);
	}

	public Cell cellAt(final int x, final int y) {
		return maze[y][x];
	}

	public Cell cellAt(final @NonNull Point point) {
		return cellAt(point.x(), point.y());
	}

	public boolean isTarget(final @NonNull Point point) {
		return cellAt(point).equals(Cell.TARGET);
	}

	public boolean isWall(final @NonNull Point point) {
		return cellAt(point).equals(Cell.WALL);
	}

	public boolean isOutOfBounds(final @NonNull Point point) {
		return point.x() < 0 || point.y() < 0 || point.x() >= maze[0].length || point.y() >= maze.length;
	}

	public boolean isTraversable(final @NonNull Point point) {
		return !isOutOfBounds(point) && !isWall(point);
	}

	public void setCellVisited(final @NonNull Point point) {
		maze[point.y()][point.x()] = Cell.VISITED;
	}

	@Override
	public String toString() {
		return Arrays.stream(maze)
				.map(row -> Arrays.stream(row)
						.map(Cell::character)
						.map(Object::toString)
						.collect(joining()))
				.collect(joining("\n"));
	}

	@RequiredArgsConstructor
	public enum Cell {
		EMPTY(' '), WALL('#'), TARGET('X'), VISITED('.');

		@Getter private final char character;

		public static Cell fromCharacter(final char c) {
			return Arrays.stream(values())
					.filter(cell -> cell.character == c)
					.findFirst()
					.orElseThrow(() -> new RuntimeException("No cell for given character exists"));
		}
	}

	public static class Builder {

		private final Cell[][] maze;

		private Builder(final int sizeX, final int sizeY) {
			this.maze = new Cell[sizeY][sizeX];

			for (final var row : maze)
				Arrays.fill(row, Cell.EMPTY);
		}

		@SuppressWarnings("UnusedReturnValue")
		public Builder cellAt(final int x, final int y, final Cell cell) {
			maze[y][x] = cell;
			return this;
		}

		public Maze build() {
			return new Maze(maze);
		}
	}
}
