package online.vonarx.hslu.ad.e1;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
@OutputTimeUnit(NANOSECONDS)
public class TaskBenchmark {

	@Benchmark
	public void taskBenchmark() {
		new Task().task(1000000);
	}

	public static void main(String[] args) throws Exception {
		var options = new OptionsBuilder()
				.include(TaskBenchmark.class.getName())
				.build();

		new Runner(options).run();
	}
}
