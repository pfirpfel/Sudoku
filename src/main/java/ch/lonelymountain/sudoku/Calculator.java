package ch.lonelymountain.sudoku;

/**
 * Created by Arieh on 08.04.2015.
 */
public class Calculator {
    private Integer values[][];
    private final Integer ORIGINAL_VALUES[][];
    private Integer calcvalues[][][];

    public Calculator(Integer[][] values) {
        this.values = values;
        this.ORIGINAL_VALUES = values;
    }

    public void possibilities() {
        for (int h = 0; h < 9; h++) {
            for (int s = 0; s < 9; s++) {

            }
        }

    }

    private Boolean grid(int row, int size, int column) {
        for (int i = 0; i < 9; i++) {
            if (values[row][i] == size || values[i][column] == size) {
                return true;
            }
        }
        return false;
    }

    private Boolean container(int row, int size, int column) {
       return containerCheck(containerRow(row),containerColumn(column),size);
    }

    private Integer containerRow(int row) {
        if (row >= 0 && row < 3) {
            return 0;
        }else if(row >= 3 && row < 6){
            return 3;
        }else if(row >= 6 && row < 9){
            return 6;
        }else{
            return null;
        }
    }

    private Integer containerColumn(int column) {
        if (column >= 0 && column < 3) {
            return 0;
        }else if(column >= 3 && column < 6){
            return 3;
        }else if(column >= 6 && column < 9){
            return 6;
        }else{
            return null;
        }
    }

    private Boolean containerCheck(int startRow, int startColumn, int size,) {
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startColumn; c < startColumn + 3; c++) {
                if (values[r][c] == size) {
                    return true;
                }
            }
        }
        return false;
    }

}
