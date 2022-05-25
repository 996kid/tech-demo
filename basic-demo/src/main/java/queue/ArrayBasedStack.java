package queue;

/**
 * @author 996kid@gmail.com
 * @Description ArrayBasedStack
 * @Date 2022/4/12 23:03
 */
public class ArrayBasedStack {

    private int[] stack;

    private int pointer;

    ArrayBasedStack(int size) {
        stack = new int[size];
    }

    private int pop() {
        if (pointer <= 0) {
            // last element can not pop
            throw new RuntimeException("last element can not pop");
        } else {
            return stack[--pointer];
        }
    }

    private void push(int element) {
        if (pointer >= stack.length) {
            throw new RuntimeException("full stack can not push");
        } else {
            stack[pointer++] = element;
        }
    }

    public static void main(String[] args) {
        Integer i = 1;
        System.out.println(1 == i);
    }
}
