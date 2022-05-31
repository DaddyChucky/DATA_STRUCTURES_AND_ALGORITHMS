package tests;

import org.ejml.simple.SimpleMatrix;

import java.util.Collections;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public final class Tester {

    public static double[][] toPrimitive(Double[][] input) {
        double[][] output = new double[input.length][input[0].length];
        for (int i = 0; i < output.length; ++i) {
            for (int j = 0; j < output[i].length; ++j) {
                output[i][j] = input[i][j];
            }
        }
        return output;
    }

    public static void isEqual(SimpleMatrix first, Double[] second) {
        for (int i = 0; i < second.length; ++i) {
            assertEquals(first.get(0, i), second[i], 0.001);
        }
    }
}
