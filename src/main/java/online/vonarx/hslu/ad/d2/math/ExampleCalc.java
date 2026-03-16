package online.vonarx.hslu.ad.d2.math;

import online.vonarx.hslu.ad.d2.math.node.ValueNode;
import online.vonarx.hslu.ad.d2.math.node.operation.AddNode;
import online.vonarx.hslu.ad.d2.math.node.operation.MulNode;

public class ExampleCalc {

	public static void main(String[] args) {
		final var root = new MulNode(
				new AddNode(new ValueNode(2), new ValueNode(3)),
				new ValueNode(4)
		);
		System.out.println(root);
		System.out.println(root.value());
		System.out.println(JCompiler.compile(root));
	}
}
