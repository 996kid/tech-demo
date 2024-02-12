package leetcode.array;

public class MaxCount {

    /**
     * 给你一个 m x n 的矩阵 M 和一个操作数组 op 。
     * 矩阵初始化时所有的单元格都为 0 。ops[i] = [ai, bi] 意味着当所有的 0 <= x < ai 和 0 <= y < bi 时， M[x][y] 应该加 1。
     *
     * 在 执行完所有操作后 ，计算并返回 矩阵中最大整数的个数 。
     * @param args
     */
    public static void main(String[] args) {

    }

    public static int maxCount(int m, int n, int[][] ops) {
        int row = ops.length;
        int[][] mn = new int[m][n];
        for (int i = 0; i < row; i++) {
            int[] op = ops[i];
            for (int j = 0; j < m && j < op[0]; j++) {
                for (int k = 0; k < n && k < op[1]; k++) {
                    mn[j][k] += 1;
                }
            }
        }


        return 0;
    }
}
