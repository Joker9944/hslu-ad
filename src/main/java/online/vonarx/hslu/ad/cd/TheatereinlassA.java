package online.vonarx.hslu.ad.cd;

import lombok.SneakyThrows;
import online.vonarx.hslu.ad.ResourceReader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class TheatereinlassA {

	public static void main(String[] args) {
		final var slices = getInput();

		final var queue = new LinkedList<Person>();

		slices.forEach(slice -> {
			queue.addAll(slice.persons());
			IntStream.range(0, slice.entryPermitted())
					.forEach(value -> queue.pop());
		});

		System.out.println(queue.stream()
				.mapToInt(Person::id)
				.sum()
		);
	}

	@SneakyThrows
	public static List<Slice> getInput() {
		return ResourceReader.readResourceAsLines("/cd/Theatereinlass.txt").stream()
				.map(s -> s.split(" \\| "))
				.map(parts -> {
					final var entryPermitted = Integer.parseInt(parts[1]);
					final var persons = Arrays.stream(parts[0].split(" "))
							.mapToInt(Integer::parseInt)
							.mapToObj(Person::new)
							.toList();
					return new Slice(entryPermitted, persons);
				})
				.toList();
	}

	public record Person(int id) { }

	public record Slice(int entryPermitted, List<Person> persons) { }
}
