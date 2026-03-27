package online.vonarx.hslu.ad.n1.terminal;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public final class StarryNight {
	public static final int RANDOM_BOUND = 51; // random number 0 to 50
	public static final int RANDOM_OFFSET = 50;

	private static final Random random = new Random();

	public static void main(String[] args) throws Exception {
		hideCursor();
		clear();

		final var threads = IntStream.range(1, 21)
				.mapToObj(StarryNight::startThread)
				.toList();

		setupCleanup(threads);

		// basically a while true
		for (var thread : threads) thread.join();
	}

	private static Thread startThread(final int i) {
		return Thread.startVirtualThread(() -> {
			var stop = false;
			final var star = new Star(random.nextInt(RANDOM_BOUND) + RANDOM_OFFSET, i);
			printStar(star);

			while (!stop) {
				try {
					star.move();
					clearStar(star);
					printStar(star);
				} catch (final InterruptedException e) {
					stop = true;
				}
			}
		});
	}

	private static void setupCleanup(final List<Thread> threads) {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			threads.forEach(Thread::interrupt);
			resetColor();
			clear();
			showCursor();
		}));
	}

	private static void hideCursor() {
		System.out.print("\033[?25l");
	}

	private static void showCursor() {
		System.out.print("\033[?25h");
	}

	private static void clear() {
		System.out.print("\033[2J\033[H");
	}

	private static void printStar(final Star star) {
		setColor(star.color());
		System.out.print("\033[" + star.current().y() + ";" + star.current().x() + "H*");
	}

	private static void clearStar(final Star star) {
		System.out.print("\033[" + star.previous().y() + ";" + star.previous().x() + "H ");
	}

	private static void setColor(final int color) {
		System.out.printf("\033[38;5;%dm", color);
	}

	private static void resetColor() {
		System.out.print("\033[0m");
	}
}
