package leetcode.array;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description TreeNode
 * @date 2023/12/11
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
     }
}
