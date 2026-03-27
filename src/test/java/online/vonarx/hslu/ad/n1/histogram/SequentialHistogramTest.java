package online.vonarx.hslu.ad.n1.histogram;

class SequentialHistogramTest extends HistogramTest {

	@Override
	protected Histogram createHistogram() {
		return new SequentialHistogram();
	}
}
