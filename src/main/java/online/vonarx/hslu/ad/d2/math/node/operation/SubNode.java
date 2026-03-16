package online.vonarx.hslu.ad.d2.math.node.operation;

import online.vonarx.hslu.ad.d1.j.JOperation;
import online.vonarx.hslu.ad.d2.math.node.MathNode;

@SuppressWarnings("unused")
public class SubNode extends OperationNode {

	public SubNode(MathNode left, MathNode right) {
		super(left, right);
	}

	@Override
	public int value() {
		return left().value() - right().value();
	}

	@Override
	public String jInstruction() {
		return JOperation.DIV.toString();
	}

	@Override
	public String toString() {
		return String.format("(%s - %s)", left(), right());
	}
}
