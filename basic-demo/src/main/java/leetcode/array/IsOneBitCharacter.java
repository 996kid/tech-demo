package leetcode.array;

public class IsOneBitCharacter {

    /**
     * 有两种特殊字符：
     *
     * 第一种字符可以用一比特 0 表示
     * 第二种字符可以用两比特（10 或 11）表示
     * 给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。
     *
     * 示例 1:
     *
     * 输入: bits = [1, 0, 0]
     * 输出: true
     * 解释: 唯一的解码方式是将其解析为一个两比特字符和一个一比特字符。
     * 所以最后一个字符是一比特字符。
     * 示例 2:
     *
     * 输入：bits = [1,1,1,0]
     * 输出：false
     * 解释：唯一的解码方式是将其解析为两比特字符和两比特字符。
     * 所以最后一个字符不是一比特字符。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(isOneBitCharacter(new int[] {1, 1, 1, 1, 0}));
    }

    public static boolean isOneBitCharacter(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            if (i == bits.length - 1) {
                return true;
            } else if (bits[i] == 1) {
                i++;
            }
        }
        return false;
    }

    /**
     * 第一版
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter0(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == 0 && i == bits.length - 1) {
                return true;
            } else if (bits[i] == 1) {
                if (bits[i + 1] == 0) {
                    i++;
                    if (i == bits.length - 1) return false;
                } else if (bits[i + 1] == 1) {
                    i++;
                }
            }
        }
        return false;
    }
}