package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 996kid@gmail.com
 * @date 2024/2/6
 */
public class MatrixReshape {

    /**
     * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
     *
     * 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
     *
     * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
     *
     * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
     *
     * 输入：mat = [[1,2],[3,4]], r = 1, c = 4
     * 输出：[[1,2,3,4]]
     * @param args
     */
    public static void main(String[] args) {

    }



    public static int[][] matrixReshape1(int[][] mat, int r, int c) {
        int row = mat.length;
        int col = mat[0].length;

        if (row * col != r * c) {
            return mat;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                list.add(mat[i][j]);
            }
        }

        int index = 0;
        int[][] newMat = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newMat[i][j] = list.get(index++);
            }
        }

        return newMat;
    }


    class Solution {

        /**
         * 对于一个行数为 m，列数为 n，行列下标都从 0 开始编号的二维数组，
         * 我们可以通过下面的方式，将其中的每个元素 (i,j) 映射到整数域内，
         * 并且它们按照行优先的顺序一一对应着 [0,mn) 中的每一个整数。
         * 形象化地来说，我们把这个二维数组「排扁」成了一个一维数组。如果读者对机器学习有一定了解，可以知道这就是 flatten 操作。
         *
         * @param r
         * @param c
         * @return
         */
        public int[][] matrixReshape(int[][] nums, int r, int c) {
            int m = nums.length;
            int n = nums[0].length;
            if (m * n != r * c) {
                return nums;
            }

            int[][] ans = new int[r][c];
            for (int x = 0; x < m * n; ++x) {
                //1 2 3   4 5 6   2 * 3
                ans[x / c][x % c] = nums[x / n][x % n];
            }
            return ans;
        }
    }
}
