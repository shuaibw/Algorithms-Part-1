package extra;

import java.util.Iterator;

public class LinkedStack<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }

    private Node first = null;

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedStack<String> stack=new LinkedStack<>();
        stack.push("shuaib");
        stack.push("baba");
        stack.push("rumana");
        for(String s:stack){
            System.out.println(s);
        }

    }
}
