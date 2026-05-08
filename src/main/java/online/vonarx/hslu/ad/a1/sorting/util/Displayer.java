package online.vonarx.hslu.ad.a1.sorting.util;

@FunctionalInterface
public interface Displayer {
	Displayer hide = (indices -> {});

	void display(final int[] indices);
}
