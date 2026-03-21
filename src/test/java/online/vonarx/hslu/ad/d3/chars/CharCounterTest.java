package online.vonarx.hslu.ad.d3.chars;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static java.util.Collections.emptyMap;
import static java.util.Map.of;
import static online.vonarx.hslu.ad.d3.chars.CharCounter.countChars;
import static org.junit.jupiter.api.Assertions.*;

class CharCounterTest {

	@Test void lowerCase() {
		assertEquals(of('A', 1L), countChars("a"));
	}

	@Test void upperCase() {
		assertEquals(of('A', 1L), countChars("A"));
	}

	@Test void empty() {
		assertEquals(emptyMap(), countChars(""));
	}

	@Test void nonLetters() {
		assertEquals(emptyMap(), countChars("!@#$%^&*() \n[];',./"));
	}

	@Test void sentence() {
		final var chars = new HashMap<Character, Long>();
		chars.put('A', 1L);
		chars.put('B', 1L);
		chars.put('C', 1L);
		chars.put('D', 1L);
		chars.put('E', 3L);
		chars.put('F', 1L);
		chars.put('G', 1L);
		chars.put('H', 2L);
		chars.put('I', 1L);
		chars.put('J', 1L);
		chars.put('K', 1L);
		chars.put('L', 1L);
		chars.put('M', 1L);
		chars.put('N', 1L);
		chars.put('O', 4L);
		chars.put('P', 1L);
		chars.put('Q', 1L);
		chars.put('R', 2L);
		chars.put('S', 1L);
		chars.put('T', 2L);
		chars.put('U', 2L);
		chars.put('V', 1L);
		chars.put('W', 1L);
		chars.put('X', 1L);
		chars.put('Y', 1L);
		chars.put('Z', 1L);
		assertEquals(chars, countChars("The quick brown fox jumps over the lazy dog."));
	}
}
