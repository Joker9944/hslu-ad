package online.vonarx.hslu.ad.d3.ex;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static online.vonarx.hslu.ad.ResourceReader.readResourceAsString;

@FunctionalInterface
interface HashFunction {
	int hash(String s);
}

public class HashFunctions {
	// Aufgabe b)
	static HashFunction firstCharHash = s -> s.toCharArray()[0];
	static HashFunction allCharsAddHash = s -> s.chars()
			.sum();
	static HashFunction allCharsMulHash = s -> s.chars()
			.reduce((left, right) -> left * right)
			.orElse(0);
	// Aufgabe c)
	static HashFunction builtInHash = String::hashCode;

	/**
	 * Zählt die Anzahl Kollisionen, die bei der Anwendung der Hashfunktion f auf die Liste der Wörter auftreten.
	 *
	 * @param f     die Hashfunktion, die auf die Wörter angewendet wird
	 * @param words die Liste der Wörter, auf die die Hashfunktion angewendet wird
	 * @return die Anzahl der Kollisionen
	 */
	static int countCollisions(final HashFunction f, final List<String> words) {
		Set<Integer> set = new TreeSet<>();
		int collisionCount = 0;

		for (String word : words) {
			int hash = f.hash(word);

			if (set.contains(hash)) {
				// Kollision!
				collisionCount++;
			} else {
				set.add(hash);
			}
		}

		return collisionCount;
	}

	public static void main(String[] args) throws IOException {
		final var words = asList(readResourceAsString("/d3/words.txt").split("\n"));
		System.out.printf("First char collisions: %s%n", countCollisions(firstCharHash, words));
		System.out.printf("All chars summed collisions: %s%n", countCollisions(allCharsAddHash, words));
		System.out.printf("All chars multiplied collisions: %s%n", countCollisions(allCharsMulHash, words));
		System.out.printf("Built-in hashCode collisions: %s%n", countCollisions(builtInHash, words));
		/* First char collisions: 975
		 * All chars summed collisions: 669
		 * All chars multiplied collisions: 34
		 * Built-in hashCode collisions: 0
		 */
	}
}
