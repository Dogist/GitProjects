/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.database.DAOType;
import com.sun.javafx.collections.ObservableListWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Martin Six
 */
public class LoginScreenController implements Initializable, ControlledScreen {

    private ScreensController controller;

    @FXML
    public ChoiceBox<DAOType> dbTypeChoiceBox;

    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("You clicked me!");
        controller.setScreen(Database_Manager.MAIN_SCREEN);
    }

    @FXML
    private void handleConnections(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/connectionDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Verbindungen verwalten");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbTypeChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(DAOType.values())));
        dbTypeChoiceBox.getSelectionModel().selectFirst();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        controller = screenPage;
    }

}
