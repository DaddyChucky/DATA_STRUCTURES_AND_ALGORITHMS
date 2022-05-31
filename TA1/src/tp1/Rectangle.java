package tp1;
import java.util.Collection;

public class Rectangle extends BaseShape {
    public Rectangle(Double width, Double height) {
        super();
        final double INCREMENT = 0.5;
        for (double x = 0.0; x <= width; x += INCREMENT)
            for (double y = 0.0; y <= height; y += INCREMENT)
                add(new Point2d(x, y));
    }

    public Rectangle(Point2d dimensions) { this(dimensions.X(), dimensions.Y()); }
    private Rectangle(Collection<Point2d> coords) { super(coords); }

    @Override
    public Rectangle translate(Point2d point) {
        super.translate(point);
        return this;
    }

    @Override
    public Rectangle rotate(Double angle) {
        super.rotate(angle);
        return this;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(getCoords());
    }
}
