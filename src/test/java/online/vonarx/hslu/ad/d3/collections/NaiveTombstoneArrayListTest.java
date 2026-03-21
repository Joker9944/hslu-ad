package online.vonarx.hslu.ad.d3.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NaiveTombstoneArrayListTest extends NaiveArrayListTest {

	@Override
	protected NaiveArrayList<String> createList() {
		return new NaiveTombstoneArrayList<>();
	}

	@Override
	protected NaiveArrayList<String> createList(int size) {
		return new NaiveTombstoneArrayList<>(size);
	}

	@Test
	public void tombstones() {
		final var list = createList(10);
		List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
				.forEach(s -> assertTrue(list.add(s)));
		List.of("1", "2", "3", "4", "5", "6", "7", "8")
				.forEach(s -> assertTrue(list.remove(s)));
		assertTrue(list.remove("9"));
		List.of("1", "2", "3", "4", "5", "6", "7", "8", "9")
				.forEach(s -> assertTrue(list.add(s)));
	}
}
