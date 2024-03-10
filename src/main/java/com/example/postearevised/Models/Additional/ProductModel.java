package com.example.postearevised.Models.Additional;

import com.example.postearevised.Controllers.Additional.ProductController;

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.productCategories;

public class ProductModel {
    private ProductController productController;

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    /**
     * Add Products
     */

    public void setAddProduct() {
        setAddVisibilities();
        setAddTexts();
        setAddComboBoxValues();
        setDisableRadioButtons();
        setTextFieldsToNone();
    }

    private void setAddVisibilities() {
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
        productController.anchorPaneRepositionPhotoConfirmation.setVisible(false);
    }

    private void setAddTexts() {
        productController.labelAddEditDone.setText("ADD");
    }

    private void setAddComboBoxValues() {
        productController.comboBoxCategories.getItems().addAll(productCategories);
    }

    private void setDisableRadioButtons() {
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

    private void setTextFieldsToNone() {
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
        switch (selected) {
            case "Milk Tea":

                break;
            case "Coolers":
                break;
            case "Coffee":
                break;
            case "Ice Candy Cups":
                break;
            case "Appetizer":
                break;
        }
    }
}
