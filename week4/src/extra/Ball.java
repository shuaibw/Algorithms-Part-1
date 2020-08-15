package extra;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball {
    private double rx, ry;
    private double vx, vy;
    private final double radius;
    private static final double VELOCITY = 0.0005;

    public int R, G, B;

    public Ball() {
        vx = VELOCITY * StdRandom.uniform(-1.0, 1.0);
        vy = VELOCITY * StdRandom.uniform(-1.0, 1.0);
        radius = 0.01;
        rx = StdRandom.uniform(radius, 1-radius);
        ry = StdRandom.uniform(radius, 1-radius);
        R = StdRandom.uniform(0, 256);
        G = StdRandom.uniform(0, 256);
        B = StdRandom.uniform(0, 256);
    }

    public void move(double dt) {
        double dx = rx + vx * dt;
        double dy = ry + vy * dt;
        if (dx < radius || dx + radius > 1.0) vx = -vx;
        if (dy < radius || dy + radius > 1.0) vy = -vy;
        rx = dx;
        ry = dy;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}
