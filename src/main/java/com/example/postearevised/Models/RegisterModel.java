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
            setVisibilities(isValidEmailOrPhoneNumber(), passwordMatched());
        }
    }

    public void setAttributes() {
        registerName = loginRegisterForgotPassController.textFieldName.getText().trim();
        registerEmailOrPhoneNumber = loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.getText().trim();
        registerNewPassword = loginRegisterForgotPassController.registerShowNewPassword ? loginRegisterForgotPassController.textFieldShowNewPassword.getText().trim() : loginRegisterForgotPassController.textFieldNewPassword.getText().trim();
        registerConfirmNewPassword = loginRegisterForgotPassController.registerShowConfirmNewPassword ? loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText().trim() : loginRegisterForgotPassController.textFieldConfirmNewPassword.getText().trim();
        System.out.println(registerName);
    }

    private void setVisibilities(boolean validEmailOrPhoneNumber, boolean passwordsMatch) {
        loginRegisterForgotPassController.labelName.setVisible(registerName.isBlank());
        loginRegisterForgotPassController.labelName.setText("*please fill up this form");
        loginRegisterForgotPassController.labelEmail.setVisible(registerEmailOrPhoneNumber.isBlank());
        loginRegisterForgotPassController.labelPassword.setVisible(registerNewPassword.isBlank());
        loginRegisterForgotPassController.labelPassword.setText("*please fill up this form");
        loginRegisterForgotPassController.labelConfirmPassword.setVisible(registerConfirmNewPassword.isBlank());
        loginRegisterForgotPassController.labelInvalidEmailOrPhoneNumber.setVisible(!registerEmailOrPhoneNumber.isBlank() && !validEmailOrPhoneNumber);
        loginRegisterForgotPassController.labelInvalidEmailOrPhoneNumber.setText(isAPhoneNumber() ? "*invalid number" : "*invalid email");
        loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(!registerNewPassword.isBlank() && !registerConfirmNewPassword.isBlank() && !passwordsMatch);

        if (!registerName.isBlank()) {
            if (registerName.length() < 3 || registerName.length() > 20) {
                loginRegisterForgotPassController.labelName.setText("*should be 3-20 characters long");
                loginRegisterForgotPassController.labelName.setVisible(true);
            } else {
                if (!registerName.matches(REGEX_NAME)) {
                    loginRegisterForgotPassController.labelName.setText("*must contain only English letters");
                    loginRegisterForgotPassController.labelName.setVisible(true);
                } else {
                    if (!nameFieldNotHaveSpaces()) {
                        loginRegisterForgotPassController.labelName.setText("*spaces at start/end not allowed");
                        loginRegisterForgotPassController.labelName.setVisible(true);
                    } else {
                        loginRegisterForgotPassController.labelName.setVisible(false);
                    }
                }
            }
        }

        if (!registerNewPassword.isBlank()) {
            if (isPasswordContainsName()) {
                if (!registerName.isBlank()) {
                    loginRegisterForgotPassController.labelPassword.setText("*password can't include name");
                    loginRegisterForgotPassController.labelPassword.setVisible(true);
                } else {
                    loginRegisterForgotPassController.labelPassword.setVisible(false);
                }
            } else {
                loginRegisterForgotPassController.labelPassword.setVisible(false);
            }
        }
    }

    /**
     * Password Indicator
     */

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

    /**
     * Check Validity
     */


    private void checkTextFields() throws IOException {
        setAttributes();

        boolean allFieldsNotEmpty = notEmptyTextFields();
        boolean validEmailOrPhoneNumber = isValidEmailOrPhoneNumber();
        boolean passwordsMatch = passwordMatched();
        boolean validName = isValidName();
        boolean nameNotInPassword = !isPasswordContainsName();


        setVisibilities(validEmailOrPhoneNumber, passwordsMatch);

        if (allFieldsNotEmpty && validEmailOrPhoneNumber && passwordsMatch && validName && nameNotInPassword) {
            readTAC();
        }
    }

    public void readTAC() throws IOException {
        if (openTAC()) {
            if (loginRegisterForgotPassController.checkConnectivity()) {
                openPromptRegisteredSuccess();
                goToLogin();
            }
        }
    }

    private boolean isValidEmailOrPhoneNumber() {
        return registerEmailOrPhoneNumber.matches(REGEX_EMAIL) || registerEmailOrPhoneNumber.matches(REGEX_PHONE_NUMBER);
    }

    private boolean isAPhoneNumber() {
        return registerEmailOrPhoneNumber.matches(REGEX_FIRST_FIVE_ARE_NUMBERS);
    }

    private boolean isPasswordContainsName() {
        return registerNewPassword.contains(registerName);
    }

    private boolean isValidName() {
        return registerName.length() >= 3 && registerName.length() <= 20 && registerName.matches(REGEX_NAME) && nameFieldNotHaveSpaces();
    }

    private boolean nameFieldNotHaveSpaces() {
        return registerName.length() == loginRegisterForgotPassController.textFieldName.getText().length();
    }

    private boolean notEmptyTextFields() {
        return !registerName.isBlank() && !registerEmailOrPhoneNumber.isBlank() && !registerNewPassword.isBlank() && !registerConfirmNewPassword.isBlank();
    }

    private boolean exitAreFieldsEmpty() {
        return !registerName.isBlank() || !registerEmailOrPhoneNumber.isBlank() || !registerNewPassword.isBlank() || !registerConfirmNewPassword.isBlank();
    }

    private boolean passwordMatched() {
        return registerNewPassword.equals(registerConfirmNewPassword);
    }

    /**
     * Show or Hide Password
     */

    public void toggleNewPasswordField() {
        loginRegisterForgotPassController.registerShowNewPassword = !loginRegisterForgotPassController.registerShowNewPassword;

        loginRegisterForgotPassController.btnRegisterShowHidePassword1.setImage(loginRegisterForgotPassController.registerShowNewPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
        loginRegisterForgotPassController.textFieldNewPassword.setVisible(!loginRegisterForgotPassController.registerShowNewPassword);
        loginRegisterForgotPassController.textFieldShowNewPassword.setVisible(loginRegisterForgotPassController.registerShowNewPassword);

        if (loginRegisterForgotPassController.registerShowNewPassword) {
            loginRegisterForgotPassController.textFieldShowNewPassword.setText(loginRegisterForgotPassController.textFieldNewPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldNewPassword.setText(loginRegisterForgotPassController.textFieldShowNewPassword.getText());
        }

        loginRegisterForgotPassController.btnRegisterOnRegisterPane.requestFocus();
    }

    public void toggleConfirmNewPasswordField() {
        loginRegisterForgotPassController.registerShowConfirmNewPassword = !loginRegisterForgotPassController.registerShowConfirmNewPassword;

        loginRegisterForgotPassController.btnRegisterShowHidePassword2.setImage(loginRegisterForgotPassController.registerShowConfirmNewPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
        loginRegisterForgotPassController.textFieldConfirmNewPassword.setVisible(!loginRegisterForgotPassController.registerShowConfirmNewPassword);
        loginRegisterForgotPassController.textFieldShowConfirmNewPassword.setVisible(loginRegisterForgotPassController.registerShowConfirmNewPassword);

        if (loginRegisterForgotPassController.registerShowConfirmNewPassword) {
            loginRegisterForgotPassController.textFieldShowConfirmNewPassword.setText(loginRegisterForgotPassController.textFieldConfirmNewPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldConfirmNewPassword.setText(loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText());
        }

        loginRegisterForgotPassController.btnRegisterOnRegisterPane.requestFocus();
    }

    /**
     * Click Field Icons
     */

    public void selectName() {
        loginRegisterForgotPassController.textFieldName.requestFocus();
        loginRegisterForgotPassController.textFieldName.positionCaret(loginRegisterForgotPassController.textFieldName.getText().length());
    }

    public void selectEmail() {
        loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.requestFocus();
        loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.positionCaret(loginRegisterForgotPassController.textFieldName.getText().length());
    }

    public void selectNewPassword() {
        if (loginRegisterForgotPassController.registerShowNewPassword) {
            loginRegisterForgotPassController.textFieldShowNewPassword.requestFocus();
            loginRegisterForgotPassController.textFieldShowNewPassword.positionCaret(loginRegisterForgotPassController.textFieldShowNewPassword.getText().length());
        } else {
            loginRegisterForgotPassController.textFieldNewPassword.requestFocus();
            loginRegisterForgotPassController.textFieldNewPassword.positionCaret(loginRegisterForgotPassController.textFieldNewPassword.getText().length());
        }
    }

    public void selectConfirmNewPassword() {
        if (loginRegisterForgotPassController.registerShowConfirmNewPassword) {
            loginRegisterForgotPassController.textFieldShowConfirmNewPassword.requestFocus();
            loginRegisterForgotPassController.textFieldShowConfirmNewPassword.positionCaret(loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText().length());
        } else {
            loginRegisterForgotPassController.textFieldConfirmNewPassword.requestFocus();
            loginRegisterForgotPassController.textFieldConfirmNewPassword.positionCaret(loginRegisterForgotPassController.textFieldConfirmNewPassword.getText().length());
        }
    }

    /**
     * Redirecting to another scene
     * Pop-up windows
     */

    private boolean openPromptConfirmGoBack() throws IOException {
        setGoBackConfirmation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ExitConfirmation.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelName.getScene().getWindow());

        newStage.setTitle(ExitConfirmation.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }

    private boolean openTAC() throws IOException {
        loginRegisterForgotPassController.toggleRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(TermsAndCondition.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelName.getScene().getWindow());

        newStage.setTitle(TermsAndCondition.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();

        loginRegisterForgotPassController.toggleRectangleModal();

        return isConfirmed;
    }

    private void openPromptRegisteredSuccess() throws IOException {
        setAccountCreatedConfirmation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ExitConfirmation.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelName.getScene().getWindow());

        newStage.setTitle(ExitConfirmation.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
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

    public void goToLogin() {
        loginRegisterForgotPassController.switchPane(StartPane.Login.getPaneNumber());
    }
}
