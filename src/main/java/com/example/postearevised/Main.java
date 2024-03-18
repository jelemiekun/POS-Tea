package com.example.postearevised;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.SYSTEM_LOGO;
import static com.example.postearevised.Miscellaneous.InternetAndResolution.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.loginRegisterStage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String URL = LOGIN_ENUM.getURL();
        String TITLE = LOGIN_ENUM.getTITLE();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(URL));
        Scene scene = new Scene(fxmlLoader.load());
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