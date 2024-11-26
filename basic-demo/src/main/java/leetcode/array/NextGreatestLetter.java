package leetcode.array;

public class NextGreatestLetter {

    /**
     * 给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。
     * 返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
     *
     * 示例 1：
     * 输入: letters = ["c", "f", "j"]，target = "a"
     * 输出: "c"
     * 解释：letters 中字典上比 'a' 大的最小字符是 'c'。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(nextGreatestLetter(new char[]{'c','f','j'}, 'j'));
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        char minChar = 'z' + 1;
        boolean existed = false;
        for (int i = 0; i < letters.length; i++) {
            char c = letters[i];
            if (c > target) {
                existed = true;
                // 这里已经按照递增顺序排序了 不需要这样
                minChar = (char) Math.min(c, minChar);
            }
        }
        if (existed) {
            return minChar;
        }
        return letters[0];
    }
}
