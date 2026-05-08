package online.vonarx.hslu.ad.a1.sorting.util;

import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.stream.IntStream;

@UtilityClass
public class SampleArrays {

	private static final int DEFAULT_LENGTH = 100;
	private static final int SEED = 42;

	public int[] sorted() {
		return sorted(DEFAULT_LENGTH);
	}

	public int[] sorted(final int n) {
		return IntStream.rangeClosed(0, n)
				.toArray();
	}

	public int[] reversed() {
		return reversed(DEFAULT_LENGTH);
	}

	public int[] reversed(final int n) {
		return IntStream.iterate(n, i -> i >= 0, i -> i - 1)
				.toArray();
	}

	public int[] randomDeterministic() {
		return randomDeterministic(DEFAULT_LENGTH);
	}

	public int[] randomDeterministic(final int n) {
		return randomDeterministic(n, SEED);
	}

	public int[] randomDeterministic(final int n, final int seed) {
		return new Random(seed).ints(0, n + 1)
				.limit(n)
				.toArray();
	}
}
