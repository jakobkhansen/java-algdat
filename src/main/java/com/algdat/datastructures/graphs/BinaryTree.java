package com.algdat.datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

import com.algdat.utils.BinaryTreePrinter;

// Insert, Traversal (Pre, post, in), delete, 
public class BinaryTree {
    public class Node {
        public int value;

        public Node left, right;

        public Node parent;

        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    Node root;

    public BinaryTree() {}

    public void insert(int value) {
        if (root == null) {
            root = new Node(value, null);

        } else {
            insertRecursive(value, root);
        }
    }

    private void insertRecursive(int value, Node current) {
        if (value <= current.value) {
            if (current.left == null) {
                current.left = new Node(value, current);
            } else {
                insertRecursive(value, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = new Node(value, current);
            } else {
                insertRecursive(value, current.right);
            }
        }
    }

    public Node find(int value) {
        return findRecursive(root, value);
    }

    private Node findRecursive(Node current, int value) {
        if (current.value == value) {
            return current;

        } else if (value < current.value && current.left != null) {
            return findRecursive(current.left, value);

        } else if (current.right != null) {
            return findRecursive(current.right, value);
        }

        return null;
    }

    public void delete(int value) {
        delete(find(value));
    }

    private void delete(Node nodeToDelete) {
        // Very unclean, consider doing recursive instead
        // Recursive is however not "inplace"
        if (nodeToDelete.left != null && nodeToDelete.right != null) {
            Node replacer = firstInorderNode(nodeToDelete.right);

            if (replacer.right != null) {
                replacer.right.parent = replacer.parent;
            }
            replacer.parent.left = replacer.right;

            replacer.left = nodeToDelete.left;
            replacer.right = nodeToDelete.right;

            replacer.left.parent = replacer;
            replacer.right.parent = replacer;
            replacer.parent = nodeToDelete.parent;

            if (isLeftChild(nodeToDelete)) {
                nodeToDelete.parent.left = replacer;
            } else {
                nodeToDelete.parent.right = replacer;
            }


        // Left child only
        } else if (nodeToDelete.left != null) {
            if (isLeftChild(nodeToDelete)) {
                nodeToDelete.left.parent = nodeToDelete.parent;
                nodeToDelete.parent.left = nodeToDelete.left;
            } else {
                nodeToDelete.right.parent = nodeToDelete.parent;
                nodeToDelete.parent.right = nodeToDelete.left;
            }

        // Right child only
        } else if (nodeToDelete.right != null) {
            if (isLeftChild(nodeToDelete)) {
                nodeToDelete.right.parent = nodeToDelete.parent;
                nodeToDelete.parent.left = nodeToDelete.right;
            } else {
                nodeToDelete.right.parent = nodeToDelete.parent;
                nodeToDelete.parent.right = nodeToDelete.right;
            }


        // No children
        } else {
            if (isLeftChild(nodeToDelete)) {
                nodeToDelete.parent.left = null;
            } else {
                nodeToDelete.parent.right = null;
            }
        }
    }

    private boolean isLeftChild(Node child) {
        return child.parent.left == child;
    }

    private boolean isRightChild(Node child) {
        return child.parent.right == child;
    }

    private Node firstInorderNode(Node start) {
        Node current = start;
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public List<Node> inorderTraversal(Node start) {
        List<Node> traversal = new ArrayList<>();

        inorderTraversalRecursive(start, traversal);

        return traversal;
    }

    private void inorderTraversalRecursive(Node current, List<Node> traversal) {
        if (current.left != null) {
            inorderTraversalRecursive(current.left, traversal);
        }

        traversal.add(current);

        if (current.right != null) {
            inorderTraversalRecursive(current.right, traversal);
        }
    }

    public List<Node> preorderTraversal(Node start) {
        List<Node> traversal = new ArrayList<>();

        preorderTraversalRecursive(start, traversal);

        return traversal;
    }

    private void preorderTraversalRecursive(Node current, List<Node> traversal) {
        traversal.add(current);

        if (current.left != null) {
            preorderTraversalRecursive(current.left, traversal);
        }

        if (current.right != null) {
            preorderTraversalRecursive(current.right, traversal);
        }
    }

    public List<Node> postorderTraversal(Node start) {
        List<Node> traversal = new ArrayList<>();

        postorderTraversalRecursive(start, traversal);

        return traversal;
    }

    private void postorderTraversalRecursive(Node current, List<Node> traversal) {
        if (current.left != null) {
            postorderTraversalRecursive(current.left, traversal);
        }

        if (current.right != null) {
            postorderTraversalRecursive(current.right, traversal);
        }

        traversal.add(current);
    }

    // Test
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(6);
        tree.insert(9);
        tree.insert(7);
        tree.insert(8);
        tree.insert(4);
        tree.insert(10);
        tree.insert(12);
        tree.insert(25);

        BinaryTreePrinter.printNode(tree.root);
        tree.delete(6);
        BinaryTreePrinter.printNode(tree.root);
        tree.delete(7);
        BinaryTreePrinter.printNode(tree.root);

        List<Node> inorder = tree.inorderTraversal(tree.root);

        System.out.println("Inorder");
        System.out.print("[ ");
        for (Node node : inorder) {
            System.out.print(node + " ");
        }
        System.out.println("]");

        List<Node> preorder = tree.preorderTraversal(tree.root);

        System.out.println("Preorder");
        System.out.print("[ ");
        for (Node node : preorder) {
            System.out.print(node + " ");
        }
        System.out.println("]");

        List<Node> postorder = tree.postorderTraversal(tree.root);

        System.out.println("Postorder");
        System.out.print("[ ");
        for (Node node : postorder) {
            System.out.print(node + " ");
        }
        System.out.println("]");
    }

}
