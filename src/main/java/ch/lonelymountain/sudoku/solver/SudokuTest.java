package ch.lonelymountain.sudoku.solver;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Robin Bruegger
 */
public class SudokuTest {

    public static void main(String[] args) throws IOException {

        //##############################################################
        //Simple test
        //##############################################################
        int[][] sudoku = {{0, 0, 5, 3, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 2, 0},
                {0, 7, 0, 0, 1, 0, 5, 0, 0},
                {4, 0, 0, 0, 0, 5, 3, 0, 0},
                {0, 1, 0, 0, 7, 0, 0, 0, 6},
                {0, 0, 3, 2, 0, 0, 0, 8, 0},
                {0, 6, 0, 5, 0, 0, 0, 0, 9},
                {0, 0, 4, 0, 0, 0, 0, 3, 0},
                {0, 0, 0, 0, 0, 9, 7, 0, 0}};
        ISudokuSolver solver = new SudokuSolver();
        int solutions = solver.solveSudoku(sudoku, true);
        System.out.println("Found " + solutions + " solution" + (solutions == 1 ? "" : "s") + "!");


//		//##############################################################
//		//Hard test
//		//##############################################################
//		int[][] sudoku = {{4, 0, 0, 0, 0, 0, 8, 0, 5},
//						  {0, 3, 0, 0, 0, 0, 0, 0, 0},
//						  {0, 0, 0, 7, 0, 0, 0, 0, 0},
//						  {0, 2, 0, 0, 0, 0, 0, 6, 0},
//						  {0, 0, 0, 0, 8, 0, 4, 0, 0},
//						  {0, 0, 0, 0, 1, 0, 0, 0, 0},
//						  {0, 0, 0, 6, 0, 3, 0, 7, 0},
//						  {5, 0, 0, 2, 0, 0, 0, 0, 0},
//						  {1, 0, 4, 0, 0, 0, 0, 0, 0}};
//		ISudokuSolver solver = new SudokuSolver();
//		int solutions = solver.solveSudoku(sudoku, true);
//		System.out.println("Found " + solutions + " solution" + (solutions == 1 ? "" : "s") + "!");


//		//##############################################################
//		//95 very hard sudokus benchmark
//		//##############################################################
//		ISudokuSolver solver = new SudokuSolver();
//		BufferedReader br = new BufferedReader(new FileReader(new File("hardest95Sudokus.txt")));
//		String line;
//
//		//Benchmark: Solve 95 very hard Sudokus
//		long start = System.currentTimeMillis();
//		while ((line = br.readLine()) != null) {
//		   int[][] sudoku = new int[9][9];
//		   for(int i = 0; i < 81; i++) {
//			   if(line.charAt(i) == '.') {
//				   sudoku[i/9][i%9] = 0;
//			   } else {
//				   sudoku[i/9][i%9] = line.charAt(i) - '0';
//			   }
//		   }
//		   solver.solveSudoku(sudoku, false);
//		}
//		long end = System.currentTimeMillis();
//		System.out.println("Solving 95 hard Sudokus took " + (end - start) / 1000f + " seconds!");
//
//		br.close();
    }

}