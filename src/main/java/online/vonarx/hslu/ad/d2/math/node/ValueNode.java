package online.vonarx.hslu.ad.d2.math.node;

import online.vonarx.hslu.ad.d1.j.JOperation;
import org.jspecify.annotations.NonNull;

public record ValueNode(int value) implements MathNode {

	@Override
	@NonNull
	public String toString() {
		return Integer.toString(value);
	}

	@Override
	public String jInstruction() {
		return String.format("%s %s", JOperation.LOAD, value);
	}
}
