package com.example.postearevised.Controllers.Additional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.DisplayColorsEnum.LIGHT_ENUM;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.promptStage;
import static com.example.postearevised.Miscellaneous.References.StylesReference.cssUsing;

public class PromptLayoutController implements Initializable {

    @FXML
    private ImageView btnRight;

    @FXML
    private ImageView btnCenter;

    @FXML
    private ImageView btnLeft;

    @FXML
    private Label labelContent;

    @FXML
    private Label labelHeader;

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelHeader.setText(promptHeaderText);
        labelContent.setText(promptContentText);

        btnLeft.setVisible(promptBtnLeftVisible);
        btnCenter.setVisible(promptBtnCenterVisible);
        btnRight.setVisible(promptBtnRightVisible);

        btnLeft.setImage(btnLeftImageReference);
        btnCenter.setImage(btnCenterImageReference);
        btnRight.setImage(btnRightImageReference);

        imageView.setImage(iconImageReference);

        if (cssUsing == null)
            Platform.runLater(() -> {promptStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(LIGHT_ENUM.getCssURL())).toExternalForm());});
        else
            Platform.runLater(() -> {promptStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssUsing)).toExternalForm());});
    }

    @FXML
    void btnRightClicked() {
        isConfirmed = btnRightBoolean;
        closeThisStage();
    }

    @FXML
    void btnRightTouched() {
        isConfirmed = btnRightBoolean;
        closeThisStage();
    }

    @FXML
    void btnCenterClicked() {
        isConfirmed = btnCenterBoolean;
        closeThisStage();
    }

    @FXML
    void btnCenterTouched() {
        isConfirmed = btnCenterBoolean;
        closeThisStage();
    }

    @FXML
    void btnLeftClicked() {
        isConfirmed = btnLeftBoolean;
        closeThisStage();
    }

    @FXML
    void btnLeftTouched() {
        isConfirmed = btnLeftBoolean;
        closeThisStage();
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) labelHeader.getScene().getWindow();
        thisStage.close();
    }
}
