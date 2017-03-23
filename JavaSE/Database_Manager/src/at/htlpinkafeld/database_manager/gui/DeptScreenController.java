/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.database.DAOException;
import at.htlpinkafeld.database_manager.pojo.Department;
import at.htlpinkafeld.database_manager.service.DeptService;
import at.htlpinkafeld.database_manager.service.DeptServiceDelegationImpl;
import com.sun.javafx.collections.ObservableListWrapper;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author Martin Six
 */
public class DeptScreenController implements Initializable, ToolBarDelegateInterface, ChangesObserver {

    private DeptService deptService;

    private ChangesListener changesListener;

    @FXML
    private TableView<Department> deptTableView;

    @FXML
    private TableColumn<Department, Integer> deptnoCol;

    @FXML
    private TableColumn<Department, String> dnameCol;

    @FXML
    private TableColumn<Department, String> locCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        deptService = new DeptServiceDelegationImpl();

        List<Department> departments = deptService.getDepartments();

        deptTableView.setItems(new ObservableListWrapper(departments));

        deptnoCol.setCellValueFactory(new PropertyValueFactory<>("deptno"));

        dnameCol.setCellValueFactory(new PropertyValueFactory<>("dname"));
        dnameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        locCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
        locCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @Override
    public void setChangesListener(ChangesListener changesListener) {
        this.changesListener = changesListener;
    }

    protected void reloadDeptTableView() {
        List<Department> departments = deptService.getDepartments();

        deptTableView.getItems().setAll(departments);
    }

    @FXML
    private void onDepartmentChange(TableColumn.CellEditEvent<Department, String> event) {
        Department d = event.getRowValue();

        if (event.getTableColumn().equals(dnameCol)) {
            d.setDname(event.getNewValue());
        } else if (event.getTableColumn().equals(locCol)) {
            d.setLoc(event.getNewValue());
        }

        deptService.updateDepartment(d);

        changesListener.setUncommitedChanges();
    }

    @Override
    public void handelInsertAction(ActionEvent event) {
        DeptDialogController.createDeptDialogStage(deptService, changesListener).showAndWait();
        deptTableView.getItems().setAll(deptService.getDepartments());
    }

    @Override
    public void handelSaveAction(ActionEvent event) {
        deptService.commit();
        deptTableView.getItems().setAll(deptService.getDepartments());

        changesListener.unsetUncommitedChanges();
    }

    @Override
    public void handelDeleteAction(ActionEvent event) {
        Department d = deptTableView.getSelectionModel().getSelectedItem();
        if (d != null) {
            try {
                deptService.deleteDepartment(d);
                deptTableView.getItems().remove(d);

                changesListener.setUncommitedChanges();
            } catch (DAOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Delete Error");
                alert.setHeaderText("Delete Error");
                alert.setContentText("An error occured during deletion!");

                alert.showAndWait();
            }
        }
    }

    @Override
    public void handelRevertAction(ActionEvent event) {
        deptService.rollback();
        deptTableView.getItems().setAll(deptService.getDepartments());

        changesListener.unsetUncommitedChanges();
    }

}
