package cz.mateusz.pattern_matching;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoore implements PatternFinder {

    private final static int PATTERN_OCCURRENCE_NOT_FOUND = -1;

    @Override
    public int find(String pattern, String source) {
        if(pattern == null || pattern.isBlank()) return PATTERN_OCCURRENCE_NOT_FOUND;
        if(source == null || source.isBlank()) return PATTERN_OCCURRENCE_NOT_FOUND;

        final char[] pArray = pattern.toCharArray();
        final char[] sArray = source.toCharArray();

        final int pLen = pArray.length;
        final int sLen = sArray.length;

        /**
         * If character from source exists in the pattern, last.get(c) is the index of the last (rightmost) occurrence
         * of a character in question in the pattern. Otherwise, assume the default value is -1
         */
        final Map<Character, Integer> last = new HashMap<>();

        for(int i = 0; i < sLen; i++) {
            last.put(sArray[i], -1);
        }

        for(int k = 0; k < pLen; k++) {
            last.put(pArray[k], k);
        }

        final int PATTERN_RIGHTMOST_START_POINTER = pLen - 1;

        int sourceStartPointer = PATTERN_RIGHTMOST_START_POINTER;
        int patternStartPointer = PATTERN_RIGHTMOST_START_POINTER;

        while(sourceStartPointer < sLen) {
            if(sArray[sourceStartPointer] == pArray[patternStartPointer]) {
                if(patternStartPointer == 0) return sourceStartPointer;
                --patternStartPointer;
                --sourceStartPointer;
            }
            else {
                sourceStartPointer += pLen - Math.min(patternStartPointer, 1 + last.get(sArray[sourceStartPointer]) );
                patternStartPointer = PATTERN_RIGHTMOST_START_POINTER;
            }
        }

        return PATTERN_OCCURRENCE_NOT_FOUND;
    }
}
