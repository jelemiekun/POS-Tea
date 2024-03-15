package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.Enums.EnumProduct;
import com.example.postearevised.Objects.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.ModeOfPayment.*;
import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductOrderReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

public class MenuModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(-7);
        dropShadow.setOffsetY(0);
        dropShadow.setColor(dropShadowColor);

        mainController.anchorPaneRightPanel.setEffect(dropShadow);
    }

    public void checkIfIsMenuEmpty() {
        removeFlowPaneChildren();

        if (allProductObservableList.isEmpty()) { //availableAllProductObservableList dapat to
            updateElementsIfEmpty();
        } else {
            updateElementsIfNotEmpty();
        }
    }

    private void removeFlowPaneChildren() {
        mainController.flowPaneMenu.getChildren().clear();
    }

    private void updateElementsIfEmpty() {
        mainController.anchorPaneMenuIsEmpty.setVisible(true);
        mainController.labelMenuCategorySelected.setText("Menu");
        mainController.labelMenuCategoryResultCounter.setText("");

        mainController.imageViewMenuAll.setImage(allCategory);
        mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
        mainController.imageViewMenuCoolers.setImage(coolersCategory);
        mainController.imageViewMenuCoffee.setImage(coffeeCategory);
        mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
        mainController.imageViewMenuAppetizers.setImage(appetizersCategory);

        mainController.labelNoOrdersSelected.setVisible(true);
        mainController.anchorPaneHideHalfRightPanel.setVisible(false);
    }

    private void updateElementsIfNotEmpty() {
        mainController.anchorPaneMenuIsEmpty.setVisible(false);

        switchCategory(AllProductCategoryEnum.getNumber());
    }

    public void switchCategory(int categoryNumber) {
        switchCategorySetVisibilities(categoryNumber);
        switchCategorySetTexts(categoryNumber);
        switchCategorySetFlowPaneChildren(categoryNumber);
    }

    private void switchCategorySetVisibilities(int categoryNumber) {
        if (!allProductObservableList.isEmpty()) {//availableAllProductObservableList dapat to
            switch (categoryNumber) {
                case 1:
                    mainController.imageViewMenuMilkTea.setImage(milkTeasCategorySelected);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 2:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategorySelected);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 3:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategorySelected);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 4:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategorySelected);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 5:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategorySelected);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 6:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategorySelected);
                    break;
            }
        }
    }

    private void switchCategorySetTexts(int categoryNumber) {
        if (!allProductObservableList.isEmpty()) {//availableAllProductObservableList dapat to
            switch (categoryNumber) {
                case 1:
                    mainController.labelMenuCategorySelected.setText("Milk Tea Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableMilkTeaObservableList.size() + " Milk Teas Result");
                    break;
                case 2:
                    mainController.labelMenuCategorySelected.setText("Coolers Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableCoolersObservableList.size() + " Coolers Result");
                    break;
                case 3:
                    mainController.labelMenuCategorySelected.setText("Coffee Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableCoffeeObservableList.size() + " Coffees Result");
                    break;
                case 4:
                    mainController.labelMenuCategorySelected.setText("Ice Candy Cups Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableIceCandyCupsObservableList.size() + " Ice Candy Cups Result");
                    break;
                case 5:
                    mainController.labelMenuCategorySelected.setText("Appetizers Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableAppetizerObservableList.size() + " Appetizers Result");
                    break;
                case 6:
                    mainController.labelMenuCategorySelected.setText("Menu");
                    mainController.labelMenuCategoryResultCounter.setText(allProductObservableList.size() + " Result");//availableAllProductObservableList dapat to
                    break;
            }
        }
    }

    private void switchCategorySetFlowPaneChildren(int categoryNumber) {
        removeFlowPaneChildren();
        switch (categoryNumber) {
            case 1:
                for (Product product : availableMilkTeaObservableList) {
                    addProductToFlowPane(product, product.getProductName(), product.getImage());
                }
                break;
            case 2:
                for (Product product : availableCoolersObservableList) {
                    addProductToFlowPane(product, product.getProductName(), product.getImage());
                }
                break;
            case 3:
                for (Product product : availableCoffeeObservableList) {
                    addProductToFlowPane(product, product.getProductName(), product.getImage());
                }
                break;
            case 4:
                for (Product product : availableIceCandyCupsObservableList) {
                    addProductToFlowPane(product, product.getProductName(), product.getImage());
                }
                break;
            case 5:
                for (Product product : availableAppetizerObservableList) {
                    addProductToFlowPane(product, product.getProductName(), product.getImage());
                }
                break;
            case 6:
                for (Product product : allProductObservableList) {
                    addProductToFlowPane(product, product.getProductName(), product.getImage());
                }
                break;
        }
    }

    private void addProductToFlowPane(Product product, String productName, Image imageProduct) {
        double width = 320;
        double height = 200;

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setCursor(Cursor.HAND);

        Label label = new Label(productName);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        label.setTextFill(Color.WHITE);

        ImageView imageView = new ImageView();
        imageView.setImage(imageProduct);

        Rectangle shadow = new Rectangle(width, height);
        shadow.setFill(Color.BLACK);
        shadow.setOpacity(0.35);

        anchorPane.getChildren().addAll(imageView, shadow, label);

        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        AnchorPane.setBottomAnchor(label, 15.0);
        AnchorPane.setLeftAnchor(label, 20.0);

        anchorPane.setOnMouseClicked(event -> {
            openProductSelectedFXML(product);
            event.consume();
        });

        anchorPane.setOnTouchPressed(event -> {
            openProductSelectedFXML(product);
            event.consume();
        });

        mainController.flowPaneMenu.getChildren().add(anchorPane);
    }

    private void openProductSelectedFXML(Product product) {
        editOrShowSelectedProduct = product;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/postearevised/Scenes/Additional/Product.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();
        newStage.setTitle(EnumProduct.Product.getTitle());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setSelectedProduct();

        newStage.showAndWait();

        checkIfAddingProductToOrderSuccess(product);
        clearSelectedProductReference();
//        isAddingProductSuccess();
    }

    private void checkIfAddingProductToOrderSuccess(Product product) {
        if (isProductOrderAdded) {
            setPanesAfterAddingOrder();
            setTextFieldListeners();
            createAnchorPane(product);

            isProductOrderAdded = false;
        }
    }

    private void setPanesAfterAddingOrder() {
        mainController.labelNoOrdersSelected.setVisible(false);
        mainController.anchorPaneHideHalfRightPanel.setVisible(true);

        mainController.imageGCash.setImage(GCASH);
        mainController.labelGCash.setTextFill(Color.BLACK);
        mainController.imageCash.setImage(CASH);
        mainController.labelCash.setTextFill(Color.BLACK);
    }

    private void setTextFieldListeners() {
        mainController.textFieldMenuCustomerName.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
                mainController.textFieldMenuCustomerName.setText(oldValue);
            }
        }));

        mainController.textFieldMenuEnterAmount.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                mainController.textFieldMenuEnterAmount.setText(oldValue);
            }
        }));
    }

    private void noOrderSelected() {
        mainController.labelNoOrdersSelected.setVisible(true);
        mainController.anchorPaneHideHalfRightPanel.setVisible(false);
        mainController.flowPaneOrdersSelected.getChildren().clear();
    }

    public void createAnchorPane(Product product) {
        ProductOrder productOrder = productOrderReference;
        final int productPrice = productOrder.getTotalAmount();

        ProductOrder existingOrder = null;
        for (ProductOrder order : referenceProductOrderObservableList) {
            if (order.getProductName().equals(productOrder.getProductName()) &&
                    order.getProductImage().equals(productOrder.getProductImage()) &&
                    order.getFirstAttribute().equals(productOrder.getFirstAttribute()) &&
                    order.getSecondAttribute().equals(productOrder.getSecondAttribute()) &&
                    order.getThirdAttribute().equals(productOrder.getThirdAttribute())) {
                existingOrder = order;
                break;
            }
        }


        // If the product exists, increase its quantity
        if (existingOrder != null) {
            anchorPaneProductsInOrderAddQuantityOnAction(existingOrder, existingOrder.getLabelQuantity(), existingOrder.getLabelPrice(), productPrice);
            updateTotalAmountOfProduct(existingOrder, existingOrder.getLabelPrice(), productPrice);
            updateTotalAmountOfOrder();
            return; // Exit the method since no new pane needs to be created
        }

        referenceProductOrderObservableList.add(productOrder);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setLayoutX(10.0);
        anchorPane.setPrefWidth(435.0);
        anchorPane.setPrefHeight(116.0);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(436.0);
        rectangle.setHeight(115.0);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setArcWidth(5.0);
        rectangle.setArcHeight(5.0);
        anchorPane.getChildren().add(rectangle);

        AnchorPane innerAnchorPane = new AnchorPane();
        innerAnchorPane.setCursor(Cursor.HAND);
        innerAnchorPane.setLayoutX(47.0);
        innerAnchorPane.setLayoutY(15.0);
        innerAnchorPane.setPrefWidth(200.0);
        innerAnchorPane.setPrefHeight(84.0);
        innerAnchorPane.setOnMouseClicked(event -> anchorPaneEditSelectedOrderProduct(productOrder, product));
        innerAnchorPane.setOnTouchReleased(event -> anchorPaneEditSelectedOrderProduct(productOrder, product));

        ImageView imageView = new ImageView();
        imageView.setFitWidth(99.0);
        imageView.setFitHeight(85.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(productOrder.getProductImage());
        AnchorPane.setLeftAnchor(imageView, 0.0);
        AnchorPane.setTopAnchor(imageView, 0.0);
        innerAnchorPane.getChildren().add(imageView);

        Label label1 = new Label(productOrder.getProductName());
        label1.setLayoutX(104.0);
        label1.setLayoutY(1.0);
        label1.setFont(new javafx.scene.text.Font("Arial", 24.0));
        innerAnchorPane.getChildren().add(label1);

        Label label6 = new Label(productOrder.getThirdAttribute());
        label6.setLayoutX(105.0);
        label6.setLayoutY(30);
        innerAnchorPane.getChildren().add(label6);

        Label label2 = new Label(productOrder.getFirstAttribute());
        label2.setLayoutX(105.0);
        label2.setLayoutY(47.0);
        innerAnchorPane.getChildren().add(label2);

        Label label3 = new Label(productOrder.getSecondAttribute());
        label3.setLayoutX(105.0);
        label3.setLayoutY(64.0);
        innerAnchorPane.getChildren().add(label3);

        anchorPane.getChildren().add(innerAnchorPane);

        Label label4 = productOrder.getLabelPrice();
        label4.setText("₱" + ((int) productOrder.getTotalAmount()) + ".00");
        label4.setLayoutX(355.0);
        label4.setLayoutY(43.0);
        label4.setFont(new javafx.scene.text.Font("Arial", 18.0));
        anchorPane.getChildren().add(label4);

        Label label5 = productOrder.getLabelQuantity();
        label5.setText(String.valueOf(productOrder.getQuantity()));
        label5.setLayoutX(290.0);
        label5.setLayoutY(45.0);
        label5.setFont(new javafx.scene.text.Font("Arial", 19.0));
        anchorPane.getChildren().add(label5);

        Line line1 = new Line();
        line1.setStartX(-40.0);
        line1.setStartY(-29.0);
        line1.setEndX(-40.0);
        line1.setEndY(86.0);
        line1.setLayoutX(292.0);
        line1.setLayoutY(29.0);
        anchorPane.getChildren().add(line1);

        Line line2 = new Line();
        line2.setStartX(-40.0);
        line2.setStartY(-29.0);
        line2.setEndX(-40.0);
        line2.setEndY(86.0);
        line2.setLayoutX(374.0);
        line2.setLayoutY(29.0);
        anchorPane.getChildren().add(line2);

        AnchorPane minusQuantityAnchorPane = new AnchorPane();
        minusQuantityAnchorPane.setCursor(Cursor.HAND);
        minusQuantityAnchorPane.setLayoutX(261.0);
        minusQuantityAnchorPane.setLayoutY(39.0);
        minusQuantityAnchorPane.setPrefWidth(22.0);
        minusQuantityAnchorPane.setPrefHeight(35.0);
        minusQuantityAnchorPane.setOnMouseClicked(event -> anchorPaneProductsInOrderMinusQuantityOnAction(productOrder, label5, label4, productPrice));
        minusQuantityAnchorPane.setOnTouchReleased(event -> anchorPaneProductsInOrderMinusQuantityOnAction(productOrder, label5, label4, productPrice));

        Label minusLabel = new Label("-");
        minusLabel.setLayoutX(5.0);
        minusLabel.setLayoutY(-6.0);
        minusLabel.setFont(new javafx.scene.text.Font("Arial", 34.0));
        minusQuantityAnchorPane.getChildren().add(minusLabel);

        anchorPane.getChildren().add(minusQuantityAnchorPane);

        AnchorPane addQuantityAnchorPane = new AnchorPane();
        addQuantityAnchorPane.setCursor(Cursor.HAND);
        addQuantityAnchorPane.setLayoutX(304.0);
        addQuantityAnchorPane.setLayoutY(38.0);
        addQuantityAnchorPane.setPrefWidth(22.0);
        addQuantityAnchorPane.setPrefHeight(35.0);
        addQuantityAnchorPane.setOnMouseClicked(event -> anchorPaneProductsInOrderAddQuantityOnAction(productOrder, label5, label4, productPrice));
        addQuantityAnchorPane.setOnTouchReleased(event -> anchorPaneProductsInOrderAddQuantityOnAction(productOrder, label5, label4, productPrice));

        Label addLabel = new Label("+");
        addLabel.setLayoutX(3.0);
        addLabel.setFont(new javafx.scene.text.Font("Arial", 30.0));
        addQuantityAnchorPane.getChildren().add(addLabel);

        anchorPane.getChildren().add(addQuantityAnchorPane);

        ImageView deleteImageView = new ImageView();
        deleteImageView.setCursor(Cursor.HAND);
        deleteImageView.setLayoutX(8.0);
        deleteImageView.setLayoutY(44.0);
        deleteImageView.setFitWidth(25.0);
        deleteImageView.setFitHeight(25.0);
        deleteImageView.setOnMouseClicked(event -> deleteProductInOrderOnAction(anchorPane, productOrder));
        deleteImageView.setOnTouchReleased(event -> deleteProductInOrderOnAction(anchorPane, productOrder));
        deleteImageView.setPickOnBounds(true);
        deleteImageView.setPreserveRatio(true);
        deleteImageView.setImage(CLOSE);
        anchorPane.getChildren().add(deleteImageView);

        updateTotalAmountOfOrder();

        mainController.flowPaneOrdersSelected.getChildren().add(anchorPane);
    }

    private void anchorPaneEditSelectedOrderProduct(ProductOrder productOrder, Product product) {
        editOrShowSelectedProduct = product;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/postearevised/Scenes/Additional/Product.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();
        newStage.setTitle(EnumProduct.Product.getTitle());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setEditOrderProduct(productOrder);

        newStage.showAndWait();

        checkIfAddingProductToOrderSuccess(product);
        clearSelectedProductReference();
    }

    // Event handlers
    private void anchorPaneProductsInOrderMinusQuantityOnAction(ProductOrder productOrder, Label labelPrice, Label labelAmount, int price) {
        if (productOrder.getQuantity() > 1) {
            int newQuantity = productOrder.getQuantity();
            newQuantity--;

            productOrder.setQuantity(newQuantity);
            labelPrice.setText(String.valueOf(newQuantity));

            updateTotalAmountOfProduct(productOrder, labelAmount, price);

            updateTotalAmountOfOrder();
        }
    }

    private void anchorPaneProductsInOrderAddQuantityOnAction(ProductOrder productOrder, Label labelPrice, Label labelAmount, int price) {
        int newQuantity = productOrder.getQuantity();
        newQuantity++;

        productOrder.setQuantity(newQuantity);
        labelPrice.setText(String.valueOf(newQuantity));

        updateTotalAmountOfProduct(productOrder, labelAmount, price);
        updateTotalAmountOfOrder();
    }

    private void deleteProductInOrderOnAction(AnchorPane anchorPaneToDelete, ProductOrder productOrder) {
        referenceProductOrderObservableList.remove(productOrder);
        mainController.flowPaneOrdersSelected.getChildren().remove(anchorPaneToDelete);

        if (referenceProductOrderObservableList.isEmpty())
            noOrderSelected();

        updateTotalAmountOfOrder();
    }
    private void updateTotalAmountOfProduct(ProductOrder productOrder, Label label, int price) {
        int totalAmount = productOrder.getQuantity() * price;

        productOrder.setTotalAmount(totalAmount);
        label.setText("₱" + ((int) productOrder.getTotalAmount()) + ".00");
    }

    private void updateTotalAmountOfOrder() {
        referenceTotalPrice = 0;
        System.out.println("Is reference product order empty? " + referenceProductOrderObservableList.isEmpty());
        for (ProductOrder productOrder : referenceProductOrderObservableList) {
            System.out.println("Product name in order: " + productOrder.getProductName());
            System.out.println("Total price reference: " + referenceTotalPrice);
            referenceTotalPrice += productOrder.getTotalAmount();
        }

        mainController.labelMenuTotalPrice.setText("₱" + referenceTotalPrice + ".00");
    }

    private void clearSelectedProductReference() {
        editOrShowSelectedProduct = null;
    }

    public void updateModeOfPayment(boolean isCash) {
        if (isCash)
            cashSelected();
        else
            gCashSelected();
    }

    private void cashSelected() {
        mainController.imageCash.setImage(CASH_SELECTED);
        mainController.labelCash.setTextFill(Color.WHITE);
        referenceModeOfPayment = Cash.getModeOfPayment();

        mainController.imageGCash.setImage(GCASH);
        mainController.labelGCash.setTextFill(Color.BLACK);
    }

    private void gCashSelected() {
        mainController.imageGCash.setImage(GCASH_SELECTED);
        mainController.labelGCash.setTextFill(Color.WHITE);
        referenceModeOfPayment = GCash.getModeOfPayment();

        mainController.imageCash.setImage(CASH);
        mainController.labelCash.setTextFill(Color.BLACK);
    }

    public void orderCancelledOrAddedToQueue() {
        clearProductOrderReferences();
        noOrderSelected();
        updateTotalAmountOfOrder();
        clearFields();
    }

    private void clearFields() {
        mainController.anchorPaneMenu.requestFocus();
        mainController.textFieldMenuCustomerName.setText("");
        mainController.textFieldMenuEnterAmount.setText("");
    }

    public void payClicked() {
        boolean proceed = checkCustomerName() && checkAmountPaid() && checkModeOfPayment();

        if (proceed) {
            setAttributes();
            addToOrderQueue();
            orderCancelledOrAddedToQueue();
            clearFields();
            incrementCustomerNumber();
        }
    }

    private void incrementCustomerNumber() {
        orderNumberReference++;
        mainController.labelCustomerNumber.setText(String.valueOf(orderNumberReference));
    }

    private void setAttributes() {
        referenceOrderNumber = orderNumberReference;
    }

    private boolean checkCustomerName() {
        referenceCustomerName = mainController.textFieldMenuCustomerName.getText();

        if (!referenceCustomerName.isBlank()) {
            return true;
        } else {
            System.out.println("Blank name");
            // blank yung name, anong prompt/error
            return false;
        }
    }

    private boolean checkAmountPaid() {
        if (!mainController.textFieldMenuEnterAmount.getText().isBlank()) {
            referenceAmountPaid = (int) Double.parseDouble(mainController.textFieldMenuEnterAmount.getText());

            if (referenceAmountPaid >= referenceTotalPrice) {
                return true;
            } else {
                System.out.println("Insufficient amount");
                // insufficient amount, anong prompt/error
            }
        } else {
            System.out.println("Blank amount");
            // blank yung amount, anong prompt/error
        }
        return false;
    }

    private boolean checkModeOfPayment() {
        if (!referenceModeOfPayment.isBlank()) {
            return true;
        } else {
            System.out.println("No payment selected");
            // blank yung payment, anong prompt/error
            return false;
        }
    }

    private void addToOrderQueue() {
        referenceChange = getChange();

        Order order = new Order(referenceProductOrderObservableList, referenceCustomerName, referenceOrderNumber,
                                referenceTotalPrice, referenceAmountPaid, referenceChange, referenceModeOfPayment);
        orderQueueObservableList.add(order);

        setOrderReference(order);
        invokeOrderListStartsHereMethod();
        clearOrderReference();
    }

    private int getChange() {
        return referenceAmountPaid - referenceTotalPrice;
    }

    private void setOrderReference(Order order) {
        orderReference = order;
    }

    private void invokeOrderListStartsHereMethod() {
        mainController.orderListModel.orderListOperationStartsHere();
    }

    private void clearOrderReference() {
        orderReference = null;
    }

//    orderReference = null;
//    productOrderReference = null;
//    isProductOrderAdded = false;
//        referenceProductOrderObservableList.clear();
//    referenceCustomerName = "";
//    referenceTotalPrice = 0;
//    referenceAmountPaid = 0;
//    referenceChange = 0;
//    referenceModeOfPayment = "";
//    referenceDateAndTime = null;
}
