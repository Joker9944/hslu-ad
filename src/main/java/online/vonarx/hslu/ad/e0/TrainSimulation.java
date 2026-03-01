package online.vonarx.hslu.ad.e0;

public final class TrainSimulation {

	public static void main(String[] args) {
		final var wagon1 = new Wagon("W001", 60);
		final var wagon2 = new Wagon("W002", 40);
		final var wagon3 = new Wagon("W003", 80);

		wagon1.next(wagon2);
		wagon2.next(wagon3);

		System.out.println(calcTotalCapacity(wagon1));
	}

	private static int calcTotalCapacity(final Wagon wagon) {
		var next = wagon;
		var totalCapacity = wagon.capacity();

		while (next.next() != null) {
			next = next.next();
			totalCapacity += next.capacity();
		}

		return totalCapacity;
	}
}
