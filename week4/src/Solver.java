import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private static class Node implements Comparable<Node> {
        Board board;
        int manhattan;
//        int hamming;
        int moves;
        Node prev;

        public Node(Board board, Node prev, int moves) {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
            manhattan = board.manhattan();
//            hamming = board.hamming();
        }

        @Override
        public int compareTo(Node o) {
            int cmp = Integer.compare(manhattan + moves, o.manhattan + o.moves);
            if (cmp == 0) return Integer.compare(manhattan, o.manhattan);
            return cmp;
        }
    }

    private Node min;
    private boolean solvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        MinPQ<Node> pq = new MinPQ<>();
        MinPQ<Node> pqTwin = new MinPQ<>();
        pq.insert(new Node(initial, null, 0));
        pqTwin.insert(new Node(initial.twin(), null, 0));
        solvable = false;

        min = pq.delMin();
        Node minTwin = pqTwin.delMin();
        while (!min.board.isGoal() && !minTwin.board.isGoal()) {
            for (Board b : min.board.neighbors()) {
                if (min.prev != null && min.prev.board.equals(b)) continue;
                pq.insert(new Node(b, min, min.moves + 1));
            }
            min = pq.delMin();
            for (Board b : minTwin.board.neighbors()) {
                if (minTwin.prev != null && minTwin.prev.board.equals(b)) continue;
                pqTwin.insert(new Node(b, minTwin, minTwin.moves + 1));
            }
            minTwin = pqTwin.delMin();
        }
        if (min.board.isGoal()) solvable = true;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!solvable) return -1;
        return min.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!solvable) return null;
        Stack<Board> moves = new Stack<>();
        Node tmp = min;
        while (tmp != null) {
            moves.push(tmp.board);
            tmp = tmp.prev;
        }
        return moves;
    }

    // test client (see below)
    public static void main(String[] args) {

//        int[][] tiles={
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,0}
//        };


        // read in the board specified in the filename
        int n = StdIn.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = StdIn.readInt();
            }
        }

        // solve the slider puzzle
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        if (solver.isSolvable()) {
            for (Board b : solver.solution()) {
                StdOut.println(b);
            }
//            StdOut.println(solver.moves());
        } else {
            StdOut.println("Unsolvable!!");
        }

    }

}