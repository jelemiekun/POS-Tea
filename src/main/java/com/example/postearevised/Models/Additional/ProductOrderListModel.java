package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.ProductOrderListController;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProductOrderListModel {
    private ProductOrderListController productOrderListController;

    public void setProductOrderListController(ProductOrderListController productOrderListController) {
        this.productOrderListController = productOrderListController;
    }

    public void setContentsInvoke() {
        setHeader();
        setFlowPaneProductOrders();
    }

    private void setHeader() {
        productOrderListController.labelCustomerNumber.setText("Order no. " + productOrderListController.order.getOrderNumber());
        productOrderListController.labelCustomerName.setText("Customer Name: " + productOrderListController.order.getCustomerName());
    }

    private void setFlowPaneProductOrders() {
        for (ProductOrder productOrder : productOrderListController.order.getProductOrderObservableList()) {
            AnchorPane productOrderAnchorPane = new AnchorPane();
            productOrderAnchorPane.setPrefSize(826, 46);

            Label productNameLabel = new Label(productOrder.getProductName());
            productNameLabel.setFont(new Font("Arial", 20));
            productNameLabel.setAlignment(Pos.CENTER_LEFT);
            AnchorPane.setLeftAnchor(productNameLabel, 90.0);
            AnchorPane.setTopAnchor(productNameLabel, 13.0);

            Label quantityLabel = new Label(productOrder.getQuantity() + "x");
            quantityLabel.setFont(new Font("Arial", 20));
            quantityLabel.setAlignment(Pos.CENTER_RIGHT);
            quantityLabel.setContentDisplay(ContentDisplay.RIGHT);
            AnchorPane.setRightAnchor(quantityLabel, 85.0);
            AnchorPane.setTopAnchor(quantityLabel, 13.0);

            productOrderAnchorPane.getChildren().addAll(productNameLabel, quantityLabel);

            int attributeCount = 0;

            if (!productOrder.getThirdAttribute().isEmpty() && !productOrder.getThirdAttribute().equals(".")) {
                Label firstAttributeLabel = new Label("- " + productOrder.getThirdAttribute());
                firstAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(firstAttributeLabel, 270.0);
                AnchorPane.setTopAnchor(firstAttributeLabel, 13.0 + (attributeCount * 30));
                productOrderAnchorPane.getChildren().add(firstAttributeLabel);
                attributeCount++;
            }

            if (!productOrder.getFirstAttribute().isEmpty() && !productOrder.getFirstAttribute().equals(".")) {
                Label secondAttributeLabel = new Label("- " + productOrder.getFirstAttribute());
                secondAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(secondAttributeLabel, 270.0);
                AnchorPane.setTopAnchor(secondAttributeLabel, 13.0 + (attributeCount * 30));
                productOrderAnchorPane.getChildren().add(secondAttributeLabel);
                attributeCount++;
            }

            if (!productOrder.getSecondAttribute().isEmpty() && !productOrder.getSecondAttribute().equals(".")) {
                Label thirdAttributeLabel = new Label("- " + productOrder.getSecondAttribute());
                thirdAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(thirdAttributeLabel, 270.0);
                AnchorPane.setTopAnchor(thirdAttributeLabel, 13.0 + (attributeCount * 30));
                productOrderAnchorPane.getChildren().add(thirdAttributeLabel);
                attributeCount++;
            }

            productOrderAnchorPane.setPrefHeight(46 + (attributeCount * 30));

            productOrderListController.flowPaneProductOrders.getChildren().add(productOrderAnchorPane);
        }
    }

    public void closeThisStage() {
        Stage thisStage = (Stage) productOrderListController.flowPaneProductOrders.getScene().getWindow();
        thisStage.close();
    }
}
