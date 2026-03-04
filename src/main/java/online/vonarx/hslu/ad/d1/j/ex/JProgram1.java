package online.vonarx.hslu.ad.d1.j.ex;

import online.vonarx.hslu.ad.d1.j.StackMachine;

public class JProgram1 {

	private final static String PROGRAM = """
			LOAD 2
			LOAD 3
			ADD
			LOAD 4
			MUL
			PRINT
			""";

	public static void main(String[] args) {
		final var machine = new StackMachine(PROGRAM);
		do System.out.println(machine.stackState()); while (machine.step());
		System.out.println(machine.stackState());
	}
}
