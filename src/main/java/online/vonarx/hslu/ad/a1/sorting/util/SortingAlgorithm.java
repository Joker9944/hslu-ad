package online.vonarx.hslu.ad.a1.sorting.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SortingAlgorithm {

	@Getter private final Displayer displayer;

	public abstract void sort(final int[] ints);
}
