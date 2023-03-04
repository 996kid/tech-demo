package leetcode.binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
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
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 *
 * 输入：root = []
 * 输出：[]
 *
 * 输入：root = [1]
 * 输出：[1]
 */
// 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
public class InorderTraversal {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodevals = new ArrayList<>();
        inorder(root, nodevals);
        return nodevals;
    }

    //递归
    private static void inorder(TreeNode root, List<Integer> nodevals) {
        // 每遍历到一个节点都要先遍历它的左子树(子递归), 自身, 右子树(子递归)
        if (root == null) return;
        inorder(root.left, nodevals);
        nodevals.add(root.val);
        inorder(root.right, nodevals);
    }

    public static void main(String[] args) {
        System.out.println();
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}