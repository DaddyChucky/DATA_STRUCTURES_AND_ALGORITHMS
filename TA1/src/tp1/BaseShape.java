package tp1;
import java.util.*;

public class BaseShape implements Cloneable {
    private Collection<Point2d> coords;

    public BaseShape() {
        coords = new ArrayList<>();
    }
    public BaseShape(Collection<Point2d> coord) {
        coords = new ArrayList<>(coord);
    }

    public BaseShape add(Point2d coord) {
        coords.add(coord);
        return this;
    }

    public BaseShape add(BaseShape shape) {
        for (Point2d point : shape.coords)
            coords.add(point);
        return this;
    }

    public BaseShape addAll(Collection<Point2d> coords) {
        for (Point2d point : coords)
            this.coords.add(point);
        return this;
    }

    public BaseShape remove(Point2d coord) {
        coords.remove(coord);
        return this;
    }

    public BaseShape remove(BaseShape shape) {
        //Réf: https://www.geeksforgeeks.org/arraylist-removeif-method-in-java/
        for (Point2d iteratedPoint : shape.coords)
            coords.removeIf(currentPoint -> (currentPoint.equals(iteratedPoint)));
        return this;
    }

    public BaseShape removeAll(Collection<Point2d> coords) {
        this.coords.removeAll(coords);
        return this;
    }

    public Collection<Point2d> getCoords() {
        return new ArrayList<>(coords);
    }

    public Collection<Point2d> getCoordsDeepCopy() {
        ArrayList<Point2d> coordsDeepCopy = new ArrayList<>(coords.size());

        for (Point2d point : coords)
            coordsDeepCopy.add(new Point2d(point.X(), point.Y()));

        return coordsDeepCopy;
    }

    public BaseShape translate(Point2d point) {
        for (Point2d pt : coords)
            pt.translate(point);
        return this;
    }

    public BaseShape rotate(Double angle) {
        for (Point2d pt : coords)
            pt.rotate(angle);
        return this;
    }

    public Double getMaxX() {
        Double maxX = new Double(Double.NEGATIVE_INFINITY);

        for (Point2d point : coords)
            if (point.X() > maxX)
                maxX = point.X();

        return maxX;
    }

    public Double getMaxY() {
        Double maxY = new Double(Double.NEGATIVE_INFINITY);

        for (Point2d point : coords)
            if (point.Y() > maxY)
                maxY = point.Y();

        return maxY;
    }

    public Point2d getMaxCoord() {
        return new Point2d(getMaxX(), getMaxY());
    }

    public Double getMinX() {
        Double minX = new Double(Double.POSITIVE_INFINITY);

        for (Point2d point : coords)
            if (point.X() < minX)
                minX = point.X();

        return minX;
    }

    public Double getMinY() {
        Double minY = new Double(Double.POSITIVE_INFINITY);

        for (Point2d point : coords)
            if (point.Y() < minY)
                minY = point.Y();

        return minY;
    }

    public Point2d getMinCoord() {
        return new Point2d(getMinX(), getMinY());
    }

    public BaseShape clone() {
        return new BaseShape(coords);
    }
}
