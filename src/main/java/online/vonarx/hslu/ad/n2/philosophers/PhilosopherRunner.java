package online.vonarx.hslu.ad.n2.philosophers;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class PhilosopherRunner {

	public static <T extends Philosopher<F>, F> void run(final List<String> names, final ForksFactory<F> forksFactory, final PhilosopherFactory<T, F> philosopherFactory)
			throws ExecutionException, InterruptedException {
		final var philosophers = generatePhilosophers(names, forksFactory, philosopherFactory);
		run(philosophers);
	}

	private static <T extends Philosopher<F>, F> List<T> generatePhilosophers(final List<String> names, final ForksFactory<F> forksFactory, final PhilosopherFactory<T, F> factory) {
		final List<F> forks = forksFactory.create(names.size());
		return IntStream.range(0, names.size())
				.mapToObj(i -> factory.create(names.get(i), forks.get(i), forks.get((i + 1) % forks.size())))
				.toList();
	}

	private static void run(List<? extends Philosopher<?>> philosophers)
			throws ExecutionException, InterruptedException {
		try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			final var futures = philosophers.stream()
					.map(philosopher -> executor.submit(() -> {
						while (!philosopher.finishedEating()) {
							try {
								philosopher.think();
								philosopher.eat();
							} catch (final InterruptedException e) {
								throw new RuntimeException("Exception during thread", e);
							}
						}
					}))
					.toList();
			for (final var future : futures) future.get();
		}
	}

	@FunctionalInterface
	public interface PhilosopherFactory<T extends Philosopher<F>, F> {
		T create(final String name, final F left, final F right);
	}

	@FunctionalInterface
	public interface ForksFactory<F> {
		List<F> create(final int amount);
	}
}
