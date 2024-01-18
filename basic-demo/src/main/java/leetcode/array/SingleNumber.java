package leetcode.array;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description SingleNumber
 * @date 2024/1/16
 */

public class SingleNumber {

    public static void main(String[] args) {
        int[] arr = {0,2,3,4,3,2,0};
        System.out.println(singleNumber(arr));
    }

    /**
     * 异或特性：
     *      任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a
     *      任何数和其自身做异或运算，结果是 0，即 a⊕a=0
     *      异或运算满足交换律和结合律，即
     *      a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b   最后留下来的值为单独存在的数
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int s = 0;
        for (int i : nums) {
            s = s ^ i;
        }
        return s;
    }
}
