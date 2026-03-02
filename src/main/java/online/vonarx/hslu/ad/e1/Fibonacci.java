package online.vonarx.hslu.ad.e1;

public class Fibonacci {

	public static int fiboRec1(final int n) {
		// n smaller than 0 is illegal
		if (n < 0) throw new IllegalArgumentException(String.format("n must be equal or greater than 0, got %s", n));

		// n equals 0 or 1 are the base cases (Rekursionsbasis). aka where the recursion can be solved.
		if (n == 0) return 0;
		if (n == 1) return 1;

		// n not equals 0 or 1 is where the recursive steps are built (Rekursionsvorschrift)
		return fiboRec1(n - 2) + fiboRec1(n - 1);
	}
}
