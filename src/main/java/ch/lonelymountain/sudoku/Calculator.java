package ch.lonelymountain.sudoku;

/**
 * Created by Arieh on 08.04.2015.
 */
public class Calculator {
    private Integer values[][];
    private final Integer ORIGINAL_VALUES[][];

    public Calculator(Integer[][] values, Integer[][] ORIGINAL_VALUES) {
        this.values = values;
        this.ORIGINAL_VALUES = ORIGINAL_VALUES;
    }

    public void possibilities() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (values[row][column] == 0) {
                    for (int possiblevalue = 1; possiblevalue < 10; possiblevalue++) {
                        if (containerCheck(row, column, possiblevalue) == false && grid(row, column, possiblevalue) == false) {
                            values[row][column] = possiblevalue;

                            break;
                        }
                    }
                    if (values[row][column] == 0) {
                        backwards(row, column);
                        //  return;
                    }
                }
            }
        }
        printer();
    }

    //für mögliche Veränderung der vorherigen Werte
    public void backwards(int row, int column) {
        int line = row;
        int col = column;

        //rückwärts dürch den Array
        while (values[row][column] == 0) {
            if (col > 0) {
                col--;
                System.out.println(line + " ++++ " + col);
            } else if (line > 0) {
                System.out.println(line + " ++++ " + col);
                col += 8;
                line--;
                System.out.println(line + " ++++ " + col);
            }
            //probiert die noch möglichen zahlen von s bis 9
            System.out.println(line + " " + col);
            for (int s = values[line][col]; s < 9; s++) {
                if (containerCheck(line, col, s) == false && grid(line, col, s) == false) {
                    values[line][col] = s;
                   if(tryCorrect(line, row, col, column)== true){
                    return;
                   }
                } else {
                    values[line][col] = 0;
                }
            }
        }
    }
        //probiert neue werte im Bereich zwischen fehler und vorherigen.
    private Boolean tryCorrect(int startRow, int endRow, int startColumn, int endColumn) {
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startColumn; c <= endColumn; c++) {
                if (values[r][c] == 0) {
                    for (int newValue = 1; newValue < 10; newValue++) {
                        if (containerCheck(r, c, newValue) == false && grid(r, c, newValue) == false) {
                            values[r][c]=newValue;
                        }
                    }
                }
            }
        }
        if(values[endRow][endColumn] != 0){
            return true;
        }else{
            return false;
        }
    }


 /*   private Boolean controllBackwards(int row, int column) {
        int temporary;
        for (int r = 0; r <= row; r++) {
            for (int c = column; c <= column; c++) {
                temporary = values[r][c];
                values[r][c] = 10;
                if (containerCheck(containerRow(r), containerColumn(c), temporary) == true
                        && grid(row, column, temporary) == true) {

                }
                values[r][c] = temporary;
            }

        }
        return false;
    }*/

    //gibt resultat aus
    public void printer() {
        String test = "";
        for (int i = 0; i < 9; i++) {
            for (int s = 0; s < 9; s++) {
                test += values[i][s] + ":";
                tes2 += ORIGINAL_VALUES[i][s] + ";";
            }
            System.out.println(test);
            test = "";
        }
    }

    //gibt zurück ob horizontal oder senkrecht enthalten
    private Boolean grid(int row, int column, int size) {
        for (int i = 0; i < 9; i++) {
            if (values[row][i] == size || values[i][column] == size) {
                return true;
            }
        }
        return false;
    }

    //gibt Startzeile des 9er Containers zurück.
    private Integer containerRow(int row) {
        if (row >= 0 && row < 3) {
            return 0;
        } else if (row >= 3 && row < 6) {
            return 3;
        } else if (row >= 6 && row < 9) {
            return 6;
        } else {
            return null;
        }
    }

    //gibt Startspalte des 9er Containers zurück.
    private Integer containerColumn(int column) {
        if (column >= 0 && column < 3) {
            return 0;
        } else if (column >= 3 && column < 6) {
            return 3;
        } else if (column >= 6 && column < 9) {
            return 6;
        } else {
            return null;
        }
    }

    //gibt zurück ob Zahl in container enthalten ist.
    private Boolean containerCheck(int row, int column, int size) {
        int startRow = containerRow(row);
        int startColumn = containerColumn(column);

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
