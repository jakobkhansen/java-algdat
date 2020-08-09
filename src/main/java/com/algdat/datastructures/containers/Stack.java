package com.algdat.datastructures.containers;

import java.util.LinkedList;

public class Stack<T> {
    LinkedList<T> stack = new LinkedList<>();

    public void push(T element) {
        stack.add(element);
    }

    public T pop() {
        if (stack.size() == 0) {
            return null;
        }

        return stack.removeLast();
    }

    public T peek() {
        return stack.getLast();
    }



    // Test
    public static void main(String[] args) {
        Stack<String> testStack = new Stack<>();

        testStack.push("First element");
        testStack.push("Second element");
        testStack.push("Third element");
        testStack.push("Fourth element");

        System.out.println(testStack.pop());

        testStack.push("Fifth element");

        System.out.println(testStack.pop());
        System.out.println(testStack.pop());
        System.out.println(testStack.pop());
        System.out.println(testStack.pop());

    }
}
