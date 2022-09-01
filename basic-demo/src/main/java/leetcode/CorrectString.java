package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/** 20. 有效的括号
 *  给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * @author 996kid@gmail.com
 * @Description CorrectString
 * @Date 2022/7/18 22:14
 */
public class CorrectString {

    public static void main(String[] args) {
        Map<Character, Character> mapping = new HashMap<>(4);
        mapping.put(')', '(');
        mapping.put(']', '[');
        mapping.put('}', '{');
        System.out.println(isCorrect("[]{}{}({})", mapping));
    }

    // 遍历字符串 左括号压栈 右括号判断栈顶元素是不是匹配的左括号
    // 如果是 则出栈, 不是 则 返回false
    // 最后栈是不是为空 不为空说明不完全匹配
    private static boolean isCorrect(String string, Map<Character, Character> mapping) {
        Stack<Character> stack = new Stack<>();
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.empty()) {
                    char top = stack.peek();
                    if (top == mapping.get(c)) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }
}
