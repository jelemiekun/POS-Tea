package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
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
        updateBestSeller();

        updateUIs();
    }

    private void updateRevenue() {
        referenceTotalRevenue = 0;
        for (Order order : orderHistoryObservableList) {
            referenceTotalRevenue += order.getTotalPrice();
            for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                System.out.println(productOrder.getProductName());
            }
        }
    }

    private void updateCustomer() {
        referenceTotalCustomer = orderHistoryObservableList.size();
    }

    private void updateOrder() {
        referenceTotalOrder = 0;

        for (Order order : orderHistoryObservableList) {
            for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                referenceTotalOrder += productOrder.getQuantity();
            }
        }
    }

    private void updateCategories() {
        referenceMilkTeaCounter = 0;
        referenceCoolersCounter = 0;
        referenceCoffeeCounter = 0;
        referenceIceCandyCupsCounter = 0;
        referenceAppetizerCounter = 0;

        for (Order order : orderHistoryObservableList) {

            synchronized (order.getProductOrderObservableList()) {
                for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                    String category = productOrder.getProductCategory();
                    switch (category) {
                        case "Milk Tea":
                            referenceMilkTeaCounter++;
                            break;
                        case "Coolers":
                            referenceCoolersCounter++;
                            break;
                        case "Coffee":
                            referenceCoffeeCounter++;
                            break;
                        case "Ice Candy Cups":
                            referenceIceCandyCupsCounter++;
                            break;
                        case "Appetizers":
                            referenceAppetizerCounter++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }


    private void updateBestSeller() {
        Map<ProductOrder, Long> productOrderCounts = orderHistoryObservableList.stream()
                .flatMap(order -> order.getProductOrderObservableList().stream())
                .collect(Collectors.groupingBy(
                        productOrder -> productOrder,
                        Collectors.summingLong(ProductOrder::getQuantity)
                ));

        List<Map.Entry<ProductOrder, Long>> sortedEntries = new ArrayList<>(productOrderCounts.entrySet());
        sortedEntries.sort((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()));

        Map<String, ProductOrder> uniqueProductsMap = new HashMap<>();
        topTenProducts = new ArrayList<>();

        for (Map.Entry<ProductOrder, Long> entry : sortedEntries) {
            ProductOrder product = entry.getKey();
            String key = product.getProductName() + product.getProductCategory() + product.getImagePath();

            if (!uniqueProductsMap.containsKey(key)) {
                uniqueProductsMap.put(key, product);
                topTenProducts.add(product);
                if (topTenProducts.size() >= 10) {
                    break;
                }
            }
        }

        System.out.println("Top 10 best-selling products:");
        int i = 1;
        for (ProductOrder product : topTenProducts) {
            System.out.println(i + ": " + product.getProductName());
            i++;
        }
    }





    private void updateUIs() {
        mainController.labelDashboardTotalRevenue.setText(String.valueOf(referenceTotalRevenue));
        mainController.labelDashboardTotalCustomer.setText(String.valueOf(referenceTotalCustomer));
        mainController.labelDashboardTotalOrder.setText(String.valueOf(referenceTotalOrder));

        mainController.pieChartDashboard.getData().clear();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        if (referenceMilkTeaCounter == 0 && referenceCoolersCounter == 0 && referenceCoffeeCounter == 0 && referenceIceCandyCupsCounter == 0 && referenceAppetizerCounter == 0) {
            PieChart.Data emptyData = new PieChart.Data("Empty", 1);
            emptyData.nodeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    newValue.setStyle("-fx-pie-color: #808080;");
                }
            });
            pieChartData.add(emptyData);

            isNoSalesLabelsVisible(true);
        } else {
            isNoSalesLabelsVisible(false);

            if (referenceMilkTeaCounter > 0) {
                pieChartData.add(new PieChart.Data(MILK_TEA_ENUM.getCategory(), referenceMilkTeaCounter));
            }
            if (referenceCoolersCounter > 0) {
                pieChartData.add(new PieChart.Data(COOLERS_ENUM.getCategory(), referenceCoolersCounter));
            }
            if (referenceCoffeeCounter > 0) {
                pieChartData.add(new PieChart.Data(COFFEE_ENUM.getCategory(), referenceCoffeeCounter));
            }
            if (referenceIceCandyCupsCounter > 0) {
                pieChartData.add(new PieChart.Data(ICE_CANDY_CUPS_ENUM.getCategory(), referenceIceCandyCupsCounter));
            }
            if (referenceAppetizerCounter > 0) {
                pieChartData.add(new PieChart.Data(APPETIZERS_ENUM.getCategory(), referenceAppetizerCounter));
            }

            for (int i = 0; i < pieChartData.size(); i++) {
                PieChart.Data data = pieChartData.get(i);
                String color = pieChartColors[i % pieChartColors.length];
                data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        newValue.setStyle("-fx-pie-color: " + color + ";");
                    }
                });
            }
        }

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " ",
                                Bindings.format("%.0f", data.pieValueProperty())
                        )
                )
        );

        mainController.pieChartDashboard.getData().addAll(pieChartData);

        for (Node legend : mainController.pieChartDashboard.lookupAll(".chart-legend-item")) {
            if (legend instanceof Label) {
                ((Label) legend).setFont(Font.font("Arial", FontWeight.NORMAL, 18));
            }
        }
    }

    private void isNoSalesLabelsVisible(boolean isVisible) {
        mainController.labelDashBoardNoSalesPieChart.setVisible(isVisible);
        mainController.labelDashBoardNoSalesBestSeller.setVisible(isVisible);
    }


}