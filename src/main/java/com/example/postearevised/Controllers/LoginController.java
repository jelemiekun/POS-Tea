package com.example.postearevised.Controllers;

import com.example.postearevised.Models.LoginModel;
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

public class LoginController implements Initializable {
    public Image showImage = new Image(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Show Password.png"));
    public Image hideImage = new Image(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Hide Password.png"));
    public LoginModel loginModel;
    public boolean showPassword;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginModel = new LoginModel();
        loginModel.setLoginController(this);
        loginModel.setImage();
    }

    @FXML
    void btnForgotPasswordClicked(MouseEvent event) throws IOException {

    }

    @FXML
    void btnForgotPasswordTouched(TouchEvent event) throws IOException {

    }

    @FXML
    void btnLoginClicked(MouseEvent event) throws IOException {
        loginModel.goToAnotherScene(true);
    }

    @FXML
    void btnLoginTouched(TouchEvent event) throws IOException {
        loginModel.goToAnotherScene(true);
    }

    @FXML
    void btnRegisterClicked(MouseEvent event) throws IOException {
        loginModel.goToAnotherScene(false);
    }

    @FXML
    void btnRegisterTouched(TouchEvent event) throws IOException {
        loginModel.goToAnotherScene(false);
    }

    @FXML
    void btnShowHidePasswordClicked(MouseEvent event) throws IOException {
        loginModel.togglePasswordField();
    }

    @FXML
    void btnShowHidePasswordTouched(TouchEvent event) throws IOException {
        loginModel.togglePasswordField();
    }

    @FXML
    void textFieldAccountPressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            loginModel.goToAnotherScene(true);
    }

    @FXML
    void textFieldPasswordPressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            loginModel.goToAnotherScene(true);
    }
}
