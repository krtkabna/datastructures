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
        Node tail = new Node(value);
        if (head == null) {
            head = new Node(value);
        } else {
            Node curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(tail);
        }
        size++;
    }

    @Override
    public void add(Object value, int index) {
        checkIndexOOB(index);
        Node toAdd = new Node(value);
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        toAdd.setNext(temp.getNext());
        temp.setNext(toAdd);

        size++;
    }

    @Override
    public Object remove(int index) {
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        temp.setNext(temp.getNext().getNext());

        size--;
        return null;
    }

    @Override
    public Object get(int index) {
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        return null;
    }

    @Override
    public Object set(Object value, int index) {
        checkIndexOOB(index);
        Node toSet = new Node(value);
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.getNext();
        }
        toSet.setNext(temp.getNext());
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

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
