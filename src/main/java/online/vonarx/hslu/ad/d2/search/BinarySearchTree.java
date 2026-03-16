package online.vonarx.hslu.ad.d2.search;

import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

import java.util.Collection;

@NoArgsConstructor
public class BinarySearchTree<E extends Comparable<E>> implements Searchable<E> {

	private BinaryTreeSearchNode<E> root;

	@SuppressWarnings("unused")
	public BinarySearchTree(final @NonNull E element) {
		add(element);
	}

	public BinarySearchTree(final @NonNull Collection<E> elements) {
		addAll(elements);
	}

	@Override
	public boolean search(final @NonNull E element) {
		if (root == null) return false;
		return root.search(element);
	}

	@Override
	public void add(final @NonNull E element) {
		if (root == null) root = new BinaryTreeSearchNode<>(element);
		else root.add(element);
	}

	@Override
	public void addAll(final @NonNull Collection<E> elements) {
		elements.forEach(this::add);
	}

	@Override
	public void remove(final @NonNull E element) {
		throw new UnsupportedOperationException("remove");
	}

	@Override
	public void removeAll(final @NonNull Collection<E> elements) {
		elements.forEach(this::remove);
	}
}
