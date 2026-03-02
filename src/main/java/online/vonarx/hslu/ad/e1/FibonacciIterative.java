package online.vonarx.hslu.ad.e1;

public class FibonacciIterative {

	// will crash when n > 19
	private final static int[] cache = new int[20];

	static {
		cache[1] = 1;
	}

	public static int fiboIter(final int n) {
		// n smaller than 0 is illegal
		if (n < 0) throw new IllegalArgumentException(String.format("n must be equal or greater than 0, got %s", n));

		if (n <= 1) return cache[n];

		for (int i = 2; i <= n; i++) {
			cache[i] = cache[i - 1] + cache[i - 2];
		}

		return cache[n];
	}
}
