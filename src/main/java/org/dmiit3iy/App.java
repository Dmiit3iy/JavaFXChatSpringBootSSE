package org.dmiit3iy;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.dmiit3iy.сontroller.ChatController;
import org.dmiit3iy.сontroller.CloseEvent;
import org.dmiit3iy.сontroller.ControllerData;

import java.io.IOException;


public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("authorization.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static <T> Stage getStage(String name, String title, T data) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(name));


        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setScene(
                new Scene(loader.load())
        );

        stage.setTitle(title);
        if (data != null) {
            ControllerData<T> controller = loader.getController();
            controller.initData(data);

        }
        return stage;
    }

    public static <T> Stage getStage(String name, String title, T data, boolean isNeedClose) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(name));


        Stage stage = new Stage(StageStyle.DECORATED);

        stage.setScene(
                new Scene(loader.load())
        );

        stage.setTitle(title);
        if (data != null) {
            ControllerData<T> controller = loader.getController();
            controller.initData(data);
        }

        if (isNeedClose) {
            CloseEvent closeEvent = loader.getController();
            stage.setOnCloseRequest(closeEvent.getCloseEventHandler());
        }
        return stage;
    }


    public static <T> Stage openWindow(String name, String title, T data) throws IOException {

        Stage stage = getStage(name, title, data);
        stage.show();
        return stage;
    }

    public static <T> Stage openWindow(String name, String title, T data, boolean isNeed) throws IOException {

        Stage stage = getStage(name, title, data, isNeed);
        stage.show();
        return stage;
    }


    public static <T> Stage openWindowAndWait(String name, String title, T data) throws IOException {
        Stage stage = getStage(name, title, data);
        stage.showAndWait();

        return stage;
    }

    public static void closeWindow(Event event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static void showMessage(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}