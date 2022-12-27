package cz.mateusz.sets;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BinomialCalculator {

    @Test
    public void given_4th_degree_polynomial_calculate_it_based_on_pascals_triangle() {
        final int a = 1, b = -3, degree = 4;
        final String binomialEquation = binomial(a, b, degree);
        System.out.println("Equation: " + binomialEquation);
    }

    @Test
    public void given_5th_degree_polynomial_calculate_it_based_on_pascals_triangle() {
        final int a = 1, b = 1, degree = 5;
        final String binomialEquation = binomial(a, b, degree);
        System.out.println("Equation: " + binomialEquation);
    }

    private String binomial(int a, int b, int exp) {
        final int degree = exp + 1;
        List<int[]> pTriangle = pascals(degree);
        final int[] degreeTriangleRow = pTriangle.get(degree - 1);
        final StringBuilder equation = new StringBuilder();
        final int n = degree - 1;
        for(int k = 0; k < degreeTriangleRow.length; k++) {
            equation.append(String.format("%dx^%d ", (int) (Math.pow(b, k) * degreeTriangleRow[k]), n - k));
        }
        return equation.toString();
    }

    private List<int[]> pascals(int n) {
        final List<int[]> rows = new ArrayList<>();

        int row = 0;
        int col = 1;
        for(int k = 0; k <= n - 1; k++) {
            if(k == 0) rows.add(new int[] {1});
            else if(k == 1) rows.add(new int[] {1, 1});
            else {
                int[] columns = new int[col];
                for(int j = 0; j < col; j++) {
                    if(j == 0 || j == col - 1) columns[j] = 1;
                    else {
                        final int[] previousRow = rows.get(row - 1);
                        columns[j] = previousRow[j - 1] + previousRow[j];
                    }
                }
                rows.add(columns);
            }
            ++row;
            ++col;
        }

        return rows;
    }
}
