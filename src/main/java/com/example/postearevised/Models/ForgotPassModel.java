package com.example.postearevised.Models;

import com.example.postearevised.Controllers.LoginRegisterForgotPassController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.ExitConfirmation;
import static com.example.postearevised.Miscellaneous.Enums.StartPane.*;
import static com.example.postearevised.Miscellaneous.Prompt.*;
import static com.example.postearevised.Miscellaneous.Reference.*;

public class ForgotPassModel {
    private LoginRegisterForgotPassController loginRegisterForgotPassController;

    public void setLoginRegisterController(LoginRegisterForgotPassController loginRegisterForgotPassController) {
        this.loginRegisterForgotPassController = loginRegisterForgotPassController;
    }

    public void checkIfProgressStartedBeforeGoBack() throws IOException {
        hasStared();

        boolean confirmGoBack = true;
        if (loginRegisterForgotPassController.forgotPassStarted) {
            confirmGoBack = openPromptConfirmGoBack();
        }

        if (confirmGoBack)
            loginRegisterForgotPassController.switchPane(Login.getPaneNumber());
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

    private void hasStared() {
        loginRegisterForgotPassController.forgotPassStarted = !loginRegisterForgotPassController.textFieldForgotPass1.getText().isBlank();
    }

    /**
     * Pane 1
     */

    public void checkPane1Input() {
        hasStared();

        setAttributes(ForgotPassword1.getPaneNumber());

        if (forgotPassAccount.isBlank()) {
            loginRegisterForgotPassController.labelForgotPassInvalidAccount.setVisible(true);
            loginRegisterForgotPassController.labelForgotPassInvalidAccount.setText("*Account cannot be empty!");
        } else if (!forgotPassAccount.matches(REGEX_EMAIL) && !forgotPassAccount.matches(REGEX_PHONE_NUMBER)) {
            loginRegisterForgotPassController.labelForgotPassInvalidAccount.setVisible(true);
            loginRegisterForgotPassController.labelForgotPassInvalidAccount.setText("*Invalid email address or phone number!");
        } else {
            generateOTP();
            proceedPane2();
        }
    }

    private void generateOTP() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        forgotPassOTP = String.valueOf(randomNumber);
        System.out.println(randomNumber);
    }

    private void proceedPane2() {
        loginRegisterForgotPassController.forgotPasswordSwitchPane(ForgotPassword2.getPaneNumber());
    }

    /**
     * Pane 2
     */

    public void checkPane2Input() {
        String inputOTP = loginRegisterForgotPassController.textFieldForgotPass2.getText();

        if (inputOTP.isBlank()) {
            loginRegisterForgotPassController.labelIncorrectOTP.setVisible(true);
            loginRegisterForgotPassController.labelIncorrectOTP.setText("*OTP cannot be empty!");
        } else if (!inputOTP.equals(forgotPassOTP)) {
            loginRegisterForgotPassController.labelIncorrectOTP.setVisible(true);
            loginRegisterForgotPassController.labelIncorrectOTP.setText("*OTP does not match!");
        } else {
            proceedPane3();
        }
    }

    private void proceedPane3() {
        loginRegisterForgotPassController.forgotPasswordSwitchPane(ForgotPassword3.getPaneNumber());
    }

    /**
     * Pane 3
     */

    public void checkPane3Input() throws IOException {
        setAttributes(ForgotPassword3.getPaneNumber());

        boolean resetSuccess = !forgotPassNewPassword.isBlank() && !forgotPassConfirmNewPassword.isBlank() && forgotPassNewPassword.equals(forgotPassConfirmNewPassword);
        boolean arePasswordsMatch = !forgotPassNewPassword.isBlank() && !forgotPassConfirmNewPassword.isBlank() && !forgotPassNewPassword.equals(forgotPassConfirmNewPassword);

        loginRegisterForgotPassController.labelNewPassword.setVisible(forgotPassNewPassword.isBlank());
        loginRegisterForgotPassController.labelConfirmNewPassword.setVisible(forgotPassConfirmNewPassword.isBlank());
        loginRegisterForgotPassController.labelNewPasswordNotMatch.setVisible(arePasswordsMatch);

        if (resetSuccess)
            openPromptGoBackToLogin();
    }

    private void openPromptGoBackToLogin() throws IOException {
        setResetPasswordSuccessfully();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ExitConfirmation.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelUsername.getScene().getWindow());

        newStage.setTitle(ExitConfirmation.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();

        loginRegisterForgotPassController.switchPane(Login.getPaneNumber());
    }

    /**
     * Main
     */

    public void setAttributes(int paneNumber) {
        switch (paneNumber) {
            case 4:
                forgotPassAccount = loginRegisterForgotPassController.textFieldForgotPass1.getText();
                break;
            case 5:
                break;
            case 6:
                forgotPassNewPassword = loginRegisterForgotPassController.forgotPassShowNewPassword ? loginRegisterForgotPassController.textFieldShowForgotPass31.getText() : loginRegisterForgotPassController.textFieldForgotPass31.getText();
                forgotPassConfirmNewPassword = loginRegisterForgotPassController.forgotPassShowConfirmNewPassword ? loginRegisterForgotPassController.textFieldShowForgotPass32.getText() : loginRegisterForgotPassController.textFieldForgotPass32.getText();
                break;
        }
    }

    public void togglePasswordField1() {
        loginRegisterForgotPassController.forgotPassShowNewPassword = !loginRegisterForgotPassController.forgotPassShowNewPassword;

        loginRegisterForgotPassController.btnForgotPassShowHidePassword1.setImage(loginRegisterForgotPassController.forgotPassShowNewPassword ? loginRegisterForgotPassController.hideImage : loginRegisterForgotPassController.showImage);
        loginRegisterForgotPassController.textFieldForgotPass31.setVisible(!loginRegisterForgotPassController.forgotPassShowNewPassword);
        loginRegisterForgotPassController.textFieldShowForgotPass31.setVisible(loginRegisterForgotPassController.forgotPassShowNewPassword);

        if (loginRegisterForgotPassController.forgotPassShowNewPassword) {
            loginRegisterForgotPassController.textFieldShowForgotPass31.setText(loginRegisterForgotPassController.textFieldForgotPass31.getText());
        } else {
            loginRegisterForgotPassController.textFieldForgotPass31.setText(loginRegisterForgotPassController.textFieldShowForgotPass31.getText());
        }

        loginRegisterForgotPassController.btnForgotPass3Proceed.requestFocus();
    }

    public void togglePasswordField2() {
        System.out.println(loginRegisterForgotPassController.forgotPassShowConfirmNewPassword);
        loginRegisterForgotPassController.forgotPassShowConfirmNewPassword = !loginRegisterForgotPassController.forgotPassShowConfirmNewPassword;
        System.out.println(loginRegisterForgotPassController.forgotPassShowConfirmNewPassword);

        loginRegisterForgotPassController.btnForgotPassShowHidePassword2.setImage(loginRegisterForgotPassController.forgotPassShowConfirmNewPassword ? loginRegisterForgotPassController.hideImage : loginRegisterForgotPassController.showImage);
        loginRegisterForgotPassController.textFieldForgotPass32.setVisible(!loginRegisterForgotPassController.forgotPassShowConfirmNewPassword);
        loginRegisterForgotPassController.textFieldShowForgotPass32.setVisible(loginRegisterForgotPassController.forgotPassShowConfirmNewPassword);

        if (loginRegisterForgotPassController.forgotPassShowConfirmNewPassword) {
            loginRegisterForgotPassController.textFieldShowForgotPass32.setText(loginRegisterForgotPassController.textFieldForgotPass32.getText());
        } else {
            loginRegisterForgotPassController.textFieldForgotPass32.setText(loginRegisterForgotPassController.textFieldShowForgotPass32.getText());
        }

        loginRegisterForgotPassController.btnForgotPass3Proceed.requestFocus();
    }
}
