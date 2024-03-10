package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Main;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import static com.example.postearevised.Miscellaneous.References.ProductReference.productCategories;

public class ProductModel {
    private ProductController productController;

    public void setProductController(ProductController productController) {
        this.productController = productController;
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

    public void uploadPhoto() {
        hideAnchorPaneEditPhoto();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Get the path to your resources directory
            String resourcesPath = Objects.requireNonNull(Main.class.getResource("/")).getPath();
            File resourcesDirectory = new File(resourcesPath);

            // Create a target directory within resources to store the image
            File imagesDirectory = new File(resourcesDirectory, "com/example/postearevised/Medias/Product Media");
            imagesDirectory.mkdirs(); // Create directories if they don't exist

            // Define the target file path within the images directory
            File targetFile = new File(imagesDirectory, selectedFile.getName());

            try {
                // Copy the selected image file to the target directory
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image copied to: " + targetFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // User canceled the operation
            System.out.println("No file selected");
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
        productController.anchorPaneRepositionPhotoConfirmation.setVisible(false);
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
        productController.coolersTextFieldMeidunPrice.setText("");
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
    }

    private void setBottomHalfVisible() {
        productController.anchorPaneBottomHalf.setVisible(true);
    }
}
