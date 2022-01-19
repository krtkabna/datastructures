import com.wasp.datastructures.ArrayList;
import com.wasp.datastructures.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ArrayListTest {
    private List list;

    @Before
    public void init() {
        int[] test = {10, 20, 30, 40, 40, 50};
        list = new ArrayList();
        {
            for (int i = 0; i < test.length; i++) {
                list.add(test[i]);
            }
        }
    }

    @Test
    public void testNegativeCapacity() {
        assertThrows(NegativeArraySizeException.class, () -> new ArrayList(-42));
    }

    @Test
    public void testIndexOOB() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(42, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(8));
    }

    @Test
    public void testAdd() {
        list.add(1);
        assertEquals(1, list.get(6));
        list.add(2);
        assertEquals(2, list.get(7));
    }

    @Test
    public void testAddByIndex() {
        list.add(2, 1);
        assertEquals(2, list.get(1));
        //assert that the other elements have moved
        assertEquals(30, list.get(3));
        assertEquals(40, list.get(4));
    }

    @Test
    public void testRemove() {
        assertEquals(10, list.remove(0));
        assertEquals(20, list.get(0));
    }

    @Test
    public void testGet() {
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
    }

    @Test
    public void testSet() {
        list.set(13, 1);
        assertEquals(13, list.get(1));
    }

    @Test
    public void testClear() {
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testSize() {
        list.remove(4);
        assertEquals(5, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(list.contains(10));
        assertTrue(list.contains(50));
        assertFalse(list.contains(42));
    }

    @Test
    public void testIndexOf() {
        assertEquals(0, list.indexOf(10));
        assertEquals(3, list.indexOf(40));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(4, list.lastIndexOf(40));
    }

    @Test
    public void testToString() {
        assertEquals("[10, 20, 30, 40, 40, 50]", list.toString());
    }

}
