package online.vonarx.hslu.ad.n1.histogram;

import online.vonarx.hslu.ad.ResourceReader;
import org.openjdk.jmh.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Scope.Benchmark;

@BenchmarkMode(AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
@OutputTimeUnit(MICROSECONDS)
@State(Benchmark)
public class HistogramBenchmark {

	static final byte[] pixels;

	static final Histogram sequential = new SequentialHistogram();
	static final Histogram mutex = new MutexHistogram();
	static final Histogram reduction = new ReductionHistogramCoreSize();

	static {
		try (final var is = ResourceReader.readResource("/n1/bridge.jpg")) {
			pixels = ((DataBufferByte) ImageIO.read(is).getRaster().getDataBuffer()).getData();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Param({"1000", "4000", "16000", "64000"})
	int chunkSize;

	@Benchmark
	public int[] sequential() throws InterruptedException {
		/* Benchmark                      (chunkSize)  Mode  Cnt        Score       Error  Units
		 * HistogramBenchmark.sequential         1000  avgt   25    54798.296 ±   387.059  us/op
		 * HistogramBenchmark.sequential         4000  avgt   25    55110.452 ±   698.442  us/op
		 * HistogramBenchmark.sequential        16000  avgt   25    54925.999 ±  1141.928  us/op
		 * HistogramBenchmark.sequential        64000  avgt   25    54710.884 ±   671.207  us/op
		 */
		return sequential.calcHistogram(pixels);
	}

	@Benchmark
	public int[] mutex() throws InterruptedException {
		/* Benchmark                      (chunkSize)  Mode  Cnt        Score       Error  Units
		 * HistogramBenchmark.mutex              1000  avgt   25  2696047.682 ± 91279.926  us/op
		 * HistogramBenchmark.mutex              4000  avgt   25  2662352.744 ± 63415.939  us/op
		 * HistogramBenchmark.mutex             16000  avgt   25  2695660.608 ± 74531.645  us/op
		 * HistogramBenchmark.mutex             64000  avgt   25  2737871.652 ± 57738.064  us/op
		 */
		return mutex.calcHistogram(pixels, chunkSize);
	}

	@Benchmark
	public int[] reduction() throws InterruptedException {
		/* Benchmark                      (chunkSize)  Mode  Cnt        Score       Error  Units
		 * HistogramBenchmark.reduction          1000  avgt   25   120226.253 ±  4755.797  us/op
		 * HistogramBenchmark.reduction          4000  avgt   25    30383.420 ±   789.423  us/op
		 * HistogramBenchmark.reduction         16000  avgt   25     9194.466 ±   116.517  us/op
		 * HistogramBenchmark.reduction         64000  avgt   25     5368.570 ±   118.334  us/op
		 */
		return reduction.calcHistogram(pixels, chunkSize);
	}
}
