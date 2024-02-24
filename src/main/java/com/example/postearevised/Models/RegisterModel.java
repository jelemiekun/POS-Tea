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
import static com.example.postearevised.Miscellaneous.Prompt.isConfirmed;
import static com.example.postearevised.Miscellaneous.Reference.REGEX_EMAIL;

public class RegisterModel {
    private LoginRegisterForgotPassController loginRegisterForgotPassController;

    public void setLoginRegisterController(LoginRegisterForgotPassController loginRegisterForgotPassController) {
        this.loginRegisterForgotPassController = loginRegisterForgotPassController;
    }

    /**
     * Register
     */
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public void setAttributes() {
        username = loginRegisterForgotPassController.textFieldUsername.getText();
        email = loginRegisterForgotPassController.textFieldEmail.getText();
        password = loginRegisterForgotPassController.textFieldPassword1.getText();
        confirmPassword = loginRegisterForgotPassController.textFieldConfirmPassword.getText();
    }

    public void registerAction() throws IOException {
        checkTextFields();
    }

    private void checkTextFields() throws IOException {
        setAttributes();

        boolean allFieldsNotEmpty = notEmptyTextFields();
        boolean validEmail = isValidEmail();
        boolean passwordsMatch = passwordMatched();

        loginRegisterForgotPassController.labelUsername.setVisible(username.isBlank());
        loginRegisterForgotPassController.labelEmail.setVisible(email.isBlank());
        loginRegisterForgotPassController.labelPassword.setVisible(password.isBlank());
        loginRegisterForgotPassController.labelConfirmPassword.setVisible(confirmPassword.isBlank());
        loginRegisterForgotPassController.labelInvalidEmail.setVisible(!email.isBlank() && !validEmail);
        loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(!passwordsMatch);

        if (allFieldsNotEmpty && validEmail && passwordsMatch) {
            if (openTAC()) {
                promptRegisteredSuccess();
            }
        }
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
        return email.matches(REGEX_EMAIL);
    }

    private boolean notEmptyTextFields() {
        return !username.isBlank() && !email.isBlank() && !password.isBlank() && !confirmPassword.isBlank();
    }

    private boolean exitAreFieldsEmpty() {
        return !username.isBlank() || !email.isBlank() || !password.isBlank() || !confirmPassword.isBlank();
    }

    private boolean passwordMatched() {
        return password.equals(confirmPassword);
    }

    public void close() throws IOException {
        setAttributes();
        if (exitAreFieldsEmpty()) {
            if (openPrompt()) {
                goToLogin();
            }
        } else {
            goToLogin();
        }
    }

    private boolean openPrompt() throws IOException {
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
