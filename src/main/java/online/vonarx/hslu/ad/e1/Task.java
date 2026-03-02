package online.vonarx.hslu.ad.e1;

public class Task {

	private int countTask1 = 0;
	private int countTask2 = 0;
	private int countTask3 = 0;

	public void task(final int n) {
		// task1 => 4 => O(1)
		task1();
		task1();
		task1();
		task1();
		for (int i = 0; i < n; i++) {
			// task2 => 3 * n => O(n)
			task2();
			task2();
			task2();
			for (int j = 0;j < n; j++) {
				// task3 => 2 * n * n => O(n^2)
				task3();
				task3();
			}
		}
		System.out.printf("task1 total count: %s%n", countTask1);
		System.out.printf("task1 calls per iteration %s%n", countTask1 / 4);
		System.out.printf("task2 count: %s%n", countTask2);
		System.out.printf("task2 calls per iteration %s%n", countTask2 / n / 3);
		System.out.printf("task3 count: %s%n", countTask3);
		System.out.printf("task3 calls per iteration %s%n", countTask3 / n / 2);
	}

	public void task1() {
		countTask1++;
	}

	public void task2() {
		countTask2++;
	}

	public void task3() {
		countTask3++;
	}
}
