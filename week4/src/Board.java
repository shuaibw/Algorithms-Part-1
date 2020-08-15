import edu.princeton.cs.algs4.Stack;

public class Board {

    private final int[][] tiles;
    private final int dim;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        dim = tiles.length;
        this.tiles = deepClone(tiles);
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dim).append("\n");
        for (int[] row : tiles) {
            for (int i : row) {
                sb.append(String.format("%2d ", i));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return dim;
    }

    // number of tiles out of place
    public int hamming() {
        int c = 1;
        int sum = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tiles[i][j] != c) sum++;
                c++;
                if (c == dim * dim) return sum;
            }
        }
        return sum;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int val = tiles[i][j];
                if (val == 0) continue;
                val--;
                int r = val / dim;
                int c = val % dim;
                int add = Math.abs(r - i) + Math.abs(c - j);
                sum += add;
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board)) return false;
        return toString().compareTo(y.toString()) == 0;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();
        int[] idx = findCell(0);
        int r = idx[0];
        int c = idx[1];
        if (isValidIdx(r - 1, c)) neighbors.push(new Board(swap(r, c, r - 1, c)));
        if (isValidIdx(r + 1, c)) neighbors.push(new Board(swap(r, c, r + 1, c)));
        if (isValidIdx(r, c - 1)) neighbors.push(new Board(swap(r, c, r, c - 1)));
        if (isValidIdx(r, c + 1)) neighbors.push(new Board(swap(r, c, r, c + 1)));
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[] idx = findCell(2);
        int r = idx[0];
        int c = idx[1];
        int[][] twin = null;
        if (isValidIdx(r - 1, c) && tiles[r - 1][c] != 0) twin = swap(r, c, r - 1, c);
        else if (isValidIdx(r + 1, c) && tiles[r + 1][c] != 0) twin = swap(r, c, r + 1, c);
        else if (isValidIdx(r, c - 1) && tiles[r][c - 1] != 0) twin = swap(r, c, r, c - 1);
        else if (isValidIdx(r, c + 1) && tiles[r][c + 1] != 0) twin = swap(r, c, r, c + 1);
        assert twin != null;
        return new Board(twin);
    }

    private int[][] swap(int r1, int c1, int r2, int c2) {
        int[][] copy = deepClone(tiles);
        int temp = copy[r1][c1];
        copy[r1][c1] = copy[r2][c2];
        copy[r2][c2] = temp;
        return copy;
    }

    private boolean isValidIdx(int r, int c) {
        return r >= 0 && r < dim && c >= 0 && c < dim;
    }

    private int[] findCell(int val) {
        int[] cell = new int[2];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (tiles[i][j] == val) {
                    cell[0] = i;
                    cell[1] = j;
                    return cell;
                }
            }
        }
        return cell;
    }

    private int[][] deepClone(int[][] ara) {
        int[][] copy = new int[dim][];
        for (int i = 0; i < dim; i++) {
            copy[i] = new int[dim];
            for (int j = 0; j < dim; j++) {
                copy[i][j] = ara[i][j];
            }
        }
        return copy;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        int[][] ara = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };
        Board board = new Board(ara);
        for (Board b : board.neighbors()) {
            System.out.println(b);
            System.out.println("Manhattan " + b.manhattan());
            System.out.println("Hamming " + b.hamming());
        }
        System.out.println(board);
        System.out.println(board.dimension());
    }

}