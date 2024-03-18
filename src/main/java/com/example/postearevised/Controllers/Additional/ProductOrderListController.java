package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.ProductOrderListModel;
import com.example.postearevised.Models.Main.OrderListModel;
import com.example.postearevised.Objects.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductOrderListController implements Initializable {
    private ProductOrderListModel productOrderListModel;
    public Order order;

    @FXML
    public FlowPane flowPaneProductOrders;

    @FXML
    public Label labelCustomerName;

    @FXML
    public Label labelCustomerNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productOrderListModel = new ProductOrderListModel();
        productOrderListModel.setProductOrderListController(this);

        OrderListModel orderListModel = new OrderListModel();
        orderListModel.setProductOrderListController(this);
    }

    @FXML
    void btnOrderDone() {
        orderDone = true;
        productOrderListModel.closeThisStage();
    }

    public void setContents() {
        productOrderListModel.setContentsInvoke();
    }

    public void setOrderAndAnchorPane(Order order) {
        this.order = order;
    }
}
