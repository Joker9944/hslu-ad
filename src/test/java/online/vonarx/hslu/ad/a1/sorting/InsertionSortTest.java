package online.vonarx.hslu.ad.a1.sorting;

import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

	static final int[] SORTED = new int[]{1, 2, 3};
	static final int[] REVERSED = new int[]{3, 2, 1};
	static final int[] UNSORTED = new int[]{2, 1, 3};

	@Test
	void sorted() {
		final var ints = Arrays.copyOf(SORTED, SORTED.length);
		new InsertionSort(Displayer.hide).sort(ints);
		assertArrayEquals(SORTED, ints);
	}

	@Test
	void reversed() {
		final var ints = Arrays.copyOf(REVERSED, REVERSED.length);
		new InsertionSort(Displayer.hide).sort(ints);
		assertArrayEquals(SORTED, ints);
	}

	@Test
	void unsored() {
		final var ints = Arrays.copyOf(UNSORTED, UNSORTED.length);
		new InsertionSort(Displayer.hide).sort(ints);
		assertArrayEquals(SORTED, ints);
	}
}
