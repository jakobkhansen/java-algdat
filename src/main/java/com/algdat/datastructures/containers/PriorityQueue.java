package com.algdat.datastructures.containers;


import java.util.ArrayList;
import java.util.List;

public class PriorityQueue<T> {
    boolean descending;

    private class QueueNode {
        T element;
        int priority;

        public QueueNode(T element, int priority) {
            this.element = element;
            this.priority = priority;
        }
    }

    List<QueueNode> queue = new ArrayList<>();

    public PriorityQueue(boolean descending) {
        this.descending = descending;
    }

    public void addElement(T element, int priority) {
        QueueNode node = new QueueNode(element, priority);

        int i = 0;
        while (i < queue.size()) {
            if (descending && node.priority > queue.get(i).priority) {
                break;
            } else if (node.priority < queue.get(i).priority) {
                break;
            }

            i++;
        }

        if (i == queue.size()) {
            queue.add(node);
        } else {
            queue.add(i, node);
        }
    }

    public T pop() {
        if (queue.size() == 0) {
            return null;
        }

        return queue.remove(0).element;
    }

    public void updatePriority(T element, int priority) {
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).element == element) {
                queue.remove(i);
                addElement(element, priority);
                break;
            }
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }


    public boolean contains(T element) {
        for (QueueNode node : queue) {
            if (node.element == element) {
                return true;
            }
        }
        return false;
    }
}
