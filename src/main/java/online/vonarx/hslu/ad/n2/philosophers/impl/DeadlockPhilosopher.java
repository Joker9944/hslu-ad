package online.vonarx.hslu.ad.n2.philosophers.impl;

import online.vonarx.hslu.ad.n2.philosophers.Fork;
import online.vonarx.hslu.ad.n2.philosophers.Philosopher;
import online.vonarx.hslu.ad.n2.philosophers.PhilosopherRunner;

import java.util.concurrent.ExecutionException;

import static java.lang.System.currentTimeMillis;

public class DeadlockPhilosopher extends Philosopher<Fork> {

	public DeadlockPhilosopher(final String name, final Fork left, final Fork right) {
		super(name, left, right);
	}

	public static void main(final String[] args) throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, Fork::generateForks, DeadlockPhilosopher::new);
	}

	@Override
	public void eat() throws InterruptedException {
		final var start = currentTimeMillis();
		synchronized (left()) {
			synchronized (right()) {
				final var delta = currentTimeMillis() - start;
				eat(delta);
			}
		}
	}
}
