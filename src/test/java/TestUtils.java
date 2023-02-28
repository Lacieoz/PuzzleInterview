import org.junit.Test;
import rrpuzzle.utils.Utils;

import static org.junit.Assert.*;

/**
 * Test for Utils.java
 */
public class TestUtils {

    @Test
    public void testBothStringsNull () {
        String s1 = null;
        String s2 = null;
        assertTrue(Utils.equalStringWithNull(s1, s2));
    }

    @Test
    public void testStringsNullAndValue1 () {
        String s1 = null;
        String s2 = "Ciao";
        assertFalse(Utils.equalStringWithNull(s1, s2));
    }

    @Test
    public void testStringsNullAndValue2 () {
        String s1 = "Ciao";
        String s2 = null;
        assertFalse(Utils.equalStringWithNull(s1, s2));
    }

    @Test
    public void testStringsBothValueEqual () {
        String s1 = "Ciao";
        String s2 = "Ciao";
        assertTrue(Utils.equalStringWithNull(s1, s2));
    }

    @Test
    public void testStringsBothValueDifferent () {
        String s1 = "Ciao";
        String s2 = "Arrivederci";
        assertFalse(Utils.equalStringWithNull(s1, s2));
    }

    @Test
    public void testBothIntegersNull () {
        Integer i1 = null;
        Integer i2 = null;
        assertTrue(Utils.equalIntegerWithNull(i1, i2));
    }

    @Test
    public void testIntegersNullAndValue1 () {
        Integer i1 = null;
        Integer i2 = 1;
        assertFalse(Utils.equalIntegerWithNull(i1, i2));
    }

    @Test
    public void testIntegersNullAndValue2 () {
        Integer i1 = 1;
        Integer i2 = null;
        assertFalse(Utils.equalIntegerWithNull(i1, i2));
    }

    @Test
    public void testIntegersBothValueEqual () {
        Integer i1 = 1;
        Integer i2 = 1;
        assertTrue(Utils.equalIntegerWithNull(i1, i2));
    }

    @Test
    public void testIntegersBothValueDifferent () {
        Integer i1 = 1;
        Integer i2 = 2;
        assertFalse(Utils.equalIntegerWithNull(i1, i2));
    }
}
