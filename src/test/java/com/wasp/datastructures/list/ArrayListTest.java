package com.wasp.datastructures.list;

import org.junit.Before;

public class ArrayListTest extends ListTest {

    @Before
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
