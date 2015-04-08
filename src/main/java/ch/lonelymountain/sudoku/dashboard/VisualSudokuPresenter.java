package ch.lonelymountain.sudoku.dashboard;

/*
 * #%L
 * igniter
 * %%
 * Copyright (C) 2013 - 2014 Adam Bien
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

@SuppressWarnings("ALL")
public class VisualSudokuPresenter implements Initializable {

    @FXML
    Pane lightsBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private final TextField[][] columns = new TextField[9][9];
    private int[][] 
    private static final int SIZE_OF_IMPUTBOX = 31;
    private static final int ADDITIONAL_SPACE = 4;

    public void generateFields() {
        int generatePosY = 0;
        int generatePosX = 0;
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
                columns[i][s] = new TextField();
                lightsBox.getChildren().add(columns[i][s]);
                columns[i][s].setMaxSize(SIZE_OF_IMPUTBOX, SIZE_OF_IMPUTBOX);

                columns[i][s].setLayoutX(generatePosX);
                columns[i][s].setLayoutY(generatePosY);

                //final copy of index for listener
                final int POS_Y = i;
                final int POS_X = s;
                //Listener controlls misentrys
                columns[i][s].textProperty().addListener((observable, oldValue, newValue) -> {
                    try {
                        int control = Integer.parseInt(columns[POS_Y][POS_X].getText());

                        if (control < 1 || control > 9) {
                            columns[POS_Y][POS_X].clear();
                        }

                    } catch (Exception ex) {
                        System.err.println(ex);
                        columns[POS_Y][POS_X].clear();
                    }
                });
                generatePosX += SIZE_OF_IMPUTBOX + 1;
            }
            generatePosX = 0;
            generatePosY += SIZE_OF_IMPUTBOX + 1;
        }


    }

    public void graber() {
        for (int s = 0; s < 9; s++) {
            for (int i = 0; i < 9; i++) {

            }
        }


    }

}
