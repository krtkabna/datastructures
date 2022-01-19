import com.wasp.datastructures.ArrayList;
import com.wasp.datastructures.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ArrayListTest {
    private List list;

    @Before
    public void init() {
        int[] test = {10, 20, 30, 40, 50, 50};
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
    public void testAdd() {
        list.add(1);
        assertEquals(1, list.get(6));
        list.add(2);
        assertEquals(2, list.get(7));
    }

    @Test
    public void testAddByIndex() {
        System.out.println(list.toString());
        list.add(2, 1);
        System.out.println(list.toString());
        assertEquals(2, list.get(1));
        //assert that the other elements have moved
        assertEquals(30, list.get(3));
        list.add(4, 3);
        assertEquals(4, list.get(3));
        System.out.println(list.toString());
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
    public void testIndexOf() {
        assertEquals(0, list.indexOf(10));
        assertEquals(4, list.indexOf(50));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(5, list.lastIndexOf(50));
    }

    @Test
    public void testToString() {
        assertEquals("[10, 20, 30, 40, 50, 50]", list.toString());
    }

}
