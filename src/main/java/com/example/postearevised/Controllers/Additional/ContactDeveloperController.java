package com.example.postearevised.Controllers.Additional;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactDeveloperController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label labelEmailCopied;

    @FXML
    private Label labelPhoneCopied;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(this::anchorPaneRequestFocus);
    }

    @FXML
    void anchorPaneRequestFocus() {
        anchorPane.requestFocus();
    }

    @FXML
    void btnCopyEmail() {
        showLabel(true);
        copyToClipboard(true);
        anchorPaneRequestFocus();
    }

    @FXML
    void btnCopyPhone() {
        showLabel(false);
        copyToClipboard(false);
        anchorPaneRequestFocus();
    }

    private void showLabel(boolean isEmail) {
        (!isEmail ? labelEmailCopied : labelPhoneCopied).setVisible(false);
        (isEmail ? labelEmailCopied : labelPhoneCopied).setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> (isEmail ? labelEmailCopied : labelPhoneCopied).setVisible(false)));
        timeline.play();
    }

    private void copyToClipboard(boolean isEmail) {
        String textToCopy = isEmail ? textFieldEmail.getText().trim() : textFieldPhone.getText().trim();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(textToCopy);
        clipboard.setContent(content);
    }
}
