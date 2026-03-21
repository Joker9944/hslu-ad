package online.vonarx.hslu.ad.d3.collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class NaiveArrayListTest {

	protected abstract NaiveArrayList<String> createList();

	protected abstract NaiveArrayList<String> createList(final int size);

	@Test
	public void add() {
		final var list = createList(4);
		assertTrue(list.add("a"));
		assertTrue(list.add("b"));
		assertTrue(list.add("c"));
		assertTrue(list.add("d"));
		assertFalse(list.add("e"));
	}

	@Test
	public void duplicates() {
		final var list = createList(4);
		assertTrue(list.add("a"));
		assertTrue(list.add("a"));
		assertTrue(list.add("a"));
		assertTrue(list.add("a"));
		assertTrue(list.contains("a"));
		assertTrue(list.remove("a"));
		assertTrue(list.remove("a"));
		assertTrue(list.remove("a"));
		assertTrue(list.remove("a"));
		assertFalse(list.remove("a"));
		assertFalse(list.contains("a"));
	}

	@Test
	public void contains() {
		final var list = createList();
		assertTrue(list.add("b"));
		assertTrue(list.add("y"));
		assertTrue(list.contains("b"));
		assertTrue(list.contains("y"));
		assertFalse(list.contains("a"));
		assertFalse(list.contains("c"));
		assertFalse(list.contains("x"));
		assertFalse(list.contains("z"));
	}

	@Test
	public void remove() {
		final var list = createList();
		assertTrue(list.add("a"));
		assertTrue(list.add("z"));
		assertTrue(list.remove("a"));
		assertTrue(list.remove("z"));
	}

	@Test
	public void size() {
		final var list = createList();
		assertTrue(list.add("a"));
		assertTrue(list.add("z"));
		assertEquals(2, list.size());
		assertTrue(list.remove("a"));
		assertTrue(list.remove("z"));
		assertEquals(0, list.size());
	}
}
