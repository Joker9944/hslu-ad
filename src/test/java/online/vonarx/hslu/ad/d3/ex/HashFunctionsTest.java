package online.vonarx.hslu.ad.d3.ex;

import org.junit.jupiter.api.Test;

import static online.vonarx.hslu.ad.d3.ex.HashFunctions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HashFunctionsTest {

	public static final String test = "Test";

	public static final int asciiUpperT = 84;
	public static final int asciiLowerE = 101;
	public static final int asciiLowerS = 115;
	public static final int asciiLowerT = 116;

	@Test
	public void firstCharHashTest() {
		assertEquals(asciiUpperT,
				firstCharHash.hash(test));
	}

	@Test
	public void allCharsAddHashTest() {
		assertEquals(asciiUpperT + asciiLowerE + asciiLowerS + asciiLowerT,
				allCharsAddHash.hash(test));
	}

	@Test
	public void allCharsMulHashTest() {
		assertEquals(asciiUpperT * asciiLowerE * asciiLowerS * asciiLowerT,
				allCharsMulHash.hash(test));
	}

	@Test
	public void builtInHashTest() {
		assertEquals(2603186,
				builtInHash.hash(test));
	}
}
