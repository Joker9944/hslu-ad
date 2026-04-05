package online.vonarx.hslu.ad.n2.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class MTLDAnalyzerIntegrationTest {

	static final BookNode books = BookNode.getFromXml("/n2/books.xml");

	final MTLDAnalyzer mtldAnalyzer = createMTLDAnalyzer();

	protected abstract MTLDAnalyzer createMTLDAnalyzer();

	@Test
	void average() {
		assertEquals(73.84633449991216, mtldAnalyzer.analyzeBook(books).getAverage());
	}
}
