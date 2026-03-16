package online.vonarx.hslu.ad.d2.math;

import online.vonarx.hslu.ad.d1.j.StackMachine;
import online.vonarx.hslu.ad.d2.math.node.ValueNode;
import online.vonarx.hslu.ad.d2.math.node.operation.AddNode;
import online.vonarx.hslu.ad.d2.math.node.operation.MulNode;

public class ExampleCalc {

	public static void main(final String[] args) {
		final var root = new MulNode(
				new AddNode(new ValueNode(2), new ValueNode(3)),
				new ValueNode(4)
		);
		doIt(root);
	}

	static void doIt(final MulNode root) {
		System.out.println(root);
		System.out.println(root.value());
		final var program = JCompiler.compile(root);
		System.out.println(program);
		final var machine = new StackMachine(program);
		machine.run();
	}
}
