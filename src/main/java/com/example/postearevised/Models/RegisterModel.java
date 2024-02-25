package com.example.postearevised.Models;

import com.example.postearevised.Controllers.LoginRegisterForgotPassController;
import com.example.postearevised.Miscellaneous.Enums.StartPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Prompt.*;
import static com.example.postearevised.Miscellaneous.Reference.*;

public class RegisterModel {
    private LoginRegisterForgotPassController loginRegisterForgotPassController;

    public void setLoginRegisterController(LoginRegisterForgotPassController loginRegisterForgotPassController) {
        this.loginRegisterForgotPassController = loginRegisterForgotPassController;
    }

    /**
     * Register
     */

    public void togglePasswordField1() {
        loginRegisterForgotPassController.registerShowNewPassword = !loginRegisterForgotPassController.registerShowNewPassword;

        loginRegisterForgotPassController.btnRegisterShowHidePassword1.setImage(loginRegisterForgotPassController.registerShowNewPassword ? loginRegisterForgotPassController.hideImage : loginRegisterForgotPassController.showImage);
        loginRegisterForgotPassController.textFieldNewPassword.setVisible(!loginRegisterForgotPassController.registerShowNewPassword);
        loginRegisterForgotPassController.textFieldShowNewPassword.setVisible(loginRegisterForgotPassController.registerShowNewPassword);

        if (loginRegisterForgotPassController.registerShowNewPassword) {
            loginRegisterForgotPassController.textFieldShowNewPassword.setText(loginRegisterForgotPassController.textFieldNewPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldNewPassword.setText(loginRegisterForgotPassController.textFieldShowNewPassword.getText());
        }

        loginRegisterForgotPassController.btnRegister1.requestFocus();
    }

    public void togglePasswordField2() {
        loginRegisterForgotPassController.registerShowConfirmNewPassword = !loginRegisterForgotPassController.registerShowConfirmNewPassword;

        loginRegisterForgotPassController.btnRegisterShowHidePassword2.setImage(loginRegisterForgotPassController.registerShowConfirmNewPassword ? loginRegisterForgotPassController.hideImage : loginRegisterForgotPassController.showImage);
        loginRegisterForgotPassController.textFieldConfirmNewPassword.setVisible(!loginRegisterForgotPassController.registerShowConfirmNewPassword);
        loginRegisterForgotPassController.textFieldShowConfirmNewPassword.setVisible(loginRegisterForgotPassController.registerShowConfirmNewPassword);

        if (loginRegisterForgotPassController.registerShowConfirmNewPassword) {
            loginRegisterForgotPassController.textFieldShowConfirmNewPassword.setText(loginRegisterForgotPassController.textFieldConfirmNewPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldConfirmNewPassword.setText(loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText());
        }

        loginRegisterForgotPassController.btnRegister1.requestFocus();
    }

    public void typing() {
        if (loginRegisterForgotPassController.registerSubmittedOnce) {
            setAttributes();
            setVisibilities(isValidEmail(), passwordMatched());
        }
    }

    public void setAttributes() {
        registerUsername = loginRegisterForgotPassController.textFieldUsername.getText();
        registerEmail = loginRegisterForgotPassController.textFieldEmail.getText();
        registerNewPassword = loginRegisterForgotPassController.registerShowNewPassword ? loginRegisterForgotPassController.textFieldShowNewPassword.getText() : loginRegisterForgotPassController.textFieldNewPassword.getText();
        registerConfirmNewPassword = loginRegisterForgotPassController.registerShowConfirmNewPassword ? loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText() : loginRegisterForgotPassController.textFieldConfirmNewPassword.getText();
    }

    public void registerAction() throws IOException {
        loginRegisterForgotPassController.registerSubmittedOnce = true;
        checkTextFields();
    }

    private void checkTextFields() throws IOException {
        setAttributes();

        boolean allFieldsNotEmpty = notEmptyTextFields();
        boolean validEmail = isValidEmail();
        boolean passwordsMatch = passwordMatched();

        setVisibilities(validEmail, passwordsMatch);

        if (allFieldsNotEmpty && validEmail && passwordsMatch) {
            if (openTAC()) {
                promptRegisteredSuccess();
            }
        }
    }

    private void setVisibilities(boolean validEmail, boolean passwordsMatch) {
        loginRegisterForgotPassController.labelUsername.setVisible(registerUsername.isBlank());
        loginRegisterForgotPassController.labelEmail.setVisible(registerEmail.isBlank());
        loginRegisterForgotPassController.labelPassword.setVisible(registerNewPassword.isBlank());
        loginRegisterForgotPassController.labelConfirmPassword.setVisible(registerConfirmNewPassword.isBlank());
        loginRegisterForgotPassController.labelInvalidEmail.setVisible(!registerEmail.isBlank() && !validEmail);
        loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(!registerNewPassword.isBlank() && !registerConfirmNewPassword.isBlank() && !passwordsMatch);
    }

    private void promptRegisteredSuccess() throws IOException {
        setAccountCreatedConfirmation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ExitConfirmation.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelUsername.getScene().getWindow());

        newStage.setTitle(ExitConfirmation.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();

        goToLogin();
    }

    private boolean openTAC() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(TermsAndCondition.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelUsername.getScene().getWindow());

        newStage.setTitle(TermsAndCondition.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }

    private boolean isValidEmail() {
        return registerEmail.matches(REGEX_EMAIL);
    }

    private boolean notEmptyTextFields() {
        return !registerUsername.isBlank() && !registerEmail.isBlank() && !registerNewPassword.isBlank() && !registerConfirmNewPassword.isBlank();
    }

    private boolean exitAreFieldsEmpty() {
        return !registerUsername.isBlank() || !registerEmail.isBlank() || !registerNewPassword.isBlank() || !registerConfirmNewPassword.isBlank();
    }

    private boolean passwordMatched() {
        return registerNewPassword.equals(registerConfirmNewPassword);
    }

    public void close() throws IOException {
        setAttributes();
        if (exitAreFieldsEmpty()) {
            if (openPromptConfirmGoBack()) {
                goToLogin();
            }
        } else {
            goToLogin();
        }
    }

    private boolean openPromptConfirmGoBack() throws IOException {
        setGoBackConfirmation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ExitConfirmation.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelUsername.getScene().getWindow());

        newStage.setTitle(ExitConfirmation.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }

    private void goToLogin() {
        loginRegisterForgotPassController.switchPane(StartPane.Login.getPaneNumber());
    }
}
