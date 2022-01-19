package com.wasp.datastructures;

import com.wasp.datastructures.util.List;

public class LinkedList implements List {
    private Node head;
    private int size;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public void add(Object value) {
        if (head == null) {
            head = new Node(value);
        } else {

        }
        size++;
    }

    @Override
    public void add(Object value, int index) {
        checkIndexOOB(index);
        int count = 0;
        Node temp = head, prev = null;
        while (temp != null && count < index) {
            prev = temp;
            temp = temp.next;
            count++;
        }

        size++;
    }

    @Override
    public Object remove(int index) {
        size--;
        return null;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(Object value, int index) {
        return null;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object value) {
        return 0;
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
