package com.wasp.datastructures;

import com.wasp.datastructures.util.List;
import java.util.Arrays;

public class ArrayList implements List {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double GROW_FACTOR = 1.5;
    private int index;
    private Object[] arr;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        arr = new Object[capacity];
        this.index = 0;
    }

    @Override
    public void add(Object value) {
        add(value, index);
    }

    @Override
    public void add(Object value, int index) {
        this.index++;
        checkIndexOOB(index);
        sizeUp();

        Object bef = get(index);
        Object aft;
        arr[index] = value;
        for (int i = index + 1; i < size(); i++) {
            aft = arr[i];
            arr[i] = bef;
            bef = aft;
        }
    }

    @Override
    public Object remove(int index) {
        checkIndexOOB(index);
        sizeDown();
        Object before = get(index);

        for (int i = index; i < size(); i++) {
            arr[i] = arr[i + 1];
        }
        this.index--;

        return before;
    }

    @Override
    public Object get(int index) {
        checkIndexOOB(index);
        return arr[index];
    }

    @Override
    public Object set(Object value, int index) {
        checkIndexOOB(index);
        Object before = arr[index];
        arr[index] = value;

        return before;
    }

    @Override
    public void clear() {
        arr = new Object[arr.length];
        index = 0;
    }

    @Override
    public int size() {
        return index;
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
            if (arr[i] == value) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object value) {
        for (int i = size() - 1; i >= 0; i--) {
            if (arr[i] == value) return i;
        }
        return -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            sb.append(arr[i].toString());

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
            throw new IndexOutOfBoundsException(index);
        }
    }

    private void sizeUp() {
        if (size() + 1 >= arr.length) {
            int capacity = (int) (arr.length * GROW_FACTOR);
            Object[] temp = Arrays.copyOf(arr, capacity);
            arr = temp;
        }
    }

    private void sizeDown() {
        if (size() - 1 <= arr.length / GROW_FACTOR) {
            int capacity = (int) (arr.length / GROW_FACTOR);
            Object[] temp = Arrays.copyOf(arr, capacity);
            arr = temp;
        }
    }
}
