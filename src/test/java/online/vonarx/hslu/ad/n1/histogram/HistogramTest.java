package online.vonarx.hslu.ad.n1.histogram;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public abstract class HistogramTest {

	final Histogram histogram = createHistogram();

	protected abstract Histogram createHistogram();

	@Test
	void negativeByte() throws InterruptedException {
		final var expected = new int[256];
		expected[255] = 1;
		assertArrayEquals(expected, histogram.calcHistogram(new byte[]{-1}, 20));
	}

	@Test
	void emptyArray() throws InterruptedException {
		assertArrayEquals(new int[256], histogram.calcHistogram(new byte[0], 20));
	}

	@Test
	void singleElement() throws InterruptedException {
		final var expected = new int[256];
		expected[0] = 1;
		assertArrayEquals(expected, histogram.calcHistogram(new byte[]{0}, 20));
	}

	@Test
	void edgeCases() throws InterruptedException {
		final var expected = new int[256];
		expected[0] = 1;
		expected[127] = 1;
		expected[128] = 1;
		expected[255] = 1;
		assertArrayEquals(expected, histogram.calcHistogram(new byte[]{0, 127, -128, -1}, 20)); // 0, 127, 128, 255 unsigned
	}

	@Test
	void allValuesOnce() throws InterruptedException {
		final var input = new byte[256];
		for (var i = 0; i < input.length; i++) input[i] = (byte) i;
		final var expected = new int[256];
		Arrays.fill(expected, 1);
		assertArrayEquals(expected, histogram.calcHistogram(input, 20));
	}
}
