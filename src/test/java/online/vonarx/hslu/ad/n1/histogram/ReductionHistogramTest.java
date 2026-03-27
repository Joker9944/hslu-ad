package online.vonarx.hslu.ad.n1.histogram;

class ReductionHistogramTest extends HistogramTest {

	@Override
	protected Histogram createHistogram() {
		return new ReductionHistogramCoreSize();
	}
}
