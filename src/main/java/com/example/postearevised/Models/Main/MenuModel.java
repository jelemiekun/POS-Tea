package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import com.example.postearevised.Objects.Products.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue.OrderHistoryAndOrderQueueCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.MainPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.NotificationContents.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.Others.InvoiceGenerator.*;
import static com.example.postearevised.Miscellaneous.References.DateTimeFormatterReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductOrderReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;
import static com.example.postearevised.Miscellaneous.References.StylesReference.*;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.TOP_LEFT;

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

    public void setCustomerNumber() {
        mainController.labelCustomerNumber.setText(String.valueOf(orderHistoryObservableList.size() + 1));
    }

    public void goToEditProducts() {
        mainController.mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
        mainController.settingsModel.openSelectedPane(SETTINGS_PANE_EDIT_PRODUCT_ENUM.getPaneNumber());
    }

    public void checkIfIsMenuEmpty() {
        removeFlowPaneChildren();

        if (allProductObservableList.isEmpty()) { //availableAllProductObservableList dapat to
            updateElementsIfEmpty();
        } else {
            updateElementsIfNotEmpty();
        }
    }

    public void setHalfRightPanel() {
        mainController.anchorPaneHideHalfRightPanel.setVisible(!referenceProductOrderObservableList.isEmpty());
    }

    private void removeFlowPaneChildren() {
        mainController.flowPaneMenu.getChildren().clear();
    }

    private void updateElementsIfEmpty() {
        mainController.anchorPaneMenuIsEmpty.setVisible(true);
        mainController.labelMenuCategorySelected.setText("Menu");
        mainController.labelMenuCategoryResultCounter.setText("");
        mainController.labelMenuCategoryUnavailableProductCounter.setText("");

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

        switchCategory(ALL_PRODUCT_CATEGORY_ENUM.getNumber());
    }

    public void switchCategory(int categoryNumber) {
        switchCategorySetVisibilities(categoryNumber);
        switchCategorySetTexts(categoryNumber);
        switchCategorySetFlowPaneChildren(categoryNumber);
    }

    private void switchCategorySetVisibilities(int categoryNumber) {
        if (!allProductObservableList.isEmpty()) {
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
        if (!allProductObservableList.isEmpty()) {
            switch (categoryNumber) {
                case 1:
                    mainController.labelMenuCategorySelected.setText("Milk Tea Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableMilkTeaObservableList.size()  + unavailableMilkTeaObservableList.size() + " Milk Teas Result");
                    break;
                case 2:
                    mainController.labelMenuCategorySelected.setText("Coolers Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableCoolersObservableList.size() + unavailableCoolersObservableList.size() + " Coolers Result");
                    break;
                case 3:
                    mainController.labelMenuCategorySelected.setText("Coffee Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableCoffeeObservableList.size() + unavailableCoffeeObservableList.size() + " Coffees Result");
                    break;
                case 4:
                    mainController.labelMenuCategorySelected.setText("Ice Candy Cups Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableIceCandyCupsObservableList.size() + unavailableIceCandyCupsObservableList.size() + " Ice Candy Cups Result");
                    break;
                case 5:
                    mainController.labelMenuCategorySelected.setText("Appetizers Menu");
                    mainController.labelMenuCategoryResultCounter.setText(availableAppetizerObservableList.size() + unavailableAppetizerObservableList.size() + " Appetizers Result");
                    break;
                case 6:
                    mainController.labelMenuCategorySelected.setText("Menu");
                    mainController.labelMenuCategoryResultCounter.setText(allProductObservableList.size() + " Result");
                    break;
            }
        }
    }

    private void switchCategorySetFlowPaneChildren(int categoryNumber) {
        removeFlowPaneChildren();
        switch (categoryNumber) {
            case 1:
                for (Product product : availableMilkTeaObservableList) {
                    addAvailableProductToFlowPane(product, product.getProductName(), product.getImage());
                }

                if (!unavailableMilkTeaObservableList.isEmpty()) {
                    if (availableMilkTeaObservableList.isEmpty())
                        createEmptySpace();

                    mainController.labelMenuCategoryUnavailableProductCounter.setText(unavailableMilkTeaObservableList.size() + " unavailable product(s)");
                    mainController.labelMenuCategoryUnavailableProductCounter.setLayoutX(650);

                    createLabelUnavailableProducts();
                    for (Product product: unavailableMilkTeaObservableList) {
                        addUnavailableProductToFlowPane(product.getProductName(), product.getImage());
                    }
                } else {
                    mainController.labelMenuCategoryUnavailableProductCounter.setText("");
                }
                break;
            case 2:
                for (Product product : availableCoolersObservableList) {
                    addAvailableProductToFlowPane(product, product.getProductName(), product.getImage());
                }

                if (!unavailableCoolersObservableList.isEmpty()) {
                    if (availableCoolersObservableList.isEmpty())
                        createEmptySpace();

                    mainController.labelMenuCategoryUnavailableProductCounter.setText(unavailableCoolersObservableList.size() + " unavailable product(s)");
                    mainController.labelMenuCategoryUnavailableProductCounter.setLayoutX(663);

                    createLabelUnavailableProducts();
                    for (Product product: unavailableCoolersObservableList) {
                        addUnavailableProductToFlowPane(product.getProductName(), product.getImage());
                    }
                } else {
                    mainController.labelMenuCategoryUnavailableProductCounter.setText("");
                }
                break;
            case 3:
                for (Product product : availableCoffeeObservableList) {
                    addAvailableProductToFlowPane(product, product.getProductName(), product.getImage());
                }

                if (!unavailableCoffeeObservableList.isEmpty()) {
                    if (availableCoffeeObservableList.isEmpty())
                        createEmptySpace();

                    mainController.labelMenuCategoryUnavailableProductCounter.setText(unavailableCoffeeObservableList.size() + " unavailable product(s)");
                    mainController.labelMenuCategoryUnavailableProductCounter.setLayoutX(675);

                    createLabelUnavailableProducts();
                    for (Product product: unavailableCoffeeObservableList) {
                        addUnavailableProductToFlowPane(product.getProductName(), product.getImage());
                    }
                } else {
                    mainController.labelMenuCategoryUnavailableProductCounter.setText("");
                }
                break;
            case 4:
                for (Product product : availableIceCandyCupsObservableList) {
                    addAvailableProductToFlowPane(product, product.getProductName(), product.getImage());
                }

                if (!unavailableIceCandyCupsObservableList.isEmpty()) {
                    if (availableIceCandyCupsObservableList.isEmpty())
                        createEmptySpace();

                    mainController.labelMenuCategoryUnavailableProductCounter.setText(unavailableIceCandyCupsObservableList.size() + " unavailable product(s)");
                    mainController.labelMenuCategoryUnavailableProductCounter.setLayoutX(550);

                    createLabelUnavailableProducts();
                    for (Product product: unavailableIceCandyCupsObservableList) {
                        addUnavailableProductToFlowPane(product.getProductName(), product.getImage());
                    }
                } else {
                    mainController.labelMenuCategoryUnavailableProductCounter.setText("");
                }
                break;
            case 5:
                for (Product product : availableAppetizerObservableList) {
                    addAvailableProductToFlowPane(product, product.getProductName(), product.getImage());
                }

                if (!unavailableAppetizerObservableList.isEmpty()) {
                    if (availableAppetizerObservableList.isEmpty())
                        createEmptySpace();

                    mainController.labelMenuCategoryUnavailableProductCounter.setText(unavailableAppetizerObservableList.size() + " unavailable product(s)");
                    mainController.labelMenuCategoryUnavailableProductCounter.setLayoutX(630);

                    createLabelUnavailableProducts();
                    for (Product product: unavailableAppetizerObservableList) {
                        addUnavailableProductToFlowPane(product.getProductName(), product.getImage());
                    }
                } else {
                    mainController.labelMenuCategoryUnavailableProductCounter.setText("");
                }
                break;
            case 6:
                for (Product product : availableAllProductObservableList) {
                    addAvailableProductToFlowPane(product, product.getProductName(), product.getImage());
                }

                if (!unavailableAllProductObservableList.isEmpty()) {
                    if (availableAllProductObservableList.isEmpty())
                        createEmptySpace();

                    mainController.labelMenuCategoryUnavailableProductCounter.setText(unavailableAllProductObservableList.size() + " unavailable product(s)");
                    mainController.labelMenuCategoryUnavailableProductCounter.setLayoutX(783);

                    createLabelUnavailableProducts();
                    for (Product product: unavailableAllProductObservableList) {
                        addUnavailableProductToFlowPane(product.getProductName(), product.getImage());
                    }
                } else {
                    mainController.labelMenuCategoryUnavailableProductCounter.setText("");
                }
                break;
        }
    }

    private void addAvailableProductToFlowPane(Product product, String productName, Image imageProduct) {
        double width = 320;
        double height = 200;

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setCursor(Cursor.HAND);
        anchorPane.setId("menuproducthover");

        Label label = new Label(productName);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        label.setTextFill(Color.WHITE);
        label.setPrefWidth(290);
        label.setPrefHeight(100);
        label.setAlignment(TOP_LEFT);
        label.setWrapText(true);
        label.setEffect(setDropShadowRightDown());

        Label labelPrice = new Label();
        labelPrice.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        labelPrice.setTextFill(Color.WHITE);
        labelPrice.setEffect(setDropShadowRightDown());

        if (product instanceof MilkTea || product instanceof Coolers) {
            labelPrice.setText("from ₱ " + (int) getLowestDoubleValue(product));
        } else if (product instanceof Coffee coffee) {
            labelPrice.setText("₱ " + (int) coffee.getPrice());
        } else if (product instanceof IceCandyCups iceCandyCups) {
            labelPrice.setText("₱ " + (int) iceCandyCups.getPrice());
        } else if (product instanceof Appetizer appetizer) {
            labelPrice.setText("₱ " + (int) appetizer.getPrice());
        }

        ImageView imageView = new ImageView();
        imageView.setImage(imageProduct);

        Rectangle shadow = new Rectangle(width, height);
        shadow.setFill(Color.BLACK);
        shadow.setOpacity(0.35);

        anchorPane.getChildren().addAll(imageView, shadow, label, labelPrice);

        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        AnchorPane.setTopAnchor(label, 15.0);
        AnchorPane.setLeftAnchor(label, 20.0);

        AnchorPane.setBottomAnchor(labelPrice, 15.0);
        AnchorPane.setLeftAnchor(labelPrice, 20.0);

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

    private void addUnavailableProductToFlowPane(String productName, Image imageProduct) {
        double width = 320;
        double height = 200;

        AnchorPane anchorPane = new AnchorPane();

        Label label = new Label(productName);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        label.setTextFill(Color.WHITE);
        label.setPrefWidth(290);
        label.setPrefHeight(100);
        label.setAlignment(TOP_LEFT);
        label.setEffect(setDropShadowRightDown());

        Label labelUnavailable = new Label("This product is unavailable");
        labelUnavailable.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        labelUnavailable.setTextFill(Color.WHITE);
        labelUnavailable.setEffect(setDropShadowRightDown());

        ImageView imageView = new ImageView();
        imageView.setImage(imageProduct);

        Rectangle shadow = new Rectangle(width, height);
        shadow.setFill(Color.BLACK);
        shadow.setOpacity(0.35);

        ImageView imageView1 = new ImageView(productUnavailable);

        anchorPane.getChildren().addAll(imageView, shadow, label, labelUnavailable, imageView1);

        anchorPane.setPrefWidth(width);
        anchorPane.setPrefHeight(height);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView1.setFitWidth(110);
        imageView1.setFitHeight(110);

        AnchorPane.setTopAnchor(label, 15.0);
        AnchorPane.setLeftAnchor(label, 20.0);

        AnchorPane.setBottomAnchor(labelUnavailable, 15.0);
        AnchorPane.setLeftAnchor(labelUnavailable, 20.0);

        AnchorPane.setTopAnchor(imageView1, (height - imageView1.getFitHeight()) / 2);
        AnchorPane.setLeftAnchor(imageView1, (width - imageView1.getFitWidth()) / 2);

        mainController.flowPaneMenu.getChildren().add(anchorPane);
    }

    private void createLabelUnavailableProducts() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(1100);

        Label label = new Label("Unavailable Products");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        label.setTextFill(Paint.valueOf("#000000"));

        anchorPane.getChildren().add(label);

        AnchorPane.setLeftAnchor(label, 50.0);
        mainController.flowPaneMenu.getChildren().add(anchorPane);
    }

    private void createEmptySpace() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(1100);

        Rectangle rectangle = new Rectangle(1100, 1);
        rectangle.setFill(Color.WHITE);
        rectangle.setOpacity(0.0);

        anchorPane.getChildren().add(rectangle);

        mainController.flowPaneMenu.getChildren().add(anchorPane);
    }

    public static double getLowestDoubleValue(Product product) {
        double lowestValue = Double.MAX_VALUE;

        if (product instanceof MilkTea milkTea) {
            lowestValue = Math.min(lowestValue, milkTea.getSmallPrice());
            lowestValue = Math.min(lowestValue, milkTea.getMediumPrice());
            lowestValue = Math.min(lowestValue, milkTea.getLargePrice());
//            lowestValue = Math.min(lowestValue, milkTea.getAddOnsOnePrice());
//            lowestValue = Math.min(lowestValue, milkTea.getAddOnsTwoPrice());
        } else if (product instanceof Coolers coolers) {
            lowestValue = Math.min(lowestValue, coolers.getSmallPrice());
            lowestValue = Math.min(lowestValue, coolers.getMediumPrice());
            lowestValue = Math.min(lowestValue, coolers.getLargePrice());
//            lowestValue = Math.min(lowestValue, coolers.getAddOnsOnePrice());
//            lowestValue = Math.min(lowestValue, coolers.getAddOnsTwoPrice());
        }

        return lowestValue;
    }

    public static double getMaxCombinedPrice(Product product) {
        double maxPrice = 0;

        if (product instanceof MilkTea milkTea) {
            double[] sizePrices = {milkTea.getSmallPrice(), milkTea.getMediumPrice(), milkTea.getLargePrice()};
            double[] addOnsPrices = {milkTea.getAddOnsOnePrice(), milkTea.getAddOnsTwoPrice()};
            maxPrice = calculateMaxPrice(sizePrices, addOnsPrices);
        } else if (product instanceof Coolers coolers) {
            double[] sizePrices = {coolers.getSmallPrice(), coolers.getMediumPrice(), coolers.getLargePrice()};
            double[] addOnsPrices = {coolers.getAddOnsOnePrice(), coolers.getAddOnsTwoPrice()};
            maxPrice = calculateMaxPrice(sizePrices, addOnsPrices);
        }

        return maxPrice;
    }

    private static double calculateMaxPrice(double[] sizePrices, double[] addOnsPrices) {
        double maxPrice = 0;

        for (double sizePrice : sizePrices) {
            for (double addOnsPrice : addOnsPrices) {
                double combinedPrice = sizePrice + addOnsPrice;
                if (combinedPrice > maxPrice) {
                    maxPrice = combinedPrice;
                }
            }
        }

        return maxPrice;
    }

    private void openProductSelectedFXML(Product product) {
        editOrShowSelectedProduct = product;
        mainController.mainModel.showRectangleModal();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(PRODUCT_ENUM.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }

        Stage newStage = new Stage();
        newStage.setTitle(PRODUCT_ENUM.getTITLE());
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
            setLabelSelectPaymentClickable();

            if (referenceProductOrderObservableList.isEmpty())
                setLabelSelectModeOfPaymentVisibility();

            isProductOrderAdded = false;
        }
        mainController.mainModel.hideRectangleModal();
    }

    private void setPanesAfterAddingOrder() {
        mainController.labelNoOrdersSelected.setVisible(false);
        mainController.anchorPaneHideHalfRightPanel.setVisible(true);
    }

    private void setLabelSelectModeOfPaymentVisibility() {
        mainController.labelSelectModeOfPayment.setVisible(true);
        mainController.textFieldModeOfPaymentOthers.setVisible(false);
    }

    private void setLabelSelectPaymentClickable() {
        mainController.labelSelectModeOfPayment.setOnMouseClicked(event -> mainController.comboBoxModeOfPayment.show());
        mainController.labelSelectModeOfPayment.setOnTouchReleased(event -> mainController.comboBoxModeOfPayment.show());
    }

    public void modeOfPaymentSelected() {
        mainController.labelMenuNoModeOfPayment.setVisible(false);
        String selectedModeOfPayment = mainController.comboBoxModeOfPayment.getValue();

        if (selectedModeOfPayment != null) {
            mainController.labelSelectModeOfPayment.setVisible(false);

            if (selectedModeOfPayment.equals("Others")) {
                referenceModeOfPayment = "";
                mainController.textFieldModeOfPaymentOthers.setVisible(true);
            } else {
                mainController.textFieldModeOfPaymentOthers.setVisible(false);
                referenceModeOfPayment = selectedModeOfPayment;
            }
        }
    }

    public void setTextFieldListeners() {
        mainController.textFieldMenuCustomerName.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                mainController.textFieldMenuCustomerName.setText(newValue);
            } else if (!newValue.matches(REGEX_NAME_16_CHAR_NO_SPACE_IN_FRONT_NO_NUMBERS)) {
                mainController.textFieldMenuCustomerName.setText(oldValue);
            }
        }));


        mainController.textFieldMenuEnterAmount.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY_NO_LEADING_ZERO)) {
                mainController.textFieldMenuEnterAmount.setText(oldValue);
            }
        }));

        mainController.textFieldModeOfPaymentOthers.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                mainController.textFieldModeOfPaymentOthers.setText(newValue);
                mainController.labelMenuNoModeOfPayment.setVisible(true);
            } else if (!newValue.matches(REGEX_NAME_16_CHAR_NO_SPACE_IN_FRONT_NO_NUMBERS)) {
                mainController.textFieldModeOfPaymentOthers.setText(oldValue);
            } else {
                mainController.labelMenuNoModeOfPayment.setVisible(false);
            }
        }));
    }

    public void setComboBoxModEOfPaymentItems() {
        mainController.comboBoxModeOfPayment.setItems(modeOfPaymentChoices);

        mainController.comboBoxModeOfPayment.setStyle("-fx-font-family: Arial; -fx-font-size: 26px;");
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

        if (existingOrder != null) {
            productExists(existingOrder, productPrice);
            return false;
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
            labelProductName.setPrefWidth(100);
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
            labelProductPrice.setLayoutX(340.0);
            labelProductPrice.setLayoutY(43.0);
            labelProductPrice.setPrefWidth(90);
            labelProductPrice.setTextAlignment(TextAlignment.CENTER);
            labelProductPrice.setAlignment(CENTER);
            labelProductPrice.setContentDisplay(ContentDisplay.CENTER);
            labelProductPrice.setFont(new javafx.scene.text.Font("Arial", 18.0));
            anchorPane.getChildren().add(labelProductPrice);

            Label labelQuantity = productOrder.getLabelQuantity();
            labelQuantity.setText(String.valueOf(productOrder.getQuantity()));
            labelQuantity.setLayoutX(278.0);
            labelQuantity.setLayoutY(45.0);
            labelQuantity.setTextAlignment(TextAlignment.CENTER);
            labelQuantity.setAlignment(CENTER);
            labelQuantity.setPrefWidth(30);
            labelQuantity.setContentDisplay(ContentDisplay.CENTER);
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

            Tooltip menuProductOrderClickToEdit = new Tooltip("Click to edit");
            Tooltip menuProductOrderDeleteProduct = new Tooltip("Delete item");

            menuProductOrderClickToEdit.setStyle(toolTipStyle);
            menuProductOrderDeleteProduct.setStyle(toolTipStyle);

            Tooltip.install(innerAnchorPane, menuProductOrderClickToEdit);
            Tooltip.install(deleteImageView, menuProductOrderDeleteProduct);

            mainController.flowPaneOrdersSelected.getChildren().add(anchorPane);
        }
    }

    private void anchorPaneEditSelectedOrderProduct(ProductOrder productOrder, Product product, Label firstAttribute, Label secondAttribute, Label thirdAttribute, Label productPrice, Label quantity) {
        editOrShowSelectedProduct = product;

        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PRODUCT_ENUM.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }

        Stage newStage = new Stage();
        newStage.setTitle(PRODUCT_ENUM.getTITLE());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setEditOrderProduct();

        newStage.showAndWait();

        mainController.mainModel.hideRectangleModal();
    }

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
        for (ProductOrder productOrder : referenceProductOrderObservableList) {
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

    public void orderCancelledOrAddedToQueue(boolean isCancel) {
        boolean proceed = true;

        if (isCancel) {
            setOrderCancellation();
            proceed = mainController.mainModel.openPrompt();
        }

        if (isCancel && proceed) {
            setOrderCancelled();
            mainController.mainModel.generateNotification();
        }

        if (proceed) {
            clearProductOrderReferences();
            noOrderSelected();
            updateTotalAmountOfOrder();
            clearFields();
        }
    }

    private void clearFields() {
        mainController.anchorPaneMenu.requestFocus();
        mainController.textFieldMenuCustomerName.setText("");
        mainController.textFieldMenuEnterAmount.setText("");
        mainController.textFieldModeOfPaymentOthers.setVisible(false);
        mainController.textFieldModeOfPaymentOthers.setText("");
        mainController.textFieldModeOfPaymentOthers.setPromptText("Please specify...");
        mainController.labelSelectModeOfPayment.setVisible(true);
        mainController.comboBoxModeOfPayment.setValue(null);
    }

    public void payClicked() {
        boolean checkedCustomerName = checkCustomerName();
        boolean checkedAmountPaid = checkAmountPaid();
        boolean checkedModeOfPayment = checkModeOfPayment();

        if (checkedCustomerName && checkedAmountPaid && checkedModeOfPayment) {
            String amountPaid = mainController.textFieldMenuEnterAmount.getText().trim();
            setProceedPayment(amountPaid);

            if (mainController.mainModel.openPrompt()) {
                setReferenceOrderNumber();
                Order order = makeOrder();
                order.setInvoice(generateInvoice(order, 1));
                if (addOrderToCSV(order, true)) {
                    setPaymentSuccessful(String.valueOf(referenceChange));
                    boolean dump = mainController.mainModel.openPrompt();

                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            addToOrderQueue(order);
                        }
                    });

                    t1.start();

                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        errorMessage = e.getMessage();
                        logError(false);
                    }

                    setOrderAddedToOrderQueue();
                    mainController.mainModel.generateNotification();

                    orderCancelledOrAddedToQueue(false);
                    clearFields();
                    incrementCustomerNumber();
                    clearOrderReference();
                } else {
                    setErrorAddOrder();
                    mainController.mainModel.openPrompt();
                }
            }
        }
    }

    private void incrementCustomerNumber() {
        Platform.runLater(() -> {
            mainController.labelCustomerNumber.setText(String.valueOf(orderHistoryObservableList.size() + orderQueueObservableList.size() + 1));
            System.out.println("Menu model 717: " + orderHistoryObservableList.size() + orderQueueObservableList.size() + 1);
        });
    }

    public void setReferenceOrderNumber() {
        try {
            referenceOrderNumber = Integer.parseInt(mainController.labelCustomerNumber.getText());
        } catch (NumberFormatException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
    }

    private boolean checkCustomerName() {
        referenceCustomerName = mainController.textFieldMenuCustomerName.getText().trim();

        if (!referenceCustomerName.isBlank()) {
            return true;
        } else {
            mainController.labelMenuNoName.setVisible(true);
            return false;
        }
    }

    private boolean checkAmountPaid() {
        if (!mainController.textFieldMenuEnterAmount.getText().isBlank()) {
            referenceAmountPaid = (int) Double.parseDouble(mainController.textFieldMenuEnterAmount.getText().trim());

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
        if (mainController.textFieldModeOfPaymentOthers.isVisible()) {
            referenceModeOfPayment = mainController.textFieldModeOfPaymentOthers.getText().trim();
        }

        if (!referenceModeOfPayment.isBlank()) {
            return true;
        } else {
            mainController.labelMenuNoModeOfPayment.setVisible(true);
            return false;
        }
    }

    private Order makeOrder() {
        ObservableList<ProductOrder> copyList = FXCollections.observableArrayList(referenceProductOrderObservableList);

        int change = referenceAmountPaid - referenceTotalPrice;

        referenceChange = change;
        LocalDateTime orderDateAndTime = getOrderDateAndTime();
        String transactionID = orderDateAndTime.format(transactionIDFormatter);

        return new Order(copyList, referenceCustomerName, referenceOrderNumber,
                referenceTotalPrice, referenceAmountPaid, change, referenceModeOfPayment, orderDateAndTime, transactionID);
    }

    private void addToOrderQueue(Order order) {
        orderQueueObservableList.add(order);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                invokeOrderListStartsHereMethod(order);
            }
        });
    }
    private LocalDateTime getOrderDateAndTime() {
        return LocalDateTime.now();
    }

    private void invokeOrderListStartsHereMethod(Order order) {
        mainController.orderListModel.orderListOperationStartsHere(order);
    }

    private void clearOrderReference() {
        orderReference = null;
    }
}
