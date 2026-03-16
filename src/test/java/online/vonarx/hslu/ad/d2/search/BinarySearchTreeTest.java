package online.vonarx.hslu.ad.d2.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTreeTest {

	public static final List<String> elements = List.of("C", "B", "A", "D", "E", "F", "G");
	public static final List<String> otherElements = List.of("X", "Y", "Z");

	public BinarySearchTree<String> defaultTree;

	@BeforeEach
	public void setup() {
		defaultTree = new BinarySearchTree<>(elements);
	}

	@Test
	public void searchFound() {
		elements.forEach(element -> assertTrue(defaultTree.search(element)));
	}

	@Test
	public void searchNotFound() {
		otherElements.forEach(element -> assertFalse(defaultTree.search(element)));
	}

	@Test
	public void searchEmpty() {
		final var empty = new BinarySearchTree<String>();
		assertFalse(empty.search("A"));
	}
}
