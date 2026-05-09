package online.vonarx.hslu.ad.a1.sorting.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SortingAlgorithm {

	@Getter private final Displayer displayer;

	public abstract void sort(final int[] ints);

	public static void swap(final int[] ints, final int i, final int j) {
		final var tmp = ints[i];
		ints[i] = ints[j];
		ints[j] = tmp;
	}
}
