package online.vonarx.hslu.ad.cd;

import lombok.Builder;
import lombok.SneakyThrows;
import online.vonarx.hslu.ad.ResourceReader;

import java.util.List;

import static java.util.stream.Collectors.toSet;

public class ReparaturAnDerRaumstationA {

	public static void main(String[] args) {
		final var tasks = getInput();

		final var totalTasks = tasks.stream()
				.map(Task::tool)
				.collect(toSet())
				.size();
		final var totalTime = tasks.stream()
				.mapToInt(Task::time)
				.sum();

		System.out.println(totalTasks + totalTime);
	}

	@SneakyThrows
	public static List<Task> getInput() {
		return ResourceReader.readResourceAsLines("/cd/Reparatur an der Raumstation.txt")
				.stream()
				.map(s -> s.split(" "))
				.map(strings -> Task.builder()
						.number(Integer.parseInt(strings[0].substring(0, strings[0].length() - 1)))
						.tool(strings[1])
						.time(Integer.parseInt(strings[2])))
				.map(Task.TaskBuilder::build)
				.toList();
	}

	@Builder
	public record Task(int number, String tool, int time) {}
}
