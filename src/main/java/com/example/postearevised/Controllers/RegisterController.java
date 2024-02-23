package com.example.postearevised.Controllers;

import com.example.postearevised.Models.RegisterModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    public RegisterModel registerModel;

    @FXML
    public Label labelUsername;
    @FXML
    public Label labelEmail;
    @FXML
    public Label labelPassword;
    @FXML
    public Label labelConfirmPassword;

    @FXML
    public AnchorPane anchorPaneTAC;

    @FXML
    public ImageView btnClose;

    @FXML
    public ImageView btnRegister;

    @FXML
    public PasswordField textFieldConfirmPassword;

    @FXML
    public TextField textFieldEmail;

    @FXML
    public PasswordField textFieldPassword;

    @FXML
    public TextField textFieldUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerModel = new RegisterModel();
        registerModel.setRegisterController(this);
        registerModel.setAttributes();
    }

    @FXML
    void anchorPaneTACClicked(MouseEvent event) {

    }

    @FXML
    void anchorPaneTACTouched(TouchEvent event) {

    }

    @FXML
    void btnCloseClicked(MouseEvent event) throws IOException {
        registerModel.close();
    }

    @FXML
    void btnCloseTouched(TouchEvent event) throws IOException {
        registerModel.close();
    }

    @FXML
    void btnRegisterClicked(MouseEvent event) {
        registerModel.checkTextFields();
    }

    @FXML
    void btnRegisterTouched(TouchEvent event) {
        registerModel.checkTextFields();
    }

    @FXML
    void textFieldPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            registerModel.checkTextFields();
    }

}
