package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.DeleteHistoryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.EXIT_CONFIRMATION_ENUM;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.isConfirmed;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setDeleteRecord;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.dropShadowColor;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.SYSTEM_LOGO;

public class DeleteHistoryModel {
    private DeleteHistoryController deleteHistoryController;

    public void setDeleteHistoryController(DeleteHistoryController deleteHistoryController) {
        this.deleteHistoryController = deleteHistoryController;
    }

    public void setEmptyLabel() {
        deleteHistoryController.labelOrderHistoryEmpty.setVisible(!deleteHistoryController.flowPaneYearlyRecords.getChildren().isEmpty());
    }

    public void populateFlowPane() {
        ObservableList<String> listYearObservableList = FXCollections.observableArrayList("2020", "2021", "2022", "2023", "2024");

        for (String string : listYearObservableList) {
            AnchorPane anchorPane = createAnchorPane(string);
            deleteHistoryController.flowPaneYearlyRecords.getChildren().add(anchorPane);
        }
    }

    private AnchorPane createAnchorPane(String year) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(660, 50);
        anchorPane.setCursor(Cursor.HAND);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(660);
        rectangle.setHeight(50);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        anchorPane.getChildren().add(rectangle);

        Label labelOrderHistory = new Label("Order History");
        labelOrderHistory.setFont(Font.font("Arial", 18));
        AnchorPane.setLeftAnchor(labelOrderHistory, 20.0);
        AnchorPane.setTopAnchor(labelOrderHistory, 15.0);
        anchorPane.getChildren().add(labelOrderHistory);

        Label labelYear = new Label("Year " + year);
        labelYear.setFont(Font.font("Arial", 18));
        labelYear.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        labelYear.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
        AnchorPane.setLeftAnchor(labelYear, 424.0);
        AnchorPane.setTopAnchor(labelYear, 15.0);
        anchorPane.getChildren().add(labelYear);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(dropShadowColor);
        anchorPane.setEffect(dropShadow);

        anchorPane.setOnMouseClicked(event -> orderHistoryClickedTouched(anchorPane, rectangle, year));
        anchorPane.setOnTouchReleased(event -> orderHistoryClickedTouched(anchorPane, rectangle, year));

        FlowPane.setMargin(anchorPane, new Insets(0, 0, 7, 0));

        return anchorPane;
    }

    void orderHistoryClickedTouched(AnchorPane anchorPaneSelected, Rectangle selectedRectangle, String year) {
        if (deleteHistoryController.selectedAnchorPane != null) {
            ((Rectangle) deleteHistoryController.selectedAnchorPane.getChildren().get(0)).setStroke(null);
        }
        selectedRectangle.setStroke(Color.BLACK);
        selectedRectangle.setStrokeWidth(2);
        selectedRectangle.setStrokeType(StrokeType.INSIDE);
        deleteHistoryController.selectedAnchorPane = anchorPaneSelected;

        System.out.println("Delete History Selected Year: " + year);

        setDeleteVisible();
    }

    public boolean openPrompt() {
        setDeleteRecord();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(deleteHistoryController.labelOrderHistoryEmpty.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }
    private void setDeleteVisible() {
        deleteHistoryController.btnDeleteRecord.setVisible(true);
        deleteHistoryController.labelDeleteConfirmation.setVisible(true);
    }

    public void setDeleteHide() {
        deleteHistoryController.btnDeleteRecord.setVisible(false);
        deleteHistoryController.labelDeleteConfirmation.setVisible(false);
    }
}
