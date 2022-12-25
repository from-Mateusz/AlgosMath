package cz.mateusz.pattern_matching;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class KnuthMorrisPrattTests {

    @Test
    public void when_asked_to_examine_pattern_then_compute_its_reuse_guide() {
        final KnuthMorrisPratt kmp = new KnuthMorrisPratt();
        final char[] pattern = "abcabdab".toCharArray();
        final int[] expected = { 0, 0, 0, 1, 2, 0, 1, 2 };
        assertThat(kmp.examine(pattern), equalTo(expected));
    }

    @Test
    public void given_some_text_and_pattern_when_asked_to_find_pattern_within_text_then_give_its_starting_index_within_this_text() {
        final KnuthMorrisPratt kmp = new KnuthMorrisPratt();
        final char[] pattern = "mama".toCharArray();
        final char[] text = "moja mama jest simply the best".toCharArray();
        final int expected = 5;
        assertThat(kmp.find(text, pattern), is(expected));
    }
}
