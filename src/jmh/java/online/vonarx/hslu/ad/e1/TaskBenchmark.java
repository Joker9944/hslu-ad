package online.vonarx.hslu.ad.e1;

import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
@OutputTimeUnit(NANOSECONDS)
public class TaskBenchmark {

	@Benchmark
	public void benchmark() {
		new Task().task(1000000);
	}
}
