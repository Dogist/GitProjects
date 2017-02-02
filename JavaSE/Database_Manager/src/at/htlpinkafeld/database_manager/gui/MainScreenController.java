/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.database.DAOFactory;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Martin Six
 */
public class MainScreenController implements Initializable, ChangesListener {

    @FXML
    private TextField connectionText;
    private static boolean uncommitedChanges = false;

    @FXML
    private Button insertButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button revertButton;

    @FXML
    private TabPane tabPane;

    @FXML
    private Parent empScreen;
    @FXML
    private EmpScreenController empScreenController;

    @FXML
    private Parent deptScreen;
    @FXML
    private DeptScreenController deptScreenController;

    @FXML
    private Parent emp_deptScreen;
    @FXML
    private Emp_DeptScreenController emp_deptScreenController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectionText.setText(DAOFactory.getConnectionData().toString());
//        Image i = new Image("resources/Plus.png");
//        ImageView iv = new ImageView(i);
//
//        insertButton.setGraphic(iv);
//        deleteButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/Minus.png"))));
//        saveButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/Save.png"))));
//        revertButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("resources/Refresh.png"))));

        empScreenController.setChangesListener(this);
        deptScreenController.setChangesListener(this);
        emp_deptScreenController.setChangesListener(this);

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                switch (tabPane.getSelectionModel().getSelectedItem().getId()) {
                    case "empTab":
                        empScreenController.reloadEmpListView();
                        break;
                    case "deptTab":
                        deptScreenController.reloadDeptTableView();
                        break;
                    case "emp_deptTab":
                        emp_deptScreenController.reloadEmp_DeptTreeView();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void setUncommitedChanges() {
        if (!uncommitedChanges) {
            connectionText.setText(DAOFactory.getConnectionData().toString() + "                                                                                          There are uncommited changes!");
            uncommitedChanges = true;
        }
    }

    @Override
    public void unsetUncommitedChanges() {
        if (uncommitedChanges) {
            connectionText.setText(DAOFactory.getConnectionData().toString());
            uncommitedChanges = false;
        }
    }

    @FXML
    private void handleCloseConnection(ActionEvent event) {
        if (uncommitedChanges) {
            handelRevertAction(event);
        }

        Alert alert = new Alert(AlertType.CONFIRMATION, "Close Connection ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            LoginScreenController.openLoginScreen();
            connectionText.getScene().getWindow().hide();
        }
    }

    @FXML
    private void handelInsertAction(ActionEvent event) {
        switch (tabPane.getSelectionModel().getSelectedItem().getId()) {
            case "empTab":
                empScreenController.handelInsertAction(event);
                break;
            case "deptTab":
                deptScreenController.handelInsertAction(event);
                break;
            case "emp_deptTab":
                emp_deptScreenController.handelInsertAction(event);
                break;
            default:
                break;
        }
    }

    @FXML
    private void handelSaveAction(ActionEvent event) {
        if (uncommitedChanges) {
            switch (tabPane.getSelectionModel().getSelectedItem().getId()) {
                case "empTab":
                    empScreenController.handelSaveAction(event);
                    break;
                case "deptTab":
                    deptScreenController.handelSaveAction(event);
                    break;
                case "emp_deptTab":
                    emp_deptScreenController.handelSaveAction(event);
                    break;
                default:
                    break;
            }

        }
    }

    @FXML
    private void handelDeleteAction(ActionEvent event) {
        switch (tabPane.getSelectionModel().getSelectedItem().getId()) {
            case "empTab":
                empScreenController.handelDeleteAction(event);
                break;
            case "deptTab":
                deptScreenController.handelDeleteAction(event);
                break;
            case "emp_deptTab":
                emp_deptScreenController.handelDeleteAction(event);
                break;
            default:
                break;
        }
    }

    @FXML
    private void handelRevertAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Revert all changes ?");

        if (uncommitedChanges) {
            alert.showAndWait();
        }
        if (uncommitedChanges || alert.getResult() == ButtonType.OK) {

            switch (tabPane.getSelectionModel().getSelectedItem().getId()) {
                case "empTab":
                    empScreenController.handelRevertAction(event);
                    break;
                case "deptTab":
                    deptScreenController.handelRevertAction(event);
                    break;
                case "emp_deptTab":
                    emp_deptScreenController.handelRevertAction(event);
                    break;
                default:
                    break;
            }
        }
    }

    public static void openMainScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainScreenController.class.getResource(Database_Manager.MAIN_SCREEN_FXML));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.UNIFIED);
            stage.setTitle("Database Manager");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
