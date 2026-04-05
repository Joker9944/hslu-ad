package online.vonarx.hslu.ad.n2.philosophers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

@RequiredArgsConstructor
public abstract class Philosopher<F> {
	public static final List<String> PHILOSOPHER_NAMES = List.of("Homer", "Socrates", "Plato", "Aristotle", "Diogenes");

	public static final int RANDOM_BOUND = 21; // random number 0 to 200
	public static final int RANDOM_OFFSET_THINKING = 10;
	public static final int RANDOM_OFFSET_EATING = 30;

	public static final int MAX_EAT_COUNT = 10;

	private final Random random = new Random();

	@Getter(AccessLevel.PROTECTED) private final String name;
	@Getter(AccessLevel.PROTECTED) private final F left;
	@Getter(AccessLevel.PROTECTED) private final F right;

	private int eatCount = 0;

	public void think() throws InterruptedException {
		final var millis = nextMillis(RANDOM_OFFSET_THINKING);
		printThink(millis);
		sleep(millis);
	}

	public abstract void eat() throws InterruptedException;

	protected void eat(final long delta) throws InterruptedException {
		final var millis = nextMillis(RANDOM_OFFSET_EATING);
		printEat(millis, delta);
		sleep(millis);
		eatCount++;
	}

	public boolean finishedEating() {
		return eatCount >= MAX_EAT_COUNT;
	}

	protected long nextMillis(final int offset) {
		return random.nextInt(RANDOM_BOUND) + offset;
	}

	protected void print(final String format, final Object... args) {
		final var modifiedArgs = new Object[args.length + 1];
		modifiedArgs[0] = name;
		System.arraycopy(args, 0, modifiedArgs, 1, args.length);
		System.out.printf("[%s] " + format + "%n", modifiedArgs);
	}

	protected void printThink(final long millis) {
		print("thinking very hard for %d", millis);
	}

	protected void printEat(final long millis, final long delta) {
		print("lecker, Spaghetti for %d, waited for %d", millis, delta);
	}
}
