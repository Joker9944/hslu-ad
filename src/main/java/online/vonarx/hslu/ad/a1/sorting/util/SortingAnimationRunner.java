package online.vonarx.hslu.ad.a1.sorting.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SortingAnimationRunner {

	public <T extends SortingAlgorithm> void run(final StepSortingAlgorithmFactory<T> factory, final int[] ints) {
		final var animation = SortingAnimation.instance();
		final var algorithm = factory.create((final int[] indices) -> animation.showArray(ints, 50, indices));
		animation.showArray(ints, 500);
		algorithm.sort(ints);
		animation.showArray(ints, 500);
	}

	@FunctionalInterface
	public interface StepSortingAlgorithmFactory<T extends SortingAlgorithm> {
		T create(final Displayer displayer);
	}
}
