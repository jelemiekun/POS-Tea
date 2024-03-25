package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.DeleteHistoryController;
import com.example.postearevised.Objects.Order.Order;
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
import java.time.LocalDate;
import java.util.*;

import static com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue.OrderHistoryAndOrderQueueCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class DeleteHistoryModel {
    private DeleteHistoryController deleteHistoryController;

    public void setDeleteHistoryController(DeleteHistoryController deleteHistoryController) {
        this.deleteHistoryController = deleteHistoryController;
    }

    public void setEmptyLabel() {
        deleteHistoryController.labelOrderHistoryEmpty.setVisible(orderHistoryObservableList.isEmpty());
        deleteHistoryController.labelOrderHistoryEmpty.requestFocus();
    }

    public void checkRecord() {
        deleteHistoryController.flowPaneYearlyRecords.getChildren().clear();

        if (orderHistoryObservableList.isEmpty()) {
            setEmptyLabel();
        } else {
            Map<Integer, ObservableList<Order>> yearOrderMap = new HashMap<>();
            for (Order order : orderHistoryObservableList) {
                int year = order.getDateAndTime().getYear();
                ObservableList<Order> yearOrders = yearOrderMap.getOrDefault(year, FXCollections.observableArrayList());
                yearOrders.add(order);
                yearOrderMap.put(year, yearOrders);
            }

            List<Map.Entry<Integer, ObservableList<Order>>> entries = new ArrayList<>(yearOrderMap.entrySet());
            Collections.reverse(entries);

            for (Map.Entry<Integer, ObservableList<Order>> entry : entries) {
                int year = entry.getKey();

                AnchorPane anchorPane = createAnchorPane(String.valueOf(year));
                deleteHistoryController.flowPaneYearlyRecords.getChildren().add(anchorPane);
            }
        }
    }

    public void deleteOrdersByYear(int year) {
        List<Order> ordersToRemove = new ArrayList<>();

        // Find orders with the specified year and add them to ordersToRemove list
        for (Order order : orderHistoryObservableList) {
            if (order.getDateAndTime().getYear() == year) {
                ordersToRemove.add(order);
            }
        }

        if (deleteOrdersInCSV(ordersToRemove, false)) {
            orderHistoryObservableList.removeAll(ordersToRemove);
        } else {
            setErrorDeleteRecord();
            openPrompt();
        }

        // Perform any additional operations after removing orders
        checkRecord();
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

        Label labelYear = new Label("Year ");
        labelYear.setFont(Font.font("Arial", 18));
        labelYear.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        labelYear.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
        AnchorPane.setLeftAnchor(labelYear, 424.0);
        AnchorPane.setTopAnchor(labelYear, 15.0);
        anchorPane.getChildren().add(labelYear);

        Label labelYearInteger = new Label(year);
        labelYearInteger.setFont(Font.font("Arial", 18));
        labelYearInteger.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        labelYearInteger.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
        AnchorPane.setLeftAnchor(labelYearInteger, 468.0);
        AnchorPane.setTopAnchor(labelYearInteger, 15.0);
        anchorPane.getChildren().add(labelYearInteger);


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
        selectedRectangle.setStrokeWidth(4);
        selectedRectangle.setStrokeType(StrokeType.INSIDE);
        deleteHistoryController.selectedAnchorPane = anchorPaneSelected;

        String currentYear = String.valueOf(LocalDate.now().getYear());

        if (year.equals(currentYear))
            setNotAvailableToDeleteVisible();
        else
            setDeleteVisible();
    }

    public boolean openPrompt() {
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
        deleteHistoryController.labelDeleteConfirmation.setText("WOULD YOU LIKE TO DELETE THIS YEAR RECORD?");
        deleteHistoryController.labelDeleteConfirmation.setVisible(true);
    }

    private void setNotAvailableToDeleteVisible() {
        deleteHistoryController.btnDeleteRecord.setVisible(false);
        deleteHistoryController.labelDeleteConfirmation.setText("CURRENT YEAR RECORD CANNOT BE DELETED.");
        deleteHistoryController.labelDeleteConfirmation.setVisible(true);
    }

    public void setDeleteHide() {
        deleteHistoryController.btnDeleteRecord.setVisible(false);
        deleteHistoryController.labelDeleteConfirmation.setVisible(false);
    }
}
