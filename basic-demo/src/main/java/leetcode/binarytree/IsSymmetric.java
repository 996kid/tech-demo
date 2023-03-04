package leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
//给你一个二叉树的根节点 root ， 检查它是否轴对称。
// 左子树 前序遍历 右子树后序遍历
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        prevOrder(root.left, list);
        houOrder(root.right, list2);
        if (list.size() == list2.size()) {
            for (int i = 0; i < list2.size(); i++) {
                if (list.get(i) == null && list2.get(i) != null) return false;
                if (list.get(i) == null && list2.get(i) == null) continue;
                if (list.get(i) != null && !list.get(i).equals(list2.get(i))) return false;
            }
            return true;
        }
        return false;
    }

    //前序
    private void prevOrder(TreeNode p, List<Integer> r) {
        if (p == null) {
            r.add(null);
            return;
        }
        r.add(p.val);
        prevOrder(p.left, r);
        prevOrder(p.right, r);
    }

    //后序
    private void houOrder(TreeNode p, List<Integer> r) {
        if (p == null) {
            r.add(null);
            return;
        }
        r.add(p.val);
        houOrder(p.right, r);
        houOrder(p.left, r);
    }
}