package online.vonarx.hslu.ad.n2.philosophers;

import online.vonarx.hslu.ad.n2.philosophers.impl.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Fork;

import java.util.concurrent.ExecutionException;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static online.vonarx.hslu.ad.n2.philosophers.Philosopher.PHILOSOPHER_NAMES;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Scope.Benchmark;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
@OutputTimeUnit(MICROSECONDS)
@State(Benchmark)
public class PhilosopherBenchmark {

	@Benchmark
	public void deadlock() throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, online.vonarx.hslu.ad.n2.philosophers.Fork::generateForks, DeadlockPhilosopher::new);
	}

	@Benchmark
	public void fair() throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, online.vonarx.hslu.ad.n2.philosophers.Fork::generateFairReentrantLocks, FairPhilosopher::new);
	}

	@Benchmark
	public void globalLock() throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, online.vonarx.hslu.ad.n2.philosophers.Fork::generateForks, GlobalLockPhilosopher::new);
	}

	@Benchmark
	public void order() throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, online.vonarx.hslu.ad.n2.philosophers.Fork::generateForks, OrderPhilosopher::new);
	}

	@Benchmark
	public void timeout() throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, online.vonarx.hslu.ad.n2.philosophers.Fork::generateReentrantLocks, TimeoutPhilosopher::new);
	}
}
