package online.vonarx.hslu.ad.d1.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NaiveLinkedStackTest {

	public NaiveLinkedStack<String> emptyList;
	public NaiveLinkedStack<String> filledList;

	@BeforeEach
	public void setup() {
		emptyList = new NaiveLinkedStack<>();
		filledList = new NaiveLinkedStack<>();
		filledList.push("1");
		filledList.push("2");
		filledList.push("3");
	}

	@Test
	public void sizeEmpty() {
		assertEquals(0, emptyList.size());
	}

	@Test
	public void sizeNotEmpty() {
		assertEquals(3, filledList.size());
	}

	@Test
	public void containsEmpty() {
		assertFalse(emptyList.contains("empty"));
	}

	@Test
	public void containsHead() {
		assertTrue(filledList.contains("3"));
	}

	@Test
	public void containsTail() {
		assertTrue(filledList.contains("1"));
	}

	@Test
	public void containsMiddle() {
		assertTrue(filledList.contains("2"));
	}

	@Test
	public void popEmpty() {
		assertNull(emptyList.pop());
		assertEquals(0, emptyList.size());
	}

	@Test
	public void pop() {
		assertEquals("3", filledList.pop());
		assertEquals(2, filledList.size());
	}

	@Test
	public void removeEmpty() {
		assertFalse(emptyList.remove("empty"));
		assertEquals(0, emptyList.size());
	}

	@Test
	public void removeNotFound() {
		assertFalse(filledList.remove("not found"));
		assertEquals(3, filledList.size());
	}

	@Test
	public void removeHead() {
		assertTrue(filledList.remove("3"));
		assertEquals(2, filledList.size());
	}

	@Test
	public void removeTail() {
		assertTrue(filledList.remove("1"));
		assertEquals(2, filledList.size());
	}

	@Test
	public void removeMiddle() {
		assertTrue(filledList.remove("2"));
		assertEquals(2, filledList.size());
	}

	@Test
	public void iteratorEmpty() {
		final var iterator = emptyList.iterator();
		assertFalse(iterator.hasNext());
		final var e = assertThrows(NoSuchElementException.class, iterator::next);
		assertEquals("No more elements in this list", e.getMessage());
	}

	@Test
	public void iterator() {
		final var iterator = filledList.iterator();
		assertTrue(iterator.hasNext());
		assertEquals("3", iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("2", iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals("1", iterator.next());
		assertFalse(iterator.hasNext());
		final var e = assertThrows(NoSuchElementException.class, iterator::next);
		assertEquals("No more elements in this list", e.getMessage());
	}
}
