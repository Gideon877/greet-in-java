package greet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTest {

    @Test
    void addNumbers() {
        Calculate calculate = new Calculate();

        assertEquals(calculate.add(1, 2), 3);
    }

}