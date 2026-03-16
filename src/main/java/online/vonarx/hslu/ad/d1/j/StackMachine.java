package online.vonarx.hslu.ad.d1.j;

import lombok.Builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StackMachine {
	private final static Logger log = LogManager.getLogger(StackMachine.class);

	private final Stack<Integer> stack = new Stack<>();

	private final List<Command> program;
	private int programCounter = 0;

	public StackMachine(final String program) {
		this.program = parseProgram(program);
	}

	private static List<Command> parseProgram(final String program) {
		return Arrays.stream(program.split("\n"))
				.map(line -> line.split(" "))
				.map(tokens -> Command.builder()
						.operation(JOperation.valueOf(tokens[0]))
						.args(Arrays.copyOfRange(tokens, 1, tokens.length))
						.build())
				.toList();
	}

	public boolean step() {
		if (finished()) return false;

		final var command = program.get(programCounter++);
		switch (command.operation()) {
			case LOAD -> {
				final var i = Integer.parseInt(command.args[0]);
				stack.push(i);
				log.debug("LOAD:  {} -> stack", i);
			}
			case ADD -> {
				final var b = stack.pop();
				final var a = stack.pop();
				final var result = a + b;
				stack.push(result);
				log.debug("ADD:   {} + {} = {} -> stack", a, b, result);
			}
			case MUL -> {
				final var b = stack.pop();
				final var a = stack.pop();
				final var result = a * b;
				stack.push(result);
				log.debug("MUL:   {} * {} = {} -> stack", a, b, result);
			}
			case PRINT -> {
				final var i = stack.pop();
				System.out.println(i);
				log.debug("PRINT: stack -> {} -> out", i);
			}
			case SUB -> {
				final var b = stack.pop();
				final var a = stack.pop();
				final var result = a - b;
				stack.push(result);
				log.debug("SUB:   {} - {} = {} -> stack", a, b, result);
			}
			case DIV -> {
				final var b = stack.pop();
				final var a = stack.pop();
				final var result = a / b;
				stack.push(result);
				log.debug("DIV:   {} / {} = {} -> stack", a, b, result);
			}
			case null, default ->
					throw new UnsupportedOperationException(String.format("Operation %s not supported", command.operation));
		}

		return !finished();
	}

	public String stackState() {
		return stack.toString();
	}

	public void run() {
		do log.debug(stackState()); while (step());
		log.debug(stackState());
	}

	public boolean finished() {
		return programCounter >= program.size();
	}

	@Builder private record Command(JOperation operation, String[] args) {}
}
