package online.vonarx.hslu.ad.n2.philosophers.impl;

import online.vonarx.hslu.ad.n2.philosophers.Fork;
import online.vonarx.hslu.ad.n2.philosophers.PhilosopherRunner;

import java.util.concurrent.ExecutionException;

public class OrderPhilosopher extends DeadlockPhilosopher {

	public OrderPhilosopher(final String name, final Fork left, final Fork right) {
		super(name,
				left.n() < right.n() ? left : right,
				left.n() > right.n() ? left : right);
	}

	public static void main(final String[] args) throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, Fork::generateForks, OrderPhilosopher::new);
	}
}
