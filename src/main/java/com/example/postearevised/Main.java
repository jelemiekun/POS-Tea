package com.example.postearevised;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Others.*;
import static com.example.postearevised.Miscellaneous.Reference.loginRegisterStage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String URL = Login.getURL();
        String TITLE = Login.getTITLE();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(URL));
        Scene scene = new Scene(fxmlLoader.load());
        loginRegisterStage = stage;
        loginRegisterStage.setTitle(TITLE);
        loginRegisterStage.setResizable(false);
        loginRegisterStage.setScene(scene);
        setScreenResolution(false, false);
        loginRegisterStage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}