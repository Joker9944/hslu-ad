package online.vonarx.hslu.ad.a1.sorting;

import lombok.experimental.UtilityClass;
import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import online.vonarx.hslu.ad.a1.sorting.util.SampleArrays;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAlgorithm;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAnimationRunner;

public class BubbleSort extends SortingAlgorithm {

	public BubbleSort(Displayer displayer) {
		super(displayer);
	}

	@Override
	public void sort(int[] ints) {
		for (int i = 0; i < ints.length - 1; i++) {
			var swapped = false;

			for (int j = 0; j < ints.length - i - 1; j++) {
				if (ints[j] <= ints[j + 1]) continue;
				swap(ints, j , j + 1);
				displayer().display(new int[]{ints.length - i - 1, j, j + 1});
				swapped = true;
			}

			if (!swapped) break;
		}
	}

	@UtilityClass
	public class Sorted {
		public static void main(String[] args) {
			SortingAnimationRunner.run(BubbleSort::new, SampleArrays.sorted());
		}
	}

	@UtilityClass
	public class Reversed {
		public static void main(String[] args) {
			SortingAnimationRunner.run(BubbleSort::new, SampleArrays.reversed());
		}
	}

	@UtilityClass
	public class Random {
		public static void main(String[] args) {
			SortingAnimationRunner.run(BubbleSort::new, SampleArrays.randomDeterministic());
		}
	}
}
