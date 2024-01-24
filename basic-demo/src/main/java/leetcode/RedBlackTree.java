package leetcode;

// 实现红黑树
public class RedBlackTree {

    // 实现红黑树的节点
private static class Node {
        // 节点的值
        int value;
        // 节点的颜色
        boolean color;
        // 左子节点
        Node left;
        // 右子节点
        Node right;
        // 父节点
        Node parent;

        // 构造函数
        Node(int value) {
            this.value = value;
        }
    }

    // 根节点
    private Node root;

    // 插入节点
    public void insert(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
            root.color = false;
            return;
        }

        Node parent = root;
        while (true) {
            if (value < parent.value) {
                if (parent.left == null) {
                    parent.left = node;
                    node.parent = parent;
                    break;
                }
                parent = parent.left;
            } else {
                if (parent.right == null) {
                    parent.right = node;
                    node.parent = parent;
                    break;
                }
                parent = parent.right;
            }
        }

        // 调整红黑树
        fixAfterInsertion(node);
    }

    // 调整红黑树
    private void fixAfterInsertion(Node node) {
        // 如果插入的是根节点，直接将根节点染成黑色
        if (node.parent == null) {
            node.color = false;
            return;
        }

        // 如果插入的节点的父节点是黑色，不需要调整
        if (node.parent.color == false) {
            return;
        }

        // 如果插入的节点的父节点是红色，需要调整
        // 如果插入的节点的叔叔节点是红色，将父节点和叔叔节点染成黑色，将祖父节点染成红色，然后将祖父节点作为当前节点，继续调整
        if (uncleOf(node) != null && uncleOf(node).color == true) {
            node.parent.color = false;
            uncleOf(node).color = false;
            grandparentOf(node).color = true;
            fixAfterInsertion(grandparentOf(node));
            return;
        }

        // 如果插入的节点的叔叔节点是黑色，需要旋转
        // 如果插入的节点是父节点的右子节点，将父节点左旋
        if (node == node.parent.right) {
            rotateLeft(node.parent);
            node = node.left;
        }

        // 如果插入的节点是父节点的左子节点，将父节点染成黑色，祖父节点染成红色，然后将祖父节点右旋
        node.parent.color = false;
        grandparentOf(node).color = true;
        rotateRight(grandparentOf(node));
    }

    // 左旋
    private void rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        if (right.left != null) {
            right.left.parent = node;
        }
        right.parent = node.parent;
        if (node.parent == null) {
            root = right;
        } else if (node.parent.left == node) {
            node.parent.left = right;
        } else {
            node.parent.right = right;
        }
        right.left = node;
        node.parent = right;
    }

    // 右旋
    private void rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        if (left.right != null) {
            left.right.parent = node;
        }
        left.parent = node.parent;
        if (node.parent == null) {
            root = left;
        } else if (node.parent.left == node) {
            node.parent.left = left;
        } else {
            node.parent.right = left;
        }
        left.right = node;
        node.parent = left;
    }

    // 获取节点的祖父节点
    private Node grandparentOf(Node node) {
        if (node.parent == null) {
            return null;
        }
        return node.parent.parent;
    }

    // 获取节点的叔叔节点
    private Node uncleOf(Node node) {
        Node grandparent = grandparentOf(node);
        if (grandparent == null) {
            return null;
        }
        if (node.parent == grandparent.left) {
            return grandparent.right;
        } else {
            return grandparent.left;
        }
    }

    // 获取节点的兄弟节点
    private Node siblingOf(Node node) {
        if (node.parent == null) {
            return null;
        }
        if (node == node.parent.left) {
            return node.parent.right;
        } else {
            return node.parent.left;
        }
    }


}
