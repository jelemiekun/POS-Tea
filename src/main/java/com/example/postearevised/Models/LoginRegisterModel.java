package com.example.postearevised.Models;

import com.example.postearevised.Controllers.LoginRegisterController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Reference.*;

public class LoginRegisterModel {

    private LoginRegisterController loginRegisterController;

    public void setLoginRegisterController(LoginRegisterController loginRegisterController) {
        this.loginRegisterController = loginRegisterController;
    }

    public void setPane() {
        loginRegisterController.anchorPaneLogin.setVisible(true);
        loginRegisterController.anchorPaneRegister.setVisible(false);
    }

    public void togglePane() {
        loginRegisterController.anchorPaneLogin.setVisible(!loginRegisterController.anchorPaneLogin.isVisible());
        loginRegisterController.anchorPaneRegister.setVisible(!loginRegisterController.anchorPaneRegister.isVisible());
        clearFieldsAndHideLabels();
        togglePaneTitle();
    }

    private void clearFieldsAndHideLabels() {
        loginRegisterController.textFieldAccount.setText("");
        loginRegisterController.textFieldPassword.setText("");
        loginRegisterController.textFieldShowPassword.setText("");
        loginRegisterController.textFieldUsername.setText("");
        loginRegisterController.textFieldEmail.setText("");
        loginRegisterController.textFieldPassword1.setText("");
        loginRegisterController.textFieldConfirmPassword.setText("");
        loginRegisterController.labelUsername.setVisible(false);
        loginRegisterController.labelEmail.setVisible(false);
        loginRegisterController.labelPassword.setVisible(false);
        loginRegisterController.labelConfirmPassword.setVisible(false);
        loginRegisterController.labelInvalidEmail.setVisible(false);
        loginRegisterController.labelPasswordNotMatch.setVisible(false);
    }

    private void togglePaneTitle() {
        loginRegisterStage.setTitle(loginRegisterController.anchorPaneLogin.isVisible() ? Login.getTITLE() : Register.getTITLE());
    }

    /**
     * Main
     */

    public void setImage() {
        loginRegisterController.btnShowHidePassword.setImage(loginRegisterController.showImage);
    }

    public void togglePasswordField() {
        loginRegisterController.showPassword = !loginRegisterController.showPassword;

        loginRegisterController.btnShowHidePassword.setImage(loginRegisterController.showPassword ? loginRegisterController.hideImage : loginRegisterController.showImage);
        loginRegisterController.textFieldShowPassword.setVisible(loginRegisterController.showPassword);
        loginRegisterController.textFieldPassword.setVisible(!loginRegisterController.showPassword);

        if (loginRegisterController.showPassword) {
            loginRegisterController.textFieldShowPassword.setText(loginRegisterController.textFieldPassword.getText());
        } else {
            loginRegisterController.textFieldPassword.setText(loginRegisterController.textFieldShowPassword.getText());
        }

        loginRegisterController.btnLogin.requestFocus();
    }

    public void goToAnotherScene(boolean isLogin) throws IOException {
        boolean proceed = false;
        String title = isLogin ? Main.getTITLE() : Register.getTITLE();
        String url = isLogin ? Main.getURL(): Register.getURL();

        if (isLogin) {
            proceed = checkCredentials();
        } else {
            togglePane();
        }

        if (proceed) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            Parent root = loader.load();
            mainStage = new Stage();
            mainStage.setTitle(title);
            mainStage.setResizable(false);
            mainStage.setScene(new Scene(root));
            mainStage.show();
            closeThisStage();
        }
    }

    // If account is found
    private boolean checkCredentials() {
        String account = loginRegisterController.textFieldAccount.getText();
        String password = loginRegisterController.showPassword ? loginRegisterController.textFieldShowPassword.getText() : loginRegisterController.textFieldPassword.getText();

        if (account.equals(accountReference) && password.equals(passwordReference)) { // CHECK HERE SA DATABASE
            return true;
        } else {
            showIncorrectCredentials();
            return false;
        }
    }

    private void showIncorrectCredentials() {
        loginRegisterController.labelIncorrectCredentials.setVisible(true);
    }

    /**
     * Register
     */
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public void setAttributes() {
        username = loginRegisterController.textFieldUsername.getText();
        email = loginRegisterController.textFieldEmail.getText();
        password = loginRegisterController.textFieldPassword1.getText();
        confirmPassword = loginRegisterController.textFieldConfirmPassword.getText();
    }

    public void registerAction() throws IOException {
        checkTextFields();
    }

    private void checkTextFields() throws IOException {
        setAttributes();

        boolean allFieldsNotEmpty = notEmptyTextFields();
        boolean validEmail = isValidEmail();
        boolean passwordsMatch = passwordMatched();

        loginRegisterController.labelUsername.setVisible(username.isBlank());
        loginRegisterController.labelEmail.setVisible(email.isBlank());
        loginRegisterController.labelPassword.setVisible(password.isBlank());
        loginRegisterController.labelConfirmPassword.setVisible(confirmPassword.isBlank());
        loginRegisterController.labelInvalidEmail.setVisible(!email.isBlank() && !validEmail);
        loginRegisterController.labelPasswordNotMatch.setVisible(!passwordsMatch);

        if (allFieldsNotEmpty && validEmail && passwordsMatch) {
            if (openTAC()) {
                System.out.println("Success account");
            }
        }
    }

    private boolean openTAC() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(TermsAndCondition.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterController.labelUsername.getScene().getWindow());

        newStage.setTitle(TermsAndCondition.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }

    private boolean isValidEmail() {
        return email.matches(regexEmail);
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
            if (openExitConfirmation()) {
                goToLogin();
            }
        } else {
            goToLogin();
        }
    }

    private boolean openExitConfirmation() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ExitConfirmation.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(loginRegisterController.labelUsername.getScene().getWindow());

        newStage.setTitle(ExitConfirmation.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }

    private void goToLogin() {
        togglePane();
    }

    private void closeThisStage() {
        Stage stage = (Stage) loginRegisterController.labelUsername.getScene().getWindow();
        stage.close();
    }
}