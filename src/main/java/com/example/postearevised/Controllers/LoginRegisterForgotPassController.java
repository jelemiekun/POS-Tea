package com.example.postearevised.Controllers;

import com.example.postearevised.Models.ForgotPassModel;
import com.example.postearevised.Models.LoginModel;
import com.example.postearevised.Models.RegisterModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.ExitConfirmation;
import static com.example.postearevised.Miscellaneous.Enums.StartPane.*;
import static com.example.postearevised.Miscellaneous.Others.*;
import static com.example.postearevised.Miscellaneous.Prompt.*;
import static com.example.postearevised.Miscellaneous.Reference.loginRegisterStage;

public class LoginRegisterForgotPassController implements Initializable {
    @FXML
    public Rectangle rectangleModal;
    public boolean rectangleModalVisible = false;
    public final Image showImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Show Password.png")));
    public final Image hideImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Hide Password.png")));
    public LoginModel loginModel;
    public RegisterModel registerModel;
    public ForgotPassModel forgotPassModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginModel = new LoginModel();
        loginModel.setLoginRegisterController(this);
        loginModel.setPane();
        loginModel.setImageAndPasswordFieldAndCheckBox();

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
                btnLogin.requestFocus();
                break;
            case 2: // Register
                loginRegisterStage.setTitle(Register.getName());
                anchorPaneLogin.setVisible(false);
                anchorPaneRegister.setVisible(true);
                anchorPaneForgotPass.setVisible(false);
                btnRegisterOnRegisterPane.requestFocus();
                break;
            case 3: // Forgot PasswordColors
                loginRegisterStage.setTitle(ForgotPassword.getName());
                anchorPaneLogin.setVisible(false);
                anchorPaneRegister.setVisible(false);
                anchorPaneForgotPass.setVisible(true);
                forgotPasswordInitialize();
                btnForgotPass1Proceed.requestFocus();
                break;
        }

        clearFieldsAndLabels();
    }

    public void toggleRectangleModal() {
        rectangleModalVisible = !rectangleModalVisible;
        rectangleModal.setVisible(rectangleModalVisible);
    }


    //Pag hiwa-hiwalayin ko ba 'to? hahahahaha

    private void clearFieldsAndLabels() {
        // Login
        textFieldAccount.setText("");
        textFieldPassword.setText("");
        textFieldShowPassword.setText("");
        labelIncorrectCredentials.setVisible(false);

        btnLoginShowHidePassword.setImage(hideImage);
        textFieldPassword.setVisible(true);
        textFieldShowPassword.setVisible(false);
        checkBoxRememberPassword.setSelected(false);

        loginShowPassword = false;

        // Register
        textFieldName.setText("");
        textFieldEmailOrPhoneNumber.setText("");
        textFieldNewPassword.setText("");
        textFieldShowNewPassword.setText("");
        textFieldConfirmNewPassword.setText("");
        textFieldShowConfirmNewPassword.setText("");
        labelName.setVisible(false);
        labelEmail.setVisible(false);
        labelPassword.setVisible(false);
        labelConfirmPassword.setVisible(false);
        labelInvalidEmailOrPhoneNumber.setVisible(false);
        labelPasswordNotMatch.setVisible(false);

        btnRegisterShowHidePassword1.setImage(hideImage);
        btnRegisterShowHidePassword2.setImage(hideImage);

        textFieldNewPassword.setVisible(true);
        textFieldConfirmNewPassword.setVisible(true);
        textFieldShowNewPassword.setVisible(false);
        textFieldShowConfirmNewPassword.setVisible(false);

        registerShowNewPassword = false;
        registerShowConfirmNewPassword = false;
        registerSubmittedOnce = false;

        registerModel.emptyPassword();

        registerNameToolTipImage.setVisible(false);
        registerPasswordToolTipImage.setVisible(false);

        // Forgot PasswordColors
        textFieldForgotPass1.setText("");
        textFieldForgotPass2.setText("");
        textFieldForgotPass31.setText("");
        textFieldForgotPass32.setText("");
        textFieldShowForgotPass31.setText("");

        textFieldShowForgotPass32.setText("");
        labelForgotPassInvalidAccount.setVisible(false);
        labelIncorrectOTP.setVisible(false);
        labelNewPassword.setVisible(false);
        labelConfirmNewPassword.setVisible(false);
        labelNewPasswordNotMatch.setVisible(false);

        textFieldForgotPass31.setVisible(true);
        textFieldForgotPass32.setVisible(true);
        textFieldShowForgotPass31.setVisible(false);
        textFieldShowForgotPass32.setVisible(false);
        btnForgotPassShowHidePassword1.setImage(hideImage);
        btnForgotPassShowHidePassword2.setImage(hideImage);

        forgotPassShowNewPassword = false;
        forgotPassShowConfirmNewPassword = false;

        forgotPass1SubmittedOnce = false;
        forgotPass2SubmittedOnce = false;
        forgotPass3SubmittedOnce = false;

        resetPasswordToolTipImage.setVisible(false);
    }

    public boolean checkConnectivity() throws IOException {
        boolean proceed;

        if (isInternetRequired) {
            if (isInternetAvailable()) {
                proceed = true;
            } else {
                noInternet();
                proceed = false;
            }
        } else {
            return true;
        }

        return proceed;
    }

    public void noInternet() throws IOException{
        toggleRectangleModal();
        openPromptInternetRequired();
        toggleRectangleModal();
    }

    private void openPromptInternetRequired() throws IOException {
        setInternetRequired();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ExitConfirmation.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(labelName.getScene().getWindow());

        newStage.setTitle(ExitConfirmation.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
    }

    /**
     * Login
     */
    public CheckBox checkBoxRememberPassword;
    public boolean loginShowPassword;

    @FXML
    public AnchorPane anchorPaneRegister;

    @FXML
    public AnchorPane btnForgotPassword;

    @FXML
    public ImageView btnLogin;

    @FXML
    public AnchorPane btnRegisterFromLogin;

    @FXML
    public ImageView btnLoginShowHidePassword;

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
    void btnForgotPasswordClicked(MouseEvent event) throws IOException {
        if (checkConnectivity())
            switchPane(ForgotPassword.getPaneNumber());
    }

    @FXML
    void btnForgotPasswordTouched(TouchEvent event) throws IOException {
        if (checkConnectivity())
            switchPane(ForgotPassword.getPaneNumber());
    }

    @FXML
    void btnLoginShowHidePasswordClicked(MouseEvent event) {
        loginModel.togglePasswordField();
    }

    @FXML
    void btnLoginShowHidePasswordTouched(TouchEvent event) {
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
        else
            loginModel.hideIncorrectCredentials();
    }

    @FXML
    void textFieldPasswordPressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            loginModel.checkInputsBeforeLogin();
        else
            loginModel.hideIncorrectCredentials();
    }

    @FXML
    void btnRegisterFromLoginClicked(MouseEvent event) throws IOException {
        if (checkConnectivity())
            switchPane(Register.getPaneNumber());
    }

    @FXML
    void btnRegisterFromLoginTouched(TouchEvent event) throws IOException {
        if (checkConnectivity())
            switchPane(Register.getPaneNumber());
    }

    @FXML
    void checkBoxRememberPasswordAction(ActionEvent event) {

    }


    /**
     * Register
     */
    private boolean registerNameToolTipClicked = false;
    private boolean registerPasswordToolTipClicked = false;
    public boolean registerSubmittedOnce = false;
    public boolean registerShowNewPassword;
    public boolean registerShowConfirmNewPassword;
    @FXML
    public AnchorPane nameFieldIcon;
    @FXML
    public AnchorPane emailFieldIcon;
    @FXML
    public AnchorPane newPasswordFieldIcon;
    @FXML
    public AnchorPane confirmNewPasswordFieldIcon;
    @FXML
    public ImageView registerNameToolTip;
    @FXML
    public ImageView registerNameToolTipImage;
    @FXML
    public ImageView registerPasswordToolTip;
    @FXML
    public ImageView registerPasswordToolTipImage;
    @FXML
    public Rectangle registerRectangle1;
    @FXML
    public Rectangle registerRectangle2;
    @FXML
    public Rectangle registerRectangle3;
    @FXML
    public Rectangle registerRectangle4;
    @FXML
    public Label registerIndicator;
    @FXML
    public TextField textFieldShowNewPassword;
    @FXML
    public TextField textFieldShowConfirmNewPassword;
    @FXML
    public ImageView btnRegisterShowHidePassword1;
    @FXML
    public ImageView btnRegisterShowHidePassword2;
    @FXML
    public Label labelPasswordNotMatch;
    @FXML
    public Label labelInvalidEmailOrPhoneNumber;
    @FXML
    public Label labelName;
    @FXML
    public Label labelEmail;
    @FXML
    public Label labelPassword;
    @FXML
    public Label labelConfirmPassword;

    @FXML
    public ImageView btnCloseRegister;

    @FXML
    public ImageView btnRegisterOnRegisterPane;

    @FXML
    public PasswordField textFieldConfirmNewPassword;

    @FXML
    public TextField textFieldEmailOrPhoneNumber;

    @FXML
    public PasswordField textFieldNewPassword;

    @FXML
    public TextField textFieldName;

    @FXML
    void btnCloseClicked(MouseEvent event) throws IOException {
        registerModel.close();
    }

    @FXML
    void btnCloseTouched(TouchEvent event) throws IOException {
        registerModel.close();
    }

    @FXML
    void btnRegisterOnRegisterPaneClicked(MouseEvent event) throws IOException {
        if (checkConnectivity())
            registerModel.registerAction();
    }

    @FXML
    void btnRegisterOnRegisterPaneTouched(TouchEvent event) throws IOException {
        if (checkConnectivity())
            registerModel.registerAction();
    }

    @FXML
    void textFieldTyping(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.registerAction();
        } else {
            registerModel.typing();
        }
    }

    @FXML
    void textFieldPasswordTyping(KeyEvent event) throws IOException{
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.registerAction();
        } else {
            registerModel.typing();
            registerModel.passwordIndicator();
        }
    }

    @FXML
    void btnRegisterShowHidePassword1Clicked(MouseEvent event) {
        registerModel.toggleNewPasswordField();
    }

    @FXML
    void btnRegisterShowHidePassword1Touched(MouseEvent event) {
        registerModel.toggleNewPasswordField();
    }

    @FXML
    void btnRegisterShowHidePassword2Clicked(MouseEvent event) {
        registerModel.toggleConfirmNewPasswordField();
    }

    @FXML
    void btnRegisterShowHidePassword2Touched(MouseEvent event) {
        registerModel.toggleConfirmNewPasswordField();
    }

    @FXML
    void registerPasswordToolTipEntered(MouseEvent event) {
        if (!registerPasswordToolTipClicked)
            registerPasswordToolTipImage.setVisible(true);
    }

    @FXML
    void registerPasswordToolTipExited(MouseEvent event) {
        if (!registerPasswordToolTipClicked)
            registerPasswordToolTipImage.setVisible(false);
    }

    @FXML
    void registerPasswordToolTipClicked(MouseEvent event) {
        registerPasswordToolTipClicked = !registerPasswordToolTipClicked;
        registerPasswordToolTipImage.setVisible(registerPasswordToolTipClicked);
    }

    @FXML
    void registerPasswordToolTipTouched(TouchEvent event) {
        registerPasswordToolTipClicked = !registerPasswordToolTipClicked;
        registerPasswordToolTipImage.setVisible(registerPasswordToolTipClicked);
    }

    @FXML
    void registerNameToolTipEntered(MouseEvent event) {
        if (!registerNameToolTipClicked)
            registerNameToolTipImage.setVisible(true);
    }

    @FXML
    void registerNameToolTipExited(MouseEvent event) {
        if (!registerNameToolTipClicked)
            registerNameToolTipImage.setVisible(false);
    }

    @FXML
    void registerNameToolTipClicked(MouseEvent event) {
        registerNameToolTipClicked = !registerNameToolTipClicked;
        registerNameToolTipImage.setVisible(registerNameToolTipClicked);
    }

    @FXML
    void registerNameToolTipTouched(TouchEvent event) {
        registerNameToolTipClicked = !registerNameToolTipClicked;
        registerNameToolTipImage.setVisible(registerNameToolTipClicked);
    }

    @FXML
    void nameFieldIconClicked(MouseEvent event) {
        registerModel.selectName();
    }

    @FXML
    void nameFieldIconTouched(TouchEvent event) {
        registerModel.selectName();
    }

    @FXML
    void emailFieldIconClicked(MouseEvent event) {
        registerModel.selectEmail();
    }

    @FXML
    void emailFieldIconTouched(TouchEvent event) {
        registerModel.selectEmail();
    }

    @FXML
    void newPasswordFieldIconClicked(MouseEvent event) {
        registerModel.selectNewPassword();
    }

    @FXML
    void newPasswordFieldIconTouched(TouchEvent event) {
        registerModel.selectNewPassword();
    }

    @FXML
    void confirmNewPasswordFieldIconClicked(MouseEvent event) {
        registerModel.selectConfirmNewPassword();
    }

    @FXML
    void confirmNewPasswordFieldIconTouched(TouchEvent event) {
        registerModel.selectConfirmNewPassword();
    }


    /**
     * Forgot Password
     */
    private boolean resetPasswordToolTipClicked = false;
    public boolean forgotPass1SubmittedOnce = false;
    public boolean forgotPass2SubmittedOnce = false;
    public boolean forgotPass3SubmittedOnce = false;
    public boolean forgotPassStarted = false;
    public boolean forgotPassShowNewPassword;
    public boolean forgotPassShowConfirmNewPassword;
    @FXML
    public ImageView resetPasswordToolTip;
    @FXML
    public ImageView resetPasswordToolTipImage;
    @FXML
    public Rectangle resetRectangle1;
    @FXML
    public Rectangle resetRectangle2;
    @FXML
    public Rectangle resetRectangle3;
    @FXML
    public Rectangle resetRectangle4;
    @FXML
    public Label resetIndicator;
    @FXML
    public ImageView btnForgotPass1Proceed;
    @FXML
    public ImageView btnForgotPass2Proceed;
    @FXML
    public ImageView btnForgotPass3Proceed;
    @FXML
    public Label btnResendOTP;
    @FXML
    public Label labelCountdown;
    @FXML
    public Label labelResendOTPTitle;
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
    public ImageView btnForgotPassShowHidePassword1;
    @FXML
    public ImageView btnForgotPassShowHidePassword2;
    @FXML
    public TextField textFieldForgotPass1;
    @FXML
    public TextField textFieldForgotPass2;
    @FXML
    public PasswordField textFieldForgotPass31;
    @FXML
    public PasswordField textFieldForgotPass32;
    @FXML
    public TextField textFieldShowForgotPass31;
    @FXML
    public TextField textFieldShowForgotPass32;
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
                btnForgotPass2Proceed.requestFocus();
                break;
            case 6:
                anchorPaneForgotPass1.setVisible(false);
                anchorPaneForgotPass2.setVisible(false);
                anchorPaneForgotPass3.setVisible(true);
                btnForgotPass3Proceed.requestFocus();
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
    void btnForgotPass1ProceedClicked(MouseEvent event) throws IOException {
        if (checkConnectivity())
            forgotPassModel.checkPane1Input();
    }

    @FXML
    void btnForgotPass1ProceedTouched(TouchEvent event) throws IOException {
        if (checkConnectivity())
            forgotPassModel.checkPane1Input();
    }

    @FXML
    void textFieldForgotPass1PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            forgotPassModel.checkPane1Input();
        else
            forgotPassModel.typing(ForgotPassword1.getPaneNumber());
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
    void btnForgotPass2ProceedClicked(MouseEvent event) throws IOException {
        if (checkConnectivity())
            forgotPassModel.checkPane2Input();
    }

    @FXML
    void btnForgotPass2ProceedTouched(TouchEvent event) throws IOException {
        if (checkConnectivity())
            forgotPassModel.checkPane2Input();
    }

    @FXML
    void textFieldForgotPass2PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            forgotPassModel.checkPane2Input();
        else
            forgotPassModel.typing(ForgotPassword2.getPaneNumber());
    }

    @FXML
    void btnResendOTPClicked(MouseEvent event) {
        forgotPassModel.countDownResendOTP();
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
        if (checkConnectivity())
            forgotPassModel.checkPane3Input();
    }

    @FXML
    void btnForgotPass3ProceedTouched(TouchEvent event) throws IOException {
        if (checkConnectivity())
            forgotPassModel.checkPane3Input();
    }

    @FXML
    void textFieldForgotPass3PressedEnter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER)
            forgotPassModel.checkPane3Input();
        else
            forgotPassModel.typing(ForgotPassword3.getPaneNumber());
    }

    @FXML
    void btnForgotPassShowHidePassword1Clicked(MouseEvent event) {
        forgotPassModel.togglePasswordField1();
    }

    @FXML
    void btnForgotPassShowHidePassword1Touched(TouchEvent event) {
        forgotPassModel.togglePasswordField1();
    }

    @FXML
    void btnForgotPassShowHidePassword2Clicked(MouseEvent event) {
        forgotPassModel.togglePasswordField2();
    }

    @FXML
    void btnForgotPassShowHidePassword2Touched(TouchEvent event) {
        forgotPassModel.togglePasswordField2();
    }

    @FXML
    void resetPasswordToolTipEntered(MouseEvent event) {
        if (!resetPasswordToolTipClicked)
            resetPasswordToolTipImage.setVisible(true);
    }

    @FXML
    void resetPasswordToolTipExited(MouseEvent event) {
        if (!resetPasswordToolTipClicked)
            resetPasswordToolTipImage.setVisible(false);
    }

    @FXML
    void resetPasswordToolTipClicked(MouseEvent event) {
        resetPasswordToolTipClicked = !resetPasswordToolTipClicked;
        resetPasswordToolTipImage.setVisible(resetPasswordToolTipClicked);
    }

    @FXML
    void resetPasswordToolTipTouched(TouchEvent event) {
        resetPasswordToolTipClicked = !resetPasswordToolTipClicked;
        resetPasswordToolTipImage.setVisible(resetPasswordToolTipClicked);
    }
}
