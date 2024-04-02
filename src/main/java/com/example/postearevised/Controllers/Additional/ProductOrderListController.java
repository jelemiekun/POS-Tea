package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.ProductOrderListModel;
import com.example.postearevised.Models.Main.OrderListModel;
import com.example.postearevised.Objects.Order.Order;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.askForPasswordStage;
import static com.example.postearevised.Miscellaneous.References.StageReference.productOrderListStage;
import static com.example.postearevised.Miscellaneous.References.StylesReference.cssUsing;

import java.net.URL;
import java.util.Objects;
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
        Platform.runLater(() -> {productOrderListStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssUsing)).toExternalForm());});
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
