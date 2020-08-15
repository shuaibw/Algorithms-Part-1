import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final Stack<LineSegment> lineSegments = new Stack<>();

    public BruteCollinearPoints(Point[] points) {
        check(points);

        int n = points.length;
        Point[] copy = new Point[n];
        System.arraycopy(points, 0, copy, 0, n);
        Arrays.sort(copy);
        for (int i = 1; i < n; i++) {
            if (copy[i].slopeTo(copy[i - 1]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        double slope1 = copy[i].slopeTo(copy[j]);
                        double slope2 = copy[i].slopeTo(copy[k]);
                        double slope3 = copy[i].slopeTo(copy[l]);
                        if (slope1 == slope2 && slope2 == slope3) {
                            lineSegments.push(new LineSegment(copy[i], copy[l]));
                        }
                    }
                }
            }
        }
    }
    // finds all line segments containing 4 points

    public int numberOfSegments() {
        return lineSegments.size();
    }
    // the number of line segments

    public LineSegment[] segments() {
        int n = numberOfSegments();
        LineSegment[] ls = new LineSegment[n];
        int i = 0;
        for (LineSegment l : lineSegments) {
            ls[i++] = l;
        }
        return ls;
    }
    // the line segments

    private void check(Point[] p) {
        if (p == null) throw new IllegalArgumentException();
        for (Point point : p) {
            if (point == null) throw new IllegalArgumentException();
        }
    }
}