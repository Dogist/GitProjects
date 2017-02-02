/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.connectionData.ConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.HSQLDBConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.MySQLConnectionData;
import at.htlpinkafeld.database_manager.dao.connectionData.OracleConnectionData;
import at.htlpinkafeld.database_manager.dao.database.DAOFactory;
import at.htlpinkafeld.database_manager.dao.database.DAOType;
import com.sun.javafx.collections.ObservableListWrapper;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Martin Six
 */
public class LoginScreenController implements Initializable {

    @FXML
    private ChoiceBox<DAOType> dbTypeChoiceBox;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

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

    @FXML
    private void handleLogin(ActionEvent event) {
        ConnectionData cd = null;
        try {
            switch (dbTypeChoiceBox.getValue()) {
                case HSQLDB:
                    cd = new HSQLDBConnectionData(usernameField.getText(), passwordField.getText(), serverOrPathField.getText());
                    break;
                case MySQL:
                    cd = new MySQLConnectionData(serverOrPathField.getText(), Integer.parseInt(portField.getText()), dbNameField.getText(), usernameField.getText(), passwordField.getText());
                    break;
                case ORACLE:
                    cd = new OracleConnectionData(serverOrPathField.getText(), Integer.parseInt(portField.getText()), dbNameField.getText(), usernameField.getText(), passwordField.getText());
                    break;
            }

            DAOFactory.setDAOFactory(cd);

            MainScreenController.openMainScreen();
            dbTypeChoiceBox.getScene().getWindow().hide();
        } catch (SQLException | NumberFormatException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Connection Error");
            alert.setHeaderText("Connection Error");
            alert.setContentText(ex.getLocalizedMessage());

            alert.showAndWait();
        }
    }

    @FXML
    private void handleConnections(ActionEvent event) {

        ConnectionDialogController.openConnectionManagerScreen(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    }

    public final void setDbNameFieldText(String value) {
        dbNameField.setText(value);
    }

    public final void setDbTypeChoiceBoxValue(DAOType value) {
        dbTypeChoiceBox.setValue(value);
    }

    public final void setPortFieldText(String value) {
        portField.setText(value);
    }

    public final void setServerOrPathFieldText(String value) {
        serverOrPathField.setText(value);
    }

    public static void openLoginScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginScreenController.class.getResource(Database_Manager.LOGIN_SCREEN_FXML));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UNIFIED);
            stage.setTitle("Database Manager - Login");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
