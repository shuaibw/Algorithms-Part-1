import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stack;

public class KdTree {
    private static class Node {
        Point2D p;
        RectHV rect;
        Node left;
        Node right;
        boolean xOrder;

        public Node(Node prev, Point2D p) {
            this.p = p;
            if (prev == null) {
                rect = new RectHV(0, 0, 1, 1);
                xOrder = true;
            } else {
                xOrder = !prev.xOrder;
                if (prev.xOrder) {
                    int cmp = Point2D.X_ORDER.compare(p, prev.p);
                    if (cmp < 0) rect = new RectHV(prev.rect.xmin(), prev.rect.ymin(), prev.p.x(), prev.rect.ymax());
                    else rect = new RectHV(prev.p.x(), prev.rect.ymin(), prev.rect.xmax(), prev.rect.ymax());
                } else {
                    int cmp = Point2D.Y_ORDER.compare(p, prev.p);
                    if (cmp < 0) rect = new RectHV(prev.rect.xmin(), prev.rect.ymin(), prev.rect.xmax(), prev.p.y());
                    else rect = new RectHV(prev.rect.xmin(), prev.p.y(), prev.rect.xmax(), prev.rect.ymax());
                }
            }
        }

    }

    private Node root;
    private int size = 0;

    public KdTree() {
    }                               // construct an empty set of points

    public boolean isEmpty() {
        return size == 0;
    }                      // is the set empty?

    public int size() {
        return size;
    }                         // number of points in the set

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (contains(p)) return;
        root = insert(root, null, p);
    }             // add the point to the set (if it is not already in the set)

    private Node insert(Node x, Node prev, Point2D p) {
        if (x == null) {
            size++;
            return new Node(prev, p);
        }
        int cmp = x.xOrder ? Point2D.X_ORDER.compare(p, x.p) : Point2D.Y_ORDER.compare(p, x.p);
        if (cmp < 0) x.left = insert(x.left, x, p);
        else x.right = insert(x.right, x, p);
        return x;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Node x = root;
        while (x != null) {
            if (p.compareTo(x.p) == 0) return true;
            int cmp = x.xOrder ? Point2D.X_ORDER.compare(p, x.p) : Point2D.Y_ORDER.compare(p, x.p);
            if (cmp < 0) x = x.left;
            else x = x.right;
        }
        return false;
    }            // does the set contain point p?

    public void draw() {
        draw(root);
    }                         // draw all points to standard draw

    private void draw(Node x) {
        if (x == null) return;
        draw(x.left);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        x.p.draw();
        StdDraw.setPenRadius();
        if (x.xOrder) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }
        draw(x.right);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        Stack<Point2D> stack = new Stack<>();
        range(root, rect, stack);
        return stack;
    }             // all points that are inside the rectangle (or on the boundary)

    private void range(Node x, RectHV rect, Stack<Point2D> stack) {
        if (x == null) return;
        if (rect.contains(x.p)) stack.push(x.p);
        if (x.left != null && rect.intersects(x.left.rect)) range(x.left, rect, stack);
        if (x.right != null && rect.intersects(x.right.rect)) range(x.right, rect, stack);
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        return nearest(root, p, root.p);
    }            // a nearest neighbor in the set to point p; null if the set is empty

    private Point2D nearest(Node x, Point2D p, Point2D pointSoFar) {
        if (x == null) return pointSoFar;
        if (x.rect.distanceSquaredTo(p) < p.distanceSquaredTo(pointSoFar)) {
            double dist = x.p.distanceSquaredTo(p);
            if (p.distanceSquaredTo(x.p) < p.distanceSquaredTo(pointSoFar)) {
                pointSoFar = x.p;
            }
            int cmp = x.xOrder ? Point2D.X_ORDER.compare(p, x.p) : Point2D.Y_ORDER.compare(p, x.p);
            if (cmp < 0) {
                pointSoFar = nearest(x.left, p, pointSoFar);
                pointSoFar = nearest(x.right, p, pointSoFar);
            } else {
                pointSoFar = nearest(x.right, p, pointSoFar);
                pointSoFar = nearest(x.left, p, pointSoFar);
            }
        }
        return pointSoFar;
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            kdTree.insert(new Point2D(x, y));
        }
        System.out.println(kdTree.size);
        kdTree.draw();
    }                 // unit testing of the methods (optional)
}