package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.Enums.EnumProduct;
import com.example.postearevised.Objects.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Enums.SettingsPane.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.referenceImagePath;

public class SettingsModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setDropShadow() {
        DropShadow dropShadow1 = new DropShadow();
        dropShadow1.setRadius(5);
        dropShadow1.setOffsetX(7);
        dropShadow1.setOffsetY(0);
        dropShadow1.setColor(dropShadowColor);

        mainController.anchorPaneSettingsDropShadow.setEffect(dropShadow1);

        DropShadow dropShadow2 = new DropShadow();
        dropShadow2.setRadius(5);
        dropShadow2.setOffsetX(0);
        dropShadow2.setOffsetY(7);
        dropShadow2.setColor(dropShadowColor);

        mainController.anchorPaneSettingsAccountLeftPanel.setEffect(dropShadow2);
        mainController.anchorPaneSettingsDisplayLeftPanel.setEffect(dropShadow2);
        mainController.anchorPaneSettingsEditProductsLeftPanel.setEffect(dropShadow2);
        mainController.anchorPaneSettingsTACLeftPanel.setEffect(dropShadow2);
        mainController.anchorPaneSettingsSystemManualLeftPanel.setEffect(dropShadow2);

        mainController.anchorPaneAccountTopPanel.setEffect(dropShadow2);
        mainController.anchorPaneDisplayTopPanel.setEffect(dropShadow2);
        mainController.anchorPaneProductsTopPanel.setEffect(dropShadow2);
        mainController.anchorPaneTACTopPanel.setEffect(dropShadow2);
        mainController.anchorPaneSystemManualTopPanel.setEffect(dropShadow2);
    }

    public void setPane() {
        openSelectedPane(Account.getPaneNumber());
    }

    public void openSelectedPane(int paneNumber) {

        switch (paneNumber) {
            case 1: // Account
                mainController.anchorPaneSettingsAccount.setVisible(true);
                mainController.anchorPaneSettingsDisplay.setVisible(false);
                mainController.anchorPaneSettingsTAC.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(false);
                mainController.anchorPaneSettingsSystemManual.setVisible(false);



                mainController.rectangleAccount.setStroke(Color.BLACK);
                mainController.rectangleAccount.setStrokeWidth(2);
                mainController.rectangleAccount.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleDisplay.setStroke(null);
                mainController.rectangleEditProducts.setStroke(null);
                mainController.rectangleTAC.setStroke(null);
                mainController.rectangleSystemManual.setStroke(null);
                break;
            case 2: // Display
                mainController.anchorPaneSettingsAccount.setVisible(false);
                mainController.anchorPaneSettingsDisplay.setVisible(true);
                mainController.anchorPaneSettingsTAC.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(false);
                mainController.anchorPaneSettingsSystemManual.setVisible(false);



                mainController.rectangleDisplay.setStroke(Color.BLACK);
                mainController.rectangleDisplay.setStrokeWidth(2);
                mainController.rectangleDisplay.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleAccount.setStroke(null);
                mainController.rectangleEditProducts.setStroke(null);
                mainController.rectangleTAC.setStroke(null);
                mainController.rectangleSystemManual.setStroke(null);
                break;
            case 3: // Edit Products
                editProductsInitializeTable();
                addCheckboxListeners();

                mainController.anchorPaneSettingsAccount.setVisible(false);
                mainController.anchorPaneSettingsDisplay.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(true);
                mainController.anchorPaneSettingsTAC.setVisible(false);
                mainController.anchorPaneSettingsSystemManual.setVisible(false);



                mainController.rectangleEditProducts.setStroke(Color.BLACK);
                mainController.rectangleEditProducts.setStrokeWidth(2);
                mainController.rectangleEditProducts.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleAccount.setStroke(null);
                mainController.rectangleDisplay.setStroke(null);
                mainController.rectangleTAC.setStroke(null);
                mainController.rectangleSystemManual.setStroke(null);
                break;
            case 4: // TAC
                mainController.anchorPaneSettingsAccount.setVisible(false);
                mainController.anchorPaneSettingsDisplay.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(false);
                mainController.anchorPaneSettingsTAC.setVisible(true);
                mainController.anchorPaneSettingsSystemManual.setVisible(false);



                mainController.rectangleTAC.setStroke(Color.BLACK);
                mainController.rectangleTAC.setStrokeWidth(2);
                mainController.rectangleTAC.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleAccount.setStroke(null);
                mainController.rectangleDisplay.setStroke(null);
                mainController.rectangleEditProducts.setStroke(null);
                mainController.rectangleSystemManual.setStroke(null);
                break;
            case 5: // System Manual
                mainController.anchorPaneSettingsAccount.setVisible(false);
                mainController.anchorPaneSettingsDisplay.setVisible(false);
                mainController.anchorPaneSettingsTAC.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(false);
                mainController.anchorPaneSettingsSystemManual.setVisible(true);



                mainController.rectangleSystemManual.setStroke(Color.BLACK);
                mainController.rectangleSystemManual.setStrokeWidth(2);
                mainController.rectangleSystemManual.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleAccount.setStroke(null);
                mainController.rectangleDisplay.setStroke(null);
                mainController.rectangleEditProducts.setStroke(null);
                mainController.rectangleTAC.setStroke(null);
                break;
        }
    }

    /**
     * Add Products
     */

    private void editProductsInitializeTable() {
        mainController.tableProductsColImage.setCellValueFactory(new PropertyValueFactory<>("imageViewSmall"));
        mainController.tableProductsColProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        mainController.tableProductsColCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        mainController.tableProductsColAvailable.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        mainController.tableProductsColDelete.setCellValueFactory(new PropertyValueFactory<>("checkBoxDelete"));

        mainController.tableProducts.setItems(allProductObservableList);
        mainController.tableProductsColImage.setReorderable(false);
        mainController.tableProductsColProductName.setReorderable(false);
        mainController.tableProductsColCategory.setReorderable(false);
        mainController.tableProductsColAvailable.setReorderable(false);
        mainController.tableProductsColDelete.setReorderable(false);
    }

    public void openAddProductsFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/postearevised/Scenes/Additional/Product.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle(EnumProduct.Product.getTitle());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setAddProduct();
        productController.productModel.isAdd = true;

        newStage.showAndWait();

        isAddingProductSuccess();
    }

    private void isAddingProductSuccess() {
        if (!addedProductSuccess) {
            removePhotoFromResources();
            clearProductReferenceValues();
        }
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
     * Edit Product
     */

    public void editAProduct() throws IOException {
        editOrShowSelectedProduct = getSelectedProduct();
        openEditProductsFXML();
        clearSelectedProduct();
    }

    private void openEditProductsFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/postearevised/Scenes/Additional/Product.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle(EnumProduct.Product.getTitle());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setEditProduct();
        productController.productModel.isAdd = false;

        newStage.showAndWait();

        isAddingProductSuccess();
        refreshProductTable();
    }

    private Product getSelectedProduct() {
        return mainController.tableProducts.getSelectionModel().getSelectedItem();
    }

    private void clearSelectedProduct() {
        editOrShowSelectedProduct = null;
        mainController.tableProducts.getSelectionModel().clearSelection();
    }



    /**
     * Delete Product
     */
    private void addCheckboxListeners() {
        mainController.tableProductsColDelete.setCellValueFactory(cellData -> {
            CheckBox checkBox = new CheckBox();
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                // Do something when the checkbox is checked or unchecked
                if (newValue) {
                    deleteProductSelectedCounter++;
                } else {
                    deleteProductSelectedCounter--;
                }
                updateAddDeleteProductUI();
            });
            return new SimpleObjectProperty<>(checkBox);
        });
    }

    private void updateAddDeleteProductUI() {
        mainController.labelAddDeleteProductBtn.setText(deleteProductSelectedCounter == 0 ? "Add Product  " : "Delete Product");
        mainController.imageViewAddDeleteProductBtn.setImage(deleteProductSelectedCounter == 0 ? ADD_PRODUCT : DELETE_PRODUCT);
        trueAddProductFalseDeleteProduct = deleteProductSelectedCounter != 0;
    }

    public void deleteSelectedProductsProcess() {
        List<Product> listProductsToDelete;

        listProductsToDelete = getSelectedProductsInColumn();
        deleteSelectedProducts(listProductsToDelete);
    }

    public void refreshProductTable() {
        mainController.tableProducts.refresh();
    }

    /**
     * ayaw gumana netong mga to haha
     */
    public List<Product> getSelectedProductsInColumn() {
        ObservableList<Product> selectedProducts = FXCollections.observableArrayList();

        for (Product product : mainController.tableProducts.getItems()) {
            if (product.getCheckBox().isSelected()) {
                selectedProducts.add(product);
                System.out.println(product.getProductName() + ": " + product.getCheckBox().isSelected());
            }
        }

        return selectedProducts;
    }

    private void deleteSelectedProducts(List<com.example.postearevised.Objects.Product> listProductsToDelete) {
        for (com.example.postearevised.Objects.Product product : listProductsToDelete) {
            System.out.println(product.getProductName() + ": " + product.getCheckBox().isSelected());
        }
    }
}