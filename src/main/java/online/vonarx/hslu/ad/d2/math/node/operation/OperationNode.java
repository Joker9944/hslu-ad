package online.vonarx.hslu.ad.d2.math.node.operation;

import lombok.Data;
import online.vonarx.hslu.ad.d2.math.node.Node;

@Data
public abstract class OperationNode implements Node {

	private final Node left;
	private final Node right;
}
