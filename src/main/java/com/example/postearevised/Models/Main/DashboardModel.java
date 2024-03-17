package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.ProductOrder;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

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

        updateUIs();
    }

    private void updateRevenue() {
        referenceTotalRevenue = 0;
        synchronized (synchronizedOrderHistoryObservableList) {
            for (Order order : synchronizedOrderHistoryObservableList) {
                referenceTotalRevenue += order.getTotalPrice();
                for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                    System.out.println(productOrder.getProductName());
                }
                System.out.println("updateRevenue order.getList.isEmpty? " + order.getProductOrderObservableList().isEmpty());
            }
        }
    }


    private void updateCustomer() {
        synchronized (synchronizedOrderHistoryObservableList) {
            referenceTotalCustomer = synchronizedOrderHistoryObservableList.size();
        }
    }


    private void updateOrder() {
        synchronized (synchronizedOrderHistoryObservableList) {
            referenceTotalOrder = 0;

            for (Order order : synchronizedOrderHistoryObservableList) {
                System.out.println("Customer name: " + order.getCustomerName());

                synchronized (order.getProductOrderObservableList()) {
                    for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                        System.out.println("Product name: " + productOrder.getProductName());
                        referenceTotalOrder += productOrder.getQuantity();
                    }
                }

                System.out.println("updateOrder ProductOrder productORder order.getList.isEmpty? : " + order.getProductOrderObservableList().isEmpty());
            }
        }
    }


    private void updateCategories() {
        synchronized (synchronizedOrderHistoryObservableList) {
            referenceMilkTeaCounter = 0;
            referenceCoolersCounter = 0;
            referenceCoffeeCounter = 0;
            referenceIceCandyCupsCounter = 0;
            referenceAppetizerCounter = 0;

            for (Order order : synchronizedOrderHistoryObservableList) {

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
                System.out.println("updateCategories productOrder order.getList.isEmpty? " + order.getProductOrderObservableList().isEmpty());
            }
            System.out.println("updateCategories orderHistoryListSize: " + synchronizedOrderHistoryObservableList.size());
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
        } else {
            pieChartData.addAll(
                    new PieChart.Data(MilkTeaEnum.getCategory(), referenceMilkTeaCounter),
                    new PieChart.Data(CoolersEnum.getCategory(), referenceCoolersCounter),
                    new PieChart.Data(CoffeeEnum.getCategory(), referenceCoffeeCounter),
                    new PieChart.Data(IceCandyCupsEnum.getCategory(), referenceIceCandyCupsCounter),
                    new PieChart.Data(AppetizersEnum.getCategory(), referenceAppetizerCounter)
            );

            // Assign colors to each data segment
            String[] colors = {"#ff0000", "#ffa500", "#ffff00", "#008000", "#0000ff"};
            for (int i = 0; i < pieChartData.size(); i++) {
                PieChart.Data data = pieChartData.get(i);
                String color = colors[i % colors.length];
                data.nodeProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        newValue.setStyle("-fx-pie-color: " + color + ";");
                    }
                });
            }
        }

        pieChartData.forEach(
                data ->
                        data.nameProperty().bind(
                                Bindings.concat(
                                        data.getName(), " ", data.pieValueProperty().asString()
                                )
                        )
        );

        mainController.pieChartDashboard.getData().addAll(pieChartData);







        System.out.println("Total Revenue: " + referenceTotalRevenue);
        System.out.println("Total Customer: " + referenceTotalCustomer);
        System.out.println("Total Order: " + referenceTotalOrder);
    }

}