package tp1;

public class Point2d extends AbstractPoint {
    private final Integer X = 0;
    private final Integer Y = 1;

    public Point2d(Double x, Double y) { super(new Double[] { x, y }); }

    public Point2d(Double[] vector) { super(new Double[] { vector[0], vector[1] }); }

    public Double X() { return vector[X]; }

    public Double Y() { return vector[Y]; }

    @Override
    public Point2d translate(Double[] translateVector) {
        PointOperator.translate(vector, translateVector);
        return this;
    }

    public Point2d translate(Point2d translateVector) {
        PointOperator.translate(vector, translateVector.vector);
        return this;
    }

    @Override
    public Point2d rotate(Double[][] rotationMatrix) {
        PointOperator.rotate(vector, rotationMatrix);
        return this;
    }

    public Point2d rotate(Double angle) {
        final Double[][] rotationMatrix = new Double[][] { // Réf: https://en.wikipedia.org/wiki/Rotation_matrix
                { Math.cos(angle), -Math.sin(angle) },
                { Math.sin(angle), Math.cos(angle) }
        };
        PointOperator.rotate(vector, rotationMatrix);
        return this;
    }

    @Override
    public Point2d divide(Double divider) {
        PointOperator.divide(vector, divider);
        return this;
    }

    @Override
    public Point2d multiply(Double multiplier) {
        PointOperator.multiply(vector, multiplier);
        return this;
    }

    @Override
    public Point2d add(Double adder) {
        PointOperator.add(vector, adder);
        return this;
    }

    @Override
    public Point2d clone() {
        return new Point2d(vector);
    }
}
