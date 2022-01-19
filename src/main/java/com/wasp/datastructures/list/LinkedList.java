package com.wasp.datastructures.list;

public class LinkedList implements List {
    private Node head;
    private int size;

    public LinkedList() {}

    @Override
    public void add(Object value) {
        Node newNode = new Node(value);
        if(this.head == null) {
            head = newNode;
        } else {
            Node current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
    }

    @Override
    public void add(Object value, int index) {

    }

    @Override
    public Object remove(int index) {
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

    }

    @Override
    public int size() {
        return 0;
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

    class Node {
        private Object data;
        private Node next;
        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }
}
