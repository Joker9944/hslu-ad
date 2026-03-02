package online.vonarx.hslu.ad.e1;

public class FibonacciOptimized {

	// will crash when n > 19
	private final static int[] cache = new int[5000];

	public static int fiboRec2(final int n) {
		// n smaller than 0 is illegal
		if (n < 0) throw new IllegalArgumentException(String.format("n must be equal or greater than 0, got %s", n));

		// n equals 0 or 1 are the base cases (Rekursionsbasis). aka where the recursion can be solved.
		if (n == 0) return 0;
		if (n == 1) return 1;

		return calcIfAbsent(n);
	}

	private static int calcIfAbsent(final int n) {
		// check if we have the element in cache
		if (cache[n] != 0) return cache[n];

		// if not calc it and cache
		// n not equals 0 or 1 is where we build the recursive step (Rekursionsvorschrift)
		final var element = fiboRec2(n - 2) + fiboRec2(n - 1);
		cache[n] = element;
		return element;
	}
}
