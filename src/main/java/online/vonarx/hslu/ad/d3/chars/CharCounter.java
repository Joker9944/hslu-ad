package online.vonarx.hslu.ad.d3.chars;

import lombok.experimental.UtilityClass;

import java.util.Map;

import static java.lang.Character.toChars;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@UtilityClass
public final class CharCounter {

	public static final int asciiUpperA = 65;
	public static final int asciiUpperZ = 90;
	private static final int asciiUpperLowerOffset = 32;

	public static Map<Character, Long> countChars(final String s) {
		return s.chars()
				.map(c -> c > asciiUpperZ ? c - asciiUpperLowerOffset : c) // map lowercase letters to uppercase
				.filter(c -> c >= asciiUpperA && c <= asciiUpperZ) // filter out characters which are not uppercase letters
				.mapToObj(c -> toChars(c)[0])
				.collect(groupingBy(identity(), counting()));
	}
}
