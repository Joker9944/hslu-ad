package online.vonarx.hslu.ad.cd;

import lombok.SneakyThrows;
import online.vonarx.hslu.ad.ResourceReader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DasErbeVonSirGeorgeA {

	public static void main(String[] args) {
		final var allMembers = getInput().stream()
				.map(s -> s.split(" -> "))
				.flatMap(Arrays::stream)
				.collect(Collectors.toSet());

		final var childrenByParent = getInput().stream()
				.map(s -> s.split(" -> "))
				.map(strings -> new Relation(strings[0], strings[1]))
				.collect(Collectors.groupingBy(Relation::parent));

		System.out.println(allMembers.stream()
				.filter(s -> !childrenByParent.containsKey(s))
				.count());
	}

	@SneakyThrows
	public static List<String> getInput() {
		final var input = ResourceReader.readResourceAsLines("/cd/Das Erbe von Sir George.txt");
		return input.subList(1, input.size());
	}

	public record Relation(String child, String parent) { }
}
