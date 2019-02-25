package javaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLNumConverter.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("jfoenixTheme.css");
//        scene.getStylesheets().add("tableViewTheme.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("NumConverter");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
