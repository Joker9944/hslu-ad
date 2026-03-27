package online.vonarx.hslu.ad.d3.chars.ex;

import java.io.IOException;

import static online.vonarx.hslu.ad.ResourceReader.readResourceAsString;
import static online.vonarx.hslu.ad.d3.chars.CharCounter.countChars;

public class WordsExample {

	public static void main(String[] args) throws IOException {
		final var words = readResourceAsString("/d3/words.txt");
		System.out.println(countChars(words));
	}
}
