package com.example.postearevised.Models;

import com.example.postearevised.Controllers.LoginRegisterForgotPassController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Reference.*;

public class LoginModel {

    private LoginRegisterForgotPassController loginRegisterForgotPassController;

    public void setLoginRegisterController(LoginRegisterForgotPassController loginRegisterForgotPassController) {
        this.loginRegisterForgotPassController = loginRegisterForgotPassController;
    }

    public void setPane() {
        loginRegisterForgotPassController.anchorPaneLogin.setVisible(true);
        loginRegisterForgotPassController.anchorPaneRegister.setVisible(false);
        loginRegisterForgotPassController.anchorPaneForgotPass.setVisible(false);
    }

    /**
     * Main
     */

    public void setImageAndPasswordFieldAndCheckBox() {
        loginRegisterForgotPassController.btnLoginShowHidePassword.setImage(loginRegisterForgotPassController.showImage);
        loginRegisterForgotPassController.textFieldPassword.setVisible(true);
        loginRegisterForgotPassController.textFieldShowPassword.setVisible(false);
        loginRegisterForgotPassController.checkBoxRememberPassword.setSelected(false);
    }

    public void togglePasswordField() {
        loginRegisterForgotPassController.loginShowPassword = !loginRegisterForgotPassController.loginShowPassword;

        loginRegisterForgotPassController.btnLoginShowHidePassword.setImage(loginRegisterForgotPassController.loginShowPassword ? loginRegisterForgotPassController.hideImage : loginRegisterForgotPassController.showImage);
        loginRegisterForgotPassController.textFieldShowPassword.setVisible(loginRegisterForgotPassController.loginShowPassword);
        loginRegisterForgotPassController.textFieldPassword.setVisible(!loginRegisterForgotPassController.loginShowPassword);

        if (loginRegisterForgotPassController.loginShowPassword) {
            loginRegisterForgotPassController.textFieldShowPassword.setText(loginRegisterForgotPassController.textFieldPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldPassword.setText(loginRegisterForgotPassController.textFieldShowPassword.getText());
        }

        loginRegisterForgotPassController.btnLogin.requestFocus();
    }

    public void checkInputsBeforeLogin() throws IOException {
        boolean proceed = checkCredentials();

        if (proceed) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.getURL()));
            Parent root = loader.load();
            mainStage = new Stage();
            mainStage.setTitle(Main.getTITLE());
            mainStage.setResizable(false);
            mainStage.setScene(new Scene(root));
            mainStage.show();
            closeThisStage();
        }
    }

    // If account is found
    private boolean checkCredentials() {
        loginAccount = loginRegisterForgotPassController.textFieldAccount.getText();
        loginPassword = loginRegisterForgotPassController.loginShowPassword ? loginRegisterForgotPassController.textFieldShowPassword.getText() : loginRegisterForgotPassController.textFieldPassword.getText();

        if (loginAccount.equals(accountReference) && loginPassword.equals(passwordReference)) { // CHECK HERE SA DATABASE
            return true;
        } else {
            showIncorrectCredentials();
            return false;
        }
    }

    private void showIncorrectCredentials() {
        loginRegisterForgotPassController.labelIncorrectCredentials.setVisible(true);
    }

    private void closeThisStage() {
        Stage stage = (Stage) loginRegisterForgotPassController.labelUsername.getScene().getWindow();
        stage.close();
    }
}