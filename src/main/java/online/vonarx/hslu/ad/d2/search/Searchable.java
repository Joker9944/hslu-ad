package online.vonarx.hslu.ad.d2.search;

import java.util.Collection;

public interface Searchable<E> {

	boolean search(final E element);

	void add(final E element);
	void addAll(final Collection<E> elements);

	void remove(final E element);
	@SuppressWarnings("unused")
	void removeAll(final Collection<E> elements);
}
