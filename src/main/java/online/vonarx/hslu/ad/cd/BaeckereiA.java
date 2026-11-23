package online.vonarx.hslu.ad.cd;

import lombok.SneakyThrows;
import online.vonarx.hslu.ad.ResourceReader;

import java.util.List;

public class BaeckereiA {

	static void main() {
		var ovenFree = 0L;
		var total = 0L;
		for (final var order : getInput()) {
			final var start = Math.max(ovenFree, order.entryMoment());
			total += (start - order.entryMoment()) * order.priority();
			ovenFree = start + order.bakeTime();
		}

		System.out.println(total);
	}

	@SneakyThrows
	public static List<Order> getInput() {
		return ResourceReader.readResourceAsLines("/cd/Bäckerei.txt")
				.stream()
				.map(s -> s.split(" "))
				.map(Order::new)
				.sorted()
				.toList();
	}

	public record Order(int entryMoment, int bakeTime, int priority) implements Comparable<Order> {

		public Order(final String[] parts) {
			this(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		}

		@Override
		public int compareTo(final Order that) {
			return this.entryMoment - that.entryMoment;
		}
	}
}
