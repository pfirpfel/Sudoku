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

import ch.lonelymountain.sudoku.dashboard.input.LightView;

import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import sun.awt.im.InputContext;

import javax.inject.Inject;

/**
 * @author adam-bien.com
 */
public class DashboardPresenter implements Initializable {

    @FXML
    Label message;

    @FXML
    Pane lightsBox;

    @FXML
    TextField colums[];

    @Inject
    private String prefix;

    @Inject
    private String happyEnding;

    @Inject
    private LocalDate date;

    private String theVeryEnd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //fetched from followme.properties
        //this.theVeryEnd = rb.getString("theEnd");
    }

    TextField[] columns = new TextField[81];

    public void createLights() {

        for (int i = 0; i < 81; i++) {
            //final copy of index for listener
            final int Pos = i;
            columns[i] = new TextField();
            lightsBox.getChildren().add(columns[i]);
            columns[i].setMaxWidth(31);
            columns[i].setLayoutX(1000);
            columns[i].textProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    int Controll  = Integer.parseInt(columns[Pos].getText());

                    if(Controll < 1 || Controll >9){
                        columns[Pos].clear();
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                    columns[Pos].clear();
                }
            });
        }

    }

    private void Listener(int field) {

        System.out.println("N");
    }

    public void launch() {


        // message.setText("Date: " + date + " -> " + prefix + tower.readyToTakeoff() + happyEnding + theVeryEnd);
    }

}
