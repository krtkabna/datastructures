package com.wasp.datastructures.list;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public abstract class ListTest {
    List list;

    @Test
    public void testNegativeCapacity() {
        assertThrows(NegativeArraySizeException.class, () -> new ArrayList(-42));
    }

    @Test
    public void testAddIndexOOB() {
        assertThrows(IndexOutOfBoundsException.class,
            () -> list.add(42, -1));

    }

    @Test
    public void testRemoveIndexOOB() {
        assertEquals(6, list.size());
        assertThrows(IndexOutOfBoundsException.class,
            () -> list.remove(8));
    }

    @Test
    public void testAddToLastIndex() {
        assertEquals(6, list.size());
        list.add(1);
        assertEquals(1, list.get(6));
        assertEquals(7, list.size());
    }

    @Test
    public void testAddByIndex() {
        assertEquals(6, list.size());
        list.add(2, 1);
        assertEquals(2, list.get(1));
        assertEquals(7, list.size());
        //assert that the other elements have moved
        assertEquals(30, list.get(3));
        assertEquals(40, list.get(4));
    }

    @Test
    public void testRemove() {
        assertEquals(6, list.size());
        assertEquals(10, list.remove(0));
        assertEquals(20, list.get(0));
        assertEquals(5, list.size());
    }

    @Test
    public void testGet() {
        list.set(1, 0);
        list.set(2, 1);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testSet() {
        list.set(13, 1);
        assertEquals(13, list.get(1));
    }

    @Test
    public void testClear() {
        assertEquals(6, list.size());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(6, list.size());
        list.remove(4);
        assertEquals(5, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertEquals(6, list.size());
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContains() {
        list.add(9);
        assertTrue(list.contains(9));
        assertFalse(list.contains(42));
    }

    @Test
    public void testIndexOf() {
        list.clear();
        list.add(10);
        list.add(20);
        list.add(40);
        assertEquals(0, list.indexOf(10));
        assertEquals(2, list.indexOf(40));
    }

    @Test
    public void testLastIndexOf() {
        list.clear();
        list.add(10);
        list.add(20);
        list.add(20);
        list.add(30);
        assertEquals(2, list.lastIndexOf(20));
    }

    @Test
    public void testToString() {
        assertEquals("[10, 20, 30, 40, 40, 50]", list.toString());
    }

}
