package online.vonarx.hslu.ad.n1.histogram;

import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Scope.Benchmark;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
@OutputTimeUnit(MICROSECONDS)
@State(Benchmark)
public class HistogramCoreSizeBenchmark {

	static final Histogram reduction = new ReductionHistogramCoreSize();

	@Param({"2", "4", "6", "8", "12", "16", "24", "32"})
	int threadCount;

	@Benchmark
	public int[] reductionCoreSize() throws InterruptedException {
		/* Benchmark                                     (threadCount)  Mode  Cnt      Score     Error  Units
		 * HistogramCoreSizeBenchmark.reductionCoreSize              2  avgt   25  28356.550 ± 246.249  us/op
		 * HistogramCoreSizeBenchmark.reductionCoreSize              4  avgt   25  15947.988 ± 209.800  us/op
		 * HistogramCoreSizeBenchmark.reductionCoreSize              6  avgt   25  12091.740 ± 182.661  us/op
		 * HistogramCoreSizeBenchmark.reductionCoreSize              8  avgt   25  10375.875 ± 253.853  us/op
		 * HistogramCoreSizeBenchmark.reductionCoreSize             12  avgt   25   8125.909 ±  94.747  us/op
		 * HistogramCoreSizeBenchmark.reductionCoreSize             16  avgt   25   6517.423 ± 104.747  us/op
		 * HistogramCoreSizeBenchmark.reductionCoreSize             24  avgt   25   5552.411 ± 116.184  us/op
		 * HistogramCoreSizeBenchmark.reductionCoreSize             32  avgt   25   5445.738 ± 131.914  us/op
		 */
		return reduction.calcHistogram(HistogramBenchmark.pixels, threadCount);
	}
}
