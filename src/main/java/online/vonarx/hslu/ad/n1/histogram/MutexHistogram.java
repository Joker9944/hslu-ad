package online.vonarx.hslu.ad.n1.histogram;

import java.util.stream.IntStream;

public class MutexHistogram implements Histogram {

	@Override
	public int[] calcHistogram(final byte[] pixels, final int size) throws InterruptedException {
		final var histogram = new int[256];
		final var threads = IntStream.range(0, Math.ceilDiv(pixels.length, size))
				.mapToObj(threadIndex -> {
					final var from = threadIndex * size;
					final var to = Math.min((threadIndex + 1) * size, pixels.length);
					return Thread.startVirtualThread(() -> {
						for (var i = from; i < to; i++) {
							synchronized (histogram) {
								histogram[Byte.toUnsignedInt(pixels[i])]++;
							}
						}
					});
				})
				.toList();
		for (var thread : threads) thread.join();
		return histogram;
	}
}
