package online.vonarx.hslu.ad.a1.sorting;

import online.vonarx.hslu.ad.a1.sorting.util.Displayer;
import online.vonarx.hslu.ad.a1.sorting.util.SampleArrays;
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
public class InsertionSortBenchmark {

	static final InsertionSort algo = new InsertionSort(Displayer.hide);

	@Param({"100", "100000", "200000", "400000"})
	int n;

	@Benchmark
	public int[] sorted() {
		/* InsertionSortBenchmark.sorted       100  avgt   25     ≈ 10⁻⁴            ms/op
		 * InsertionSortBenchmark.sorted    100000  avgt   25      0.052 ±   0.001  ms/op
		 * InsertionSortBenchmark.sorted    200000  avgt   25      0.172 ±   0.001  ms/op
		 * InsertionSortBenchmark.sorted    400000  avgt   25      0.329 ±   0.003  ms/op
		 */
		final var ints = SampleArrays.sorted(n);
		algo.sort(ints);
		return ints;
	}

	@Benchmark
	public int[] reversed() {
		/* InsertionSortBenchmark.reversed     100  avgt   25      0.001 ±   0.001  ms/op
		 * InsertionSortBenchmark.reversed  100000  avgt   25    888.834 ±   0.730  ms/op
		 * InsertionSortBenchmark.reversed  200000  avgt   25   3589.785 ±  20.768  ms/op
		 * InsertionSortBenchmark.reversed  400000  avgt   25  14501.835 ± 104.398  ms/op
		 */
		final var ints = SampleArrays.reversed(n);
		algo.sort(ints);
		return ints;
	}

	@Benchmark
	public int[] random() {
		/* InsertionSortBenchmark.random       100  avgt   25      0.001 ±   0.001  ms/op
		 * InsertionSortBenchmark.random    100000  avgt   25    464.007 ±  11.491  ms/op
		 * InsertionSortBenchmark.random    200000  avgt   25   1875.683 ±  14.817  ms/op
		 * InsertionSortBenchmark.random    400000  avgt   25   7447.283 ±  64.130  ms/op
		 */
		final var ints = SampleArrays.randomDeterministic(n);
		algo.sort(ints);
		return ints;
	}
}
