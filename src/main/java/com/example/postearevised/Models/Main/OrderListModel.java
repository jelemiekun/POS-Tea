package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductOrderListController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.postearevised.Miscellaneous.Database.CSV.OrderHistory.OrderHistoryCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setErrorAddingOrderToCSV;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setOrderSuccessful;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;

public class OrderListModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private ProductOrderListController productOrderListController;
    public void setProductOrderListController(ProductOrderListController productOrderListController) { this.productOrderListController = productOrderListController; }

    /**
     * Date And Time
     */

    private LocalDateTime localDateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy - hh:mm:ss a");

    public void createAndStartDaemonThreadForDateAndTime() {
        Thread daemonThreadForDateAndTime = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    localDateTime = LocalDateTime.now();
                    String formattedDateTime = localDateTime.format(formatter);

                    Platform.runLater(() -> {
                        mainController.labelOrderQueueDateAndTIme.setText(formattedDateTime);
                    });
                    try {
                        Thread.sleep(ONE_SECOND);
                    } catch (InterruptedException e) {
                        errorMessage = e.getMessage();
                        logError(false);
                    }
                }
            }
        });

        daemonThreadForDateAndTime.setDaemon(true);

        daemonThreadForDateAndTime.start();
    }

    public void orderListOperationStartsHere(Order order) {
        System.out.println("orderListOperationStartsHere is orderReference list empty? " + order.getProductOrderObservableList().isEmpty());
        addOrderToList(order);
        updateOrderQueueLabelsAndPane();
    }
    private boolean notExceedLabelTop(AnchorPane innerAnchorPane, double labelTop, double maxHeight) {
        if (labelTop >= maxHeight) {
            labelTop += 25;
            Label moreLabel = new Label("More...");
            moreLabel.setFont(Font.font("Arial", 18));
            AnchorPane.setTopAnchor(moreLabel, labelTop);
            AnchorPane.setLeftAnchor(moreLabel, 16.0);
            innerAnchorPane.getChildren().add(moreLabel);
            return false;
        } else {
            return true;
        }
    }

    private void addOrderToList(Order order) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(350, 500);
        System.out.println("line 88: " + order.getProductOrderObservableList().isEmpty());

        // Create Rectangle
        Rectangle rectangle = new Rectangle(350, 500);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);

        // Add drop shadow to AnchorPane
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(dropShadowColor);
        anchorPane.setEffect(dropShadow);

        // Create Labels
        Label orderNumberLabel = new Label("Order Number: " + order.getOrderNumber());
        orderNumberLabel.setFont(Font.font("Arial", 24));
        AnchorPane.setTopAnchor(orderNumberLabel, 16.0);
        AnchorPane.setLeftAnchor(orderNumberLabel, 16.0);

        Label customerNameLabel = new Label("Customer Name: " + order.getCustomerName());
        customerNameLabel.setFont(Font.font("Arial", 24));
        AnchorPane.setTopAnchor(customerNameLabel, 54.0);
        AnchorPane.setLeftAnchor(customerNameLabel, 16.0);

        // Create inner AnchorPane
        AnchorPane innerAnchorPane = new AnchorPane();
        innerAnchorPane.setPrefSize(297, 260);
        innerAnchorPane.setOnMouseClicked(event -> orderContentClickedTouched(order, anchorPane));
        innerAnchorPane.setOnTouchReleased(event -> orderContentClickedTouched(order, anchorPane));
        innerAnchorPane.setCursor(Cursor.HAND);
        AnchorPane.setTopAnchor(innerAnchorPane, 99.0);
        AnchorPane.setLeftAnchor(innerAnchorPane, 28.0);

        ObservableList<ProductOrder> productOrders = order.getProductOrderObservableList();
        double labelTop = 0.0;
        final double maxHeight = 260;
        for (ProductOrder productOrder : productOrders) {
            if (notExceedLabelTop(innerAnchorPane, labelTop, maxHeight)) {
                Label productNameLabel = new Label(productOrder.getProductName());
                productNameLabel.setFont(Font.font("Arial", 22));
                AnchorPane.setTopAnchor(productNameLabel, labelTop);
                AnchorPane.setLeftAnchor(productNameLabel, 16.0);
                innerAnchorPane.getChildren().add(productNameLabel);

                Label quantityLabel = new Label(productOrder.getQuantity() + "x");
                quantityLabel.setFont(Font.font("Arial", 18));
                AnchorPane.setTopAnchor(quantityLabel, labelTop);
                AnchorPane.setRightAnchor(quantityLabel, 16.0);
                quantityLabel.setStyle("-fx-font-weight: bold;");
                innerAnchorPane.getChildren().add(quantityLabel);

                labelTop += 25.0;
            } else {
                break;
            }

            if (!productOrder.getFirstAttribute().isEmpty()) {
                if (notExceedLabelTop(innerAnchorPane, labelTop, maxHeight)) {
                    Label firstAttributeLabel = new Label("- " + productOrder.getFirstAttribute());
                    firstAttributeLabel.setFont(Font.font("Arial", 18));
                    AnchorPane.setTopAnchor(firstAttributeLabel, labelTop);
                    AnchorPane.setLeftAnchor(firstAttributeLabel, 16.0);
                    innerAnchorPane.getChildren().add(firstAttributeLabel);
                    labelTop += 20.0;
                } else {
                    break;
                }
            }

            if (!productOrder.getSecondAttribute().isEmpty()) {
                if (notExceedLabelTop(innerAnchorPane, labelTop, maxHeight)) {
                    Label secondAttributeLabel = new Label("- " + productOrder.getSecondAttribute());
                    secondAttributeLabel.setFont(Font.font("Arial", 18));
                    AnchorPane.setTopAnchor(secondAttributeLabel, labelTop);
                    AnchorPane.setLeftAnchor(secondAttributeLabel, 16.0);
                    innerAnchorPane.getChildren().add(secondAttributeLabel);
                    labelTop += 20.0;
                } else {
                    break;
                }
            }

            if (!productOrder.getThirdAttribute().isEmpty()) {
                if (notExceedLabelTop(innerAnchorPane, labelTop, maxHeight)) {
                    Label thirdAttributeLabel = new Label("- " + productOrder.getThirdAttribute());
                    thirdAttributeLabel.setFont(Font.font("Arial", 18));
                    AnchorPane.setTopAnchor(thirdAttributeLabel, labelTop);
                    AnchorPane.setLeftAnchor(thirdAttributeLabel, 16.0);
                    innerAnchorPane.getChildren().add(thirdAttributeLabel);
                    labelTop += 20.0;
                } else {
                    break;
                }
            }

            labelTop += 20.0;
        }

        System.out.println(labelTop);

        ImageView imageView = new ImageView();
        imageView.setImage(ORDER_QUEUE_DONE_BUTTON);
        imageView.setFitWidth(167);
        imageView.setFitHeight(55);
        imageView.setPreserveRatio(true);
        imageView.setOnMouseClicked(event -> orderDoneClickedTouched(order, anchorPane));
        imageView.setOnTouchReleased(event -> orderDoneClickedTouched(order, anchorPane));
        imageView.setCursor(Cursor.HAND);
        AnchorPane.setTopAnchor(imageView, 431.0);
        AnchorPane.setLeftAnchor(imageView, 95.0);

        anchorPane.getChildren().addAll(rectangle, orderNumberLabel, customerNameLabel, innerAnchorPane, imageView);

        mainController.flowPaneOrderQueue.getChildren().add(anchorPane);
    }

    private void orderContentClickedTouched(Order order, AnchorPane anchorPaneToDelete) {
        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PRODUCT_ORDER_LIST.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }

        Stage newStage = new Stage();
        newStage.setTitle(PRODUCT_ORDER_LIST.getTITLE());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductOrderListController productOrderListController = loader.getController();
        productOrderListController.setOrderAndAnchorPane(order);
        productOrderListController.setContents();

        newStage.showAndWait();

        if (orderDone) {
            mainController.mainModel.hideRectangleModal();
            orderDoneClickedTouched(order, anchorPaneToDelete);

            orderDone = false;
        }
        mainController.mainModel.hideRectangleModal();
    }

    public void orderDoneClickedTouched(Order order, AnchorPane anchorPaneToDelete) {
        orderDoneGetDateAndTime(order);
        if (addOrderToCSV(order)) {
            if (openPrompt(order)) {
                addOrderToOrderHistory(order);
                removeOrderToOrderQueue(order, anchorPaneToDelete);

                updateOrderQueueLabelsAndPane();
            }
        } else {
            setErrorAddingOrderToCSV();
            mainController.mainModel.openPrompt();
        }
    }

    private boolean openPrompt(Order order) {
        setOrderSuccessful(String.valueOf(order.getOrderNumber()), order.getCustomerName());
        return mainController.mainModel.openPrompt();
    }

    private void orderDoneGetDateAndTime(Order order) {
        order.setDateAndTime(LocalDateTime.now());
    }

    private void removeOrderToOrderQueue(Order order, AnchorPane anchorPaneToDelete) {
        orderQueueObservableList.remove(order);
        mainController.flowPaneOrderQueue.getChildren().remove(anchorPaneToDelete);
    }

    private void addOrderToOrderHistory(Order order) {
        orderHistoryObservableList.add(order);
    }


    public void updateOrderQueueLabelsAndPane() {
        mainController.labelOrderQueueOrderInQueue.setText(String.valueOf(orderQueueObservableList.size()));
        mainController.labelOrderQueueTotalOrder.setText(String.valueOf(orderHistoryObservableList.size()));
        mainController.anchorPaneOrderListNoOrders.setVisible(orderQueueObservableList.isEmpty());
        mainController.labelCustomerNumber.setText(String.valueOf(orderHistoryObservableList.size() + orderQueueObservableList.size() + 1));
    }
}
