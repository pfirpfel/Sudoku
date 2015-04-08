package ch.lonelymountain.sudoku.GUI;

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


import ch.lonelymountain.sudoku.Calculator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

@SuppressWarnings("ALL")
public class DashboardPresenter implements Initializable {

    @FXML
    Pane contentBox;

    @FXML
    Pane previewBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateFields();
    }

    private final TextField[][] fields = new TextField[9][9];
    private Integer[][] allValues = new Integer[9][9];
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
            generatePosX = 0;
            generatePosY += SIZE_OF_IMPUTBOX + 1;
        }
    }
    public void calculate(){
        Calculator calc = new Calculator();
        calc

    }
}
