/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.pojo.Department;
import at.htlpinkafeld.database_manager.service.DeptService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Martin Six
 */
public class DeptDialogController implements Initializable, ChangesObserver {

    private static DeptService deptService;

    private ChangesListener changesListener;

    @FXML
    private TextField deptnoField;
    @FXML
    private TextField dnameField;
    @FXML
    private TextField locField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setChangesListener(ChangesListener changesListener) {
        this.changesListener = changesListener;
    }

    @FXML
    private void insertDepartmentHandel(ActionEvent event) {
        deptService.insertDepartment(new Department(dnameField.getText().trim(), dnameField.getText().trim()));

        changesListener.setUncommitedChanges();

        deptnoField.getScene().getWindow().hide();
    }

    @FXML
    private void cancelInsertHandel(ActionEvent event) {
        deptnoField.getScene().getWindow().hide();
    }

    public static Stage createDeptDialogStage(DeptService deptService, ChangesListener changesListener) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DeptDialogController.class.getResource(Database_Manager.DEPT_DIALOG_SCREEN_FXML));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNIFIED);
            stage.setTitle("neue Abteilung erstellen");
            stage.setScene(new Scene(root));

            ((DeptDialogController) fxmlLoader.getController()).setChangesListener(changesListener);
            DeptDialogController.deptService = deptService;

            return stage;

        } catch (IOException ex) {
            Logger.getLogger(ConnectionDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
