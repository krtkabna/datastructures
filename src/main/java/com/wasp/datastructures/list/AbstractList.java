package com.wasp.datastructures.list;

public abstract class AbstractList implements List {
    static final String INDEX_OOB_MSG = "Index out of bounds: ";
    int size;

    @Override
    public void add(Object value) {
        add(value, size);
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

    void checkIndexOOB(int index) {
        if (index < 0 || (index >= size())) {
            throw new IndexOutOfBoundsException(INDEX_OOB_MSG + index);
        }
    }
}
