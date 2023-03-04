package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马字符转数字
 */
public class RomanToInt {

    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    private static Map<Character, Integer> mapping = new HashMap<>();

    static {
        mapping.put('I', 1);
        mapping.put('V', 5);
        mapping.put('X', 10);
        mapping.put('L', 50);
        mapping.put('C', 100);
        mapping.put('D', 500);
        mapping.put('M', 1000);
    }
    public int romanToInt(String s) {
        int num = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            /**
             * 前一个比后一个数大时正常累积数字
             * 小时需要减去前一个值
             */
            if (i < chars.length - 1) {
                if (mapping.get(chars[i]) < mapping.get(chars[i+1])) {
                    num -= mapping.get(chars[i]);
                } else {
                    num += mapping.get(chars[i]);
                }
            } else {
                num += mapping.get(chars[i]);
            }
        }
        return num;
    }
}