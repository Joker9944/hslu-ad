package online.vonarx.hslu.ad.a2.sorting;

import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuicksortTest {

	static final int[] SORTED = new int[]{1, 2, 3, 4, 5};
	static final int[] REVERSED = new int[]{5, 4, 3, 2, 1};
	static final int[] UNSORTED = new int[]{12, 10, 52, 9, 77, 13, 18, 52, 11, 25, 8, 5, 17};

	@Test
	void sorted() {
		final var ints = Arrays.copyOf(SORTED, SORTED.length);
		new Quicksort(Displayer.hide).sort(ints);
		assertArrayEquals(SORTED, ints);
	}

	@Test
	void reversed() {
		final var ints = Arrays.copyOf(REVERSED, REVERSED.length);
		new Quicksort(Displayer.hide).sort(ints);
		assertArrayEquals(REVERSED, ints);
	}

	@Test
	void unsorted() {
		final var ints = Arrays.copyOf(UNSORTED, UNSORTED.length);
		final int[] expected = Arrays.copyOf(UNSORTED, UNSORTED.length);
		Arrays.sort(expected);
		new Quicksort(Displayer.hide).sort(ints);
		assertArrayEquals(expected, ints);
	}
}
