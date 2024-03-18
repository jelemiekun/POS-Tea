package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.Enums.EnumProduct;
import com.example.postearevised.Objects.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import static com.example.postearevised.Miscellaneous.Enums.MainPane.*;
import static com.example.postearevised.Miscellaneous.Enums.ModeOfPayment.*;
import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPane.SystemManual;
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

    public void goToSystemManual() throws IOException {
        mainController.mainModel.openSelectedPane(Settings.getPaneNumber());
        mainController.settingsModel.openSelectedPane(SystemManual.getPaneNumber());
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

        mainController.labelMenuNoName.setVisible(false);
        mainController.labelMenuNoAmount.setVisible(false);
        mainController.labelMenuNoModeOfPayment.setVisible(false);
    }

    private boolean productNotExisted(ProductOrder productOrder, int productPrice) {
        ProductOrder existingOrder = isExistingOrder(productOrder);

        // If the product exists, increase its quantity
        if (existingOrder != null) {
            productExists(existingOrder, productPrice);
            return false; // Exit the method since no new pane needs to be created
        } else {
            return true;
        }
    }

    private synchronized ProductOrder isExistingOrder(ProductOrder productOrder) {
        for (ProductOrder order : referenceProductOrderObservableList) {
            if (order.getProductName().equals(productOrder.getProductName()) &&
                    order.getProductImage().equals(productOrder.getProductImage()) &&
                    order.getFirstAttribute().equals(productOrder.getFirstAttribute()) &&
                    order.getSecondAttribute().equals(productOrder.getSecondAttribute()) &&
                    order.getThirdAttribute().equals(productOrder.getThirdAttribute())) {
                return order;
            }
        }
        return  null;
    }

    private void productExists(ProductOrder existingOrder, int productPrice) {
        anchorPaneProductsInOrderAddQuantityOnAction(existingOrder, existingOrder.getLabelQuantity(), existingOrder.getLabelPrice(), productPrice);
        updateTotalAmountOfProduct(existingOrder, existingOrder.getLabelPrice(), productPrice);
        updateTotalAmountOfOrder();
    }

    public synchronized void createAnchorPane(Product product) {
        ProductOrder productOrder = referenceProductOrder;
        final int productPrice = productOrder.getTotalAmount();

        if (productNotExisted(productOrder, productPrice)) {
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

            ImageView imageView = new ImageView();
            imageView.setFitWidth(99.0);
            imageView.setFitHeight(85.0);
            imageView.setPickOnBounds(true);
            imageView.setImage(productOrder.getProductImage());
            AnchorPane.setLeftAnchor(imageView, 0.0);
            AnchorPane.setTopAnchor(imageView, 0.0);
            innerAnchorPane.getChildren().add(imageView);

            Label labelProductName = new Label(productOrder.getProductName());
            labelProductName.setLayoutX(104.0);
            labelProductName.setLayoutY(1.0);
            labelProductName.setFont(new javafx.scene.text.Font("Arial", 24.0));
            innerAnchorPane.getChildren().add(labelProductName);

            Label labelFirstAttribute = new Label(productOrder.getThirdAttribute());
            labelFirstAttribute.setLayoutX(105.0);
            labelFirstAttribute.setLayoutY(30);
            innerAnchorPane.getChildren().add(labelFirstAttribute);

            Label labelSecondAttribute = new Label(productOrder.getFirstAttribute());
            labelSecondAttribute.setLayoutX(105.0);
            labelSecondAttribute.setLayoutY(47.0);
            innerAnchorPane.getChildren().add(labelSecondAttribute);

            Label labelThirdAttribute = new Label(productOrder.getSecondAttribute());
            labelThirdAttribute.setLayoutX(105.0);
            labelThirdAttribute.setLayoutY(64.0);
            innerAnchorPane.getChildren().add(labelThirdAttribute);

            anchorPane.getChildren().add(innerAnchorPane);

            Label labelProductPrice = productOrder.getLabelPrice();
            labelProductPrice.setText("₱" + (productOrder.getTotalAmount()) + ".00");
            labelProductPrice.setLayoutX(355.0);
            labelProductPrice.setLayoutY(43.0);
            labelProductPrice.setFont(new javafx.scene.text.Font("Arial", 18.0));
            anchorPane.getChildren().add(labelProductPrice);

            Label labelQuantity = productOrder.getLabelQuantity();
            labelQuantity.setText(String.valueOf(productOrder.getQuantity()));
            labelQuantity.setLayoutX(290.0);
            labelQuantity.setLayoutY(45.0);
            labelQuantity.setFont(new javafx.scene.text.Font("Arial", 19.0));
            anchorPane.getChildren().add(labelQuantity);

            innerAnchorPane.setOnMouseClicked(event -> anchorPaneEditSelectedOrderProduct(productOrder, product, labelFirstAttribute, labelSecondAttribute, labelThirdAttribute, labelProductPrice, labelQuantity));
            innerAnchorPane.setOnTouchReleased(event -> anchorPaneEditSelectedOrderProduct(productOrder, product, labelFirstAttribute, labelSecondAttribute, labelThirdAttribute, labelProductPrice, labelQuantity));

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
            minusQuantityAnchorPane.setOnMouseClicked(event -> anchorPaneProductsInOrderMinusQuantityOnAction(productOrder, labelQuantity, labelProductPrice, productPrice));
            minusQuantityAnchorPane.setOnTouchReleased(event -> anchorPaneProductsInOrderMinusQuantityOnAction(productOrder, labelQuantity, labelProductPrice, productPrice));

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
            addQuantityAnchorPane.setOnMouseClicked(event -> anchorPaneProductsInOrderAddQuantityOnAction(productOrder, labelQuantity, labelProductPrice, productPrice));
            addQuantityAnchorPane.setOnTouchReleased(event -> anchorPaneProductsInOrderAddQuantityOnAction(productOrder, labelQuantity, labelProductPrice, productPrice));

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
            deleteImageView.setOnMouseClicked(event -> deleteProductOrderInOrderOnAction(anchorPane, productOrder));
            deleteImageView.setOnTouchReleased(event -> deleteProductOrderInOrderOnAction(anchorPane, productOrder));
            deleteImageView.setPickOnBounds(true);
            deleteImageView.setPreserveRatio(true);
            deleteImageView.setImage(CLOSE);
            anchorPane.getChildren().add(deleteImageView);

            updateTotalAmountOfOrder();

            mainController.flowPaneOrdersSelected.getChildren().add(anchorPane);
        }
    }

    private void anchorPaneEditSelectedOrderProduct(ProductOrder productOrder, Product product, Label firstAttribute, Label secondAttribute, Label thirdAttribute, Label productPrice, Label quantity) {
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
        productController.productModel.setEditOrderProduct();

        newStage.showAndWait();
    }

    // Event handlers
    private void anchorPaneProductsInOrderMinusQuantityOnAction(ProductOrder productOrder, Label labelQuantity, Label labelProductPrice, int price) {
        if (productOrder.getQuantity() > 1) {
            int newQuantity = productOrder.getQuantity();
            newQuantity--;

            productOrder.setQuantity(newQuantity);
            labelQuantity.setText(String.valueOf(newQuantity));

            updateTotalAmountOfProduct(productOrder, labelProductPrice, price);

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

    private void deleteProductOrderInOrderOnAction(AnchorPane anchorPaneToDelete, ProductOrder productOrder) {
        referenceProductOrderObservableList.remove(productOrder);

        synchronized (mainController.flowPaneOrdersSelected.getChildren()) {
            mainController.flowPaneOrdersSelected.getChildren().remove(anchorPaneToDelete);
        }

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
        System.out.println("Is reference product order empty? (updateTotalAmountOfOrder) " + referenceProductOrderObservableList.isEmpty());
        for (ProductOrder productOrder : referenceProductOrderObservableList) {
            System.out.println("Product name in order: " + productOrder.getProductName());
            System.out.println("Total price reference: " + referenceTotalPrice);
            referenceTotalPrice += productOrder.getTotalAmount();
        }

        mainController.labelMenuTotalPrice.setText("₱" + referenceTotalPrice + ".00");
    }

    public void customerNameTyping() {
        mainController.labelMenuNoName.setVisible(false);
    }

    public void amountTyping() {
        mainController.labelMenuNoAmount.setVisible(false);
    }


    private void clearSelectedProductReference() {
        editOrShowSelectedProduct = null;
    }

    public void updateModeOfPayment(boolean isCash) {
        if (isCash)
            cashSelected();
        else
            gCashSelected();

        mainController.labelMenuNoModeOfPayment.setVisible(false);
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
        boolean checkedCustomerName = checkCustomerName();
        boolean checkedAmountPaid = checkAmountPaid();
        boolean checkedModeOfPayment = checkModeOfPayment();

        if (checkedCustomerName && checkedAmountPaid && checkedModeOfPayment) {
            setAttributes();
            getChange();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    addToOrderQueue();
                }
            });

            t1.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            orderCancelledOrAddedToQueue();
            clearFields();
            incrementCustomerNumber();
            clearOrderReference();
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
            mainController.labelMenuNoName.setVisible(true);
            return false;
        }
    }

    private boolean checkAmountPaid() {
        if (!mainController.textFieldMenuEnterAmount.getText().isBlank()) {
            referenceAmountPaid = (int) Double.parseDouble(mainController.textFieldMenuEnterAmount.getText());

            if (referenceAmountPaid >= referenceTotalPrice) {
                return true;
            } else {
                mainController.labelMenuNoAmount.setText("*insufficient amount");
                mainController.labelMenuNoAmount.setVisible(true);
            }
        } else {
            mainController.labelMenuNoAmount.setText("*please input amount");
            mainController.labelMenuNoAmount.setVisible(true);
        }
        return false;
    }

    private boolean checkModeOfPayment() {
        if (!referenceModeOfPayment.isBlank()) {
            return true;
        } else {
            mainController.labelMenuNoModeOfPayment.setVisible(true);
            return false;
        }
    }

    private void addToOrderQueue() {
        ObservableList<ProductOrder> copyList = FXCollections.observableArrayList(referenceProductOrderObservableList);
        Order order = new Order(copyList, referenceCustomerName, referenceOrderNumber,
                referenceTotalPrice, referenceAmountPaid, referenceChange, referenceModeOfPayment);
        System.out.println("line 702: " + order.getProductOrderObservableList().isEmpty());
        orderQueueObservableList.add(order);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                invokeOrderListStartsHereMethod(order);
            }
        });
    }


    private void getChange() {
        referenceChange = referenceAmountPaid - referenceTotalPrice;
    }

    private void invokeOrderListStartsHereMethod(Order order) {
        mainController.orderListModel.orderListOperationStartsHere(order);
    }

    private void clearOrderReference() {
        orderReference = null;
    }
}
