package online.vonarx.hslu.ad.a1.sorting;

import online.vonarx.hslu.ad.a1.sorting.util.SampleArrays;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

abstract class SortingAlgorithmTest {

	private static int[] knownGoodSort(final int[] unsorted) {
		final var sorted = Arrays.copyOf(unsorted, unsorted.length);
		Arrays.sort(sorted);
		return sorted;
	}

	protected abstract SortingAlgorithm createSortingAlgorithm();

	@Test
	void sorted() {
		final var ints = SampleArrays.sorted(10);
		createSortingAlgorithm().sort(ints);
		assertArrayEquals(SampleArrays.sorted(10), ints);
	}

	@Test
	void reversed() {
		final var ints = SampleArrays.reversed(10);
		createSortingAlgorithm().sort(ints);
		assertArrayEquals(knownGoodSort(ints), ints);
	}

	@Test
	void unsorted() {
		final var ints = SampleArrays.randomDeterministic(10);
		createSortingAlgorithm().sort(ints);
		assertArrayEquals(knownGoodSort(ints), ints);
	}
}
