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

import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

public class ProductModel {
    private ProductController productController;

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    public boolean isAdd = false;

    public void initializedHideElements() {
        productController.anchorPaneEditPhoto.setVisible(false);
    }

    public void initializeTextFieldAddListener() {
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
                Path destinationDir = Path.of(resourcesDir.toString(), "com", "example", "postearevised", "Product Media", "uploaded media");

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
        initializeTextFieldAddListener();
        setAddProductsVisibilities();
        setAddProductsTexts();
        setAddProductsComboBoxValues();
        setAddEditProductsDisableRadioButtons();
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

    private void setAddEditProductsDisableRadioButtons() {
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

    public void addEditProduct() {
        setAttributes();

        if (isAdd)
            instantiateProduct();
        else
            setObjectAttributes();

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
                referenceCoolersSmallPrice = productController.coolersTextFieldSmallPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.coolersTextFieldSmallPrice.getText());
                referenceCoolersMediumPrice = productController.coolersTextFieldMediumPrice.getText().isBlank() ? 0 : Double.parseDouble(productController.coolersTextFieldMediumPrice.getText());
                referenceCoolersLargePrice = productController.coolersTextFieldLargePrice.getText().isBlank() ? 0 : Double.parseDouble(productController.coolersTextFieldLargePrice.getText());
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
        productController.iceCandyCupsLabelPrice.setVisible(false);
        productController.milkTeaLabelAddOnsPriceOne.setVisible(false);
        productController.milkTeaLabelAddOnsPriceTwo.setVisible(false);
        productController.milkTeaLabelLargePrice.setVisible(false);
        productController.milkTeaLabelMediumPrice.setVisible(false);
        productController.milkTeaLabelSmallPrice.setVisible(false);

        if (editSelectedProduct instanceof MilkTea) {
            productController.anchorPaneMilkTea.setVisible(true);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);
        } else if (editSelectedProduct instanceof Coolers) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(true);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);
        } else if (editSelectedProduct instanceof Coffee) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(true);
            productController.anchorPaneIceCandyCups.setVisible(false);
            productController.anchorPaneAppetizer.setVisible(false);
        } else if (editSelectedProduct instanceof IceCandyCups) {
            productController.anchorPaneMilkTea.setVisible(false);
            productController.anchorPaneCoolers.setVisible(false);
            productController.anchorPaneCoffee.setVisible(false);
            productController.anchorPaneIceCandyCups.setVisible(true);
            productController.anchorPaneAppetizer.setVisible(false);
        } else if (editSelectedProduct instanceof Appetizer) {
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

        productController.textFieldProductName.setText(editSelectedProduct.getProductName());
        productController.textFieldProductDescription.setText(editSelectedProduct.getProductDescription());
        productController.imgProduct.setImage(editSelectedProduct.getImage());
        referenceImagePath = editSelectedProduct.getImagePath();

        productController.comboBoxCategories.setValue(editSelectedProduct.getCategory());

        if (editSelectedProduct instanceof MilkTea editSelectedMilkTea) {
            productController.milkTeaTextFieldSmallPrice.setText(String.valueOf((int) editSelectedMilkTea.getSmallPrice()));
            productController.milkTeaTextFieldMediumPrice.setText(String.valueOf((int) editSelectedMilkTea.getMediumPrice()));
            productController.milkTeaTextFieldLargePrice.setText(String.valueOf((int) editSelectedMilkTea.getLargePrice()));

            productController.milkTeaTextFieldAddOnsOneName.setText(editSelectedMilkTea.getAddOnsOne());
            productController.milkTeaTextFieldAddOnsPriceOne.setText(String.valueOf((int) editSelectedMilkTea.getAddOnsOnePrice()));
            productController.milkTeaTextFieldAddOnsTwoName.setText(editSelectedMilkTea.getAddOnsTwo());
            productController.milkTeaTextFieldAddOnsPriceTwo.setText(String.valueOf((int) editSelectedMilkTea.getAddOnsTwoPrice()));
        } else if (editSelectedProduct instanceof Coolers editSelectedCoolers) {
            productController.coolersTextFieldSmallPrice.setText(String.valueOf((int) editSelectedCoolers.getSmallPrice()));
            productController.coolersTextFieldMediumPrice.setText(String.valueOf((int) editSelectedCoolers.getMediumPrice()));
            productController.coolersTextFieldLargePrice.setText(String.valueOf((int) editSelectedCoolers.getLargePrice()));
        } else if (editSelectedProduct instanceof Coffee editSelectedCoffee) {
            productController.coffeeTextFieldPrice.setText(String.valueOf((int) editSelectedCoffee.getPrice()));
        } else if (editSelectedProduct instanceof IceCandyCups editSelectedIceCandyCups) {
            productController.iceCandyCupsTextFieldPrice.setText(String.valueOf((int) editSelectedIceCandyCups.getPrice()));
        } else if (editSelectedProduct instanceof Appetizer editSelectedAppetizer) {
            productController.appetizerTextFieldPrice.setText(String.valueOf((int) editSelectedAppetizer.getPrice()));
        }
    }

    private void setObjectAttributes() {
        editSelectedProduct.setProductName(referenceProductName);
        editSelectedProduct.setProductDescription(referenceProductDescription);
        editSelectedProduct.setImagePath(referenceImagePath);

        if (editSelectedProduct instanceof MilkTea editSelectedMilkTea) {
            editSelectedMilkTea.setSmallPrice(referenceMilkTeaSmallPrice);
            editSelectedMilkTea.setMediumPrice(referenceMilkTeaMediumPrice);
            editSelectedMilkTea.setLargePrice(referenceMilkTeaLargePrice);

            editSelectedMilkTea.setAddOnsOne(referenceMilkTeaAddOnsOne);
            editSelectedMilkTea.setAddOnsOnePrice(referenceMilkTeaAddOnsOnePrice);
            editSelectedMilkTea.setAddOnsTwo(referenceMilkTeaAddOnsTwo);
            editSelectedMilkTea.setAddOnsTwoPrice(referenceMilkTeaAddOnsTwoPrice);
        } else if (editSelectedProduct instanceof Coolers editSelectedCoolers) {
            editSelectedCoolers.setSmallPrice(referenceCoolersSmallPrice);
            editSelectedCoolers.setMediumPrice(referenceCoolersMediumPrice);
            editSelectedCoolers.setLargePrice(referenceCoolersLargePrice);
        } else if (editSelectedProduct instanceof Coffee editSelectedCoffee) {
            editSelectedCoffee.setPrice(referenceCoffeePrice);
        } else if (editSelectedProduct instanceof IceCandyCups editSelectedIceCandyCups) {
            editSelectedIceCandyCups.setPrice(referenceIceCandyCupsPrice);
        } else if (editSelectedProduct instanceof Appetizer editSelectedAppetizer) {
            editSelectedAppetizer.setPrice(referenceAppetizersPrice);
        }
    }

    private void refreshTable() {

    }

    private void closeThisStage() {
        Stage stage = (Stage) productController.imgProduct.getScene().getWindow();
        stage.close();
    }

}