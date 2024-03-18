package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Main.OrderListModel;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.ProductOrder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductOrderListController implements Initializable {
    private Order order;

    @FXML
    private FlowPane flowPaneProductOrders;

    @FXML
    private Label labelCustomerName;

    @FXML
    private Label labelCustomerNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrderListModel orderListModel = new OrderListModel();
        orderListModel.setProductOrderListController(this);
    }

    public void setContents() {
        setHeader();
        setFlowPaneProductOrders();
    }

    private void setHeader() {
        labelCustomerNumber.setText("Order no. " + order.getOrderNumber());
        labelCustomerName.setText("Customer Name: " + order.getCustomerName());
    }

    private void setFlowPaneProductOrders() {
        for (ProductOrder productOrder : order.getProductOrderObservableList()) {
            AnchorPane productOrderAnchorPane = new AnchorPane();
            productOrderAnchorPane.setPrefSize(826, 46);

            Label productNameLabel = new Label(productOrder.getProductName());
            productNameLabel.setFont(new Font("Arial", 20));
            productNameLabel.setAlignment(Pos.CENTER_LEFT);
            AnchorPane.setLeftAnchor(productNameLabel, 90.0);
            AnchorPane.setTopAnchor(productNameLabel, 13.0);

            Label quantityLabel = new Label(String.valueOf(productOrder.getQuantity()) + "x");
            quantityLabel.setFont(new Font("Arial", 20));
            quantityLabel.setAlignment(Pos.CENTER_RIGHT);
            quantityLabel.setContentDisplay(ContentDisplay.RIGHT);
            AnchorPane.setRightAnchor(quantityLabel, 85.0);
            AnchorPane.setTopAnchor(quantityLabel, 13.0);

            productOrderAnchorPane.getChildren().addAll(productNameLabel, quantityLabel);

            int attributeCount = 0;

            if (!productOrder.getFirstAttribute().isEmpty()) {
                Label firstAttributeLabel = new Label("- " + productOrder.getFirstAttribute());
                firstAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(firstAttributeLabel, 230.0);
                AnchorPane.setTopAnchor(firstAttributeLabel, 13.0 + (attributeCount * 30));
                productOrderAnchorPane.getChildren().add(firstAttributeLabel);
                attributeCount++;
            }

            if (!productOrder.getSecondAttribute().isEmpty()) {
                Label secondAttributeLabel = new Label("- " + productOrder.getSecondAttribute());
                secondAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(secondAttributeLabel, 230.0);
                AnchorPane.setTopAnchor(secondAttributeLabel, 13.0 + (attributeCount * 30));
                productOrderAnchorPane.getChildren().add(secondAttributeLabel);
                attributeCount++;
            }

            if (!productOrder.getThirdAttribute().isEmpty()) {
                Label thirdAttributeLabel = new Label("- " + productOrder.getThirdAttribute());
                thirdAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(thirdAttributeLabel, 230.0);
                AnchorPane.setTopAnchor(thirdAttributeLabel, 13.0 + (attributeCount * 30));
                productOrderAnchorPane.getChildren().add(thirdAttributeLabel);
                attributeCount++;
            }

            // Adjust AnchorPane height based on the number of attributes
            productOrderAnchorPane.setPrefHeight(46 + (attributeCount * 30));

            // Add the product order AnchorPane to the FlowPane
            flowPaneProductOrders.getChildren().add(productOrderAnchorPane);
        }
    }





    public void setOrderAndAnchorPane(Order order) {
        this.order = order;
    }

    @FXML
    void btnOrderDone() {
        orderDone = true;
        closeThisStage();
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) flowPaneProductOrders.getScene().getWindow();
        thisStage.close();
    }
}
