package online.vonarx.hslu.ad.n1.histogram;

class MutexHistogramTest extends HistogramTest {

	@Override
	protected Histogram createHistogram() {
		return new MutexHistogram();
	}
}
