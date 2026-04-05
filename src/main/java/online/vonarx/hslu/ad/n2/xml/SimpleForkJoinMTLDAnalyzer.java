package online.vonarx.hslu.ad.n2.xml;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SimpleForkJoinMTLDAnalyzer extends RecursiveTask<MTLDAnalyzer.CumulativeAverage> implements MTLDAnalyzer {

	final BookNode bookNode;

	public SimpleForkJoinMTLDAnalyzer() {
		this.bookNode = null;
	}

	@Override
	public CumulativeAverage analyzeBook(final @NonNull BookNode node) {
		if (node.name().equals("paragraph"))
			return analyzeParagraph(node.text());

		if (node.name().equals("title"))
			// Titles are discarded according to provided exercise code
			return new CumulativeAverage();

		final var cumulativeAverage = analyzeChildren(node);

		if (node.name().equals("book"))
			System.out.printf("Book '%s' has avg. MTLD of %.2f%n", node.getTitle(), cumulativeAverage.getAverage());

		return cumulativeAverage;
	}

	@Override
	protected CumulativeAverage compute() {
		if (bookNode == null) throw new IllegalStateException("Compute method called from invalid scope");
		return analyzeBook(bookNode);
	}

	protected CumulativeAverage analyzeParagraph(final String paragraph) {
		return new CumulativeAverage().add(calculateMTLD(paragraph));
	}

	protected CumulativeAverage analyzeChildren(final BookNode node) {
		final var forks = node.children().stream()
				.map(SimpleForkJoinMTLDAnalyzer::new)
				.toList();
		forks.forEach(ForkJoinTask::fork);
		return forks.stream()
				.map(ForkJoinTask::join)
				.reduce(CumulativeAverage::add)
				.orElseThrow(() -> new IllegalStateException("Forked with no children"));
	}
}
