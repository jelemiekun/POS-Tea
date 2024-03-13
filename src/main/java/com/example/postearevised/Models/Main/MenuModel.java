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

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

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
        if (orderReference != null) {
            if (isProductOrderAdded) {
//                setPanesAfterAddingOrder();
//                createAnchorPane(product);
//
//                isProductOrderAdded = false;
            }
        } else {

        }
    }

    private void setPanesAfterAddingOrder() {
        mainController.labelNoOrdersSelected.setVisible(false);
        mainController.anchorPaneHideHalfRightPanel.setVisible(true);
    }
    public void createAnchorPane(Product product) {
        ProductOrder productOrder = productOrderReference;

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
        innerAnchorPane.setOnMouseClicked(event -> anchorPaneProductsInOrderOnAction(product));
        innerAnchorPane.setOnTouchReleased(event -> anchorPaneProductsInOrderOnAction(product));

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

        Label label2 = new Label(productOrder.getFirstAttribute());
        label2.setLayoutX(105.0);
        label2.setLayoutY(34.0);
        innerAnchorPane.getChildren().add(label2);

        Label label3 = new Label(productOrder.getSecondAttribute());
        label3.setLayoutX(105.0);
        label3.setLayoutY(51.0);
        innerAnchorPane.getChildren().add(label3);

        anchorPane.getChildren().add(innerAnchorPane);

        int totalAmount = (int) productOrder.getTotalAmount();
        Label label4 = new Label("₱" + ((int) productOrder.getTotalAmount()) + ".00");
        label4.setLayoutX(355.0);
        label4.setLayoutY(43.0);
        label4.setFont(new javafx.scene.text.Font("Arial", 18.0));
        anchorPane.getChildren().add(label4);

        Label label5 = new Label("1");
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
        minusQuantityAnchorPane.setOnMouseClicked(event -> anchorPaneProductsInOrderMinusQuantityOnAction(productOrder, label5, label4, totalAmount));
        minusQuantityAnchorPane.setOnTouchReleased(event -> anchorPaneProductsInOrderMinusQuantityOnAction(productOrder, label5, label4, totalAmount));

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
        addQuantityAnchorPane.setOnMouseClicked(event -> anchorPaneProductsInOrderAddQuantityOnAction(productOrder, label5, label4, totalAmount));
        addQuantityAnchorPane.setOnTouchReleased(event -> anchorPaneProductsInOrderAddQuantityOnAction(productOrder, label5, label4, totalAmount));

        updateOrderTotalAmount();

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
        deleteImageView.setOnMouseClicked(event -> deleteProductInOrderOnAction());
        deleteImageView.setOnTouchReleased(event -> deleteProductInOrderOnAction());
        deleteImageView.setPickOnBounds(true);
        deleteImageView.setPreserveRatio(true);
        deleteImageView.setImage(CLOSE);
        anchorPane.getChildren().add(deleteImageView);

        mainController.flowPaneOrdersSelected.getChildren().add(anchorPane);
    }

    // Event handlers
    private void anchorPaneProductsInOrderOnAction(Product product) {
        System.out.println("About order clicked");
    }

    private void anchorPaneProductsInOrderMinusQuantityOnAction(ProductOrder productOrder, Label label, Label labelPrice, int price) {
        int quantity = Integer.parseInt(label.getText());
        if (quantity > 1) {
            quantity--;
            label.setText(Integer.toString(quantity));

            int totalAmountForThisOrder = quantity * price;
            labelPrice.setText("₱" + (totalAmountForThisOrder) + ".00");

            productOrder.setTotalAmount(totalAmountForThisOrder);

            updateOrderTotalAmount();
        }
    }

    private void anchorPaneProductsInOrderAddQuantityOnAction(ProductOrder productOrder, Label label, Label labelPrice, int price) {
        int quantity = Integer.parseInt(label.getText());
        quantity++;
        label.setText(Integer.toString(quantity));

        int totalAmountForThisOrder = quantity * price;
        labelPrice.setText("₱" + (totalAmountForThisOrder) + ".00");

        productOrder.setTotalAmount(totalAmountForThisOrder);

        updateOrderTotalAmount();
    }

    private void deleteProductInOrderOnAction() {

    }
    private void updateOrderTotalAmount() {

    }

    private void clearSelectedProductReference() {
        editOrShowSelectedProduct = null;
    }
}
