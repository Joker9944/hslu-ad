package online.vonarx.hslu.ad.n1.histogram;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static online.vonarx.hslu.ad.n1.histogram.ReductionHistogram.reduce;

public class ReductionHistogramCoreSize implements Histogram {

	@Override
	public int[] calcHistogram(byte[] pixels) throws InterruptedException {
		return calcHistogram(pixels, 2);
	}

	@Override
	public int[] calcHistogram(byte[] pixels, int threadCount) throws InterruptedException {
		final var chunkSize = pixels.length / threadCount;
		try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			final var futures = new ExecutorCompletionService<int[]>(executor);
			IntStream.range(0, threadCount)
					.forEach(threadIndex -> futures.submit(() -> {
						final var slice = new int[256];
						final var from = threadIndex * chunkSize;
						final var to = (threadIndex == threadCount - 1) ? pixels.length : (threadIndex + 1) * chunkSize;
						for (var i = from; i < to; i++)
							slice[Byte.toUnsignedInt(pixels[i])]++;
						return slice;
					}));
			return reduce(threadCount, futures);
		} catch (final ExecutionException e) {
			throw new IllegalStateException("Exception in thread", e);
		}
	}
}
