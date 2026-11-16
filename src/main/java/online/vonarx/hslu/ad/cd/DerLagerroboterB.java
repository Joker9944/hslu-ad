package online.vonarx.hslu.ad.cd;

import static online.vonarx.hslu.ad.a1.sorting.util.SortingAlgorithm.swap;

public class DerLagerroboterB {

	static void main() {
		final var boxes = DerLagerroboterA.getInput();

		var countSwaps = 0;
		var i = 1;
		while (i < boxes.length) {
			if (i == 0 || boxes[i] > boxes[i - 1]) {
				i++;
			} else if (boxes[i] < boxes[i - 1]) {
				swap(boxes, i , i - 1);
				i--;
				countSwaps++;
			} else {
				throw new IllegalArgumentException("2 boxes with the same size found");
			}
		}

		System.out.println(countSwaps);
	}
}
