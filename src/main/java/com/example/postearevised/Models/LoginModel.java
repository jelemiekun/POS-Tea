package com.example.postearevised.Models;

import com.example.postearevised.Miscellaneous.Enums.Scenes;
import com.example.postearevised.Controllers.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Reference.mainStage;

public class LoginModel {
    private String accountReference = "admin";
    private String passwordReference = "admin";
    /**
     * Remove nasa taas pag may database na
     */
    private LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setImage() {
        loginController.btnShowHidePassword.setImage(loginController.showImage);
    }

    public void togglePasswordField() {
        loginController.showPassword = !loginController.showPassword;

        loginController.btnShowHidePassword.setImage(loginController.showPassword ? loginController.hideImage : loginController.showImage);
        loginController.textFieldShowPassword.setVisible(loginController.showPassword);
        loginController.textFieldPassword.setVisible(!loginController.showPassword);

        if (loginController.showPassword) {
            loginController.textFieldShowPassword.setText(loginController.textFieldPassword.getText());
        } else {
            loginController.textFieldPassword.setText(loginController.textFieldShowPassword.getText());
        }

        loginController.btnLogin.requestFocus();
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
        return !loginController.textFieldAccount.getText().isBlank() && !loginController.textFieldPassword.getText().isBlank();
    }

    // If account is found
    private boolean checkCredentials() {
        String account = loginController.textFieldAccount.getText();
        String password = loginController.showPassword ? loginController.textFieldShowPassword.getText() : loginController.textFieldPassword.getText();

        if (account.equals(accountReference) && password.equals(passwordReference)) {
            return true;
        } else {
            showIncorrectCredentials();
            return false;
        }
    }

    private void showIncorrectCredentials() {
        loginController.labelIncorrectCredentials.setVisible(true);
    }

    private void closeThisStage() {
        Stage stage = (Stage) loginController.btnLogin.getScene().getWindow();
        stage.close();
    }
}