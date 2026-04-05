package online.vonarx.hslu.ad.n2.xml;

import org.openjdk.jmh.annotations.*;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static org.openjdk.jmh.annotations.Mode.AverageTime;
import static org.openjdk.jmh.annotations.Scope.Benchmark;

/* Benchmark                         Mode  Cnt       Score      Error  Units
 * MTLDAnalyzerBenchmark.sequential  avgt   25  370732.635 ± 3769.312  us/op
 * MTLDAnalyzerBenchmark.simple      avgt   25   44596.980 ±  770.240  us/op
 */
@BenchmarkMode(AverageTime)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(5)
@OutputTimeUnit(MICROSECONDS)
@State(Benchmark)
public class MTLDAnalyzerBenchmark {

	static final BookNode books = BookNode.getFromXml("/n2/books.xml");

	static final MTLDAnalyzer sequential = new SequentialMTLDAnalyzer();
	static final MTLDAnalyzer simple = new SimpleForkJoinMTLDAnalyzer();

	@Benchmark
	public double sequential() {
		return sequential.analyzeBook(books).getAverage();
	}

	@Benchmark
	public double simple() {
		return simple.analyzeBook(books).getAverage();
	}
}
