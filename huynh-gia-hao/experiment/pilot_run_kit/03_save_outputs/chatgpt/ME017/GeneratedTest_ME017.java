package humaneval.generated;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class GeneratedTest_ME017 {
 @Test public void empty(){assertEquals(Collections.emptyList(),humaneval.correct.PARSE_MUSIC.parse_music(""));}
 @Test public void eachNoteType(){assertEquals(Arrays.asList(4,2,1),humaneval.correct.PARSE_MUSIC.parse_music("o o| .|"));}
 @Test public void repeatedNotes(){assertEquals(Arrays.asList(1,1,1,1),humaneval.correct.PARSE_MUSIC.parse_music(".| .| .| .|"));}
 @Test public void mixedSequence(){assertEquals(Arrays.asList(4,2,1,2,4),humaneval.correct.PARSE_MUSIC.parse_music("o o| .| o| o"));}
 @Test public void whitespaceOnly(){assertEquals(Collections.emptyList(),humaneval.correct.PARSE_MUSIC.parse_music("   "));}
}
