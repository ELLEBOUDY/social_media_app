package com.socialmedia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
            Scene scene = new Scene(loader.load(), 1100, 700);

            primaryStage.setTitle("Social Media App - Login");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false); // يمنع شد الأطراف
            primaryStage.setMinWidth(1400);
            primaryStage.setMinHeight(800);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}