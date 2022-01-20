package com.wasp.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList extends AbstractList implements List, Iterable {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double GROW_FACTOR = 1.5;
    private Object[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        array = new Object[initialCapacity];
        this.size = 0;
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

        if (size() - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size() - index);
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
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size(); i++) {
            sj.add(String.valueOf(array[i]));
        }
        return sj.toString();
    }

    //for test purposes only
    int getCurrentCapacity() {
        return array.length;
    }

    private void ensureCapasity() {
        if (size() + 1 >= array.length) {
            int capacity = (int) (array.length * GROW_FACTOR);
            Object[] temp = Arrays.copyOf(array, capacity);
            array = temp;
        }
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Object next() {
                return get(index++);
            }

            @Override
            public void remove() {
                ArrayList.this.remove(index - 1);
            }
        };
    }
}
