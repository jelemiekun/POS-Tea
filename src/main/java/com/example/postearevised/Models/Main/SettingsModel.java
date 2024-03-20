package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.Enums.EnumProduct;
import com.example.postearevised.Miscellaneous.References.ProductOrderReference;
import com.example.postearevised.Objects.Products.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.ImportExport.*;
import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPane.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.ERROR_LOG_PATH;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

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
                editProductsCheckIfOrderIsOngoing();
                refreshProductTable();

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

    public void populateComboBoxImportExport() {
        mainController.importExportComboBox.getItems().clear();
        mainController.importExportComboBox.getItems().addAll("Import/Export CSV","Import CSV", "Export CSV");
        mainController.importExportComboBox.setValue("Import/Export CSV");
    }

    public void comboBoxValueSelected() throws IOException {
        mainController.mainModel.showRectangleModal();
        String selected = mainController.importExportComboBox.getValue();

        if (selected.equals(Import.getImportOperation())) {
            switch (chooseFilePath(mainStage, true)) {
                // 0 - do nothing, 1 - successful, 2 - invalid file format, 3 - other unexpected errors, open notepad contains error message
                case 1:
                    setImportSuccessful();
                    mainController.mainModel.openPrompt();
                    break;
                case 2:
                    setInvalidFileFormat();
                    mainController.mainModel.openPrompt();
                    break;
                case 3:
                    setImportOtherError();
                    mainController.mainModel.openPrompt();
                    openError();
                    break;
            }
        } else if (selected.equals(Export.getImportOperation())){
            // 4 - export successful
            if (chooseFilePath(mainStage, false) == 4) {
                mainController.mainModel.openPrompt();
            }
        }
        mainController.mainModel.hideRectangleModal();
        mainController.importExportComboBox.setValue("Import/Export CSV");
    }

    private void openError() throws IOException {
        try {
            // Get current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
            String dateTime = dateFormat.format(new Date());

            // Create a FileWriter object with append mode set to true
            FileWriter fileWriter = new FileWriter(ERROR_LOG_PATH, true);

            // Create a BufferedWriter object
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the date and time to the notepad
            bufferedWriter.write(dateTime);
            bufferedWriter.newLine();

            // Write the error message to the notepad
            bufferedWriter.write("Error: " + errorMessage);
            bufferedWriter.newLine();

            // Add two empty lines
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            // Close the BufferedWriter
            bufferedWriter.close();

            // Inform the user
            System.out.println("Error written to notepad successfully!");

            // Open the notepad
            Desktop.getDesktop().open(new File(ERROR_LOG_PATH));
        } catch (IOException e) {
            setErrorPrintingError();
            mainController.mainModel.openPrompt();
        }
    }

    /**
     * Add Products
     */

    private void editProductsCheckIfOrderIsOngoing() {
        mainController.orderIsOngoing = !ProductOrderReference.referenceProductOrderObservableList.isEmpty();
        cantEditProductsLabelVisibility();
    }

    private void cantEditProductsLabelVisibility() {
        mainController.labelSettingsEditProductsUnavailable.setVisible(mainController.orderIsOngoing);
    }

    private void editProductsInitializeTable() {
        mainController.tableProductsColImage.setCellValueFactory(new PropertyValueFactory<>("imageViewSmall"));
        mainController.tableProductsColProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        mainController.tableProductsColCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        mainController.tableProductsColAvailable.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        mainController.tableProducts.setItems(allProductObservableList);
        mainController.tableProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        mainController.tableProductsColImage.setReorderable(false);
        mainController.tableProductsColProductName.setReorderable(false);
        mainController.tableProductsColCategory.setReorderable(false);
        mainController.tableProductsColAvailable.setReorderable(false);
    }

    public void openAddProductsFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PRODUCT_ENUM.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle(EnumProduct.PRODUCT_ENUM.getTitle());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setAddProduct();

        newStage.showAndWait();

        isAddingProductSuccess();
        refreshProductTable();
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
    }

    private void openEditProductsFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PRODUCT_ENUM.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setTitle(EnumProduct.PRODUCT_ENUM.getTitle());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setEditProduct();

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

    public void deleteSelectedProductsProcess() throws IOException {
        setDeleteProduct();
        if (mainController.mainModel.openPrompt()) {
            ObservableList<Product> selectedItemsToDelete = mainController.tableProducts.getSelectionModel().getSelectedItems();
            if (deleteProductInCSV(selectedItemsToDelete)) {
                for (Product product : selectedItemsToDelete) {
                    availableAllProductObservableList.remove(product);
                    availableMilkTeaObservableList.remove(product);
                    availableCoolersObservableList.remove(product);
                    availableCoffeeObservableList.remove(product);
                    availableIceCandyCupsObservableList.remove(product);
                    availableAppetizerObservableList.remove(product);
                }

                mainController.tableProducts.getItems().removeAll(selectedItemsToDelete);
                refreshProductTable();
            } else {
                setErrorDeleteProduct();
                mainController.mainModel.openPrompt();
            }
        }
    }

    public void refreshProductTable() {
        mainController.tableProducts.refresh();
        mainController.tableProducts.getSelectionModel().clearSelection();

        if (allProductObservableList.isEmpty()) {
            Label placeholderLabel = new Label("Your product list is empty.\nClick \"Add Product\" to start adding products");
            placeholderLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 28));
            placeholderLabel.setAlignment(Pos.CENTER);
            placeholderLabel.setContentDisplay(ContentDisplay.CENTER);
            placeholderLabel.setTextAlignment(TextAlignment.CENTER);
            mainController.tableProducts.setPlaceholder(placeholderLabel);
        }
    }

    /**
     * System Manual
     */

    public void setVideo() {
        URL videoUrl = getClass().getResource(SAMPLE_VIDEO_PATH);
        if (videoUrl == null) {
            System.err.println("Video file not found: " + SAMPLE_VIDEO_PATH);
            return;
        }

        Media media = new Media(videoUrl.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mainController.systemVideoPlayer.setMediaPlayer(mediaPlayer);
    }

}