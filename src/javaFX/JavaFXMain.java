package javaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class JavaFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLNumConverter.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("jfoenixTheme.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("NumConverter");
        primaryStage.setMaxHeight(800);
        primaryStage.setMaxWidth(500);
        primaryStage.setMinHeight(475);
        primaryStage.setMinWidth(330);
        primaryStage.getIcons().add(new Image("javaFX/converterIcon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
