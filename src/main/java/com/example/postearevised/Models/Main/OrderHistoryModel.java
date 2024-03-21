package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
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
import javafx.scene.control.TableColumn;
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

import static com.example.postearevised.Miscellaneous.Enums.OrderHistorySortEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

public class OrderHistoryModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOrderHistoryTable() {
        setComboBox(true);
        setCellValueFactories();
        setReorderToFalse();
        setReverseItem();
    }

    public void setOrderHistory() {
        setComboBox(false);
        mainController.anchorPaneOrderHistory.requestFocus();
        refreshOrderHistoryTable();
        setTextFieldSearch();
    }

    private void setComboBox(boolean initialized) {
        if (initialized)
            mainController.comboBoxOrderHistory.getItems().addAll(orderHistorySortByChoices);

        mainController.comboBoxOrderHistory.setValue(ALL_TIME_ENUM.getTitle());
        getComboBoxValue();
    }

    public void getComboBoxValue() {
        System.out.println(mainController.comboBoxOrderHistory.getValue());
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
                    categories.append("\n");
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
                    names.append("\n");
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
                    quantities.append("\n");
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
                    prices.append("\n");
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
        mainController.tableViewOrderHistoryColDateAndTime.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateAndTime()));

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
        mainController.textFieldOrderHistorySearch.setText("");
        mainController.tableViewOrderHistory.setItems(orderHistoryObservableList);
        mainController.tableViewOrderHistory.refresh();
        mainController.anchorPaneOrderHistory.requestFocus();
        mainController.tableViewOrderHistory.getSortOrder().clear();
        mainController.tableViewOrderHistory.getSortOrder().add(mainController.tableViewOrderHistoryColDateAndTime);
        mainController.tableViewOrderHistoryColDateAndTime.setSortType(TableColumn.SortType.DESCENDING);
        mainController.tableViewOrderHistory.sort();
    }

    public void setTextFieldSearch() {
        mainController.textFieldOrderHistorySearch.setText("");

        mainController.textFieldOrderHistorySearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY))
                mainController.textFieldOrderHistorySearch.setText(oldValue);
            else
                searchTheText(newValue);
        });
    }

    private void searchTheText(String stringToSearch) {
        if (!orderHistoryObservableList.isEmpty()) {
            ObservableList<Order> filteredOrders = FXCollections.observableArrayList();
            for (Order order : orderHistoryObservableList) {
                if (order.getCustomerName().toLowerCase().contains(stringToSearch.toLowerCase())) {
                    filteredOrders.add(order);
                } else {
                    for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                        if (productOrder.getProductName().toLowerCase().contains(stringToSearch.toLowerCase())) {
                            filteredOrders.add(order);
                            break;
                        }
                    }
                }
            }

            Platform.runLater(() -> {
                if (filteredOrders.isEmpty()) {
                    mainController.tableViewOrderHistory.setItems(FXCollections.observableArrayList());
                    Label placeholderLabel = new Label("No matching records found.");
                    placeholderLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 28));
                    placeholderLabel.setAlignment(Pos.CENTER);
                    placeholderLabel.setContentDisplay(ContentDisplay.CENTER);
                    placeholderLabel.setTextAlignment(TextAlignment.CENTER);
                    mainController.tableViewOrderHistory.setPlaceholder(placeholderLabel);
                } else {
                    mainController.tableViewOrderHistory.setItems(filteredOrders);
                }
            });
        }
    }





    public void refreshOrderHistoryTable() {
        if (orderHistoryObservableList.isEmpty()) {
            Label placeholderLabel = new Label("Your order history is empty.\nMake a new order to see transactions.");
            placeholderLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 28));
            placeholderLabel.setAlignment(Pos.CENTER);
            placeholderLabel.setContentDisplay(ContentDisplay.CENTER);
            placeholderLabel.setTextAlignment(TextAlignment.CENTER);
            mainController.tableViewOrderHistory.setPlaceholder(placeholderLabel);
        }

        //setReverseItem(); wag to i uncomment, mag w-wierd as fuck
    }

    public void orderHistoryDeleteBin() {
        openDeleteHistoryFXML();
    }

    private void openDeleteHistoryFXML() {
        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(DELETE_HISTORY.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }

        Stage newStage = new Stage();
        newStage.setTitle(DELETE_HISTORY.getTITLE());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        newStage.showAndWait();
        mainController.mainModel.hideRectangleModal();
    }
}
