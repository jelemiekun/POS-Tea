package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.OrderDetailsModel;
import com.example.postearevised.Objects.Order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class OrderDetailsController implements Initializable {

    @FXML
    public AnchorPane anchorPaneMain;

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


    public ObservableList<Order> selectedOrder = FXCollections.observableArrayList();
    private OrderDetailsModel orderDetailsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDetailsModel = new OrderDetailsModel();
        orderDetailsModel.setOrderDetailsController(this);

        selectedOrder.clear();
        selectedOrder.add(selectedOrderDetails);

        orderDetailsModel.setHeader();
        orderDetailsModel.setTable();

        orderDetailsModel.requestFocusOnMainAnchorPane();
    }

    @FXML
    public void requestFocusMainPane() {
        orderDetailsModel.requestFocusOnMainAnchorPane();
    }
}
