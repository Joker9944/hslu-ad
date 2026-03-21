package online.vonarx.hslu.ad.d3.chars;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static online.vonarx.hslu.ad.d3.chars.LetterCountDecryptor.sortCharsByCount;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LetterCountDecryptorTest {

	@Test
	void sort() {
		assertEquals(List.of('C', 'A', 'B'), sortCharsByCount(Map.of('A', 2L, 'B', 1L, 'C', 3L)));
	}
}
