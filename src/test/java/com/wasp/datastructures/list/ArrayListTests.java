package com.wasp.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTests extends ListTests {

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
        ArrayList arrayList = new ArrayList(3);
        assertEquals(3, arrayList.getCurrentCapacity());
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        assertEquals((int)(3 * 1.5), arrayList.getCurrentCapacity());
    }
}
