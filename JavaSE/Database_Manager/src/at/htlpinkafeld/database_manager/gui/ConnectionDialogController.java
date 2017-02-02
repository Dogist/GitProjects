/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.connectionData.ConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.ConnectionDataManagement;
import at.htlpinkafeld.database_manager.dao.connectionData.HSQLDBConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.MySQLConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.OracleConnectionData;
import at.htlpinkafeld.database_manager.dao.database.DAOType;
import com.sun.javafx.collections.ObservableListWrapper;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Martin Six
 */
public class ConnectionDialogController implements Initializable {

    @FXML
    private ComboBox<ConnectionData> connectionDataComboBox;

    @FXML
    private ChoiceBox<DAOType> dbTypeChoiceBox;

    @FXML
    private Label serverOrPathLabel;
    @FXML
    private TextField serverOrPathField;

    @FXML
    private Label portLabel;
    @FXML
    private TextField portField;

    @FXML
    private Label dbNameLabel;
    @FXML
    private TextField dbNameField;

    private LoginScreenController parentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbTypeChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(DAOType.values())));
        dbTypeChoiceBox.getSelectionModel().selectFirst();

        dbTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DAOType>() {
            @Override
            public void changed(ObservableValue<? extends DAOType> observable, DAOType oldValue, DAOType newValue) {
                if (newValue.equals(DAOType.HSQLDB) && !oldValue.equals(DAOType.HSQLDB)) {
                    serverOrPathLabel.setText("Path");
                    serverOrPathField.clear();

                    portField.clear();
                    portField.setVisible(false);
                    portLabel.setVisible(false);

                    dbNameLabel.setVisible(false);
                    dbNameField.clear();
                    dbNameField.setVisible(false);

                } else if (oldValue.equals(DAOType.HSQLDB) && !newValue.equals(DAOType.HSQLDB)) {
                    serverOrPathLabel.setText("Server");
                    serverOrPathField.clear();

                    portField.setVisible(true);
                    portLabel.setVisible(true);

                    dbNameLabel.setVisible(true);
                    dbNameField.setVisible(true);

                }
            }
        });

        connectionDataComboBox.setItems(new ObservableListWrapper<>(ConnectionDataManagement.getConnectionData()));
        connectionDataComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConnectionData>() {
            @Override
            public void changed(ObservableValue<? extends ConnectionData> observable, ConnectionData oldValue, ConnectionData newValue) {
                if (newValue != null) {
                    dbTypeChoiceBox.getSelectionModel().select(newValue.getDaoType());
                    switch (newValue.getDaoType()) {
                        case HSQLDB:
                            serverOrPathField.setText(((HSQLDBConnectionData) newValue).getPath());
                            break;
                        case MySQL:
                            MySQLConnectionData msqlcd = (MySQLConnectionData) newValue;
                            serverOrPathField.setText(msqlcd.getServerUrl());
                            portField.setText(String.valueOf(msqlcd.getPort()));
                            dbNameField.setText(msqlcd.getDbName());
                            break;
                        case ORACLE:
                            OracleConnectionData ocd = (OracleConnectionData) newValue;
                            serverOrPathField.setText(ocd.getServerUrl());
                            portField.setText(String.valueOf(ocd.getPort()));
                            dbNameField.setText(ocd.getDbName());
                            break;
                        default:
                    }
                }
            }
        });

    }

    @FXML
    private void useConnectionData(ActionEvent event) {
        parentController.setDbTypeChoiceBoxValue(dbTypeChoiceBox.getValue());
        parentController.setDbNameFieldText(dbNameField.getText());
        parentController.setPortFieldText(portField.getText());
        parentController.setServerOrPathFieldText(serverOrPathField.getText());

        dbTypeChoiceBox.getScene().getWindow().hide();
    }

    @FXML
    private void insertConnectionData(ActionEvent event) {
        ConnectionData cd = null;
        switch (dbTypeChoiceBox.getValue()) {
            case HSQLDB:
                cd = new HSQLDBConnectionData(null, null, serverOrPathField.getText());
                break;
            case MySQL:
                cd = new MySQLConnectionData(serverOrPathField.getText(), Integer.parseInt(portField.getText()), dbNameField.getText(), null, null);
                break;
            case ORACLE:
                cd = new OracleConnectionData(serverOrPathField.getText(), Integer.parseInt(portField.getText()), dbNameField.getText(), null, null);
                break;
            default:
        }
        ConnectionDataManagement.insertConnectionData(cd);

        connectionDataComboBox.getItems().add(cd);
    }

    @FXML
    private void deleteConnectionData(ActionEvent event) {
        ConnectionData cd = null;
        switch (dbTypeChoiceBox.getValue()) {
            case HSQLDB:
                cd = new HSQLDBConnectionData(null, null, serverOrPathField.getText());
                break;
            case MySQL:
                cd = new MySQLConnectionData(serverOrPathField.getText(), Integer.parseInt(portField.getText()), dbNameField.getText(), null, null);
                break;
            case ORACLE:
                cd = new OracleConnectionData(serverOrPathField.getText(), Integer.parseInt(portField.getText()), dbNameField.getText(), null, null);
                break;
            default:
        }
        ConnectionDataManagement.deleteConnectionData(cd);

        connectionDataComboBox.getItems().remove(cd);
    }

    public static void openConnectionManagerScreen(LoginScreenController controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ConnectionDialogController.class.getResource(Database_Manager.CONNECTIONDIALOG_SCREEN_SCREEN_FXML));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNIFIED);
            stage.setTitle("Verbindungen verwalten");
            stage.setScene(new Scene(root));

            ((ConnectionDialogController) fxmlLoader.getController()).parentController = controller;

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ConnectionDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
