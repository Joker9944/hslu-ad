package online.vonarx.hslu.ad.n2.philosophers.impl;

import online.vonarx.hslu.ad.n2.philosophers.Fork;
import online.vonarx.hslu.ad.n2.philosophers.Philosopher;
import online.vonarx.hslu.ad.n2.philosophers.PhilosopherRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.currentTimeMillis;

public class FairPhilosopher extends Philosopher<ReentrantLock> {

	public FairPhilosopher(final String name, final ReentrantLock left, final ReentrantLock right) {
		super(name, left, right);
	}

	public static void main(final String[] args) throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, Fork::generateFairReentrantLocks, FairPhilosopher::new);
	}

	@Override
	public void eat() throws InterruptedException {
		final var start = currentTimeMillis();
		left().lock();
		try {
			right().lock();
			try {
				final var delta = currentTimeMillis() - start;
				eat(delta);
			} finally {
				right().unlock();
			}
		} finally {
			left().unlock();
		}
	}
}
