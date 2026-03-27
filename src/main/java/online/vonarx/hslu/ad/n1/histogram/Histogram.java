package online.vonarx.hslu.ad.n1.histogram;

public interface Histogram {
	int DEFAULT_SIZE = 1000;

	default int[] calcHistogram(final byte[] pixels) throws InterruptedException {
		return calcHistogram(pixels, DEFAULT_SIZE);
	}

	int[] calcHistogram(final byte[] pixels, final int size) throws InterruptedException;
}
