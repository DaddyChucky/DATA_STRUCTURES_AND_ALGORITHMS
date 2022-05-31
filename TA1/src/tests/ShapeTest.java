package tests;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;
import tp1.*;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {
    @Test
    void shapeConstructor() {
        Collection<Point2d> Collection = new ArrayList<>();
        Collection.add(new Point2d(0.0, 0.0));
        BaseShape shape = new BaseShape(Collection);
        Collection.add(new Point2d(1.0, 0.0));
        assertNotEquals(shape.getCoords().size(), Collection.size());
    }

    @Test
    void shapeCoords() {
        BaseShape shape = new BaseShape();
        Collection<Point2d> Collection = shape.getCoords();
        Collection.add(new Point2d(1.0, 0.0));
        assertNotEquals(shape.getCoords().size(), Collection.size());
    }

    @Test
    void translate() {
        Double[][] rawVector = { {0.0, 0.0} };
        Double[][] rawTranslate = { {1.0, 2.0} };
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix translate = new SimpleMatrix(Tester.toPrimitive(rawTranslate));
        SimpleMatrix correctOutput = vector.plus(translate);
        List<Point2d> list = new ArrayList<>();
        list.add(new Point2d(rawVector[0][0], rawVector[0][1]));
        BaseShape output = new BaseShape(list).translate(new Point2d(rawTranslate[0][0], rawTranslate[0][1]));
        Point2d point = output.getCoords().iterator().next();
        Tester.isEqual(correctOutput, new Double[] { point.X(), point.Y() });
    }

    @Test
    void rotate() {
        Double[][] rawVector = { {1.0, 5.0} };
        Double[][] rawRotate = {
                {0.0, -1.0},
                {1.0, 0.0},
        };
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix rotate = new SimpleMatrix(Tester.toPrimitive(rawRotate));
        SimpleMatrix correctOutput = rotate.mult(vector.transpose()).transpose();
        List<Point2d> list = new ArrayList<>();
        list.add(new Point2d(rawVector[0][0], rawVector[0][1]));
        BaseShape output = new BaseShape(list).rotate(Math.toRadians(90));
        Point2d point = output.getCoords().iterator().next();
        Tester.isEqual(correctOutput, new Double[] { point.X(), point.Y() });
    }

    @Test
    void squareRectangle() {
        Square square = new Square(5.0);
        Rectangle rectangle = new Rectangle(5.0, 5.0);
        rectangle.remove(square);
        assertEquals(rectangle.getCoords().size(), 0.0);
    }

    @Test
    void circleEllipse() {
        Circle circle = new Circle(5.0);
        Ellipse ellipse = new Ellipse(5.0, 5.0);
        ellipse.remove(circle);
        assertEquals(ellipse.getCoords().size(), 0.0);
    }

    @Test
    void ellipseDimCtor() {
        Ellipse ellipse = new Ellipse(new Point2d(5.0, 5.0));
        assertTrue(ellipse.getCoords().size() >= 16);
    }

    @Test
    void rectangleDimCtor() {
        BaseShape rect = new Rectangle(new Point2d(5.0, 5.0));
        assertTrue(rect.getCoords().size() >= 25);
    }

    @Test
    void rotatingEllipse() {
        Ellipse ellipse = new Ellipse(new Point2d(3.0, 2.0));
        ellipse.rotate(Math.toRadians(90));
        assertTrue(ellipse.getCoords().size() > 1,
                "Il vous manque probablement de points dans votre ellipse");
        assertNotEquals(ellipse.getCoords(), new Ellipse(new Point2d(3.0, 2.0)));
    }
}
