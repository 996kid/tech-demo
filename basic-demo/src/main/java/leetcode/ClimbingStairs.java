package leetcode;

/** 爬梯子 斐波那契数列
 * @author 996kid@gmail.com
 * @Description ClimbingStairs
 * @Date 2022/7/15 17:24
 */
public class ClimbingStairs {


    public static void main(String[] args) {
        System.out.println(findStep(9));
    }

    /**
     * f(n) = f(n-1) + f(n-2)
     * n=2时, 2种情况 1 1, 2
     * n=3时, 3种情况 1 1 1, 1 2, 2 1
     * n=4时, 5种情况 1 1 1 1, 1 1 2, 1 2 1, 2 1 1, 2 2
     * 1 1 2 3 5
     * @param steps
     * @return
     */
    private static int findStep(int steps) {
        if (steps == 1 || steps == 0) {
            return 1;
        }
        return findStep(steps - 1) + findStep(steps - 2);
    }
}
