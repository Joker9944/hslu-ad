package online.vonarx.hslu.ad.n2.philosophers;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public record Fork(int n) {

	public static List<Fork> generateForks(final int amount) {
		return IntStream.range(0, amount)
				.mapToObj(Fork::new)
				.toList();
	}

	public static List<ReentrantLock> generateReentrantLocks(final int amount) {
		return IntStream.range(0, amount)
				.mapToObj(value -> new ReentrantLock())
				.toList();
	}

	public static List<ReentrantLock> generateFairReentrantLocks(final int amount) {
		return IntStream.range(0, amount)
				.mapToObj(value -> new ReentrantLock(true))
				.toList();
	}
}
