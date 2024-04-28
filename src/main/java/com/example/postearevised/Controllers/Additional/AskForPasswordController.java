package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Objects.Account.Account;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Database.CSV.Accounts.AccountCSV.updateAccountToAccountCSV;
import static com.example.postearevised.Miscellaneous.Enums.AskForPasswordHeaderTitlesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.SettingsReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.*;
import static com.example.postearevised.Miscellaneous.References.StylesReference.*;

public class AskForPasswordController implements Initializable {

    private String userPassword = "";

    private int attempts = 0;

    @FXML
    private AnchorPane normalPane;

    @FXML
    private AnchorPane deletePane;

    @FXML
    private AnchorPane btnDonePassword;

    @FXML
    private Label labelPasswordAsk;

    @FXML
    private Label labelHeaderTitle;

    @FXML
    private Label labelIncorrect;

    @FXML
    private Label labelPassForThisUser;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            switch (headerTitle) {
                case "Edit Users":
                    labelHeaderTitle.setText(USERS_ENUM.getHeaderTitle());
                    normalPane();
                    break;
                case "Edit Account Details":
                    labelHeaderTitle.setText(ACCOUNT_DETAILS_ENUM.getHeaderTitle());
                    normalPane();
                    break;
                case "Edit Questions":
                    labelHeaderTitle.setText(RECOVERY_QUESTIONS_ENUM.getHeaderTitle());
                    normalPane();
                    break;
                case "Delete Account":
                    labelHeaderTitle.setText(DELETE_ACCOUNT_ENUM.getHeaderTitle());
                    deletePane();
                    break;
                case "User":
                    userPane();
                    break;
            }

            passwordField.setVisible(true);
            labelPasswordAsk.setVisible(true);

            askForPasswordStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssUsing)).toExternalForm());
        });
    }

    private void normalPane() {
        labelIncorrect.setLayoutX(82);
        labelIncorrect.setLayoutY(160);
        normalPane.setVisible(true);
        deletePane.setVisible(false);
    }

    private void deletePane() {
        labelIncorrect.setLayoutX(136);
        labelIncorrect.setLayoutY(194);
        normalPane.setVisible(false);
        deletePane.setVisible(true);
    }

    private void userPane() {
        if (registerAdminPassword) {
            labelHeaderTitle.setText("Admin registration");
            labelPassForThisUser.setText("Register a password for this admin user.");
        } else {
            labelHeaderTitle.setText(USERS_SELECTION_ENUM.getHeaderTitle());
            labelPassForThisUser.setText("Enter password for this user.");
        }

        labelPassForThisUser.setVisible(true);
        labelIncorrect.setText("Password for this user must not be blank!");
        btnDonePassword.setVisible(true);

        if (accountReference.getUserPasswords() != null)
            userPassword = accountReference.getUserPasswords().get(userIndex);
    }

    @FXML
    void btnDoneClickedTouched() {
        check();
    }

    @FXML
    void btnDonePressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            check();
    }

    @FXML
    void btnCancelEntered(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btnCancelClickedTouched();
    }

    @FXML
    void btnCancelClickedTouched() {
        accountEditingProceed = false;
        closeThisStage();
    }

    @FXML
    void btnDonePasswordPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            check();
    }

    @FXML
    void btnDonePasswordClickedTouched() {
        check();
    }

    private boolean isUpdatingPasswordSuccess() {
        Account oldAccount = accountReference.copy();

        String passwordFieldText = passwordField.getText().trim();

        if (!(accountReference.getUserPasswords() == null)) {
            accountReference.getUserPasswords().add(passwordFieldText);
        } else {
            ObservableList<String> password = FXCollections.observableArrayList();
            password.add(passwordFieldText);
            accountReference.setUserPasswords(password);
        }

        return updateAccountToAccountCSV(oldAccount, accountReference);
    }

    private void check() {
        if (!headerTitle.equals("Delete Account")) {
            boolean isSwitching = true;

            if (!labelIncorrect.getText().equals("Password for this user must not be blank!")) {
                if (!labelIncorrect.getText().equals("Incorrect password!")) {
                    if (attempts == MAXIMUM_ATTEMPTS_FOR_CRITICAL_INPUTS) {
                        userSelectedSuccess = false;
                        maxAttemptLimitReached = true;
                        setErrorChangingPasswordMaximumAttemptReached();
                        openPrompt();
                        closeThisStage();
                    } else {
                        if (passwordField.getText().equals(accountReference.getPassword())) {
                            accountEditingProceed = true;
                            closeThisStage();
                        } else {
                            passwordField.setText("");
                            labelIncorrect.setVisible(true);
                        }
                        attempts++;

                    }

                    isSwitching = false;
                }
            }

            if (isSwitching) {
                if (!isInputPasswordExistingUser) {
                    if (!passwordField.getText().isEmpty()) {
                        setUserConfirmPassword();
                        if ((openPrompt())) {
                            if (!isUpdatingPasswordSuccess()) {
                                setErrorFailedToUpdateAccountToCSV();
                                openPrompt();
                            } else {
                                accountEditingProceed = true;
                                closeThisStage();
                            }
                        }
                    } else {
                        labelIncorrect.setVisible(true);
                    }
                } else {
                    userPane();
                    if (attempts == MAXIMUM_ATTEMPTS_FOR_CRITICAL_INPUTS) {
                        userSelectedSuccess = false;
                        maxAttemptLimitReached = true;
                        setErrorChangingPasswordMaximumAttemptReached();
                        openPrompt();
                        closeThisStage();
                    } else {
                        if (passwordField.getText().equals(userPassword)) {
                            accountEditingProceed = true;
                            userSelectedSuccess = true;
                            closeThisStage();
                        } else {
                            labelIncorrect.setLayoutY(148);
                            labelIncorrect.setText("Incorrect password!");
                            labelIncorrect.setVisible(true);
                        }

                        attempts++;
                    }
                }
            }
        } else {
            if (attempts == MAXIMUM_ATTEMPTS_FOR_CRITICAL_INPUTS) {
                userSelectedSuccess = false;
                maxAttemptLimitReached = true;
                setErrorChangingPasswordMaximumAttemptReached();
                openPrompt();
                closeThisStage();
            } else {
                if (passwordField.getText().equals(accountReference.getPassword())) {
                    accountEditingProceed = true;
                    closeThisStage();
                } else {
                    passwordField.setText("");
                    labelIncorrect.setVisible(true);
                }
                attempts++;
            }
        }

        passwordField.setText("");
    }

    private boolean openPrompt() {
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
        promptStage.initOwner(labelHeaderTitle.getScene().getWindow());

        promptStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        promptStage.setResizable(false);
        promptStage.getIcons().add(SYSTEM_LOGO);
        promptStage.setScene(new Scene(root));
        promptStage.showAndWait();

        return isConfirmed;
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) labelHeaderTitle.getScene().getWindow();
        thisStage.close();
    }
}
