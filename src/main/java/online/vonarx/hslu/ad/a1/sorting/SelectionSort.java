package online.vonarx.hslu.ad.a1.sorting;

import lombok.experimental.UtilityClass;
import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import online.vonarx.hslu.ad.a1.sorting.util.SampleArrays;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAlgorithm;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAnimationRunner;

public class SelectionSort extends SortingAlgorithm {

	public SelectionSort(final Displayer displayer) {
		super(displayer);
	}

	@Override
	public void sort(int[] ints) {
		for (int i = 0; i < ints.length - 1; i++) {
			int minIndex = i; // position of smallest element

			for (int j = i + 1; j < ints.length; j++) // search for smaller elements after i + 1
				if (ints[j] < ints[minIndex]) minIndex = j;

			swap(ints, i, minIndex); // swap the smallest element to the beginning
			displayer().display(new int[]{i, minIndex});
		}
	}

	@UtilityClass
	public class Sorted {
		public static void main(final String[] args) {
			SortingAnimationRunner.run(SelectionSort::new, SampleArrays.sorted());
		}
	}

	@UtilityClass
	public class Reversed {
		public static void main(final String[] args) {
			SortingAnimationRunner.run(SelectionSort::new, SampleArrays.reversed());
		}
	}

	@UtilityClass
	public class Random {
		public static void main(final String[] args) {
			SortingAnimationRunner.run(SelectionSort::new, SampleArrays.randomDeterministic());
		}
	}
}
