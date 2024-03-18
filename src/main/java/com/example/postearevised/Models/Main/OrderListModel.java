package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.ProductOrder;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;

public class OrderListModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

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
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        daemonThreadForDateAndTime.setDaemon(true);

        daemonThreadForDateAndTime.start();
    }

    public void orderListOperationStartsHere() {
        Order order = getOrderReference();
        System.out.println("orderListOperationStartsHere is orderReference list empty? " + order.getProductOrderObservableList().isEmpty());
        addOrderToList(order);
        updateOrderQueueLabelsAndPane();
    }

    private Order getOrderReference() {
        return new Order(orderReference.getProductOrderObservableList(), orderReference.getCustomerName(), orderReference.getOrderNumber(), orderReference.getTotalPrice(), orderReference.getAmountPaid(), orderReference.getChange(), orderReference.getModeOfPayment());
    }

    private boolean notExceedLabelTop(AnchorPane innerAnchorPane, double labelTop) {
        if (labelTop >= 260) {
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
        innerAnchorPane.setPrefSize(297, 321);
        innerAnchorPane.setOnMouseClicked(event -> orderContentClickedTouched(order));
        innerAnchorPane.setOnTouchReleased(event -> orderContentClickedTouched(order));
        innerAnchorPane.setCursor(Cursor.HAND);
        AnchorPane.setTopAnchor(innerAnchorPane, 99.0);
        AnchorPane.setLeftAnchor(innerAnchorPane, 28.0);

        ObservableList<ProductOrder> productOrders = order.getProductOrderObservableList();
        double labelTop = 0.0;
        for (ProductOrder productOrder : productOrders) {
            if (notExceedLabelTop(innerAnchorPane, labelTop)) {
                Label productNameLabel = new Label(productOrder.getProductName());
                productNameLabel.setFont(Font.font("Arial", 22));
                AnchorPane.setTopAnchor(productNameLabel, labelTop);
                AnchorPane.setLeftAnchor(productNameLabel, 16.0);
                innerAnchorPane.getChildren().add(productNameLabel);

                Label quantityLabel = new Label("x" + productOrder.getQuantity());
                quantityLabel.setFont(Font.font("Arial", 18));
                AnchorPane.setTopAnchor(quantityLabel, labelTop);
                AnchorPane.setRightAnchor(quantityLabel, 16.0);
                quantityLabel.setStyle("-fx-font-weight: bold;");
                innerAnchorPane.getChildren().add(quantityLabel);

                labelTop += 25.0;
            } else {
                break;
            }

            if (!productOrder.getFirstAttribute().isEmpty() && notExceedLabelTop(innerAnchorPane, labelTop)) {
                Label firstAttributeLabel = new Label("- " + productOrder.getFirstAttribute());
                firstAttributeLabel.setFont(Font.font("Arial", 18));
                AnchorPane.setTopAnchor(firstAttributeLabel, labelTop);
                AnchorPane.setLeftAnchor(firstAttributeLabel, 16.0);
                innerAnchorPane.getChildren().add(firstAttributeLabel);
                labelTop += 20.0;
            } else {
                break;
            }

            if (!productOrder.getSecondAttribute().isEmpty() && notExceedLabelTop(innerAnchorPane, labelTop)) {
                Label secondAttributeLabel = new Label("- " + productOrder.getSecondAttribute());
                secondAttributeLabel.setFont(Font.font("Arial", 18));
                AnchorPane.setTopAnchor(secondAttributeLabel, labelTop);
                AnchorPane.setLeftAnchor(secondAttributeLabel, 16.0);
                innerAnchorPane.getChildren().add(secondAttributeLabel);
                labelTop += 20.0;
            } else {
                break;
            }

            if (!productOrder.getThirdAttribute().isEmpty() && notExceedLabelTop(innerAnchorPane, labelTop)) {
                Label thirdAttributeLabel = new Label("- " + productOrder.getThirdAttribute());
                thirdAttributeLabel.setFont(Font.font("Arial", 18));
                AnchorPane.setTopAnchor(thirdAttributeLabel, labelTop);
                AnchorPane.setLeftAnchor(thirdAttributeLabel, 16.0);
                innerAnchorPane.getChildren().add(thirdAttributeLabel);
                labelTop += 20.0;
            } else {
                break;
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

        clearOrderReference();
    }

    private void orderContentClickedTouched(Order order) {
        System.out.println("line 207: " + order.getProductOrderObservableList().isEmpty());
        System.out.println("Inner AnchorPane clicked");
    }

    private void orderDoneClickedTouched(Order order, AnchorPane anchorPaneToDelete) {
        System.out.println("line 212: " + order.getProductOrderObservableList().isEmpty());
        orderDoneGetDateAndTime(order);
        addOrderToOrderHistory(order);
        removeOrderToOrderQueue(order, anchorPaneToDelete);

        System.out.println("Done clicked");

        updateOrderQueueLabelsAndPane();
    }

    private void orderDoneGetDateAndTime(Order order) {
        order.setDateAndTime(LocalDateTime.now());
    }

    private void removeOrderToOrderQueue(Order order, AnchorPane anchorPaneToDelete) {
        System.out.println("line 227: " + order.getProductOrderObservableList());
        orderQueueObservableList.remove(order);
        mainController.flowPaneOrderQueue.getChildren().remove(anchorPaneToDelete);
    }

    private void addOrderToOrderHistory(Order order) {
        synchronized (synchronizedOrderHistoryObservableList) {
            synchronizedOrderHistoryObservableList.add(order);
        }
    }


    private void updateOrderQueueLabelsAndPane() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mainController.labelOrderQueueOrderInQueue.setText(String.valueOf(orderQueueObservableList.size()));
                mainController.labelOrderQueueTotalOrder.setText(String.valueOf(synchronizedOrderHistoryObservableList.size()));
                mainController.anchorPaneOrderListNoOrders.setVisible(orderQueueObservableList.isEmpty());
            }
        });
    }

    private void clearOrderReference() {
        orderReference = null;
    }
}
