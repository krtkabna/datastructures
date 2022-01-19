package com.wasp.datastructures.list;

public class LinkedList implements List {
    private Node head;
    private int size;

    public LinkedList() {}

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
    }

    @Override
    public void add(Object value, int index) {
        checkIndexOOB(index);
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
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;

        size--;
        return null;
    }

    @Override
    public Object get(int index) {
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        return null;
    }

    @Override
    public Object set(Object value, int index) {
        checkIndexOOB(index);
        Node toSet = new Node(value);
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        toSet.next = temp.next;
        return temp;
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

    public String toString() {
        return null;
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

