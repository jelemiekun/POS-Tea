package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.ProductModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.References.StageReference.productStage;
import static com.example.postearevised.Miscellaneous.References.StylesReference.cssUsing;

public class ProductController implements Initializable {
    public ProductModel productModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productModel = new ProductModel();
        productModel.setProductController(this);
        productModel.setImageView();
        productModel.initializedHideElements();
        productModel.setDropShadow();
        Platform.runLater(() -> {productStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssUsing)).toExternalForm());});
    }


    @FXML
    public Label labelPleaseSelectProductCategoryFirst;
    @FXML
    public Label labelGuideMessagePleaseFillUp;
    @FXML
    public Label labelGuideMessageDescription;
    //TODO label guide message it requires 16:9 product ratio in order for the image not to be stretched.
    //TODO label guide message as much as possible make the product name short
    @FXML
    public AnchorPane anchorPanePhoto;
    @FXML
    public AnchorPane anchorPaneProductNameWhole;
    @FXML
    public AnchorPane anchorPaneProductDescriptionWhole;
    @FXML
    public AnchorPane anchorPaneMain;
    @FXML
    public AnchorPane anchorPaneBottomHalf;
    @FXML
    public AnchorPane anchorPaneAppetizer;
    @FXML
    public AnchorPane anchorPaneBtnDone;
    @FXML
    public AnchorPane anchorPaneBtnEditPhoto;
    @FXML
    public AnchorPane anchorPaneCoffee;
    @FXML
    public AnchorPane anchorPaneCoolers;
    @FXML
    public AnchorPane anchorPaneEditPhoto;
    @FXML
    public AnchorPane anchorPaneIceCandyCups;
    @FXML
    public AnchorPane anchorPaneMilkTea;
    @FXML
    public AnchorPane anchorPaneRemovePhoto;
    @FXML
    public AnchorPane anchorPaneUploadPhoto;

    @FXML
    public Label appetizerLabelPrice;
    @FXML
    public Label coffeeLabelPrice;
    @FXML
    public Label coolersLabelLargePrice;
    @FXML
    public Label coolersLabelMediumPrice;
    @FXML
    public Label coolersLabelSmallPrice;
    @FXML
    public Label coolersLabelAddOnsPriceOne;
    @FXML
    public Label coolersLabelAddOnsPriceTwo;
    @FXML
    public Label iceCandyCupsLabelPrice;
    @FXML
    public Label labelAddEditDone;
    @FXML
    public Label labelDescriptionWordCounter;
    @FXML
    public Label labelProductDescription;
    @FXML
    public Label labelProductName;
    @FXML
    public Label milkTeaLabelAddOnsPriceOne;
    @FXML
    public Label milkTeaLabelAddOnsPriceTwo;
    @FXML
    public Label milkTeaLabelLargePrice;
    @FXML
    public Label milkTeaLabelMediumPrice;
    @FXML
    public Label milkTeaLabelSmallPrice;
    @FXML
    public Label milkTeaLabel1;
    @FXML
    public Label milkTeaLabel2;
    @FXML
    public Label milkTeaLabel3;
    @FXML
    public Label coolersLabel1;
    @FXML
    public Label coolersLabel2;
    @FXML
    public Label coolersLabel3;
    @FXML
    public Label coffeeLabel1;
    @FXML
    public Label coffeeLabel2;
    @FXML
    public Label iceCandyCupsLabel1;
    @FXML
    public Label appetizerLabel1;

    @FXML
    public TextField appetizerTextFieldPrice;
    @FXML
    public TextField coffeeTextFieldPrice;
    @FXML
    public TextField coolersTextFieldLargePrice;
    @FXML
    public TextField coolersTextFieldMediumPrice;
    @FXML
    public TextField coolersTextFieldSmallPrice;
    @FXML
    public TextField coolersTextFieldAddOnsOneName;
    @FXML
    public TextField coolersTextFieldAddOnsPriceOne;
    @FXML
    public TextField coolersTextFieldAddOnsTwoName;
    @FXML
    public TextField coolersTextFieldAddOnsPriceTwo;
    @FXML
    public TextField iceCandyCupsTextFieldPrice;
    @FXML
    public TextField milkTeaTextFieldAddOnsOneName;
    @FXML
    public TextField milkTeaTextFieldAddOnsPriceOne;
    @FXML
    public TextField milkTeaTextFieldAddOnsPriceTwo;
    @FXML
    public TextField milkTeaTextFieldAddOnsTwoName;
    @FXML
    public TextField milkTeaTextFieldLargePrice;
    @FXML
    public TextField milkTeaTextFieldMediumPrice;
    @FXML
    public TextField milkTeaTextFieldSmallPrice;
    @FXML
    public TextField textFieldProductName;

    @FXML
    public ImageView btnEditProductDescription;
    @FXML
    public ImageView btnEditProductName;
    @FXML
    public ImageView imgProduct;

    @FXML
    public ComboBox<String> comboBoxCategories;

    @FXML
    public RadioButton coffeeRadioBtnCold;
    @FXML
    public RadioButton coffeeRadioBtnHot;
    @FXML
    public RadioButton coolersRadioBtnLarge;
    @FXML
    public RadioButton coolersRadioBtnMedium;
    @FXML
    public RadioButton coolersRadioBtnSmall;
    @FXML
    public RadioButton coolersRadioBtnTeaBase;
    @FXML
    public RadioButton coolersRadioBtnWaterBase;
    @FXML
    public RadioButton coolersRadioBtnAddOnsOne;
    @FXML
    public RadioButton coolersRadioBtnAddOnsTwo;
    @FXML
    public RadioButton milkTeaRadioBtnAddOnsOne;
    @FXML
    public RadioButton milkTeaRadioBtnAddOnsTwo;
    @FXML
    public RadioButton milkTeaRadioBtnLarge;
    @FXML
    public RadioButton milkTeaRadioBtnMedium;
    @FXML
    public RadioButton milkTeaRadioBtnSmall;
    @FXML
    public RadioButton milkTeaRadioBtnTeaBase;
    @FXML
    public RadioButton milkTeaRadioBtnWaterBase;

    @FXML
    public ToggleGroup coffeeTemperatureToggleGroup;
    @FXML
    public ToggleGroup coolersLiquidBaseToggleGroup;
    @FXML
    public ToggleGroup coolersSizesToggleGroup;
    @FXML
    public ToggleGroup coolersAddOnsToggleGroup;
    @FXML
    public ToggleGroup milkTeaAddOnsToggleGroup;
    @FXML
    public ToggleGroup milkTeaLiquidBaseToggleGroup;
    @FXML
    public ToggleGroup milkTeaSizesToggleGroup;

    @FXML
    public TextArea textFieldProductDescription;


    @FXML
    void anchorPaneMainClicked(MouseEvent event) {
        productModel.deselect(event);
    }

    @FXML
    void anchorPaneMainTouched(TouchEvent event) {
        productModel.deselect(event);
    }

    @FXML
    void anchorPaneBtnDoneClicked() {
        productModel.addEditProductAddOrder();
    }

    @FXML
    void anchorPaneBtnDoneTouched() {
        productModel.addEditProductAddOrder();
    }

    @FXML
    void anchorPaneBtnEditPhotoClicked(MouseEvent event) {
        productModel.editPhotoClickedTouched(event);
    }

    @FXML
    void anchorPaneBtnEditPhotoTouched(TouchEvent event) {
        productModel.editPhotoClickedTouched(event);
    }

    @FXML
    void radioBtnPressedEnter(KeyEvent event) {
        productModel.updateToggleGroups();
        if (event.getCode() == KeyCode.ENTER) {
            if (!anchorPaneBtnDone.isDisabled())
                productModel.addEditProductAddOrder();
        }
    }

    @FXML
    void anchorPaneEditPhotoClicked() {

    }

    @FXML
    void anchorPaneEditPhotoTouched() {

    }

    @FXML
    void anchorPaneRemovePhotoClicked() {
        productModel.removePhoto();
    }

    @FXML
    void anchorPaneRemovePhotoTouched() {
        productModel.removePhoto();
    }

    @FXML
    void anchorPaneUploadPhotoClicked() {
        productModel.uploadPhoto();
    }

    @FXML
    void anchorPaneUploadPhotoTouched() {
        productModel.uploadPhoto();
    }

    @FXML
    void appetizerTextFieldPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void btnEditProductDescriptionClicked() {

    }

    @FXML
    void btnEditProductDescriptionTouched() {

    }

    @FXML
    void btnEditProductNameClicked(MouseEvent event) {

    }

    @FXML
    void btnEditProductNameTouched(TouchEvent event) {

    }

    @FXML
    void coffeeRadioBtnColdClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void coffeeRadioBtnHotClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void coffeeTextFieldPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void comboBoxCategoriesOnAction() {
        productModel.comboBoxValueSelected();
    }

    @FXML
    void coolersRadioBtnLargeClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void coolersRadioBtnMediumClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void coolersRadioBtnSmallClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void coolersRadioBtnTeaBaseClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void coolersRadioBtnWaterBaseClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void coolersRadioBtnAddOnsOneClicked() { productModel.updateToggleGroups(); }

    @FXML
    void coolersRadioBtnAddOnsTwoClicked() { productModel.updateToggleGroups(); }

    @FXML
    void coolersTextFieldLargePriceTyping(InputMethodEvent event) {

    }

    @FXML
    void coolersTextFieldMediumPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void coolersTextFieldSmallPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void iceCandyCupsTextFieldPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void milkTeaRadioBtnAddOnsOneClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void milkTeaRadioBtnAddOnsTwoClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void milkTeaRadioBtnLargeClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void milkTeaRadioBtnMediumClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void milkTeaRadioBtnSmallClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void milkTeaRadioBtnTeaBaseClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void milkTeaRadioBtnWaterBaseClicked() {
        productModel.updateToggleGroups();
    }

    @FXML
    void milkTeaTextFieldAddOnsOneNameTyping(InputMethodEvent event) {

    }

    @FXML
    void milkTeaTextFieldAddOnsPriceOneTyping(InputMethodEvent event) {

    }

    @FXML
    void milkTeaTextFieldAddOnsPriceTwoTyping(InputMethodEvent event) {

    }

    @FXML
    void milkTeaTextFieldAddOnsTwoNameTyping(InputMethodEvent event) {

    }

    @FXML
    void milkTeaTextFieldLargePriceTyping(InputMethodEvent event) {

    }

    @FXML
    void milkTeaTextFieldMediumPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void milkTeaTextFieldSmallPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void textFieldProductDescriptionTyping(KeyEvent event) {
        productModel.updateWordCounter();
    }
}
