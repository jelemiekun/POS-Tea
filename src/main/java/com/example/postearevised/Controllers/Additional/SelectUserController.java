package com.example.postearevised.Controllers.Additional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.References.AccountReference.*;

public class SelectUserController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> comboBoxUsers;

    @FXML
    void comboBoxUsersOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullNames.clear();

        for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
            String firstName = accountReference.getFirstNames().get(i);
            String middleName = accountReference.getMiddleNames().get(i).isEmpty() || accountReference.getMiddleNames().get(i).equals(".") ? "" : accountReference.getMiddleNames().get(i);
            String lastName = accountReference.getLastNames().get(i);

            String isDefault = i == 0 ? " (Default)" : "";
            fullNames.add(firstName + " " + middleName + " " + lastName + isDefault);

            comboBoxUsers.setItems(fullNames);
        }

        Platform.runLater(this::anchorPaneRequestFocus);
    }

    @FXML
    void anchorPaneRequestFocus() {
        anchorPane.requestFocus();;
    }
}
