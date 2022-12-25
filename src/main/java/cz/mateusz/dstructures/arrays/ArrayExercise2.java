package cz.mateusz.dstructures.arrays;

public class ArrayExercise2 {

    // Assuming that provided arrays of matrices are equal in terms of size
    public static int[][][] addThreeDimensionalArrays(int[][][] arr1, int[][][] arr2) {
        if(arr1.length != arr2.length) {
            return new int[][][] { {{ 0 }} };
        }

        int result[][][] = new int[arr1.length][arr1[0].length][arr1[0][0].length];
        for(int i = 0; i < arr1.length; i++) {
            for(int j = 0; j < arr1[i].length; j++) {
                for(int k = 0; k < arr1[i][j].length; k++) {
                    result[i][j][k] = arr1[i][j][k] + arr2[i][j][k];
                }
            }
        }
        return result;
    }

    public static void main(String ...args) {
        int arr1[][][] = new int[][][] {
                {
                        {0, 2, 3},
                        {5, 8, 9},
                        {10, 20, 4}
                }
        };

        int arr2[][][] = new int[][][] {
                {
                        {0, 2, 3},
                        {5, 8, 9},
                        {10, 20, 4}
                }
        };

        int result[][][] = addThreeDimensionalArrays(arr1, arr2);

        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                System.out.print(" [ ");
                for(int k = 0; k < result[i][j].length; k++) {
                    System.out.print(result[i][j][k] + ", ");
                }
                System.out.print(" ] ");
            }
        }
    }
}
