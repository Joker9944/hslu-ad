package online.vonarx.hslu.ad.d1.maze;

import online.vonarx.hslu.ad.d1.maze.model.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeParserTest {

	@Test
	public void findLogestRow() {
		final var rows = new String[3];
		rows[0] = "###";
		rows[1] = "######";
		rows[2] = "##";
		assertEquals(6, MazeParser.findLogestRow(rows));
	}

	@Test
	public void parse() {
		final var rawMaze = """
				 #
				#X#
				""";
		final var maze = MazeParser.parse(rawMaze);
		assertEquals(Maze.Cell.EMPTY, maze.cellAt(0, 0));
		assertEquals(Maze.Cell.WALL, maze.cellAt(1, 0));
		assertEquals(Maze.Cell.EMPTY, maze.cellAt(2, 0));
		assertEquals(Maze.Cell.WALL, maze.cellAt(0, 1));
		assertEquals(Maze.Cell.TARGET, maze.cellAt(1, 1));
		assertEquals(Maze.Cell.WALL, maze.cellAt(2, 1));
	}
}
