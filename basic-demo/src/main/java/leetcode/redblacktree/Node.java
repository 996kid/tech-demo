package leetcode.redblacktree;// Red Black Tree implementation in Java

class Node {
  int value;
  Node left;
  Node right;
  Node parent;
  String color;

  public Node(int value) {
    this.value = value;
    this.left = null;
    this.right = null;
    this.color = "red";
  }
}

class RedBlackTree {
  Node root;

  public RedBlackTree() {
    this.root = null;
  }

  // Insert a value into the tree
  public void insert(int value) {
    Node node = new Node(value);

    if (this.root == null) {
      // If the tree is empty, set the root to the new node and color it black
      this.root = node;
      this.root.color = "black";
      return;
    }

    Node current = this.root;
    while (current != null) {
      if (value < current.value) {
        if (current.left == null) {
          current.left = node;
          node.parent = current;
          break;
        }
        current = current.left;
      } else {
        if (current.right == null) {
          current.right = node;
          node.parent = current;
          break;
        }
        current = current.right;
      }
    }

    this.fixTree(node);
  }

  // Fix the tree after insertion
  public void fixTree(Node node) {
    while (node.parent != null && node.parent.color.equals("red")) {
      Node parent = node.parent;
      Node grandparent = parent.parent;

      if (grandparent != null && grandparent.left == parent) {
        Node uncle = grandparent.right;

        if (uncle != null && uncle.color.equals("red")) {
          grandparent.color = "red";
          parent.color = "black";
          uncle.color = "black";
          node = grandparent;
        } else {
          if (parent.right == node) {
            this.rotateLeft(parent);
            node = parent;
            parent = node.parent;
          }

          this.rotateRight(grandparent);
          parent.color = "black";
          grandparent.color = "red";
          node = parent;
        }
      } else {
        Node uncle = grandparent.left;

        if (uncle != null && uncle.color.equals("red")) {
          grandparent.color = "red";
          parent.color = "black";
          uncle.color = "black";
          node = grandparent;
        } else {
          if (parent.left == node) {
            this.rotateRight(parent);
            node = parent;
            parent = node.parent;
          }

          this.rotateLeft(grandparent);
          parent.color = "black";
          grandparent.color = "red";
          node = parent;
        }
      }
    }

    this.root.color = "black";
  }

  // Rotate a node to the left
  public void rotateLeft(Node node) {
    Node right = node.right;
    node.right = right.left;

    if (right.left != null) {
      right.left.parent = node;
    }

    right.parent = node.parent;

    if (node.parent == null) {
      this.root = right;
    } else if (node == node.parent.left) {
      node.parent.left = right;
    } else {
      node.parent.right = right;
    }

    right.left = node;
    node.parent = right;
  }

  // Rotate a node to the right
  public void rotateRight(Node node) {
    Node left = node.left;
    node.left = left.right;

    if (left.right != null) {
      left.right.parent = node;
    }

    left.parent = node.parent;

    if (node.parent == null) {
      this.root = left;
    } else if (node == node.parent.right) {
      node.parent.right = left;
    } else {
      node.parent.left = left;
    }

    left.right = node;
    node.parent = left;
  }
} 
