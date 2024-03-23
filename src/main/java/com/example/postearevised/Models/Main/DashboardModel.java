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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.DashboardReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class DashboardModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void updateContents() {
        updateReferences();
        updateUIs();
    }

    private void updateReferences() {
        updateReferenceRevenue();
        updateReferenceCustomer();
        updateReferenceOrder();
        updateReferenceCategories();
        updateReferenceBestSeller();
    }

    private void updateReferenceRevenue() {
        referenceTotalRevenue = 0;
        for (Order order : orderHistoryObservableList) {
            referenceTotalRevenue += order.getTotalPrice();
            for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                System.out.println(productOrder.getProductName());
            }
        }
    }

    private void updateReferenceCustomer() {
        referenceTotalCustomer = orderHistoryObservableList.size();
    }

    private void updateReferenceOrder() {
        referenceTotalOrder = 0;
        for (Order order : orderHistoryObservableList) {
            for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                referenceTotalOrder += productOrder.getQuantity();
            }
        }
    }

    private void updateReferenceCategories() {
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
                            referenceMilkTeaCounter += productOrder.getQuantity();
                            break;
                        case "Coolers":
                            referenceCoolersCounter += productOrder.getQuantity();
                            break;
                        case "Coffee":
                            referenceCoffeeCounter += productOrder.getQuantity();
                            break;
                        case "Ice Candy Cups":
                            referenceIceCandyCupsCounter += productOrder.getQuantity();
                            break;
                        case "Appetizers":
                            referenceAppetizerCounter += productOrder.getQuantity();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }


    private void updateReferenceBestSeller() {
        topTenProducts.clear();

        Map<String, ProductOrder> productTotalQuantities = orderHistoryObservableList.stream()
                .flatMap(order -> order.getProductOrderObservableList().stream())
                .collect(Collectors.groupingBy(
                        productOrder -> productOrder.getProductName() + productOrder.getProductCategory(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                productOrders -> {
                                    ProductOrder firstProductOrder = productOrders.get(0);
                                    int totalQuantity = productOrders.stream().mapToInt(ProductOrder::getQuantity).sum();
                                    return new ProductOrder(
                                            firstProductOrder.getProductName(),
                                            firstProductOrder.getProductCategory(),
                                            firstProductOrder.getProductImage(),
                                            firstProductOrder.getImagePath(),
                                            firstProductOrder.getFirstAttribute(),
                                            firstProductOrder.getSecondAttribute(),
                                            firstProductOrder.getThirdAttribute(),
                                            firstProductOrder.getTotalAmount(),
                                            totalQuantity
                                    );
                                }
                        )
                ));

        List<ProductOrder> sortedEntries = productTotalQuantities.values().stream()
                .sorted(Comparator.comparingInt(ProductOrder::getQuantity).reversed())
                .limit(10)
                .toList();

        System.out.println("Top 10 best-selling products:");
        for (int i = 0; i < sortedEntries.size(); i++) {
            ProductOrder product = sortedEntries.get(i);
            System.out.println((i + 1) + ": " + product.getProductName() + " - Total Quantity: " + product.getQuantity() + ": " + product.getImagePath());
            topTenProducts.add(product);
        }
    }







    private void updateUIs() {
        updateUIRevenueCustomerAndOrder();
        updateUIPieChart();
        updateUIBestSeller();
    }

    private void updateUIRevenueCustomerAndOrder() {
        mainController.labelDashboardTotalRevenue.setText(String.valueOf(referenceTotalRevenue));
        mainController.labelDashboardTotalCustomer.setText(String.valueOf(referenceTotalCustomer));
        mainController.labelDashboardTotalOrder.setText(String.valueOf(referenceTotalOrder));
    }

    private void updateUIPieChart() {
        mainController.pieChartDashboard.getData().clear();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        int totalCounter = referenceMilkTeaCounter + referenceCoolersCounter + referenceCoffeeCounter + referenceIceCandyCupsCounter + referenceAppetizerCounter;
        if (totalCounter == 0) {
            PieChart.Data emptyData = new PieChart.Data("Empty", 100);
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
                pieChartData.add(new PieChart.Data(MILK_TEA_ENUM.getCategory(), (referenceMilkTeaCounter * 100.0) / totalCounter));
            }
            if (referenceCoolersCounter > 0) {
                pieChartData.add(new PieChart.Data(COOLERS_ENUM.getCategory(), (referenceCoolersCounter * 100.0) / totalCounter));
            }
            if (referenceCoffeeCounter > 0) {
                pieChartData.add(new PieChart.Data(COFFEE_ENUM.getCategory(), (referenceCoffeeCounter * 100.0) / totalCounter));
            }
            if (referenceIceCandyCupsCounter > 0) {
                pieChartData.add(new PieChart.Data(ICE_CANDY_CUPS_ENUM.getCategory(), (referenceIceCandyCupsCounter * 100.0) / totalCounter));
            }
            if (referenceAppetizerCounter > 0) {
                pieChartData.add(new PieChart.Data(APPETIZERS_ENUM.getCategory(), (referenceAppetizerCounter * 100.0) / totalCounter));
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
                                Bindings.createStringBinding(() -> String.format("%.0f%%", data.getPieValue()), data.pieValueProperty())
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

    private void updateUIBestSeller() {
        mainController.flowPaneBestSeller.getChildren().clear();

        for (ProductOrder productOrder : topTenProducts) {
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefWidth(441);
            anchorPane.setPrefHeight(161);

            ImageView imageView;

            System.out.println("Image Path: " + productOrder.getImagePath()); // Print out the image path
            File productImage = new File(productOrder.getImagePath());

            if (!productOrder.getImagePath().isEmpty()) {
                if (productOrder.getImagePath().startsWith("/com/example/postearevised/")) {
                    imageView = new ImageView(new Image(productOrder.getImagePath()));
                } else if (productOrder.getImagePath().startsWith("com")) {
                    imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
                } else if (productOrder.getImagePath().equals("com")) {
                    imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
                } else if (productOrder.getImagePath().isEmpty()) {
                    imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
                } else if (productOrder.getImagePath().equals("example")) {
                    imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
                } else if (productOrder.getImagePath().isBlank()) {
                    imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
                } else if (productOrder.getImagePath().contains("no image")) {
                    imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
                } else if (productOrder.getImagePath().contains("Product Media")) {
                    imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
                } else if (!productImage.exists()) {
                    imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
                } else {
                    File file = new File(productOrder.getImagePath());
                    String fileUrl = null;
                    try {
                        fileUrl = file.toURI().toURL().toString();
                    } catch (MalformedURLException e) {
                        errorMessage = e.getMessage();
                        logError(false);
                    }
                    imageView = new ImageView(new Image(fileUrl));
                }
            } else {
                imageView = new ImageView(new Image("/com/example/postearevised/Product Media/no image/no image.png"));
            }


            imageView.setFitWidth(141);
            imageView.setFitHeight(85);
            imageView.setLayoutX(29);
            imageView.setLayoutY(52);

            Label nameLabel = new Label(productOrder.getProductName());
            nameLabel.setLayoutX(29);
            nameLabel.setLayoutY(21);
            nameLabel.setPrefWidth(365);
            nameLabel.setPrefHeight(27);
            nameLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 24;");

            Label totalOrdersLabel = new Label("Total Orders:");
            totalOrdersLabel.setLayoutX(273);
            totalOrdersLabel.setLayoutY(74);
            totalOrdersLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");

            Label ordersCountLabel = new Label(String.valueOf(productOrder.getQuantity()));
            ordersCountLabel.setLayoutX(369);
            ordersCountLabel.setLayoutY(74);
            ordersCountLabel.setPrefWidth(64);
            ordersCountLabel.setPrefHeight(23);
            ordersCountLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 20;");
            ordersCountLabel.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
            ordersCountLabel.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);

            anchorPane.getChildren().addAll(imageView, nameLabel, totalOrdersLabel, ordersCountLabel);

            mainController.flowPaneBestSeller.getChildren().add(anchorPane);
        }
    }



    private void isNoSalesLabelsVisible(boolean isVisible) {
        mainController.labelDashBoardNoSalesPieChart.setVisible(isVisible);
        mainController.labelDashBoardNoSalesBestSeller.setVisible(isVisible);
    }


}