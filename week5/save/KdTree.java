import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Comparator;

public class KdTree {
    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }

        public Comparator<Node> XOrder() {
            return (o1, o2) -> Point2D.X_ORDER.compare(o1.p, o2.p);
        }

        public Comparator<Node> YOrder() {
            return (o1, o2) -> Point2D.Y_ORDER.compare(o1.p, o2.p);
        }
    }

    private Node root;
    private int size;

    public KdTree() {
        root = null;
        size = 0;
    }                          // construct an empty set of points

    public boolean isEmpty() {
        return size == 0;
    }                   // is the set empty?

    public int size() {
        return size;
    }                       // number of points in the set

    public void insert(Point2D p) {
        root = insert(root, p, ,true);
    }              // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p)            // does the set contain point p?

    public void draw()                         // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty

    private Node insert(Node x, Point2D p, RectHV rect,boolean xOrder) {
        if (x == null) return new Node(p, new RectHV())
        int cmp =
    }

    public static void main(String[] args)                  // unit testing of the methods (optional)
}