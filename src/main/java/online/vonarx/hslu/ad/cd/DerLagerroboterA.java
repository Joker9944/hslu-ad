package online.vonarx.hslu.ad.cd;

import lombok.SneakyThrows;
import online.vonarx.hslu.ad.ResourceReader;

import java.util.Arrays;

public class DerLagerroboterA {

	static void main() {
		final var boxes = getInput();

		var totalErr = 0;
		for (int i = 0; i < boxes.length - 1; i++) {
			if (boxes[i] > boxes[i + 1]) totalErr++;
		}

		System.out.println(totalErr);
	}

	@SneakyThrows
	public static int[] getInput() {
		return Arrays.stream(ResourceReader.readResourceAsString("/cd/Der Lagerroboter.txt")
						.split(" "))
				.mapToInt(Integer::valueOf)
				.toArray();
	}
}
