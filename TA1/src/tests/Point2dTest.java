package tests;

import org.junit.jupiter.api.Test;
import tp1.Point2d;

import static org.junit.jupiter.api.Assertions.*;

public class Point2dTest {

    @Test
    void ctorSplit() {
        Point2d point = new Point2d(1.0, 2.0);
        assertEquals(point.X(), 1.0);
        assertEquals(point.Y(), 2.0);
    }

    @Test
    void ctorVector() {
        Point2d point = new Point2d(new Double[] {1.0, 2.0});
        assertEquals(point.X(), 1.0);
        assertEquals(point.Y(), 2.0);
    }

    @Test
    void translatePoint() {
        Point2d point = new Point2d(1.0, 2.0);
        point.translate(new Point2d(1.0, 1.0));
        assertEquals(point, new Point2d(2.0, 3.0));
    }

    @Test
    void translateDouble() {
        Point2d point = new Point2d(1.0, 2.0);
        point.translate(new Double[] {1.0, 1.0});
        assertEquals(point, new Point2d(2.0, 3.0));
    }

    @Test
    void rotateDouble() {
        Point2d point = new Point2d(1.0, 2.0);
        point.rotate(Math.toRadians(90));
        assertEquals(point, new Point2d(-2.0, 1.0));
    }

    @Test
    void divide() {
        Point2d point = new Point2d(1.0, 2.0);
        point.divide(2.0);
        assertEquals(point, new Point2d(0.5, 1.0));
    }

    @Test
    void multiply() {
        Point2d point = new Point2d(1.0, 2.0);
        point.multiply(2.0);
        assertEquals(point, new Point2d(2.0, 4.0));
    }

    @Test
    void add() {
        Point2d point = new Point2d(1.0, 2.0);
        point.add(2.0);
        assertEquals(point, new Point2d(3.0, 4.0));
    }

    @Test
    void testClone() {
        Point2d point = new Point2d(1.0, 2.0);
        assertEquals(point, point.clone());
        assertNotSame(point, point.clone());
    }
}