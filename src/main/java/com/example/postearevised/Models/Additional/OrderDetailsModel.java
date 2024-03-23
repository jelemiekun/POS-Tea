package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.OrderDetailsController;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class OrderDetailsModel {
    private OrderDetailsController orderDetailsController;

    public void setOrderDetailsController(OrderDetailsController orderDetailsController) {
        this.orderDetailsController = orderDetailsController;
    }

    public void setTable() {
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

//                if (!productOrder.getThirdAttribute().isEmpty() && !productOrder.getThirdAttribute().equals(".")) {
//                    quantities.append("\n");
//                }
//                if (!productOrder.getFirstAttribute().isEmpty() && !productOrder.getFirstAttribute().equals(".")) {
//                    quantities.append("\n");
//                }
//                if (!productOrder.getSecondAttribute().isEmpty() && !productOrder.getSecondAttribute().equals(".")) {
//                    quantities.append("\n");
//                }

                if (i < productOrders.size() - 1) {
                    quantities.append("\n");
                }
            }
            return new SimpleStringProperty(quantities.toString());
        });
        orderDetailsController.tableViewRecordDetailsTotalAmount.setCellValueFactory(cellData -> {
            String totalPrice = "₱ " + cellData.getValue().getTotalPrice();
            return new SimpleStringProperty(totalPrice);
        });
    }
}
