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
// 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。














public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> pList = new ArrayList<>();
        List<Integer> qList = new ArrayList<>();
        prevOrder(p, pList);
        prevOrder(q, qList);
        if (pList.size() == qList.size()) {
            for (int i = 0; i < qList.size(); i++) {
                if (pList.get(i) == null && qList.get(i) != null) return false;
                if (pList.get(i) == null && qList.get(i) == null) continue;
                if (pList.get(i) != null && !pList.get(i).equals(qList.get(i))) return false;
            }
            return true;
        }
        return false;
    }

    private void prevOrder(TreeNode p, List<Integer> r) {
        if (p == null) {
            r.add(null);
            return;
        }
        r.add(p.val);
        prevOrder(p.left, r);
        prevOrder(p.right, r);
    }
}