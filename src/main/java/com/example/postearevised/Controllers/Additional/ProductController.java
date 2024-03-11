package com.example.postearevised.Controllers.Additional;

import com.example.postearevised.Models.Additional.ProductModel;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public ProductModel productModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productModel = new ProductModel();
        productModel.setProductController(this);
        productModel.initializedHideElements();
    }

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
    public ToggleGroup coffeeTemperature;
    @FXML
    public ToggleGroup coolersLiquidBase;
    @FXML
    public ToggleGroup coolersSizes;
    @FXML
    public ToggleGroup milkTeaAddOns;
    @FXML
    public ToggleGroup milkTeaLiquidBase;
    @FXML
    public ToggleGroup milkTeaSizes;

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
    void anchorPaneBtnDoneClicked(MouseEvent event) {
        productModel.addProduct();
    }

    @FXML
    void anchorPaneBtnDoneTouched(TouchEvent event) {
        productModel.addProduct();
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
    void anchorPaneEditPhotoClicked(MouseEvent event) {

    }

    @FXML
    void anchorPaneEditPhotoTouched(TouchEvent event) {

    }

    @FXML
    void anchorPaneRemovePhotoClicked(MouseEvent event) {
        productModel.removePhoto();
    }

    @FXML
    void anchorPaneRemovePhotoTouched(TouchEvent event) {
        productModel.removePhoto();
    }

    @FXML
    void anchorPaneUploadPhotoClicked(MouseEvent event) {
        productModel.uploadPhoto();
    }

    @FXML
    void anchorPaneUploadPhotoTouched(TouchEvent event) {
        productModel.uploadPhoto();
    }

    @FXML
    void appetizerTextFieldPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void btnEditProductDescriptionClicked(MouseEvent event) {

    }

    @FXML
    void btnEditProductDescriptionTouched(TouchEvent event) {

    }

    @FXML
    void btnEditProductNameClicked(MouseEvent event) {

    }

    @FXML
    void btnEditProductNameTouched(TouchEvent event) {

    }

    @FXML
    void coffeeRadioBtnColdClicked(ActionEvent event) {

    }

    @FXML
    void coffeeRadioBtnHotClicked(ActionEvent event) {

    }

    @FXML
    void coffeeTextFieldPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void comboBoxCategoriesOnAction(ActionEvent event) {
        productModel.comboBoxValueSelected();
    }

    @FXML
    void coolersRadioBtnLargeClicked(ActionEvent event) {

    }

    @FXML
    void coolersRadioBtnMediumClicked(ActionEvent event) {

    }

    @FXML
    void coolersRadioBtnSmallClicked(ActionEvent event) {

    }

    @FXML
    void coolersRadioBtnTeaBaseClicked(ActionEvent event) {

    }

    @FXML
    void coolersRadioBtnWaterBaseClicked(ActionEvent event) {

    }

    @FXML
    void coolersTextFieldLargePriceTyping(InputMethodEvent event) {

    }

    @FXML
    void coolersTextFieldMeidunPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void coolersTextFieldSmallPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void iceCandyCupsTextFieldPriceTyping(InputMethodEvent event) {

    }

    @FXML
    void imgRepositionPhotoCancelClicked(MouseEvent event) {

    }

    @FXML
    void imgRepositionPhotoCancelTouched(TouchEvent event) {

    }

    @FXML
    void imgRepositionPhotoConfirmClicked(MouseEvent event) {

    }

    @FXML
    void imgRepositionPhotoConfirmTouched(TouchEvent event) {

    }

    @FXML
    void milkTeaRadioBtnAddOnsOneClicked(ActionEvent event) {

    }

    @FXML
    void milkTeaRadioBtnAddOnsTwoClicked(ActionEvent event) {

    }

    @FXML
    void milkTeaRadioBtnLargeClicked(ActionEvent event) {

    }

    @FXML
    void milkTeaRadioBtnMeidumClicked(ActionEvent event) {

    }

    @FXML
    void milkTeaRadioBtnSmallClicked(ActionEvent event) {

    }

    @FXML
    void milkTeaRadioBtnTeaBaseClicked(ActionEvent event) {

    }

    @FXML
    void milkTeaRadioBtnWaterBaseClicked(ActionEvent event) {

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

    }
}
