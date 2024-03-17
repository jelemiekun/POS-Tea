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
            referenceTotalRevenue += order.getTotalPrice();
        }
    }

    private void updateCustomer() {
        referenceTotalCustomer = orderHistoryObservableList.size();
    }

    private void updateOrder() {
        referenceTotalOrder = 0;

        for (Order order : orderHistoryObservableList) {
            System.out.println("Customer name: " + order.getCustomerName());
            for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                System.out.println("Product name: " + productOrder.getProductName());
                referenceTotalOrder += productOrder.getQuantity();
            }
            System.out.println("ProductOrder productORder order.getList.isEmpty? : " + order.getProductOrderObservableList().isEmpty());
        }
    }

    private void updateCategories() {

    }

    private void updateUIs() {
        mainController.labelDashboardTotalRevenue.setText(String.valueOf(referenceTotalRevenue));
        mainController.labelDashboardTotalCustomer.setText(String.valueOf(referenceTotalCustomer));
        mainController.labelDashboardTotalOrder.setText(String.valueOf(referenceTotalOrder));

        System.out.println("Total Revenue: " + referenceTotalRevenue);
        System.out.println("Total Customer: " + referenceTotalCustomer);
        System.out.println("Total Order: " + referenceTotalOrder);
    }
}
