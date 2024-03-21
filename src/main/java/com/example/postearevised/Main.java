package com.example.postearevised;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.Others.Resolution.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        String URL = LOGIN_ENUM.getURL();
        String TITLE = LOGIN_ENUM.getTITLE();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(URL));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(true);
        }

        loginRegisterStage = stage;
        loginRegisterStage.setTitle(TITLE);
        loginRegisterStage.setResizable(false);
        loginRegisterStage.setScene(scene);
        setScreenResolution(false, false);
        loginRegisterStage.getIcons().add(SYSTEM_LOGO);
        loginRegisterStage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}