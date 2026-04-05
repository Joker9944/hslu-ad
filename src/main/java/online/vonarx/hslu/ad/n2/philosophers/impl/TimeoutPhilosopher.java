package online.vonarx.hslu.ad.n2.philosophers.impl;

import lombok.SneakyThrows;
import online.vonarx.hslu.ad.n2.philosophers.Fork;
import online.vonarx.hslu.ad.n2.philosophers.Philosopher;
import online.vonarx.hslu.ad.n2.philosophers.PhilosopherRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TimeoutPhilosopher extends Philosopher<ReentrantLock> {
	public static final int TIMEOUT = 5;

	public TimeoutPhilosopher(final String name, final ReentrantLock left, final ReentrantLock right) {
		super(name, left, right);
	}

	public static void main(final String[] args) throws ExecutionException, InterruptedException {
		PhilosopherRunner.run(PHILOSOPHER_NAMES, Fork::generateReentrantLocks, TimeoutPhilosopher::new);
	}

	@Override
	@SneakyThrows
	public void eat() {
		final var start = currentTimeMillis();
		if (!left().tryLock(TIMEOUT, MILLISECONDS)) {
			print("done waiting for left fork...");
			return;
		}
		try {
			if (!right().tryLock(TIMEOUT, MILLISECONDS)) {
				print("done waiting for right fork...");
				return;
			}
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
