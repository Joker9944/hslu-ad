package online.vonarx.hslu.ad.d1.maze;

import online.vonarx.hslu.ad.d1.maze.model.Maze;

import java.util.Arrays;

import static java.util.Comparator.naturalOrder;

public class MazeParser {

	public static Maze parse(final String rawMaze) {
		final var rows = rawMaze.split("\n");
		final var longestRowLength = findLogestRow(rows);

		final var builder = Maze.builder(longestRowLength, rows.length);

		for (var y = 0; y < rows.length; y++) {
			final var rawParts = rows[y].toCharArray();

			for (var x = 0; x < rawParts.length; x++) {
				final var rawPart = rawParts[x];
				if (rawPart == Maze.Cell.EMPTY.character()) continue;

				builder.cellAt(x, y, Maze.Cell.fromCharacter(rawPart));
			}
		}

		return builder.build();
	}

	protected static int findLogestRow(final String[] rows) {
		return Arrays.stream(rows)
				.map(String::length)
				.max(naturalOrder())
				.orElseThrow(() -> new RuntimeException("Empty rows supplied"));
	}
}
