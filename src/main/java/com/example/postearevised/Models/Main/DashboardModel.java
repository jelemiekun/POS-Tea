package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.ProductOrder;
import javafx.collections.ObservableList;

import static com.example.postearevised.Miscellaneous.References.DashboardReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class DashboardModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void updateContents() {
        updateRevenue();
        updateCustomer();
        updateOrder();
        updateCategories();

        updateUIs();
    }

    private void updateRevenue() {
        referenceTotalRevenue = 0;

        for (Order order : orderHistoryObservableList) {
            ObservableList<ProductOrder> productOrders = order.getProductOrderObservableList();
            if (!productOrders.isEmpty()) {
                for (ProductOrder productOrder : productOrders) {
                    referenceTotalRevenue += productOrder.getTotalAmount();
                }
            }
        }
    }

    private void updateCustomer() {
        referenceTotalCustomer = orderHistoryObservableList.size();
    }

    private void updateOrder() {
        referenceTotalOrder = 0;
        int i = 0;

        for (Order order : orderHistoryObservableList) {
            referenceTotalOrder += order.getProductOrderObservableList().size();
        }
    }

    private void updateCategories() {

    }

    private void updateUIs() {
        System.out.println("Total Revenue: " + referenceTotalRevenue);
        System.out.println("Total Customer: " + referenceTotalCustomer);
        System.out.println("Total Order: " + referenceTotalOrder);
    }
}
