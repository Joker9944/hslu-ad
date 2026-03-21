package online.vonarx.hslu.ad.d3.collections;

import org.openjdk.jmh.annotations.*;

import java.util.List;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
@OutputTimeUnit(NANOSECONDS)
public class NaiveArrayListBenchmark {

	static final List<String> numbersFull = List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
	static final List<String> numbersHalf = List.of("0", "1", "2", "3", "4");

	@Benchmark
	public void tombstone() {
		/* Benchmark                          Mode  Cnt    Score   Error  Units
		 * NaiveArrayListBenchmark.tombstone  avgt   25  290.092 ± 4.383  ns/op
		 */
		doIt(new NaiveTombstoneArrayList<>(10));
	}

	@Benchmark
	public void linked() {
		/* Benchmark                       Mode  Cnt    Score   Error  Units
		 * NaiveArrayListBenchmark.linked  avgt   25  181.160 ± 6.285  ns/op
		 */
		doIt(new NaiveLinkedArrayList<>(10));
	}

	public void doIt(final NaiveArrayList<String> list) {
		numbersFull.forEach(list::add);
		numbersFull.forEach(list::contains);
		numbersFull.forEach(list::remove);
		numbersHalf.forEach(list::add);
		numbersHalf.forEach(list::add);
		numbersHalf.forEach(list::contains);
		numbersHalf.forEach(list::remove);
		numbersHalf.forEach(list::remove);
	}
}
