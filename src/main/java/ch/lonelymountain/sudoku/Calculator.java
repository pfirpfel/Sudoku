package ch.lonelymountain.sudoku;

/**
 * Created by Arieh on 08.04.2015.
 */
public class Calculator {
    private Integer values[][];
    private Integer calcvalues[][][];

    public Calculator(Integer[][] values) {
        this.values = values;
    }

    public void possibilities(){
        for (int h =0; h<9;h++){
            for(int s = 0; s<9;s++){
                
            }
        }

    }

    private Boolean horizontal(int row, int size, int column){
        for(int i = 0;i<9;i++){
            if(values[row][i]== size  || values[i][column] == size){
                return true;
            }
        }
        return false;
    }
}
