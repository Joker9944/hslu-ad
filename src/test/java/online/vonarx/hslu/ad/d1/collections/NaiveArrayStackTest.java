package online.vonarx.hslu.ad.d1.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class NaiveArrayStackTest {

	public NaiveStack<String> emptyStack;
	public NaiveStack<String> filledStack;
	public NaiveStack<String> fullStack;

	@BeforeEach
	public void setup() {
		emptyStack = new NaiveArrayStack<>();
		filledStack = new NaiveArrayStack<>();
		filledStack.push("1");
		filledStack.push("2");
		filledStack.push("3");
		fullStack = new NaiveArrayStack<>(1);
		fullStack.push("full");
	}

	@Test
	public void popEmpty() {
		assertThrows(EmptyStackException.class, emptyStack::pop);
	}

	@Test
	public void popNotEmpty() {
		assertDoesNotThrow(filledStack::pop);
	}

	@Test
	public void pushFull() {
		assertTrue(fullStack.full());
		assertThrows(FullStackException.class, () -> fullStack.push("overflow"));
	}

	@Test
	public void pushAndPop() {
		emptyStack.push("test");
		assertEquals("test", emptyStack.pop());
	}
}
