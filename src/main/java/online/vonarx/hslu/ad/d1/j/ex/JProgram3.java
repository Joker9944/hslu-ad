package online.vonarx.hslu.ad.d1.j.ex;

import online.vonarx.hslu.ad.d1.j.StackMachine;

public class JProgram3 {

	private final static String PROGRAM = """
			LOAD 5
			LOAD 6
			LOAD 7
			LOAD 4
			SUB
			DIV
			MUL
			PRINT
			""";

	public static void main(String[] args) {
		new StackMachine(PROGRAM).run();
	}
}
