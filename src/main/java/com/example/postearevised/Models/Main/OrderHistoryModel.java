package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.References.OrderHistoryReference;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.ProductOrder;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderHistoryModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOrderHistoryTable() {
        mainController.tableViewOrderHistoryColCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        mainController.tableViewOrderHistoryColProductCategory.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder categories = new StringBuilder();
            for (ProductOrder productOrder : productOrders) {
                categories.append(productOrder.getProductCategory()).append(", ");
            }
            return new SimpleStringProperty(categories.toString());
        });

        mainController.tableViewOrderHistoryColProductName.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder names = new StringBuilder();
            for (ProductOrder productOrder : productOrders) {
                names.append(productOrder.getProductName()).append(", ");
            }
            return new SimpleStringProperty(names.toString());
        });

        mainController.tableViewOrderHistoryColQuantity.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder quantities = new StringBuilder();
            for (ProductOrder productOrder : productOrders) {
                quantities.append("x").append(productOrder.getQuantity()).append(", ");
            }
            return new SimpleStringProperty(quantities.toString());
        });

        mainController.tableViewOrderHistoryColPrice.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder prices = new StringBuilder();
            for (ProductOrder productOrder : productOrders) {
                prices.append(productOrder.getTotalAmount()).append(", ");
            }
            return new SimpleStringProperty(prices.toString());
        });



        mainController.tableViewOrderHistoryColTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        mainController.tableViewOrderHistoryColAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        mainController.tableViewOrderHistoryColChange.setCellValueFactory(new PropertyValueFactory<>("change"));
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

        synchronized (OrderHistoryReference.orderHistoryObservableList) {
            mainController.tableViewOrderHistory.setItems(OrderHistoryReference.orderHistoryObservableList);
        }
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
}
