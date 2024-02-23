package com.example.postearevised;

import com.example.postearevised.Miscellaneous.Enums.Scenes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource(Scenes.Login.getURL()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(Scenes.Login.getTITLE());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}