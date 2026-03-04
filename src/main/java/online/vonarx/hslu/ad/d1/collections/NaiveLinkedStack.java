package online.vonarx.hslu.ad.d1.collections;

import lombok.Data;
import lombok.Getter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NaiveLinkedStack<E> {

	private Node<E> head;

	@Getter private int size = 0;

	public NaiveLinkedStack<E> push(final E element) {
		if (head == null) {
			head = new Node<>(element);
		} else {
			head = new Node<>(element, head);
		}
		size++;
		return this;
	}

	public boolean contains(final E element) {
		var current = head;

		while (current != null) {
			if (current.element().equals(element)) return true;
			current = current.next();
		}

		return false;
	}

	public E pop() {
		if (head == null) return null;

		final var current = head;
		head = current.next();
		size--;
		return current.element();
	}

	public boolean remove(final E element) {
		Node<E> previous = null;
		var current = head;

		while (current != null) {
			if (!current.element().equals(element)) {
				// not a match
				previous = current;
				current = current.next();
				continue;
			}

			// found the element

			if (previous == null) {
				// the element is the list head
				head = head.next();
				size--;
				return true;
			}

			if (current.next() == null) {
				// the element is the list tail
				previous.next(null);
				size--;
				return true;
			}

			// the element is in the middle of the list
			previous.next(current.next());
			size--;
			return true;
		}

		// did not find a matching element

		return false;
	}

	public Iterator<E> iterator() {
		return new Iterator<>() {
			private Node<E> next = head;

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public E next() {
				if (!hasNext()) throw new NoSuchElementException("No more elements in this list");

				final var current = next;
				next = next.next();
				return current.element();
			}
		};
	}

	@Data
	private static class Node<E> {
		private Node<E> next;
		private final E element;

		public Node(final E element) {
			this.element = element;
		}

		public Node(final E element, final Node<E> next) {
			this(element);
			this.next = next;
		}
	}
}
