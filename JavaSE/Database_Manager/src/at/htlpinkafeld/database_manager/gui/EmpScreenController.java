/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import at.htlpinkafeld.database_manager.dao.database.DAOException;
import at.htlpinkafeld.database_manager.dao.database.SQLEmpDAO;
import at.htlpinkafeld.database_manager.gui.employeeList_utils.EmployeeComparators;
import at.htlpinkafeld.database_manager.gui.employeeList_utils.EmployeeFilters;
import at.htlpinkafeld.database_manager.pojo.Department;
import at.htlpinkafeld.database_manager.pojo.Employee;
import at.htlpinkafeld.database_manager.service.DeptService;
import at.htlpinkafeld.database_manager.service.DeptServiceDelegationImpl;
import at.htlpinkafeld.database_manager.service.EmpService;
import at.htlpinkafeld.database_manager.service.EmpServiceDelegationImpl;
import com.sun.javafx.collections.ObservableListWrapper;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

/**
 *
 * @author Martin Six
 */
public class EmpScreenController implements Initializable, ToolBarDelegateInterface, ChangesObserver {

    private EmpService empService;
    private DeptService deptService;
    private StringConverter<Double> doubleStringConverter;
    private StringConverter<Integer> integerStringConverter;

    private ChangesListener changesListener;

    @FXML
    private ListView<Employee> employeeListView;
    private FilteredList<Employee> filteredEmployees;
    private ObservableList<Employee> observableEmployees;

    @FXML
    private ChoiceBox<String> comparatorChoiceBox;
    private final String[] stringComparator = {"==", "!=", "like"};
    private final String[] otherComparator = {"==", "!=", "<=", ">=", "<", ">"};

    @FXML
    private ChoiceBox<String> columnChoiceBox;

    @FXML
    private Pane searchInputPane;
    private TextField searchInputTextField;
    private DatePicker searchInputDatePicker;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        empService = new EmpServiceDelegationImpl();
        deptService = new DeptServiceDelegationImpl();

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

        integerStringConverter = new StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return string.isEmpty() ? null : Integer.parseInt(string);
            }
        };

        observableEmployees = new ObservableListWrapper<>(empService.getEmployees());

        filteredEmployees = new FilteredList<>(observableEmployees);

        employeeListView.setItems(new SortedList<>(filteredEmployees, new EmployeeComparators.EmpnoComparator()));
        mgrBox.setItems(new ObservableListWrapper(empService.getEmployees()));
        employeeListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
            @Override
            public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee newValue) {
                if (newValue != null) {

                    mgrBox.getItems().setAll(empService.getEmployees());
                    mgrBox.getItems().remove(newValue);
                    mgrBox.getItems().add(0, null);

                    updateFields(newValue);
                }
            }
        });

        initializeSearchFunction();

        deptBox.setItems(new ObservableListWrapper<>(deptService.getDepartments()));

        UnaryOperator<TextFormatter.Change> doubleFilter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                String text = change.getControlNewText();
                if (text.matches("[0-9]{1,7}|[0-9]{1,7}\\.[0-9]{0,2}|")) {
                    return change;
                }
                return null;
            }
        };

        salField.setTextFormatter(new TextFormatter<>(doubleFilter));
        commField.setTextFormatter(new TextFormatter<>(doubleFilter));

    }

    @Override
    public void setChangesListener(ChangesListener changesListener) {
        this.changesListener = changesListener;
    }

    protected void reloadEmpListView() {
        observableEmployees.setAll(empService.getEmployees());

        deptBox.getItems().setAll(deptService.getDepartments());

        updateFields(null);
    }

    private void updateFields(Employee e) {
        if (e != null) {
            empnoField.setText(String.valueOf(e.getEmpno()));
            empnoField.setDisable(false);
            enameField.setText(e.getEname());
            enameField.setDisable(false);
            jobField.setText(e.getJob());
            jobField.setDisable(false);

            if (e.getMgr() != null) {
                mgrBox.getSelectionModel().select(e.getMgr());
            } else {
                mgrBox.getSelectionModel().select(0);
                mgrBox.setValue(null);
            }
            mgrBox.setDisable(false);

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

    private void initializeSearchFunction() {
        searchInputTextField = new TextField();
        searchInputTextField.prefWidthProperty().bind(searchInputPane.widthProperty());
        searchInputTextField.prefHeightProperty().bind(searchInputPane.heightProperty());

        searchInputTextField.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                String text = change.getControlNewText();
                if (text.matches("[0-9]{0,7}")) {
                    return change;
                }
                return null;
            }
        }));
        searchInputPane.getChildren().add(searchInputTextField);

        searchInputDatePicker = new DatePicker();

        searchInputDatePicker.prefWidthProperty()
                .bind(searchInputPane.widthProperty());
        searchInputDatePicker.prefHeightProperty()
                .bind(searchInputPane.heightProperty());

        columnChoiceBox.setItems(
                new ObservableListWrapper<>(Arrays.asList(SQLEmpDAO.ALL_COLUMNS)));
        columnChoiceBox.getSelectionModel()
                .selectFirst();

        comparatorChoiceBox.setItems(
                new ObservableListWrapper<>(Arrays.asList(otherComparator)));

        columnChoiceBox.getSelectionModel()
                .selectedItemProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        //TODO Wrong Textformater if switch from job to ename or similar
                        if (newValue.contentEquals(SQLEmpDAO.ENAME_COL) || newValue.contentEquals(SQLEmpDAO.JOB_COL)) {
                            comparatorChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(stringComparator)));
                            comparatorChoiceBox.getSelectionModel().selectFirst();

                            searchInputPane.getChildren().clear();
                            searchInputTextField.clear();
                            searchInputTextField.setTextFormatter(null);
                            searchInputPane.getChildren().add(searchInputTextField);
                        } else if (!newValue.contentEquals(SQLEmpDAO.HIREDATE_COL)) {
                            comparatorChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(otherComparator)));
                            comparatorChoiceBox.getSelectionModel().selectFirst();

                            searchInputPane.getChildren().clear();
                            searchInputTextField.clear();

                            if (newValue.contentEquals(SQLEmpDAO.SAL_COL) || newValue.contentEquals(SQLEmpDAO.COMM_COL)) {
                                searchInputTextField.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
                                    @Override
                                    public TextFormatter.Change apply(TextFormatter.Change change) {
                                        String text = change.getControlNewText();
                                        if (text.matches("[0-9]{1,7}|[0-9]{1,7}\\.[0-9]{0,2}|")) {
                                            return change;
                                        }
                                        return null;
                                    }
                                }));
                            } else {
                                searchInputTextField.setTextFormatter(new TextFormatter<>(new UnaryOperator<TextFormatter.Change>() {
                                    @Override
                                    public TextFormatter.Change apply(TextFormatter.Change change) {
                                        String text = change.getControlNewText();
                                        if (text.matches("[0-9]{0,7}")) {
                                            return change;
                                        }
                                        return null;
                                    }
                                }));
                            }
                            searchInputPane.getChildren().add(searchInputTextField);
                        } else {
                            comparatorChoiceBox.setItems(new ObservableListWrapper<>(Arrays.asList(otherComparator)));
                            comparatorChoiceBox.getSelectionModel().selectFirst();

                            searchInputPane.getChildren().clear();
                            searchInputPane.getChildren().add(searchInputDatePicker);

                        }
                    }
                }
                );

        comparatorChoiceBox.getSelectionModel()
                .selectFirst();

    }

    @FXML
    private void handleSearchAction(ActionEvent event) {

        switch (columnChoiceBox.getValue()) {
            case SQLEmpDAO.EMPNO_COL:
                filteredEmployees.setPredicate(new EmployeeFilters.EmpnoFilter(integerStringConverter.fromString(searchInputTextField.getText()), comparatorChoiceBox.getValue()));
                break;
            case SQLEmpDAO.ENAME_COL:
                filteredEmployees.setPredicate(new EmployeeFilters.EnameFilter(searchInputTextField.getText(), comparatorChoiceBox.getValue()));
                break;
            case SQLEmpDAO.JOB_COL:
                filteredEmployees.setPredicate(new EmployeeFilters.JobFilter(searchInputTextField.getText(), comparatorChoiceBox.getValue()));
                break;
            case SQLEmpDAO.MGR_COL:
                filteredEmployees.setPredicate(new EmployeeFilters.MgrFilter(integerStringConverter.fromString(searchInputTextField.getText()), comparatorChoiceBox.getValue()));
                break;
            case SQLEmpDAO.HIREDATE_COL:
                filteredEmployees.setPredicate(new EmployeeFilters.HiredateFilter(searchInputDatePicker.getValue(), comparatorChoiceBox.getValue()));
                break;
            case SQLEmpDAO.SAL_COL:
                filteredEmployees.setPredicate(new EmployeeFilters.SalFilter(doubleStringConverter.fromString(searchInputTextField.getText()), comparatorChoiceBox.getValue()));
                break;
            case SQLEmpDAO.COMM_COL:
                filteredEmployees.setPredicate(new EmployeeFilters.CommFilter(doubleStringConverter.fromString(searchInputTextField.getText()), comparatorChoiceBox.getValue()));
                break;
            case SQLEmpDAO.DEPTNO_COL:
                filteredEmployees.setPredicate(new EmployeeFilters.DeptnoFilter(integerStringConverter.fromString(searchInputTextField.getText()), comparatorChoiceBox.getValue()));
                break;
        }
    }

    @FXML
    private void handleSaveEmployeeAction(ActionEvent event) {
        Employee e = employeeListView.getSelectionModel().getSelectedItem();
        if (e != null) {
            e.setEname(enameField.getText());
            e.setJob(jobField.getText());
            e.setMgr(mgrBox.getValue());
            e.setHiredate(hiredatePicker.getValue());
            e.setSal(doubleStringConverter.fromString(salField.getText()));
            e.setComm(doubleStringConverter.fromString(commField.getText()));
            e.setDept(deptBox.getValue());

            empService.updateEmployee(e);

            observableEmployees.remove(e);
            observableEmployees.add(e);

            employeeListView.getSelectionModel().select(e);

            mgrBox.setValue(e.getMgr());

            changesListener.setUncommitedChanges();

        }
    }

    @FXML
    private void handleCancelEmployeeAction(ActionEvent event) {
        Employee e = employeeListView.getSelectionModel().getSelectedItem();
        if (e != null) {
            updateFields(e);
        }
    }

    @Override
    public void handelInsertAction(ActionEvent event) {
        Employee e = new Employee();
        e.setDept(deptService.getDepartments().get(0));
        empService.insertEmployee(e);
        observableEmployees.add(e);
        employeeListView.getSelectionModel().select(e);
        changesListener.setUncommitedChanges();
    }

    @Override
    public void handelSaveAction(ActionEvent event) {
        empService.commit();
        observableEmployees.setAll(empService.getEmployees());
        updateFields(null);
        changesListener.unsetUncommitedChanges();
    }

    @FXML
    @Override
    public void handelDeleteAction(ActionEvent event) {
        Employee e = employeeListView.getSelectionModel().getSelectedItem();
        if (e != null) {
            try {
                empService.deleteEmployee(e);
                updateFields(null);
                observableEmployees.remove(e);
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
        empService.rollback();
        observableEmployees.setAll(empService.getEmployees());
        employeeListView.getSelectionModel().clearSelection();
        updateFields(null);
        changesListener.unsetUncommitedChanges();
    }

}
