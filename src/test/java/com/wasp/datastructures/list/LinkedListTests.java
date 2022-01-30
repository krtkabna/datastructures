package com.wasp.datastructures.list;

public class LinkedListTests extends ListTests {

    @Override
    protected List<Integer> createList() {
        return new LinkedList<>();
    }

}
