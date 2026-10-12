package online.vonarx.hslu.ad.cd;

import lombok.SneakyThrows;
import online.vonarx.hslu.ad.ResourceReader;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TheatereinlassB {

	final static public List<Integer> vips = List.of(80, 99, 33, 115);

	@SneakyThrows
	public static void main(String[] args) {
		final var hint = getInput().stream()
				.map(line -> line.split(" "))
				.flatMap(Stream::of)
				.filter(s -> s.matches("\\d+"))
				.mapToInt(Integer::valueOf)
				.filter(c -> c >= 32)
				.mapToObj(Character::toString)
				.collect(Collectors.joining());
		System.out.println(hint);
		/* Personen mit den Nummern 80, 99, 33 und 115 sind VIPs, diese Personen stellen sich nicht hinten an, sondern
		 * werden in der Schlange vorne eingefügt! Ansonsten bleibt alles wie in Teil A, die Lösungszahl kann also auf die
		 * gleiche Weise berechnet werden.
		 */

		final var slices = TheatereinlassA.getInput();

		final var queue = new LinkedList<TheatereinlassA.Person>();

		slices.forEach(slice -> {
			slice.persons().forEach(person -> {
				if (vips.contains(person.id()))
					queue.addFirst(person);
				else
					queue.addLast(person);
			});
			IntStream.range(0, slice.entryPermitted())
					.forEach(value -> queue.pop());
		});

		System.out.println(queue.stream()
				.mapToInt(TheatereinlassA.Person::id)
				.sum()
		);
	}

	@SneakyThrows
	public static List<String> getInput() {
		final var input = ResourceReader.readResourceAsString("/cd/Theatereinlass.txt").split("\n");
		return List.of(input);
	}
}
