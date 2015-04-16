package ch.lonelymountain.sudoku.GUI;

import ch.lonelymountain.sudoku.solver.SudokuSolver;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

@SuppressWarnings("ALL")
public class DashboardPresenter implements Initializable{

    @FXML
    Pane contentBox;

    @FXML
    FlowPane previewBox;

    @FXML
    ProgressBar progrss;

    @FXML
    javafx.scene.control.Label countOfPossibillities;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateFields();
    }

    private final TextField[][] fields = new TextField[9][9];
    private int[][] allValues = new int[9][9];
    private int[][] calculatedValjues = new int[9][9];
    private static final int SIZE_OF_IMPUTBOX = 31;
    private static final int ADDITIONAL_SPACE = 4;
    private static final int START_COORDINATE_X = 15;
    private static final int START_COORDINATE_Y = 10;

    public void generateFields() {
        notNullArray();
        int generatePosY = START_COORDINATE_Y;
        int generatePosX = START_COORDINATE_X;
        for (int s = 0; s < 9; s++) {
            //etwas grösserer Abstand für bessere übersicht
            switch (s) {

                case 3:
                    generatePosY += ADDITIONAL_SPACE;
                    break;
                case 6:
                    generatePosY += ADDITIONAL_SPACE;
                    break;
            }
            //etwas grösserer Abstand für bessere übersicht
            for (int i = 0; i < 9; i++) {
                switch (i) {

                    case 3:
                        generatePosX += ADDITIONAL_SPACE;
                        break;
                    case 6:
                        generatePosX += ADDITIONAL_SPACE;
                        break;
                }
                fields[i][s] = new TextField();
                contentBox.getChildren().add(fields[i][s]);
                fields[i][s].setMaxSize(SIZE_OF_IMPUTBOX, SIZE_OF_IMPUTBOX);

                fields[i][s].setLayoutX(generatePosX);
                fields[i][s].setLayoutY(generatePosY);

                //final copy of index for listener
                final int POS_Y = i;
                final int POS_X = s;
                //Listener controlls misentrys
                fields[i][s].textProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        int control = Integer.parseInt(fields[POS_Y][POS_X].getText());

                        if (control > 0 && control < 10) {
                            allValues[POS_X][POS_Y] = control;
                        } else {
                            fields[POS_Y][POS_X].clear();
                        }

                    } catch (Exception ex) {
                        System.err.println(ex);
                        fields[POS_Y][POS_X].clear();
                    }
                });
                generatePosX += SIZE_OF_IMPUTBOX + 1;
            }
            generatePosX = START_COORDINATE_X;
            generatePosY += SIZE_OF_IMPUTBOX + 1;
        }
    }

    private void notNullArray() {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                allValues[row][column] = 0;
            }
        }
    }

    public void calculate(){
        int drawings = 100;
        SudokuSolver solver = new SudokuSolver();
        int numSolutions = solver.solveSudoku(allValues, false);
        if (numSolutions>9000){
            countOfPossibillities.setText("Möglichkeiten: Over 9000");
        }else if(numSolutions == 0){
            countOfPossibillities.setText("Möglichkeiten:  " + numSolutions);
            return;
        }else{
            countOfPossibillities.setText("Möglichkeiten: " + numSolutions);
        }

        ArrayList<int[][]> sudokus = solver.getSudokus();

//        new Thread(task).start();
//        task.run();  task.cancel();
        if(numSolutions<100){
            drawings = numSolutions;
        }
        for (int count = 0; count <= 100; count++) {
            progrss.setProgress((count / 100));
            System.out.println(count);
            drawThumbnail(sudokus.get(count));

            calculatedValjues = sudokus.get(count);

        }
    }
//    Task task = new Task<Void>(){
//        @Override public Void call() {
//            SudokuSolver solver = new SudokuSolver();
//            ArrayList<int[][]> sudokus = solver.getSudokus();
//            int yPos, xPos, value;
//            String number;
//            int SIZE = 225;
//            System.out.println("Testworks?");
//            FlowPane fullContent = new FlowPane();
//            for (int count = 0; count <= 100; count++) {
//              //  calculatedValjues = sudokus.get(count);
//
//                System.out.println("Testworks?");
//                // Draw lines
//                Pane thumbnail = new Pane();
//                Canvas canvas = new Canvas(SIZE + 5, SIZE + 5);
//                GraphicsContext graphic = canvas.getGraphicsContext2D();
//                for (int d = 1; d <= SIZE + 1; d += SIZE / 9) {
//                    if ((d == 1) || (d == (SIZE / 9 * 3 + 1)) || (d == (SIZE / 9 * 6 + 1)) || (SIZE + 1 == d)) {
//                        graphic.setLineWidth(3);
//                    }
//                    graphic.strokeLine(d, 0, d, SIZE);
//                    graphic.strokeLine(0, d, SIZE + 1, d);
//                    graphic.setLineWidth(1);
//                }
//                //draw numbers
//                graphic.setFont(new Font("Arial", SIZE / 9));
//                for (int r = 0; r < 9; r++) {
//                    yPos = r * (SIZE / 9) + (SIZE / 9) - SIZE / 10 / 8;
//                    for (int c = 0; c < 9; c++) {
//                        xPos = c * (SIZE / 9) + (SIZE / 9 / 3);
//                        graphic.strokeText("1", xPos, yPos);
//
//                    }
//
//                }
//                thumbnail.getChildren().addAll(canvas);
//                thumbnail.setOnMouseClicked((event) -> {
//                   fillSolvedSudokusBackIn(calculatedValjues);
//                });
//
//                fullContent.getChildren().addAll(thumbnail);
//            }
//            previewBox.getChildren().addAll(fullContent);
//            System.out.println("TestworkNot");
//            return null;
//        }
//    };

    //drawing thumbnails for the possible salutions
    private void drawThumbnail(int[][] sudoku) {
        int yPos, xPos, value;
        String number;
        int SIZE = 225;
        // Draw lines
        Pane thumbnail = new Pane();
        Canvas canvas = new Canvas(SIZE + 5, SIZE + 5);
        GraphicsContext graphic = canvas.getGraphicsContext2D();
        for (int d = 1; d <= SIZE + 1; d += SIZE / 9) {
            if ((d == 1) || (d == (SIZE / 9 * 3 + 1)) || (d == (SIZE / 9 * 6 + 1)) || (SIZE + 1 == d)) {
                graphic.setLineWidth(3);
            }
            graphic.strokeLine(d, 0, d, SIZE);
            graphic.strokeLine(0, d, SIZE + 1, d);
            graphic.setLineWidth(1);
        }
            //draw numbers
        graphic.setFont(new Font("Arial", SIZE / 9));
        for (int r = 0; r < 9; r++) {
            yPos = r * (SIZE / 9) + (SIZE / 9) - SIZE / 10 / 8;
            for (int c = 0; c < 9; c++) {
                xPos = c * (SIZE / 9) + (SIZE / 9 / 3);
                graphic.strokeText(String.valueOf(sudoku[r][c]), xPos, yPos);

            }

        }
        thumbnail.getChildren().addAll(canvas);
        thumbnail.setOnMouseClicked((event) -> {
            fillSolvedSudokusBackIn(sudoku);
        });
        previewBox.getChildren().addAll(thumbnail);
    }

    //click on thumbnails fills in the salution
    public void fillSolvedSudokusBackIn(int[][] array) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                fields[r][c].setText(String.valueOf(array[r][c]));
            }
        }

    }
}
