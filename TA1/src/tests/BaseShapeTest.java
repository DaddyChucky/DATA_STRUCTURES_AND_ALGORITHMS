package tests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import tp1.BaseShape;
import tp1.Point2d;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BaseShapeTest {

    @Test
    public void defaultCtorCreatesEmptyCoords() {
        BaseShape empty = new BaseShape();
        assertEquals(0, empty.getCoords().size());
    }

    @Test
    void ctorCreatesNewCoords() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(1, notEmpty.getCoords().size());
        assertSame(tmp.get(0), notEmpty.getCoords().iterator().next());
        assertNotSame(tmp, notEmpty.getCoords());
    }

    @Test
    void addPoint() {
        BaseShape shape = new BaseShape();
        Point2d point = new Point2d(1.0, 0.0);
        shape.add(point);
        assertSame(point, shape.getCoords().iterator().next());
    }

    @Test
    void addShape() {
        BaseShape shape1 = new BaseShape();
        BaseShape shape2 = new BaseShape();
        Point2d point = new Point2d(1.0, 0.0);
        shape1.add(point);
        shape2.add(shape1);
        assertSame(shape1.getCoords().iterator().next(), shape2.getCoords().iterator().next());
    }

    @Test
    void addAll() {
        List<Point2d> list = new ArrayList<>();
        list.add(new Point2d(1.0, 0.0));
        list.add(new Point2d(2.0, 0.0));
        BaseShape shape = new BaseShape();
        shape.addAll(list);
        assertEquals(list.size(), shape.getCoords().size());
        int i = 0;
        for (Point2d point : shape.getCoords()) {
            assertSame(point, list.get(i++));
        }
    }

    @Test
    void removePoint() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        BaseShape notEmpty = new BaseShape(tmp);
        notEmpty.remove(new Point2d(1.0, 0.0));
        assertEquals(0, notEmpty.getCoords().size());
    }

    @Test
    void removeShape() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        BaseShape shape1 = new BaseShape(tmp);
        BaseShape shape2 = new BaseShape(tmp);
        shape1.remove(shape2);
        assertEquals(0, shape1.getCoords().size());
    }

    @Test
    void removeAll() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(1.0, 2.0));
        List<Point2d> toRemove = new ArrayList<>();
        toRemove.add(new Point2d(1.0, 0.0));
        BaseShape shape1 = new BaseShape(tmp);
        shape1.removeAll(toRemove);
        assertEquals(1, shape1.getCoords().size());
        assertEquals(new Point2d(1.0, 2.0), shape1.getCoords().iterator().next());
    }

    @Test
    void getCoords() {
        List<Point2d> tmp = new ArrayList<>();
        Point2d point = new Point2d(1.0, 0.0);
        tmp.add(point);
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(1, notEmpty.getCoords().size());
        assertSame(point, notEmpty.getCoords().iterator().next());
    }

    @Test
    void getCoordsDeepCopy() {
        List<Point2d> tmp = new ArrayList<>();
        Point2d point = new Point2d(1.0, 0.0);
        tmp.add(point);
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(1, notEmpty.getCoordsDeepCopy().size());
        assertNotSame(point, notEmpty.getCoordsDeepCopy().iterator().next());
    }

    @Test
    void translate() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 2.0));
        List<Point2d> answer = new ArrayList<>();
        answer.add(new Point2d(2.0, 1.0));
        answer.add(new Point2d(3.0, 3.0));
        BaseShape notEmpty = new BaseShape(tmp);
        notEmpty.translate(new Point2d(1.0, 1.0));
        assertEquals(answer, notEmpty.getCoords());
    }

    @Test
    void rotate() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 2.0));
        List<Point2d> answer = new ArrayList<>();
        answer.add(new Point2d(0.0, 1.0));
        answer.add(new Point2d(-2.0, 2.0));
        BaseShape notEmpty = new BaseShape(tmp);
        notEmpty.rotate(Math.toRadians(90));
        assertEquals(answer, notEmpty.getCoords());
    }

    @Test
    void getMaxX() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 3.0));
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(2.0, notEmpty.getMaxX());
    }

    @Test
    void getMaxY() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 3.0));
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(3.0, notEmpty.getMaxY());
    }

    @Test
    void getMaxCoord() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 3.0));
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(new Point2d(2.0, 3.0), notEmpty.getMaxCoord());
    }

    @Test
    void getMinX() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 3.0));
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(1.0, notEmpty.getMinX());
    }

    @Test
    void getMinY() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 3.0));
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(0.0, notEmpty.getMinY());
    }

    @Test
    void getMinCoord() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 3.0));
        BaseShape notEmpty = new BaseShape(tmp);
        assertEquals(new Point2d(1.0, 0.0), notEmpty.getMinCoord());
    }

    @Test
    void testClone() {
        List<Point2d> tmp = new ArrayList<>();
        tmp.add(new Point2d(1.0, 0.0));
        tmp.add(new Point2d(2.0, 3.0));
        BaseShape notEmpty = new BaseShape(tmp);
        assertNotSame(notEmpty.clone(), notEmpty);
        assertNotSame(notEmpty.clone().getCoords(), notEmpty.getCoords());
    }
}