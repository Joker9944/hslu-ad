package online.vonarx.hslu.ad.cd;

import lombok.SneakyThrows;
import online.vonarx.hslu.ad.ResourceReader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DasErbeVonSirGeorgeB {

	public static void main(String[] args) {
		final var input = getInput();
		final var amount = Integer.parseInt(input.getFirst());

		final var childToParent = input.subList(1, input.size()).stream()
				.map(s -> s.split(" -> "))
				.collect(Collectors.toMap(r -> r[0], r -> r[1]));

		final var childrenByParent = childToParent.entrySet().stream()
				.collect(Collectors.groupingBy(Map.Entry::getValue));

		var parent = childToParent.get("John");
		var denominator = childrenByParent.get(parent).size();
		while (!parent.equals("George")) {
			parent = childToParent.get(parent);
			denominator = denominator * childrenByParent.get(parent).size();
		}

		System.out.println(amount / denominator);
	}

	@SneakyThrows
	public static List<String> getInput() {
		return ResourceReader.readResourceAsLines("/cd/Das Erbe von Sir George.txt");
	}
}