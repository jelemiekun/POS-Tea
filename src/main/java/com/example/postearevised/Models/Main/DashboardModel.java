package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.postearevised.Miscellaneous.Enums.MainPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.DashboardReference.*;
import static com.example.postearevised.Miscellaneous.References.DateTimeFormatterReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.StylesReference.*;

public class DashboardModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setupDashboard() {
        setStyles();
        setFirstChoiceBox();

        if (!orderHistoryObservableList.isEmpty())
            setRefreshDashboardComboBoxOptions();
        else
            mainController.dashboardComboBoxFirstSelection.setValue("Daily");
    }

    public void resetToToday() {
        if (!orderHistoryObservableList.isEmpty()) {
            setToToday();
            firstChoiceBoxOnAction();
        }
    }

    private void setStyles() {
        mainController.dashboardComboBoxFirstSelection.setStyle(dashboardComboBoxStyle);
        mainController.dashboardComboBoxSecondSelection.setStyle(dashboardComboBoxStyle);
        mainController.dashboardComboBoxThirdSelection.setStyle(dashboardComboBoxStyle);
        mainController.dashboardComboBoxFourthSelection.setStyle(dashboardComboBoxStyle);
    }

    private void setFirstChoiceBox() {
        mainController.dashboardComboBoxFirstSelection.setItems(firstChoiceBoxObservableList);
    }

    private void setToToday() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        String monthName = getMonthName(currentMonth);

        isAddingProductsFromImport = true;
        mainController.dashboardComboBoxFirstSelection.setValue("Daily");
        mainController.dashboardComboBoxSecondSelection.setValue(String.valueOf(currentYear));
        mainController.dashboardComboBoxThirdSelection.setValue(monthName);
        mainController.dashboardComboBoxFourthSelection.setValue(String.valueOf(currentDay));
        isAddingProductsFromImport = false;
    }

    public void setRefreshDashboardComboBoxOptions() {
        populateChoiceBoxLists();
        refreshComboBox();
        setToToday();
    }

    public void firstChoiceBoxOnAction() {
        if (!isAddingProductsFromImport) {
            if (!orderHistoryObservableList.isEmpty()) {
                if (mainController.dashboardComboBoxSecondSelection.getValue() == null || mainController.dashboardComboBoxThirdSelection.getValue() == null || mainController.dashboardComboBoxFourthSelection.getValue() == null)
                    setRefreshDashboardComboBoxOptions();

                setUIIfEmpty(false);

                String selected = mainController.dashboardComboBoxFirstSelection.getValue();

                switch (selected) {
                    case "Daily", "Weekly":
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
            } else {
                setUIIfEmpty(true);
            }
        }
    }

    private void setUIIfEmpty(boolean isEmpty) {
        mainController.labelDashBoardNoSalesRecordedGraph.setVisible(isEmpty);
        mainController.anchorPaneResetToToday.setVisible(!isEmpty);
        mainController.flowPaneDashboardLegends.setVisible(!isEmpty);
        mainController.labelDashboardBarChartTitle.setVisible(!isEmpty);
        mainController.dashboardBarChart.setVisible(!isEmpty);
    }

    private void populateChoiceBoxLists() {
        LocalDateTime oldestDateTime = LocalDateTime.MAX;
        for (Order order : orderHistoryObservableList) {
            LocalDateTime orderDateTime = order.getDateAndTime();
            if (orderDateTime.isBefore(oldestDateTime)) {
                oldestDateTime = orderDateTime;
            }
        }

        int oldestYear = oldestDateTime.getYear();
        int oldestMonth = oldestDateTime.getMonthValue();
        int oldestDay = oldestDateTime.getDayOfMonth();

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        int currentDay = currentDate.getDayOfMonth();

        secondChoiceBoxObservableList.clear();
        for (int year = oldestYear; year <= currentYear; year++) {
            secondChoiceBoxObservableList.add(String.valueOf(year));
        }

        thirdChoiceBoxObservableList.clear();
        int startMonth = (oldestYear == currentYear) ? oldestMonth : 1;
        int endMonth = (oldestYear == currentYear) ? currentMonth : 12;
        for (int month = startMonth; month <= endMonth; month++) {
            thirdChoiceBoxObservableList.add(getMonthName(month));
        }

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

    public void updateAllTimeFavorites() {
        updateReferenceBestSeller();
        updateUIBestSeller();
    }

    private void updateReferences() {
        updateReferenceRevenue();
        updateReferenceCustomer();
        updateReferenceOrder();
        updateReferenceCategories();
    }

    private void updateReferenceRevenue() {
        referenceTotalRevenue = 0;
        for (Order order : dashboardOrderObservableListReference) {
            referenceTotalRevenue += order.getTotalPrice();
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
        updateUIBarChartLegends();
    }

    private void updateUITitles() {
        String selected = mainController.dashboardComboBoxFirstSelection.getValue();

        String toConcat = "";

        switch (selected) {
            case "Daily", "Weekly":
                toConcat = "(" + mainController.dashboardComboBoxSecondSelection.getValue() + ", " + mainController.dashboardComboBoxThirdSelection.getValue() + " " + mainController.dashboardComboBoxFourthSelection.getValue() + ")";
                break;
            case "Monthly":
                toConcat = "(" + mainController.dashboardComboBoxSecondSelection.getValue() + ", " + mainController.dashboardComboBoxThirdSelection.getValue() + ")";
                break;
            case "Annually":
                toConcat = "(" + mainController.dashboardComboBoxSecondSelection.getValue() + ")";
                break;
        }

        mainController.labelDashboardTotalRevenueTitle.setText("TOTAL REVENUE " + toConcat.toUpperCase());
        mainController.labelDashboardTotalCustomerTitle.setText("TOTAL CUSTOMER " + toConcat.toUpperCase());
        mainController.labelDashboardTotalOrderTitle.setText("TOTAL ORDER " + toConcat.toUpperCase());
        mainController.labelDashboardBarChartTitle.setText("Top Selling Food Categories " + toConcat);

    }

    private void updateUIRevenueCustomerAndOrder() {
        mainController.labelDashboardTotalRevenue.setText("₱" + referenceTotalRevenue + ".00");
        mainController.labelDashboardTotalCustomer.setText(String.valueOf(referenceTotalCustomer));
        mainController.labelDashboardTotalOrder.setText(String.valueOf(referenceTotalOrder));
    }

    private void updateUIBarChart() {
        mainController.dashboardBarChart.getData().clear();

        if (!dashboardOrderObservableListReference.isEmpty()) {
            List<XYChart.Series<String, Number>> seriesList = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("");

                series.getData().add(new XYChart.Data<>("", getCountForIndex(i)));

                seriesList.add(series);
            }

            mainController.dashboardBarChart.getData().addAll(seriesList);

            int index = 0;
            for (XYChart.Series<String, Number> series : mainController.dashboardBarChart.getData()) {
                for (XYChart.Data<String, Number> data : series.getData()) {
                    Node bar = data.getNode();
                    if (bar != null) {
                        bar.setStyle("-fx-bar-fill: " + dashboardGraphColors[index] + ";");
                        Tooltip dashboardBarStickToolTip = new Tooltip(getStringForIndex(index) + " (" + getCountForIndex(index) + ")");
                        dashboardBarStickToolTip.setStyle(toolTipStyle);
                        Tooltip.install(bar, dashboardBarStickToolTip);
                    }
                    index++;
                }
            }
        }
    }

    private String getStringForIndex(int index) {
        switch (index) {
            case 0:
                return "Milk Tea";
            case 1:
                return "Coolers";
            case 2:
                return "Coffee";
            case 3:
                return "Ice Candy Cups";
            case 4:
                return "Appetizer";
            default:
                return "";
        }
    }

    private int getCountForIndex(int index) {
        switch (index) {
            case 0:
                return referenceMilkTeaCounter;
            case 1:
                return referenceCoolersCounter;
            case 2:
                return referenceCoffeeCounter;
            case 3:
                return referenceIceCandyCupsCounter;
            case 4:
                return referenceAppetizerCounter;
            default:
                return 0;
        }
    }

    private void updateUIBarChartLegends() {
        mainController.rectangleDashboardLegends1.setFill(Paint.valueOf(dashboardGraphColors[0]));
        mainController.rectangleDashboardLegends2.setFill(Paint.valueOf(dashboardGraphColors[1]));
        mainController.rectangleDashboardLegends3.setFill(Paint.valueOf(dashboardGraphColors[2]));
        mainController.rectangleDashboardLegends4.setFill(Paint.valueOf(dashboardGraphColors[3]));
        mainController.rectangleDashboardLegends5.setFill(Paint.valueOf(dashboardGraphColors[4]));
    }


    private void updateUIBestSeller() {
        if (topTenProducts.isEmpty()) {
            mainController.labelDashBoardNoSalesBestSeller.setVisible(true);
        } else {
            mainController.labelDashBoardNoSalesBestSeller.setVisible(false);

            mainController.flowPaneBestSeller.getChildren().clear();

            for (ProductOrder productOrder : topTenProducts) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setPrefWidth(441);
                anchorPane.setPrefHeight(161);

                ImageView imageView;

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
    }


    public void goToSystemManual() {
        mainController.mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
        mainController.settingsModel.openSelectedPane(SystemManual.getPaneNumber());
    }
}