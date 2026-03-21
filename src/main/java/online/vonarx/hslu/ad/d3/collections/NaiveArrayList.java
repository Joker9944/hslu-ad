package online.vonarx.hslu.ad.d3.collections;

public interface NaiveArrayList<E> {

	int DEFAULT_SIZE = 20;

	boolean add(final E element);

	boolean contains(final E element);

	boolean remove(final E element);

	int size();
}
