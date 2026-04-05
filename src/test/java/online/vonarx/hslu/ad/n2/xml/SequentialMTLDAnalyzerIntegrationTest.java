package online.vonarx.hslu.ad.n2.xml;

class SequentialMTLDAnalyzerIntegrationTest extends MTLDAnalyzerIntegrationTest {

	@Override
	protected MTLDAnalyzer createMTLDAnalyzer() {
		return new SequentialMTLDAnalyzer();
	}
}
