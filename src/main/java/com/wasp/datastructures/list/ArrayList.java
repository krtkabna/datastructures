package com.wasp.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<E> extends AbstractList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double GROW_FACTOR = 1.5;
    private E[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        array = (E[]) new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public void add(E value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format(INDEX_OOB_MSG_FORMAT, index, size));
        }
        ensureCapacity();

        System.arraycopy(array, index, array, index + 1, size - index + 1);
        array[index] = value;

        size++;
    }

    @Override
    public E remove(int index) {
        checkIndexOOB(index);
        E before = get(index);

        if (size() - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size() - index);
        }
        size--;

        return before;
    }

    @Override
    public E get(int index) {
        checkIndexOOB(index);
        return array[index];
    }

    @Override
    public E set(E value, int index) {
        checkIndexOOB(index);
        E before = array[index];
        array[index] = value;

        return before;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[array.length];
        size = 0;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(array[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E value) {
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

    private void ensureCapacity() {
        if (size() + 1 >= array.length) {
            int capacity = (int) (array.length * GROW_FACTOR);
            E[] temp = Arrays.copyOf(array, capacity);
            array = temp;
        }
    }

    public Iterator<E> iterator() {
        return new Iterator<>() {
            //should be before first, -1
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                checkEmptyList();
                return get(index++);
            }

            @Override
            public void remove() {
                checkEmptyList();
                ArrayList.this.remove(index - 1);
            }

            private void checkEmptyList() {
                if (size == 0) {
                    throw new IteratingEmptyListException();
                }
            }
        };
    }
}
