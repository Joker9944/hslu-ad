package online.vonarx.hslu.ad.n4.barrier;

import java.util.concurrent.Semaphore;

public class Barrier {

	private final int size;
	private int count = 0;
	private final Semaphore barrier = new Semaphore(0);
	private final Object lock = new Object();

	public Barrier(final int size) {
		this.size = size;
	}

	public void waitForThreads() throws InterruptedException {
		boolean isLastThread;
		synchronized (lock) {
			count++;
			isLastThread = count == size;
		}
		if (isLastThread) barrier.release(size - 1);
		else barrier.acquire();
	}
}
