package com.wasp.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest extends ListTest {

    @Override
    protected List createList() {
        return new ArrayList();
    }

    @Test
    public void testNegativeCapacity() {
        assertThrows(NegativeArraySizeException.class, () -> new ArrayList(-42));
    }

    @Test
    public void testEnsureCapacity() {
        //todo
    }
}
