package cz.mateusz.recursion;

public class Exponentiation {

    static double trivial(double x,  int n) {
        if(n == 0) return 1;
        else return x * trivial(x,n-1);
    }

    static double repeatedSquaring(double x, int n) {
        if(n == 0) return 1;
        else {
            double partial = repeatedSquaring(x, n/2);
            double result = partial * partial;
            if( n % 2 == 1) result *= x;
            return result;
        }
    }

    public static void main(String ...args) {
        System.out.println("2 to the power of 4 (trivial way): " + trivial(2, 4));
        System.out.println("2 to the power of 4 (repeated squaring way): " + repeatedSquaring(2, 4));
    }
}
