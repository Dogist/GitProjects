/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.pojo.Department;
import com.sun.javafx.collections.ObservableListWrapper;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Martin Six
 */
public class DeptScreenController implements Initializable {

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
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(10, "ACOUNTING", "NEW YORK"));
        departments.add(new Department(20, "RESEARCH", "DALLAS"));
        departments.add(new Department(30, "SALES", "CHICAGO"));
        departments.add(new Department(40, "OPERATIONS", "BOSTON"));
        deptTableView.setItems(new ObservableListWrapper(departments));

        deptnoCol.setCellValueFactory(new PropertyValueFactory<>("deptno"));
        dnameCol.setCellValueFactory(new PropertyValueFactory<>("dname"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
    }

}
