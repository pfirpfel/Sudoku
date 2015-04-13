package ch.lonelymountain.sudoku.solver;

/**
 * A basic implementation of a sudoku solver.
 *
 * Every sudoku solver has to be able to determine which numbers are still
 * possible for each empty field. Rather than storing this information per
 * cell or dynamically computing it every time, this solver uses a both
 * simpler and faster datastructure.
 *
 * Every column, every row and every 3x3 block is represented by an
 * integer, with a bit reserved for each number from 1 to 9. Every cell belongs to
 * 3 integers, one for it's row, one for it's column and one for it's block.
 *
 * If the N'th bit in an integer is set, this means that the number N does not
 * yet occur in this column / row / 3x3 block and is therefore still available.
 * Because each cell belongs to 3 integers, we can find out about the available
 * numbers for a cell by using a binary AND beween the cell's 3 responsible integers.
 * The resulting int will have bit N set only if it was available in all 3
 * responsible ints. Which bits in an int are set can easely be determined by
 * some bitshifting.
 *
 * This datastructure not only makes the availability lookup fast and simple, it is
 * even easier to maintain correct state if a number is added or removed from the sudoku.
 * All that has to be done is set or unset a bit in each of the 3 responsible integers.
 *
 * @author Robin Bruegger
 */
public class SudokuSolver implements ISudokuSolver {

    private static final int SOLUTION_SEARCH_LIMIT = 1000;

    /* (non-Javadoc)
     * @see ch.lonelymountain.sudoku.solver.ISudokuSolver#solveSudoku(int[][], boolean printSolutions)
     */
    @Override
    public int solveSudoku(int[][] sudoku, boolean printSolutions) {

        //construct internal datastructure
        Sudoku sudokuObj = new Sudoku(sudoku);
        return this.solve(sudokuObj, 0, 0, printSolutions);
    }

    /**
     * Recursive method to solve a sudoku. Finds all solutions.
     *
     * @param s sudoku datastructure
     * @param index The sudoku is traversed row by row. The index points to the current cell
     * @param solutions solution counter
     * @param printSolutions if TRUE discovered sudoku solutions are printed to stdout
     * @return The number of solutions
     */
    private int solve(Sudoku s, int index, int solutions, boolean printSolutions) {

        //is the sudoku fully solved?
        if(index == 81) {
            if(printSolutions) s.print();
            return solutions + 1;
        }

        //
        if(solutions >= SOLUTION_SEARCH_LIMIT)
            return solutions;


//		//check solvability
//		for(int i = index; i < 81; i++) {
//			final int x = i / 9;
//			final int y = i % 9;
//			if(s.field[x][y] == 0 && s.getAvailableFor(x, y) == 0) {
//				return solutions;
//			}
//		}

        final int x = index / 9;
        final int y = index % 9;

        if(s.field[x][y] > 0) {
            //Current field is already filled, proceed to next cell
            return solve(s, index + 1, solutions, printSolutions);
        }
        else {
            //Fill in next number
            final int available = s.getAvailableFor(x, y);
            for(int i = 1; i <= 9; i++) {
                if((available & (1 << i)) > 0) {
                    s.set(x, y, i);
                    solutions = this.solve(s, index + 1, solutions, printSolutions);
                    s.unset(x, y);
                }
            }
            return solutions;
        }
    }

    /**
     * Internal datastructure of the sudoku solver.
     *
     * For a detailed description on how this solver works see {@link SudokuSolver}
     *
     * @author Robin Br�gger
     */
    private class Sudoku {

        /**
         * Representation of the sudoku. Numbers from 1 to 9 are allowed.
         * 0 indicates an empty field.
         */
        int[][] field;

        /**
         * Holds the 9 ints which store row availability
         */
        int[] aRow;

        /**
         * Holds the 9 ints which store column availability
         */
        int[] aColumn;

        /**
         * Holds the 9 ints which store block availability
         */
        int[][] aBlock;

        /**
         * Construct internal sudoku solving datastructure
         *
         * @param field Representation of the sudoku. Numbers from 1 to 9 are allowed.
         * 0 indicates an empty field. A correct sudoku input is assumed.
         */
        public Sudoku(int[][] field) {
            this.field = field;

            //initialize availability: rows
            aRow = new int[9];
            for(int x = 0; x < 9; x++) {

                //Set bits 1 to 9. A set bit indicates an available number for this row
                int row = 0x3FE; //...01111111110
                for(int y = 0; y < 9; y++) {
                    if(field[x][y] > 0) {
                        row -= 1 << field[x][y]; //unset i-th bit
                    }
                }
                aRow[x] = row;
            }

            //initialize availability: columns
            aColumn = new int[9];
            for(int y = 0; y < 9; y++) {

                //Set bits 1 to 9. A set bit indicates an available number for this column
                int column = 0x3FE; //...01111111110
                for(int x = 0; x < 9; x++) {
                    if(field[x][y] > 0) {
                        column -= 1 << field[x][y];
                    }
                }
                aColumn[y] = column;
            }

            //initialize availability: blocks
            aBlock = new int[3][3];
            //traverse the 3x3 blocks
            for(int blockX = 0; blockX < 3; blockX++) {
                for(int blockY = 0; blockY < 3; blockY++) {

                    //traverse the 3x3 fields in each block
                    int block = 0x3FE; //...01111111110
                    for(int x = 0; x < 3; x++) {
                        for(int y = 0;  y < 3; y++) {
                            if(field[x + blockX * 3][y + blockY * 3] > 0) {
                                block -= 1 << field[x + blockX * 3][y + blockY * 3];
                            }
                        }
                    }
                    aBlock[blockX][blockY] = block;
                }
            }

        }

        /**
         * Computes the available numbers in the cell (x, y)
         *
         * @param x x-value of the cell
         * @param y y-value of the cell
         * @return An int with has the N'th bit set if number N is available for cell (x, y)
         */
        int getAvailableFor(int x, int y) {
            //the number has to be available in the block, the row and the column
            return aRow[x] & aColumn[y] & aBlock[x/3][y/3];
        }

        /**
         * Set the number for a cell
         *
         * @param x x-value of the cell
         * @param y y-value of the cell
         * @param number number to be set
         */
        void set(int x, int y, int number) {
            field[x][y] = number;
            aRow[x] -= 1 << number;
            aColumn[y] -= 1 << number;
            aBlock[x/3][y/3] -= 1 << number;
        }

        /**
         * Remove the number from a cell an make it empty again. Used for backtracking.
         *
         * @param x x-value of the cell
         * @param y y-value of the cell
         */
        void unset(int x, int y) {
            final int number = field[x][y];
            field[x][y] = 0;
            aRow[x] += 1 << number;
            aColumn[y] += 1 << number;
            aBlock[x/3][y/3] += 1 << number;
        }

        /**
         * Pretty print the current state of the sudoku
         */
        void print() {
            SudokuUtils.print(field);
        }
    }
}
