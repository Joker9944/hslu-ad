package online.vonarx.hslu.ad.d2.math.node.operation;

import lombok.Data;
import online.vonarx.hslu.ad.d2.math.node.MathNode;

@Data
public abstract class OperationNode implements MathNode {

	private final MathNode left;
	private final MathNode right;
}
