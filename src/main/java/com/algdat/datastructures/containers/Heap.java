package com.algdat.datastructures.containers;

import java.util.ArrayList;
import java.util.List;

public class Heap<T extends Comparable<T>> {
   List<T> array = new ArrayList<>();


   private int getParent(int index) {
      if (index == 0) {
         return -1;
      }

      return ((index - 1) / 2);
   }

   private int getLeftChild(int index) {
      if ((index * 2 + 1) >= array.size()) {
         return -1;
      }

      return index * 2 + 1;
   }

   private int getRightChild(int index) {
      if ((index * 2 + 2) >= array.size()) {
         return -1;
      }

      return index * 2 + 2;
   }

   private void bubbleUp(int index) {
      int current = index;
      int parent = getParent(current);
      while (parent != -1 && greaterThan(array.get(parent), array.get(current))) {
         swap(parent, current);
         current = parent;
         parent = getParent(current);
      }
   }

   private void bubbleDown(int index) {
      int left = getLeftChild(index);
      int right = getRightChild(index);
      int larger = smallestChild(left, right);

      if (larger != -1 && lessThan(array.get(larger), array.get(index))) {
         swap(larger, index);
         bubbleDown(larger);
      }
   }

   public T removeMin() {
      T toBeRemoved = array.get(0);

      swap(0, array.size() - 1);
      array.remove(array.size() - 1);

      bubbleDown(0);

      return toBeRemoved;
   }


   public void insert(T element) {
      array.add(element);
      bubbleUp(array.size() - 1);
   }

   private void swap(int index1, int index2) {
      T temp = array.get(index1);
      array.set(index1, array.get(index2));
      array.set(index2, temp);
   }

   private boolean greaterThan(T first, T second) {
      return first.compareTo(second) > 0;
   }

   private boolean lessThan(T first, T second) {
      return first.compareTo(second) < 0;
   }

   private int smallestChild(int left, int right) {
      if (left >= array.size() || left == -1 && right >= array.size() || left == -1) {
         return -1;
      }

      if (left >= array.size() || left == -1) {
         return right;
      }

      if (right >= array.size() || right == -1) {
         return left;
      }

      if (lessThan(array.get(left), array.get(right))) {
         return left;
      }

      return right;
   }

   public String toString() {
      String ret = "\nPrinting Nodes: \n";
      for (int i = 0; i < array.size(); i++) {
         ret += "Node: " + array.get(i).toString() + "\n";
         int left = getLeftChild(i);
         int right = getRightChild(i);
         int parent = getParent(i);
         if (left != -1) {
            ret += "Left: " + array.get(left) + "\n";
         }

         if (right != -1) {
            ret += "Right: " + array.get(right) + "\n";
         }

         if (parent != -1) {
            ret += "Parent: " + array.get(parent) + "\n";
         }
         ret += "\n";
      }

      return ret;
   }

   public static void main(String[] args) {
      Heap<Integer> heap = new Heap<>();

      heap.insert(3);
      heap.insert(2);
      heap.insert(4);
      heap.insert(5);
      heap.insert(0);


      System.out.println(heap.removeMin());
      System.out.println(heap.removeMin());
      System.out.println(heap.removeMin());
   }
}
