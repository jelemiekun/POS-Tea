package com.example.postearevised.Controllers.Main;

import com.example.postearevised.Models.Main.ForgotPassModel;
import com.example.postearevised.Models.Main.LoginModel;
import com.example.postearevised.Models.Main.RegisterModel;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Database.CSV.Accounts.AccountCSV.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.*;
import static com.example.postearevised.Miscellaneous.Enums.StartPane.*;
import static com.example.postearevised.Miscellaneous.Enums.StartPane.Register;
import static com.example.postearevised.Miscellaneous.Others.Internet.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.LoginForgotRegisterReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

public class LoginRegisterForgotPassController implements Initializable {
    @FXML
    public Rectangle rectangleModal;
    public boolean rectangleModalVisible = false;
    public int loginAttemptCounter = 0;
    public final Image showImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Show Password.png")));
    public final Image hideImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/LoginRegister/Hide Password.png")));
    public LoginModel loginModel;
    public RegisterModel registerModel;
    public ForgotPassModel forgotPassModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.gc();

        anchorPaneLoading.setVisible(true);
        loading();

        Thread readCSVs = new Thread(() -> {
            doesAccountCSVExist();
            doesStayLoggedInCSVExist();
        });

        readCSVs.start();

        try {
            readCSVs.join();
        } catch (InterruptedException e) {
            errorMessage = e.getMessage();
            logError(true);
        }

        loginModel = new LoginModel();
        registerModel = new RegisterModel();
        forgotPassModel = new ForgotPassModel();

        loginModel.setLoginRegisterController(this);

        Platform.runLater(() -> {
            if (directLogin) {
                textFieldAccount.setText(loginAccount);
                textFieldPassword.setText(loginPassword);
                loginModel.checkInputsBeforeLogin();
                fadeOutLoading();
            }
        });

        loginModel.setPane();
        loginModel.setImageAndPasswordFieldAndCheckBox();

        registerModel.setLoginRegisterController(this);
        registerModel.setAttributes();
        registerModel.setNoSpaceTextFieldListeners();

        forgotPassModel.setLoginRegisterController(this);

        setLoginAccountAndPasswordNoSpaceInputLimitListenerAndToolTip();

        checkBoxRememberPassword.setSelected(true);

        Platform.runLater(() -> {
            loginModel.checkIfResolutionIsTooLow();
            anchorPaneLogin.requestFocus();

            fadeOutLoading();
        });
    }

    /**
     * Loading
     */

    @FXML
    public AnchorPane anchorPaneLoading;
    @FXML
    public Label labelLoading;
    private Timeline loadingTimeline;
    private void loading() {
        loadingTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> labelLoading.setText("Loading.")),
                new KeyFrame(Duration.seconds(1), event -> labelLoading.setText("Loading..")),
                new KeyFrame(Duration.seconds(1.5), event -> labelLoading.setText("Loading..."))
        );
        loadingTimeline.setCycleCount(Timeline.INDEFINITE);
        loadingTimeline.play();
    }

    private void fadeOutLoading() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(1200), anchorPaneLoading);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            anchorPaneLoading.setVisible(false);
            loadingTimeline.stop();
        });
        fadeOut.play();
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
                registerModel.switchPane(1);
                btnRegisterOnRegisterPane.requestFocus();
                registerModel.setComboBoxRecoveryQuestion();
                break;
            case 3: // Forgot Password
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
        anchorPaneLogin.requestFocus();
        textFieldAccount.setText("");
        textFieldPassword.setText("");
        textFieldShowPassword.setText("");
        labelIncorrectCredentials.setVisible(false);

        btnLoginShowHidePassword.setImage(hideImage);
        textFieldPassword.setVisible(true);
        textFieldShowPassword.setVisible(false);

        loginShowPassword = false;

        loginModel.disableLimitInput();

        loginlabelPasswordLimitReached.setVisible(false);

        setLoginAccountAndPasswordNoSpaceInputLimitListenerAndToolTip();

        // Register
        anchorPaneRegister.requestFocus();
        textFieldName.setText("");
        textFieldMiddleName.setText("");
        textFieldLastName.setText("");
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


        registerFirstStepSubmittedOnce = false;
        registerFistStepProceed = false;
        labelName11.setVisible(false);
        labelName111.setVisible(false);
        labelConfirmPassword1.setVisible(false);
        labelFillUpThisForm1.setVisible(false);
        labelFillUpThisForm2.setVisible(false);
        labelFillUpThisForm3.setVisible(false);
        labelFillUpThisForm4.setVisible(false);
        registerRecoveryQuestionComboBox1.setValue("");
        registerRecoveryQuestionComboBox2.setValue("");
        textFieldRecoveryQuestionAnswer1.setText("");
        textFieldRecoveryQuestionAnswer2.setText("");
        registerLastStepSubmittedOnce = false;
        registerLastStepProceed = false;
        isUpdatingComboBox = false;


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

        registerNameToolTipClicked = false;
        registerNameToolTipImage.setVisible(false);
        registerPasswordToolTipClicked = false;
        registerPasswordToolTipImage.setVisible(false);

        registerModel.disableLimitInput();

        labelName1.setVisible(false);

        labelRegisterPasswordLimitReached.setVisible(false);

        setRegisterAccountAndPasswordNoSpaceInputLimitListener();

        // Forgot Password
        anchorPaneForgotPass1.requestFocus();
        textFieldForgotPass1.setText("");
        textFieldForgotPass31.setText("");
        textFieldForgotPass32.setText("");
        textFieldShowForgotPass31.setText("");

        textFieldShowForgotPass32.setText("");
        labelForgotPassInvalidAccount.setVisible(false);
        labelNewPassword.setVisible(false);
        labelConfirmNewPassword.setVisible(false);
        labelNewPasswordNotMatch.setVisible(false);
        labelForgotPassQuestion1.setText("Question One");
        labelForgotPassQuestion2.setText("Quesiton Two");
        textFieldForgotPassAnswer1.setText("");
        textFieldForgotPassAnswer2.setText("");
        labelIncorrectAnswer1.setVisible(false);
        labelIncorrectAnswer2.setVisible(false);

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

        forgotPassModel.emptyPassword();

        forgotPasswordToolTipClicked = false;
        forgotPasswordToolTipImage.setVisible(false);

        forgotPassModel.disableLimitInput1();

        forgotLabelPasswordLimitReached.setVisible(false);

        setForgotPassAccountAndPasswordNoSpaceInputLimitListener();
    }

    public boolean checkConnectivity() {
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

    public void noInternet() {
        toggleRectangleModal();
        openPromptInternetRequired();
        toggleRectangleModal();
    }

    private void openPromptInternetRequired() {
        setInternetRequired();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        promptStage = new Stage();

        promptStage.initModality(Modality.WINDOW_MODAL);
        promptStage.initOwner(labelName.getScene().getWindow());

        promptStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        promptStage.setResizable(false);
        promptStage.getIcons().add(SYSTEM_LOGO);
        promptStage.setScene(new Scene(root));
        promptStage.showAndWait();
    }

    /**
     * Resolution too low
     */
    @FXML
    public AnchorPane anchorPaneResolutionTooLow;

    /**
     * Login
     */
    public boolean loginShowPassword;

    @FXML
    public Label loginlabelPasswordLimitReached;

    @FXML
    public Label labelRegisterHere;

    @FXML
    public Label labelForgotPassword;

    @FXML
    public CheckBox checkBoxRememberPassword;

    @FXML
    public AnchorPane anchorPaneRegister;

    @FXML
    public ImageView btnLogin;

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

    public final ChangeListener<String> LoginAccountInputLimitListener = (observable, oldValue, newValue) -> {
        if (newValue.length() > INPUT_LIMIT_TO_ELEVEN) {
            textFieldAccount.setText(oldValue);
        } else if (!newValue.matches(REGEX_DIGITS_ONLY)) {
            textFieldAccount.setText(oldValue);
        }
    };

    void setLoginAccountAndPasswordNoSpaceInputLimitListenerAndToolTip() {
        textFieldAccount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldAccount.setText(oldValue);
            }
        });
        textFieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldPassword.setText(oldValue);
            }
        });
        textFieldShowPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldShowPassword.setText(oldValue);
            }
        });
        textFieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldPassword.setText(oldValue);
                loginlabelPasswordLimitReached.setVisible(true);
            }
        });
        textFieldShowPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldShowPassword.setText(oldValue);
                loginlabelPasswordLimitReached.setVisible(true);
            }
        });

        Tooltip.install(btnLoginShowHidePassword, showPasswordToolTip);
        Tooltip.install(btnRegisterShowHidePassword1, showPasswordToolTip);
        Tooltip.install(btnRegisterShowHidePassword2, showPasswordToolTip);
        Tooltip.install(btnForgotPassShowHidePassword1, showPasswordToolTip);
        Tooltip.install(btnForgotPassShowHidePassword2, showPasswordToolTip);
    }

    @FXML
    void loginBtnForgotPasswordClicked() {
        if (checkConnectivity())
            switchPane(ForgotPassword.getPaneNumber());
    }

    @FXML
    void loginBtnForgotPasswordTouched() {
        if (checkConnectivity())
            switchPane(ForgotPassword.getPaneNumber());
    }

    @FXML
    void btnLoginShowHidePasswordClicked() {
        loginModel.togglePasswordField();
    }

    @FXML
    void btnLoginShowHidePasswordTouched() {
        loginModel.togglePasswordField();
    }

    @FXML
    void btnLoginClicked() {
        loginModel.checkInputsBeforeLogin();
    }

    @FXML
    void btnLoginTouched() {
        loginModel.checkInputsBeforeLogin();
    }

    @FXML
    void loginTextFieldAccountPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginModel.checkInputsBeforeLogin();
        } else {
            loginModel.hideIncorrectLabels();
            loginModel.checkAccountInLoginIfPhoneNumber();
        }
    }

    @FXML
    void loginTextFieldPasswordPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            loginModel.checkInputsBeforeLogin();
        else
            loginModel.hideIncorrectLabels();
    }

    @FXML
    void btnRegisterFromLoginClicked() {
        if (checkConnectivity())
            switchPane(Register.getPaneNumber());
    }

    @FXML
    void btnRegisterFromLoginTouched() {
        if (checkConnectivity())
            switchPane(Register.getPaneNumber());
    }

    @FXML
    public void anchorPaneLoginClicked() {
        anchorPaneLogin.requestFocus();
    }

    @FXML
    public void btnLoginShowHidePasswordPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginModel.togglePasswordField();
            btnLoginShowHidePassword.requestFocus();
        }
    }

    @FXML
    public void btnLoginPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginModel.checkInputsBeforeLogin();
        }
    }

    @FXML
    public void labelRegisterHerePressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (checkConnectivity())
                switchPane(Register.getPaneNumber());
        }
    }

    @FXML
    public void labelForgotPasswordPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (checkConnectivity())
                switchPane(ForgotPassword.getPaneNumber());
        }
    }

    @FXML
    void loginCheckBoxRememberPasswordAction() {
//        loginModel.checkBoxDeselected();  // uncomment to if you need ng pop up sa pag deselect
    }


    /**
     * Register
     */
    @FXML
    public Label labelForgotPassQuestion1;
    @FXML
    public Label labelForgotPassQuestion2;
    @FXML
    public TextField textFieldForgotPassAnswer1;
    @FXML
    public TextField textFieldForgotPassAnswer2;
    @FXML
    public Label labelIncorrectAnswer1;
    @FXML
    public Label labelIncorrectAnswer2;
    @FXML
    public TextField textFieldMiddleName;
    @FXML
    public TextField textFieldLastName;
    @FXML
    public AnchorPane anchorPaneRegisterAccountDetails;
    @FXML
    public AnchorPane anchorPaneRegisterBasicInfo;
    public boolean registerIsWeakPassword = false;
    public boolean iconsClicked = false;
    private boolean registerNameToolTipClicked = false;
    private boolean registerPasswordToolTipClicked = false;
    public boolean registerSubmittedOnce = false;
    public boolean registerFirstStepSubmittedOnce = false;
    public boolean registerFistStepProceed = false;
    public boolean registerLastStepSubmittedOnce = false;
    public boolean registerLastStepProceed = false;
    public boolean registerShowNewPassword;
    public boolean registerShowConfirmNewPassword;
    public boolean isUpdatingComboBox = false;
    @FXML
    public Label labelName1;
    @FXML
    public Label labelRegisterPasswordLimitReached;
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
    public ImageView btnRegisterOnRegisterPane1;

    @FXML
    public Label labelConfirmPassword1;
    @FXML
    public Label labelName11;
    @FXML
    public Label labelName111;
    @FXML
    public AnchorPane anchorPaneRegisterRecoveryQuestions;
    @FXML
    public Label labelRegisterHeader;
    @FXML
    public Label labelFillUpThisForm1;
    @FXML
    public Label labelFillUpThisForm2;
    @FXML
    public Label labelFillUpThisForm3;
    @FXML
    public Label labelFillUpThisForm4;
    @FXML
    public ComboBox<String> registerRecoveryQuestionComboBox1;
    @FXML
    public ComboBox<String> registerRecoveryQuestionComboBox2;
    @FXML
    public TextField textFieldRecoveryQuestionAnswer1;
    @FXML
    public TextField textFieldRecoveryQuestionAnswer2;
    @FXML
    public ImageView btnGoBackRegister;

    @FXML
    public void btnGoBackRegisterClickedTouched() {
        if (anchorPaneRegisterAccountDetails.isVisible())
            registerModel.switchPane(1);
        else if (anchorPaneRegisterRecoveryQuestions.isVisible())
            registerModel.switchPane(2);
    }

    @FXML
    public void registerRecoveryQuestionComboBox1OnAction() {
        registerModel.comboBox1OnAction();
    }

    @FXML
    public void registerRecoveryQuestionComboBox2OnAction() {
        registerModel.comboBox2OnAction();
    }

    @FXML
    public void btnRegisterOnRegisterFinalBtnRegistrationClickedTouched() {
        registerModel.registerFinalProcessOnAction();
    }

    @FXML
    public void btnRegisterOnRegisterFinalBtnRegistrationPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            registerModel.registerFinalProcessOnAction();
    }

    @FXML
    public void textFieldRecoveryQuestionAnswerTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.registerFinalProcessOnAction();
        } else {
            registerModel.registerFinalProcessTyping();
        }
    }

    public final ChangeListener<String> RegisterAccountInputLimitListener = (observable, oldValue, newValue) -> {
        if (newValue.length() > INPUT_LIMIT_TO_ELEVEN) {
            textFieldEmailOrPhoneNumber.setText(oldValue);
        } else if (!newValue.matches(REGEX_DIGITS_ONLY)) {
            textFieldEmailOrPhoneNumber.setText(oldValue);
        }
    };

    @FXML
    void btnRegisterOnRegisterPane1PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            registerModel.checkFirstStepFields();
    }

    @FXML
    void btnRegisterOnRegisterPane1Clicked() {
        registerModel.checkFirstStepFields();
    }

    @FXML
    void btnRegisterOnRegisterPaneTouched1() {
        registerModel.checkFirstStepFields();
    }

    @FXML
    void registerBtnCloseClicked() {
        registerModel.close();
    }

    @FXML
    void btnCloseTouched() {
        registerModel.close();
    }


    @FXML
    void btnRegisterOnRegisterPaneClicked() {
        if (checkConnectivity())
            registerModel.registerAction();
    }

    @FXML
    void btnRegisterOnRegisterPaneTouched() {
        if (checkConnectivity())
            registerModel.registerAction();
    }

    @FXML
    void registerTextFieldTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.registerAction();
        } else {
            registerModel.typing();
        }
    }

    @FXML
    void textFieldName1Typing(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.registerAction1();
        } else {
            labelName1.setVisible(false);
            registerModel.typingFirstStep();
        }
    }

    @FXML
    public void emailFieldIcon1ClickedTouched() {
        registerModel.selectMiddleName();
        registerModel.iconsClicked();
    }

    @FXML
    public void iconSurNameClickedTouched() {
        registerModel.selectSurName();
        registerModel.iconsClicked();
    }

    @FXML
    void registerTextFieldPasswordTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.registerAction();
        } else {
            registerModel.typing();
            registerModel.passwordIndicator();
        }
    }

    @FXML
    void btnRegisterShowHidePassword1Clicked() {
        registerModel.toggleNewPasswordField();
    }

    @FXML
    void btnRegisterShowHidePassword1Touched() {
        registerModel.toggleNewPasswordField();
    }

    @FXML
    void btnRegisterShowHidePassword2Clicked() {
        registerModel.toggleConfirmNewPasswordField();
    }

    @FXML
    void btnRegisterShowHidePassword2Touched() {
        registerModel.toggleConfirmNewPasswordField();
    }

    @FXML
    void registerPasswordToolTipEntered() {
        if (!registerPasswordToolTipClicked)
            registerPasswordToolTipImage.setVisible(true);
    }

    @FXML
    void registerPasswordToolTipExited() {
        if (!registerPasswordToolTipClicked)
            registerPasswordToolTipImage.setVisible(false);
    }

    @FXML
    void registerPasswordToolTipClicked() {
        registerPasswordToolTipClicked = !registerPasswordToolTipClicked;
        registerPasswordToolTipImage.setVisible(registerPasswordToolTipClicked);
    }

    @FXML
    void registerPasswordToolTipTouched() {
        registerPasswordToolTipClicked = !registerPasswordToolTipClicked;
        registerPasswordToolTipImage.setVisible(registerPasswordToolTipClicked);
    }

    @FXML
    void registerNameToolTipEntered() {
        if (!registerNameToolTipClicked)
            registerNameToolTipImage.setVisible(true);
    }

    @FXML
    void registerNameToolTipExited() {
        if (!registerNameToolTipClicked)
            registerNameToolTipImage.setVisible(false);
    }

    @FXML
    void registerNameToolTipClicked() {
        registerNameToolTipClicked = !registerNameToolTipClicked;
        registerNameToolTipImage.setVisible(registerNameToolTipClicked);
    }

    @FXML
    void registerNameToolTipTouched() {
        registerNameToolTipClicked = !registerNameToolTipClicked;
        registerNameToolTipImage.setVisible(registerNameToolTipClicked);
    }

    @FXML
    void registerNameFieldIconClicked() {
        registerModel.selectGivenName();
        registerModel.iconsClicked();
    }

    @FXML
    void registerNameFieldIconTouched() {
        registerModel.selectGivenName();
        registerModel.iconsClicked();
    }

    @FXML
    void registerEmailFieldIconClicked() {
        registerModel.selectEmail();
        registerModel.iconsClicked();
    }

    @FXML
    void registerEmailFieldIconTouched() {
        registerModel.selectEmail();
        registerModel.iconsClicked();
    }

    @FXML
    void registerNewPasswordFieldIconClicked() {
        registerModel.selectNewPassword();
        registerModel.iconsClicked();
    }

    @FXML
    void registerNewPasswordFieldIconTouched() {
        registerModel.selectNewPassword();
        registerModel.iconsClicked();
    }

    @FXML
    void registerConfirmNewPasswordFieldIconClicked() {
        registerModel.selectConfirmNewPassword();
        registerModel.iconsClicked();
    }

    @FXML
    void registerConfirmNewPasswordFieldIconTouched() {
        registerModel.selectConfirmNewPassword();
        registerModel.iconsClicked();
    }

    @FXML
    void textFieldEmailOrPhoneNumberTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.registerAction();
        } else {
            registerModel.typing();
            registerModel.checkAccountInRegisterIfPhoneNumber();
            registerModel.checkIfDuplicateAccount();
        }
    }

    @FXML
    public void anchorPaneRegisterClicked() {
        if (!iconsClicked)
            anchorPaneRegister.requestFocus();
    }

    @FXML
    public void btnRegisterShowHidePassword1PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.toggleNewPasswordField();
            btnRegisterShowHidePassword1.requestFocus();
        }
    }

    @FXML
    public void btnRegisterShowHidePassword2PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            registerModel.toggleConfirmNewPasswordField();
            btnRegisterShowHidePassword2.requestFocus();
        }
    }

    @FXML
    public void btnRegisterOnRegisterPanePressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (checkConnectivity())
                registerModel.registerAction();
        }
    }

    void setRegisterAccountAndPasswordNoSpaceInputLimitListener() {
        textFieldEmailOrPhoneNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldEmailOrPhoneNumber.setText(oldValue);
            }
        });
        textFieldNewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldNewPassword.setText(oldValue);
            }
        });
        textFieldConfirmNewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldConfirmNewPassword.setText(oldValue);
            }
        });
        textFieldShowNewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldShowNewPassword.setText(oldValue);
            }
        });
        textFieldShowConfirmNewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldShowConfirmNewPassword.setText(oldValue);
            }
        });
        textFieldNewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldNewPassword.setText(oldValue);
                labelRegisterPasswordLimitReached.setVisible(true);
            }
        });
        textFieldShowNewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldShowNewPassword.setText(oldValue);
                labelRegisterPasswordLimitReached.setVisible(true);
            }
        });
        textFieldConfirmNewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldConfirmNewPassword.setText(oldValue);
            }
        });
        textFieldShowConfirmNewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldShowConfirmNewPassword.setText(oldValue);
            }
        });
        textFieldName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > NAME_LIMIT) {
                textFieldName.setText(oldValue);
                labelName1.setVisible(true);
            }
        });
    }


    /**
     * Forgot Password
     */
    public boolean forgotIsWeakPassword = false;
    private boolean forgotPasswordToolTipClicked = false;
    public boolean forgotPass1SubmittedOnce = false;
    public boolean forgotPass2SubmittedOnce = false;
    public boolean forgotPass3SubmittedOnce = false;
    public boolean forgotPassStarted = false;
    public boolean forgotPassShowNewPassword;
    public boolean forgotPassShowConfirmNewPassword;
    @FXML
    public Label forgotLabelPasswordLimitReached;
    @FXML
    public ImageView forgotPasswordToolTip;
    @FXML
    public ImageView forgotPasswordToolTipImage;
    @FXML
    public Rectangle forgotRectangle1;
    @FXML
    public Rectangle forgotRectangle2;
    @FXML
    public Rectangle forgotRectangle3;
    @FXML
    public Rectangle forgotRectangle4;
    @FXML
    public Label forgotIndicator;
    @FXML
    public ImageView btnForgotPass1Proceed;
    @FXML
    public ImageView btnForgotPass2Proceed;
    @FXML
    public ImageView btnForgotPass3Proceed;
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
    public PasswordField textFieldForgotPass31;
    @FXML
    public PasswordField textFieldForgotPass32;
    @FXML
    public TextField textFieldShowForgotPass31;
    @FXML
    public TextField textFieldShowForgotPass32;
    @FXML
    public Label labelForgotPassInvalidAccount;
    @FXML
    public Label labelNewPassword;
    @FXML
    public Label labelConfirmNewPassword;
    @FXML
    public Label labelNewPasswordNotMatch;

    public final ChangeListener<String> ForgotAccountInputLimitListener = (observable, oldValue, newValue) -> {
        if (newValue.length() > INPUT_LIMIT_TO_ELEVEN) {
            textFieldForgotPass1.setText(oldValue);
        } else if (!newValue.matches(REGEX_DIGITS_ONLY)) {
            textFieldForgotPass1.setText(oldValue);
        }
    };

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
                anchorPaneForgotPass1.requestFocus();
                break;
            case 5:
                forgotPassModel.setRecoveryQuestions();
                anchorPaneForgotPass1.setVisible(false);
                anchorPaneForgotPass2.setVisible(true);
                anchorPaneForgotPass3.setVisible(false);
                anchorPaneForgotPass2.requestFocus();
                break;
            case 6:
                anchorPaneForgotPass1.setVisible(false);
                anchorPaneForgotPass2.setVisible(false);
                anchorPaneForgotPass3.setVisible(true);
                anchorPaneForgotPass3.requestFocus();
                break;
        }
    }

    @FXML
    public void anchorPaneForgotClicked() {
        anchorPaneForgotPass.requestFocus();
    }

    /**
     * Forgot password - Pane 1
     */
    @FXML
    void btnCloseForgotPass1Clicked() {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnCloseForgotPass1Touched() {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnForgotPass1ProceedClicked() {
        if (checkConnectivity())
            forgotPassModel.checkPane1Input();
    }

    @FXML
    void btnForgotPass1ProceedTouched() {
        if (checkConnectivity())
            forgotPassModel.checkPane1Input();
    }

    @FXML
    void textFieldForgotPass1PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            forgotPassModel.checkPane1Input();
        } else {
            forgotPassModel.typing(ForgotPassword1.getPaneNumber());
            forgotPassModel.checkAccountInForgotIfPhoneNumber();
        }
    }

    @FXML
    void btnForgotPass1ProceedPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (checkConnectivity())
                forgotPassModel.checkPane1Input();
        }
    }


    /**
     * Forgot password - Pane 2
     */

    @FXML
    void btnCloseForgotPass2Clicked() {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnCloseForgotPass2Touched() {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnForgotPass2ProceedClicked() {
        if (checkConnectivity())
            forgotPassModel.checkPane2Input();
    }

    @FXML
    void btnForgotPass2ProceedTouched() {
        if (checkConnectivity())
            forgotPassModel.checkPane2Input();
    }

    @FXML
    void textFieldForgotPassAnswer1PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            forgotPassModel.checkPane2Input();
        } else {
            forgotPassModel.typing(ForgotPassword2.getPaneNumber());
        }
    }

    @FXML
    void textFieldForgotPassAnswer2PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            forgotPassModel.checkPane2Input();
        } else {
            forgotPassModel.typing(ForgotPassword2.getPaneNumber());
        }
    }

    @FXML
    void btnForgotPass2ProceedPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (checkConnectivity())
                forgotPassModel.checkPane2Input();
        }
    }


    /**
     * Forgot password - Pane 3
     */

    @FXML
    void btnCloseForgotPass3Clicked() {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnCloseForgotPass3Touched() {
        forgotPassModel.checkIfProgressStartedBeforeGoBack();
    }

    @FXML
    void btnForgotPass3ProceedClicked() {
        if (checkConnectivity())
            forgotPassModel.checkPane3Input();
    }

    @FXML
    void btnForgotPass3ProceedTouched() {
        if (checkConnectivity())
            forgotPassModel.checkPane3Input();
    }

    @FXML
    void textFieldForgotPass3PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            forgotPassModel.checkPane3Input();
        } else {
            forgotPassModel.typing(ForgotPassword3.getPaneNumber());
            forgotPassModel.passwordIndicator();
        }
    }

    @FXML
    void btnForgotPassShowHidePassword1Clicked() {
        forgotPassModel.togglePasswordField1();
    }

    @FXML
    void btnForgotPassShowHidePassword1Touched() {
        forgotPassModel.togglePasswordField1();
    }

    @FXML
    void btnForgotPassShowHidePassword2Clicked() {
        forgotPassModel.togglePasswordField2();
    }

    @FXML
    void btnForgotPassShowHidePassword2Touched() {
        forgotPassModel.togglePasswordField2();
    }

    @FXML
    void forgotPasswordToolTipEntered() {
        if (!forgotPasswordToolTipClicked)
            forgotPasswordToolTipImage.setVisible(true);
    }

    @FXML
    void forgotPasswordToolTipExited() {
        if (!forgotPasswordToolTipClicked)
            forgotPasswordToolTipImage.setVisible(false);
    }

    @FXML
    void forgotPasswordToolTipClicked() {
        forgotPasswordToolTipClicked = !forgotPasswordToolTipClicked;
        forgotPasswordToolTipImage.setVisible(forgotPasswordToolTipClicked);
    }

    @FXML
    void forgotPasswordToolTipTouched() {
        forgotPasswordToolTipClicked = !forgotPasswordToolTipClicked;
        forgotPasswordToolTipImage.setVisible(forgotPasswordToolTipClicked);
    }

    @FXML
    void btnForgotPassShowHidePassword1PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            forgotPassModel.togglePasswordField1();
            btnForgotPassShowHidePassword1.requestFocus();
        }
    }

    @FXML
    void btnForgotPassShowHidePassword2PressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            forgotPassModel.togglePasswordField2();
            btnForgotPassShowHidePassword2.requestFocus();
        }
    }

    @FXML
    void btnForgotPass3ProceedPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (checkConnectivity())
                forgotPassModel.checkPane3Input();
        }
    }

    void setForgotPassAccountAndPasswordNoSpaceInputLimitListener() {
        textFieldForgotPass1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldForgotPass1.setText(oldValue);
            }
        });
        textFieldForgotPass31.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldForgotPass31.setText(oldValue);
            }
        });
        textFieldShowForgotPass31.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldShowForgotPass31.setText(oldValue);
            }
        });
        textFieldForgotPass32.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldForgotPass32.setText(oldValue);
            }
        });
        textFieldShowForgotPass32.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.contains(" ")) {
                textFieldShowForgotPass32.setText(oldValue);
            }
        });
        textFieldForgotPass31.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldForgotPass31.setText(oldValue);
                forgotLabelPasswordLimitReached.setVisible(true);
            }
        });
        textFieldShowForgotPass31.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldShowForgotPass31.setText(oldValue);
                forgotLabelPasswordLimitReached.setVisible(true);
            }
        });
        textFieldForgotPass32.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldForgotPass32.setText(oldValue);
            }
        });
        textFieldShowForgotPass32.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > PASSWORD_LIMIT) {
                textFieldShowForgotPass32.setText(oldValue);
            }
        });
    }
}
