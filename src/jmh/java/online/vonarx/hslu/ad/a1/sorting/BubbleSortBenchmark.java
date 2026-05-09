package online.vonarx.hslu.ad.a1.sorting;

import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import online.vonarx.hslu.ad.a1.sorting.util.SampleArrays;
import online.vonarx.hslu.ad.a1.sorting.util.SortingAlgorithm;
import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Scope.Benchmark;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(2)
@OutputTimeUnit(MILLISECONDS)
@State(Benchmark)
public class BubbleSortBenchmark {

	static final SortingAlgorithm algo = new BubbleSort(Displayer.hide);

	@Param({"100", "100000", "200000", "400000"})
	int n;

	@Benchmark
	public int[] sorted() {
		final var ints = SampleArrays.sorted(n);
		algo.sort(ints);
		return ints;
	}

	@Benchmark
	public int[] reversed() {
		final var ints = SampleArrays.reversed(n);
		algo.sort(ints);
		return ints;
	}

	@Benchmark
	public int[] random() {
		final var ints = SampleArrays.randomDeterministic(n);
		algo.sort(ints);
		return ints;
	}
}
