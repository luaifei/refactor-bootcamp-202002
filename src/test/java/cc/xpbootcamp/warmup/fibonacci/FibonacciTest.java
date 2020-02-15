package cc.xpbootcamp.warmup.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FibonacciTest {
    @Test
    void should_return_1_when_calculate_given_position_is_1() {
        Fibonacci fibonacci = new Fibonacci();
        assertEquals(1L, fibonacci.get(1));
    }

    @Test
    void should_return_1_when_calculate_given_position_is_2() {
        Fibonacci fibonacci = new Fibonacci();
        assertEquals(1L, fibonacci.get(2));
    }

    @Test
    void should_return_2_when_calculate_given_position_is_3() {
        Fibonacci fibonacci = new Fibonacci();
        assertEquals(2L, fibonacci.get(3));
    }

    @Test
    void should_return_3_when_calculate_given_position_is_4() {
        Fibonacci fibonacci = new Fibonacci();
        assertEquals(3L, fibonacci.get(4));
    }
}
