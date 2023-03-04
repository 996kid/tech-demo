package leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * 去掉非数字 非字母后 是否回文串
 */
public class IsPalindrome {

    private static List<String> nums = Arrays.asList("1","2","3","4","5","6","7","8","9","0");

    private static List<String> abc = Arrays.asList("a", "b", "c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");


    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        char[] newChars = new char[chars.length];
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            String temp = chars[i] + "";
            if (nums.contains(temp) || abc.contains(temp) || abc.contains(temp.toLowerCase())) {
                newChars[j++] = temp.toLowerCase().toCharArray()[0];
            }
        }
        for (int i = 0; i < j; i++) {
            if (newChars[i] != newChars[j - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }
}