package online.vonarx.hslu.ad.d2.math.node.operation;

import online.vonarx.hslu.ad.d1.j.JOperation;
import online.vonarx.hslu.ad.d2.math.node.MathNode;

public class AddNode extends OperationNode {

	public AddNode(MathNode left, MathNode right) {
		super(left, right);
	}

	@Override
	public int value() {
		return left().value() + right().value();
	}

	@Override
	public String jInstruction() {
		return JOperation.ADD.toString();
	}

	@Override
	public String toString() {
		return String.format("(%s + %s)", left(), right());
	}
}
