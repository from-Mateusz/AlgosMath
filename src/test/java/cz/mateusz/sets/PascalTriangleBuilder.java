package cz.mateusz.sets;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PascalTriangleBuilder {

    @Test
    public void build_1_row_pascal_triangle() {
        List<int[]> triangle = draw(1);
        int[] expectedRow0 = {1};
        assertThat(triangle, hasSize(1));
        assertThat(triangle.get(0), equalTo(expectedRow0));
    }

    @Test
    public void draw_up_to_2_rows_pascal_triangle() {
        List<int[]> triangle = draw(2);
        int[] expectedRow0 = {1};
        int[] expectedRow1 = {1, 1};
        assertThat(triangle, hasSize(2));
        assertThat(triangle.get(0), equalTo(expectedRow0));
        assertThat(triangle.get(1), equalTo(expectedRow1));
    }

    @Test
    public void draw_up_to_3_rows_pascal_triangle() {
        List<int[]> triangle = draw(3);
        int[] expectedRow0 = {1};
        int[] expectedRow1 = {1, 1};
        int[] expectedRow2 = {1, 2, 1};
        assertThat(triangle, hasSize(3));
        assertThat(triangle.get(0), equalTo(expectedRow0));
        assertThat(triangle.get(1), equalTo(expectedRow1));
        assertThat(triangle.get(2), equalTo(expectedRow2));
    }

    @Test
    public void draw_up_to_4_rows_pascal_triangle() {
        List<int[]> triangle = draw(4);
        int[] expectedRow0 = {1};
        int[] expectedRow1 = {1, 1};
        int[] expectedRow2 = {1, 2, 1};
        int[] expectedRow3 = {1, 3, 3, 1};
        assertThat(triangle, hasSize(4));
        assertThat(triangle.get(0), equalTo(expectedRow0));
        assertThat(triangle.get(1), equalTo(expectedRow1));
        assertThat(triangle.get(2), equalTo(expectedRow2));
        assertThat(triangle.get(3), equalTo(expectedRow3));
    }

    @Test
    public void draw_up_to_5_rows_pascal_triangle() {
        List<int[]> triangle = draw(5);
        int[] expectedRow0 = {1};
        int[] expectedRow1 = {1, 1};
        int[] expectedRow2 = {1, 2, 1};
        int[] expectedRow3 = {1, 3, 3, 1};
        int[] expectedRow4 = {1, 4, 6, 4, 1};
        assertThat(triangle, hasSize(5));
        assertThat(triangle.get(0), equalTo(expectedRow0));
        assertThat(triangle.get(1), equalTo(expectedRow1));
        assertThat(triangle.get(2), equalTo(expectedRow2));
        assertThat(triangle.get(3), equalTo(expectedRow3));
        assertThat(triangle.get(4), equalTo(expectedRow4));
    }


    private List<int[]> draw(int n) {
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
