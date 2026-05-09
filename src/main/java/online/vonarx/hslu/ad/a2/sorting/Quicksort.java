package online.vonarx.hslu.ad.a2.sorting;

import lombok.experimental.UtilityClass;
import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import online.vonarx.hslu.ad.a1.sorting.util.SampleArrays;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAlgorithm;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAnimationRunner;

public class Quicksort extends SortingAlgorithm {

	public Quicksort(Displayer displayer) {
		super(displayer);
	}

	public void sort(final int[] ints) {
		quicksort(ints, 0, ints.length - 1);
	}

	private void quicksort(final int[] ints, final int start, final int end) {
		if (start >= end) return;

		final var pivot = partition(ints, start, end);
		quicksort(ints, start, pivot);
		quicksort(ints, pivot + 1, end);
	}

	private int partition(final int[] ints, final int start, final int end) {
		final var pivot = ints[start];
		var i = start - 1;
		var j = end + 1;

		while (true) {
			do i++; while (ints[i] < pivot);
			do j--; while (ints[j] > pivot);

			if (i >= j)
				return j;

			swap(ints, i, j);
			displayer().display(new int[]{i, j, start, end});
		}
	}

	@UtilityClass
	public class Sorted {
		public static void main(final String[] args) {
			SortingAnimationRunner.run(Quicksort::new, SampleArrays.sorted());
		}
	}

	@UtilityClass
	public class Reversed {
		public static void main(final String[] args) {
			SortingAnimationRunner.run(Quicksort::new, SampleArrays.reversed());
		}
	}

	@UtilityClass
	public class Random {
		public static void main(final String[] args) {
			SortingAnimationRunner.run(Quicksort::new, SampleArrays.randomDeterministic());
		}
	}

	@UtilityClass
	public class Exercise {
		public static void main(final String[] args) {
			SortingAnimationRunner.run(Quicksort::new, new int[]{12, 10, 52, 9, 77, 13, 18, 52, 11, 25, 8, 5, 17});
		}
	}
}
