package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.OrderDetailsController;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class OrderDetailsModel {
    private OrderDetailsController orderDetailsController;

    public void setOrderDetailsController(OrderDetailsController orderDetailsController) {
        this.orderDetailsController = orderDetailsController;
    }

    public void setTable() {
        orderDetailsController.tableViewRecordDetails.setItems(orderDetailsController.selectedOrder);
        orderDetailsController.tableViewRecordDetailsFoodCategories.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder categories = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                categories.append(productOrder.getProductCategory());

                if (!productOrder.getThirdAttribute().isEmpty() && !productOrder.getThirdAttribute().equals(".")) {
                    categories.append("\n");
                }
                if (!productOrder.getFirstAttribute().isEmpty() && !productOrder.getFirstAttribute().equals(".")) {
                    categories.append("\n");
                }
                if (!productOrder.getSecondAttribute().isEmpty() && !productOrder.getSecondAttribute().equals(".")) {
                    categories.append("\n");
                }

                if (i < productOrders.size() - 1) {
                    categories.append("\n");
                }
            }
            return new SimpleStringProperty(categories.toString());
        });
        orderDetailsController.tableViewRecordDetailsProductDetails.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                names.append(productOrder.getProductName());

                if (!productOrder.getThirdAttribute().isEmpty() && !productOrder.getThirdAttribute().equals(".")) {
                    names.append("\n    - ").append(productOrder.getThirdAttribute());
                }
                if (!productOrder.getFirstAttribute().isEmpty() && !productOrder.getFirstAttribute().equals(".")) {
                    names.append("\n    - ").append(productOrder.getFirstAttribute());
                }
                if (!productOrder.getSecondAttribute().isEmpty() && !productOrder.getSecondAttribute().equals(".")) {
                    names.append("\n    - ").append(productOrder.getSecondAttribute());
                }

                if (i < productOrders.size() - 1) {
                    names.append("\n");
                }
            }
            return new SimpleStringProperty(names.toString());
        });
        orderDetailsController.tableViewRecordDetailsQuantity.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder quantities = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                quantities.append(productOrder.getQuantity()).append("x");

                if (!productOrder.getThirdAttribute().isEmpty() && !productOrder.getThirdAttribute().equals(".")) {
                    quantities.append("\n");
                }
                if (!productOrder.getFirstAttribute().isEmpty() && !productOrder.getFirstAttribute().equals(".")) {
                    quantities.append("\n");
                }
                if (!productOrder.getSecondAttribute().isEmpty() && !productOrder.getSecondAttribute().equals(".")) {
                    quantities.append("\n");
                }

                if (i < productOrders.size() - 1) {
                    quantities.append("\n");
                }
            }
            return new SimpleStringProperty(quantities.toString());
        });
        orderDetailsController.tableViewRecordDetailsProductPrice.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder prices = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                prices.append("₱ ").append(productOrder.getTotalAmount());

                if (!productOrder.getThirdAttribute().isEmpty() && !productOrder.getThirdAttribute().equals(".")) {
                    prices.append("\n");
                }
                if (!productOrder.getFirstAttribute().isEmpty() && !productOrder.getFirstAttribute().equals(".")) {
                    prices.append("\n");
                }
                if (!productOrder.getSecondAttribute().isEmpty() && !productOrder.getSecondAttribute().equals(".")) {
                    prices.append("\n");
                }

                if (i < productOrders.size() - 1) {
                    prices.append("\n");
                }
            }
            return new SimpleStringProperty(prices.toString());
        });
        orderDetailsController.tableViewRecordDetailsTotalAmount.setCellValueFactory(cellData -> {
            String totalPrice = "₱ " + cellData.getValue().getTotalPrice();
            return new SimpleStringProperty(totalPrice);
        });
        orderDetailsController.tableViewRecordDetailsAmountPaid.setCellValueFactory(cellData -> {
            String amountPaid = "₱ " + cellData.getValue().getAmountPaid();
            return new SimpleStringProperty(amountPaid);
        });
        orderDetailsController.tableViewRecordDetailsChange.setCellValueFactory(cellData -> {
            String change = "₱ " + cellData.getValue().getChange();
            return new SimpleStringProperty(change);
        });
        orderDetailsController.tableViewRecordDetails.setSelectionModel(null);
    }

    public void setHeader() {
        orderDetailsController.labelOrderNumber.setText(String.valueOf(selectedOrderDetails.getOrderNumber()));
        orderDetailsController.labelOrderCustomerName.setText(selectedOrderDetails.getCustomerName());

        LocalDateTime dateTime = selectedOrderDetails.getDateAndTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy | hh:mm:ss a");
        String formattedDateTime = dateTime.format(formatter);
        orderDetailsController.labelOrderDateAndTime.setText(formattedDateTime);

        orderDetailsController.labelOrderModeOfPayment.setText(selectedOrderDetails.getModeOfPayment());
    }

    public void requestFocusOnMainAnchorPane() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                orderDetailsController.anchorPaneMain.requestFocus();
                orderDetailsController.tableViewRecordDetails.getSelectionModel().clearSelection();
            }
        });
    }
}
