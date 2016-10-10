/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_manager.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Martin Six
 */
public class Database_Manager extends Application {

    public static final String LOGIN_SCREEN = "login";
    public static final String LOGIN_SCREEN_FXML = "login.fxml";
    public static final String EMP_SCREEN = "emp";
    public static final String EMP_SCREEN_FXML = "emp.fxml";
    public static final String DEPT_SCREEN = "dept";
    public static final String DEPT_SCREEN_FXML = "dept.fxml";
    public static final String EMP_DEPT_SCREEN = "emp_dept";
    public static final String EMP_DEPT_SCREEN_FXML = "emp_dept.fxml";

    @Override
    public void start(Stage stage) throws Exception {
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Database_Manager.LOGIN_SCREEN, Database_Manager.LOGIN_SCREEN_FXML);
//        mainContainer.loadScreen(Database_Manager.EMP_SCREEN, Database_Manager.EMP_SCREEN_FXML);
//        mainContainer.loadScreen(Database_Manager.DEPT_SCREEN, Database_Manager.DEPT_SCREEN_FXML);
//        mainContainer.loadScreen(Database_Manager.EMP_DEPT_SCREEN, Database_Manager.EMP_DEPT_SCREEN_FXML);

        mainContainer.setScreen(Database_Manager.LOGIN_SCREEN);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
