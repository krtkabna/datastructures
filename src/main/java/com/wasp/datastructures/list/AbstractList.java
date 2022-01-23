package com.wasp.datastructures.list;

public abstract class AbstractList<E> implements List<E> {
    static final String INDEX_OOB_MSG_FORMAT = "Index %s is out of bounds in [0, %s]";
    int size;

    @Override
    public void add(E value) {
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
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    void checkIndexOOB(int index) {
        if (index < 0 || (index >= size())) {
            throw new IndexOutOfBoundsException(String.format(INDEX_OOB_MSG_FORMAT, index, size - 1));
        }
    }
}
