package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Objects.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static com.example.postearevised.Miscellaneous.References.ImagesReference.NO_IMAGE;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public class ProductModel {
    private ProductController productController;

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    public void initializedHideElements() {
        productController.anchorPaneEditPhoto.setVisible(false);
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

                // Get the current working directory
                String currentDir = System.getProperty("user.dir");

                // Get the path to the resources directory
                Path resourcesDir = Path.of(currentDir, "src", "main", "resources");

                // Get the destination directory within resources
                Path destinationDir = Path.of(resourcesDir.toString(), "com", "example", "postearevised", "Product Media");

                // Create directory if it doesn't exist
                Files.createDirectories(destinationDir);

                // Create a copy of the selected file in the destination directory
                Path copiedFilePath = destinationDir.resolve(selectedFile.getName());
                Files.copy(inputStream, copiedFilePath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("File copied successfully!");

                setImagePathValue(String.valueOf(copiedFilePath));
                setImageProduct(String.valueOf(copiedFilePath));
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
        Image image = new Image(copiedFilePath);
        productController.imgProduct.setImage(image);
    }

    /**
     * Remove Photo
     */

    public void removePhoto() {
        removePhotoFromResources();
        productController.imgProduct.setImage(NO_IMAGE);
        referenceImagePath = "";
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
        setAddProductsVisibilities();
        setAddProductsTexts();
        setAddProductsComboBoxValues();
        setAddProductsDisableRadioButtons();
        setAddProductsTextFieldsToNone();
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

    private void setAddProductsDisableRadioButtons() {
        productController.coffeeRadioBtnCold.setDisable(true);
        productController.coffeeRadioBtnHot.setDisable(true);
        productController.coolersRadioBtnLarge.setDisable(true);
        productController.coolersRadioBtnMedium.setDisable(true);
        productController.coolersRadioBtnSmall.setDisable(true);
        productController.coolersRadioBtnTeaBase.setDisable(true);
        productController.coolersRadioBtnWaterBase.setDisable(true);
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
        productController.anchorPaneBtnDone.setDisable(false);
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

    public void addProduct() {
        setAttributes();
        instantiateProduct();
        clearProductReferenceValues();
        closeThisStage();
    }

    private void setAttributes() {
        referenceProductName = productController.textFieldProductName.getText();
        referenceProductDescription = productController.textFieldProductDescription.getText();
        referenceCategory = productController.comboBoxCategories.getValue();
        // referenceImagePath, na set na sa setImagePathValue();

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
                referenceCoolersSmallPrice = Double.parseDouble(productController.coolersTextFieldSmallPrice.getText());
                referenceCoolersMediumPrice = Double.parseDouble(productController.coolersTextFieldMediumPrice.getText());
                referenceCoolersLargePrice = Double.parseDouble(productController.coolersTextFieldLargePrice.getText());
                break;
            case "Coffee":
                referenceCoffeePrice = Double.parseDouble(productController.coffeeTextFieldPrice.getText());
                break;
            case "Ice Candy Cups":
                referenceIceCandyCupsPrice = Double.parseDouble(productController.iceCandyCupsTextFieldPrice.getText());
                break;
            case "Appetizers":
                referenceAppetizersPrice = Double.parseDouble(productController.appetizerTextFieldPrice.getText());
                break;
        }

    }

    private void instantiateProduct() {
        switch(referenceCategory) {
            case "Milk Tea":
                MilkTea milkTea = new MilkTea(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceMilkTeaSmallPrice, referenceMilkTeaMediumPrice, referenceMilkTeaLargePrice,
                        referenceMilkTeaAddOnsOne, referenceMilkTeaAddOnsOnePrice,
                        referenceMilkTeaAddOnsTwo, referenceMilkTeaAddOnsTwoPrice);

                productObservableList.add(milkTea);
                milkTeaObservableList.add(milkTea);
                break;
            case "Coolers":
                Coolers coolers = new Coolers(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceCoolersSmallPrice, referenceCoolersMediumPrice, referenceCoolersLargePrice);

                productObservableList.add(coolers);
                coolersObservableList.add(coolers);
                break;
            case "Coffee":
                Coffee coffee = new Coffee(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceCoffeePrice);

                productObservableList.add(coffee);
                coffeeObservableList.add(coffee);
                break;
            case "Ice Candy Cups":
                IceCandyCups iceCandyCups = new IceCandyCups(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceIceCandyCupsPrice);

                productObservableList.add(iceCandyCups);
                iceCandyCupsObservableList.add(iceCandyCups);
                break;
            case "Appetizers":
                Appetizer appetizer = new Appetizer(referenceProductName, referenceProductDescription, referenceImagePath, referenceCategory,
                        referenceAppetizersPrice);

                productObservableList.add(appetizer);
                appetizerObservableList.add(appetizer);
                break;
        }
    }

    private void closeThisStage() {
        Stage stage = (Stage) productController.imgProduct.getScene().getWindow();
        stage.close();
    }

}
