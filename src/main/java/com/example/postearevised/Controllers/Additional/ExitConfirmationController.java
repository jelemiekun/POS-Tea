package com.example.postearevised.Controllers.Additional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;

import static com.example.postearevised.Miscellaneous.Reference.isConfirmed;

public class ExitConfirmationController {

    @FXML
    private ImageView btnNo;

    @FXML
    private ImageView btnYes;

    @FXML
    private Label labelContent;

    @FXML
    private Label labelHeader;

    @FXML
    void btnNoClicked(MouseEvent event) {
        isConfirmed = false;
        closeThisStage();
    }

    @FXML
    void btnNoTouched(TouchEvent event) {
        isConfirmed = false;
        closeThisStage();
    }

    @FXML
    void btnYesClicked(MouseEvent event) {
        isConfirmed = true;
        closeThisStage();
    }

    @FXML
    void btnYesTouched(TouchEvent event) {
        isConfirmed = true;
        closeThisStage();
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) labelHeader.getScene().getWindow();
        thisStage.close();
    }
}
