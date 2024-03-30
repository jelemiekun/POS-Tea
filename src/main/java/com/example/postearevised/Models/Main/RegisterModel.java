package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.LoginRegisterForgotPassController;
import com.example.postearevised.Miscellaneous.Enums.StartPane;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.example.postearevised.Miscellaneous.Enums.PasswordColorsEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.LoginForgotRegisterReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;
import static com.example.postearevised.Miscellaneous.References.StylesReference.registerRecoveryQuestionStyle;

public class RegisterModel {
    private LoginRegisterForgotPassController loginRegisterForgotPassController;

    public void setLoginRegisterController(LoginRegisterForgotPassController loginRegisterForgotPassController) {
        this.loginRegisterForgotPassController = loginRegisterForgotPassController;
    }

    /**
     * Register
     */

    public void setComboBoxRecoveryQuestion() {
        loginRegisterForgotPassController.registerRecoveryQuestionComboBox1.setItems(recoveryQuestionsObservableList);
        loginRegisterForgotPassController.registerRecoveryQuestionComboBox2.setItems(recoveryQuestionsObservableList);

        setComboBoxStyle();
    }

    private void setComboBoxStyle() {
        loginRegisterForgotPassController.registerRecoveryQuestionComboBox1.setStyle(registerRecoveryQuestionStyle);
        loginRegisterForgotPassController.registerRecoveryQuestionComboBox2.setStyle(registerRecoveryQuestionStyle);
    }

    public void switchPane(int paneNumber) {
        switch (paneNumber) {
            case 1:
                loginRegisterForgotPassController.labelRegisterHeader.setText("Kindly fill in this form to Register");
                loginRegisterForgotPassController.anchorPaneRegisterBasicInfo.setVisible(true);
                loginRegisterForgotPassController.anchorPaneRegisterAccountDetails.setVisible(false);
                loginRegisterForgotPassController.anchorPaneRegisterRecoveryQuestions.setVisible(false);
                break;
            case 2:
                loginRegisterForgotPassController.labelRegisterHeader.setText("Kindly fill in this form to Register");
                loginRegisterForgotPassController.anchorPaneRegisterBasicInfo.setVisible(false);
                loginRegisterForgotPassController.anchorPaneRegisterAccountDetails.setVisible(true);
                loginRegisterForgotPassController.anchorPaneRegisterRecoveryQuestions.setVisible(false);
                break;
            case 3:
                loginRegisterForgotPassController.labelRegisterHeader.setText("To enhance security, select a question and answer. This information can also be used for account recovery.");
                loginRegisterForgotPassController.anchorPaneRegisterBasicInfo.setVisible(false);
                loginRegisterForgotPassController.anchorPaneRegisterAccountDetails.setVisible(false);
                loginRegisterForgotPassController.anchorPaneRegisterRecoveryQuestions.setVisible(true);
                break;
        }
    }

    public void iconsClicked() {
        loginRegisterForgotPassController.iconsClicked = true;
        disableIconsClicked();
    }

    public void disableIconsClicked() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginRegisterForgotPassController.iconsClicked = false;
            }
        });
    }

    public void checkFirstStepFields() {
        setAttributesFirstStep();
        firstStepShowLabels();


        if (loginRegisterForgotPassController.registerFistStepProceed)
            switchPane(2);
    }

    public void registerAction() {
        loginRegisterForgotPassController.registerSubmittedOnce = true;
        checkTextFields();
    }

    public void registerAction1() {
        loginRegisterForgotPassController.registerFirstStepSubmittedOnce = true;
        checkFirstStepFields();
    }

    public void typingFirstStep() {
        setAttributesFirstStep();
        if (loginRegisterForgotPassController.registerFirstStepSubmittedOnce) {
            firstStepShowLabels();
        }
    }

    public void firstStepShowLabels() {
        boolean firstNameCheck = false;
        boolean middleNameCheck = false;
        boolean lastNameCheck = false;

        if (registerGivenName.isEmpty()) {
            loginRegisterForgotPassController.labelName11.setText("*please fill up this form");
            loginRegisterForgotPassController.labelName11.setVisible(true);
        } else if (!registerGivenName.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
            loginRegisterForgotPassController.labelName11.setText("*please enter english alphabet only");
            loginRegisterForgotPassController.labelName11.setVisible(true);
        } else {
            loginRegisterForgotPassController.labelName11.setVisible(false);
            firstNameCheck = true;
        }

        if (!registerMiddleName.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
            loginRegisterForgotPassController.labelName111.setText("*please enter english alphabet only");
            loginRegisterForgotPassController.labelName111.setVisible(true);
        } else {
            loginRegisterForgotPassController.labelName111.setVisible(false);
            middleNameCheck = true;
        }

        if (registerSurName.isEmpty()) {
            loginRegisterForgotPassController.labelConfirmPassword1.setText("*please fill up this form");
            loginRegisterForgotPassController.labelConfirmPassword1.setVisible(true);
        } else if (!registerSurName.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
            loginRegisterForgotPassController.labelConfirmPassword1.setText("*please enter english alphabet only");
            loginRegisterForgotPassController.labelConfirmPassword1.setVisible(true);
        } else {
            loginRegisterForgotPassController.labelConfirmPassword1.setVisible(false);
            lastNameCheck = true;
        }

        loginRegisterForgotPassController.registerFistStepProceed = firstNameCheck && middleNameCheck && lastNameCheck;
    }

    public void typing() {
        hideForgotPassVisibility();
        if (loginRegisterForgotPassController.registerSubmittedOnce) {
            setAttributes();
            setAccountDetailsVisibilities(isValidEmailOrPhoneNumber(), passwordMatched());
        }
    }

    private void hideForgotPassVisibility() {
        loginRegisterForgotPassController.labelRegisterPasswordLimitReached.setVisible(false);
    }

    public void setAttributesFirstStep() {
        registerGivenName = loginRegisterForgotPassController.textFieldName.getText().trim();
        registerMiddleName = loginRegisterForgotPassController.textFieldMiddleName.getText().trim();
        registerSurName = loginRegisterForgotPassController.textFieldLastName.getText().trim();
    }

    public void setAttributes() {
        registerEmailOrPhoneNumber = loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.getText().trim();
        registerNewPassword = loginRegisterForgotPassController.registerShowNewPassword ? loginRegisterForgotPassController.textFieldShowNewPassword.getText().trim() : loginRegisterForgotPassController.textFieldNewPassword.getText().trim();
        registerConfirmNewPassword = loginRegisterForgotPassController.registerShowConfirmNewPassword ? loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText().trim() : loginRegisterForgotPassController.textFieldConfirmNewPassword.getText().trim();
    }

    private void setAccountDetailsVisibilities(boolean validEmailOrPhoneNumber, boolean passwordsMatch) {
        loginRegisterForgotPassController.labelName1.setVisible(registerGivenName.length() > NAME_LIMIT);
        loginRegisterForgotPassController.labelName.setVisible(registerGivenName.isBlank());
        loginRegisterForgotPassController.labelName.setText("*please fill up this form");
        loginRegisterForgotPassController.labelEmail.setVisible(registerEmailOrPhoneNumber.isBlank());
        loginRegisterForgotPassController.labelPassword.setVisible(registerNewPassword.isBlank());
        loginRegisterForgotPassController.labelPassword.setText("*please fill up this form");
        loginRegisterForgotPassController.labelConfirmPassword.setVisible(registerConfirmNewPassword.isBlank());
        loginRegisterForgotPassController.labelInvalidEmailOrPhoneNumber.setVisible(!registerEmailOrPhoneNumber.isBlank() && !validEmailOrPhoneNumber);
        loginRegisterForgotPassController.labelInvalidEmailOrPhoneNumber.setText(isAPhoneNumber() ? "*invalid number" : "*invalid email");

        boolean showPasswordNotMatch = true;
        boolean newPasswordAndConfirmNewPasswordNotMatched = isNewPasswordAndConfirmNewPasswordNotMatched(passwordsMatch);

        if (loginRegisterForgotPassController.registerIsWeakPassword) {
            loginRegisterForgotPassController.labelPasswordNotMatch.setText("*Weak password. Require mixed characters.");
            loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(true);
            showPasswordNotMatch = false;
        } else {
            loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(false);
        }

        if (showPasswordNotMatch) {
            if (newPasswordAndConfirmNewPasswordNotMatched) {
                loginRegisterForgotPassController.labelPasswordNotMatch.setText("*Password does not match");
                loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(true);
            } else {
                loginRegisterForgotPassController.labelPasswordNotMatch.setVisible(false);
            }
        }
    }

    public void checkAccountInRegisterIfPhoneNumber() {
        String account = loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.getText();

        if (account.matches(REGEX_FIRST_SIX_ARE_NUMBERS)) {
            enableLimitInput();
        } else {
            disableLimitInput();
        }
    }

    private void enableLimitInput() {
        loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.textProperty().addListener(loginRegisterForgotPassController.RegisterAccountInputLimitListener);
    }

    public void disableLimitInput() {
        loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.textProperty().removeListener(loginRegisterForgotPassController.RegisterAccountInputLimitListener);
    }

    public void comboBox1OnAction() {
        String selectedItem = loginRegisterForgotPassController.registerRecoveryQuestionComboBox1.getValue();
        if (selectedItem != null) {
            loginRegisterForgotPassController.registerRecoveryQuestionComboBox2.setItems(FXCollections.observableArrayList(
                    recoveryQuestionsObservableList.stream()
                            .filter(question -> !question.equals(selectedItem))
                            .collect(Collectors.toList())
            ));
        }
    }

    public void comboBox2OnAction() {
        String selectedItem = loginRegisterForgotPassController.registerRecoveryQuestionComboBox2.getValue();
        if (selectedItem != null) {
            loginRegisterForgotPassController.registerRecoveryQuestionComboBox1.setItems(FXCollections.observableArrayList(
                    recoveryQuestionsObservableList.stream()
                            .filter(question -> !question.equals(selectedItem))
                            .collect(Collectors.toList())
            ));
        }
    }

    public void registerFinalProcessOnAction() {
        submittedOnceLastProcess();
        setLastProcessLabels();

        if (loginRegisterForgotPassController.registerLastStepProceed)
            readTAC();
    }

    private void submittedOnceLastProcess() {
        loginRegisterForgotPassController.registerLastStepSubmittedOnce = true;
    }

    public void registerFinalProcessTyping() {
        if (loginRegisterForgotPassController.registerLastStepSubmittedOnce) {
            setLastProcessLabels();
        }
    }

    private void setLastProcessLabels() {
        loginRegisterForgotPassController.labelFillUpThisForm1.setVisible(loginRegisterForgotPassController.registerRecoveryQuestionComboBox1.getValue().isEmpty());
        loginRegisterForgotPassController.labelFillUpThisForm2.setVisible(loginRegisterForgotPassController.textFieldRecoveryQuestionAnswer1.getText().isEmpty());
        loginRegisterForgotPassController.labelFillUpThisForm3.setVisible(loginRegisterForgotPassController.registerRecoveryQuestionComboBox2.getValue().isEmpty());
        loginRegisterForgotPassController.labelFillUpThisForm4.setVisible(loginRegisterForgotPassController.textFieldRecoveryQuestionAnswer2.getText().isEmpty());

        loginRegisterForgotPassController.registerLastStepProceed = !loginRegisterForgotPassController.registerRecoveryQuestionComboBox1.getValue().isEmpty() &&
                !loginRegisterForgotPassController.textFieldRecoveryQuestionAnswer1.getText().isEmpty() &&
                !loginRegisterForgotPassController.registerRecoveryQuestionComboBox2.getValue().isEmpty() &&
                !loginRegisterForgotPassController.textFieldRecoveryQuestionAnswer2.getText().isEmpty();
    }

    /**
     * Password Indicator
     */

    public void passwordIndicator() {
        setAttributes();

        if (registerNewPassword.isBlank()) {
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
        return (registerNewPassword.length() < 8) ? 1 : -1;
    }

    private int checkFair() {
        if (registerNewPassword.length() < 8) return -1; // Password length is less than 8 characters

        int criteriaMet = 0;
        if (registerNewPassword.matches(".*[A-Z].*")) criteriaMet++;
        if (registerNewPassword.matches(".*\\d.*")) criteriaMet++;
        if (registerNewPassword.matches(".*[^A-Za-z0-9].*")) criteriaMet++;

        return (criteriaMet == 1) ? 2 : 3;
    }

    private int checkGood() {
        int criteriaMet = 0;
        if (registerNewPassword.length() >= 8) {
            criteriaMet++;
        }
        if (registerNewPassword.matches(".*[A-Z].*")) criteriaMet++;
        if (registerNewPassword.matches(".*\\d.*")) criteriaMet++;
        if (registerNewPassword.matches(".*[^A-Za-z0-9].*")) criteriaMet++;
        return criteriaMet;
    }

    public void emptyPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = true;
        loginRegisterForgotPassController.registerRectangle1.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(WHITE_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setVisible(false);
    }

    private void weakPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = true;
        loginRegisterForgotPassController.registerRectangle1.setFill(WEAK_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(WEAK_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(WEAK_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void fairPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = false;
        loginRegisterForgotPassController.registerRectangle1.setFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(FAIR_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(FAIR_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void goodPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = false;
        loginRegisterForgotPassController.registerRectangle1.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(WHITE_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(GOOD_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(GOOD_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    private void strongPassword() {
        loginRegisterForgotPassController.registerIsWeakPassword = false;
        loginRegisterForgotPassController.registerRectangle1.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle2.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle3.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerRectangle4.setFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setText(STRONG_ENUM.getText());
        loginRegisterForgotPassController.registerIndicator.setTextFill(STRONG_ENUM.getColor());
        loginRegisterForgotPassController.registerIndicator.setVisible(true);
    }

    /**
     * Check Validity
     */


    private void checkTextFields() {
        setAttributes();

        boolean allFieldsNotEmpty = notEmptyTextFields();
        boolean validEmailOrPhoneNumber = isValidEmailOrPhoneNumber();
        boolean passwordsMatch = passwordMatched();
        boolean notWeakPassword = !loginRegisterForgotPassController.registerIsWeakPassword;


        setAccountDetailsVisibilities(validEmailOrPhoneNumber, passwordsMatch);

        if (allFieldsNotEmpty && validEmailOrPhoneNumber && passwordsMatch && notWeakPassword) {
            switchPane(3);
        }
    }

    /**
     *
     *  ITO SA PHASE 4, IF VALID NA LAHAT, READTAC NA, TAPOS BAGANG REGISTER SUCCESS
     *
     */
    public void readTAC() {
        if (openTAC()) {
            if (loginRegisterForgotPassController.checkConnectivity()) {
                if (createAccount()) {
                    openPromptRegisteredSuccess();
                    goToLogin();
                }
            }
        }
    }

    private boolean createAccount() {
        
    }

    private boolean isNewPasswordAndConfirmNewPasswordNotMatched(boolean passwordsMatch) {
        return !registerNewPassword.isBlank() && !registerConfirmNewPassword.isBlank() && !passwordsMatch;
    }

    private boolean isValidEmailOrPhoneNumber() {
        return registerEmailOrPhoneNumber.matches(REGEX_EMAIL) || registerEmailOrPhoneNumber.matches(REGEX_PHONE_NUMBER);
    }

    private boolean isAPhoneNumber() {
        return registerEmailOrPhoneNumber.matches(REGEX_FIRST_SIX_ARE_NUMBERS);
    }

    private boolean nameFieldNotHaveSpaces() {
        return registerGivenName.length() == loginRegisterForgotPassController.textFieldName.getText().length();
    }

    private boolean notEmptyTextFields() {
        return !registerGivenName.isBlank() && !registerEmailOrPhoneNumber.isBlank() && !registerNewPassword.isBlank() && !registerConfirmNewPassword.isBlank();
    }

    private boolean exitAreFieldsEmpty() {
        return !loginRegisterForgotPassController.textFieldName.getText().isBlank() || !loginRegisterForgotPassController.textFieldMiddleName.getText().isBlank() || !loginRegisterForgotPassController.textFieldLastName.getText().isBlank();
    }

    private boolean passwordMatched() {
        return registerNewPassword.equals(registerConfirmNewPassword);
    }

    /**
     * Show or Hide Password
     */

    public void toggleNewPasswordField() {
        loginRegisterForgotPassController.registerShowNewPassword = !loginRegisterForgotPassController.registerShowNewPassword;

        loginRegisterForgotPassController.btnRegisterShowHidePassword1.setImage(loginRegisterForgotPassController.registerShowNewPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
        loginRegisterForgotPassController.textFieldNewPassword.setVisible(!loginRegisterForgotPassController.registerShowNewPassword);
        loginRegisterForgotPassController.textFieldShowNewPassword.setVisible(loginRegisterForgotPassController.registerShowNewPassword);

        if (loginRegisterForgotPassController.registerShowNewPassword) {
            loginRegisterForgotPassController.textFieldShowNewPassword.setText(loginRegisterForgotPassController.textFieldNewPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldNewPassword.setText(loginRegisterForgotPassController.textFieldShowNewPassword.getText());
        }

        loginRegisterForgotPassController.btnRegisterOnRegisterPane.requestFocus();
    }

    public void toggleConfirmNewPasswordField() {
        loginRegisterForgotPassController.registerShowConfirmNewPassword = !loginRegisterForgotPassController.registerShowConfirmNewPassword;

        loginRegisterForgotPassController.btnRegisterShowHidePassword2.setImage(loginRegisterForgotPassController.registerShowConfirmNewPassword ? loginRegisterForgotPassController.showImage : loginRegisterForgotPassController.hideImage);
        loginRegisterForgotPassController.textFieldConfirmNewPassword.setVisible(!loginRegisterForgotPassController.registerShowConfirmNewPassword);
        loginRegisterForgotPassController.textFieldShowConfirmNewPassword.setVisible(loginRegisterForgotPassController.registerShowConfirmNewPassword);

        if (loginRegisterForgotPassController.registerShowConfirmNewPassword) {
            loginRegisterForgotPassController.textFieldShowConfirmNewPassword.setText(loginRegisterForgotPassController.textFieldConfirmNewPassword.getText());
        } else {
            loginRegisterForgotPassController.textFieldConfirmNewPassword.setText(loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText());
        }

        loginRegisterForgotPassController.btnRegisterOnRegisterPane.requestFocus();
    }

    /**
     * Click Field Icons
     */

    public void selectGivenName() {
        loginRegisterForgotPassController.textFieldName.requestFocus();
        loginRegisterForgotPassController.textFieldName.positionCaret(loginRegisterForgotPassController.textFieldName.getText().length());
    }

    public void selectMiddleName() {
        loginRegisterForgotPassController.textFieldMiddleName.requestFocus();
        loginRegisterForgotPassController.textFieldMiddleName.positionCaret(loginRegisterForgotPassController.textFieldMiddleName.getText().length());
    }

    public void selectSurName() {
        loginRegisterForgotPassController.textFieldLastName.requestFocus();
        loginRegisterForgotPassController.textFieldLastName.positionCaret(loginRegisterForgotPassController.textFieldLastName.getText().length());
    }

    public void selectEmail() {
        loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.requestFocus();
        loginRegisterForgotPassController.textFieldEmailOrPhoneNumber.positionCaret(loginRegisterForgotPassController.textFieldName.getText().length());
    }

    public void selectNewPassword() {
        if (loginRegisterForgotPassController.registerShowNewPassword) {
            loginRegisterForgotPassController.textFieldShowNewPassword.requestFocus();
            loginRegisterForgotPassController.textFieldShowNewPassword.positionCaret(loginRegisterForgotPassController.textFieldShowNewPassword.getText().length());
        } else {
            loginRegisterForgotPassController.textFieldNewPassword.requestFocus();
            loginRegisterForgotPassController.textFieldNewPassword.positionCaret(loginRegisterForgotPassController.textFieldNewPassword.getText().length());
        }
    }

    public void selectConfirmNewPassword() {
        if (loginRegisterForgotPassController.registerShowConfirmNewPassword) {
            loginRegisterForgotPassController.textFieldShowConfirmNewPassword.requestFocus();
            loginRegisterForgotPassController.textFieldShowConfirmNewPassword.positionCaret(loginRegisterForgotPassController.textFieldShowConfirmNewPassword.getText().length());
        } else {
            loginRegisterForgotPassController.textFieldConfirmNewPassword.requestFocus();
            loginRegisterForgotPassController.textFieldConfirmNewPassword.positionCaret(loginRegisterForgotPassController.textFieldConfirmNewPassword.getText().length());
        }
    }

    /**
     * Redirecting to another scene
     * Pop-up windows
     */

    private boolean openPromptConfirmGoBack() {
        loginRegisterForgotPassController.toggleRectangleModal();

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

        loginRegisterForgotPassController.toggleRectangleModal();

        return isConfirmed;
    }

    private boolean openTAC() {
        loginRegisterForgotPassController.toggleRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(TERMS_AND_CONDITION_ENUM.getURL()));
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

        newStage.setTitle(TERMS_AND_CONDITION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();

        loginRegisterForgotPassController.toggleRectangleModal();

        return isConfirmed;
    }

    private void openPromptRegisteredSuccess() {
        loginRegisterForgotPassController.toggleRectangleModal();

        setAccountCreatedConfirmation();
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

        loginRegisterForgotPassController.toggleRectangleModal();
    }

    public void close() {
        setAttributes();
        setAttributesFirstStep();
        if (exitAreFieldsEmpty()) {
            if (openPromptConfirmGoBack()) {
                goToLogin();
            }
        } else {
            goToLogin();
        }
    }

    public void goToLogin() {
        loginRegisterForgotPassController.switchPane(StartPane.Login.getPaneNumber());
    }
}
