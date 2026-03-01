package online.vonarx.hslu.ad.e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FibonacciTest {

    @Test
    public void testIllegal() {
        final var e = assertThrows(IllegalArgumentException.class, () -> Fibonacci.fiboRec1(-1));
        assertEquals("n must be equal or greater than 0, got -1", e.getMessage());
    }

    @Test
    public void test0() {
        assertEquals(0, Fibonacci.fiboRec1(0));
    }

    @Test
    public void test1() {
        assertEquals(1, Fibonacci.fiboRec1(1));
    }

    @Test
    public void test2() {
        assertEquals(1, Fibonacci.fiboRec1(2));
    }

    @Test
    public void test14() {
        assertEquals(377, Fibonacci.fiboRec1(14));
    }
}