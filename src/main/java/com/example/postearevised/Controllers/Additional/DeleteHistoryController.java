package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.DeleteHistoryModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Others.PromptContents.setDeleteRecord;
import static com.example.postearevised.Miscellaneous.References.StageReference.deleteHistoryStage;
import static com.example.postearevised.Miscellaneous.References.StylesReference.cssUsing;

public class DeleteHistoryController implements Initializable {

    public DeleteHistoryModel deleteHistoryModel;
    public AnchorPane selectedAnchorPane = null;
    @FXML
    public AnchorPane btnDeleteRecord;

    @FXML
    public AnchorPane anchorPane;

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
        deleteHistoryModel.checkRecord();
        deleteHistoryModel.setEmptyLabel();
        Platform.runLater(() -> deleteHistoryStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssUsing)).toExternalForm()));
    }

    @FXML
    void btnDeleteRecordClickedTouched() {
        deleteHistoryModel.btnDeleteRecordOnAction();
    }
}
