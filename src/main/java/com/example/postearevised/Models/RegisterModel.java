package com.example.postearevised.Models;

import com.example.postearevised.Controllers.RegisterController;
import com.example.postearevised.Miscellaneous.Enums.Scenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Reference.mainStage;

public class RegisterModel {
    private RegisterController registerController;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }

    public void setAttributes() {
        username = registerController.textFieldUsername.getText();
        email = registerController.textFieldEmail.getText();
        password = registerController.textFieldPassword.getText();
        confirmPassword = registerController.textFieldConfirmPassword.getText();
    }

    public void checkTextFields() {
        setAttributes();
        if (notEmptyTextFields()) {
            System.out.println("not empty fields");
        } else {
            setAttributes();
            System.out.println(username.isEmpty());
            registerController.labelUsername.setVisible(username.isEmpty());
            registerController.labelEmail.setVisible(email.isEmpty());
            registerController.labelPassword.setVisible(password.isEmpty());
            registerController.labelConfirmPassword.setVisible(confirmPassword.isEmpty());
        }
    }

    private boolean notEmptyTextFields() {
        return !username.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty();
    }

    public void close() throws IOException {
        setAttributes();
        if (notEmptyTextFields()) {
            System.out.println("meow");
        } else {
            goToLogin();
        }
    }

    private void goToLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Scenes.Login.getURL()));
        Parent root = loader.load();
        mainStage = new Stage();
        mainStage.setTitle(Scenes.Login.getTITLE());
        mainStage.setScene(new Scene(root));
        mainStage.show();
        closeThisStage();
    }

    private void closeThisStage() {
        Stage stage = (Stage) registerController.labelUsername.getScene().getWindow();
        stage.close();
    }
}
