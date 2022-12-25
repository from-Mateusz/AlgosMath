package cz.mateusz.pattern_matching;

public class BruteForce implements PatternFinder {

    /**
     * The worst-case running time for this algorithmic technique is at most O(nm)
     */

    private final static int PATTERN_OCCURRENCE_NOT_FOUND = -1;

    @Override
    public int find(String pattern, String source) {
        if(pattern == null || pattern.isBlank()) return PATTERN_OCCURRENCE_NOT_FOUND;
        if(source == null || source.isBlank()) return PATTERN_OCCURRENCE_NOT_FOUND;

        final char[] pArray = pattern.toCharArray();
        final char[] sourceArr = source.toCharArray();

        int pLen = pArray.length;
        int sLen = sourceArr.length;

        for(int i = 0; i <= (sLen - pLen); i++) {
            int k = 0;
            while(k < pLen && (pArray[k] == sourceArr[k+i])) {
                k++;
            }

            if(k == pLen) {
                return i;
            }
        }

        return PATTERN_OCCURRENCE_NOT_FOUND;
    }
}
