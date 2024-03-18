package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.LoginRegisterForgotPassController;
import com.example.postearevised.Miscellaneous.Enums.StartPane;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.PasswordColors.*;
import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

public class RegisterModel {
    private LoginRegisterForgotPassController loginRegisterForgotPassController;

    public void setLoginRegisterController(LoginRegisterForgotPassController loginRegisterForgotPassController) {
        this.loginRegisterForgotPassController = loginRegisterForgotPassController;
    }

    /**
     * Register
     */


    public void iconsClicked() {
        loginRegisterForgotPassController.iconsClicked = true;
        disableIconsClicked();
    }

    public void disableIconsClicked() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginRegisterForgotPassController.iconsClicked = false;
            }
        });
    }

    public void registerAction() throws IOException {
        loginRegisterForgotPassController.registerSubmittedOnce = true;
        checkTextFields();
    }

    public void typing() {
        hideForgotPassVisibility();
        if (loginRegisterForgotPassController.registerSubmittedOnce) {
            setAttributes();
            setVisibilities(isValidEmailOrPhoneNumber(), passwordMatched());
        }
    }

    private void hideForgotPassVisibility() {
        loginRegisterForgotPassController.labelRegisterPasswordLimitReached.setVisible(false);
    }

    public void setAttributes() {
        registerName = loginRegisterForgotPassController.textFieldName.getText().trim();
        registerEmailOrPhoneNumber = loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.getText().trim();
        registerNewPassword = loginRegisterForgotPassController.registerShowNewPassword ? loginRegisterForgotPassController.textFieldShowNewPassword.getText().trim() : loginRegisterForgotPassController.textFieldNewPassword.getText().trim();
        registerConfirmNewPassword = loginRegisterForgotPassController.registerShowConfirmNewPassword ? loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText().trim() : loginRegisterForgotPassController.textFieldConfirmNewPassword.getText().trim();
        System.out.println(registerName);
    }

    private void setVisibilities(boolean validEmailOrPhoneNumber, boolean passwordsMatch) {
        loginRegisterForgotPassController.labelName1.setVisible(registerName.length() > NAME_LIMIT);
        loginRegisterForgotPassController.labelName.setVisible(registerName.isBlank());
        loginRegisterForgotPassController.labelName.setText("*please fill up this form");
        loginRegisterForgotPassController.labelEmail.setVisible(registerEmailOrPhoneNumber.isBlank());
        loginRegisterForgotPassController.labelPassword.setVisible(registerNewPassword.isBlank());
        loginRegisterForgotPassController.labelPassword.setText("*please fill up this form");
        loginRegisterForgotPassController.labelConfirmPassword.setVisible(registerConfirmNewPassword.isBlank());
        loginRegisterForgotPassController.labelInvalidEmailOrPhoneNumber.setVisible(!registerEmailOrPhoneNumber.isBlank() && !validEmailOrPhoneNumber);
        loginRegisterForgotPassController.labelInvalidEmailOrPhoneNumber.setText(isAPhoneNumber() ? "*invalid number" : "*invalid email");

        boolean showPasswordNotMatch = true;
        boolean newPasswordAndConfirmNewPasswordNotMatched = isNewPasswordAndConfirmNewPasswordNotMatched(passwordsMatch);

        if (loginRegisterForgotPassController.registerIsWeakPassword) {
            loginRegisterForgotPassController.labelPasswordNotMatch.setText("*Weak password. Require mixed characters.");
            loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(true);
            showPasswordNotMatch = false;
        } else {
            loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(false);
        }

        if (showPasswordNotMatch) {
            if (newPasswordAndConfirmNewPasswordNotMatched) {
                loginRegisterForgotPassController.labelPasswordNotMatch.setText("*Password does not match");
                loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(true);
            } else {
                loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(false);
            }
        }

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

    public void checkAccountInRegisterIfPhoneNumber() {
        String account = loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.getText();

        if (account.matches(REGEX_FIRST_SIX_ARE_NUMBERS)) {
            enableLimitInput();
        } else {
            disableLimitInput();
        }
    }

    private void enableLimitInput() {
        loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.textProperty().addListener(loginRegisterForgotPassController.RegisterAccountInputLimitListener);
    }

    public void disableLimitInput() {
        loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.textProperty().removeListener(loginRegisterForgotPassController.RegisterAccountInputLimitListener);
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
        loginRegisterForgotPassController.registerIsWeakPassword = true;
        loginRegisterForgotPassController.registerRectangle1.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(WHITE_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setVisible(false);
    }

    private void weakPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = true;
        loginRegisterForgotPassController.registerRectangle1.setFill(WEAK_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(WEAK_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(WEAK_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void fairPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = false;
        loginRegisterForgotPassController.registerRectangle1.setFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(FAIR_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void goodPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = false;
        loginRegisterForgotPassController.registerRectangle1.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(GOOD_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void strongPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = false;
        loginRegisterForgotPassController.registerRectangle1.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(STRONG_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(STRONG_ENUM.getColor());
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
        boolean notWeakPassword = !loginRegisterForgotPassController.registerIsWeakPassword;


        setVisibilities(validEmailOrPhoneNumber, passwordsMatch);

        if (allFieldsNotEmpty && validEmailOrPhoneNumber && passwordsMatch && validName && nameNotInPassword && notWeakPassword) {
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

    private boolean isNewPasswordAndConfirmNewPasswordNotMatched(boolean passwordsMatch) {
        return !registerNewPassword.isBlank() && !registerConfirmNewPassword.isBlank() && !passwordsMatch;
    }

    private boolean isValidEmailOrPhoneNumber() {
        return registerEmailOrPhoneNumber.matches(REGEX_EMAIL) || registerEmailOrPhoneNumber.matches(REGEX_PHONE_NUMBER);
    }

    private boolean isAPhoneNumber() {
        return registerEmailOrPhoneNumber.matches(REGEX_FIRST_SIX_ARE_NUMBERS);
    }

    private boolean isPasswordContainsName() {
        return registerNewPassword.contains(registerName);
    }

    private boolean isValidName() {
        return registerName.length() >= 3 && registerName.length() <= NAME_LIMIT && registerName.matches(REGEX_NAME) && nameFieldNotHaveSpaces();
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
        loginRegisterForgotPassController.toggleRectangleModal();

        setGoBackConfirmation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelName.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();

        loginRegisterForgotPassController.toggleRectangleModal();

        return isConfirmed;
    }

    private boolean openTAC() throws IOException {
        loginRegisterForgotPassController.toggleRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(TERMS_AND_CONDITION_ENUM.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelName.getScene().getWindow());

        newStage.setTitle(TERMS_AND_CONDITION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();

        loginRegisterForgotPassController.toggleRectangleModal();

        return isConfirmed;
    }

    private void openPromptRegisteredSuccess() throws IOException {
        loginRegisterForgotPassController.toggleRectangleModal();

        setAccountCreatedConfirmation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelName.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();

        loginRegisterForgotPassController.toggleRectangleModal();
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

    public void goToLogin() {
        loginRegisterForgotPassController.switchPane(StartPane.Login.getPaneNumber());
    }
}
