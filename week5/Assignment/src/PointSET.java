import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Stack;
import java.util.TreeSet;


public class PointSET {

    private final TreeSet<Point2D> points;

    public PointSET() {
        points = new TreeSet<>();
    }                               // construct an empty set of points

    public boolean isEmpty() {
        return points.isEmpty();
    }                      // is the set empty?

    public int size() {
        return points.size();
    }                         // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        points.add(p);
    }              // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return points.contains(p);
    }            // does the set contain point p?

    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }                         // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Stack<Point2D> inner = new Stack<>();
        for (Point2D p : points) {
            if (rect.contains(p)) inner.push(p);
        }
        return inner;
    }             // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        Point2D min = null;
        double minDist = Double.POSITIVE_INFINITY;
        for (Point2D point : points) {
            double dist = point.distanceSquaredTo(p);
            if (dist < minDist) {
                minDist = dist;
                min = point;
            }
        }
        return min;
    }             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {

    }                  // unit testing of the methods (optional)
}