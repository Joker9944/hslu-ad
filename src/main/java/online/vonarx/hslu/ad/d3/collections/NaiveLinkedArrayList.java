package online.vonarx.hslu.ad.d3.collections;

import lombok.Getter;
import online.vonarx.hslu.ad.d1.collections.NaiveLinkedStack;

@SuppressWarnings({"rawtypes", "unchecked"})
public class NaiveLinkedArrayList<E> implements NaiveArrayList<E> {

	private final NaiveLinkedStack[] list;
	private final int maxSize;
	@Getter private int size = 0;

	public NaiveLinkedArrayList() {
		this(NaiveArrayList.DEFAULT_SIZE);
	}

	public NaiveLinkedArrayList(final int size) {
		maxSize = size;
		this.list = new NaiveLinkedStack[size];
	}

	@Override
	public boolean add(E element) {
		if (size >= maxSize) return false;
		add(calcIndex(element), element);
		return true;
	}

	@Override
	public boolean contains(E element) {
		return contains(calcIndex(element), element);
	}

	@Override
	public boolean remove(E element) {
		return remove(calcIndex(element), element);
	}

	private void add(final int index, final E element) {
		if (list[index] == null) list[index] = new NaiveLinkedStack<>();
		list[index].push(element);
		size++;
	}

	private boolean contains(final int index, final E element) {
		if (list[index] == null) return false;
		return list[index].contains(element);
	}

	private boolean remove(final int index, final E element) {
		if (list[index] == null) return false;
		final var success = list[index].remove(element);
		if (success) size--;
		return success;
	}

	private int calcIndex(final E element) {
		return Math.abs(element.hashCode() % list.length);
	}
}
