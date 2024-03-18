package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.DeleteHistoryModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteHistoryController implements Initializable {

    public DeleteHistoryModel deleteHistoryModel;
    @FXML
    public AnchorPane btnDeleteRecord;

    @FXML
    public FlowPane flowPaneYearlyRecords;

    @FXML
    public Label labelDeleteConfirmation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteHistoryModel = new DeleteHistoryModel();
        deleteHistoryModel.setDeleteHistoryController(this);
    }

    @FXML
    void btnDeleteRecordClicked(MouseEvent event) {

    }

    @FXML
    void btnDeleteRecordTouched(TouchEvent event) {

    }
}
