package cz.mateusz.sets;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LexicographicalPermutations {

    @Test
    public void given_any_number_then_divide_it_into_single_digits_set() {
        final Word word = Word.numeric(12345);
        final int expectedFirstChar = 1;
        final int expectedMiddleChar = 3;
        final int expectedLastChar = 5;
        assertThat(word.get(0), equalTo(expectedFirstChar));
        assertThat(word.get(2), equalTo(expectedMiddleChar));
        assertThat(word.get(4), equalTo(expectedLastChar));
    }

    @Test
    public void given_any_number_then_calculate_its_factorial() {
        int expected = 120;
        assertThat(factorial(5), is(expected));
    }

    @Test
    public void given_any_number_then_order_its_digits_in_lexicographical_order() {
        final Word word = Word.numeric(54321);
        sortLexicographically(word);
        final Word expected = Word.numeric(12345);
        assertThat(word, equalTo(expected));
    }

    @Test
    public void given_any_number_then_order_its_digit_in_lexicographical_order_and_return_solely_number() {
        final Word word = Word.numeric(54321);
        sortLexicographically(word);
        final int expected = 12345;
        assertThat(word.number(), is(expected));
    }

    @Test
    public void given_any_number_then_generate_its_permutations_in_lexicographical_way() {
        final int number = 12345;
        final Word[] permutations = generate(number);
        final int expectedPermutationsQty = factorial(5);
        printPermutations(permutations);
        assertThat(permutations.length, is(factorial(5)));
    }

    public Word[] generate(int number) {
        final Word word = Word.numeric(number);
        if(word.length() < 2) return new Word[] { Word.numeric(word.number()) };

        final int permutationsQty = factorial(word.length());
        final Word[] permutations = new Word[permutationsQty];
        sortLexicographically(word);

        int permutation = 0;
        permutations[permutation] = Word.numeric(word.number());
        int i,j;
        while(++permutation < permutationsQty) {
            i = word.length() - 2;
            j = word.length() - 1;
            while((word.exists(i) && word.exists(j)) && (word.get(i) > word.get(j))) {
                i--;
                j--;
            }

            j = word.length() - 1;
            while((word.exists(j)) && (word.get(i) > word.get(j))) {
                j--;
            }

            word.swap(i, j);

            i += 1;
            j = word.length() - 1;
            while((word.exists(i) && word.exists(j)) && i < j) {
                word.swap(i, j);
                i++;
                j--;
            }

            permutations[permutation] = Word.numeric(word.number());
        }
        return permutations;
    }

    public void sortLexicographically(Word word) {
        for(int i = 0; i < word.length(); i++) {
            for(int j = i + 1; j < word.length(); j++) {
                if(word.get(i) > word.get(j)) word.swap(i, j);
            }
        }
    }

    public int factorial(int number) {
        if(number < 2) return 1;
        return number * factorial(number - 1);
    }

    public void printPermutations(Word[] permutations) {
        if(permutations.length == 0) System.out.println("Nothing of the permutations to print!");
        System.out.printf("Generated %d lexicographical permutations of a word: %s\n", permutations.length, permutations[0]);
        for(int p = 0; p < permutations.length; p++) {
            System.out.printf("'%5s' Permutation [%d]: %s \n", "", p + 1, permutations[p]);
        }
    }

    public static class Word {

        private int[] digits;

        private int occupied = 0;

        public Word(int[] digits) {
            this.digits = digits;
            this.occupied = digits.length;
        }

        public Word(int length) {
            this.digits = new int[length];
        }

        public int get(int index) {
            return digits[index];
        }

        public int number() {
            int number = 0;
            for( int d = 0; d < digits.length; d++ ) {
                number += digits[d] * Math.pow(10, ((digits.length - 1) - d));
            }
            return number;
        }

        public boolean exists(int index) {
            return index < this.length() && 0 < index;
        }

        public void swap(int a, int b) {
            int digit = digits[a];
            digits[a] = digits[b];
            digits[b] = digit;
        }

        public int length() {
            return digits.length;
        }

        @Override
        public String toString() {
            String print = "";
            for(int i = 0; i < digits.length; i++) {
                print += digits[i];
            }
            return print;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Word word = (Word) o;
            return occupied == word.occupied && Arrays.equals(digits, word.digits);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(occupied);
            result = 31 * result + Arrays.hashCode(digits);
            return result;
        }

        public static Word numeric(int number) {
            int remains = number;
            int oom = (int) Math.floor(Math.log10(number));
            int[] digits = new int[oom + 1];
            for(int i = 0; i < digits.length; i++) {
                digits[i] = remains / (int) Math.pow(10, (oom - i));
                remains = remains % (int) Math.pow(10, (oom - i));
            }

            return new Word(digits);
        }
    }
}
