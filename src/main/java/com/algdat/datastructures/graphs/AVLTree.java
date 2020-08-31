package com.algdat.datastructures.graphs;

import com.algdat.utils.AVLTreePrinter;

public class AVLTree {
    public class Node {
        public int value;
        public Node left, right;
        int height = 0;

        public Node(int value) {
            this.value = value;
        }
    }

    Node root;


    private int height(Node n) {
        if (n == null) {
            return -1;
        }

        return n.height;
    }

    private int balance(Node n) {
        if (n == null) {
            return -1;
        }
        return height(n.left) - height(n.right); 
    }

    private Node leftRotate(Node z) {
        Node y = z.right;
        Node b = y.left;

        y.left = z;
        z.right = b;

        z.height = Integer.max(height(z.left), height(z.right)) + 1;
        y.height = Integer.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private Node rightRotate(Node z) {
        Node y = z.left;
        Node b = y.right;

        y.right = z;
        z.left = b;

        z.height = Integer.max(height(z.left), height(z.right)) + 1;
        y.height = Integer.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = insertRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = insertRecursive(current.right, value);
        }

        current.height = 1 + Integer.max(height(current.left), height(current.right));

        int balance = balance(current);

        if (balance > 1) {
            if (value < current.left.value) {
                return rightRotate(current);
            } else {
                current.left = leftRotate(current.left);
                return rightRotate(current);
            }
        } else if (balance < -1) {
            if (value > current.right.value) {
                return leftRotate(current);
            } else {
                current.right = rightRotate(current.right);
                return leftRotate(current);
            }
        }


        return current;
    }


    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(10);
        AVLTreePrinter.printNode(tree.root);
        tree.insert(20);
        AVLTreePrinter.printNode(tree.root);
        tree.insert(30);
        AVLTreePrinter.printNode(tree.root);
        tree.insert(40);
        AVLTreePrinter.printNode(tree.root);
        tree.insert(50);
        AVLTreePrinter.printNode(tree.root);
        tree.insert(25);
        AVLTreePrinter.printNode(tree.root);
    }

}
