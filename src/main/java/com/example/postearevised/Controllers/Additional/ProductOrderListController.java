package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Main.OrderListModel;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.ProductOrder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductOrderListController implements Initializable {
    private OrderListModel orderListModel;
    private Order order;

    @FXML
    private FlowPane flowPaneProductOrders;

    @FXML
    private Label labelCustomerName;

    @FXML
    private Label labelCustomerNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderListModel = new OrderListModel();
        orderListModel.setProductOrderListController(this);
    }

    public void setContents() {
        setHeader();
        setFlowPaneProductOrders();
    }

    private void setHeader() {
        labelCustomerName.setText("Order no. " + order.getOrderNumber());
        labelCustomerName.setText("Customer Name: " + order.getCustomerName());
    }

    private void setFlowPaneProductOrders() {
        for (ProductOrder productOrder : order.getProductOrderObservableList()) {
            AnchorPane productOrderAnchorPane = new AnchorPane();
            productOrderAnchorPane.setPrefSize(826, 46);

            Label productNameLabel = new Label(productOrder.getProductName());
            productNameLabel.setFont(new Font("Arial", 18));
            AnchorPane.setLeftAnchor(productNameLabel, 71.0);
            AnchorPane.setTopAnchor(productNameLabel, 13.0);

            if (!productOrder.getFirstAttribute().isEmpty()) {
                Label firstAttributeLabel = new Label(productOrder.getFirstAttribute());
                firstAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(firstAttributeLabel, 200.0);
                AnchorPane.setTopAnchor(firstAttributeLabel, 13.0);
                productOrderAnchorPane.getChildren().add(firstAttributeLabel);
            }

            if (!productOrder.getSecondAttribute().isEmpty()) {
                Label secondAttributeLabel = new Label(productOrder.getSecondAttribute());
                secondAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(secondAttributeLabel, 400.0);
                AnchorPane.setTopAnchor(secondAttributeLabel, 13.0);
                productOrderAnchorPane.getChildren().add(secondAttributeLabel);
            }

            if (!productOrder.getThirdAttribute().isEmpty()) {
                Label thirdAttributeLabel = new Label(productOrder.getThirdAttribute());
                thirdAttributeLabel.setFont(new Font("Arial", 18));
                AnchorPane.setLeftAnchor(thirdAttributeLabel, 600.0);
                AnchorPane.setTopAnchor(thirdAttributeLabel, 13.0);
                productOrderAnchorPane.getChildren().add(thirdAttributeLabel);
            }

            Label quantityLabel = new Label(String.valueOf(productOrder.getQuantity()));
            quantityLabel.setFont(new Font("Arial", 18));
            quantityLabel.setAlignment(Pos.CENTER_RIGHT);
            quantityLabel.setContentDisplay(ContentDisplay.RIGHT);
            AnchorPane.setRightAnchor(quantityLabel, 13.0);
            AnchorPane.setTopAnchor(quantityLabel, 13.0);

            productOrderAnchorPane.getChildren().add(quantityLabel);

            // Add the product order AnchorPane to the FlowPane
            flowPaneProductOrders.getChildren().add(productOrderAnchorPane);
        }
    }

    public void setOrderAndAnchorPane(Order order) {
        this.order = order;
    }

    @FXML
    void btnOrderDoneClicked(MouseEvent event) {
        orderDone = true;
        closeThisStage();
    }

    @FXML
    void btnOrderDoneTouched(TouchEvent event) {
        orderDone = true;
        closeThisStage();
    }

    private void closeThisStage() {
        Stage thisStage = (Stage) flowPaneProductOrders.getScene().getWindow();
        thisStage.close();
    }
}
