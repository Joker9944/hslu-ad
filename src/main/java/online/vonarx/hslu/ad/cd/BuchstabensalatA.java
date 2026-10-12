package online.vonarx.hslu.ad.cd;

import lombok.SneakyThrows;
import online.vonarx.hslu.ad.ResourceReader;

import java.util.List;

public class BuchstabensalatA {

	public static void main(String[] args) {
		final var numbers = getInput()
				.stream()
				.filter(s -> s.matches("\\d+"))
				.mapToInt(Integer::valueOf)
				.toArray();

		var sum = 0;
		for (var i = 0; i < numbers.length; i++) {
			sum += i * numbers[i];
		}

		System.out.println(sum);
	}

	@SneakyThrows
	public static List<String> getInput() {
		final var input = ResourceReader.readResourceAsString("/cd/Buchstabensalat.txt").split("\n");
		return List.of(input);
	}
}
