package online.vonarx.hslu.ad.n1.histogram;

public class SequentialHistogram implements Histogram {

	@Override
	public int[] calcHistogram(final byte[] pixels, final int size) {
		final var histogram = new int[256];
		for (var pixel : pixels)
			histogram[Byte.toUnsignedInt(pixel)]++;
		return histogram;
	}
}
