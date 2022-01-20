package com.wasp.datastructures.list;

import org.junit.jupiter.api.BeforeEach;

public class ArrayListTest extends ListTest {

    @BeforeEach
    public void init() {
        list = new ArrayList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(40);
        list.add(50);
    }
}
