package ch.lonelymountain.sudoku.solver;

/**
 * Inteface for a Sudoku solver
 *
 * @author Robin Bruegger
 */
public interface ISudokuSolver {

    /**
     * Finds all solutions of a given Sudoku. A correct Sudoku input is assumend.
     *
     * @param sudoku Representation of the Sudoku. Numbers from 1 to 9 are allowed.
     * 0 indicates an empty field. A correct Sudoku input is assumed.
     * @param printSolutions if TRUE discovered sudoku solutions are printed to stdout
     * @return number of solutions for this sudoku
     */
    public abstract int solveSudoku(int[][] sudoku, boolean printSolutions);

}