package leetcode.stack;

import java.util.Stack;

/** 栈实现队列
 * @author 996kid@gmail.com
 * @Description StackBasedQueue
 * @Date 2022/7/18 22:50
 */
public class StackBasedQueue {

    // 当往队列里add数据时, 压入输入栈
    private Stack<Integer> input;

    // 当往队列里拿数据时, 首先判断输出栈是否为空, 不为空直接取, 为空则从输入栈 出栈 放入输出栈
    private Stack<Integer> output;

    public StackBasedQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        if (output.empty()) {
            while (!input.empty()) {
                int t = input.pop();
                output.push(t);
            }
        }
        return output.pop();
    }

    public int peek() {
        if (output.empty()) {
            while (!input.empty()) {
                int t = input.pop();
                output.push(t);
            }
        }
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }



}
