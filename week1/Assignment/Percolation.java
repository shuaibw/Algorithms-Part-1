import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf, extraUf;
    private final boolean[][] sites;
    private final int dim;
    private int open = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);
        extraUf = new WeightedQuickUnionUF(n * n + 1);
        sites = new boolean[n][n];
        dim = n;

    }

    private boolean checkRange(int row, int col) {
        return row >= 1 && row <= dim && col >= 1 && col <= dim;
    }

    private void connectSite(int r1, int c1, int r2, int c2) {
        if (checkRange(r1, c1) && isOpen(r1, c1)) {
            int p = idx(r1, c1);
            int q = idx(r2, c2);
            uf.union(p, q);
            extraUf.union(p, q);
        }
    }

    private int idx(int row, int col) {
        return (row - 1) * dim + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!checkRange(row, col)) throw new IllegalArgumentException();
        if (!sites[row - 1][col - 1]) {
            sites[row - 1][col - 1] = true;
            open++;
            if (row == 1) {
                uf.union(idx(row, col), dim * dim);
                extraUf.union(idx(row, col), dim * dim);
            }
            if (row == dim) {
                uf.union(idx(row, col), dim * dim + 1);
            }
            connectSite(row + 1, col, row, col);
            connectSite(row - 1, col, row, col);
            connectSite(row, col + 1, row, col);
            connectSite(row, col - 1, row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!checkRange(row, col)) throw new IllegalArgumentException();
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!checkRange(row, col)) throw new IllegalArgumentException();
        int p = idx(row, col);
        return extraUf.find(p) == extraUf.find(dim * dim);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return open;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(dim * dim) == uf.find(dim * dim + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}