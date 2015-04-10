package ch.lonelymountain.sudoku;

import javax.sound.midi.Soundbank;

/**
 * Created by Arieh on 08.04.2015.
 */
public class Calculator {
    private Integer values[][];
    private final Integer ORIGINAL_VALUES[][];
     public Integer countwhile = 0;
    public Calculator(Integer[][] values, Integer[][] ORIGINAL_VALUES) {
        this.values = values;
        this.ORIGINAL_VALUES = ORIGINAL_VALUES;
    }

    public void possibilities() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                printer();
                if (values[row][column] == 0) {
                    for (int possiblevalue = 1; possiblevalue < 10; possiblevalue++) {
                        if (containerCheck(row, column, possiblevalue) == false && grid(row, column, possiblevalue) == false) {
                            values[row][column] = possiblevalue;

                            break;
                        }
                    }
                    if (values[row][column] == 0) {
                        System.out.println("Call " + row +"  "+ column);
                        int test[] = testBack(row, column);
                        row = test[0];
                        column = test[1];
                        //  backwards(row, column);
                        //  return;
                    }
                }
            }
        }
        printer();
    }

    private int[] testBack(int row, int column) {
        if (column > 0) {
            column--;
        } else if (row > 0) {
            column += 8;
            row--;
        }
        Boolean pause = true;
        Boolean valid = false;
        printer();
        int pos[] = new int[2];
        while (pause == true) {
            //kontrolle ob maximale höhe von vorheriger Zahl erreicht ist.
            for (int s = values[row][column] + 1; s < 11; s++) {
                System.out.println("++++++++++++++++++++");
                if (grid(row, column, s) == true || containerCheck(row, column, s) == true || s>=9) {
                    valid = true;
                }
            }
            //setzt wenn maximale höhe von vorheriger Zahl erreicht ist  auf 0 und holt die davor.
            if (valid == true) {
                values[row][column] = 0;
                if (column > 0) {
                    column--;
                } else if (row > 0) {
                    column += 8;
                    row--;
                }
                valid = false;
            }
            printer();
            System.out.println(row +"   " +column);
            for(int val = values[row][column]+1; val<10; val++){
                if (grid(row, column, val) == false && containerCheck(row, column, val) == false) {
                    values[row][column]=val;
                    pos[0] = row;
                    pos[1] = column;
                    pause = false;
                    return pos;
                }else{
                    values[row][column]=0;
                    System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||7");
                }
            }
            countwhile++;
            if(countwhile >5000)pause =false;
        }
        pos[0] = 8;
        pos[1] = 8;
        return pos;
    }


    //gibt resultat aus
    public void printer() {
        String test = "";
        for (int i = 0; i < 9; i++) {
            for (int s = 0; s < 9; s++) {
                test += values[i][s] + ":";

            }

            System.out.println(test);
            test = "";
        }
        System.out.println("-------------------------");
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
