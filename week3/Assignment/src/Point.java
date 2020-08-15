import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // constructs the point (x, y)

    public void draw() {
        StdDraw.point(x, y);
    }
    // draws this point

    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }
    // draws the line segment from this point to that point

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    // string representation

    public int compareTo(Point that) {
        if (y < that.y) return -1;
        if (y > that.y) return 1;
        return Integer.compare(x, that.x);
    }
    // compare two points by y-coordinates, breaking ties by x-coordinates

    public double slopeTo(Point that) {
        double dx = x - that.x;
        double dy = y - that.y;
        if (dx == 0 && dy == 0) return Double.NEGATIVE_INFINITY;
        if (dx == 0) return Double.POSITIVE_INFINITY;
        if (dy == 0) return 0.0;
        return dy / dx;
    }
    // the slope between this point and that point

    public static int ccw(Point a, Point b, Point c) {
        double area = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area < 0) return -1;
        else if (area > 0) return +1;
        else return 0;
    }

    public Comparator<Point> slopeOrder() {
        return new BySlope();
    }
    // compare two points by slopes they make with this point

    private class BySlope implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            return Double.compare(slope1, slope2);
        }
    }

//    private class MyVersion implements Comparator<Point> {
//        @Override
//        public int compare(Point p1, Point p2) {
//            double slope1 = slopeTo(p1);
//            double slope2 = slopeTo(p2);
//            if (slope1 > slope2) return 1;
//            if (slope1 < slope2) return -1;
//            return p1.compareTo(p2);
//        }
//    }
}