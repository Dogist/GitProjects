/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.database.DAOException;
import at.htlpinkafeld.database_manager.pojo.Department;
import at.htlpinkafeld.database_manager.pojo.Employee;
import at.htlpinkafeld.database_manager.service.DeptService;
import at.htlpinkafeld.database_manager.service.DeptServiceDelegationImpl;
import at.htlpinkafeld.database_manager.service.EmpService;
import at.htlpinkafeld.database_manager.service.EmpServiceDelegationImpl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

/**
 *
 * @author Martin Six
 */
public class Emp_DeptScreenController implements Initializable, ToolBarDelegateInterface, ChangesObserver {

    private EmpService empService;
    private DeptService deptService;

    private StringConverter<Double> doubleStringConverter;

    private ChangesListener changesListener;

    @FXML
    private TreeView emp_DeptTreeView;

    @FXML
    private StackPane detailStackPane;

    @FXML
    private BorderPane employeePane;
    @FXML
    private TextField empnoField;
    @FXML
    private TextField enameField;
    @FXML
    private TextField jobField;
    @FXML
    private ComboBox<Employee> mgrBox;
    @FXML
    private DatePicker hiredatePicker;
    @FXML
    private TextField salField;
    @FXML
    private TextField commField;
    @FXML
    private ComboBox<Department> deptBox;

    @FXML
    private BorderPane departmentPane;
    @FXML
    private TextField deptnoField;
    @FXML
    private TextField dnameField;
    @FXML
    private TextField locField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        doubleStringConverter = new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Double fromString(String string) {
                return string.isEmpty() ? null : Double.parseDouble(string);
            }
        };

        empService = new EmpServiceDelegationImpl();
        deptService = new DeptServiceDelegationImpl();

        TreeItem rootNode = new TreeItem("Departments");
        rootNode.setExpanded(true);
        emp_DeptTreeView.setRoot(rootNode);

        reloadEmp_DeptTreeView();

        emp_DeptTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem>() {
            @Override
            public void changed(ObservableValue observable, TreeItem oldValue, TreeItem newValue) {
                if (newValue == null) {
                    detailStackPane.getChildren().clear();
                } else {
                    Object o = newValue.getValue();
                    if (oldValue == null || !(oldValue.getValue().getClass().equals(o.getClass()))) {
                        detailStackPane.getChildren().clear();
                        if (newValue.getValue() instanceof Department) {
                            detailStackPane.getChildren().add(departmentPane);
                        } else if (o instanceof Employee) {
                            detailStackPane.getChildren().add(employeePane);
                        }
                    }
                    if (o instanceof Department) {

                        updateDepartmentFields((Department) o);

                    } else if (o instanceof Employee) {
                        mgrBox.getItems().setAll(empService.getEmployees());
                        mgrBox.getItems().add(0, null);
                        mgrBox.getItems().remove((Employee) o);

                        updateEmployeeFields((Employee) o);
                    }
                }
            }
        });

        detailStackPane.getChildren().clear();
    }

    protected void reloadEmp_DeptTreeView() {
        ObservableList<TreeItem> deptNodes = emp_DeptTreeView.getRoot().getChildren();

        deptNodes.clear();

        List<Department> departments = deptService.getDepartments();

        deptBox.getItems().setAll(departments);

        List<Employee> employees = empService.getEmployees();

        for (Department d : departments) {
            TreeItem<Department> deptNode = new TreeItem<>(d);

            for (Employee e : employees) {
                if (d.equals(e.getDept())) {
                    TreeItem empLeaf = new TreeItem(e);
                    deptNode.getChildren().add(empLeaf);
                }
            }
            deptNodes.add(deptNode);
        }
    }

    private void updateEmployeeFields(Employee e) {
        if (e != null) {
            empnoField.setText(String.valueOf(e.getEmpno()));
            empnoField.setDisable(false);
            enameField.setText(e.getEname());
            enameField.setDisable(false);
            jobField.setText(e.getJob());
            jobField.setDisable(false);

            mgrBox.setDisable(false);
            if (e.getMgr() != null) {
                mgrBox.getSelectionModel().select(e.getMgr());
            } else {
                mgrBox.getSelectionModel().select(0);
                mgrBox.setValue(null);
            }

            hiredatePicker.setValue(e.getHiredate());
            hiredatePicker.setDisable(false);
            salField.setText(doubleStringConverter.toString(e.getSal()));
            salField.setDisable(false);
            commField.setText(doubleStringConverter.toString(e.getComm()));
            commField.setDisable(false);

            deptBox.getSelectionModel().select(e.getDept());
            deptBox.setDisable(false);

        } else {
            empnoField.setText("");
            empnoField.setDisable(true);
            enameField.setText("");
            enameField.setDisable(true);
            jobField.setText("");
            jobField.setDisable(true);

            mgrBox.getSelectionModel().clearSelection();
            mgrBox.setDisable(true);

            hiredatePicker.setValue(null);
            hiredatePicker.setDisable(true);

            salField.setText("");
            salField.setDisable(true);
            commField.setText("");
            commField.setDisable(true);
            deptBox.getSelectionModel().clearSelection();
            deptBox.setDisable(true);
        }
    }

    private void updateDepartmentFields(Department d) {
        if (d != null) {
            deptnoField.setText(String.valueOf(d.getDeptno()));
            deptnoField.setDisable(false);
            dnameField.setText(d.getDname());
            dnameField.setDisable(false);
            locField.setText(d.getLoc());
            locField.setDisable(false);

        } else {
            deptnoField.setText("");
            deptnoField.setDisable(true);
            dnameField.setText("");
            dnameField.setDisable(true);
            locField.setText("");
            locField.setDisable(true);
        }
    }

    @Override
    public void setChangesListener(ChangesListener changesListener) {
        this.changesListener = changesListener;
    }

    @FXML
    private void handleSaveActionButton(ActionEvent event) {
        TreeItem treeItem = ((TreeItem) emp_DeptTreeView.getSelectionModel().getSelectedItem());
        if (treeItem != null) {
            Object o = treeItem.getValue();
            if (o instanceof Department) {
                Department d = (Department) o;
                d.setDname(dnameField.getText());
                d.setLoc(locField.getText());

                TreeItem parent = treeItem.getParent();

                parent.getChildren().remove(treeItem);
                parent.getChildren().add(treeItem);

                emp_DeptTreeView.getSelectionModel().select(treeItem);

                changesListener.setUncommitedChanges();

                deptService.updateDepartment(d);

            } else if (o instanceof Employee) {
                Employee e = (Employee) o;
                e.setEname(enameField.getText());
                e.setJob(jobField.getText());
                e.setMgr(mgrBox.getValue());
                e.setHiredate(hiredatePicker.getValue());
                e.setSal(doubleStringConverter.fromString(salField.getText()));
                e.setComm(doubleStringConverter.fromString(commField.getText()));
                e.setDept(deptBox.getValue());

                TreeItem parent = treeItem.getParent();

                parent.getChildren().remove(treeItem);

                TreeItem newParent = ((TreeItem) emp_DeptTreeView.getRoot().getChildren().filtered(new Predicate() {
                    @Override
                    public boolean test(Object t) {
                        return ((TreeItem) t).getValue().equals(e.getDept());
                    }
                }).get(0));

                newParent.getChildren().add(treeItem);

                emp_DeptTreeView.getSelectionModel().select(treeItem);

                changesListener.setUncommitedChanges();

                empService.updateEmployee(e);
            }
        }
    }

    @FXML
    private void handleCancelActionButton(ActionEvent event) {
        TreeItem treeItem = ((TreeItem) emp_DeptTreeView.getSelectionModel().getSelectedItem());
        if (treeItem != null) {
            Object o = treeItem.getValue();
            if (o instanceof Department) {
                updateDepartmentFields((Department) o);
            } else if (o instanceof Employee) {
                updateEmployeeFields((Employee) o);
            }
        }
    }

    @Override
    public void handelInsertAction(ActionEvent event) {
        TreeItem treeItem = ((TreeItem) emp_DeptTreeView.getSelectionModel().getSelectedItem());
        if (treeItem != null && (treeItem.getValue() instanceof Department || treeItem.getValue() instanceof Employee)) {

            Object o = treeItem.getValue();
            Employee e = new Employee();
            if (o instanceof Department) {
                e.setDept((Department) o);
            } else {
                e.setDept(((Employee) o).getDept());
            }
            empService.insertEmployee(e);
            TreeItem empTItem = new TreeItem(e);
            if (o instanceof Department) {
                ((TreeItem) emp_DeptTreeView.getSelectionModel().getSelectedItem()).getChildren().add(empTItem);
            } else {
                ((TreeItem) emp_DeptTreeView.getSelectionModel().getSelectedItem()).getParent().getChildren().add(empTItem);
            }
            emp_DeptTreeView.getSelectionModel().select(empTItem);
        } else {
            Department d = new Department("", "");
            deptService.insertDepartment(d);
            TreeItem deptTItem = new TreeItem(d);
            emp_DeptTreeView.getRoot().getChildren().add(deptTItem);
            emp_DeptTreeView.getSelectionModel().select(deptTItem);
        }
        changesListener.setUncommitedChanges();
    }

    @Override
    public void handelSaveAction(ActionEvent event) {
        empService.commit();
        deptService.commit();

        emp_DeptTreeView.getSelectionModel().clearSelection();
        reloadEmp_DeptTreeView();

        changesListener.unsetUncommitedChanges();
    }

    @FXML
    @Override
    public void handelDeleteAction(ActionEvent event) {
        TreeItem ti = (TreeItem) emp_DeptTreeView.getSelectionModel().getSelectedItem();
        if (ti != null) {
            Object o = ti.getValue();
            if (o instanceof Employee || o instanceof Department) {
                try {
                    if (o instanceof Employee) {
                        empService.deleteEmployee((Employee) o);
                    } else {
                        deptService.deleteDepartment((Department) o);
                    }
                    ti.getParent().getChildren().remove(ti);

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
    }

    @Override
    public void handelRevertAction(ActionEvent event) {
        empService.rollback();
        deptService.rollback();

        emp_DeptTreeView.getSelectionModel().clearSelection();
        reloadEmp_DeptTreeView();

        changesListener.unsetUncommitedChanges();
    }

}
