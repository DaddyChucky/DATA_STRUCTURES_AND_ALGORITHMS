package tests;

import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;
import tp1.BaseShape;
import tp1.Point2d;
import tp1.PointOperator;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @Test
    void translate() {
        Double[][] rawVector = { {0.0, 0.0, 0.0} };
        Double[][] rawTranslate = { {1.0, 2.0, 3.0} };
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix translate = new SimpleMatrix(Tester.toPrimitive(rawTranslate));
        SimpleMatrix correctOutput = vector.plus(translate);
        PointOperator.translate(rawVector[0], rawTranslate[0]);
        Tester.isEqual(correctOutput, rawVector[0]);
    }

    @Test
    void rotate() {
        Double[][] rawVector = { {1.0, 2.0, 3.0} };
        Double[][] rawRotate = {
                {1.0, 2.0, 0.0},
                {3.0, 4.0, 0.0},
                {0.0, 0.0, 1.0}
        };
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix rotate = new SimpleMatrix(Tester.toPrimitive(rawRotate));
        SimpleMatrix correctOutput = rotate.mult(vector.transpose()).transpose();
        PointOperator.rotate(rawVector[0], rawRotate);
        Tester.isEqual(correctOutput, rawVector[0]);
    }

    @Test
    void divide() {
        Double[][] rawVector = { {1.0, 2.0, 3.0} };
        Double scale = 5.0;
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix correctOutput = vector.divide(scale);
        PointOperator.divide(rawVector[0], scale);
        Tester.isEqual(correctOutput, rawVector[0]);
    }

    @Test
    void multiply() {
        Double[][] rawVector = { {1.0, 2.0, 3.0} };
        Double scale = 5.0;
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix correctOutput = vector.scale(scale);
        PointOperator.multiply(rawVector[0], scale);
        Tester.isEqual(correctOutput, rawVector[0]);
    }

    @Test
    void add() {
        Double[][] rawVector = { {1.0, 2.0, 3.0} };
        Double scale = 5.0;
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix correctOutput = vector.plus(scale);
        PointOperator.add(rawVector[0], scale);
        Tester.isEqual(correctOutput, rawVector[0]);
    }

    @Test
    void getMaxCoord() {
        List<Point2d> coords = new ArrayList<>();
        coords.add(new Point2d(1.0, 2.0));
        coords.add(new Point2d(-1.0, 5.0));
        coords.add(new Point2d(10.0, 0.0));
        BaseShape tmp = new BaseShape(coords);
        assertEquals(new Point2d(10.0, 5.0), new Point2d(tmp.getMaxX(), tmp.getMaxY()));
    }

    @Test
    void getMinCoord() {
        List<Point2d> coords = new ArrayList<>();
        coords.add(new Point2d(1.0, 2.0));
        coords.add(new Point2d(-1.0, 5.0));
        coords.add(new Point2d(10.0, 0.0));
        BaseShape tmp = new BaseShape(coords);
        assertEquals(new Point2d(-1.0, 0.0), new Point2d(tmp.getMinX(), tmp.getMinY()));
    }

    @Test
    void pointConstructor() {
        Double[] data = { 1.0, 2.0 };
        Point2d test = new Point2d(data);
        data[0] = 0.0;
        assertEquals(test.X(), 1.0);
    }

    @Test
    void methodsChangeInternal() {
        Double[] data = { 1.0, 2.0 };
        Point2d point = new Point2d(data);
        Point2d test1 = new Point2d(data).rotate(1.0).translate(new Double[] { 1.0, 1.0}).divide(5.0).multiply(3.5).add(22.0);
        Point2d test2 = new Point2d(data).rotate(1.0).translate(new Double[] { 1.0, 1.0}).divide(5.0).multiply(3.5).add(22.0);
        assertEquals(test1, test2);
    }

    @Test
    void rotatePoint() {
        Double[][] rawVector = { {1.0, 5.0} };
        Double[][] rawRotate = {
                {0.0, -1.0},
                {1.0, 0.0},
        };
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix rotate = new SimpleMatrix(Tester.toPrimitive(rawRotate));
        SimpleMatrix correctOutput = rotate.mult(vector.transpose()).transpose();
        Point2d output = new Point2d(rawVector[0]).rotate(Math.toRadians(90));
        Tester.isEqual(correctOutput, new Double[] {output.X(), output.Y()});
    }

    @Test
    void translatePoint() {
        Double[][] rawVector = { {0.0, 0.0} };
        Double[][] rawTranslate = { {1.0, 2.0} };
        SimpleMatrix vector = new SimpleMatrix(Tester.toPrimitive(rawVector));
        SimpleMatrix translate = new SimpleMatrix(Tester.toPrimitive(rawTranslate));
        SimpleMatrix correctOutput = vector.plus(translate);
        Point2d output = new Point2d(rawVector[0]).translate(new Point2d(rawTranslate[0]));
        Tester.isEqual(correctOutput, new Double[] {output.X(), output.Y()});
    }

    @Test
    void cloneTest() {
        Point2d test = new Point2d(0.0, 0.0);
        assertNotSame(test, test.clone());
    }
}
