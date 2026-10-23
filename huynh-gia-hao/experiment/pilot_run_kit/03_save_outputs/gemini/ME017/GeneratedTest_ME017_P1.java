package humaneval.generated;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class GeneratedTest_ME017 {

    @Test
    public void testEmptyString() {
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music("");
        assertEquals(expected, actual);
    }

    @Test
    public void testWhitespaceOnly() {
        List<Integer> expected = Collections.emptyList();
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music("   ");
        assertEquals(expected, actual);
    }

    @Test
    public void testSingleWholeNote() {
        List<Integer> expected = Collections.singletonList(4);
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music("o");
        assertEquals(expected, actual);
    }

    @Test
    public void testSingleHalfNote() {
        List<Integer> expected = Collections.singletonList(2);
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music("o|");
        assertEquals(expected, actual);
    }

    @Test
    public void testSingleQuarterNote() {
        List<Integer> expected = Collections.singletonList(1);
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music(".|");
        assertEquals(expected, actual);
    }

    @Test
    public void testEachNoteTypeOnce() {
        List<Integer> expected = Arrays.asList(4, 2, 1);
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music("o o| .|");
        assertEquals(expected, actual);
    }

    @Test
    public void testRepeatedWholeNotes() {
        List<Integer> expected = Arrays.asList(4, 4, 4, 4);
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music("o o o o");
        assertEquals(expected, actual);
    }

    @Test
    public void testRepeatedQuarterNotes() {
        List<Integer> expected = Arrays.asList(1, 1, 1, 1);
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music(".| .| .| .|");
        assertEquals(expected, actual);
    }

    @Test
    public void testMixedSequence() {
        List<Integer> expected = Arrays.asList(2, 2, 1, 1, 4, 4, 2);
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music("o| o| .| .| o o o|");
        assertEquals(expected, actual);
    }

    @Test
    public void testLongerMelody() {
        List<Integer> expected = Arrays.asList(4, 2, 1, 2, 2, 1, 1, 4);
        List<Integer> actual = humaneval.correct.PARSE_MUSIC.parse_music("o o| .| o| o| .| .| o");
        assertEquals(expected, actual);
    }
}
