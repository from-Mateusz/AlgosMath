package cz.mateusz.dstructures.arrays;

import java.util.Random;

public class ArrayExercise {

    static class ArrayShuffle implements Cloneable {
        private int arr[];

        private int elementsCount;

        ArrayShuffle(int capacity) {
            arr = new int[capacity];
            fillRandomly();
        }

        private void fillRandomly() {
            Random random = new Random();
            while(elementsCount < arr.length) {
                arr[elementsCount] = random.nextInt(100);
                elementsCount++;
            }
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

        public int getSize() {
            return elementsCount;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            ArrayShuffle cloned = (ArrayShuffle) super.clone();
            cloned.arr = new int[elementsCount];
            for(int i = 0; i < elementsCount; i++) {
                cloned.arr[i] = arr[i];
            }
            return cloned;
        }
    }

    public static void singleShuffle(ArrayShuffle arrayShuffle) {
        Random random = new Random();
        int swapIndex = random.nextInt(arrayShuffle.elementsCount);
        int swapWithIndex = random.nextInt(arrayShuffle.elementsCount);
        while(swapIndex == swapWithIndex) {
            swapWithIndex = random.nextInt(arrayShuffle.elementsCount);
        }
        int temp = arrayShuffle.arr[swapWithIndex];
        arrayShuffle.arr[swapWithIndex] = arrayShuffle.arr[swapIndex];
        arrayShuffle.arr[swapIndex] = temp;
    }

    public static void multipleShuffle(ArrayShuffle arrayShuffle) {
        Random random = new Random();
        for(int i = 0; i < arrayShuffle.elementsCount; i++) {
            int replacedIndex = random.nextInt(i + 1);
            int replacedElement = arrayShuffle.arr[i];
            arrayShuffle.arr[i] = arrayShuffle.arr[replacedIndex];
            arrayShuffle.arr[replacedIndex] = replacedElement;
        }
    }

    public static void main(String ...args) throws Exception {
        ArrayShuffle arrayShuffle = new ArrayShuffle(10);
        ArrayShuffle clonedArrayShuffle = (ArrayShuffle) arrayShuffle.clone();
        arrayShuffle.preview();
        clonedArrayShuffle.preview();
        singleShuffle(arrayShuffle);
        arrayShuffle.preview();
        clonedArrayShuffle.preview();
        multipleShuffle(clonedArrayShuffle);
        clonedArrayShuffle.preview();
    }
}
