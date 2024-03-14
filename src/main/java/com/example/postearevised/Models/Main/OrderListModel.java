package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.Product;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.ONE_SECOND;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.ORDER_QUEUE_DONE_BUTTON;
import static com.example.postearevised.Miscellaneous.References.OrderReference.*;

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
        addOrderToList(orderReference);
        updateOrderQueueLabels();
    }

    private void addOrderToList(Order order) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(350, 500);

        // Create Rectangle
        Rectangle rectangle = new Rectangle(350, 500);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);

        // Add drop shadow to AnchorPane
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
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
        innerAnchorPane.setOnMouseClicked(event -> orderContentClickedTouched());
        innerAnchorPane.setOnTouchReleased(event -> orderContentClickedTouched());
        innerAnchorPane.setCursor(Cursor.HAND);
        AnchorPane.setTopAnchor(innerAnchorPane, 99.0);
        AnchorPane.setLeftAnchor(innerAnchorPane, 28.0);

        // Create ImageView
        ImageView imageView = new ImageView();
        imageView.setImage(ORDER_QUEUE_DONE_BUTTON);
        imageView.setFitWidth(167);
        imageView.setFitHeight(55);
        imageView.setPreserveRatio(true);
        imageView.setOnMouseClicked(event -> orderDoneClickedTouched());
        imageView.setOnTouchReleased(event -> orderDoneClickedTouched());
        imageView.setCursor(Cursor.HAND);
        AnchorPane.setTopAnchor(imageView, 431.0);
        AnchorPane.setLeftAnchor(imageView, 95.0);

        // Add components to AnchorPane
        anchorPane.getChildren().addAll(rectangle, orderNumberLabel, customerNameLabel, innerAnchorPane, imageView);

        mainController.flowPaneOrderQueue.getChildren().add(anchorPane);
    }

    private void orderContentClickedTouched() {
        System.out.println("Inner AnchorPane clicked");
    }

    private void orderDoneClickedTouched() {
        System.out.println("ImageView clicked");
    }

    private void updateOrderQueueLabels() {
        mainController.labelOrderQueueOrderInQueue.setText(String.valueOf(orderObservableList.size()));
        mainController.labelOrderQueueTotalOrder.setText(String.valueOf(orderNumberReference - 1));
    }
}
