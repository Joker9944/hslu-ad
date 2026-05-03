package online.vonarx.hslu.ad.n4.shop;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class OrderProcess {

	public static void main(String[] args) {
		final var process = new OrderProcess();

		final var order = new Order();

		try (final var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			final var checkInventoryAsync = CompletableFuture.supplyAsync(() -> process.checkInventory(order), executor);
			CompletableFuture.supplyAsync(() -> process.calculateShippingCosts(order), executor)
					.thenApplyAsync(shippingCost -> process.validatePayment(order, shippingCost), executor)
					.thenAcceptBoth(checkInventoryAsync, (paymentValid, inStock) -> {
				if (!(paymentValid && inStock)) System.out.println("Order not valid");
				else
					CompletableFuture.supplyAsync(() -> process.confirmOrder(order), executor)
							.thenAccept(confirmed -> System.out.println(confirmed ? "Order confirmed" : "Could not confirm order"));
			}).join();
		}
	}

	/**
	 * Task A
	 *
	 * @param order
	 * @return
	 */
	private boolean checkInventory(final Order order) {
		return true;
	}

	/**
	 * Task B
	 *
	 * @param order
	 * @param shippingCosts
	 * @return
	 */
	private boolean validatePayment(final Order order, int shippingCosts) {
		return true;
	}

	/**
	 * Task C
	 *
	 * @param order
	 * @return
	 */
	private int calculateShippingCosts(final Order order) {
		return 100;
	}

	/**
	 * Task D
	 *
	 * @param order
	 * @return
	 */
	private boolean confirmOrder(final Order order) {
		return true;
	}
}
