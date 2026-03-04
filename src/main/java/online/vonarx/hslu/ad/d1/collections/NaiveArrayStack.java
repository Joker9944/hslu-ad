package online.vonarx.hslu.ad.d1.collections;

import java.util.EmptyStackException;

public class NaiveArrayStack<E> implements NaiveStack<E> {

	private final Object[] array;
	private int nextEmptyCell = 0;

	public NaiveArrayStack(final int size) {
		array = new Object[size];
	}

	public NaiveArrayStack() {
		this(20);
	}

	@Override
	public void push(E e) {
		if (full())
			throw new FullStackException();

		array[nextEmptyCell] = e;
		nextEmptyCell++;
	}

	@Override
	public E pop() {
		if (nextEmptyCell == 0)
			throw new EmptyStackException();

		nextEmptyCell--;
		//noinspection unchecked
		return (E) array[nextEmptyCell];
	}

	@Override
	public boolean full() {
		return nextEmptyCell == array.length;
	}
}
