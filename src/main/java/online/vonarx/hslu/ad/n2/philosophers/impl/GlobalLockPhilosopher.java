package online.vonarx.hslu.ad.n2.philosophers.impl;

import online.vonarx.hslu.ad.n2.philosophers.Fork;
import online.vonarx.hslu.ad.n2.philosophers.Philosopher;
import online.vonarx.hslu.ad.n2.philosophers.PhilosopherRunner;

import java.util.concurrent.ExecutionException;

import static java.lang.System.currentTimeMillis;

public class GlobalLockPhilosopher extends Philosopher<Fork> {

	private static final Object lock = new Object();

	public GlobalLockPhilosopher(final String name, final Fork left, final Fork right) {
		super(name, left, right);
	}

	public static void main(final String[] args) throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, Fork::generateForks, GlobalLockPhilosopher::new);
	}

	@Override
	public void eat() throws InterruptedException {
		final var start = currentTimeMillis();
		synchronized (lock) {
			final var delta = currentTimeMillis() - start;
			eat(delta);
		}
	}
}
