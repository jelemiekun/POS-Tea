package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductOrderListController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue.OrderHistoryAndOrderQueueCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.NotificationContents.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.productOrderListStage;
import static com.example.postearevised.Miscellaneous.References.StylesReference.*;

public class OrderListModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private ProductOrderListController productOrderListController;
    public void setProductOrderListController(ProductOrderListController productOrderListController) { this.productOrderListController = productOrderListController; }

    public void readImportedOrders() {
        if (!orderQueueObservableList.isEmpty()) {
            setOrderQueueImported();
            mainController.mainModel.generateNotification();
            for (Order order : orderQueueObservableList) {
                orderListOperationStartsHere(order);
            }
        }
    }

    public void orderListOperationStartsHere(Order order) {
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

        Rectangle rectangle = new Rectangle(350, 500);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setId("orderqueuebox");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(dropShadowColor);
        anchorPane.setEffect(dropShadow);

        Label orderNumberLabel = new Label("Order Number: " + order.getOrderNumber());
        orderNumberLabel.setFont(Font.font("Arial", 24));
        AnchorPane.setTopAnchor(orderNumberLabel, 16.0);
        AnchorPane.setLeftAnchor(orderNumberLabel, 16.0);

        Label customerNameLabel = new Label("Customer Name: " + order.getCustomerName());
        customerNameLabel.setFont(Font.font("Arial", 24));
        customerNameLabel.setPrefWidth(320);
        customerNameLabel.setAlignment(Pos.CENTER_LEFT);
        customerNameLabel.setContentDisplay(ContentDisplay.LEFT);
        customerNameLabel.setTextAlignment(TextAlignment.LEFT);
        AnchorPane.setTopAnchor(customerNameLabel, 54.0);
        AnchorPane.setLeftAnchor(customerNameLabel, 16.0);

        AnchorPane innerAnchorPane = new AnchorPane();
        innerAnchorPane.setPrefSize(297, 260);
        innerAnchorPane.setOnMouseClicked(event -> orderContentClickedTouched(order, anchorPane));
        innerAnchorPane.setOnTouchReleased(event -> orderContentClickedTouched(order, anchorPane));
        innerAnchorPane.setCursor(Cursor.HAND);
        AnchorPane.setTopAnchor(innerAnchorPane, 99.0);
        AnchorPane.setLeftAnchor(innerAnchorPane, 28.0);

        ObservableList<ProductOrder> productOrders = order.getProductOrderObservableList();
        double labelTop = 0.0;
        final double maxHeight = 245;
        for (ProductOrder productOrder : productOrders) {
            if (notExceedLabelTop(innerAnchorPane, labelTop, maxHeight)) {
                Label productNameLabel = new Label(productOrder.getProductName());
                productNameLabel.setPrefWidth(240);
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

            if (!productOrder.getThirdAttribute().isEmpty() && !productOrder.getThirdAttribute().equals(".")) {
                if (notExceedLabelTop(innerAnchorPane, labelTop, maxHeight)) {
                    Label firstAttributeLabel = new Label("    - " + productOrder.getThirdAttribute());
                    firstAttributeLabel.setFont(Font.font("Arial", 18));
                    AnchorPane.setTopAnchor(firstAttributeLabel, labelTop);
                    AnchorPane.setLeftAnchor(firstAttributeLabel, 16.0);
                    innerAnchorPane.getChildren().add(firstAttributeLabel);
                    labelTop += 20.0;
                } else {
                    break;
                }
            }

            if (!productOrder.getFirstAttribute().isEmpty() && !productOrder.getFirstAttribute().equals(".")) {
                if (notExceedLabelTop(innerAnchorPane, labelTop, maxHeight)) {
                    Label secondAttributeLabel = new Label("    - " + productOrder.getFirstAttribute());
                    secondAttributeLabel.setFont(Font.font("Arial", 18));
                    AnchorPane.setTopAnchor(secondAttributeLabel, labelTop);
                    AnchorPane.setLeftAnchor(secondAttributeLabel, 16.0);
                    innerAnchorPane.getChildren().add(secondAttributeLabel);
                    labelTop += 20.0;
                } else {
                    break;
                }
            }

            if (!productOrder.getSecondAttribute().isEmpty() && !productOrder.getSecondAttribute().equals(".")) {
                if (notExceedLabelTop(innerAnchorPane, labelTop, maxHeight)) {
                    Label thirdAttributeLabel = new Label("    - " + productOrder.getSecondAttribute());
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
        imageView.setId("menuproducthover");

        anchorPane.getChildren().addAll(rectangle, orderNumberLabel, customerNameLabel, innerAnchorPane, imageView);

        Tooltip orderQueueOrderAnchorPane = new Tooltip("Click to view");
        orderQueueOrderAnchorPane.setStyle(toolTipStyle);
        Tooltip.install(anchorPane, orderQueueOrderAnchorPane);

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

        productOrderListStage = new Stage();
        productOrderListStage.setTitle(PRODUCT_ORDER_LIST.getTITLE());
        productOrderListStage.setScene(new Scene(root));
        productOrderListStage.getIcons().add(SYSTEM_LOGO);
        productOrderListStage.setResizable(false);

        productOrderListStage.initModality(Modality.WINDOW_MODAL);
        productOrderListStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductOrderListController productOrderListController = loader.getController();
        productOrderListController.setOrderAndAnchorPane(order);
        productOrderListController.setContents();

        productOrderListStage.showAndWait();

        if (orderDone) {
            mainController.mainModel.hideRectangleModal();
            orderDoneClickedTouched(order, anchorPaneToDelete);

            orderDone = false;
        }
        mainController.mainModel.hideRectangleModal();
    }

    public void orderDoneClickedTouched(Order order, AnchorPane anchorPaneToDelete) {
        List<Order> passThisListToDeleteOrderInOrderQueueCSV = new ArrayList<>(Collections.singletonList(order));
        if (addOrderToCSV(order, false) && deleteOrdersInCSV(passThisListToDeleteOrderInOrderQueueCSV, true)) {
            if (openPrompt(order)) {
                addOrderToOrderHistory(order);
                removeOrderToOrderQueue(order, anchorPaneToDelete);
                notification();

                updateOrderQueueLabelsAndPane();
            }
        } else {
            setErrorAddingOrderToCSV();
            mainController.mainModel.openPrompt();
        }
    }

    private void notification() {
        setOrderCompleted();
        mainController.mainModel.generateNotification();
    }

    private boolean openPrompt(Order order) {
        setOrderSuccessful(String.valueOf(order.getOrderNumber()), order.getCustomerName());
        mainController.mainModel.openPrompt();
        return true;
    }

    private void removeOrderToOrderQueue(Order order, AnchorPane anchorPaneToDelete) {
        orderQueueObservableList.remove(order);
        mainController.flowPaneOrderQueue.getChildren().remove(anchorPaneToDelete);
    }

    private void addOrderToOrderHistory(Order order) {
        orderHistoryObservableList.add(order);
        mainController.orderHistoryModel.refreshOrderHistory();
    }


    public void updateOrderQueueLabelsAndPane() {
        mainController.labelOrderQueueOrderInQueue.setText(String.valueOf(orderQueueObservableList.size()));
        mainController.labelOrderQueueTotalOrder.setText(String.valueOf(orderHistoryObservableList.size()));
        mainController.anchorPaneOrderListNoOrders.setVisible(orderQueueObservableList.isEmpty());
        mainController.labelCustomerNumber.setText(String.valueOf(orderHistoryObservableList.size() + orderQueueObservableList.size() + 1));
    }
}
