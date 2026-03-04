package online.vonarx.hslu.ad.d1.collections;

import java.util.EmptyStackException;

/**
 * Naive implementation of a {@code java.util.Stack} for educational purposes.
 * Stacks are a FILO (Last In First Out) data structure.
 *
 * @param <E> Type of component elements
 */
public interface NaiveStack<E> {

	/**
	 * Push an element onto the top of this stack.
	 *
	 * @param e element to be pushed on top of the stack.
	 * @throws FullStackException if this stack is already full.
	 */
	void push(E e);

	/**
	 * Removes the top element of this stack and returns it.
	 *
	 * @return the top element of this stack.
	 * @throws EmptyStackException if this stack is empty.
	 */
	E pop();

	/**
	 * Returns if this stack is full.
	 *
	 * @return {@code true} if this stack is full; {@code false} otherwise.
	 */
	boolean full();
}
