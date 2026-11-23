package online.vonarx.hslu.ad.cd;

import online.vonarx.hslu.ad.cd.BaeckereiA.Order;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BaeckereiB {

	static void main() {
		System.out.println(solve(BaeckereiA.getInput()));
	}

	static long solve(final List<Order> input) {
		final var waiting = new PriorityQueue<>(Comparator
				.comparingInt(Order::priority).reversed()
				.thenComparingInt(Order::entryMoment));

		var next = 0;
		var ovenFree = 0L;
		var total = 0L;
		while (next < input.size() || !waiting.isEmpty()) {
			if (waiting.isEmpty() && input.get(next).entryMoment() > ovenFree)
				ovenFree = input.get(next).entryMoment();
			while (next < input.size() && input.get(next).entryMoment() <= ovenFree)
				waiting.add(input.get(next++));

			final var order = waiting.poll();
			assert order != null;
			total += (ovenFree - order.entryMoment()) * order.priority();
			ovenFree += order.bakeTime();
		}
		return total;
	}
}
