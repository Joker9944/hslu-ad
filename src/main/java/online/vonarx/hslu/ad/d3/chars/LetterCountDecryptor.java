package online.vonarx.hslu.ad.d3.chars;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

import static online.vonarx.hslu.ad.d3.chars.CharCounter.*;

@UtilityClass
public final class LetterCountDecryptor {
	private static final char[] CHARS_SORTED_BY_COMMUNALITY_GERMAN = {'E', 'N', 'I', 'S', 'R', 'A', 'T', 'D', 'H', 'U',
			'L', 'C', 'G', 'M', 'O', 'B', 'W', 'F', 'K', 'Z', 'P', 'V', 'J', 'Y', 'X', 'Q'};

	public static String decrypt(final String encrypted) {
		final var charCountByCharSorted = sortCharsByCount(countChars(encrypted));

		final var encryptedChars = encrypted.toCharArray();
		final var decryptedChars = new char[encryptedChars.length];

		for (var i = 0; i < encryptedChars.length; i++) {
			final var encryptedChar = encryptedChars[i];
			if (encryptedChar >= asciiUpperA && encryptedChar <= asciiUpperZ)
				decryptedChars[i] = CHARS_SORTED_BY_COMMUNALITY_GERMAN[charCountByCharSorted.indexOf(encryptedChar)];
			else decryptedChars[i] = encryptedChar;
		}

		return String.valueOf(decryptedChars);
	}

	static List<Character> sortCharsByCount(final Map<Character, Long> charCountByChar) {
		return charCountByChar.entrySet().stream()
				.sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
				.map(Map.Entry::getKey)
				.toList();
	}
}
