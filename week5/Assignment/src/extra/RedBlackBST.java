package extra;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Stack;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private class Node {
        Key key;
        Value val;
        Node left, right;
        int count;
        boolean color;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }

    }

    private static final boolean RED = true;

    private static final boolean BLACK = false;
    private Node root;

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);

        h.color = RED;
        h.left.color = h.right.color = BLACK;
    }

    public void put(Key key, Value val) {
        if (key != null)
            root = put(root, key, val);
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        return h;
    }

    public Iterable<Key> getKeys() {
        Stack<Key> stack = new Stack<>();
        getKeys(root, stack);
        return stack;
    }

    private void getKeys(Node x, Stack<Key> stack) {
        if (x == null) return;
        getKeys(x.left, stack);
        stack.push(x.key);
        getKeys(x.right, stack);
    }


    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        int size = 1000;
        RedBlackBST<Integer, Integer> rbt = new RedBlackBST<>();
        for (int i = 0; i < size; i++) {
            rbt.put(StdRandom.uniform(-10000, 10000), StdRandom.uniform(0, 10));
        }
    }

}
