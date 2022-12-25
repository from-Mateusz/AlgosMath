package cz.mateusz.dstructures.arrays;

import java.util.Random;

public class CleanableArray {
    private int arr[];

    private int elementsCount = 0;

    CleanableArray(int capacity) {
        this.arr = new int[capacity];
    }

    private void fillBy(int constant, int n) {
        for(int i = n-1; i >= 0; i--) {
            this.arr[i] = Math.abs(constant);
            elementsCount++;
        }
    }

    public void clean() {
        Random random = new Random();
        while(elementsCount > 0) {
            int randomPos = random.nextInt(elementsCount);
            arr[randomPos] = 0;
            elementsCount--;
        }
    }

    public boolean isEmpty() {
        return elementsCount == 0;
    }

    public void preview() {
        int counter = 0;
        System.out.print("[ ");
        while(counter < elementsCount) {
            System.out.print(arr[counter] + ",");
            counter++;
        }
        System.out.print(" ]");
    }

    public static void main(String ...arg) {
        CleanableArray arr = new CleanableArray(20);
        arr.fillBy(15, 20);
        arr.preview();
        arr.clean();
        arr.preview();
    }
}
