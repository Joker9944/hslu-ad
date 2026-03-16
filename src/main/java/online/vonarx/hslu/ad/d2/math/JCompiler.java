package online.vonarx.hslu.ad.d2.math;

import lombok.NoArgsConstructor;
import online.vonarx.hslu.ad.d1.j.JOperation;
import online.vonarx.hslu.ad.d2.math.node.MathNode;
import online.vonarx.hslu.ad.d2.math.node.operation.OperationNode;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class JCompiler {

	public static String compile(final MathNode root) {
		final var jBuilder = new StringBuilder();
		compileRec(jBuilder, root);
		jBuilder.append(JOperation.PRINT);
		return jBuilder.toString();
	}

	private static void compileRec(final StringBuilder jBuilder, final MathNode node) {
		if (node instanceof OperationNode opNode) {
			compileRec(jBuilder, opNode.left());
			compileRec(jBuilder, opNode.right());
		}
		jBuilder.append(node.jInstruction()).append("\n");
	}
}
