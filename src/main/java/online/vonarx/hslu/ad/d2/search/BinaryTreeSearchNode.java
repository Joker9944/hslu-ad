package online.vonarx.hslu.ad.d2.search;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

@RequiredArgsConstructor
@EqualsAndHashCode
public final class BinaryTreeSearchNode<E extends Comparable<E>>{

	private BinaryTreeSearchNode<E> left = null;
	private BinaryTreeSearchNode<E> right = null;

	@Getter private final E element;

	public void add(final @NonNull E element) {
		if (larger(element)) addLeft(element);
		else if (smaller(element)) addRight(element);
		else throw new IllegalArgumentException(String.format("Element %s is not unique", element));
	}

	private void addLeft(final E element) {
		if (left == null) left = new BinaryTreeSearchNode<>(element);
		else left.add(element);
	}

	private void addRight(final E element) {
		if (right == null) right = new BinaryTreeSearchNode<>(element);
		else right.add(element);
	}

	public boolean search(final @NonNull E element) {
		if (this.element.equals(element)) return true;

		if (larger(element)) {
			if (left == null) return false;
			return left.search(element);
		}

		if (smaller(element)) {
			if (right == null) return false;
			return right.search(element);
		}

		throw new IllegalStateException(
				String.format("Search element %s and node element %s are not equal but compare equal",
						element, this.element));
	}

	public boolean smaller(final @NonNull E element) {
		return this.element.compareTo(element) < 0;
	}

	public boolean smaller(final @NonNull BinaryTreeSearchNode<E> that) {
		return smaller(that.element);
	}

	public boolean larger(final @NonNull E element) {
		return this.element.compareTo(element) > 0;
	}

	public boolean larger(final @NonNull BinaryTreeSearchNode<E> that) {
		return larger(that.element);
	}

	Optional<E> left() {
		if (left == null)
			return Optional.empty();
		return Optional.of(left.element);
	}

	Optional<E> right() {
		if (right == null)
			return Optional.empty();
		return Optional.of(right.element);
	}
}
