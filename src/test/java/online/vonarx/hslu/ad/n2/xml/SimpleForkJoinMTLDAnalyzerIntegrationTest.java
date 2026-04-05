package online.vonarx.hslu.ad.n2.xml;

class SimpleForkJoinMTLDAnalyzerIntegrationTest extends MTLDAnalyzerIntegrationTest {

	@Override
	protected MTLDAnalyzer createMTLDAnalyzer() {
		return new SimpleForkJoinMTLDAnalyzer();
	}
}
