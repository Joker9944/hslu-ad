package online.vonarx.hslu.ad.n2.xml;

import org.jspecify.annotations.NonNull;

public class SequentialMTLDAnalyzer implements MTLDAnalyzer {

	@Override
	public CumulativeAverage analyzeBook(final @NonNull BookNode node) {
		final var cumulativeAverage = new CumulativeAverage();
		if (node.name().equals("paragraph")) {
			cumulativeAverage.add(calculateMTLD(node.text()));
		} else {
			for (final var childNodes : node.children()) {
				cumulativeAverage.add(analyzeBook(childNodes));
			}
		}
		if (node.name().equals("book")) {
			System.out.printf("Book '%s' has avg. MTLD of %.2f%n", node.getTitle(), cumulativeAverage.getAverage());
		}
		return cumulativeAverage;
	}
}
