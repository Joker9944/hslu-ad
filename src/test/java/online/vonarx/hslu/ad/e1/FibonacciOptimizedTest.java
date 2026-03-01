package online.vonarx.hslu.ad.e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciOptimizedTest {

    @Test
    public void testIllegal() {
        final var e = assertThrows(IllegalArgumentException.class, () -> FibonacciOptimized.fiboRec2(-1));
        assertEquals("n must be equal or greater than 0, got -1", e.getMessage());
    }

    @Test
    public void test0() {
        assertEquals(0, FibonacciOptimized.fiboRec2(0));
    }

    @Test
    public void test1() {
        assertEquals(1, FibonacciOptimized.fiboRec2(1));
    }

    @Test
    public void test2() {
        assertEquals(1, FibonacciOptimized.fiboRec2(2));
    }

    @Test
    public void test14() {
        assertEquals(377, FibonacciOptimized.fiboRec2(14));
    }
}