/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.database_manager.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Martin Six
 */
public class Database_Manager extends Application {

//    public static final String LOGIN_SCREEN = "login";
    public static final String LOGIN_SCREEN_FXML = "resources/login.fxml";
//    public static final String EMP_SCREEN = "emp";
    public static final String EMP_SCREEN_FXML = "emp.fxml";
//    public static final String MAIN_SCREEN = "main_screen";
    public static final String MAIN_SCREEN_FXML = "resources/main_screen.fxml";
//    public static final String CONNECTIONDIALOG_SCREEN = "connectionDialog";
    public static final String CONNECTIONDIALOG_SCREEN_SCREEN_FXML = "resources/connectionDialog.fxml";
//    public static final String DEPT_SCREEN = "dept";
    public static final String DEPT_SCREEN_FXML = "dept.fxml";
    public static final String DEPT_DIALOG_SCREEN_FXML = "resources/deptDialog.fxml";
//    public static final String EMP_DEPT_SCREEN = "emp_dept";
    public static final String EMP_DEPT_SCREEN_FXML = "emp_dept.fxml";

    @Override
    public void start(Stage stage) throws Exception {
//        ScreensController mainContainer = new ScreensController();
//        mainContainer.loadScreen(Database_Manager.LOGIN_SCREEN, Database_Manager.LOGIN_SCREEN_FXML);
//        mainContainer.loadScreen(Database_Manager.MAIN_SCREEN, Database_Manager.MAIN_SCREEN_FXML);
//        mainContainer.loadScreen(Database_Manager.CONNECTIONDIALOG_SCREEN, Database_Manager.CONNECTIONDIALOG_SCREEN_SCREEN_FXML);
//        mainContainer.loadScreen(Database_Manager.DEPT_SCREEN, Database_Manager.DEPT_SCREEN_FXML);
//        mainContainer.loadScreen(Database_Manager.EMP_DEPT_SCREEN, Database_Manager.EMP_DEPT_SCREEN_FXML);

//        mainContainer.setScreen(Database_Manager.LOGIN_SCREEN);
//        Group root = new Group();
//        root.getChildren().addAll(mainContainer);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(LOGIN_SCREEN_FXML));
        Parent root = (Parent) fxmlLoader.load();
        stage.initStyle(StageStyle.UNIFIED);
        stage.setTitle("Database Manager - Login");
        stage.setScene(new Scene(root));
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
