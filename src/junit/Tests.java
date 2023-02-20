package junit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@SuppressWarnings("PMD")
public class Tests {

    @Test
    public void equalityExamples() {
        assertTrue(1 == 1);
        assertFalse(1 == 2);

        Integer x2 = 128;
        Integer y2 = 128;
        assertTrue(x2 == y2);

    }

    @Test
    public void assertThatAndAssertEqualsExample() {
        assertEquals(1 + 2, 3);
        assertEquals(1 + 2, 4);
        assertThat(1 + 2, is(3));
    }

    @Test
    public void findsSpecialNumbers() {
        assertThat(Code.isSpecial(0), is(true));
        assertThat(Code.isSpecial(1), is(true));
        assertThat(Code.isSpecial(2), is(true));
        assertThat(Code.isSpecial(3), is(true));
        assertThat(Code.isSpecial(4), is(false));
        assertThat(Code.isSpecial(11), is(true));
        assertThat(Code.isSpecial(15), is(false));
        assertThat(Code.isSpecial(36), is(true));
        assertThat(Code.isSpecial(37), is(false));

        // other test cases for isSpecial() method
    }

    @Test
    public void findsLongestStreak() {
        assertThat(Code.longestStreak(""), is(0));
        assertThat(Code.longestStreak("a"), is(1));
        assertThat(Code.longestStreak("cacbcfchcjckclco"), is(1));
        assertThat(Code.longestStreak("abcbccc"), is(3));
        assertThat(Code.longestStreak("abbcccdddbbddbb"), is(3));
        assertThat(Code.longestStreak("aaaaccc"), is(4));
        assertThat(Code.longestStreak("aaaaa546890cccc"), is(5));


        assertThat(Code.longestStreak("aaaaba"), is(4));
        // other test cases for longestStreak() method
    }

    @Test
    public void findsModeFromCharactersInString() {

        assertThat(Code.mode(null), is(nullValue()));
        assertThat(Code.mode(""), is(nullValue()));
        assertThat(Code.mode("abcb"), is('b'));
        assertThat(Code.mode("cbbc"), is('c'));

        // other test cases for mode() method
        assertThat(Code.getCharacterCount("cbbc",'c'), is(2));
        assertThat(Code.getCharacterCount("cbbcccc",'c'), is(5));
        assertThat(Code.getCharacterCount("cbbaaggelcddcc",'c'), is(4));
        assertThat(Code.getCharacterCount("cbb",'b'), is(2));
        assertThat(Code.getCharacterCount("bbc",'b'), is(2));
    }

    @Test
    public void removesDuplicates() {
        assertThat(Code.removeDuplicates(arrayOf(1, 1)), is(arrayOf(1)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 1, 3, 2)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 3)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(100, 0, 3, 100, 0, 4, 562, 4)),
                is(arrayOf(100, 0, 3, 4, 562)));
    }

    @Test
    public void sumsIgnoringDuplicates() {
        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 1)), is(1));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 1, 3, 2)), is(6));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 3)), is(6));
    }

    private int[] arrayOf(int... numbers) {
        return numbers;
    }

}
