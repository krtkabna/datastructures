import com.wasp.datastructures.ArrayList;
import com.wasp.datastructures.LinkedList;
import com.wasp.datastructures.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {
    int[] test = {10, 20, 30, 40, 50, 50};
    List arrayList = new ArrayList();
    {
        for (int i = 0; i < test.length; i++) {
            arrayList.add(test[i]);
        }
    }
    List linkedList = new LinkedList();

    @Test
    public void testAdd() {
        arrayList.add(1);
        assertEquals(1, arrayList.get(5));
        arrayList.add(2);
        assertEquals(2, arrayList.get(6));
    }

    @Test
    public void testAddByIndex() {
        System.out.println(arrayList.toString());
        arrayList.add(2, 1);
        System.out.println(arrayList.toString());
        assertEquals(2, arrayList.get(1));
        //assert that the other elements have moved
        assertEquals(30, arrayList.get(3));
        arrayList.add(4, 3);
        assertEquals(4, arrayList.get(3));
        System.out.println(arrayList.toString());
    }

    @Test
    public void testRemove() {
        assertEquals(10, arrayList.remove(0));
        assertEquals(20, arrayList.get(0));
    }

    @Test
    public void testGet() {
        assertEquals(10, arrayList.get(0));
        assertEquals(20, arrayList.get(1));
    }

    @Test
    public void testSet() {
        arrayList.set(13, 1);
        assertEquals(13, arrayList.get(1));
    }

    @Test
    public void testClear() {
        arrayList.clear();
        assertEquals(0, arrayList.size());
    }

    @Test
    public void testSize() {
        arrayList.remove(4);
        assertEquals(5, arrayList.size());
    }

    @Test
    public void testIndexOf() {
        assertEquals(0, arrayList.indexOf(10));
        assertEquals(4, arrayList.indexOf(50));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(5, arrayList.lastIndexOf(50));
    }

    @Test
    public void testToString() {
        assertEquals("[10, 20, 30, 40, 50, 50]", arrayList.toString());
    }
}
