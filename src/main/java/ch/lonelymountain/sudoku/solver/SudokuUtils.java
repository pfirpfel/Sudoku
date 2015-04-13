package ch.lonelymountain.sudoku.solver;

/**
 * @author Robin Bruegger
 */
public class SudokuUtils {

    /**
     * Pretty print the current state of the sudoku
     *
     * @param sudoku Representation of the sudoku. Numbers from 1 to 9 are allowed.
     * 0 indicates an empty field. A correct sudoku input is assumed.
     */
    static void print(int[][] sudoku) {
        for(int x = 0; x < 9; x++) {
            for(int i = 0; i < 9; i++) System.out.print("+---");
            System.out.println("+");
            for(int y = 0; y < 9; y++) {
                System.out.print("| " + (sudoku[x][y] > 0 ? sudoku[x][y] : " ") + " ");
            }
            System.out.println("|");
        }
        for(int i = 0; i < 9; i++) System.out.print("+---");
        System.out.println("+");
        System.out.println();
    }
}