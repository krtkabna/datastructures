package com.wasp.datastructures.list;

import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedListTests extends ListTests {

    @Override
    protected List<Integer> createList() {
        return new LinkedList<>();
    }

    @Test
    public void testIteratorHasNext() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        Iterator iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextOnEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();
        Iterator iterator = list.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator iterator = list.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            assertNotNull(iterator.next());
            count++;
        }
        assertEquals(count, list.size());

        assertThrows(NullPointerException.class, () -> iterator.next());
    }

    @Test
    public void testIteratorNextOnEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();
        Iterator iterator = list.iterator();

        assertThrows(IteratingEmptyListException.class, () -> iterator.next());
    }

    @Test
    public void testIteratorRemove() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        Iterator iterator = list.iterator();

        iterator.next();
        iterator.remove();

        assertEquals(0, list.size());
    }

    @Test
    public void testIteratorRemoveOnEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();
        Iterator iterator = list.iterator();

        assertEquals(0, list.size());
        assertThrows(IteratingEmptyListException.class, () -> iterator.remove());
    }

    @Test
    public void testForEach() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        for (Object o: list) {
            assertNotNull(o);
        }
    }
}
