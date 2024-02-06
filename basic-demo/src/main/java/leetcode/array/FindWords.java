package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 996kid@gmail.com
 * @date 2024/2/6
 */
public class FindWords {

    /**
     * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
     *
     * 美式键盘 中：
     *
     * 第一行由字符 "qwertyuiop" 组成。
     * 第二行由字符 "asdfghjkl" 组成。
     * 第三行由字符 "zxcvbnm" 组成。
     *
     * 示例 1：
     *
     * 输入：words = ["Hello","Alaska","Dad","Peace"]
     * 输出：["Alaska","Dad"]
     * @param args
     */
    public static void main(String[] args) {
        String[] words = {"Hello","Alaska","Dad","Peace"};
        Arrays.stream(findWords(words)).forEach(System.out::println);
    }

    /**
     *
     * @param words
     * @return
     */
    public static String[] findWords(String[] words) {
        String s1 = "qwertyuiopQWERTYUIOP";
        List<Character> list1 = new ArrayList<>();
        for (char c : s1.toCharArray()) {
            list1.add(c);
        }
        s1 = "asdfghjklASDFGHJKL";
        List<Character> list2 = new ArrayList<>();
        for (char c : s1.toCharArray()) {
            list2.add(c);
        }
        s1 = "ZXCVBNMzxcvbnm";
        List<Character> list3 = new ArrayList<>();
        for (char c : s1.toCharArray()) {
            list3.add(c);
        }

        List<String> result = new ArrayList<>();
        for (String s : words) {
            boolean b1 = true;
            boolean b2 = true;
            boolean b3 = true;
            char[] t = s.toCharArray();
            for (char t1 : t) {
                if (!list1.contains(t1)) {
                    b1 = false;
                }
                if (!list2.contains(t1)) {
                    b2 = false;
                }
                if (!list3.contains(t1)) {
                    b3 = false;
                }
            }
            if (b1 || b2 || b3) {
                result.add(s);
            }
        }
        return result.toArray(new String[0]);
    }

    /**
     * 我们为每一个英文字母标记其对应键盘上的行号，然后检测字符串中所有字符对应的行号是否相同。
     *
     * 我们可以预处理计算出每个字符对应的行号。
     *
     * 遍历字符串时，统一将大写字母转化为小写字母方便计算。
     *
     */
    class Solution {
        public String[] findWords(String[] words) {
            List<String> list = new ArrayList<String>();
            String rowIdx = "12210111011122000010020202";
            for (String word : words) {
                boolean isValid = true;
                char idx = rowIdx.charAt(Character.toLowerCase(word.charAt(0)) - 'a');
                for (int i = 1; i < word.length(); ++i) {
                    if (rowIdx.charAt(Character.toLowerCase(word.charAt(i)) - 'a') != idx) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    list.add(word);
                }
            }
            String[] ans = new String[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                ans[i] = list.get(i);
            }
            return ans;
        }
    }
}
