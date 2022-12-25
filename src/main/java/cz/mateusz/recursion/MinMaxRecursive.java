package cz.mateusz.recursion;

public class MinMaxRecursive {

    public static int min(int[] numbers, int n) {
        if(n == 2)
            return numbers[0] > numbers[1] ? numbers[1] : numbers[0];
        int minV = min(numbers, n - 1);
        return numbers[n - 1] > minV ? minV : numbers[n - 1];
    }

    public static int minM(int[] numbers, int n) {
        if(n == 2)
            return Math.min(numbers[0], numbers[1]);
        return Math.min(numbers[n - 1], minM(numbers, n -1));
    }

    public static void main(String... args) {
        int numbers[] = new int[1000];
        for(int n = 0; n < 1000; n++) {
            numbers[n] = (int) Math.floor(Math.random() * 1000 + 10);
        }
        System.out.println("The least number: " + min(numbers, numbers.length));
        System.out.println("The least number (ver 2): " + minM(numbers, numbers.length));
    }
}
