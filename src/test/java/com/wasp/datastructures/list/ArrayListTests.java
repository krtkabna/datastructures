package com.wasp.datastructures.list;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayListTests extends ListTests {

    @Override
    protected List<Integer> createList() {
        return new ArrayList<>();
    }

    @Test
    public void testNegativeCapacity() {
        assertThrows(NegativeArraySizeException.class, () -> new ArrayList<Integer>(-42));
    }

    @Test
    public void testEnsureCapacity() {
        ArrayList<Integer> arrayList = new ArrayList<>(3);
        assertEquals(3, arrayList.getCurrentCapacity());
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        assertEquals((int)(3 * 1.5), arrayList.getCurrentCapacity());
    }

//    @Test
//    public void testIteratorHasNext() {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(10);
//        list.add(20);
//        Iterator<Integer> iterator = list.iterator();
//
//        assertTrue(iterator.hasNext());
//        assertNotNull(iterator.next());
//
//        assertTrue(iterator.hasNext());
//        assertNotNull(iterator.next());
//
//        assertFalse(iterator.hasNext());
//    }
//
//    @Test
//    public void testIteratorHasNextOnEmptyList() {
//        ArrayList<Integer> list = new ArrayList<>();
//        Iterator iterator = list.iterator();
//
//        assertFalse(iterator.hasNext());
//    }
//
//    @Test
//    public void testIteratorNext() {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        Iterator<Integer> iterator = list.iterator();
//
//        assertNotNull(iterator.next());
//        assertThrows(IndexOutOfBoundsException.class, iterator::next);
//    }
//
//    @Test
//    public void testIteratorNextOnEmptyList() {
//        ArrayList<Integer> list = new ArrayList<>();
//        Iterator<Integer> iterator = list.iterator();
//
//        assertThrows(IteratingEmptyListException.class, iterator::next);
//    }
//
//    @Test
//    public void testIteratorRemove() {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        Iterator<Integer> iterator = list.iterator();
//
//        iterator.next();
//        iterator.remove();
//
//        assertEquals(0, list.size());
//    }
//
//    @Test
//    public void testIteratorRemoveOnEmptyList() {
//        ArrayList<Integer> list = new ArrayList<>();
//        Iterator iterator = list.iterator();
//
//        assertEquals(0, list.size());
//        assertThrows(IteratingEmptyListException.class, iterator::remove);
//    }
//
//    @Test
//    public void testForEach() {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(10);
//        list.add(20);
//        list.add(30);
//
//        for (Object o: list) {
//            assertNotNull(o);
//        }
//    }
}
