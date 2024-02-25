package com.example.postearevised.Models;

import com.example.postearevised.Controllers.LoginRegisterForgotPassController;
import com.example.postearevised.Miscellaneous.Enums.StartPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.PasswordColors.*;
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
    public void registerAction() throws IOException {
        loginRegisterForgotPassController.registerSubmittedOnce = true;
        checkTextFields();
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

    private void setVisibilities(boolean validEmail, boolean passwordsMatch) {
        loginRegisterForgotPassController.labelUsername.setVisible(registerUsername.isBlank());
        loginRegisterForgotPassController.labelUsername.setText("*please fill up this form");
        loginRegisterForgotPassController.labelEmail.setVisible(registerEmail.isBlank());
        loginRegisterForgotPassController.labelPassword.setVisible(registerNewPassword.isBlank());
        loginRegisterForgotPassController.labelPassword.setText("*please fill up this form");
        loginRegisterForgotPassController.labelConfirmPassword.setVisible(registerConfirmNewPassword.isBlank());
        loginRegisterForgotPassController.labelInvalidEmail.setVisible(!registerEmail.isBlank() && !validEmail);
        loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(!registerNewPassword.isBlank() && !registerConfirmNewPassword.isBlank() && !passwordsMatch);

        if (!registerUsername.isBlank()) {
            if (registerUsername.length() < 3 || registerUsername.length() > 20) {
                loginRegisterForgotPassController.labelUsername.setText("*should be 3-20 characters long");
                loginRegisterForgotPassController.labelUsername.setVisible(true);
            } else {
                loginRegisterForgotPassController.labelUsername.setVisible(false);
            }
        }

        if (!registerNewPassword.isBlank()) {
            if (isPasswordContainsUsername()) {
                loginRegisterForgotPassController.labelPassword.setText("*password can't include username");
                loginRegisterForgotPassController.labelPassword.setVisible(true);
            } else {
                loginRegisterForgotPassController.labelPassword.setVisible(false);
            }
        }
    }

    public void passwordIndicator() {
        setAttributes();

        if (registerNewPassword.isBlank()) {
            emptyPassword();

        } else {
            int result = checkWeak();
            if (result == 1) {
                weakPassword();
            } else {

                result = checkFair();
                if (result == 2) {
                    fairPassword();
                } else if (result == -1) {
                    weakPassword();
                } else {

                    result = checkGood();
                    if (result == 1) {
                        weakPassword();
                    } else if (result == 2) {
                        fairPassword();
                    } else if (result == 3) {
                        goodPassword();
                    } else {
                        strongPassword();
                    }
                }
            }

        }
    }

    private int checkWeak() {
        return (registerNewPassword.length() < 8) ? 1 : -1;
    }

    private int checkFair() {
        if (registerNewPassword.length() < 8) return -1; // Password length is less than 8 characters

        int criteriaMet = 0;
        if (registerNewPassword.matches(".*[A-Z].*")) criteriaMet++;
        if (registerNewPassword.matches(".*\\d.*")) criteriaMet++;
        if (registerNewPassword.matches(".*[^A-Za-z0-9].*")) criteriaMet++;

        return (criteriaMet == 1) ? 2 : 3;
    }

    private int checkGood() {
        int criteriaMet = 0;
        if (registerNewPassword.length() >= 8) {
            criteriaMet++;
        }
        if (registerNewPassword.matches(".*[A-Z].*")) criteriaMet++;
        if (registerNewPassword.matches(".*\\d.*")) criteriaMet++;
        if (registerNewPassword.matches(".*[^A-Za-z0-9].*")) criteriaMet++;
        return criteriaMet;
    }

    public void emptyPassword() {
        loginRegisterForgotPassController.registerRectangle1.setFill(White.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(White.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(White.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(White.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(White.getText());
        loginRegisterForgotPassController.registerIndicator.setVisible(false);
    }

    private void weakPassword() {
        loginRegisterForgotPassController.registerRectangle1.setFill(Weak.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(White.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(White.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(White.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(Weak.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(Weak.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void fairPassword() {
        loginRegisterForgotPassController.registerRectangle1.setFill(Fair.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(Fair.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(White.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(White.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(Fair.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(Fair.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void goodPassword() {
        loginRegisterForgotPassController.registerRectangle1.setFill(Good.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(Good.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(Good.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(White.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(Good.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(Good.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void strongPassword() {
        loginRegisterForgotPassController.registerRectangle1.setFill(Strong.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(Strong.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(Strong.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(Strong.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(Strong.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(Strong.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }


    private void checkTextFields() throws IOException {
        setAttributes();

        boolean allFieldsNotEmpty = notEmptyTextFields();
        boolean validEmail = isValidEmail();
        boolean passwordsMatch = passwordMatched();
        boolean validUsername = isValidUsername();
        boolean usernameNotInPassword = !isPasswordContainsUsername();


        setVisibilities(validEmail, passwordsMatch);

        if (allFieldsNotEmpty && validEmail && passwordsMatch && validUsername && usernameNotInPassword) {
            loginRegisterForgotPassController.toggleRectangleModal();
            if (openTAC()) {
                openPromptRegisteredSuccess();
            }
            loginRegisterForgotPassController.toggleRectangleModal();
        }
    }

    private boolean isValidEmail() {
        return registerEmail.matches(REGEX_EMAIL);
    }

    private boolean isPasswordContainsUsername() {
        return registerNewPassword.contains(registerUsername);
    }

    private boolean isValidUsername() {
        return registerUsername.length() >= 3 && registerUsername.length() <= 20;
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

    public void togglePasswordField1() {
        loginRegisterForgotPassController.registerShowNewPassword = !loginRegisterForgotPassController.registerShowNewPassword;

        loginRegisterForgotPassController.btnRegisterShowHidePassword1.setImage(loginRegisterForgotPassController.registerShowNewPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
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

        loginRegisterForgotPassController.btnRegisterShowHidePassword2.setImage(loginRegisterForgotPassController.registerShowConfirmNewPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
        loginRegisterForgotPassController.textFieldConfirmNewPassword.setVisible(!loginRegisterForgotPassController.registerShowConfirmNewPassword);
        loginRegisterForgotPassController.textFieldShowConfirmNewPassword.setVisible(loginRegisterForgotPassController.registerShowConfirmNewPassword);

        if (loginRegisterForgotPassController.registerShowConfirmNewPassword) {
            loginRegisterForgotPassController.textFieldShowConfirmNewPassword.setText(loginRegisterForgotPassController.textFieldConfirmNewPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldConfirmNewPassword.setText(loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText());
        }

        loginRegisterForgotPassController.btnRegister1.requestFocus();
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

    private void openPromptRegisteredSuccess() throws IOException {
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

    public void close() throws IOException {
        setAttributes();
        if (exitAreFieldsEmpty()) {
            loginRegisterForgotPassController.toggleRectangleModal();
            if (openPromptConfirmGoBack()) {
                goToLogin();
            }
            loginRegisterForgotPassController.toggleRectangleModal();
        } else {
            goToLogin();
        }
    }

    private void goToLogin() {
        loginRegisterForgotPassController.switchPane(StartPane.Login.getPaneNumber());
    }
}
