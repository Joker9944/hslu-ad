package online.vonarx.hslu.ad.d2.math.node.operation;

import online.vonarx.hslu.ad.d1.j.JOperation;
import online.vonarx.hslu.ad.d2.math.node.MathNode;

public class MulNode extends OperationNode {

	public MulNode(MathNode left, MathNode right) {
		super(left, right);
	}

	@Override
	public int value() {
		return left().value() * right().value();
	}

	@Override
	public String jInstruction() {
		return JOperation.MUL.toString();
	}

	@Override
	public String toString() {
		return String.format("(%s * %s)", left(), right());
	}
}
