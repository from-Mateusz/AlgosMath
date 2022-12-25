package cz.mateusz.pattern_matching;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BoyerMooreTests {

    private final int NOT_FOUND_INDEX = -1;

    @ParameterizedTest
    @CsvSource({
            "'Hello', 0",
            "'forgotten', 27",
            "'', -1",
            ", -1"
    })
    public void findLeftMostPatternOccurrenceWithinText(String pattern, int expectedIndex) {
        final String EXAMINED_CONTENT = "Hello Mateusz, You are not forgotten here!";
        PatternFinder boyerMoore = new BoyerMoore();
        String testDescription = String.format("Should find first occurrence of a \"%s\" within a \"%s\", starting with index %d",
                pattern, EXAMINED_CONTENT, expectedIndex);
        assertThat(testDescription, boyerMoore.find(pattern, EXAMINED_CONTENT), equalTo(expectedIndex));
    }
}
