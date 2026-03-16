package online.vonarx.hslu.ad.d2.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeSearchMathNodeTest {

	public BinaryTreeSearchNode<String> a;
	public BinaryTreeSearchNode<String> b;

	@BeforeEach
	public void setup() {
		a = new BinaryTreeSearchNode<>("A");
		b = new BinaryTreeSearchNode<>("B");
	}

	@Test
	public void smaller() {
		assertFalse(b.smaller(a));
		assertFalse(b.smaller("A"));
		assertTrue(a.smaller(b));
		assertTrue(a.smaller("B"));
	}

	@Test
	public void larger() {
		assertTrue(b.larger(a));
		assertTrue(b.larger("A"));
		assertFalse(a.larger(b));
		assertFalse(a.larger("B"));
	}

	@Test
	public void addLeft() {
		b.add("A");
		assertTrue(b.left().isPresent());
		assertEquals("A", b.left().get());
	}

	@Test
	public void addRight() {
		b.add("C");
		assertTrue(b.right().isPresent());
		assertEquals("C", b.right().get());
	}

	@Test
	public void addNonUnique() {
		final var e = assertThrows(IllegalArgumentException.class, () -> b.add("B"));
		assertEquals("Element B is not unique", e.getMessage());
	}
}
