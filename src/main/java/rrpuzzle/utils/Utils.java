package rrpuzzle.utils;

public class Utils {

    /**
     * This function does the equal operation between 2 String considering even null values
     */
    public static boolean equalStringWithNull (String s1, String s2) {
        if (s1 == null && s2 == null)
            return true;
        return s1 != null && s1.equals(s2);
    }

    /**
     * This function does the equal operation between 2 Integer considering even null values
     */
    public static boolean equalIntegerWithNull (Integer i1, Integer i2) {
        if (i1 == null && i2 == null)
            return true;
        return i1 != null && i1.equals(i2);
    }
}
