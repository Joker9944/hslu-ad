package online.vonarx.hslu.ad.n2.xml;

import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Analyzes and calculates the Measure of Textual Lexical Diversity (MTLD)
 * for each book contained in the provided file 'books.xml'.
 */
public interface MTLDAnalyzer {
	double TTR_THRESHOLD = 0.72; // Standard MTLD threshold
	int MIN_TOKENS = 50;

	/**
	 * Main function that calculates the Measure of Textual Lexical Diversity (MTLD).
	 */
	default double calculateMTLD(final String paragraph) {
		return calculateMTLD(tokenize(paragraph));
	}

	default String[] tokenize(final String paragraph) {
		return paragraph.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
	}

	default double calculateMTLD(final String[] tokens) {
		if (tokens.length < MIN_TOKENS) return 0.0;
		final var forward = calculateMTLDSinglePass(tokens);
		// revert token order
		Collections.reverse(Arrays.asList(tokens));
		final var backward = calculateMTLDSinglePass(tokens);
		return (forward + backward) / 2.0;
	}

	default double calculateMTLDSinglePass(final String[] tokens) {
		var factorCount = 0;
		var currentWordCount = 0;
		final var uniqueWords = new HashSet<String>();

		for (final var token : tokens) {
			if (token.isEmpty()) continue;

			uniqueWords.add(token);
			currentWordCount++;

			final var currentTTR = (double) uniqueWords.size() / currentWordCount;
			if (currentTTR < TTR_THRESHOLD) {
				factorCount++;
				uniqueWords.clear();
				currentWordCount = 0;
			}
		}

		// adds final partial factor
		final var remainingTTR = (double) uniqueWords.size() / Math.max(1, currentWordCount);
		final var partialFactor = (1.0 - remainingTTR) / (1.0 - TTR_THRESHOLD);
		final var totalFactors = factorCount + partialFactor;
		return tokens.length / Math.max(1, totalFactors);
	}

	/**
	 * Calculates the MTLD for all paragraphs of a book and prints
	 * the average for each book.
	 */
	CumulativeAverage analyzeBook(final @NonNull BookNode node);

	/**
	 * Class to calculate the cumulative (running) average.
	 */
	class CumulativeAverage {
		private double sum;
		private int count;

		public CumulativeAverage add(final double value) {
			if (value > 0.0) {
				sum += value;
				count++;
			}
			return this;
		}

		public CumulativeAverage add(final CumulativeAverage other) {
			sum += other.sum;
			count += other.count;
			return this;
		}

		public double getAverage() {
			if (count == 0) throw new IllegalStateException("No values added");
			return sum / count;
		}
	}
}
