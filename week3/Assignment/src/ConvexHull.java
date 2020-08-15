import java.util.ArrayList;
import java.util.Arrays;

public class ConvexHull {
    private Point[] points;
    private ArrayList<Point> hullPoints;
    int n;

    public ConvexHull(Point[] points) {
        assert points.length >= 3;
        this.points = points;
        hullPoints = new ArrayList<>();
        n = points.length;
    }

    public void findHull() {
        Arrays.sort(points);
        Arrays.sort(points, points[0].slopeOrder());
        hullPoints.add(points[0]);
        hullPoints.add(points[1]);

    }

}
