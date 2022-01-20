package com.wasp.datastructures.list;

import java.util.Objects;

public class LinkedList implements List {
    private static final String INDEX_OOB_MSG = "Index out of bounds: ";
    private Node head;
    private int size;

    public LinkedList() {
    }

    @Override
    public void add(Object value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node tail = head;
            while(tail.next != null){
                tail = tail.next;
            }
            tail.next = newNode;
        }
        size++;
    }

    @Override
    public void add(Object value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(INDEX_OOB_MSG + index);
        }
        Node temp = new Node(value);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        temp.next = current.next;
        current.next = temp;

        size++;
    }

    @Override
    public Object remove(int index) {
        checkIndexOOB(index);
        Node curr = head;
        Object result;
        if (index == 0) {
            result = curr.data;
            curr.next = head;
        } else {
            Node prev = head;
            for (int i = 1; i < index; i++) {
                prev = curr;
                curr = curr.next;
            }
            result = curr.data;
            prev.next = curr.next.next;
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

    class Node {
        Object data;
        Node next;

        Node(Object o) {
            this.data = o;
            next = null;
        }
    }
}

