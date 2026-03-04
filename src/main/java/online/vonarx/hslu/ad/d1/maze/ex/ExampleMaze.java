package online.vonarx.hslu.ad.d1.maze.ex;

import online.vonarx.hslu.ad.d1.maze.MazeParser;
import online.vonarx.hslu.ad.d1.maze.MazeSolver;
import online.vonarx.hslu.ad.ResourceReader;
import online.vonarx.hslu.ad.d1.maze.model.Point;

import java.io.IOException;

public class ExampleMaze {
	public final static String MAZE = "/d1/maze/exampleMaze.txt";

	public static void main(String[] args) throws Exception {
		doIt(MAZE);
	}

	static void doIt(String maze2) throws IOException {
		final var rawMaze = ResourceReader.readResource(maze2);
		final var maze = MazeParser.parse(rawMaze);
		final var solver = new MazeSolver(maze);
		final var solution = solver.solveRec(new Point(0, 0));
		if (solution.isEmpty()) {
			System.out.println("Could not solve maze.");
		} else {
			System.out.println(solution);
			solution.forEach(maze::setCellVisited);
			System.out.println(maze);
		}
	}
}
