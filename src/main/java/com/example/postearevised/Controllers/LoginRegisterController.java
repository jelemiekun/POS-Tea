package com.example.postearevised.Controllers;

import com.example.postearevised.Models.LoginRegisterModel;
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

public class LoginRegisterController implements Initializable {
    /**
     * Main
     */
    public Image showImage = new Image(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Show Password.png"));
    public Image hideImage = new Image(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Hide Password.png"));
    public LoginRegisterModel loginRegisterModel;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginRegisterModel = new LoginRegisterModel();
        loginRegisterModel.setLoginRegisterController(this);
        loginRegisterModel.setAttributes();
        loginRegisterModel.setPane();
        loginRegisterModel.setImage();
    }

    @FXML
    void btnForgotPasswordClicked(MouseEvent event) {

    }

    @FXML
    void btnForgotPasswordTouched(TouchEvent event) {

    }

    @FXML
    void btnShowHidePasswordClicked(MouseEvent event) {
        loginRegisterModel.togglePasswordField();
    }

    @FXML
    void btnShowHidePasswordTouched(TouchEvent event) {
        loginRegisterModel.togglePasswordField();
    }

    @FXML
    void btnLoginClicked(MouseEvent event) throws IOException {
        loginRegisterModel.goToAnotherScene(true);
    }

    @FXML
    void btnLoginTouched(TouchEvent event) throws IOException {
        loginRegisterModel.goToAnotherScene(true);
    }

    @FXML
    void textFieldAccountPressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            loginRegisterModel.goToAnotherScene(true);
    }

    @FXML
    void textFieldPasswordPressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            loginRegisterModel.goToAnotherScene(true);
    }

    @FXML
    void btnRegisterClicked(MouseEvent event) throws IOException {
        loginRegisterModel.goToAnotherScene(false);
    }

    @FXML
    void btnRegisterTouched(TouchEvent event) throws IOException {
        loginRegisterModel.goToAnotherScene(false);
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
    public ImageView btnClose;

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
    void btnCloseClicked(MouseEvent event) {
        loginRegisterModel.close();
    }

    @FXML
    void btnCloseTouched(TouchEvent event) {
        loginRegisterModel.close();
    }

    @FXML
    void btnRegister1Clicked(MouseEvent event) {
        loginRegisterModel.checkTextFields();
    }

    @FXML
    void btnRegister1Touched(TouchEvent event) {
        loginRegisterModel.checkTextFields();
    }

    @FXML
    void textFieldPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            loginRegisterModel.checkTextFields();
    }
}
