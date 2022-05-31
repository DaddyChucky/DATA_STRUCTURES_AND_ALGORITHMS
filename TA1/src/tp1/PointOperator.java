package tp1;

import java.util.*;

public final class PointOperator {
    public static void translate(Double[] vector, Double[] translateVector) {
        for (int i = 0; i < translateVector.length; ++i)
            vector[i] += translateVector[i];
    }

    public static void rotate(Double[] vector, Double[][] rotationMatrix) {
        Double[] tempVector = Arrays.copyOf(vector, vector.length);
        Arrays.fill(vector, 0.0);

        for (int i = 0; i < rotationMatrix.length; ++i)
            for (int j = 0; j < rotationMatrix[i].length; ++j)
                vector[i] += rotationMatrix[i][j] * tempVector[j];
    }

    public static void divide(Double[] vector, Double divider) {
        for (int i = 0; i < vector.length; ++i)
            vector[i] /= divider;
    }

    public static void multiply(Double[] vector, Double multiplier) {
        for (int i = 0; i < vector.length; ++i)
            vector[i] *= multiplier;
    }

    public static void add(Double[] vector, Double adder) {
        for (int i = 0; i < vector.length; ++i)
            vector[i] += adder;
    }
}
