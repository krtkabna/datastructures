package com.wasp.datastructures.list;

import java.util.Iterator;
import java.util.Objects;

public class LinkedList implements List, Iterable {
    private static final String INDEX_OOB_MSG = "Index out of bounds: ";
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
    }

    @Override
    public void add(Object value) {
        add(value, size);
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(INDEX_OOB_MSG + index);
        }
        Node newNode = new Node(value);
        if (head == null) {
            head = tail = newNode;
        } else if (index == 0) {    //add to head
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == size) { //add to tail
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;
        } else {                    //generic case
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next; //find previous for newNode
            }
            newNode.next = current.next;
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
    }

    @Override
    public Object remove(int index) {
        checkIndexOOB(index);
        Object result;
        if (size == 0) {            //empty list
            result = null;
} else if (index == 0) {            //remove head and shift
            result = head.data;
            head = head.next;
            head.prev = null;
        } else if (index == size) {
            result = tail.data;
            tail = tail.prev;
            tail.next = null;
        } else {                    //traverse and update links
            Node curr = head;
            for (int i = 1; i < index; i++) {
                curr = curr.next;   //find element to remove
            }
            result = curr.data;
            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;
        }
        size--;
        return result;
    }

    @Override
    public Object get(int index) {
        checkIndexOOB(index);
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public Object set(Object value, int index) {
        checkIndexOOB(index);
        Node toSet = new Node(value);
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.data = toSet.data;
        return temp.data;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(Object value) {
        Node temp = head;
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(temp.data, value)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        Node temp = head;
        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(temp.data, value)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node temp = head;
        for (int i = 0; i < size(); i++) {
            sb.append(temp.data.toString());
            if (i == size() - 1) {
                sb.append("]");
            } else {
                sb.append(", ");
            }
            temp = temp.next;
        }
        return sb.toString();
    }

    private void checkIndexOOB(int index) {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    //todo
    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }

            @Override
            public void remove() {
                Iterator.super.remove();
            }
        };
    }

    private class Node {
        Object data;
        Node prev;
        Node next;

        Node(Object o) {
            this.data = o;
        }
    }
}

