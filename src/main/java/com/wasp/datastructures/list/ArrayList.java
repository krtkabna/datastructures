package com.wasp.datastructures.list;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList implements List {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double GROW_FACTOR = 1.5;
    private static final String INDEX_OOB_MSG = "Index out of bounds: ";
    private int size;
    private Object[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        array = new Object[capacity];
        this.size = 0;
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
        ensureCapasity();

        System.arraycopy(array, index, array, index + 1, size - index + 1);
        array[index] = value;

        size++;
    }

    @Override
    public Object remove(int index) {
        checkIndexOOB(index);
        Object before = get(index);

        for (int i = index; i < size(); i++) {
            array[i] = array[i + 1];
        }
        this.size--;

        return before;
    }

    @Override
    public Object get(int index) {
        checkIndexOOB(index);
        return array[index];
    }

    @Override
    public Object set(Object value, int index) {
        checkIndexOOB(index);
        Object before = array[index];
        array[index] = value;

        return before;
    }

    @Override
    public void clear() {
        array = new Object[array.length];
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
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for (int i = size() - 1; i >= 0; i--) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            sb.append(array[i].toString());

            if (size() - i == 1) {
                sb.append("]");
            } else {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private void checkIndexOOB(int index) {
        if (index < 0 || (index >= size())) {
            throw new IndexOutOfBoundsException(INDEX_OOB_MSG + index);
        }
    }

    private void ensureCapasity() {
        if (size() + 1 >= array.length) {
            int capacity = (int) (array.length * GROW_FACTOR);
            Object[] temp = Arrays.copyOf(array, capacity);
            array = temp;
        }
    }
}
