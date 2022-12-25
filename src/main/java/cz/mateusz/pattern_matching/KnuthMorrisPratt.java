package cz.mateusz.pattern_matching;

public class KnuthMorrisPratt {

    public int find(char[] text, char[] pattern) {

        final int textLength = text.length;
        final int patternLength = pattern.length;

        if(textLength == 0 || patternLength == 0) return 0; // trivial case

        final int[] reuses = examine(pattern);

        int textIndex = 0;
        int patternIndex = 0;

        while(textIndex < textLength) {
            if(text[textIndex] == pattern[patternIndex]) {
                if(patternIndex == patternLength - 1) return (textIndex - patternLength) + 1;
                textIndex++;
                patternIndex++;
            }
            else if( patternIndex > 0 ) {
                patternIndex = reuses[patternIndex - 1];
            }
            else {
                textIndex++;
            }
        }

        return 0;
    }

    public int[] examine(char[] pattern) {
        final int len = pattern.length;
        final int[] reuse = new int[len];
        int next = 1;
        int previous = 0;
        while(next < len) {
            if(pattern[previous] == pattern[next]) {
                reuse[next] = previous + 1;
                next++;
                previous++;
            }
            else if(previous > 0) {
                previous = reuse[previous - 1];
            }
            else {
                next++;
            }
        }
        return reuse;
    }
}
