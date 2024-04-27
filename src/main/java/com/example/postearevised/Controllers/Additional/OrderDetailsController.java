package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.OrderDetailsModel;
import com.example.postearevised.Objects.Order.Order;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.orderDetailsStage;
import static com.example.postearevised.Miscellaneous.References.StylesReference.cssUsing;

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
    public Label labelTransactionID;

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

    @FXML
    public ScrollPane scrollPaneMain;

    @FXML
    public TextArea textAreaInvoice;


    public ObservableList<Order> selectedOrder = FXCollections.observableArrayList();
    private OrderDetailsModel orderDetailsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDetailsModel = new OrderDetailsModel();
        orderDetailsModel.setOrderDetailsController(this);

        Platform.runLater(() -> {
            scrollPaneMain.setVvalue(0);
        });

        selectedOrder.clear();
        selectedOrder.add(selectedOrderDetails);

        orderDetailsModel.setHeader();
        orderDetailsModel.setTable();
        orderDetailsModel.setTextArea();

        orderDetailsModel.setReorderToFalse();
        orderDetailsModel.requestFocusOnMainAnchorPane();
        Platform.runLater(() -> {orderDetailsStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssUsing)).toExternalForm());});
    }

    @FXML
    public void requestFocusMainPane() {
        orderDetailsModel.requestFocusOnMainAnchorPane();
    }
}
