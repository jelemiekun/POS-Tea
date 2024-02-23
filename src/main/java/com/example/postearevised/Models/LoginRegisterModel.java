package com.example.postearevised.Models;

import com.example.postearevised.Controllers.LoginRegisterController;
import com.example.postearevised.Miscellaneous.Enums.Scenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Reference.*;

public class LoginRegisterModel {

    private LoginRegisterController loginRegisterController;

    public void setLoginRegisterController(LoginRegisterController loginRegisterController) {
        this.loginRegisterController = loginRegisterController;
    }

    public void setPane() {
        loginRegisterController.anchorPaneLogin.setVisible(true);
        loginRegisterController.anchorPaneRegister.setVisible(false);
    }

    public void togglePane() {
        loginRegisterController.anchorPaneLogin.setVisible(!loginRegisterController.anchorPaneLogin.isVisible());
        loginRegisterController.anchorPaneRegister.setVisible(!loginRegisterController.anchorPaneRegister.isVisible());
    }

    /**
     * Login
     */

    public void setImage() {
        loginRegisterController.btnShowHidePassword.setImage(loginRegisterController.showImage);
    }

    public void togglePasswordField() {
        loginRegisterController.showPassword = !loginRegisterController.showPassword;

        loginRegisterController.btnShowHidePassword.setImage(loginRegisterController.showPassword ? loginRegisterController.hideImage : loginRegisterController.showImage);
        loginRegisterController.textFieldShowPassword.setVisible(loginRegisterController.showPassword);
        loginRegisterController.textFieldPassword.setVisible(!loginRegisterController.showPassword);

        if (loginRegisterController.showPassword) {
            loginRegisterController.textFieldShowPassword.setText(loginRegisterController.textFieldPassword.getText());
        } else {
            loginRegisterController.textFieldPassword.setText(loginRegisterController.textFieldShowPassword.getText());
        }

        loginRegisterController.btnLogin.requestFocus();
    }

    public void goToAnotherScene(boolean isLogin) throws IOException {
        boolean proceed;
        String title = isLogin ? Main.getTITLE() : Register.getTITLE();
        String url = isLogin ? Main.getURL(): Register.getURL();

        if (isLogin) {
            proceed = checkInputsIfBlank() && checkCredentials();
        } else {
            proceed = true;
        }

        if (proceed) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Parent root = loader.load();
            mainStage = new Stage();
            mainStage.setTitle(title);
            mainStage.setScene(new Scene(root));
            mainStage.show();
            closeThisStage();
        }
    }

    // If fields are not empty
    private boolean checkInputsIfBlank() {
        return !loginRegisterController.textFieldAccount.getText().isEmpty() && !loginRegisterController.textFieldPassword.getText().isEmpty();
    }

    // If account is found
    private boolean checkCredentials() {
        String account = loginRegisterController.textFieldAccount.getText();
        String password = loginRegisterController.showPassword ? loginRegisterController.textFieldShowPassword.getText() : loginRegisterController.textFieldPassword.getText();

        if (account.equals(accountReference) && password.equals(passwordReference)) { // CHECK HERE SA DATABASE
            return true;
        } else {
            showIncorrectCredentials();
            return false;
        }
    }

    private void showIncorrectCredentials() {
        loginRegisterController.labelIncorrectCredentials.setVisible(true);
    }

    /**
     * Register
     */
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public void setAttributes() {
        username = loginRegisterController.textFieldUsername.getText();
        email = loginRegisterController.textFieldEmail.getText();
        password = loginRegisterController.textFieldPassword1.getText();
        confirmPassword = loginRegisterController.textFieldConfirmPassword.getText();
    }

    public void checkTextFields() {
        setAttributes();
        if (notEmptyTextFields()) {
            System.out.println("not empty fields");
        } else {
            setAttributes();
            System.out.println(username.isEmpty());
            loginRegisterController.labelUsername.setVisible(username.isEmpty());
            loginRegisterController.labelEmail.setVisible(email.isEmpty());
            loginRegisterController.labelPassword.setVisible(password.isEmpty());
            loginRegisterController.labelConfirmPassword.setVisible(confirmPassword.isEmpty());
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
        Stage stage = (Stage) loginRegisterController.labelUsername.getScene().getWindow();
        stage.close();
    }
}