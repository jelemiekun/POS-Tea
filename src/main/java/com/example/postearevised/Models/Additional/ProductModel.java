package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Objects.Order.ProductOrder;
import com.example.postearevised.Objects.Products.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.Scenes.EXIT_CONFIRMATION_ENUM;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.isConfirmed;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setErrorAddProduct;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductOrderReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;

public class ProductModel {
    private ProductController productController;

    public void setProductController(ProductController productController) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updateWordCounter();
            }
        });
        this.productController = productController;
    }

    public boolean isAddProduct = false;
    public boolean isSelectedProductOrder = false;
    public boolean isEditProduct = false;

    public void initializedHideElements() {
        productController.anchorPaneEditPhoto.setVisible(false);
    }

    public void initializeTextFieldAddListener() {
        productController.textFieldProductName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
                productController.textFieldProductName.setText(oldValue);
            }
        });

        // Milk Tea Text Fields
        productController.milkTeaTextFieldLargePrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.milkTeaTextFieldLargePrice.setText(oldValue);
            }
        });

        productController.milkTeaTextFieldMediumPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.milkTeaTextFieldMediumPrice.setText(oldValue);
            }
        });

        productController.milkTeaTextFieldSmallPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.milkTeaTextFieldSmallPrice.setText(oldValue);
            }
        });

        productController.milkTeaTextFieldAddOnsPriceOne.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.milkTeaTextFieldAddOnsPriceOne.setText(oldValue);
            }
        });

        productController.milkTeaTextFieldAddOnsPriceTwo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.milkTeaTextFieldAddOnsPriceTwo.setText(oldValue);
            }
        });

        productController.milkTeaTextFieldAddOnsOneName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
                productController.milkTeaTextFieldAddOnsOneName.setText(oldValue);
            }
        });

        productController.milkTeaTextFieldAddOnsTwoName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
                productController.milkTeaTextFieldAddOnsTwoName.setText(oldValue);
            }
        });


        // Coolers Text Fields
        productController.coolersTextFieldLargePrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.coolersTextFieldLargePrice.setText(oldValue);
            }
        });

        productController.coolersTextFieldMediumPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.coolersTextFieldMediumPrice.setText(oldValue);
            }
        });

        productController.coolersTextFieldSmallPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.coolersTextFieldSmallPrice.setText(oldValue);
            }
        });

        productController.coolersTextFieldAddOnsPriceOne.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.coolersTextFieldAddOnsPriceOne.setText(oldValue);
            }
        });

        productController.coolersTextFieldAddOnsPriceTwo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.coolersTextFieldAddOnsPriceTwo.setText(oldValue);
            }
        });

        productController.coolersTextFieldAddOnsOneName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
                productController.coolersTextFieldAddOnsOneName.setText(oldValue);
            }
        });

        productController.coolersTextFieldAddOnsTwoName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY)) {
                productController.coolersTextFieldAddOnsTwoName.setText(oldValue);
            }
        });

        // Coffee Text Field
        productController.coffeeTextFieldPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.coffeeTextFieldPrice.setText(oldValue);
            }
        });

        // Ice Candy Cups Text Field
        productController.iceCandyCupsTextFieldPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.iceCandyCupsTextFieldPrice.setText(oldValue);
            }
        });

        // Appetizer Text Field
        productController.appetizerTextFieldPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(REGEX_DIGITS_ONLY)) {
                productController.appetizerTextFieldPrice.setText(oldValue);
            }
        });

        // Word Counter
        productController.textFieldProductDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 200) {
                productController.textFieldProductDescription.setText(oldValue);
            }
        });
    }

    public void updateWordCounter() {
        productController.labelDescriptionWordCounter.setText(productController.textFieldProductDescription.getText().length() + "/200");
    }

    public void addEditProductAddOrder() {
        boolean success = false;

        if (isEditProduct) {
            setAttributes(true);

            addProductToOrderOrEditProductOrder(false);
        } else {
            if (isSelectedProductOrder) {
                addOrder();
            } else {
                setAttributes(false);

                if (isAddProduct)
                    success = instantiateProduct();
                else
                    setObjectAttributesUpdateProduct();
            }
        }

        if (success) {
            clearProductReferenceValues();
            closeThisStage();
        }
    }

    /**
     * Edit Photo Actions
     */
    public void editPhotoClickedTouched(MouseEvent event) {
        event.consume();
        productController.anchorPaneEditPhoto.setVisible(!productController.anchorPaneEditPhoto.isVisible());
    }

    public void editPhotoClickedTouched(TouchEvent event) {
        event.consume();
        productController.anchorPaneEditPhoto.setVisible(!productController.anchorPaneEditPhoto.isVisible());
    }

    public void deselect(MouseEvent event) {
        if (!productController.anchorPaneEditPhoto.getBoundsInParent().contains(event.getX(), event.getY()))
            productController.anchorPaneEditPhoto.setVisible(false);
    }

    public void deselect(TouchEvent event) {
        double x = event.getTouchPoint().getX();
        double y = event.getTouchPoint().getY();

        if (!productController.anchorPaneEditPhoto.getBoundsInParent().contains(x, y))
            hideAnchorPaneEditPhoto();
    }

    private void hideAnchorPaneEditPhoto() {
        productController.anchorPaneEditPhoto.setVisible(false);
    }

    /**
     * Upload Photo
     */
    public void uploadPhoto() {
        hideAnchorPaneEditPhoto();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                // Get the input stream of the selected file
                InputStream inputStream = Files.newInputStream(selectedFile.toPath());

                // Get the AppData directory
                String appDataDir = System.getenv("APPDATA");

                // Specify the destination directory
                Path destinationDir = Paths.get(appDataDir, "POS_Tea", "product images");

                // Create directory if it doesn't exist
                Files.createDirectories(destinationDir);

                // Create a copy of the selected file in the destination directory
                Path copiedFilePath = destinationDir.resolve(selectedFile.getName());
                Files.copy(inputStream, copiedFilePath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("File copied successfully!");

                setImagePathValue(copiedFilePath.toString());
                setImageProduct(copiedFilePath.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // User canceled the operation
            System.out.println("No file selected");
        }
    }


    private void setImagePathValue(String copiedFilePath) {
        referenceImagePath = copiedFilePath;
    }

    private void setImageProduct(String copiedFilePath) {
        File file = new File(copiedFilePath);
        String pathString = file.toURI().toString();
        Image image;
        if (pathString.isEmpty() || pathString.isBlank()) {
            image = new Image("/com/example/postearevised/Product Media/no image/no image.png");
        } else {
            image = new Image(pathString);
        }
        productController.imgProduct.setImage(image);
    }


    /**
     * Remove Photo
     */

    public void removePhoto() {
        removePhotoFromResources();
        productController.imgProduct.setImage(NO_IMAGE);
        referenceImagePath = "/com/example/postearevised/Product Media/no image/no image.png";
        hideAnchorPaneEditPhoto();
    }

    private void removePhotoFromResources() {
        if (!referenceImagePath.isBlank() && !referenceImagePath.isEmpty()) {
            Path photoPath = Path.of(referenceImagePath);
            try {
                // Attempt to delete the photo
                Files.delete(photoPath);
                System.out.println("Photo deleted successfully!");
            } catch (IOException e) {
                System.out.println("Failed to delete the photo: " + e.getMessage());
            }
        }
    }


    /**
     * Add Products
     */

    public void setAddProduct() {
        initializeTextFieldAddListener();
        setAddProductsVisibilities();
        setAddProductsTexts();
        setAddProductsComboBoxValues();
        setAddEditProductsDisableRadioButtons();
        setAddProductsTextFieldsToNone();

        isAddProduct = true;
        isSelectedProductOrder = false;
        isEditProduct = false;
    }

    private void setAddProductsVisibilities() {
        productController.btnEditProductName.setVisible(true);
        productController.btnEditProductDescription.setVisible(true);
        productController.textFieldProductName.setVisible(true);
        productController.textFieldProductDescription.setVisible(true);
        productController.labelDescriptionWordCounter.setVisible(true);
        productController.anchorPaneBtnEditPhoto.setVisible(true);
        productController.comboBoxCategories.setVisible(true);

        productController.anchorPaneBottomHalf.setVisible(false);
        productController.labelProductName.setVisible(false);
        productController.labelProductDescription.setVisible(false);

        productController.appetizerLabelPrice.setVisible(false);
        productController.coffeeLabelPrice.setVisible(false);
        productController.coolersLabelLargePrice.setVisible(false);
        productController.coolersLabelMediumPrice.setVisible(false);
        productController.coolersLabelSmallPrice.setVisible(false);
        productController.coolersLabelAddOnsPriceOne.setVisible(false);
        productController.coolersLabelAddOnsPriceTwo.setVisible(false);
        productController.iceCandyCupsLabelPrice.setVisible(false);
        productController.milkTeaLabelAddOnsPriceOne.setVisible(false);
        productController.milkTeaLabelAddOnsPriceTwo.setVisible(false);
        productController.milkTeaLabelLargePrice.setVisible(false);
        productController.milkTeaLabelMediumPrice.setVisible(false);
        productController.milkTeaLabelSmallPrice.setVisible(false);

    }

    private void setAddProductsTexts() {
        productController.labelAddEditDone.setText("ADD");
    }

    private void setAddProductsComboBoxValues() {
        productController.comboBoxCategories.getItems().addAll(productCategories);
    }

    private void setAddEditProductsDisableRadioButtons() {
        productController.coffeeRadioBtnCold.setDisable(true);
        productController.coffeeRadioBtnHot.setDisable(true);
        productController.coolersRadioBtnLarge.setDisable(true);
        productController.coolersRadioBtnMedium.setDisable(true);
        productController.coolersRadioBtnSmall.setDisable(true);
        productController.coolersRadioBtnTeaBase.setDisable(true);
        productController.coolersRadioBtnWaterBase.setDisable(true);
        productController.coolersRadioBtnAddOnsOne.setDisable(true);
        productController.coolersRadioBtnAddOnsTwo.setDisable(true);
        productController.milkTeaRadioBtnAddOnsOne.setDisable(true);
        productController.milkTeaRadioBtnAddOnsTwo.setDisable(true);
        productController.milkTeaRadioBtnLarge.setDisable(true);
        productController.milkTeaRadioBtnMedium.setDisable(true);
        productController.milkTeaRadioBtnSmall.setDisable(true);
        productController.milkTeaRadioBtnTeaBase.setDisable(true);
        productController.milkTeaRadioBtnWaterBase.setDisable(true);

    }

    private void setAddProductsTextFieldsToNone() {
        productController.appetizerTextFieldPrice.setText("");
        productController.coffeeTextFieldPrice.setText("");
        productController.coolersTextFieldLargePrice.setText("");
        productController.coolersTextFieldMediumPrice.setText("");
        productController.coolersTextFieldSmallPrice.setText("");
        productController.coolersTextFieldAddOnsOneName.setText("");
        productController.coolersTextFieldAddOnsPriceOne.setText("");
        productController.coolersTextFieldAddOnsTwoName.setText("");
        productController.coolersTextFieldAddOnsPriceTwo.setText("");
        productController.iceCandyCupsTextFieldPrice.setText("");
        productController.milkTeaTextFieldAddOnsOneName.setText("");
        productController.milkTeaTextFieldAddOnsPriceOne.setText("");
        productController.milkTeaTextFieldAddOnsPriceTwo.setText("");
        productController.milkTeaTextFieldAddOnsTwoName.setText("");
        productController.milkTeaTextFieldLargePrice.setText("");
        productController.milkTeaTextFieldMediumPrice.setText("");
        productController.milkTeaTextFieldSmallPrice.setText("");
        //productController.textFieldProductName.setText(""); Except here
    }

    public void comboBoxValueSelected() {
        String selected = productController.comboBoxCategories.getValue();
        switchPane(selected);
        referenceCategory = selected;

        enablePanesAfterComboBoxSelected();
    }

    private void enablePanesAfterComboBoxSelected() {
        productController.anchorPaneBtnDone.setDisable(false);
        productController.anchorPaneBtnEditPhoto.setDisable(false);
        productController.textFieldProductName.setDisable(false);
        productController.textFieldProductDescription.setDisable(false);
    }



    private void switchPane(String selected) {
        setBottomHalfVisible();

        switch (selected) {
            case "Milk Tea":
                productController.anchorPaneMilkTea.setVisible(true);
                productController.anchorPaneCoolers.setVisible(false);
                productController.anchorPaneCoffee.setVisible(false);
                productController.anchorPaneIceCandyCups.setVisible(false);
                productController.anchorPaneAppetizer.setVisible(false);
                break;
            case "Coolers":
                productController.anchorPaneMilkTea.setVisible(false);
                productController.anchorPaneCoolers.setVisible(true);
                productController.anchorPaneCoffee.setVisible(false);
                productController.anchorPaneIceCandyCups.setVisible(false);
                productController.anchorPaneAppetizer.setVisible(false);
                break;
            case "Coffee":
                productController.anchorPaneMilkTea.setVisible(false);
                productController.anchorPaneCoolers.setVisible(false);
                productController.anchorPaneCoffee.setVisible(true);
                productController.anchorPaneIceCandyCups.setVisible(false);
                productController.anchorPaneAppetizer.setVisible(false);
                break;
            case "Ice Candy Cups":
                productController.anchorPaneMilkTea.setVisible(false);
                productController.anchorPaneCoolers.setVisible(false);
                productController.anchorPaneCoffee.setVisible(false);
                productController.anchorPaneIceCandyCups.setVisible(true);
                productController.anchorPaneAppetizer.setVisible(false);
                break;
            case "Appetizers":
                productController.anchorPaneMilkTea.setVisible(false);
                productController.anchorPaneCoolers.setVisible(false);
                productController.anchorPaneCoffee.setVisible(false);
                productController.anchorPaneIceCandyCups.setVisible(false);
                productController.anchorPaneAppetizer.setVisible(true);
                break;
        }
        setAddProductsTextFieldsToNone();
        clearProductReferenceValues();
    }

    private void setBottomHalfVisible() {
        productController.anchorPaneBottomHalf.setVisible(true);
    }

    private void addOrder() {
        isProductOrderAdded = orderReference == null; // HINDI KO ALAM PARA SAAN TO PERO WAG TANGGALIN
        addProductToOrderOrEditProductOrder(true);
        //referenceProductOrderObservableList.add(addProductToOrder());
    }


    private void addProductToOrderOrEditProductOrder(boolean isAddOrder) {
        String orderTitle = editOrShowSelectedProduct.getProductName();
        String orderCategory = editOrShowSelectedProduct.getCategory();
        String orderImagePath = editOrShowSelectedProduct.getImagePath();
        Image orderImage = editOrShowSelectedProduct.getImage();
        String firstAttribute = "";
        String secondAttribute = "";
        String thirdAttribute = "";
        double totalAmount = 0;

        RadioButton radioButton1;
        RadioButton radioButton2;
        RadioButton radioButton3;

        switch(orderCategory) {
            case "Milk Tea":
                radioButton1 = (RadioButton) productController.milkTeaLiquidBaseToggleGroup.getSelectedToggle();
                radioButton2 = (RadioButton) productController.milkTeaAddOnsToggleGroup.getSelectedToggle();
                radioButton3 = (RadioButton) productController.milkTeaSizesToggleGroup.getSelectedToggle();

                firstAttribute = radioButton1.getText(); // liquid base or tea base
                thirdAttribute = radioButton3.getText(); // sizes ng milk tea

                if (radioButton2 != null) // add-ons
                    secondAttribute = radioButton2.getText();

                if (productController.milkTeaRadioBtnSmall.isSelected()) {
                    totalAmount += Integer.parseInt(productController.milkTeaLabelSmallPrice.getText());
                } else if (productController.milkTeaRadioBtnMedium.isSelected()) {
                    totalAmount += Integer.parseInt(productController.milkTeaLabelMediumPrice.getText());
                } else if (productController.milkTeaRadioBtnLarge.isSelected()) {
                    totalAmount += Integer.parseInt(productController.milkTeaLabelLargePrice.getText());
                } else {
                    totalAmount += 0;
                }

                if (productController.milkTeaRadioBtnAddOnsOne.isSelected()) {
                    totalAmount += Integer.parseInt(productController.milkTeaLabelAddOnsPriceOne.getText());
                } else if (productController.milkTeaRadioBtnAddOnsTwo.isSelected()) {
                    totalAmount += Integer.parseInt(productController.milkTeaLabelAddOnsPriceTwo.getText());
                } else {
                    totalAmount += 0;
                }
                break;
            case "Coolers":
                radioButton1 = (RadioButton) productController.coolersLiquidBaseToggleGroup.getSelectedToggle();
                radioButton2 = (RadioButton) productController.coolersAddOnsToggleGroup.getSelectedToggle();
                radioButton3 = (RadioButton) productController.coolersSizesToggleGroup.getSelectedToggle();

                firstAttribute = radioButton1.getText(); // liquid base or tea base ng cooler
                thirdAttribute = radioButton3.getText(); // sizes ng coolers

                if (radioButton2 != null) // add-ons
                    secondAttribute = radioButton2.getText();

                if (productController.coolersRadioBtnSmall.isSelected()) {
                    totalAmount += Integer.parseInt(productController.coolersLabelSmallPrice.getText());
                } else if (productController.coolersRadioBtnMedium.isSelected()) {
                    totalAmount += Integer.parseInt(productController.coolersLabelMediumPrice.getText());
                } else if (productController.coolersRadioBtnLarge.isSelected()) {
                    totalAmount += Integer.parseInt(productController.coolersLabelLargePrice.getText());
                } else {
                    totalAmount += 0;
                }

                if (productController.coolersRadioBtnAddOnsOne.isSelected()) {
                    totalAmount += Integer.parseInt(productController.coolersLabelAddOnsPriceOne.getText());
                } else if (productController.coolersRadioBtnAddOnsTwo.isSelected()) {
                    totalAmount += Integer.parseInt(productController.coolersLabelAddOnsPriceTwo.getText());
                } else {
                    totalAmount += 0;
                }
                break;
            case "Coffee":
                radioButton1 = (RadioButton) productController.coffeeTemperatureToggleGroup.getSelectedToggle();

                firstAttribute = radioButton1.getText();

                totalAmount += Integer.parseInt(productController.coffeeLabelPrice.getText());
                break;
            case "Ice Candy Cups":
                // No Attributes for Ice Candy Cups

                totalAmount += Integer.parseInt(productController.iceCandyCupsLabelPrice.getText());
                break;
            case "Appetizers":
                // No Attributes for Appetizers

                totalAmount += Integer.parseInt(productController.appetizerLabelPrice.getText());
                break;
        }

        if (isAddOrder) {
            referenceProductOrder = new ProductOrder(orderTitle, orderCategory, orderImage, orderImagePath ,firstAttribute, secondAttribute, thirdAttribute, (int) totalAmount, 1);
        } else {

        }
    }

    private void setAttributes(boolean isSomeReferenceExisted) {
        if (!isSomeReferenceExisted) {
            referenceProductName = productController.textFieldProductName.getText();
            referenceProductDescription = productController.textFieldProductDescription.getText();
            referenceCategory = productController.comboBoxCategories.getValue();
            // referenceImagePath, na set na sa setImagePathValue();
        }

        switch (referenceCategory) {
            case "Milk Tea":
                referenceMilkTeaSmallPrice = productController.milkTeaTextFieldSmallPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.milkTeaTextFieldSmallPrice.getText());
                referenceMilkTeaMediumPrice = productController.milkTeaTextFieldMediumPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.milkTeaTextFieldMediumPrice.getText());
                referenceMilkTeaLargePrice = productController.milkTeaTextFieldLargePrice.getText().isBlank() ? 0 : Double.parseDouble(productController.milkTeaTextFieldLargePrice.getText());
                referenceMilkTeaAddOnsOne = productController.milkTeaTextFieldAddOnsOneName.getText();
                referenceMilkTeaAddOnsOnePrice = productController.milkTeaTextFieldAddOnsPriceOne.getText().isBlank() ? 0 : Double.parseDouble(productController.milkTeaTextFieldAddOnsPriceOne.getText());
                referenceMilkTeaAddOnsTwo = productController.milkTeaTextFieldAddOnsTwoName.getText();
                referenceMilkTeaAddOnsTwoPrice = productController.milkTeaTextFieldAddOnsPriceTwo.getText().isBlank() ? 0 : Double.parseDouble(productController.milkTeaTextFieldAddOnsPriceTwo.getText());
                break;
            case "Coolers":
                referenceCoolersSmallPrice = productController.coolersTextFieldSmallPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.coolersTextFieldSmallPrice.getText());
                referenceCoolersMediumPrice = productController.coolersTextFieldMediumPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.coolersTextFieldMediumPrice.getText());
                referenceCoolersLargePrice = productController.coolersTextFieldLargePrice.getText().isBlank() ? 0 : Double.parseDouble(productController.coolersTextFieldLargePrice.getText());
                referenceCoolersAddOnsOneName = productController.coolersTextFieldAddOnsOneName.getText();
                referenceCoolersAddOnsOnePrice = productController.coolersTextFieldAddOnsPriceOne.getText().isBlank() ? 0 : Double.parseDouble(productController.coolersTextFieldAddOnsPriceOne.getText());
                referenceCoolersAddOnsTwoName = productController.coolersTextFieldAddOnsTwoName.getText();
                referenceCoolersAddOnsTwoPrice = productController.coolersTextFieldAddOnsPriceTwo.getText().isBlank() ? 0 : Double.parseDouble(productController.coolersTextFieldAddOnsPriceTwo.getText());
                break;
            case "Coffee":
                referenceCoffeePrice = productController.coffeeTextFieldPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.coffeeTextFieldPrice.getText());
                break;
            case "Ice Candy Cups":
                referenceIceCandyCupsPrice = productController.iceCandyCupsTextFieldPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.iceCandyCupsTextFieldPrice.getText());
                break;
            case "Appetizers":
                referenceAppetizersPrice = productController.appetizerTextFieldPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.appetizerTextFieldPrice.getText());
                break;
        }

    }

    private boolean instantiateProduct() {
        Product product = null;
        switch(referenceCategory) {
            case "Milk Tea":
                product = new MilkTea(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceMilkTeaSmallPrice, referenceMilkTeaMediumPrice, referenceMilkTeaLargePrice,
                        referenceMilkTeaAddOnsOne, referenceMilkTeaAddOnsOnePrice,
                        referenceMilkTeaAddOnsTwo, referenceMilkTeaAddOnsTwoPrice);
                availableMilkTeaObservableList.add((MilkTea) product);
                break;
            case "Coolers":
                product = new Coolers(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceCoolersSmallPrice, referenceCoolersMediumPrice, referenceCoolersLargePrice,
                        referenceCoolersAddOnsOneName, referenceCoolersAddOnsOnePrice, referenceCoolersAddOnsTwoName, referenceCoolersAddOnsTwoPrice);
                availableCoolersObservableList.add((Coolers) product);
                break;
            case "Coffee":
                product = new Coffee(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceCoffeePrice);
                availableCoffeeObservableList.add((Coffee) product);
                break;
            case "Ice Candy Cups":
                product = new IceCandyCups(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceIceCandyCupsPrice);
                availableIceCandyCupsObservableList.add((IceCandyCups) product);
                break;
            case "Appetizers":
                product = new Appetizer(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceAppetizersPrice);
                availableAppetizerObservableList.add((Appetizer) product);
                break;
        }


        if (product != null) {
            boolean successToCSV = addProductToCSV(product);
            if (successToCSV) {
                allProductObservableList.add(product);
                return true;
            } else {
                setErrorAddProduct();
                try {
                    openPrompt();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }


    /**
     * Edit Product
     */

    public void setEditProduct() {
        enablePanesAfterComboBoxSelected();
        setEditProductsVisibilities();
        setEditProductsTexts();
        setAddEditProductsDisableRadioButtons();
        setEditProductsTextFieldsValues();
        initializeTextFieldAddListener();

        isAddProduct = false;
        isSelectedProductOrder = false;
        isEditProduct = false;
    }

    private void setEditProductsVisibilities() {
        productController.imgProduct.requestFocus();

        productController.btnEditProductName.setVisible(true);
        productController.btnEditProductDescription.setVisible(true);
        productController.textFieldProductName.setVisible(true);
        productController.textFieldProductDescription.setVisible(true);
        productController.labelDescriptionWordCounter.setVisible(true);
        productController.anchorPaneBtnEditPhoto.setVisible(true);
        productController.comboBoxCategories.setVisible(false);

        productController.anchorPaneBottomHalf.setVisible(true);
        productController.labelProductName.setVisible(false);
        productController.labelProductDescription.setVisible(false);

        productController.appetizerLabelPrice.setVisible(false);
        productController.coffeeLabelPrice.setVisible(false);
        productController.coolersLabelLargePrice.setVisible(false);
        productController.coolersLabelMediumPrice.setVisible(false);
        productController.coolersLabelSmallPrice.setVisible(false);
        productController.coolersLabelAddOnsPriceOne.setVisible(false);
        productController.coolersLabelAddOnsPriceTwo.setVisible(false);
        productController.iceCandyCupsLabelPrice.setVisible(false);
        productController.milkTeaLabelAddOnsPriceOne.setVisible(false);
        productController.milkTeaLabelAddOnsPriceTwo.setVisible(false);
        productController.milkTeaLabelLargePrice.setVisible(false);
        productController.milkTeaLabelMediumPrice.setVisible(false);
        productController.milkTeaLabelSmallPrice.setVisible(false);

        if (editOrShowSelectedProduct instanceof MilkTea) {
            productController.anchorPaneMilkTea.setVisible(true);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);
        } else if (editOrShowSelectedProduct instanceof Coolers) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(true);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);
        } else if (editOrShowSelectedProduct instanceof Coffee) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(true);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);
        } else if (editOrShowSelectedProduct instanceof IceCandyCups) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(true);
            productController.anchorPaneAppetizer.setVisible(false);
        } else if (editOrShowSelectedProduct instanceof Appetizer) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(true);
        }
    }

    private void setEditProductsTexts() {
        productController.labelAddEditDone.setText("EDIT");
        productController.anchorPaneBtnDone.setDisable(false);

    }

    private void setEditProductsTextFieldsValues() {

        productController.textFieldProductName.setText(editOrShowSelectedProduct.getProductName());
        productController.textFieldProductDescription.setText(editOrShowSelectedProduct.getProductDescription());
        productController.imgProduct.setImage(editOrShowSelectedProduct.getImage());
        referenceImagePath = editOrShowSelectedProduct.getImagePath();

        productController.comboBoxCategories.setValue(editOrShowSelectedProduct.getCategory());

        if (editOrShowSelectedProduct instanceof MilkTea editSelectedMilkTea) {
            productController.milkTeaTextFieldSmallPrice.setText(String.valueOf((int) editSelectedMilkTea.getSmallPrice()));
            productController.milkTeaTextFieldMediumPrice.setText(String.valueOf((int) editSelectedMilkTea.getMediumPrice()));
            productController.milkTeaTextFieldLargePrice.setText(String.valueOf((int) editSelectedMilkTea.getLargePrice()));

            productController.milkTeaTextFieldAddOnsOneName.setText(editSelectedMilkTea.getAddOnsOne());
            productController.milkTeaTextFieldAddOnsPriceOne.setText(String.valueOf((int) editSelectedMilkTea.getAddOnsOnePrice()));
            productController.milkTeaTextFieldAddOnsTwoName.setText(editSelectedMilkTea.getAddOnsTwo());
            productController.milkTeaTextFieldAddOnsPriceTwo.setText(String.valueOf((int) editSelectedMilkTea.getAddOnsTwoPrice()));
        } else if (editOrShowSelectedProduct instanceof Coolers editSelectedCoolers) {
            productController.coolersTextFieldSmallPrice.setText(String.valueOf((int) editSelectedCoolers.getSmallPrice()));
            productController.coolersTextFieldMediumPrice.setText(String.valueOf((int) editSelectedCoolers.getMediumPrice()));
            productController.coolersTextFieldLargePrice.setText(String.valueOf((int) editSelectedCoolers.getLargePrice()));
            productController.coolersTextFieldAddOnsOneName.setText(editSelectedCoolers.getAddOnsOne());
            productController.coolersTextFieldAddOnsPriceOne.setText(String.valueOf((int) editSelectedCoolers.getAddOnsOnePrice()));
            productController.coolersTextFieldAddOnsTwoName.setText(editSelectedCoolers.getAddOnsTwo());
            productController.coolersTextFieldAddOnsPriceTwo.setText(String.valueOf((int) editSelectedCoolers.getAddOnsTwoPrice()));
        } else if (editOrShowSelectedProduct instanceof Coffee editSelectedCoffee) {
            productController.coffeeTextFieldPrice.setText(String.valueOf((int) editSelectedCoffee.getPrice()));
        } else if (editOrShowSelectedProduct instanceof IceCandyCups editSelectedIceCandyCups) {
            productController.iceCandyCupsTextFieldPrice.setText(String.valueOf((int) editSelectedIceCandyCups.getPrice()));
        } else if (editOrShowSelectedProduct instanceof Appetizer editSelectedAppetizer) {
            productController.appetizerTextFieldPrice.setText(String.valueOf((int) editSelectedAppetizer.getPrice()));
        }
    }

    private void setObjectAttributesUpdateProduct() {
        Product oldProduct = new Product(editOrShowSelectedProduct.getProductName(),
                editOrShowSelectedProduct.getProductDescription(),
                editOrShowSelectedProduct.getImagePath(),
                editOrShowSelectedProduct.getCategory());

        editOrShowSelectedProduct.setProductName(referenceProductName);
        editOrShowSelectedProduct.setProductDescription(referenceProductDescription);
        editOrShowSelectedProduct.setImagePath(referenceImagePath);

        if (editOrShowSelectedProduct instanceof MilkTea editSelectedMilkTea) {
            editSelectedMilkTea.setSmallPrice(referenceMilkTeaSmallPrice);
            editSelectedMilkTea.setMediumPrice(referenceMilkTeaMediumPrice);
            editSelectedMilkTea.setLargePrice(referenceMilkTeaLargePrice);
            editSelectedMilkTea.setAddOnsOne(referenceMilkTeaAddOnsOne);
            editSelectedMilkTea.setAddOnsOnePrice(referenceMilkTeaAddOnsOnePrice);
            editSelectedMilkTea.setAddOnsTwo(referenceMilkTeaAddOnsTwo);
            editSelectedMilkTea.setAddOnsTwoPrice(referenceMilkTeaAddOnsTwoPrice);
        } else if (editOrShowSelectedProduct instanceof Coolers editSelectedCoolers) {
            editSelectedCoolers.setSmallPrice(referenceCoolersSmallPrice);
            editSelectedCoolers.setMediumPrice(referenceCoolersMediumPrice);
            editSelectedCoolers.setLargePrice(referenceCoolersLargePrice);
            editSelectedCoolers.setAddOnsOne(referenceCoolersAddOnsOneName);
            editSelectedCoolers.setAddOnsOnePrice(referenceCoolersAddOnsOnePrice);
            editSelectedCoolers.setAddOnsTwo(referenceCoolersAddOnsTwoName);
            editSelectedCoolers.setAddOnsTwoPrice(referenceCoolersAddOnsTwoPrice);
        } else if (editOrShowSelectedProduct instanceof Coffee editSelectedCoffee) {
            editSelectedCoffee.setPrice(referenceCoffeePrice);
        } else if (editOrShowSelectedProduct instanceof IceCandyCups editSelectedIceCandyCups) {
            editSelectedIceCandyCups.setPrice(referenceIceCandyCupsPrice);
        } else if (editOrShowSelectedProduct instanceof Appetizer editSelectedAppetizer) {
            editSelectedAppetizer.setPrice(referenceAppetizersPrice);
        }

        boolean edited = editProductInCSV(oldProduct, editOrShowSelectedProduct);
    }


    /**
     * Product Selected From Menu
     */

    public void setSelectedProduct() {
        setSelectedProductPanesVisibilities();
        setSelectedProductRequiredOptionalLabelsVisibilities();
        setSelectedProductButton();
        setSelectedProductAddProductToOrderEnable();
        setSelectedProductTexts();

        isAddProduct = false;
        isSelectedProductOrder = true;
        isEditProduct = false;
    }

    private void setSelectedProductButton() {
        productController.labelAddEditDone.setText("Add Order");
    }

    private void setSelectedProductRequiredOptionalLabelsVisibilities() {
        if (editOrShowSelectedProduct instanceof MilkTea) {
            productController.milkTeaLabel1.setVisible(true);
            productController.milkTeaLabel2.setVisible(true);
            productController.milkTeaLabel3.setVisible(true);
        } else if (editOrShowSelectedProduct instanceof Coolers) {
            productController.coolersLabel1.setVisible(true);
            productController.coolersLabel2.setVisible(true);
            productController.coolersLabel3.setVisible(true);
        } else if (editOrShowSelectedProduct instanceof Coffee) {
            productController.coffeeLabel1.setVisible(true);
            productController.coffeeLabel2.setVisible(true);
        } else if (editOrShowSelectedProduct instanceof IceCandyCups) {
            productController.iceCandyCupsLabel1.setVisible(true);
        } else if (editOrShowSelectedProduct instanceof Appetizer) {
            productController.appetizerLabel1.setVisible(true);
        }
    }

    private void setSelectedProductPanesVisibilities() {
        productController.anchorPaneBtnDone.setVisible(true);
        productController.anchorPanePhoto.setVisible(true);

        productController.btnEditProductName.setVisible(false);
        productController.textFieldProductName.setVisible(false);

        productController.btnEditProductDescription.setVisible(false);
        productController.labelDescriptionWordCounter.setVisible(false);
        productController.textFieldProductDescription.setVisible(false);

        productController.anchorPaneBtnEditPhoto.setVisible(false);
        productController.comboBoxCategories.setVisible(false);

        if (editOrShowSelectedProduct instanceof MilkTea) {
            productController.anchorPaneMilkTea.setVisible(true);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);

            productController.milkTeaTextFieldSmallPrice.setVisible(false);
            productController.milkTeaTextFieldMediumPrice.setVisible(false);
            productController.milkTeaTextFieldLargePrice.setVisible(false);
            productController.milkTeaTextFieldAddOnsOneName.setVisible(false);
            productController.milkTeaTextFieldAddOnsPriceOne.setVisible(false);
            productController.milkTeaTextFieldAddOnsTwoName.setVisible(false);
            productController.milkTeaTextFieldAddOnsPriceTwo.setVisible(false);
        } else if (editOrShowSelectedProduct instanceof Coolers) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(true);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);

            productController.coolersTextFieldSmallPrice.setVisible(false);
            productController.coolersTextFieldMediumPrice.setVisible(false);
            productController.coolersTextFieldLargePrice.setVisible(false);
            productController.coolersTextFieldAddOnsOneName.setVisible(false);
            productController.coolersTextFieldAddOnsPriceOne.setVisible(false);
            productController.coolersTextFieldAddOnsTwoName.setVisible(false);
            productController.coolersTextFieldAddOnsPriceTwo.setVisible(false);
        } else if (editOrShowSelectedProduct instanceof Coffee) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(true);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);

            productController.coffeeTextFieldPrice.setVisible(false);
        } else if (editOrShowSelectedProduct instanceof IceCandyCups) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(true);
            productController.anchorPaneAppetizer.setVisible(false);

            productController.iceCandyCupsTextFieldPrice.setVisible(false);
        } else if (editOrShowSelectedProduct instanceof Appetizer) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(true);

            productController.appetizerTextFieldPrice.setVisible(false);
        }

    }

    public void updateToggleGroups() {
        setSelectedProductAddProductToOrderEnable();
    }

    private void setSelectedProductAddProductToOrderEnable() {
        if (editOrShowSelectedProduct instanceof MilkTea) {
            if (productController.milkTeaSizesToggleGroup.getSelectedToggle() != null && productController.milkTeaLiquidBaseToggleGroup.getSelectedToggle() != null) {
                productController.anchorPaneBtnDone.setDisable(false);
            }
        } else if (editOrShowSelectedProduct instanceof Coolers) {
            if (productController.coolersSizesToggleGroup.getSelectedToggle() != null && productController.coolersLiquidBaseToggleGroup.getSelectedToggle() != null) {
                productController.anchorPaneBtnDone.setDisable(false);
            }
        } else if (editOrShowSelectedProduct instanceof Coffee) {
            if (productController.coffeeTemperatureToggleGroup.getSelectedToggle() != null) {
                productController.anchorPaneBtnDone.setDisable(false);
            }
        } else if (editOrShowSelectedProduct instanceof IceCandyCups) {
            productController.anchorPaneBtnDone.setDisable(false);
        } else if (editOrShowSelectedProduct instanceof Appetizer) {
            productController.anchorPaneBtnDone.setDisable(false);
        }

    }

    private void setSelectedProductTexts() {
        productController.labelProductName.setText(editOrShowSelectedProduct.getProductName());
        productController.labelProductDescription.setText(editOrShowSelectedProduct.getProductDescription());
        productController.imgProduct.setImage(editOrShowSelectedProduct.getImage());

        if (editOrShowSelectedProduct instanceof MilkTea editSelectedMilkTea) {
            productController.milkTeaLabelSmallPrice.setText(String.valueOf((int) editSelectedMilkTea.getSmallPrice()));
            productController.milkTeaLabelMediumPrice.setText(String.valueOf((int) editSelectedMilkTea.getMediumPrice()));
            productController.milkTeaLabelLargePrice.setText(String.valueOf((int) editSelectedMilkTea.getLargePrice()));

            productController.milkTeaRadioBtnAddOnsOne.setText(editSelectedMilkTea.getAddOnsOne());
            productController.milkTeaRadioBtnAddOnsTwo.setText(editSelectedMilkTea.getAddOnsTwo());

            productController.milkTeaLabelAddOnsPriceOne.setText(String.valueOf((int) editSelectedMilkTea.getAddOnsOnePrice()));
            productController.milkTeaLabelAddOnsPriceTwo.setText(String.valueOf((int) editSelectedMilkTea.getAddOnsTwoPrice()));
        } else if (editOrShowSelectedProduct instanceof Coolers editSelectedCoolers) {
            productController.coolersLabelSmallPrice.setText(String.valueOf((int) editSelectedCoolers.getSmallPrice()));
            productController.coolersLabelMediumPrice.setText(String.valueOf((int) editSelectedCoolers.getMediumPrice()));
            productController.coolersLabelLargePrice.setText(String.valueOf((int) editSelectedCoolers.getLargePrice()));
            productController.coolersRadioBtnAddOnsOne.setText(editSelectedCoolers.getAddOnsOne());
            productController.coolersRadioBtnAddOnsTwo.setText(editSelectedCoolers.getAddOnsTwo());
            productController.coolersLabelAddOnsPriceOne.setText(String.valueOf( (int) editSelectedCoolers.getAddOnsOnePrice()));
            productController.coolersLabelAddOnsPriceTwo.setText(String.valueOf( (int) editSelectedCoolers.getAddOnsTwoPrice()));
        } else if (editOrShowSelectedProduct instanceof Coffee editSelectedCoffee) {
            productController.coffeeLabelPrice.setText(String.valueOf((int) editSelectedCoffee.getPrice()));
        } else if (editOrShowSelectedProduct instanceof IceCandyCups editSelectedIceCandyCups) {
            productController.iceCandyCupsLabelPrice.setText(String.valueOf((int) editSelectedIceCandyCups.getPrice()));
        } else if (editOrShowSelectedProduct instanceof Appetizer editSelectedAppetizer) {
            productController.appetizerLabelPrice.setText(String.valueOf((int) editSelectedAppetizer.getPrice()));
        }
    }

    public void setEditOrderProduct() {
        setSelectedProductPanesVisibilities();
        setSelectedEditOrderProductButton();
        setSelectedProductTexts();
        setSelectedOrderProductValues();
        setDisableRadioButtons();

        isAddProduct = false;
        isSelectedProductOrder = false;
        isEditProduct = true;
    }

    private void setSelectedEditOrderProductButton() {
        productController.anchorPaneBtnDone.setDisable(false);
        productController.labelAddEditDone.setText("OK");
    }

    private void setDisableRadioButtons() {
        if (editOrShowSelectedProduct instanceof MilkTea) {
            productController.milkTeaRadioBtnSmall.setDisable(true);
            productController.milkTeaRadioBtnMedium.setDisable(true);
            productController.milkTeaRadioBtnLarge.setDisable(true);
            productController.milkTeaRadioBtnWaterBase.setDisable(true);
            productController.milkTeaRadioBtnTeaBase.setDisable(true);
            productController.milkTeaRadioBtnAddOnsOne.setDisable(true);
            productController.milkTeaRadioBtnAddOnsTwo.setDisable(true);
        } else if (editOrShowSelectedProduct instanceof Coolers) {
            productController.coolersRadioBtnSmall.setDisable(true);
            productController.coolersRadioBtnMedium.setDisable(true);
            productController.coolersRadioBtnLarge.setDisable(true);
            productController.coolersRadioBtnWaterBase.setDisable(true);
            productController.coolersRadioBtnTeaBase.setDisable(true);
            productController.coolersRadioBtnAddOnsOne.setDisable(true);
            productController.coolersRadioBtnAddOnsTwo.setDisable(true);
        } else if (editOrShowSelectedProduct instanceof Coffee) {
            productController.coffeeRadioBtnHot.setDisable(true);
            productController.coffeeRadioBtnCold.setDisable(true);
        } else if (editOrShowSelectedProduct instanceof IceCandyCups) {
            // walang raido buttons sa ice candy cups
        } else if (editOrShowSelectedProduct instanceof Appetizer) {
            // wlaang radio buttons sa appetizer
        }
    }

    private void setSelectedOrderProductValues() {
        // BALI-BALIKTAD / NAURONG TALAGA HAHA
        String firstAttribute = referenceProductOrder.getThirdAttribute();
        String secondAttribute = referenceProductOrder.getFirstAttribute();
        String thirdAttribute = referenceProductOrder.getSecondAttribute();

        switch (editOrShowSelectedProduct.getCategory()) {
            case "Milk Tea":
                if (firstAttribute.equals(productController.milkTeaRadioBtnSmall.getText())) {
                    productController.milkTeaRadioBtnSmall.setSelected(true);
                } else if (firstAttribute.equals(productController.milkTeaRadioBtnMedium.getText())) {
                    productController.milkTeaRadioBtnMedium.setSelected(true);
                } else if (firstAttribute.equals(productController.milkTeaRadioBtnLarge.getText())) {
                    productController.milkTeaRadioBtnLarge.setSelected(true);
                }

                if (secondAttribute.equals(productController.milkTeaRadioBtnTeaBase.getText())) {
                    productController.milkTeaRadioBtnTeaBase.setSelected(true);
                } else if (secondAttribute.equals(productController.milkTeaRadioBtnWaterBase.getText())) {
                    productController.milkTeaRadioBtnWaterBase.setSelected(true);
                }

                if (thirdAttribute.equals(productController.milkTeaRadioBtnAddOnsOne.getText())) {
                    productController.milkTeaRadioBtnAddOnsOne.setSelected(true);
                } else if (thirdAttribute.equals(productController.milkTeaRadioBtnAddOnsTwo.getText())) {
                    productController.milkTeaRadioBtnAddOnsTwo.setSelected(true);
                }
                break;
            case "Coolers":
                if (firstAttribute.equals(productController.coolersRadioBtnSmall.getText())) {
                    productController.coolersRadioBtnSmall.setSelected(true);
                } else if (firstAttribute.equals(productController.coolersRadioBtnMedium.getText())) {
                    productController.coolersRadioBtnMedium.setSelected(true);
                } else if (firstAttribute.equals(productController.coolersRadioBtnLarge.getText())) {
                    productController.coolersRadioBtnLarge.setSelected(true);
                }

                if (secondAttribute.equals(productController.coolersRadioBtnTeaBase.getText())) {
                    productController.coolersRadioBtnTeaBase.setSelected(true);
                } else if (secondAttribute.equals(productController.coolersRadioBtnWaterBase.getText())) {
                    productController.coolersRadioBtnWaterBase.setSelected(true);
                }

                if (thirdAttribute.equals(productController.coolersRadioBtnAddOnsOne.getText())) {
                    productController.coolersRadioBtnAddOnsOne.setSelected(true);
                } else if (thirdAttribute.equals(productController.coolersRadioBtnAddOnsTwo.getText())) {
                    productController.coolersRadioBtnAddOnsTwo.setSelected(true);
                }
                break;
            case "Coffee":
                if (secondAttribute.equals(productController.coffeeRadioBtnHot.getText())) {
                    productController.coffeeRadioBtnHot.setSelected(true);
                } else if (secondAttribute.equals(productController.coffeeRadioBtnCold.getText())) {
                    productController.coffeeRadioBtnCold.setSelected(true);
                }
                break;
            case "Ice Candy Cups":
                // walang attributes / radio buttons
                break;
            case "Appetizers":
                // walang attributes / radio buttons
                break;
        }
    }

    public boolean openPrompt() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(productController.anchorPaneMain.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }

    private void closeThisStage() {
        Stage stage = (Stage) productController.imgProduct.getScene().getWindow();
        stage.close();
    }

}
