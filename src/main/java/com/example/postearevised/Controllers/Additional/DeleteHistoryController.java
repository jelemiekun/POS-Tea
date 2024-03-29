package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.DeleteHistoryModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Others.PromptContents.setDeleteRecord;

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
        deleteHistoryModel.checkRecord();
        deleteHistoryModel.setEmptyLabel();
    }

    @FXML
    void btnDeleteRecordClickedTouched() {
        if (selectedAnchorPane != null) {
            setDeleteRecord();
            if (deleteHistoryModel.openPrompt()) {
                String yearString = ((Label) selectedAnchorPane.getChildren().get(3)).getText();
                int year = Integer.parseInt(yearString);
                deleteHistoryModel.deleteOrdersByYear(year);
                flowPaneYearlyRecords.getChildren().remove(selectedAnchorPane);
                selectedAnchorPane = null;
                deleteHistoryModel.setDeleteHide();
                labelOrderHistoryEmpty.setVisible(flowPaneYearlyRecords.getChildren().isEmpty());
            }
        }
    }

}
