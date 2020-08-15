package week2;

public class ArrayQueue<Item> {
    private Item[] s;
    private int head;
    private int tail;
    private int size;

    private class Node {
        Item item;
    }

    public ArrayQueue(int capacity) {
        this.size = capacity;
        s = (Item[]) new Object[size + 1];
        head=tail=0;
    }

    public void enqueue(Item item){
        if((tail+1)%(size+1)==head) return;
        s[tail++]=item;
        tail=tail%(size+1);
    }
    public Item dequeue(){
        Item item;
        if(tail==head) return null;
        item=s[head++];
        head=head%(size+1);
        return item;
    }

    public static void main(String[] args) {
        ArrayQueue<String> queue=new ArrayQueue<>(5);
        queue.enqueue("shuaib");
        queue.enqueue("baba");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
