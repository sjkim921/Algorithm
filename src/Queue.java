import java.util.ArrayList;
import java.util.Arrays;

public class Queue<T> {
    private ArrayList<T> queue = null;

    public Queue() {
        queue = new ArrayList<>();
    }

    public void enQueue(T t) {
        queue.add(t);
    }

    public T deQueue() {
        if (queue.isEmpty()) return null;
        return queue.remove(0);
    }

    public String toString() {
        return Arrays.toString(queue.toArray());
    }
}
