import java.util.ArrayList;
import java.util.Arrays;

public class Stack<T> {

    private ArrayList<T> stack = new ArrayList<>();

    public void push(T t) {
        stack.add(t);
    }

    public T poll() {
        if (stack.isEmpty()) return null;
        return stack.remove(stack.size() - 1);
    }

    public String toString() {
        return Arrays.toString(stack.toArray());
    }

}
