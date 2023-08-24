package sh.miles.suketto.core.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrayUtilsTest {

    private String[] testArray;

    @BeforeEach
    public void setup() {
        testArray = new String[10];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = String.valueOf((char) (i + 65));
        }
    }

    @Test
    public void testContains() {
        assertTrue(ArrayUtils.contains(testArray, "A"), "The value 'A' is not contained within the array");
    }

    @Test
    public void testGrow() {
        assertEquals(ArrayUtils.grow(testArray, 1).length, testArray.length + 1, "The given arrays are not equal and should be");
    }

    @Test
    public void testAppend() {
        assertEquals("K", ArrayUtils.append(testArray, "K")[testArray.length], "the given values are not equal yet should be");
    }

    @Test
    public void testShrink() {
        String[] shrunk = ArrayUtils.shrink(testArray, 3, new Integer[]{0, 1, 2});
        for (int i = 0; i < shrunk.length; i++) {
            assertEquals(String.valueOf((char) (68 + i)), shrunk[i], "the values are not aligned");
        }
    }

    @Test
    public void testIndexOf() {
        assertEquals(0, ArrayUtils.indexOf(testArray, "A"));
        assertEquals(2, ArrayUtils.indexOf(testArray, "C"));
    }


}
