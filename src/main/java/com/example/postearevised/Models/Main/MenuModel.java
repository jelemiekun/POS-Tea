package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.Enums.EnumProduct;
import com.example.postearevised.Objects.Product;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
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
        label.setFont(Font.font("Arial", FontWeight.BOLD, 24));
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

        AnchorPane.setBottomAnchor(label, 10.0);
        AnchorPane.setLeftAnchor(label, 10.0);

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
        productController.productModel.isAdd = false;

        newStage.showAndWait();

        clearSelectedProductReference();
//        isAddingProductSuccess();
    }

    private void clearSelectedProductReference() {
        editOrShowSelectedProduct = null;
    }
}
