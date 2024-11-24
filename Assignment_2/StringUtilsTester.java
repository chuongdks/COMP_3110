import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class StringUtilsTester {

    // a. join(List<?> list, String separator, int startIndex, int endIndex)
    @Test
    public void testJoin() {
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        assertEquals("apple-banana", StringUtils.join(list, "-", 0, 2));
        assertEquals("banana-cherry", StringUtils.join(list, "-", 1, 3));
        assertEquals("", StringUtils.join(list, "-", 2, 2)); // Empty range
        assertEquals("apple", StringUtils.join(list, "-", 0, 1)); // Single element
    }

    // b. lastIndexOf(CharSequence seq, int searchChar)
    @Test
    public void testLastIndexOf() {
        assertEquals(3, StringUtils.lastIndexOf("hello", 'l'));
        assertEquals(-1, StringUtils.lastIndexOf("hello", 'z')); // Character not found
        assertEquals(0, StringUtils.lastIndexOf("a", 'a')); // Single character
        assertEquals(-1, StringUtils.lastIndexOf("", 'a')); // Empty string
    }

    // c. leftPad(String str, int size, char padChar)
    @Test
    public void testLeftPad() {
        assertEquals("00abc", StringUtils.leftPad("abc", 5, '0'));
        assertEquals("abc", StringUtils.leftPad("abc", 3, '0')); // No padding needed
        assertEquals("000", StringUtils.leftPad("", 3, '0')); // Pad empty string
        assertEquals("abc", StringUtils.leftPad("abc", 2, '0')); // Size less than string length
    }

    // d. repeat(String str, int repeat)
    @Test
    public void testRepeat() {
        assertEquals("abcabc", StringUtils.repeat("abc", 2));
        assertEquals("", StringUtils.repeat("abc", 0)); // Repeat zero times
        assertEquals("", StringUtils.repeat("", 5)); // Repeat empty string
        assertEquals("a", StringUtils.repeat("a", 1)); // Single repeat
    }

    // e. reverse(String str)
    @Test
    public void testReverse() {
        assertEquals("cba", StringUtils.reverse("abc"));
        assertEquals("", StringUtils.reverse("")); // Reverse empty string
        assertEquals("a", StringUtils.reverse("a")); // Single character
        assertEquals("!dlroW ,olleH", StringUtils.reverse("Hello, World!")); // Special characters
    }

    // f. split(String str, char separatorChar)
    @Test
    public void testSplit() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.split("a.b.c", '.'));
        assertArrayEquals(new String[]{"a", "b"}, StringUtils.split("a.b.", '.')); // Trailing separator
        assertArrayEquals(new String[]{}, StringUtils.split("", '.')); // Empty string
        assertArrayEquals(new String[]{"abc"}, StringUtils.split("abc", '.')); // No separator
    }

    // g. startsWith(CharSequence str, CharSequence prefix)
    @Test
    public void testStartsWith() {
        assertTrue(StringUtils.startsWith("abcdef", "abc"));
        assertFalse(StringUtils.startsWith("abcdef", "def")); // Non-matching prefix
        assertFalse(StringUtils.startsWith("", "a")); // Empty string
        assertTrue(StringUtils.startsWith("a", "a")); // Single character match
    }

    // h. substring(String str, int start)
    @Test
    public void testSubstring() {
        assertEquals("cdef", StringUtils.substring("abcdef", 2));
        assertEquals("abcdef", StringUtils.substring("abcdef", 0)); // Start from 0
        assertEquals("", StringUtils.substring("abcdef", 6)); // End of string
        assertEquals("", StringUtils.substring("", 0)); // Empty string
    }

    // i. trim(String str)
    @Test
    public void testTrim() {
        assertEquals("abc", StringUtils.trim(" abc "));
        assertEquals("abc", StringUtils.trim("abc")); // No spaces
        assertEquals("", StringUtils.trim("   ")); // Only spaces
        assertEquals("", StringUtils.trim("")); // Empty string
    }

    // j. upperCase(String str)
    @Test
    public void testUpperCase() {
        assertEquals("ABC", StringUtils.upperCase("abc"));
        assertEquals("ABC", StringUtils.upperCase("ABC")); // Already uppercase
        assertEquals("", StringUtils.upperCase("")); // Empty string
        assertEquals("HELLO, WORLD!", StringUtils.upperCase("Hello, World!")); // Special characters
    }
}
