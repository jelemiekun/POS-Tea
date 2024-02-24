package com.example.postearevised.Controllers;

import com.example.postearevised.Models.ForgotPassModel;
import com.example.postearevised.Models.LoginModel;
import com.example.postearevised.Models.RegisterModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.StartPane.*;
import static com.example.postearevised.Miscellaneous.Reference.loginRegisterStage;

public class LoginRegisterForgotPassController implements Initializable {
    public LoginModel loginModel;
    public RegisterModel registerModel;
    public ForgotPassModel forgotPassModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginModel = new LoginModel();
        loginModel.setLoginRegisterController(this);
        loginModel.setPane();
        loginModel.setImage();

        registerModel = new RegisterModel();
        registerModel.setLoginRegisterController(this);
        registerModel.setAttributes();

        forgotPassModel = new ForgotPassModel();
        forgotPassModel.setLoginRegisterController(this);
    }

    public void switchPane(int paneNumber) {
        switch (paneNumber) {
            case 1: // Login
                loginRegisterStage.setTitle(Login.getName());
                anchorPaneLogin.setVisible(true);
                anchorPaneRegister.setVisible(false);
                anchorPaneForgotPass.setVisible(false);
                break;
            case 2: // Register
                loginRegisterStage.setTitle(Register.getName());
                anchorPaneLogin.setVisible(false);
                anchorPaneRegister.setVisible(true);
                anchorPaneForgotPass.setVisible(false);
                break;
            case 3: // Forgot Password
                loginRegisterStage.setTitle(ForgotPassword.getName());
                anchorPaneLogin.setVisible(false);
                anchorPaneRegister.setVisible(false);
                anchorPaneForgotPass.setVisible(true);
                forgotPasswordInitialize();
                break;
        }

        clearFieldsAndLabels();
    }

    private void clearFieldsAndLabels() {
        // Login
        textFieldAccount.setText("");
        textFieldPassword.setText("");
        textFieldShowPassword.setText("");
        labelIncorrectCredentials.setVisible(false);

        // Register
        textFieldUsername.setText("");
        textFieldEmail.setText("");
        textFieldPassword1.setText("");
        textFieldConfirmPassword.setText("");
        labelUsername.setVisible(false);
        labelEmail.setVisible(false);
        labelPassword.setVisible(false);
        labelConfirmPassword.setVisible(false);
        labelInvalidEmail.setVisible(false);
        labelPasswordNotMatch.setVisible(false);

        // Forgot Password
        textFieldForgotPass1.setText("");
        labelForgotPassInvalidAccount.setVisible(false);

        textFieldForgotPass2.setText("");
        labelIncorrectOTP.setVisible(false);

        textFieldForgotPass31.setText("");
        textFieldForgotPass32.setText("");
        labelNewPassword.setVisible(false);
        labelConfirmNewPassword.setVisible(false);
        labelNewPasswordNotMatch.setVisible(false);
    }



    /**
     * Login
     */
    public Image showImage = new Image(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Show Password.png"));
    public Image hideImage = new Image(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Hide Password.png"));
    public boolean showPassword;

    @FXML
    public AnchorPane anchorPaneRegister;

    @FXML
    public AnchorPane btnForgotPassword;

    @FXML
    public ImageView btnLogin;

    @FXML
    public AnchorPane btnRegister;

    @FXML
    public ImageView btnShowHidePassword;

    @FXML
    public TextField textFieldAccount;

    @FXML
    public TextField textFieldShowPassword;

    @FXML
    public Label labelIncorrectCredentials;

    @FXML
    public PasswordField textFieldPassword;
    @FXML
    public AnchorPane anchorPaneLogin;

    @FXML
    void btnForgotPasswordClicked(MouseEvent event) {
        switchPane(ForgotPassword.getPaneNumber());
    }

    @FXML
    void btnForgotPasswordTouched(TouchEvent event) {
        switchPane(ForgotPassword.getPaneNumber());
    }

    @FXML
    void btnShowHidePasswordClicked(MouseEvent event) {
        loginModel.togglePasswordField();
    }

    @FXML
    void btnShowHidePasswordTouched(TouchEvent event) {
        loginModel.togglePasswordField();
    }

    @FXML
    void btnLoginClicked(MouseEvent event) throws IOException {
        loginModel.checkInputsBeforeLogin();
    }

    @FXML
    void btnLoginTouched(TouchEvent event) throws IOException {
        loginModel.checkInputsBeforeLogin();
    }

    @FXML
    void textFieldAccountPressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            loginModel.checkInputsBeforeLogin();
    }

    @FXML
    void textFieldPasswordPressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            loginModel.checkInputsBeforeLogin();
    }

    @FXML
    void btnRegisterFromLoginClicked(MouseEvent event) {
        switchPane(Register.getPaneNumber());
    }

    @FXML
    void btnRegisterFromLoginTouched(TouchEvent event) {
        switchPane(Register.getPaneNumber());
    }


    /**
     * Register
     */

    @FXML
    public Label labelPasswordNotMatch;
    @FXML
    public Label labelInvalidEmail;
    @FXML
    public Label labelUsername;
    @FXML
    public Label labelEmail;
    @FXML
    public Label labelPassword;
    @FXML
    public Label labelConfirmPassword;

    @FXML
    public ImageView btnCloseRegister;

    @FXML
    public ImageView btnRegister1;

    @FXML
    public PasswordField textFieldConfirmPassword;

    @FXML
    public TextField textFieldEmail;

    @FXML
    public PasswordField textFieldPassword1;

    @FXML
    public TextField textFieldUsername;

    @FXML
    void btnCloseClicked(MouseEvent event) throws IOException {
        registerModel.close();
    }

    @FXML
    void btnCloseTouched(TouchEvent event) throws IOException {
        registerModel.close();
    }

    @FXML
    void btnRegisterClicked(MouseEvent event) throws IOException {
        registerModel.registerAction();
    }

    @FXML
    void btnRegisterTouched(TouchEvent event) throws IOException {
        registerModel.registerAction();
    }

    @FXML
    void textFieldPressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            registerModel.registerAction();
    }

    /**
     * Forgot Password
     */

    @FXML
    public AnchorPane anchorPaneForgotPass;
    @FXML
    public AnchorPane anchorPaneForgotPass1;
    @FXML
    public AnchorPane anchorPaneForgotPass2;
    @FXML
    public AnchorPane anchorPaneForgotPass3;
    @FXML
    public ImageView btnCloseForgotPass1;
    @FXML
    public ImageView btnCloseForgotPass2;
    @FXML
    public ImageView btnCloseForgotPass3;
    @FXML
    public TextField textFieldForgotPass1;
    @FXML
    public TextField textFieldForgotPass2;
    @FXML
    public PasswordField textFieldForgotPass31;
    @FXML
    public PasswordField textFieldForgotPass32;
    @FXML
    public Label labelIncorrectOTP;
    @FXML
    public Label labelForgotPassInvalidAccount;
    @FXML
    public Label labelNewPassword;
    @FXML
    public Label labelConfirmNewPassword;
    @FXML
    public Label labelNewPasswordNotMatch;
    public boolean forgotPasswordStarted = false;

    private void forgotPasswordInitialize() {
        anchorPaneForgotPass1.setVisible(true);
        anchorPaneForgotPass2.setVisible(false);
        anchorPaneForgotPass3.setVisible(false);
    }

    public void forgotPasswordSwitchPane(int forgotPasswordPaneNumber) {
        switch (forgotPasswordPaneNumber) {
            case 4:
                anchorPaneForgotPass1.setVisible(true);
                anchorPaneForgotPass2.setVisible(false);
                anchorPaneForgotPass3.setVisible(false);
                break;
            case 5:
                anchorPaneForgotPass1.setVisible(false);
                anchorPaneForgotPass2.setVisible(true);
                anchorPaneForgotPass3.setVisible(false);
                break;
            case 6:
                anchorPaneForgotPass1.setVisible(false);
                anchorPaneForgotPass2.setVisible(false);
                anchorPaneForgotPass3.setVisible(true);
                break;
        }
    }

    /**
     * Forgot password - Pane 1
     */
    @FXML
    void btnCloseForgotPass1Clicked(MouseEvent event) throws IOException {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnCloseForgotPass1Touched(TouchEvent event) throws IOException {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnForgotPass1ProceedClicked(MouseEvent event) {
        forgotPassModel.checkPane1Input();
    }

    @FXML
    void btnForgotPass1ProceedTouched(TouchEvent event) {
        forgotPassModel.checkPane1Input();
    }

    @FXML
    void textFieldForgotPass1PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            forgotPassModel.checkPane1Input();
    }


    /**
     * Forgot password - Pane 2
     */

    @FXML
    void btnCloseForgotPass2Clicked(MouseEvent event) throws IOException {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnCloseForgotPass2Touched(TouchEvent event) throws IOException {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnForgotPass2ProceedClicked(MouseEvent event) {
        forgotPassModel.checkPane2Input();
    }

    @FXML
    void btnForgotPass2ProceedTouched(TouchEvent event) {
        forgotPassModel.checkPane2Input();
    }

    @FXML
    void textFieldForgotPass2PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            forgotPassModel.checkPane2Input();
    }


    /**
     * Forgot password - Pane 3
     */

    @FXML
    void btnCloseForgotPass3Clicked(MouseEvent event) throws IOException {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnCloseForgotPass3Touched(TouchEvent event) throws IOException {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnForgotPass3ProceedClicked(MouseEvent event) throws IOException {
        forgotPassModel.checkPane3Input();
    }

    @FXML
    void btnForgotPass3ProceedTouched(TouchEvent event) throws IOException {
        forgotPassModel.checkPane3Input();
    }

    @FXML
    void textFieldForgotPass31PressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            forgotPassModel.checkPane3Input();
    }

    @FXML
    void textFieldForgotPass32PressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            forgotPassModel.checkPane3Input();
    }

}
