package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.DeleteHistoryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;

public class DeleteHistoryController implements Initializable {

    public DeleteHistoryModel deleteHistoryModel;
    public AnchorPane selectedAnchorPane = null;
    @FXML
    public AnchorPane btnDeleteRecord;

    @FXML
    public FlowPane flowPaneYearlyRecords;

    @FXML
    public Label labelDeleteConfirmation;

    @FXML
    public Label labelOrderHistoryEmpty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteHistoryModel = new DeleteHistoryModel();
        deleteHistoryModel.setDeleteHistoryController(this);
        deleteHistoryModel.setEmptyLabel();

        deleteHistoryModel.populateFlowPane();
    }

    @FXML
    void btnDeleteRecordClickedTouched() throws IOException {
        if (deleteHistoryModel.openPrompt()) {
            flowPaneYearlyRecords.getChildren().remove(selectedAnchorPane);
            deleteHistoryModel.setDeleteHide();
            labelOrderHistoryEmpty.setVisible(flowPaneYearlyRecords.getChildren().isEmpty());
        }
    }

}
