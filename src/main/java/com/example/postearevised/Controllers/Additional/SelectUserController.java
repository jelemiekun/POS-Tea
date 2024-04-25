package com.example.postearevised.Controllers.Additional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.AskForPasswordHeaderTitlesEnum.USERS_SELECTION_ENUM;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.ASK_FOR_PASSWORD;
import static com.example.postearevised.Miscellaneous.Others.LogFile.errorMessage;
import static com.example.postearevised.Miscellaneous.Others.LogFile.logError;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.SYSTEM_LOGO;
import static com.example.postearevised.Miscellaneous.References.SettingsReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.askForPasswordStage;

public class SelectUserController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> comboBoxUsers;

    @FXML
    void comboBoxUsersOnAction() {
        userIndex = comboBoxUsers.getSelectionModel().getSelectedIndex();
        headerTitle = USERS_SELECTION_ENUM.getHeaderTitle();
        isInputPasswordExistingUser = true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ASK_FOR_PASSWORD.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        askForPasswordStage = new Stage();

        askForPasswordStage.initModality(Modality.WINDOW_MODAL);
        askForPasswordStage.initOwner(anchorPane.getScene().getWindow());

        askForPasswordStage.setTitle("Input Password");
        askForPasswordStage.setResizable(false);
        askForPasswordStage.getIcons().add(SYSTEM_LOGO);
        askForPasswordStage.setScene(new Scene(root));
        askForPasswordStage.showAndWait();

        if (userSelectedSuccess)
            closeThisStage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullNames.clear();

        for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
            String firstName = accountReference.getFirstNames().get(i);
            String middleName = accountReference.getMiddleNames().get(i).isEmpty() || accountReference.getMiddleNames().get(i).equals(".") ? "" : accountReference.getMiddleNames().get(i);
            String lastName = accountReference.getLastNames().get(i);

            String isDefault = i == 0 ? " (Admin)" : "";
            fullNames.add(firstName + " " + middleName + " " + lastName + isDefault);

            comboBoxUsers.setItems(fullNames);
        }

        Platform.runLater(this::anchorPaneRequestFocus);
    }

    @FXML
    void anchorPaneRequestFocus() {
        anchorPane.requestFocus();
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) anchorPane.getScene().getWindow();
        thisStage.close();
    }
}
