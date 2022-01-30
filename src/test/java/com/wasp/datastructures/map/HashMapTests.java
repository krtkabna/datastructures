package com.wasp.datastructures.map;

import com.wasp.datastructures.exception.DataStructureIteratorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashMapTests {
    private static final Map<Integer, String> EMPTY_MAP = new HashMap<>();
    private Map<Integer, String> map = new HashMap<>();
    private Iterator<Map.Entry<Integer, String>> iterator = map.iterator();

    @BeforeEach
    public void init() {
        map.put(2, "w");
        map.put(3, "a");
        map.put(1, "s");
        map.put(4, "p");
    }


    @Test
    void put() {
        assertEquals(4, map.size());
        assertEquals(map.get(4), map.put(4, "d"));
        assertEquals(4, map.size());
    }

    @Test
    void get() {
        map.put(4, "d");
        assertEquals("d", map.get(4));
    }

    @Test
    void remove() {
        map.put(5, "i");
        assertEquals("i", map.remove(5));
        assertFalse(map.containsKey(5));
    }

    @Test
    void containsKey() {
        assertTrue(map.containsKey(1));
    }

    @Test
    void size() {
        assertEquals(4, map.size());
        map.put(5, "i");
        map.put(6, "s");
        assertEquals(6, map.size());
    }

    @Test
    void entrySetSize() {
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        assertEquals(4, map.size());
        assertEquals(4, entrySet.size());

        for (Map.Entry<Integer, String> entry : entrySet) {
            assertTrue(entrySet.contains(entry));
        }
    }

    @Test
    void entrySetContains() {
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        for (Map.Entry<Integer, String> entry : map) {
            assertTrue(entrySet.contains(entry));
        }
    }

    @Test
    void iteratorHasNext() {
        map.remove(3);
        map.remove(4);
        assertEquals(2, map.size());

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorHasNextOnEmptyMap() {
        assertEquals(0, EMPTY_MAP.size());

        assertFalse(EMPTY_MAP.iterator().hasNext());
    }

    @Test
    void iteratorNextBucketOrder() {
        Map<String, Integer> map = new HashMap<>();
        Map.Entry<String, Integer> entry;
        Iterator<Map.Entry<String, Integer>> iterator = map.iterator();

        while (iterator.hasNext()) {
            entry = iterator.next();
            System.out.println(entry);
        }
    }

    @Test
    void iteratorNextOnEmptyMap() {
        assertThrows(NoSuchElementException.class, () -> EMPTY_MAP.iterator().next());
    }

    @Test
    void iteratorRemove() {
        iterator.next();
        iterator.remove();

        assertEquals(3, map.size());
    }

    @Test
    void iteratorRemoveWithoutCallingNext() {
        RuntimeException e = assertThrows(DataStructureIteratorException.class, () -> iterator.remove());
        assertEquals("Calling remove() before calling next()", e.getMessage());
    }
}
