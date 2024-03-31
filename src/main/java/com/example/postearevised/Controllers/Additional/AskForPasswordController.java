package com.example.postearevised.Controllers.Additional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.AskForPasswordHeaderTitlesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.SettingsReference.*;

public class AskForPasswordController implements Initializable {

    private int attempts = 0;

    @FXML
    private Label labelHeaderTitle;

    @FXML
    private Label labelIncorrect;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch (headerTitle) {
            case "Edit Users":
                labelHeaderTitle.setText(USERS_ENUM.getHeaderTitle());
                break;
            case "Edit Account Details":
                labelHeaderTitle.setText(ACCOUNT_DETAILS_ENUM.getHeaderTitle());
                break;
            case "Edit Questions":
                labelHeaderTitle.setText(RECOVERY_QUESTIONS_ENUM.getHeaderTitle());
                break;
            case "Delete Account":
                labelHeaderTitle.setText(DELETE_ACCOUNT_ENUM.getHeaderTitle());
                break;
        }
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

    private void check() {
        if (passwordField.getText().equals(accountReference.getPassword())) {
            accountEditingProceed = true;
            closeThisStage();
        } else {
            passwordField.setText("");
            labelIncorrect.setVisible(true);
        }
        attempts++;

        if (attempts == 5) {
            maxAttemptLimitReached = true;
            setErrorChangingPasswordMaximumAttemptReached();
            openPromptMaximumAttemptReached();
            closeThisStage();
        }
    }

    private void openPromptMaximumAttemptReached() {
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
        newStage.initOwner(labelHeaderTitle.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) labelHeaderTitle.getScene().getWindow();
        thisStage.close();
    }
}
