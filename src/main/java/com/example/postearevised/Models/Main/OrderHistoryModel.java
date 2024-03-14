package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.orderHistoryObservableList;

public class OrderHistoryModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOrderHistoryTable() {
        mainController.tableViewOrderHistoryColCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        mainController.tableViewOrderHistoryColProductName.setCellValueFactory(new PropertyValueFactory<>("productOrderObservableList"));
        //mainController.tableViewOrderHistoryColQuantity.setCellValueFactory(new PropertyValueFactory<>(""));
        //mainController.tableViewOrderHistoryColPrice.setCellValueFactory(new PropertyValueFactory<>(""));
        mainController.tableViewOrderHistoryColTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        mainController.tableViewOrderHistoryColAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        mainController.tableViewOrderHistoryColChange.setCellValueFactory(new PropertyValueFactory<>("change"));
        mainController.tableViewOrderHistoryColModeOfPayment.setCellValueFactory(new PropertyValueFactory<>("modeOfPayment"));
        mainController.tableViewOrderHistoryColDateAndTime.setCellValueFactory(new PropertyValueFactory<>("dateAndTime"));

        mainController.tableViewOrderHistory.setItems(orderHistoryObservableList);
        mainController.tableViewOrderHistoryColCustomerName.setReorderable(false);
        mainController.tableViewOrderHistoryColProductName.setReorderable(false);
        mainController.tableViewOrderHistoryColQuantity.setReorderable(false);
        mainController.tableViewOrderHistoryColPrice.setReorderable(false);
        mainController.tableViewOrderHistoryColTotalPrice.setReorderable(false);
        mainController.tableViewOrderHistoryColAmountPaid.setReorderable(false);
        mainController.tableViewOrderHistoryColChange.setReorderable(false);
        mainController.tableViewOrderHistoryColModeOfPayment.setReorderable(false);
        mainController.tableViewOrderHistoryColDateAndTime.setReorderable(false);
    }
}
