import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class FastCollinearPoints {
    private final Stack<LineSegment> lineSegments = new Stack<>();

    public FastCollinearPoints(Point[] points) {
        Point[] ara = check(points);
        int n = points.length;
        Point[] copy = new Point[n];
        System.arraycopy(ara, 0, copy, 0, n);
        //Both sorted by natural order
        for (Point p : copy) {
            Arrays.sort(ara);
            Arrays.sort(ara, p.slopeOrder());
            for (int i = 0; i < n; ) {
                double slope = p.slopeTo(ara[i]);
                int j = i;
                int count = 1;
                while (j < n && p.slopeTo(ara[j]) == slope) {
                    j++;
                    count++;
                }
                if (count >= 4 && p.compareTo(ara[i]) < 0) {
                    lineSegments.push(new LineSegment(p, ara[j - 1]));
                }
                if (j == i) i++;
                else i = j;
            }
        }

    }
    // finds all line segments containing 4 or more points

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

    private Point[] check(Point[] p) {
        if (p == null) throw new IllegalArgumentException();
        for (Point point : p) {
            if (point == null) throw new IllegalArgumentException();
        }
        int n = p.length;
        Point[] copy = new Point[n];
        System.arraycopy(p, 0, copy, 0, n);
        Arrays.sort(copy);
        for (int i = 1; i < n; i++) {
            if (copy[i].slopeTo(copy[i - 1]) == Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
        }
        return copy;
    }

}