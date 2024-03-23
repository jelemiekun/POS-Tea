package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.LoginRegisterForgotPassController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static com.example.postearevised.Miscellaneous.Enums.PasswordColorsEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.StartPane.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

public class ForgotPassModel {
    private LoginRegisterForgotPassController loginRegisterForgotPassController;

    public void setLoginRegisterController(LoginRegisterForgotPassController loginRegisterForgotPassController) {
        this.loginRegisterForgotPassController = loginRegisterForgotPassController;
    }

    /**
     * Set things
     */

    private void hasStared() {
        loginRegisterForgotPassController.forgotPassStarted = !loginRegisterForgotPassController.textFieldForgotPass1.getText().isBlank();
    }

    public void typing(int paneNumber) {
        switch (paneNumber) {
            case 4:
                if (loginRegisterForgotPassController.forgotPass1SubmittedOnce) {
                    setAttributes(ForgotPassword1.getPaneNumber());
                    setVisibilities(ForgotPassword1.getPaneNumber());
                    System.out.println(forgotPassAccount);
                }
                break;
            case 5:
                if (loginRegisterForgotPassController.forgotPass2SubmittedOnce) {
                    setAttributes(ForgotPassword2.getPaneNumber());
                    setVisibilities(ForgotPassword2.getPaneNumber());
                }
                break;
            case 6:
                hideForgotLabelPasswordLimitReached();
                if (loginRegisterForgotPassController.forgotPass3SubmittedOnce) {
                    setAttributes(ForgotPassword3.getPaneNumber());
                    setVisibilities(ForgotPassword3.getPaneNumber());
                }
                break;
        }
    }

    private void hideForgotLabelPasswordLimitReached() {
        loginRegisterForgotPassController.forgotLabelPasswordLimitReached.setVisible(false);
    }

    public void setAttributes(int paneNumber) {
        switch (paneNumber) {
            case 4:
                forgotPassAccount = loginRegisterForgotPassController.textFieldForgotPass1.getText();
                break;
            case 5:
                forgotPassOTP = String.valueOf(forgotPassGeneratedOTP);
                break;
            case 6:
                forgotPassNewPassword = loginRegisterForgotPassController.forgotPassShowNewPassword ? loginRegisterForgotPassController.textFieldShowForgotPass31.getText() : loginRegisterForgotPassController.textFieldForgotPass31.getText();
                forgotPassConfirmNewPassword = loginRegisterForgotPassController.forgotPassShowConfirmNewPassword ? loginRegisterForgotPassController.textFieldShowForgotPass32.getText() : loginRegisterForgotPassController.textFieldForgotPass32.getText();
                break;
        }
    }

    private void setVisibilities(int paneNumber) {
        switch (paneNumber) {
            case 4:
                if (forgotPassAccount.isBlank()) {
                    loginRegisterForgotPassController.labelForgotPassInvalidAccount.setVisible(true);
                    loginRegisterForgotPassController.labelForgotPassInvalidAccount.setText("*Account cannot be empty!");
                } else if (!forgotPassAccount.matches(REGEX_EMAIL) && !forgotPassAccount.matches(REGEX_PHONE_NUMBER)) {
                    loginRegisterForgotPassController.labelForgotPassInvalidAccount.setVisible(true);
                    loginRegisterForgotPassController.labelForgotPassInvalidAccount.setText("*Invalid email address or phone number!");
                }
                break;
            case 5:
                String inputOTP = loginRegisterForgotPassController.textFieldForgotPass2.getText();

                if (inputOTP.isBlank()) {
                    loginRegisterForgotPassController.labelIncorrectOTP.setVisible(true);
                    loginRegisterForgotPassController.labelIncorrectOTP.setText("*OTP cannot be empty!");
                } else if (!inputOTP.equals(forgotPassOTP)) {
                    loginRegisterForgotPassController.labelIncorrectOTP.setVisible(true);
                    loginRegisterForgotPassController.labelIncorrectOTP.setText("*OTP does not match!");
                }
                break;
            case 6:
                boolean passwordsMatch = arePasswordsMatch();
                boolean showPasswordNotMatch = true;
                
                loginRegisterForgotPassController.labelNewPassword.setVisible(forgotPassNewPassword.isBlank());
                loginRegisterForgotPassController.labelConfirmNewPassword.setVisible(forgotPassConfirmNewPassword.isBlank());

                if (loginRegisterForgotPassController.forgotIsWeakPassword) {
                    loginRegisterForgotPassController.labelNewPasswordNotMatch.setText("*Weak password. Require mixed characters.");
                    loginRegisterForgotPassController.labelNewPasswordNotMatch.setVisible(true);
                    showPasswordNotMatch = false;
                } else {
                    loginRegisterForgotPassController.labelNewPasswordNotMatch.setVisible(false);
                }

                if (showPasswordNotMatch) {
                    if (!passwordsMatch) {
                        loginRegisterForgotPassController.labelNewPasswordNotMatch.setText("*Password does not match.");
                        loginRegisterForgotPassController.labelNewPasswordNotMatch.setVisible(true);
                    } else {
                        loginRegisterForgotPassController.labelNewPasswordNotMatch.setVisible(false);
                    }
                }
                break;
        }
    }

    private boolean arePasswordsMatch() {
        return !forgotPassNewPassword.isBlank() && !forgotPassConfirmNewPassword.isBlank() && forgotPassNewPassword.equals(forgotPassConfirmNewPassword);
    }

    /**
     * Pane 1
     */

    public void checkAccountInForgotIfPhoneNumber() {
        String account = loginRegisterForgotPassController.textFieldForgotPass1.getText();

        if (account.matches(REGEX_FIRST_SIX_ARE_NUMBERS)) {
            enableLimitInput1();
        } else {
            disableLimitInput1();
        }
    }

    private void enableLimitInput1() {
        loginRegisterForgotPassController.textFieldForgotPass1.textProperty().addListener(loginRegisterForgotPassController.ForgotAccountInputLimitListener);
    }

    public void disableLimitInput1() {
        loginRegisterForgotPassController.textFieldForgotPass1.textProperty().removeListener(loginRegisterForgotPassController.ForgotAccountInputLimitListener);
    }

    public void checkPane1Input() {
        hasStared();
        pane1SubmittedOnce();

        setAttributes(ForgotPassword1.getPaneNumber());
        setVisibilities(ForgotPassword1.getPaneNumber());

        if (isPane1InputsValid()) {
            proceedPane2();
            enableOTPInputTo4DigitsOnly();
            countDownResendOTP();
            System.out.println(forgotPassAccount);
        }
    }

    private void pane1SubmittedOnce() {
        loginRegisterForgotPassController.forgotPass1SubmittedOnce = true;
    }

    private boolean isPane1InputsValid() {
        return !forgotPassAccount.isBlank() && (forgotPassAccount.matches(REGEX_EMAIL) || forgotPassAccount.matches(REGEX_PHONE_NUMBER));
    }

    private void proceedPane2() {
        loginRegisterForgotPassController.forgotPasswordSwitchPane(ForgotPassword2.getPaneNumber());
    }

    /**
     * Pane 2
     */

    public void enableOTPInputTo4DigitsOnly() {
        loginRegisterForgotPassController.textFieldForgotPass2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                loginRegisterForgotPassController.textFieldForgotPass2.setText(oldValue);
            } else if (newValue.length() > OTP_LENGTH) {
                loginRegisterForgotPassController.textFieldForgotPass2.setText(oldValue);
            }
        });
    }

    private void generateOTP() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        forgotPassGeneratedOTP = String.valueOf(randomNumber);
        setAttributes(ForgotPassword2.getPaneNumber());
        System.out.println(randomNumber);
    }

    public void checkPane2Input() {
        pane2SubmittedOnce();

        setAttributes(ForgotPassword2.getPaneNumber());
        setVisibilities(ForgotPassword2.getPaneNumber());

        if (isPane2InputsValid()) {
            proceedPane3();
            setCountdownTimerTo1();
        }
    }

    private void pane2SubmittedOnce() {
        loginRegisterForgotPassController.forgotPass2SubmittedOnce = true;
    }

    private boolean isPane2InputsValid() {
        String inputOTP = loginRegisterForgotPassController.textFieldForgotPass2.getText();
        return !inputOTP.isBlank() && inputOTP.equals(forgotPassOTP);
    }

    private void proceedPane3() {
        loginRegisterForgotPassController.forgotPasswordSwitchPane(ForgotPassword3.getPaneNumber());
    }

    public void countDownResendOTP() {
        generateOTP();
        setLabelsForCountdown();
        setCountdownTimer();
        countdown = new Thread(new Runnable() {
            @Override
            public void run() {
                int minutes = 3;
                int seconds = 0;

                for (int i = minutes; i >= 0; i--) {
                    for (int j = seconds; j >= 0; j--) {
                        int finalI = i;
                        int finalJ = j;

                        Platform.runLater(() -> loginRegisterForgotPassController.labelCountdown.setText(String.format("0%d:%02d", finalI, finalJ)));

                        try {
                            Thread.sleep(countdownTimer);
                        } catch (InterruptedException e) {
                            errorMessage = e.getMessage();
                            logError(false);
                        }

                    }
                    seconds = 59;
                }

                Platform.runLater(() -> countDownDone());
            }
        });

        countdown.setDaemon(true);
        countdown.start();
    }

    private void setCountdownTimer() {
        countdownTimer = ONE_SECOND;
    }

    private void setCountdownTimerTo1() {
        countdownTimer = FINISH_COUNTDOWN;
    }

    private void setLabelsForCountdown() {
        loginRegisterForgotPassController.functionalOTP = false;
        loginRegisterForgotPassController.labelResendOTPTitle.setText("Time Remaining: ");
        loginRegisterForgotPassController.labelCountdown.setVisible(true);
        //loginRegisterForgotPassController.btnResendOTP.setDisable(false);
        loginRegisterForgotPassController.btnResendOTP.setTextFill(Color.web("#c3c3c3"));
        loginRegisterForgotPassController.btnResendOTP.setCursor(Cursor.DEFAULT);
        loginRegisterForgotPassController.btnResendOTP.setFocusTraversable(true);
        loginRegisterForgotPassController.btnForgotPass2Proceed.setFocusTraversable(true);
    }

    private void countDownDone() {
        loginRegisterForgotPassController.functionalOTP = true;
        loginRegisterForgotPassController.labelResendOTPTitle.setText("Didn't receive code?");
        loginRegisterForgotPassController.labelCountdown.setVisible(false);
        loginRegisterForgotPassController.btnResendOTP.setTextFill(Color.web("#000000"));
        //loginRegisterForgotPassController.btnResendOTP.setDisable(false);
        loginRegisterForgotPassController.btnResendOTP.setCursor(Cursor.HAND);
    }


    /**
     * Pane 3
     */

    public void checkPane3Input() {
        pane3SubmittedOnce();

        setAttributes(ForgotPassword3.getPaneNumber());
        setVisibilities(ForgotPassword3.getPaneNumber());

        loginRegisterForgotPassController.toggleRectangleModal();
        if (arePane3InputsValid())
            openPromptGoBackToLogin();

        loginRegisterForgotPassController.toggleRectangleModal();
    }

    private boolean arePane3InputsValid() {
        return !forgotPassNewPassword.isBlank() && !forgotPassConfirmNewPassword.isBlank() && forgotPassNewPassword.equals(forgotPassConfirmNewPassword) && !loginRegisterForgotPassController.forgotIsWeakPassword;
    }

    private void pane3SubmittedOnce() {
        loginRegisterForgotPassController.forgotPass3SubmittedOnce = true;
    }

    /**
     * Password Indicator
     */

    public void passwordIndicator() {
        setAttributes(ForgotPassword3.getPaneNumber());

        if (forgotPassNewPassword.isBlank()) {
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
        return (forgotPassNewPassword.length() < 8) ? 1 : -1;
    }

    private int checkFair() {
        if (forgotPassNewPassword.length() < 8) return -1; // Password length is less than 8 characters

        int criteriaMet = 0;
        if (forgotPassNewPassword.matches(".*[A-Z].*")) criteriaMet++;
        if (forgotPassNewPassword.matches(".*\\d.*")) criteriaMet++;
        if (forgotPassNewPassword.matches(".*[^A-Za-z0-9].*")) criteriaMet++;

        return (criteriaMet == 1) ? 2 : 3;
    }

    private int checkGood() {
        int criteriaMet = 0;
        if (forgotPassNewPassword.length() >= 8) {
            criteriaMet++;
        }
        if (forgotPassNewPassword.matches(".*[A-Z].*")) criteriaMet++;
        if (forgotPassNewPassword.matches(".*\\d.*")) criteriaMet++;
        if (forgotPassNewPassword.matches(".*[^A-Za-z0-9].*")) criteriaMet++;
        return criteriaMet;
    }

    public void emptyPassword() {
        loginRegisterForgotPassController.forgotIsWeakPassword = true;
        loginRegisterForgotPassController.forgotRectangle1.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle2.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setText(WHITE_ENUM.getText());
        loginRegisterForgotPassController.forgotIndicator.setVisible(false);
    }

    private void weakPassword() {
        loginRegisterForgotPassController.forgotIsWeakPassword = true;
        loginRegisterForgotPassController.forgotRectangle1.setFill(WEAK_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle2.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setText(WEAK_ENUM.getText());
        loginRegisterForgotPassController.forgotIndicator.setTextFill(WEAK_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setVisible(true);
    }

    private void fairPassword() {
        loginRegisterForgotPassController.forgotIsWeakPassword = false;
        loginRegisterForgotPassController.forgotRectangle1.setFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle2.setFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setText(FAIR_ENUM.getText());
        loginRegisterForgotPassController.forgotIndicator.setTextFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setVisible(true);
    }

    private void goodPassword() {
        loginRegisterForgotPassController.forgotIsWeakPassword = false;
        loginRegisterForgotPassController.forgotRectangle1.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle2.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle3.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setText(GOOD_ENUM.getText());
        loginRegisterForgotPassController.forgotIndicator.setTextFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setVisible(true);
    }

    private void strongPassword() {
        loginRegisterForgotPassController.forgotIsWeakPassword = false;
        loginRegisterForgotPassController.forgotRectangle1.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle2.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle3.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.forgotRectangle4.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setText(STRONG_ENUM.getText());
        loginRegisterForgotPassController.forgotIndicator.setTextFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.forgotIndicator.setVisible(true);
    }


    /**
     * Show or Hide password
     */

    public void togglePasswordField1() {
        loginRegisterForgotPassController.forgotPassShowNewPassword = !loginRegisterForgotPassController.forgotPassShowNewPassword;

        loginRegisterForgotPassController.btnForgotPassShowHidePassword1.setImage(loginRegisterForgotPassController.forgotPassShowNewPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
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

        loginRegisterForgotPassController.btnForgotPassShowHidePassword2.setImage(loginRegisterForgotPassController.forgotPassShowConfirmNewPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
        loginRegisterForgotPassController.textFieldForgotPass32.setVisible(!loginRegisterForgotPassController.forgotPassShowConfirmNewPassword);
        loginRegisterForgotPassController.textFieldShowForgotPass32.setVisible(loginRegisterForgotPassController.forgotPassShowConfirmNewPassword);

        if (loginRegisterForgotPassController.forgotPassShowConfirmNewPassword) {
            loginRegisterForgotPassController.textFieldShowForgotPass32.setText(loginRegisterForgotPassController.textFieldForgotPass32.getText());
        } else {
            loginRegisterForgotPassController.textFieldForgotPass32.setText(loginRegisterForgotPassController.textFieldShowForgotPass32.getText());
        }

        loginRegisterForgotPassController.btnForgotPass3Proceed.requestFocus();
    }

    /**
     * Redirecting to another scene
     */

    public void checkIfProgressStartedBeforeGoBack() {
        hasStared();

        boolean confirmGoBack = true;
        loginRegisterForgotPassController.toggleRectangleModal();
        if (loginRegisterForgotPassController.forgotPassStarted) {
            confirmGoBack = openPromptConfirmGoBack();
        }

        if (confirmGoBack) {
            loginRegisterForgotPassController.switchPane(Login.getPaneNumber());
            setCountdownTimerTo1();
        }

        loginRegisterForgotPassController.toggleRectangleModal();
    }

    private boolean openPromptConfirmGoBack() {
        setGoBackConfirmation();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelName.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }

    private void openPromptGoBackToLogin() {
        setResetPasswordSuccessfully();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterForgotPassController.labelName.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();

        loginRegisterForgotPassController.switchPane(Login.getPaneNumber());
    }
}
