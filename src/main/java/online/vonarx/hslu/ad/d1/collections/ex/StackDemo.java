package online.vonarx.hslu.ad.d1.collections.ex;

import online.vonarx.hslu.ad.d1.collections.NaiveArrayStack;

public class StackDemo {

	public static void main(String[] args) {
		final var stack = new NaiveArrayStack<String>();
		stack.push("toll");
		stack.push(" sind ");
		stack.push("Datenstrukturen");
		System.out.print(stack.pop());
		System.out.print(stack.pop());
		System.out.println(stack.pop());
	}
}
