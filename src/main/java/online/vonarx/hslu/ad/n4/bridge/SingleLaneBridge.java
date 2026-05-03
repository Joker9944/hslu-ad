package online.vonarx.hslu.ad.n4.bridge;

public class SingleLaneBridge {

	private final int capacity;
	private final Object lock = new Object();
	private Direction currentDirection = Direction.NORTH;
	private int cars = 0;

	public SingleLaneBridge(final int capacity) {
		this.capacity = capacity;
	}

	public void enter(Direction direction) throws InterruptedException {
		synchronized (lock) {
			while (cars >= capacity || (cars > 0 && !currentDirection.equals(direction))) {
				System.out.println("Waiting to enter from " + direction.name());
				lock.wait();
			}
			System.out.println("Entering from " + direction.name());
			cars++;
			currentDirection = direction;
			lock.notifyAll();
		}
	}

	public void leave(Direction direction) throws InterruptedException {
		synchronized (lock) {
			while (cars <= 0 && !currentDirection.equals(direction)) {
				System.out.println("Waiting to leave from " + direction.name());
				lock.wait();
			}
			System.out.println("Leaving from " + direction.name());
			cars--;
			lock.notifyAll();
		}
	}

	public enum Direction {
		NORTH, SOUTH
	}
}
