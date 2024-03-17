package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class OrderHistoryModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOrderHistoryTable() {
        mainController.tableViewOrderHistoryColCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        mainController.tableViewOrderHistoryColProductCategory.setCellValueFactory(new PropertyValueFactory<>("productOrderObservableList"));
        mainController.tableViewOrderHistoryColProductName.setCellValueFactory(new PropertyValueFactory<>("productOrderObservableList"));
        mainController.tableViewOrderHistoryColQuantity.setCellValueFactory(new PropertyValueFactory<>("productOrderObservableList"));
        mainController.tableViewOrderHistoryColPrice.setCellValueFactory(new PropertyValueFactory<>("productOrderObservableList"));
        mainController.tableViewOrderHistoryColTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        mainController.tableViewOrderHistoryColAmountPaid.setCellValueFactory(new PropertyValueFactory<>("amountPaid"));
        mainController.tableViewOrderHistoryColChange.setCellValueFactory(new PropertyValueFactory<>("change"));
        mainController.tableViewOrderHistoryColModeOfPayment.setCellValueFactory(new PropertyValueFactory<>("modeOfPayment"));
        mainController.tableViewOrderHistoryColDateAndTime.setCellValueFactory(cellData -> {
            ObjectProperty<LocalDateTime> property = new SimpleObjectProperty<>(cellData.getValue().getDateAndTime());
            return property;
        });

        mainController.tableViewOrderHistoryColDateAndTime.setCellFactory(column -> {
            return new TableCell<Order, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd : HH:mm:ss");
                        setText(item.format(formatter));
                    }
                }
            };
        });

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
