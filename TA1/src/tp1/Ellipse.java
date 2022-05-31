package tp1;
import java.util.Collection;

public class Ellipse extends BaseShape {
    public Ellipse(Double widthRadius, Double heightRadius) {
        super();
        final double INCREMENT = 0.04;
        for (double x = 0.0; x <= widthRadius; x += INCREMENT) {
            // Réf: https://fr.wikipedia.org/wiki/Ellipse_(math%C3%A9matiques)
            final double Y = Math.pow(heightRadius, 2) * (-Math.pow(x, 2) / Math.pow(widthRadius, 2) + 1);
            add(new Point2d(x, Math.sqrt(Y)));
            add(new Point2d(x, -Math.sqrt(Y)));
            add(new Point2d(-x, Math.sqrt(Y)));
            add(new Point2d(-x, -Math.sqrt(Y)));
        }
    }

    public Ellipse(Point2d dimensions) { this(dimensions.X(), dimensions.Y()); }
    private Ellipse(Collection<Point2d> coords) {
        super(coords);
    }

    @Override
    public Ellipse translate(Point2d point) {
        super.translate(point);
        return this;
    }

    @Override
    public Ellipse rotate(Double angle) {
        // Réf: https://courses.lumenlearning.com/precalctwo/chapter/rotation-of-axes/
        for (Point2d point : getCoords()) {
            Point2d newPoint = new Point2d( point.X() * Math.cos(angle) - point.Y() * Math.sin(angle),
                    point.X() * Math.sin(angle) + point.Y() * Math.cos(angle) );
            remove(point);
            add(newPoint);
        }
        return this;
    }

    @Override
    public Ellipse clone() {
        return new Ellipse(getCoords());
    }
}
