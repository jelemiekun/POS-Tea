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
        if (loginRegisterForgotPassController.forgotPasswordStarted) {
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
        loginRegisterForgotPassController.forgotPasswordStarted = !loginRegisterForgotPassController.textFieldForgotPass1.getText().isBlank();
    }

    /**
     * Pane 1
     */

    public void checkPane1Input() {
        hasStared();

        String account = loginRegisterForgotPassController.textFieldForgotPass1.getText();

        if (account.isBlank()) {
            loginRegisterForgotPassController.labelForgotPassInvalidAccount.setVisible(true);
            loginRegisterForgotPassController.labelForgotPassInvalidAccount.setText("*Account cannot be empty!");
        } else if (!account.matches(REGEX_EMAIL) && !account.matches(REGEX_PHONE_NUMBER)) {
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
        randomOTP = String.valueOf(randomNumber);
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
        } else if (!inputOTP.equals(randomOTP)) {
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
        String newPassword = loginRegisterForgotPassController.textFieldForgotPass31.getText();
        String confirmNewPassword = loginRegisterForgotPassController.textFieldForgotPass32.getText();

        boolean resetSuccess = !newPassword.isBlank() && !confirmNewPassword.isBlank() && newPassword.equals(confirmNewPassword);
        boolean arePasswordsMatch = !newPassword.isBlank() && !confirmNewPassword.isBlank() && !newPassword.equals(confirmNewPassword);

        loginRegisterForgotPassController.labelNewPassword.setVisible(newPassword.isBlank());
        loginRegisterForgotPassController.labelConfirmNewPassword.setVisible(confirmNewPassword.isBlank());
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
}
