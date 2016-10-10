/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_manager.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Martin Six
 */
public class LoginScreenController implements Initializable, ControlledScreen {

    private ScreensController controller;


    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("You clicked me!");
        controller.setScreen(Database_Manager.LOGIN_SCREEN);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        controller = screenPage;
    }

}
