package leetcode;

import java.util.Stack;

/**
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 */
public class lengthOfLastWord {

    public static int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            Character character = chars[i];
            if (!character.toString().equals(" ")) {
                count++;
            } else if (count != 0 ) {
                return count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String s1 = "   fly me   to   the moon  ";
        String s2 = "luffy is still joyboy";

        System.out.println(lengthOfLastWord(s2));
    }
}