package leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        if (strs.length == 1) return prefix;
        for (int i = 1; i < strs.length; i++) {
            int length = strs[i].length();
            if (length < prefix.length()) {
                prefix = prefix.substring(0, length);
            }
            String tem = strs[i].substring(0, prefix.length());
            if (tem.equals(prefix)) {
                continue;
            } else {
                for (int j = prefix.length() - 1;j >=0; j-- ) {
                    if (tem.substring(0, tem.length() - 1).equals(prefix.substring(0, prefix.length() - 1))) {
                        prefix = prefix.substring(0, prefix.length() - 1);
                        break;
                    }
                    prefix = prefix.substring(0, prefix.length() - 1);
                    tem = tem.substring(0, tem.length() - 1);
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
//        String[] strs = new String[] {"flower","flow","flight"};
        String[] strs = new String[] {"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs));
    }
}