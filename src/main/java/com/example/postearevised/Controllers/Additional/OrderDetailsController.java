package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.OrderDetailsModel;
import com.example.postearevised.Objects.Order.Order;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailsController implements Initializable {

    @FXML
    public Label labelOrderCustomerName;

    @FXML
    public Label labelOrderDateAndTime;

    @FXML
    public Label labelOrderModeOfPayment;

    @FXML
    public Label labelOrderNumber;

    @FXML
    public TableView<Order> tableViewRecordDetails;

    @FXML
    public TableColumn<Order, String> tableViewRecordDetailsAmountPaid;

    @FXML
    public TableColumn<Order, String> tableViewRecordDetailsChange;

    @FXML
    public TableColumn<Order, String> tableViewRecordDetailsFoodCategories;

    @FXML
    public TableColumn<Order, String> tableViewRecordDetailsProductDetails;

    @FXML
    public TableColumn<Order, String> tableViewRecordDetailsProductPrice;

    @FXML
    public TableColumn<Order, String> tableViewRecordDetailsQuantity;

    @FXML
    public TableColumn<Order, String> tableViewRecordDetailsTotalAmount;


    public Order selectedOrder;
    private OrderDetailsModel orderDetailsModel;

    public void setSelectedOrder(Order order) {
        this.selectedOrder = order;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDetailsModel = new OrderDetailsModel();
        orderDetailsModel.setOrderDetailsController(this);
        orderDetailsModel.setTable();
    }
}
