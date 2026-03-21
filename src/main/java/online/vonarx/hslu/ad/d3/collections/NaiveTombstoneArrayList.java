package online.vonarx.hslu.ad.d3.collections;

import lombok.Getter;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
public final class NaiveTombstoneArrayList<E> implements NaiveArrayList<E> {

	@ToString.Include private final Object[] array;
	private final int maxSize;
	@Getter private int size = 0;

	public NaiveTombstoneArrayList() {
		this(NaiveArrayList.DEFAULT_SIZE);
	}

	public NaiveTombstoneArrayList(final int size) {
		maxSize = size;
		// search for the next biggest exponent of 2 to match size
		// consumer is only ever allowed to use 75% of actual size
		// this is done to avoid long lookup chains
		final var exponent = Math.ceil(Math.log(size * 1.25) / Math.log(2));
		this.array = new Object[(int) Math.pow(2, exponent)];
	}

	@Override
	public boolean add(final E element) {
		if (size >= maxSize) return false;
		final var index = searchFreeRight(element);
		if (index >= 0) {
			add(index, element);
			return true;
		} else return false;
	}

	@Override
	public boolean contains(final E element) {
		return searchElementRight(element) >= 0;
	}

	@Override
	public boolean remove(final E element) {
		final var elementIndex = searchElementRight(element);

		// element not found
		if (elementIndex < 0) return false;

		// if right index is occupied bury the element
		if (isOccupied(calcRightIndex(elementIndex))) {
			bury(elementIndex);
			return true;
		}

		// right element is unoccupied, remove the element and cleanup tombstones to the left
		remove(elementIndex);
		for (var index = calcLeftIndex(elementIndex); isTombstone(index); index = calcLeftIndex(index))
			removeTombstone(index);
		return true;
	}

	private int calcIndex(final E element) {
		return Math.abs(element.hashCode() % array.length);
	}

	private void add(final int index, final E element) {
		array[index] = element;
		size++;
	}

	private void remove(final int index) {
		array[index] = null;
		size--;
	}

	private void bury(final int index) {
		array[index] = new Tombstone();
		size--;
	}

	private boolean isTombstone(final int index) {
		return array[index] instanceof Tombstone;
	}

	private void removeTombstone(final int index) {
		array[index] = null;
	}

	private boolean isOccupied(final int index) {
		return array[index] != null;
	}

	private boolean contains(final int index, final E element) {
		return array[index].equals(element);
	}

	private int calcLeftIndex(final int index) {
		if (index == 0) return array.length - 1;
		return index - 1;
	}

	private int calcRightIndex(final int index) {
		if (index + 1 == array.length) return 0;
		return index + 1;
	}

	private int searchFreeRight(final E element) {
		var index = calcIndex(element);
		final var start = index;

		do {
			if (!isOccupied(index)) return index;
			index = calcRightIndex(index);
		} while (index != start);

		// signal list is full
		return -1;
	}

	private int searchElementRight(final E element) {
		var index = calcIndex(element);
		final var end = calcLeftIndex(index);

		while (isOccupied(index) && index != end) {
			if (contains(index, element)) return index;
			index = calcRightIndex(index);
		}

		return -1;
	}

	private static final class Tombstone {

		@Override
		public String toString() {
			return "\uD83D\uDC80";
		}
	}
}
