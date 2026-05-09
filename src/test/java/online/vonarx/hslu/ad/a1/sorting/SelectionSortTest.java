package online.vonarx.hslu.ad.a1.sorting;

import lombok.extern.log4j.Log4j2;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAlgorithm;

import java.util.Arrays;

@Log4j2
class SelectionSortTest extends SortingAlgorithmTest {

	@Override
	protected SortingAlgorithm createSortingAlgorithm() {
		return new SelectionSort(indices -> log.debug(Arrays.toString(indices)));
	}
}