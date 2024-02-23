package com.example.postearevised.Controllers.Additional;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;

import static com.example.postearevised.Miscellaneous.Prompt.*;

public class TermsAndConditionController {

    @FXML
    private ImageView btnAccept;

    @FXML
    private ImageView btnDecline;

    @FXML
    void btnAcceptClicked(MouseEvent event) {
        isConfirmed = true;
        closeThisStage();
    }

    @FXML
    void btnAcceptTouched(TouchEvent event) {
        isConfirmed = true;
        closeThisStage();
    }

    @FXML
    void btnDeclineClicked(MouseEvent event) {
        isConfirmed = false;
        closeThisStage();
    }

    @FXML
    void btnDeclineTouched(TouchEvent event) {
        isConfirmed = false;
        closeThisStage();
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) btnAccept.getScene().getWindow();
        thisStage.close();
    }
}
