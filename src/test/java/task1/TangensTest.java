package task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TangensTest {

    @Test
    void testBasicValues() {
        assertEquals(Math.tan(0), Tangens.tgSeries(0, 6), 0.0001);
        assertEquals(Math.tan(0.1), Tangens.tgSeries(0.1, 6), 0.0001);
        assertEquals(Math.tan(-0.1), Tangens.tgSeries(-0.1, 6), 0.0001);
        assertEquals(Math.tan(0.5), Tangens.tgSeries(0.5, 6), 0.001);
        assertEquals(Math.tan(-0.5), Tangens.tgSeries(-0.5, 6), 0.001);
        assertEquals(Math.tan(0.000001), Tangens.tgSeries(0.000001, 6), 0.0001);
        assertEquals(Math.tan(-0.000001), Tangens.tgSeries(-0.000001, 6), 0.0001);
        assertEquals(Math.tan(Math.PI/4),  Tangens.tgSeries(Math.PI/4, 6), 0.001);
    }

    @Test
    void testUndefinedPoint() {
        assertTrue(Double.isNaN(Tangens.tgSeries(Math.PI / 2, 6)));
        assertTrue(Double.isNaN(Tangens.tgSeries(3 * Math.PI / 2, 6)));
    }

    @Test
    void testRandomValues() {
        for (int i = 0; i < 10000; i++) {

            double x = Math.random() * 2 - 1;

            double expected = Math.tan(x);
            double actual = Tangens.tgSeries(x, 6);

            assertEquals(expected, actual, 0.01);
        }
    }
}