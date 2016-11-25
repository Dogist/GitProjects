/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.database.DAOType;
import com.sun.javafx.collections.ObservableListWrapper;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Martin Six
 */
public class ConnectionDialogController implements Initializable {

    @FXML
    public ChoiceBox<DAOType> dbTypeChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbTypeChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(DAOType.values())));
        dbTypeChoiceBox.getSelectionModel().selectFirst();
    }

}
