package tests;

import org.junit.jupiter.api.Test;
import tp1.Point2d;
import tp1.PointOperator;

import static org.junit.jupiter.api.Assertions.*;

public class PointOperatorTest {

    void assertEqualsDoubles(Double[] vector1, Double[] vector2) {
        for (int i = 0; i < vector1.length; ++i) {
            assertEquals(vector1[i], vector2[i], 0.001);
        }
    }

    @Test
    void translate() {
        Double[] point = new Double[] {1.0, 2.0};
        PointOperator.translate(point, new Double[] {1.0, 1.0});
        assertEqualsDoubles(point, new Double[] {2.0, 3.0});
    }

    @Test
    void rotate() {
        Double[] point = new Double[] {1.0, 2.0};
        PointOperator.rotate(point, new Double[][] {{1.0, 0.0}, {1.0, 0.0}});
        assertEqualsDoubles(point, new Double[] {1.0, 1.0});
    }

    @Test
    void divide() {
        Double[] point = new Double[] {1.0, 2.0};
        PointOperator.divide(point, 2.0);
        assertEqualsDoubles(point, new Double[] {0.5, 1.0});
    }

    @Test
    void multiply() {
        Double[] point = new Double[] {1.0, 2.0};
        PointOperator.multiply(point, 2.0);
        assertEqualsDoubles(point, new Double[] {2.0, 4.0});
    }

    @Test
    void add() {
        Double[] point = new Double[] {1.0, 2.0};
        PointOperator.add(point, 2.0);
        assertEqualsDoubles(point, new Double[] {3.0, 4.0});
    }
}