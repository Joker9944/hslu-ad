package online.vonarx.hslu.ad.a2.sorting;

import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import online.vonarx.hslu.ad.a1.sorting.util.SampleArrays;
import online.vonarx.hslu.ad.a2.sorting.Quicksort;
import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Scope.Benchmark;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
@OutputTimeUnit(MILLISECONDS)
@State(Benchmark)
public class QuicksortBenchmark {

	static final Quicksort algo = new Quicksort(Displayer.hide);

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
		/* QuicksortBenchmark.random       100  avgt   25   0.001 ±  0.001  ms/op
		 * QuicksortBenchmark.random    100000  avgt   25   6.270 ±  0.028  ms/op
		 * QuicksortBenchmark.random    200000  avgt   25  13.186 ±  0.089  ms/op
		 * QuicksortBenchmark.random    400000  avgt   25  27.135 ±  0.069  ms/op
		 */
		final var ints = SampleArrays.randomDeterministic(n);
		algo.sort(ints);
		return ints;
	}
}
