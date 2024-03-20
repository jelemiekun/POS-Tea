package com.example.postearevised.Controllers.Additional;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;

public class TermsAndConditionController implements Initializable {
    private final Image acceptEnabled = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/postearevised/Medias/Buttons/TAC/accept enabled.png")));
    private boolean scrolledToTheBottom;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    private ImageView btnAccept;

    @FXML
    private ImageView btnDecline;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPane.vvalueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == scrollPane.getVmax()) {
                scrolledToTheBottom = true;
                btnAccept.setCursor(Cursor.HAND);
                btnAccept.setDisable(false);
                btnAccept.setImage(acceptEnabled);

            } else {
                btnAccept.setCursor(Cursor.DEFAULT);
                btnAccept.setDisable(true);
                btnAccept.setPickOnBounds(false);
            }
        });
    }

    @FXML
    void btnAcceptClicked() {
        if (scrolledToTheBottom) {
            isConfirmed = true;
            closeThisStage();
        }
    }

    @FXML
    void btnAcceptTouched() {
        if (scrolledToTheBottom) {
            isConfirmed = true;
            closeThisStage();
        }
    }

    @FXML
    void btnDeclineClicked( ) {
        isConfirmed = false;
        closeThisStage();
    }

    @FXML
    void btnDeclineTouched() {
        isConfirmed = false;
        closeThisStage();
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) btnAccept.getScene().getWindow();
        thisStage.close();
    }
}
