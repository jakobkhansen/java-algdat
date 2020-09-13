package com.algdat.algorithms.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.algdat.datastructures.containers.PriorityQueue;

public class HuffmanCoding {
   static class Node {
      int value;
      Character ch;

      Node left, right;

      String encoding;

      // Internal node
      public Node(int value, Node left, Node right) {
         this.ch = null;
         this.value = value;
         this.left = left;
         this.right = right;
      }

      // Leaf node
      public Node(int value, Character ch) {
         this.ch = ch;
         this.value = value;
         this.left = null;
         this.right = null;
      }
   }




   public static List<Node> buildNodes(String input) {
      HashMap<Character, Integer> freq = new HashMap<>();
      List<Node> nodes = new ArrayList<>();

      for (Character ch : input.toCharArray()) {
         int newVal = freq.getOrDefault(ch, 0) + 1;
         freq.put(ch, newVal);
      }

      for (Character ch : freq.keySet()) {
         nodes.add(new Node(freq.get(ch), ch));
      }

      return nodes;
   }

   public static Node buildHuffmanTree(List<Node> nodes) {
      PriorityQueue<Node> pq = new PriorityQueue<>(false);

      for (Node node : nodes) {
         pq.addElement(node, node.value);
      }

      while (pq.size() > 1) {
         Node smallest1 = pq.pop();
         Node smallest2 = pq.pop();

         Node newNode = new Node(smallest1.value + smallest2.value, smallest1, smallest2);
         pq.addElement(newNode, newNode.value);
      }

      Node root = pq.pop();

      buildEncodings(root);

      return root;
   }

   public static void buildEncodings(Node root) {
      String path = "";
      buildEncodings(root, path);
   }

   public static void buildEncodings(Node current, String path) {
      current.encoding = path;
      if (current.ch == null) {
         buildEncodings(current.left, path + "0");
         buildEncodings(current.right, path + "1");
      }
   }

   public static void printEncodings(Node root) {
      if (root.ch != null) {
         System.out.println(root.ch + ": " + root.encoding);
      } else {
         printEncodings(root.left);
         printEncodings(root.right);
      }
   }

   public static String encodeString(String string, List<Node> nodes) {
      String encoding = "";

      for (Character ch : string.toCharArray()) {
         for (Node node : nodes) {
            if (node.ch == ch) {
               encoding += node.encoding;
               break;
            }
         }
      }

      return encoding;
   }

   public static String decodeString(String bitString, Node root) {
      String output = "";
      char[] bitStringChars = bitString.toCharArray();

      for (int i = 0; i < bitStringChars.length;) {
         Node current = root;

         while (current.ch == null) {
            if (bitStringChars[i] == '0') {
               current = current.left;
               i++;

            } else if (bitStringChars[i] == '1') {
               current = current.right;
               i++;
            }
         }
         output += current.ch;
      }

      return output;
   }

   public static void main(String[] args) {
      String sentence = "det er veldig vanskelig aa finne paa en eksempelsetning";

      List<Node> nodes = buildNodes(sentence);

      Node huffmanTreeRoot = buildHuffmanTree(nodes);

      printEncodings(huffmanTreeRoot);

      String encoding = encodeString(sentence, nodes);
      System.out.println("Encoding: " + encoding);

      String decoded = decodeString(encoding, huffmanTreeRoot);
      System.out.println("Decoded: " + decoded);
   }
}
