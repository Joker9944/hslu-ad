package online.vonarx.hslu.ad.d2.math;

import online.vonarx.hslu.ad.d2.math.node.ValueNode;
import online.vonarx.hslu.ad.d2.math.node.operation.AddNode;
import online.vonarx.hslu.ad.d2.math.node.operation.MulNode;

public class ACalc {

	public static void main(String[] args) {
		final var root = new MulNode(
				new AddNode(new ValueNode(4), new ValueNode(5)),
				new AddNode(new ValueNode(2), new ValueNode(3))
		);
		System.out.println(root);
		System.out.println(root.value());
		System.out.println(JCompiler.compile(root));
	}
}
