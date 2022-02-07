package com.wasp.datastructures.list;

import com.wasp.datastructures.exception.DataStructureIteratorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class ListTests {
    private List<Integer> list = createList();

    protected abstract List<Integer> createList();

    @BeforeEach
    public void init() {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(40);
        list.add(50);
    }

    @Test
    public void testAddIndexOOB() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(42, -1));

    }

    @Test
    public void testRemoveIndexOOB() {
        assertEquals(6, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(8));
    }

    @Test
    public void testGetIndexOOB() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-3));
        assertEquals(6, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(8));
    }

    @Test
    public void testAddToEmptyList() {

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
    public void testRemoveByIndex() {
        assertEquals(6, list.size());
        assertEquals(10, list.remove(0));
        assertEquals(20, list.get(0));
        assertEquals(5, list.size());
    }

    @Test
    public void testGetByIndex() {
        list.set(1, 0);
        list.set(2, 1);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testSetByIndex() {
        assertEquals(20, list.get(1));
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
    public void testContainsValue() {
        list.add(9);
        assertTrue(list.contains(9));
        assertFalse(list.contains(42));
    }

    @Test
    public void testIndexOfValue() {
        list.clear();
        list.add(10);
        list.add(20);
        list.add(40);
        assertEquals(0, list.indexOf(10));
        assertEquals(2, list.indexOf(40));
    }

    @Test
    public void testLastIndexOfValue() {
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

    @Test
    public void testIteratorHasNext() {
        list.clear();
        list.add(10);
        list.add(20);
        Iterator<Integer> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextOnEmptyList() {
        list.clear();
        Iterator iterator = list.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        Iterator<Integer> iterator = list.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            assertNotNull(iterator.next());
            count++;
        }
        assertEquals(count, list.size());
    }

    @Test
    public void testIteratorNextOnEmptyList() {
        list.clear();
        Iterator<Integer> iterator = list.iterator();

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorRemove() {
        list.clear();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();

        iterator.next();
        iterator.remove();

        assertEquals(0, list.size());
    }

    @Test
    public void testIteratorRemoveOnEmptyList() {
        list.clear();
        Iterator iterator = list.iterator();

        assertEquals(0, list.size());
        assertThrows(DataStructureIteratorException.class, iterator::remove);
    }

    @Test
    public void testForEach() {
        list.clear();
        list.add(10);
        list.add(20);
        list.add(30);

        for (Object o : list) {
            assertNotNull(o);
        }
    }

}
