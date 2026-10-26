package online.vonarx.hslu.ad.cd;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class ReparaturAnDerRaumstationB {
	public static void main(String[] args) {
		final var tasks = ReparaturAnDerRaumstationA.getInput();

		final var totalTimeByToolName = tasks.stream()
				.collect(groupingBy(ReparaturAnDerRaumstationA.Task::tool,
						summingInt(ReparaturAnDerRaumstationA.Task::time)));
		final var largestTime = totalTimeByToolName.values().stream()
				.mapToInt(i -> i)
				.max()
				.orElseThrow();

		System.out.println(totalTimeByToolName.size() + largestTime);
	}
}
