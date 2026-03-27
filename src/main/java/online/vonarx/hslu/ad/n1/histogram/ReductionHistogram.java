package online.vonarx.hslu.ad.n1.histogram;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ReductionHistogram implements Histogram {

	@Override
	public int[] calcHistogram(byte[] pixels, int size) throws InterruptedException {
		final var threadCount = Math.ceilDiv(pixels.length, size);
		try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			final var futures = new ExecutorCompletionService<int[]>(executor);
			IntStream.range(0, threadCount)
					.forEach(threadIndex -> futures.submit(() -> {
						final var chunk = new int[256];
						final var from = threadIndex * size;
						final var to = Math.min((threadIndex + 1) * size, pixels.length);
						for (var i = from; i < to; i++)
							chunk[Byte.toUnsignedInt(pixels[i])]++;
						return chunk;
					}));
			return reduce(threadCount, futures);
		} catch (final ExecutionException e) {
			throw new IllegalStateException("Exception in thread", e);
		}
	}

	static int[] reduce(final int threadCount, final ExecutorCompletionService<int[]> futures) throws InterruptedException, ExecutionException {
		final var histogram = new int[256];
		for (int thread = 0; thread < threadCount; thread++) {
			final var chunk = futures.take().get();
			for (var i = 0; i < histogram.length; i++)
				histogram[i] += chunk[i];
		}
		return histogram;
	}
}
