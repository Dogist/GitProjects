/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.database.SQLEmpDAO;
import at.htlpinkafeld.database_manager.pojo.Department;
import at.htlpinkafeld.database_manager.pojo.Employee;
import com.sun.javafx.collections.ObservableListWrapper;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

/**
 *
 * @author Martin Six
 */
public class EmpScreenController implements Initializable {

    @FXML
    public ListView<Employee> employeeListView;

    @FXML
    public ChoiceBox<String> comparatorChoiceBox;
    private final String[] stringComparator = {"==", "!="};
    private final String[] otherComparator = {"==", "!=", "<=", ">=", "<", ">"};

    @FXML
    public ChoiceBox<String> columnChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Department d10 = new Department(10, "ACOUNTING", "NEW YORK");
        Department d20 = new Department(20, "RESEARCH", "DALLAS");
        Department d30 = new Department(30, "SALES", "CHICAGO");
        Department d40 = new Department(40, "OPERATIONS", "BOSTON");

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(7839, "KING", "PRESIDENT", null, LocalDate.MIN, 5000.0, null, d10));
        employees.add(new Employee(7698, "BLAKE", "MANAGER", employees.get(0), LocalDate.MIN, 2850.0, null, d30));
        employees.add(new Employee(7782, "CLARK", "MANAGER", employees.get(0), LocalDate.MIN, 2450.0, null, d10));
        employees.add(new Employee(7566, "JONES", "MANAGER", null, LocalDate.MIN, 2975.0, null, d20));
        employees.add(new Employee(7788, "SCOTT", "ANALYST", null, LocalDate.MIN, 3000.0, null, d20));
        employees.add(new Employee(7902, "FORD", "ANALYST", null, LocalDate.MIN, 3000.0, null, d20));
        employees.add(new Employee(7369, "SMITH", "CLERK", null, LocalDate.MIN, 800.0, null, d20));
        employees.add(new Employee(7499, "ALLEN", "SALESMAN", null, LocalDate.MIN, 1600.0, 300.0, d30));
        employees.add(new Employee(7521, "WARD", "SALESMAN", null, LocalDate.MIN, 1250.0, 500.0, d30));
        employees.add(new Employee(7654, "MARTIN", "SALESMAN", null, LocalDate.MIN, 1250.0, 1400.0, d30));
        employees.add(new Employee(7844, "TURNER", "SALESMAN", null, LocalDate.MIN, 1500.0, 0.0, d30));
        employeeListView.setItems(new ObservableListWrapper(employees));

        columnChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(SQLEmpDAO.ALL_COLUMNS)));
        columnChoiceBox.getSelectionModel().selectFirst();

        comparatorChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(otherComparator)));
        comparatorChoiceBox.getSelectionModel().selectFirst();

        columnChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if ((newValue.contentEquals(SQLEmpDAO.ENAME_COL) || newValue.contentEquals(SQLEmpDAO.JOB_COL)) && comparatorChoiceBox.getItems().size() != stringComparator.length) {
                    comparatorChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(stringComparator)));
                    comparatorChoiceBox.getSelectionModel().selectFirst();
                } else if (comparatorChoiceBox.getItems().size() != otherComparator.length) {
                    comparatorChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(otherComparator)));
                    comparatorChoiceBox.getSelectionModel().selectFirst();
                }
            }
        });
    }

}
