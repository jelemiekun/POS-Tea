package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.ProductOrder;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class OrderHistoryModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOrderHistoryTable() {
        setCellValueFactories();
        setReorderToFalse();
        setReverseItem();
    }

    public void setOrderHistory() {
        mainController.anchorPaneOrderHistory.requestFocus();
        refreshOrderHistoryTable();
        setTextFieldSearch();
    }

    private void setCellValueFactories() {
        mainController.tableViewOrderHistoryColCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        mainController.tableViewOrderHistoryColProductCategory.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder categories = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                categories.append(productOrder.getProductCategory());
                if (i < productOrders.size() - 1) {
                    categories.append(", ");
                }
            }
            return new SimpleStringProperty(categories.toString());
        });
        mainController.tableViewOrderHistoryColProductName.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                names.append(productOrder.getProductName());
                if (i < productOrders.size() - 1) {
                    names.append(", ");
                }
            }
            return new SimpleStringProperty(names.toString());
        });
        mainController.tableViewOrderHistoryColQuantity.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder quantities = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                quantities.append(productOrder.getQuantity()).append("x");
                if (i < productOrders.size() - 1) {
                    quantities.append(", ");
                }
            }
            return new SimpleStringProperty(quantities.toString());
        });
        mainController.tableViewOrderHistoryColPrice.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder prices = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                prices.append("₱ ").append(productOrder.getTotalAmount());
                if (i < productOrders.size() - 1) {
                    prices.append(", ");
                }
            }
            return new SimpleStringProperty(prices.toString());
        });
        mainController.tableViewOrderHistoryColTotalPrice.setCellValueFactory(cellData -> {
            String totalPrice = "₱ " + cellData.getValue().getTotalPrice();
            return new SimpleStringProperty(totalPrice);
        });
        mainController.tableViewOrderHistoryColAmountPaid.setCellValueFactory(cellData -> {
            String amountPaid = "₱ " + cellData.getValue().getAmountPaid();
            return new SimpleStringProperty(amountPaid);
        });
        mainController.tableViewOrderHistoryColChange.setCellValueFactory(cellData -> {
            String change = "₱ " + cellData.getValue().getChange();
            return new SimpleStringProperty(change);
        });
        mainController.tableViewOrderHistoryColModeOfPayment.setCellValueFactory(new PropertyValueFactory<>("modeOfPayment"));
        mainController.tableViewOrderHistoryColDateAndTime.setCellValueFactory(cellData -> {
            ObjectProperty<LocalDateTime> property = new SimpleObjectProperty<>(cellData.getValue().getDateAndTime());
            return property;
        });

        mainController.tableViewOrderHistoryColDateAndTime.setCellFactory(column -> {
            return new TableCell<Order, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd : HH:mm:ss");
                        setText(item.format(formatter));
                    }
                }
            };
        });
    }

    private void setReorderToFalse() {
        mainController.tableViewOrderHistoryColCustomerName.setReorderable(false);
        mainController.tableViewOrderHistoryColProductName.setReorderable(false);
        mainController.tableViewOrderHistoryColQuantity.setReorderable(false);
        mainController.tableViewOrderHistoryColPrice.setReorderable(false);
        mainController.tableViewOrderHistoryColTotalPrice.setReorderable(false);
        mainController.tableViewOrderHistoryColAmountPaid.setReorderable(false);
        mainController.tableViewOrderHistoryColChange.setReorderable(false);
        mainController.tableViewOrderHistoryColModeOfPayment.setReorderable(false);
        mainController.tableViewOrderHistoryColDateAndTime.setReorderable(false);
    }

    private void setReverseItem() {
        Collections.reverse(orderHistoryObservableList);
        mainController.tableViewOrderHistory.setItems(orderHistoryObservableList);
    }

    private void setTextFieldSearch() {
        mainController.textFieldOrderHistorySearch.setText("");

        mainController.textFieldOrderHistorySearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!orderHistoryObservableList.isEmpty()) {
                if (newValue.isEmpty())
                    setReverseItem();
                else
                    searchTheText(newValue);

                System.out.println(newValue);
            }
        });
    }

    private void searchTheText(String stringToSearch) {
        // Filter the order history based on the search string
        ObservableList<Order> filteredOrders = FXCollections.observableArrayList();
        for (Order order : orderHistoryObservableList) {
            // Check if the customer name or any product name contains the search string
            if (order.getCustomerName().toLowerCase().contains(stringToSearch.toLowerCase())) {
                filteredOrders.add(order);
            } else {
                for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                    if (productOrder.getProductName().toLowerCase().contains(stringToSearch.toLowerCase())) {
                        filteredOrders.add(order);
                        break; // Add the order only once
                    }
                }
            }
        }

        // Update the table view with the filtered data
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (filteredOrders.isEmpty()) {
                    // If no results found, set a placeholder message
                    mainController.tableViewOrderHistory.getItems().clear();
                    Label placeholderLabel = new Label("No matching records found.");
                    placeholderLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 28));
                    placeholderLabel.setAlignment(Pos.CENTER);
                    placeholderLabel.setContentDisplay(ContentDisplay.CENTER);
                    placeholderLabel.setTextAlignment(TextAlignment.CENTER);
                    mainController.tableViewOrderHistory.setPlaceholder(placeholderLabel);
                } else {
                    // Set the filtered data
                    mainController.tableViewOrderHistory.setItems(filteredOrders);
                    mainController.tableViewOrderHistory.refresh();
                    System.out.println(filteredOrders);
                }
            }
        });
    }




    private void refreshOrderHistoryTable() {
        Collections.reverse(orderHistoryObservableList);
        mainController.tableViewOrderHistory.refresh();

        if (orderHistoryObservableList.isEmpty()) {
            Label placeholderLabel = new Label("Your order history is empty.\nMake a new order to see transactions.");
            placeholderLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 28));
            placeholderLabel.setAlignment(Pos.CENTER);
            placeholderLabel.setContentDisplay(ContentDisplay.CENTER);
            placeholderLabel.setTextAlignment(TextAlignment.CENTER);
            mainController.tableViewOrderHistory.setPlaceholder(placeholderLabel);
        }
    }

    public void orderHistoryDeleteBin() {
        openDeleteHistoryFXML();
    }

    private void openDeleteHistoryFXML() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(DELETE_HISTORY.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();
        newStage.setTitle(DELETE_HISTORY.getTITLE());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        newStage.showAndWait();

    }
}
