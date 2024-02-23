package com.example.postearevised;

import com.example.postearevised.Miscellaneous.Enums.Scenes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Reference.loginRegisterStage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Scenes.Login.getURL()));
        Scene scene = new Scene(fxmlLoader.load());
        loginRegisterStage = stage;
        loginRegisterStage.setTitle(Scenes.Login.getTITLE());
        loginRegisterStage.setScene(scene);
        loginRegisterStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}