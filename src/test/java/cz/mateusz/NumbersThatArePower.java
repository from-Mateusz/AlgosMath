package cz.mateusz;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class NumbersThatArePower {

    @Test
    public void test() {
        assertThat(powerSumDigTerm(2), is(81L));
    }

    public static long powerSumDigTerm(int n) {
        Digits examinedNumber = digits(10);

        int count = 0;
        int exponent = 2;
        long product = 0;
        boolean found = false;
//        for(int i = 0; i < examinedNumber; i++) {
//
//        }
        return 0L;
    }

    private static Digits digits(long number) {
        String strNumber = String.valueOf(number);
        final char[] strDigits = strNumber.toCharArray();
        final int[] digits = new int[strDigits.length];
        int sum = 0;
        for(int i = 0; i < strDigits.length; i++) {
            digits[i] = strDigits[i] - '0';
            sum += digits[i];
        }
        return new Digits(number, sum, digits);
    }

    private static class Digits {
        public final int sum;
        public final int[] digits;

        public final long number;

        public Digits(long number, int sum, int[] digits) {
            this.number = number;
            this.sum = sum;
            this.digits = digits;
        }

        int get(int i) {
            return digits[i];
        }

        int last() {
            return digits[count() - 1];
        }

        int count() {
            return digits.length;
        }

        @Override
        public String toString() {
            return "Digits{" +
                    "sum=" + sum +
                    ", digits=" + Arrays.toString(digits) +
                    ", number=" + number +
                    '}';
        }
    }

    private static int extractDigitsSum(long number) {
        String textNumber = String.valueOf(number);
        final char[] cDigits = textNumber.toCharArray();
        int sum = 0;
        for(int i = 0; i < cDigits.length; i++) {
             sum += cDigits[i] - '0';
        }
        return sum;
    }

    private static long nextNumberToExamine(long previous) {
        return previous + 1;
    }

    private static boolean isPrime(long number) {
        for(int divisor = 2; divisor < Math.sqrt(number); divisor++) {
            if(number == divisor) continue;
            if(number % divisor == 0) return false;
        }
        return true;
    }
}
