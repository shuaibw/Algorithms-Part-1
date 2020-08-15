package week1.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final boolean[][] site;
    private final int dim;
    private int open = 0;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        uf = new WeightedQuickUnionUF(n * n + 2);
        site = new boolean[n][n];
        dim = n;
    }

    public void open(int row, int col) {
        if (!checkRange(row, col)) throw new IllegalArgumentException();
        if (!site[row - 1][col - 1]) {
            site[row - 1][col - 1] = true;
            open++;
            if (row == 1) {
                uf.union(0, val(row - 1, col));
            }
            if (row == dim) {
                uf.union(dim * dim + 1, val(row - 1, col));
            }
            connectWithSurrounding(row - 1, col, row, col);
            connectWithSurrounding(row, col - 1, row, col);
            connectWithSurrounding(row + 1, col, row, col);
            connectWithSurrounding(row, col + 1, row, col);
        }
    }

    public boolean isOpen(int row, int col) {
        if (!checkRange(row, col)) throw new IllegalArgumentException();
        return site[--row][--col];
    }

    public boolean isFull(int row, int col) {
        if(!checkRange(row, col)) throw new IllegalArgumentException();
        return uf.find(val(--row, col)) == uf.find(0);
    }

    public int numberOfOpenSites() {
        return open;
    }

    public boolean percolates() {
        return uf.find(0) == uf.find(dim * dim + 1);
    }

    private boolean checkRange(int row, int col) {
        return row > 0 && col > 0 && row <= dim && col <= dim;
    }

    private void connectWithSurrounding(int r, int c, int row, int col) {
        if (checkRange(r, c) && isOpen(r, c)) {
            uf.union(val(--row, col), val(--r, c));
        }
    }
    public void print(){
        for(int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                System.out.printf((site[i][j]?"\033[1;36m":"\033[0m")+"%-3d", uf.find(val(i, j+1)));
            }
            System.out.println();
        }
        System.out.println("*********************");
    }
    private int val(int row, int col) {
        return row * dim + col;
    }

    public static void main(String[] args) {
        Percolation percolation=new Percolation(5);
        percolation.open(5,5);
        percolation.open(3,5);
        percolation.open(2,2);
        percolation.open(2,1);
        percolation.open(3,1);
        percolation.open(4,1);
        percolation.open(3,3);
        percolation.open(2,3);
        percolation.open(1,3);
        percolation.print();
        System.out.println(percolation.isFull(3,3));
    }
}