package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.postearevised.Miscellaneous.Enums.MainPaneEnum.SETTINGS_ENUM;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPaneEnum.SystemManual;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.DashboardReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class DashboardModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setupDashboard() {
        setStyles();
        setFirstChoiceBox();

        if (!orderHistoryObservableList.isEmpty()) {
            setRefreshDashboardComboBoxOptions();
        }
    }

    public void resetToToday() {
        setToToday();
        mainController.dashboardComboBoxFirstSelection.setValue("Daily");
        firstChoiceBoxOnAction();
        updateContents();
    }

    private void setStyles() {
        mainController.dashboardComboBoxFirstSelection.setStyle("-fx-font-family: Arial; -fx-font-size: 18px;");
        mainController.dashboardComboBoxSecondSelection.setStyle("-fx-font-family: Arial; -fx-font-size: 18px;");
        mainController.dashboardComboBoxThirdSelection.setStyle("-fx-font-family: Arial; -fx-font-size: 18px;");
        mainController.dashboardComboBoxFourthSelection.setStyle("-fx-font-family: Arial; -fx-font-size: 18px;");
    }

    private void setFirstChoiceBox() {
        mainController.dashboardComboBoxFirstSelection.setItems(firstChoiceBoxObservableList);
    }

    public void setToToday() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        String monthName = getMonthName(currentMonth);

        mainController.dashboardComboBoxFirstSelection.setValue("Daily");
        mainController.dashboardComboBoxSecondSelection.setValue(String.valueOf(currentYear));
        mainController.dashboardComboBoxThirdSelection.setValue(monthName);
        mainController.dashboardComboBoxFourthSelection.setValue(String.valueOf(currentDay));
    }

    private void setRefreshDashboardComboBoxOptions() {
        populateChoiceBoxLists();
        refreshComboBox();
        setToToday();
        firstChoiceBoxOnAction();
        updateContents();
    }

    public void firstChoiceBoxOnAction() {
        String selected = mainController.dashboardComboBoxFirstSelection.getValue();

        switch (selected) {
            case "Daily":
                mainController.dashboardComboBoxSecondSelection.setVisible(true);
                mainController.dashboardComboBoxThirdSelection.setVisible(true);
                mainController.dashboardComboBoxFourthSelection.setVisible(true);
                break;
            case "Weekly":
                mainController.dashboardComboBoxSecondSelection.setVisible(true);
                mainController.dashboardComboBoxThirdSelection.setVisible(true);
                mainController.dashboardComboBoxFourthSelection.setVisible(true);
                break;
            case "Monthly":
                mainController.dashboardComboBoxSecondSelection.setVisible(true);
                mainController.dashboardComboBoxThirdSelection.setVisible(true);
                mainController.dashboardComboBoxFourthSelection.setVisible(false);
                break;
            case "Annually":
                mainController.dashboardComboBoxSecondSelection.setVisible(true);
                mainController.dashboardComboBoxThirdSelection.setVisible(false);
                mainController.dashboardComboBoxFourthSelection.setVisible(false);
                break;
        }

        updateDashboardOrderObservableListReference(selected);
    }

    private void populateChoiceBoxLists() {
        // Find the oldest order
        LocalDateTime oldestDateTime = LocalDateTime.MAX;
        for (Order order : orderHistoryObservableList) {
            LocalDateTime orderDateTime = order.getDateAndTime();
            if (orderDateTime.isBefore(oldestDateTime)) {
                oldestDateTime = orderDateTime;
            }
        }

        // Extract year, month, and day from the oldest date
        int oldestYear = oldestDateTime.getYear();
        int oldestMonth = oldestDateTime.getMonthValue();
        int oldestDay = oldestDateTime.getDayOfMonth();

        // Get current date
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        // Populate secondChoiceBoxObservableList with years from oldestYear to currentYear
        secondChoiceBoxObservableList.clear();
        for (int year = oldestYear; year <= currentYear; year++) {
            secondChoiceBoxObservableList.add(String.valueOf(year));
        }

        // Populate thirdChoiceBoxObservableList with months
        thirdChoiceBoxObservableList.clear();
        int startMonth = (oldestYear == currentYear) ? oldestMonth : 1;
        int endMonth = (oldestYear == currentYear) ? currentMonth : 12;
        for (int month = startMonth; month <= endMonth; month++) {
            thirdChoiceBoxObservableList.add(getMonthName(month));
        }

        // Populate fourthChoiceBoxObservableList with days
        fourthChoiceBoxObservableList.clear();
        int startDay = (oldestYear == currentYear && oldestMonth == currentMonth) ? oldestDay : 1;
        int endDay = (oldestYear == currentYear && oldestMonth == currentMonth) ? currentDay : getDaysInMonth(currentYear, currentMonth);
        for (int day = startDay; day <= endDay; day++) {
            fourthChoiceBoxObservableList.add(String.valueOf(day));
        }
    }

    private void updateDashboardOrderObservableListReference(String selected) {
        dashboardOrderObservableListReference.clear();

        switch (selected) {
            case "Daily":
                String selectedYearMonthDayString = mainController.dashboardComboBoxSecondSelection.getValue() + " " + mainController.dashboardComboBoxThirdSelection.getValue() + " " + mainController.dashboardComboBoxFourthSelection.getValue();

                for (Order order : orderHistoryObservableList) {
                    LocalDateTime dateTime = order.getDateAndTime();

                    String formattedYearMonthDayTime = dateTime.format(yearMonthDayFormatter);

                    if (formattedYearMonthDayTime.equals(selectedYearMonthDayString))
                        dashboardOrderObservableListReference.add(order);
                }
                break;
            case "Weekly":
                break;
            case "Monthly":
                String selectedYearMonthString = mainController.dashboardComboBoxSecondSelection.getValue() + " " + mainController.dashboardComboBoxThirdSelection.getValue();

                for (Order order : orderHistoryObservableList) {
                    LocalDateTime dateTime = order.getDateAndTime();

                    String formattedYearMonthTime = dateTime.format(yearMonthFormatter);

                    if (formattedYearMonthTime.equals(selectedYearMonthString))
                        dashboardOrderObservableListReference.add(order);
                }
                break;
            case "Annually":
                String selectedYearString = mainController.dashboardComboBoxSecondSelection.getValue();

                for (Order order : orderHistoryObservableList) {
                    LocalDateTime dateTime = order.getDateAndTime();

                    String formattedYearMonthTime = dateTime.format(yearFormatter);

                    if (formattedYearMonthTime.equals(selectedYearString))
                        dashboardOrderObservableListReference.add(order);
                }
                break;
        }

        updateContents();
    }

    // Method to get month name from month number
    private String getMonthName(int month) {
        return switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "";
        };
    }

    private int getDaysInMonth(int year, int month) {
        return LocalDate.of(year, month, 1).lengthOfMonth();
    }

    private void refreshComboBox() {
        mainController.dashboardComboBoxSecondSelection.getItems().clear();
        mainController.dashboardComboBoxThirdSelection.getItems().clear();
        mainController.dashboardComboBoxFourthSelection.getItems().clear();


        mainController.dashboardComboBoxSecondSelection.setItems(secondChoiceBoxObservableList);
        mainController.dashboardComboBoxThirdSelection.setItems(thirdChoiceBoxObservableList);
        mainController.dashboardComboBoxFourthSelection.setItems(fourthChoiceBoxObservableList);
    }






    private void updateContents() {
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
        for (Order order : dashboardOrderObservableListReference) {
            referenceTotalRevenue += order.getTotalPrice();
            for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                System.out.println(productOrder.getProductName());
            }
        }
    }

    private void updateReferenceCustomer() {
        referenceTotalCustomer = dashboardOrderObservableListReference.size();
    }

    private void updateReferenceOrder() {
        referenceTotalOrder = 0;
        for (Order order : dashboardOrderObservableListReference) {
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

        for (Order order : dashboardOrderObservableListReference) {

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
        updateUITitles();
        updateUIRevenueCustomerAndOrder();
        updateUIBarChart();
        updateUIBestSeller();
    }

    private void updateUITitles() {
        String selected = mainController.dashboardComboBoxFirstSelection.getValue();

        switch (selected) {
            case "Daily":
                break;
            case "Weekly":
                break;
            case "Monthly":
                break;
            case "Annually":
                break;
        }
    }

    private void updateUIRevenueCustomerAndOrder() {
        mainController.labelDashboardTotalRevenue.setText(String.valueOf(referenceTotalRevenue));
        mainController.labelDashboardTotalCustomer.setText(String.valueOf(referenceTotalCustomer));
        mainController.labelDashboardTotalOrder.setText(String.valueOf(referenceTotalOrder));
    }

    private void updateUIBarChart() {

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


    public void goToSystemManual() {
        mainController.mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
        mainController.settingsModel.openSelectedPane(SystemManual.getPaneNumber());
    }
}