package online.vonarx.hslu.ad.a1.sorting;

import lombok.experimental.UtilityClass;
import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import online.vonarx.hslu.ad.a1.sorting.util.SampleArrays;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAlgorithm;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAnimationRunner;

public class InsertionSort extends SortingAlgorithm {


	public InsertionSort(final Displayer displayer) {
		super(displayer);
	}

	@Override
	public void sort(int[] ints) {
		for (int i = 1; i < ints.length; i++) {
			int elem = ints[i];
			int j = i;

			while (j > 0 && ints[j - 1] > elem) {
				ints[j] = ints[j - 1];
				displayer().display(new int[]{j, j-1});
				j--;
			}

			ints[j] = elem;
			displayer().display(new int[]{j, i});
		}
	}

	@UtilityClass
	public class Sorted {
		public static void main(String[] args) {
			SortingAnimationRunner.run(InsertionSort::new, SampleArrays.sorted());
		}
	}

	@UtilityClass
	public class Reversed {
		public static void main(String[] args) {
			SortingAnimationRunner.run(InsertionSort::new, SampleArrays.reversed());
		}
	}

	@UtilityClass
	public class Random {
		public static void main(String[] args) {
			SortingAnimationRunner.run(InsertionSort::new, SampleArrays.randomDeterministic());
		}
	}
}
