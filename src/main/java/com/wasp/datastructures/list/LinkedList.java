package com.wasp.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<E> extends AbstractList<E> implements List<E>, Iterable {
    private Node head;
    private Node tail;

    public LinkedList() {
    }

    @Override
    public void add(E value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(INDEX_OOB_MSG_FORMAT + index);
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
    public E remove(int index) {
        checkIndexOOB(index);
        E result;
        if (size == 0) {            //empty list
            result = null;
        } else if (index == 0) {    //remove head and shift
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
    public E get(int index) {
        checkIndexOOB(index);
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public E set(E value, int index) {
        checkIndexOOB(index);
        Node toSet = new Node(value);
        Node temp = head;
        for (int i = 0; i < index; i++) {//find index
            temp = temp.next;
        }
        temp.data = toSet.data;         //change data
        return temp.data;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int indexOf(E value) {
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
    public int lastIndexOf(E value) {
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
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Node temp = head;
        for (int i = 0; i < size(); i++) {
            stringJoiner.add(temp.data.toString());
            temp = temp.next;
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            Node curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public E next() {
                checkEmptyList();

                E result = curr.data;
                curr = curr.next;
                return result;
            }

            @Override
            public void remove() {
                checkEmptyList();

                if (curr == head) {
                    head = head.next;
                    head.prev = null;
                } else if (curr == tail) {
                    tail = tail.prev;
                    tail.next = null;
                } else if (curr == null) {
                    size--;
                    return;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }

                size--;
            }

            private void checkEmptyList() {
                if (head == null) {
                    throw new IteratingEmptyListException();
                }
            }
        };
    }

    private class Node {
        E data;
        Node prev;
        Node next;

        Node(E o) {
            this.data = o;
        }
    }
}

