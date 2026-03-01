package online.vonarx.hslu.ad.e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciIterativeTest {

    @Test
    public void testIllegal() {
        final var e = assertThrows(IllegalArgumentException.class, () -> FibonacciIterative.fiboIter(-1));
        assertEquals("n must be equal or greater than 0, got -1", e.getMessage());
    }

    @Test
    public void test0() {
        assertEquals(0, FibonacciIterative.fiboIter(0));
    }

    @Test
    public void test1() {
        assertEquals(1, FibonacciIterative.fiboIter(1));
    }

    @Test
    public void test2() {
        assertEquals(1, FibonacciIterative.fiboIter(2));
    }

    @Test
    public void test14() {
        assertEquals(377, FibonacciIterative.fiboIter(14));
    }
}
