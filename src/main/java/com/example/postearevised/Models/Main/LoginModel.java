package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.LoginRegisterForgotPassController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.InternetAndResolution.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

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
        loginRegisterForgotPassController.btnLoginShowHidePassword.setImage(loginRegisterForgotPassController.hideImage);
        loginRegisterForgotPassController.textFieldPassword.setVisible(true);
        loginRegisterForgotPassController.textFieldShowPassword.setVisible(false);
        loginRegisterForgotPassController.labelRegisterHere.setFocusTraversable(true);
        loginRegisterForgotPassController.labelForgotPassword.setFocusTraversable(true);
    }

    public void checkAccountInLoginIfPhoneNumber() {
        String account = loginRegisterForgotPassController.textFieldAccount.getText();

        if (account.matches(REGEX_FIRST_SIX_ARE_NUMBERS)) {
            enableLimitInput();
        } else {
            disableLimitInput();
        }
    }

    private void enableLimitInput() {
        loginRegisterForgotPassController.textFieldAccount.textProperty().addListener(loginRegisterForgotPassController.LoginAccountInputLimitListener);
    }

    public void disableLimitInput() {
        loginRegisterForgotPassController.textFieldAccount.textProperty().removeListener(loginRegisterForgotPassController.LoginAccountInputLimitListener);
    }


    /**
     * Check Validity
     */


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
        clearFields();
    }

    private void clearFields() {
        //loginRegisterForgotPassController.textFieldAccount.setText("");
        loginRegisterForgotPassController.textFieldPassword.setText("");
        loginRegisterForgotPassController.textFieldShowPassword.setText("");
        // Change ng textFieldAccount if carat is nasa text field account na
        loginRegisterForgotPassController.anchorPaneLogin.requestFocus();
        loginAccount = "";
        loginPassword = "";
    }

    public void hideIncorrectLabels() {
        loginRegisterForgotPassController.labelIncorrectCredentials.setVisible(false);
        loginRegisterForgotPassController.loginlabelPasswordLimitReached.setVisible(false);
    }

    /**
     * Show or Hide password
     */

    public void togglePasswordField() {
        loginRegisterForgotPassController.loginShowPassword = !loginRegisterForgotPassController.loginShowPassword;

        loginRegisterForgotPassController.btnLoginShowHidePassword.setImage(loginRegisterForgotPassController.loginShowPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
        loginRegisterForgotPassController.textFieldShowPassword.setVisible(loginRegisterForgotPassController.loginShowPassword);
        loginRegisterForgotPassController.textFieldPassword.setVisible(!loginRegisterForgotPassController.loginShowPassword);

        if (loginRegisterForgotPassController.loginShowPassword) {
            loginRegisterForgotPassController.textFieldShowPassword.setText(loginRegisterForgotPassController.textFieldPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldPassword.setText(loginRegisterForgotPassController.textFieldShowPassword.getText());
        }

        loginRegisterForgotPassController.anchorPaneLogin.requestFocus();
    }

    /**
     * Redirecting to another scene
     * Pop-up windows
     */

    public void checkInputsBeforeLogin() throws IOException {
        disableLimitInput();
        
        boolean proceed = checkCredentials();

        if (proceed) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Main.getURL()));
            Parent root = loader.load();
            mainStage = new Stage();
            mainStage.setTitle(Main.getTITLE());
            mainStage.setResizable(false);
            mainStage.setScene(new Scene(root));
            setScreenResolution(true, false);
            mainStage.getIcons().add(SYSTEM_LOGO);
            mainStage.show();
            closeThisStage();
        }
    }

    private void closeThisStage() {
        Stage stage = (Stage) loginRegisterForgotPassController.labelName.getScene().getWindow();
        stage.close();
    }
}