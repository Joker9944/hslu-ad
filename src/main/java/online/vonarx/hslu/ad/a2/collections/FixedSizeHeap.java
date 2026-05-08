package online.vonarx.hslu.ad.a2.collections;

import java.util.Arrays;
import java.util.Collections;

public class FixedSizeHeap implements IntegerHeap {

	private final int[] heap;
	private int usedSize = 0;

	public FixedSizeHeap(final int size) {
		heap = new int[size];
	}

	private static int parentIndex(final int i) {
		return (i - 1) / 2;
	}

	private static int leftIndex(final int i) {
		return 2 * i + 1;
	}

	private static int rightIndex(final int i) {
		return 2 * i + 2;
	}

	@Override
	public int getMax() {
		return heap[0];
	}

	@Override
	public boolean insert(final int v) {
		if (usedSize >= heap.length) return false;

		// insert at end
		var i = usedSize;
		heap[i] = v;
		usedSize++;

		while (i > 0 && heap[i] > heap[parentIndex(i)]) {
			swap(i, parentIndex(i));
			i = parentIndex(i);
		}

		return true;
	}

	@Override
	public int remove(int i) {
		if (usedSize <= 0) return Integer.MAX_VALUE;
		return 0;
	}

	private void swap(final int a, final int b) {
		final var tmp = heap[a];
		heap[a] = heap[b];
		heap[b] = tmp;
	}
}
