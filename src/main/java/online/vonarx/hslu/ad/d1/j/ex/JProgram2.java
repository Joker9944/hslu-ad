package online.vonarx.hslu.ad.d1.j.ex;

import online.vonarx.hslu.ad.d1.j.StackMachine;

public class JProgram2 {

	private final static String PROGRAM = """
			LOAD 4
			LOAD 5
			ADD
			LOAD 2
			LOAD 3
			ADD
			MUL
			PRINT
			""";

	public static void main(String[] args) {
		new StackMachine(PROGRAM).run();
	}
}
