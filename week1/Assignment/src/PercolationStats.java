import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONSTANT_95 = 1.96;
    private final double[] percolationArr;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        percolationArr = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            percolationArr[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(percolationArr);
    }

    public double stddev() {
        return StdStats.stddev(percolationArr);
    }

    public double confidenceLo() {
        double mean = mean();
        double sigma = stddev();
        return mean - (sigma * CONSTANT_95) / Math.sqrt(percolationArr.length);
    }

    public double confidenceHi() {
        double mean = mean();
        double sigma = stddev();
        return mean + (sigma * CONSTANT_95) / Math.sqrt(percolationArr.length);
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.printf("%-23s = %.16f\n", "mean", percolationStats.mean());
        StdOut.printf("%-23s = %.16f\n", "stddev", percolationStats.stddev());
        StdOut.printf("%-23s = [%.16f, %.16f]\n", "95% confidence interval", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }

}