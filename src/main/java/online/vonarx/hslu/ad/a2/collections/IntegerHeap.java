package online.vonarx.hslu.ad.a2.collections;

public interface IntegerHeap {
	int getMax();
	boolean insert(final int v);
	int remove(final int i);
}
