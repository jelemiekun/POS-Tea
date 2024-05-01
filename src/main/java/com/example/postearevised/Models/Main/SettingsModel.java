package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.Enums.ProductEnum;
import com.example.postearevised.Miscellaneous.References.ProductOrderReference;
import com.example.postearevised.Objects.Account.Account;
import com.example.postearevised.Objects.Products.*;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.postearevised.Miscellaneous.Database.CSV.Accounts.AccountCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.CSVUtility.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue.OrderHistoryAndOrderQueueCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ImportCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.AskForPasswordHeaderTitlesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.DisplayColorsEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ImportExportEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.PasswordColorsEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.PasswordColorsEnum.STRONG_ENUM;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Others.Internet.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.NotificationContents.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.LoginForgotRegisterReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;
import static com.example.postearevised.Miscellaneous.References.SettingsReference.*;
import static com.example.postearevised.Miscellaneous.References.StylesReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.*;

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
        openSelectedPane(SETTINGS_PANE_ACCOUNT_ENUM.getPaneNumber());
    }

    public void openSelectedPane(int paneNumber) {
        switch (paneNumber) {
            case 1: // Account
                mainController.anchorPaneSettingsAccount.setVisible(true);
                mainController.anchorPaneSettingsDisplay.setVisible(false);
                mainController.anchorPaneSettingsTAC.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(false);
                mainController.anchorPaneSettingsSystemManual.setVisible(false);

                mainController.anchorPaneSettingsAccount.requestFocus();



                mainController.rectangleAccount.setStroke(Color.BLACK);
                mainController.rectangleAccount.setStrokeWidth(2);
                mainController.rectangleAccount.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleDisplay.setStroke(null);
                mainController.rectangleEditProducts.setStroke(null);
                mainController.rectangleTAC.setStroke(null);
                mainController.rectangleSystemManual.setStroke(null);
                break;
            case 2: // Appearance
                mainController.anchorPaneSettingsAccount.setVisible(false);
                mainController.anchorPaneSettingsDisplay.setVisible(true);
                mainController.anchorPaneSettingsTAC.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(false);
                mainController.anchorPaneSettingsSystemManual.setVisible(false);

                mainController.anchorPaneSettingsDisplay.requestFocus();



                mainController.rectangleDisplay.setStroke(Color.BLACK);
                mainController.rectangleDisplay.setStrokeWidth(2);
                mainController.rectangleDisplay.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleAccount.setStroke(null);
                mainController.rectangleEditProducts.setStroke(null);
                mainController.rectangleTAC.setStroke(null);
                mainController.rectangleSystemManual.setStroke(null);
                break;
            case 3: // Edit Products
                mainController.settingsEditProductGuideMessageClickTable.setVisible(showGuideMessagesReference);

                editProductsInitializeTable();
                editProductsCheckIfOrderIsOngoing();
                refreshProductTable();

                mainController.anchorPaneSettingsAccount.setVisible(false);
                mainController.anchorPaneSettingsDisplay.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(true);
                mainController.anchorPaneSettingsTAC.setVisible(false);
                mainController.anchorPaneSettingsSystemManual.setVisible(false);

                mainController.anchorPaneSettingsEditProducts.requestFocus();

                setOrderIsOnGoing();



                mainController.rectangleEditProducts.setStroke(Color.BLACK);
                mainController.rectangleEditProducts.setStrokeWidth(2);
                mainController.rectangleEditProducts.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleAccount.setStroke(null);
                mainController.rectangleDisplay.setStroke(null);
                mainController.rectangleTAC.setStroke(null);
                mainController.rectangleSystemManual.setStroke(null);

                mainController.mainModel.setAccountAndProductAnchorPaneIfAdmin();
                break;
            case 4: // TAC
                mainController.anchorPaneSettingsAccount.setVisible(false);
                mainController.anchorPaneSettingsDisplay.setVisible(false);
                mainController.anchorPaneSettingsEditProducts.setVisible(false);
                mainController.anchorPaneSettingsTAC.setVisible(true);
                mainController.anchorPaneSettingsSystemManual.setVisible(false);

                mainController.anchorPaneSettingsTAC.requestFocus();



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


                mainController.anchorPaneSettingsSystemManual.requestFocus();


                mainController.rectangleSystemManual.setStroke(Color.BLACK);
                mainController.rectangleSystemManual.setStrokeWidth(2);
                mainController.rectangleSystemManual.setStrokeType(StrokeType.INSIDE);

                mainController.rectangleAccount.setStroke(null);
                mainController.rectangleDisplay.setStroke(null);
                mainController.rectangleEditProducts.setStroke(null);
                mainController.rectangleTAC.setStroke(null);
                break;
        }

        if (paneNumber != 5)
            pauseVideo();

        hidePasswordToolTipImage();
    }

    public void hidePasswordToolTipImage() {
        mainController.passwordToolTipClicked = false;
        mainController.passwordToolTipImage.setVisible(false);
    }

    private void setOrderIsOnGoing() {
        mainController.anchorPaneEditProduct.setDisable(orderIsOngoing);
        mainController.anchorPaneAddProduct.setDisable(orderIsOngoing);
        mainController.importExportComboBox.setDisable(orderIsOngoing);
    }

    public void populateComboBoxImportExport() {
        mainController.importExportComboBox.getItems().addAll(IMPORT_EXPORT_ENUM.getImportOperation(), IMPORT_ENUM.getImportOperation(), EXPORT_ENUM.getImportOperation());
        mainController.importExportComboBox.setValue(IMPORT_EXPORT_ENUM.getImportOperation());
        mainController.importExportComboBox.setStyle(settingEditProductImportExportComboBoxStyle);
    }

    public void comboBoxValueSelected() {
        if (!orderIsOngoing) {
            isAddingProductsFromImport = true;

            mainController.mainModel.showRectangleModal();
            String selected = mainController.importExportComboBox.getValue();

            if (selected == null) {
                mainController.mainModel.hideRectangleModal();
                mainController.importExportComboBox.setValue(IMPORT_EXPORT_ENUM.getImportOperation());
                setImportExportCancelled();
                mainController.mainModel.generateNotification();
            } else if (selected.equals(IMPORT_ENUM.getImportOperation())) {
                switch (chooseFilePath(mainStage, true)) {
                    // -1 - some product already exists, 0 - do nothing, 1 - successful, 2 - invalid file format, 3 - other unexpected errors, open notepad contains error message
                    case 1:
                        setImportSuccessful();
                        mainController.mainModel.openPrompt();
                        setImportMenuSuccessful();
                        mainController.mainModel.generateNotification();
                        break;
                    case 2:
                        setInvalidFileFormat();
                        mainController.mainModel.openPrompt();
                        setImportMenuUnsuccessful();
                        mainController.mainModel.generateNotification();
                        break;
                    case 3:
                        setImportOtherError();
                        mainController.mainModel.openPrompt();
                        logError(false);
                        setImportMenuUnsuccessful();
                        mainController.mainModel.generateNotification();
                        break;
                    case 4: // there's a problem in category csv column
                        setImportMenuUnsuccessful();
                        mainController.mainModel.generateNotification();
                }
            } else if (selected.equals(EXPORT_ENUM.getImportOperation())){
                // 4 - export successful
                if (chooseFilePath(mainStage, false) == 4) {
                    mainController.mainModel.openPrompt();
                    setExportMenuSuccessful();
                    mainController.mainModel.generateNotification();
                }
            } else {
                mainController.mainModel.hideRectangleModal();
                mainController.importExportComboBox.setValue(IMPORT_EXPORT_ENUM.getImportOperation());
            }
            mainController.mainModel.hideRectangleModal();
            mainController.importExportComboBox.setValue(IMPORT_EXPORT_ENUM.getImportOperation());
            isAddingProductsFromImport = false;
        }
    }

    /**
     * Add Products
     */

    private void editProductsCheckIfOrderIsOngoing() {
        orderIsOngoing = !ProductOrderReference.referenceProductOrderObservableList.isEmpty();
        cantEditProductsLabelVisibility();
    }

    private void cantEditProductsLabelVisibility() {
        mainController.labelSettingsEditProductsUnavailable.setVisible(orderIsOngoing);

        if (orderIsOngoing && showGuideMessagesReference)
            mainController.settingsEditProductGuideMessageClickTable.setVisible(false);
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

        mainController.tableProducts.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            Tooltip settingsEditProductTableProductTableRow = new Tooltip("View product");
            settingsEditProductTableProductTableRow.setStyle(toolTipStyle);

            row.setOnMouseEntered(event -> {
                Product rowData = row.getItem();
                if (rowData != null) {
                    Tooltip.install(row, settingsEditProductTableProductTableRow);
                }
            });

            row.setOnMouseExited(event -> Tooltip.uninstall(row, settingsEditProductTableProductTableRow));

            return row;
        });
    }

    public void openAddProductsFXML() {
        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PRODUCT_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        productStage = new Stage();
        productStage.setTitle(ProductEnum.PRODUCT_ENUM.getTitle());
        productStage.setScene(new Scene(root));
        productStage.getIcons().add(SYSTEM_LOGO);
        productStage.setResizable(false);

        productStage.initModality(Modality.WINDOW_MODAL);
        productStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setAddProduct();

        productStage.showAndWait();

        if (addingProductSuccess) {
            setAddProductSuccess();
            mainController.mainModel.generateNotification();

            addingProductSuccess = false;
        }

        isAddingProductSuccess();
        refreshProductTable();
    }

    private void isAddingProductSuccess() {
        if (!addedProductSuccess) {
            //removePhotoFromResources(); //commented so that all time favorites photos wil not be affected
            clearProductReferenceValues();
        }
        mainController.mainModel.hideRectangleModal();
    }

    private void removePhotoFromResources() {
        if (!referenceImagePath.isBlank() && !referenceImagePath.isEmpty()) {
            Path photoPath = Path.of(referenceImagePath);
            try {
                Files.delete(photoPath);
                System.out.println("Photo deleted successfully!");
            } catch (IOException e) {
                errorMessage = e.getMessage();
                logError(false);
                System.err.println("Failed to delete the photo: " + e.getMessage());
            }
        }
    }

    /**
     * Edit Product
     */

    public void editAProduct() {
        editOrShowSelectedProduct = getSelectedProduct();

        if(editOrShowSelectedProduct != null)
            openEditProductsFXML();
    }

    private void openEditProductsFXML() {
        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PRODUCT_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        productStage = new Stage();
        productStage.setTitle(ProductEnum.PRODUCT_ENUM.getTitle());
        productStage.setScene(new Scene(root));
        productStage.getIcons().add(SYSTEM_LOGO);
        productStage.setResizable(false);

        productStage.initModality(Modality.WINDOW_MODAL);
        productStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setEditProduct();

        productStage.showAndWait();

        if (editingProductSuccess) {
            setEditProductSuccess();
            mainController.mainModel.generateNotification();

            editingProductSuccess = false;
        }

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
     * Delete product(s)
     */

    public void deleteSelectedProductsProcess() {
        ObservableList<Product> selectedItemsToDelete = mainController.tableProducts.getSelectionModel().getSelectedItems();

        if (selectedItemsToDelete != null && !selectedItemsToDelete.isEmpty()) {
            setDeleteProduct();
            if (mainController.mainModel.openPrompt()) {
                if (saveChanges(6)) {
                    if (deleteProductInCSV(selectedItemsToDelete)) {
                        trySelectedItemsToDelete(selectedItemsToDelete);
                    } else {
                        setErrorDeleteProduct();
                        mainController.mainModel.openPrompt();
                    }
                } else {
                    deleteProductPasswordFailed();
                }

            } else {
                deletionCancelled();
            }
        }
    }

    private void trySelectedItemsToDelete(ObservableList<Product> selectedItemsToDelete) {
        try {
            deletingProductSuccess = true;

            List<Product> productsToRemove = new ArrayList<>(selectedItemsToDelete);

            for (Product product : productsToRemove) {
                if (product instanceof MilkTea milkTea) {
                    availableMilkTeaObservableList.remove(milkTea);
                    unavailableMilkTeaObservableList.remove(milkTea);
                } else if (product instanceof Coolers coolers) {
                    availableCoolersObservableList.remove(coolers);
                    unavailableCoolersObservableList.remove(coolers);
                } else if (product instanceof Coffee coffee) {
                    availableCoffeeObservableList.remove(coffee);
                    unavailableCoffeeObservableList.remove(coffee);
                } else if (product instanceof IceCandyCups iceCandyCups) {
                    availableIceCandyCupsObservableList.remove(iceCandyCups);
                    unavailableIceCandyCupsObservableList.remove(iceCandyCups);
                } else if (product instanceof Appetizer appetizer) {
                    availableAppetizerObservableList.remove(appetizer);
                    unavailableAppetizerObservableList.remove(appetizer);
                }

                availableAllProductObservableList.remove(product);
                unavailableAllProductObservableList.remove(product);

                allProductObservableList.remove(product);
            }

            refreshProductTable();

            mainController.tableProducts.getItems().removeAll(selectedItemsToDelete);

            refreshProductTable();

            if (deletingProductSuccess) {
                setDeleteProductSuccess();
                mainController.mainModel.generateNotification();

                deletingProductSuccess = false;
            }

        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorDeleteProduct();
            mainController.mainModel.openPrompt();
        }
    }

    private void deleteProductPasswordFailed() {
        if (!maxAttemptLimitReached) {
            deletionCancelled();
        } else {
            setErrorChangingPasswordMaximumAttemptReached();
            setProductDeletionFailedMaxLimitReached();
            mainController.mainModel.generateNotification();
        }

        Platform.runLater(() -> maxAttemptLimitReached = false);
    }

    private void deletionCancelled() {
        setErrorFailedToDeleteProductsCancelled();
        mainController.mainModel.openPrompt();
        setProductDeletionCancelled();
        mainController.mainModel.generateNotification();
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
            placeholderLabel.setId("settinglabel");
            mainController.tableProducts.setPlaceholder(placeholderLabel);

            Tooltip settingsEditProduct = new Tooltip("No product in menu");
            settingsEditProduct.setStyle(toolTipStyle);
            Tooltip.install(placeholderLabel, settingsEditProduct);
        }
    }

    /**
     * Account
     */
    public void setSettingsAccount() {
        setSettingsAccountPane1(false);
        setSettingsAccountPane2(false);
        setSettingsAccountPane3(false);
        setOthersSettingsAccountPane();

        setSettingsAccountPane1TextFieldListeners();
    }

    private void setOthersSettingsAccountPane() {
        mainController.anchorPaneSettingsBtnDeleteUser.setVisible(false);
        mainController.labelSettingsFillUpThisForm1.setVisible(false);
        mainController.labelSettingsFillUpThisForm2.setVisible(false);
        mainController.labelSettingsFillUpThisForm3.setVisible(false);
        mainController.labelSettingsFillUpThisForm4.setVisible(false);
        mainController.labelSettingsFillUpThisForm5.setVisible(false);
        mainController.labelSettingsFillUpThisForm6.setVisible(false);
        mainController.labelSettingsFillUpThisForm7.setVisible(false);
        mainController.labelSettingsFillUpThisForm8.setVisible(false);
        mainController.labelSettingsFillUpThisForm9.setVisible(false);
        mainController.labelSettingsFillUpThisForm10.setVisible(false);
        mainController.labelSettingsFillUpThisForm11.setVisible(false);
        mainController.labelMiddleNameOptional.setVisible(false);
        mainController.passwordToolTipImage.setVisible(false);
    }

    private void setSettingsAccountPane1TextFieldListeners() {
        mainController.textFieldAccountGivenName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                mainController.textFieldAccountGivenName.setText(newValue);
            } else if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY_NO_SPACE_IN_FRONT)) {
                mainController.textFieldAccountGivenName.setText(oldValue);
            }
        });
        mainController.textFieldAccountMiddleName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                mainController.textFieldAccountMiddleName.setText(newValue);
            } else if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY_NO_SPACE_IN_FRONT)) {
                mainController.textFieldAccountMiddleName.setText(oldValue);
            }
        });
        mainController.textFieldAccountLastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                mainController.textFieldAccountLastName.setText(newValue);
            } else if (!newValue.matches(REGEX_ENGLISH_ALPHABET_ONLY_NO_SPACE_IN_FRONT)) {
                mainController.textFieldAccountLastName.setText(oldValue);
            }
        });
    }

    private void disableOtherAccountEditButtons(int notToDisable) {
        setOthersSettingsAccountPane();
        switch (notToDisable) {
            case 1: // users
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(false);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(true);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(true);
                mainController.anchorPaneSettingsBtnDelete.setDisable(true);
                break;
            case 2: // account details
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(true);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(false);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(true);
                mainController.anchorPaneSettingsBtnDelete.setDisable(true);
                break;
            case 3: // security questions
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(true);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(true);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(false);
                mainController.anchorPaneSettingsBtnDelete.setDisable(true);
                break;
            case 4: // delete
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(true);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(true);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(true);
                mainController.anchorPaneSettingsBtnDelete.setDisable(false);
                break;
            case 5: // disable all
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(false);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(false);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(false);
                mainController.anchorPaneSettingsBtnDelete.setDisable(false);
                setAccountNameToUserIndex();
                removeAdditionalElements();
                break;
        }
        mainController.anchorPaneSettingsAccount.requestFocus();
    }

    private void setAccountNameToUserIndex() {
        mainController.comboBoxAccountName.setValue(fullNames.get(userIndex));
    }

    /**
     * Account - USERS
     */
    public void comboBoxNameOnAction() {
        setOthersSettingsAccountPane();
        if (mainController.comboBoxAccountName.getValue() != null) {
            if (!mainController.textFieldAccountGivenName.isDisabled())
                mainController.anchorPaneSettingsBtnDeleteUser.setVisible(!mainController.comboBoxAccountName.getValue().endsWith("(Admin)"));

            if (!mainController.comboBoxAccountName.getValue().equals(addUser)) {
                String selectedName = getFirstWord(mainController.comboBoxAccountName.getValue());
                int index = -1;

                for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
                    String firstName = getFirstWord(accountReference.getFirstNames().get(i));
                    if (selectedName.equals(firstName))
                        index = i;
                }

                if (index != -1) {
                    //setLeftPanelProfileName(index);
                    setFirstMiddleLastNameTextFields(index);
                }

                if (mainController.comboBoxAccountName.getValue().contains("Admin")) {
                    mainController.anchorPaneSettingsBtnDeleteUser.setVisible(false);
                }

                removeAdditionalElements();
            } else {
                addAUser();
            }
        }

        mainController.detectChangesUsers = false;
    }

    public void setSettingsAccountPane1(boolean isShow) {
        if (mainController.textFieldAccountGivenName.isDisabled()) {
            if (isShow) {
                disableOtherAccountEditButtons(1);
                oldAccountReference = accountReference.copy();
            } else {
                disableOtherAccountEditButtons(5);
            }
        }

        boolean proceed = checkPane1Changes();

        if (proceed && !mainController.textFieldAccountGivenName.isDisabled()) {
            disableUsersFields();
        }

        if (proceed) {
            mainController.imagePencilSettingsAccount1.setVisible(isShow);
            mainController.imagePencilSettingsAccount2.setVisible(isShow);
            mainController.imagePencilSettingsAccount3.setVisible(isShow);
            mainController.imagePencilSettingsAccount4.setVisible(isShow);

            mainController.labelMiddleNameOptional.setVisible(isShow);

            mainController.textFieldAccountGivenName.setDisable(!isShow);
            mainController.textFieldAccountMiddleName.setDisable(!isShow);
            mainController.textFieldAccountLastName.setDisable(!isShow);
            mainController.comboBoxAccountName.setDisable(!isShow);

            if (isShow)
                fullNames.add(addUser);
            else
                fullNames.remove(addUser);

            if (!mainController.comboBoxAccountName.getValue().contains("(Admin)"))
                mainController.anchorPaneSettingsBtnDeleteUser.setVisible(isShow);

            if (!isShow)
                mainController.labelSettingsAccountEditFinishUsers.setText("EDIT USERS");
            else
                mainController.labelSettingsAccountEditFinishUsers.setText("SAVE CHANGES");

            if (!isShow)
                revertBackUsers();

            mainController.detectChangesUsers = false;

            mainController.anchorPaneSettingsAccount.requestFocus();
        }
    }

    public void deleteSelectedName() {
        int index = getComboBoxNameIndex();

        System.out.println("line 733" + index + ", " +
                accountReference.getFirstNames().size() +":" +
                accountReference.getMiddleNames().size() +":" +
                accountReference.getLastNames().size() +":" +
                accountReference.getUserPasswords().size() +":");
        accountReference.getFirstNames().remove(index);
        accountReference.getMiddleNames().remove(index);
        accountReference.getLastNames().remove(index);
        accountReference.getUserPasswords().remove(index);

        mainController.mainModel.populateFullNamesObservableList();
        setComboBoxToDefault();
        mainController.anchorPaneSettingsBtnDeleteUser.setVisible(false);
        mainController.comboBoxAccountName.getItems().add(addUser);

        mainController.detectChangesUsers = true;
    }

    private void addAUser() {
        mainController.comboBoxAccountName.setValue(addUser);

        mainController.textFieldAccountGivenName.setText("");
        mainController.textFieldAccountMiddleName.setText("");
        mainController.textFieldAccountLastName.setText("");

        mainController.textFieldAccountGivenName.setPromptText("Enter First Name");
        mainController.textFieldAccountMiddleName.setPromptText("Enter Middle Name");
        mainController.textFieldAccountLastName.setPromptText("Enter Surname");

        mainController.anchorPaneSettingsBtnDeleteUser.setVisible(false);

        addAUserAdditionalElements();
    }

    private void addAUserAdditionalElements() {
        mainController.imagePencilSettingsAccount12 = new ImageView();
        mainController.imagePencilSettingsAccount12.setFitHeight(35.0);
        mainController.imagePencilSettingsAccount12.setFitWidth(35.0);
        mainController.imagePencilSettingsAccount12.setLayoutX(252.0);
        mainController.imagePencilSettingsAccount12.setLayoutY(400.0);
        mainController.imagePencilSettingsAccount12.setPickOnBounds(true);
        mainController.imagePencilSettingsAccount12.setPreserveRatio(true);
        mainController.imagePencilSettingsAccount12.setImage(pencil);

        mainController.labelNewUserPassword = new Label();
        mainController.labelNewUserPassword.setText("User Password");
        mainController.labelNewUserPassword.setLayoutX(292.0);
        mainController.labelNewUserPassword.setLayoutY(403.0);
        mainController.labelNewUserPassword.setPrefHeight(26.0);
        mainController.labelNewUserPassword.setPrefWidth(188.0);
        mainController.labelNewUserPassword.setFont(new Font("Arial", 22.0));

        mainController.passwordFieldAccountNewUser = new PasswordField();
        mainController.passwordFieldAccountNewUser.setLayoutX(580.0);
        mainController.passwordFieldAccountNewUser.setLayoutY(395.9);
        mainController.passwordFieldAccountNewUser.setPrefWidth(436.0);
        mainController.passwordFieldAccountNewUser.setPromptText("Enter User Password");
        mainController.passwordFieldAccountNewUser.setOnKeyPressed(event -> mainController.settingsUsersTyping(event));
        mainController.passwordFieldAccountNewUser.setFont(new Font("Arial", 24.0));

        mainController.labelSettingsFillUpThisForm12 = new Label("*please fill up this form");
        mainController.labelSettingsFillUpThisForm12.setLayoutX(1031.0);
        mainController.labelSettingsFillUpThisForm12.setLayoutY(407.0);
        mainController.labelSettingsFillUpThisForm12.setTextFill(Color.web("#ff4e4e"));
        mainController.labelSettingsFillUpThisForm12.setFont(new Font("Arial", 18.0));
        mainController.labelSettingsFillUpThisForm12.setVisible(false);

        mainController.labelNewUserPasswordImportant = new Label();
        mainController.labelNewUserPasswordImportant.setWrapText(true);
        mainController.labelNewUserPasswordImportant.setText("Important:\nPlease remember this password. It cannot be changed.");
        mainController.labelNewUserPasswordImportant.setLayoutX(580.0);
        mainController.labelNewUserPasswordImportant.setLayoutY(446.0);
        mainController.labelNewUserPasswordImportant.setTextFill(Color.web("#000000"));
        mainController.labelNewUserPasswordImportant.setFont(Font.font("Arial", FontWeight.BOLD, 18.0));

        mainController.anchorPaneSettingsAccountUsers.getChildren().addAll(mainController.imagePencilSettingsAccount12, mainController.labelNewUserPassword, mainController.passwordFieldAccountNewUser, mainController.labelSettingsFillUpThisForm12, mainController.labelNewUserPasswordImportant);

        mainController.anchorPaneSettingsBtnEditUsers.setLayoutX(1002.0);
        mainController.anchorPaneSettingsBtnEditUsers.setLayoutY(515.0);
        mainController.anchorPaneSettingsAccountUsers.setPrefHeight(600);
    }

    private void disableUsersFields() {
        mainController.comboBoxAccountName.setDisable(true);
        mainController.textFieldAccountGivenName.setDisable(true);
        mainController.textFieldAccountMiddleName.setDisable(true);
        mainController.textFieldAccountLastName.setDisable(true);
        mainController.anchorPaneSettingsBtnDeleteUser.setVisible(false);
        mainController.labelSettingsAccountEditFinishUsers.setText("EDIT USERS");
        disableOtherAccountEditButtons(5);

        mainController.detectChangesUsers = false;

        mainController.anchorPaneSettingsAccount.requestFocus();
    }

    private void removeAdditionalElements() {
        mainController.anchorPaneSettingsAccountUsers.getChildren().removeAll(mainController.imagePencilSettingsAccount12, mainController.labelNewUserPassword, mainController.passwordFieldAccountNewUser, mainController.labelSettingsFillUpThisForm12, mainController.labelNewUserPasswordImportant);

        mainController.anchorPaneSettingsBtnEditUsers.setLayoutX(1002.0);
        mainController.anchorPaneSettingsBtnEditUsers.setLayoutY(390.0);
        mainController.anchorPaneSettingsAccountUsers.setPrefHeight(500);
    }

    public String getFirstWord(String value) {
        String[] words = value.split("\\s+");
        return words[0];
    }

    private void setFirstMiddleLastNameTextFields(int index) {
        mainController.textFieldAccountGivenName.setText(accountReference.getFirstNames().get(index));
        mainController.textFieldAccountMiddleName.setText(accountReference.getMiddleNames().get(index));
        mainController.textFieldAccountLastName.setText(accountReference.getLastNames().get(index));
    }

    public boolean setLeftPanelProfileNameConfirmPassword() {
        isInputPasswordExistingUser = true;
        return saveChanges(5);
    }

    public void setLeftPanelProfileName(int index) {
        mainController.comboBoxAccountName.setValue(fullNames.get(index));
        mainController.labelProfileName.setText(usersNames.get(index));
    }

    public void setSettingsAccountStyle() {
        mainController.comboBoxAccountName.setStyle(settingsAccountNameComboBoxStyle);
        mainController.comboBoxSettingsQuestionOne.setStyle(settingsAccountQuestionsComboBoxStyle);
        mainController.comboBoxSettingsQuestionTwo.setStyle(settingsAccountQuestionsComboBoxStyle);
        mainController.settingsModel.setStyles(accountReference.getDisplayColor(), false);
    }

    private boolean checkPane1Changes() {
        if (mainController.detectChangesUsers) {
            if (!isNewUserFieldsEmpty()) {
                if (!isNameAlreadyExist()) {
                    if (saveChanges(1)) {
                        accountEditingProceed = false;

                        getUsersChanges();

                        return resultOfUpdateAccountToAccountCSV(updateAccountToAccountCSV(oldAccountReference, accountReference));
                    } else {
                        failedInputPasswordForAccountAccountUpdate();
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return isNewUserFieldsAllEmpty();
            }
        } else {
            return true;
        }
    }

    private boolean resultOfUpdateAccountToAccountCSV(boolean isSuccess) {
        if (isSuccess) {
            setAccountUsersSuccessful();
            mainController.mainModel.generateNotification();

            mainController.mainModel.populateFullNamesObservableList();
            setNameToNewlyAddedName();

            disableOtherAccountEditButtons(5);
            mainController.anchorPaneSettingsBtnDeleteUser.setVisible(false);
        } else {
            setErrorFailedToUpdateAccountToCSV();
            mainController.mainModel.openPrompt();

            setAccountUsersUpdateFailedCSV();
            mainController.mainModel.generateNotification();

            revertBackUsers();
        }
        return isSuccess;
    }

    private void failedInputPasswordForAccountAccountUpdate() {
        if (!maxAttemptLimitReached) {
            setErrorFailedToUpdateAccountUserCancelled();
            mainController.mainModel.openPrompt();
            setAccountUsersUpdateFailedCancelled();
        } else {
            setErrorChangingPasswordMaximumAttemptReached();
            setAccountDetailsEditFailedMaxLimitReached();
            Platform.runLater(this::disableUsersFields);
        }
        mainController.mainModel.generateNotification();
        revertBackUsers();
        maxAttemptLimitReached = false;
    }

    private void revertBackUsers() {
        Platform.runLater(() -> {
            revertToOldValuesPane1();
            mainController.mainModel.populateFullNamesObservableList();
            setComboBoxToDefault();
            disableUsersFields();
        });
    }

    private void setComboBoxToDefault() {
        mainController.comboBoxAccountName.setValue(fullNames.get(0));
        //mainController.labelProfileName.setText(usersNames.get(0));
    }

    private boolean usersRequiredFieldsNotBlank() {
        return !mainController.textFieldAccountGivenName.getText().trim().isEmpty() && !mainController.textFieldAccountLastName.getText().trim().isEmpty() && nameNotExist();
    }

    private boolean nameNotExist() {
        String fullName = mainController.textFieldAccountGivenName.getText().trim() + " " + mainController.textFieldAccountMiddleName.getText().trim() + " " + mainController.textFieldAccountLastName.getText().trim();

        for (String existingName : fullNames) {
            if (existingName.contains(" (Admin)"))
                existingName = existingName.replace(" (Admin)", "").trim();

            if (existingName.equalsIgnoreCase(fullName))
                return false;
        }
        return true;
    }

    private boolean isNewUserFieldsAllEmpty() {
        boolean isAllEmpty = mainController.textFieldAccountGivenName.getText().trim().isEmpty() && mainController.textFieldAccountLastName.getText().trim().isEmpty();

        if (mainController.passwordFieldAccountNewUser != null) {
            isAllEmpty = isAllEmpty && mainController.passwordFieldAccountNewUser.getText().trim().isEmpty();
        }

        if (isAllEmpty) {
            putUsersInformation();
            mainController.labelSettingsAccountEditFinishUsers.setText("EDIT USERS");
        }

        return isAllEmpty;
    }

    private void putUsersInformation() {
        mainController.textFieldAccountGivenName.setText(accountReference.getFirstNames().get(0));
        mainController.textFieldAccountMiddleName.setText(accountReference.getMiddleNames().get(0));
        mainController.textFieldAccountLastName.setText(accountReference.getLastNames().get(0));
    }

    private boolean isNewUserFieldsEmpty() {
        boolean isEmpty = mainController.textFieldAccountGivenName.getText().trim().isEmpty() || mainController.textFieldAccountLastName.getText().trim().isEmpty();

        if (mainController.passwordFieldAccountNewUser != null) {
            if (mainController.passwordFieldAccountNewUser.getText().trim().isEmpty()) {
                mainController.labelSettingsFillUpThisForm12.setVisible(true);
                isEmpty = true;
            }
        }

        return isEmpty;
    }

    private boolean isNameAlreadyExist() {
        return mainController.labelSettingsFillUpThisForm9.isVisible();
    }

    public void accountUsersTyping() {
        String givenName = mainController.textFieldAccountGivenName.getText().trim();
        String lastName = mainController.textFieldAccountLastName.getText().trim();

        mainController.labelSettingsFillUpThisForm1.setVisible(givenName.isEmpty());
        mainController.labelSettingsFillUpThisForm2.setVisible(lastName.isEmpty());

        mainController.labelSettingsFillUpThisForm9.setVisible(!nameNotExist());

        if (mainController.labelSettingsFillUpThisForm12 != null) {
            String userPassword = mainController.passwordFieldAccountNewUser.getText().trim();
            mainController.labelSettingsFillUpThisForm12.setVisible(userPassword.isEmpty());
        }
    }

    private void revertToOldValuesPane1() {
        if (oldAccountReference != null) {
            accountReference.getFirstNames().setAll(oldAccountReference.getFirstNames());
            accountReference.getMiddleNames().setAll(oldAccountReference.getMiddleNames());
            accountReference.getLastNames().setAll(oldAccountReference.getLastNames());
            accountReference.getUserPasswords().setAll(oldAccountReference.getUserPasswords());
        }
    }

    private void getUsersChanges() {
        String textFieldFirstName = mainController.textFieldAccountGivenName.getText().trim();
        String textFieldMiddleName = mainController.textFieldAccountMiddleName.getText().trim().isEmpty() ? "" : mainController.textFieldAccountMiddleName.getText().trim();
        String textFieldLastName = mainController.textFieldAccountLastName.getText().trim();
        String textFieldUserPassword;
        if (mainController.passwordFieldAccountNewUser != null)
            textFieldUserPassword = mainController.passwordFieldAccountNewUser.getText().trim();
        else
            textFieldUserPassword = "";

        if (mainController.comboBoxAccountName.getValue().equals(addUser)) {
            accountReference.getFirstNames().add(textFieldFirstName);
            accountReference.getMiddleNames().add(textFieldMiddleName);
            accountReference.getLastNames().add(textFieldLastName);
            accountReference.getUserPasswords().add(textFieldUserPassword);
        } else {
            String selectedName = getFirstWord(mainController.comboBoxAccountName.getValue());
            int index = -1;

            for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
                String firstName = getFirstWord(accountReference.getFirstNames().get(i));
                if (selectedName.equals(firstName))
                    index = i;
            }

            if (index != -1) {
                accountReference.getFirstNames().set(index, textFieldFirstName);
                accountReference.getMiddleNames().set(index, textFieldMiddleName);
                accountReference.getLastNames().set(index, textFieldLastName);
            }
        }
    }

    private void setNameToNewlyAddedName() {
        mainController.comboBoxAccountName.setValue(fullNames.get(fullNames.size() - 1));
        //mainController.comboBoxLeftPanelUsers.setValue(usersNames.get(usersNames.size() - 1));
    }

    private int getComboBoxNameIndex() {
        String selectedName = mainController.comboBoxAccountName.getValue();

        int index = -1;

        for (int i = 0; i < fullNames.size() ; i++) {
            if (selectedName.equals(fullNames.get(i)))
                index = i;
        }

        return index;
    }


    /**
     * Account - Account Details
     */

    public void setSettingsAccountPane2(boolean isShow) {
        if (mainController.textFieldAccountContact.isDisabled()) {
            if (isShow) {
                disableOtherAccountEditButtons(2);
                oldAccountReference = accountReference.copy();
            } else {
                disableOtherAccountEditButtons(5);
            }
        }

        if (mainController.labelSettingsAccountEditFinishAccountDetails.getText().equals("SAVE CHANGES"))
            mainController.accountDetailsSubmittedOnce = true;

        boolean proceed = checkPane2Changes();

        if (proceed && !mainController.textFieldAccountContact.isDisabled()) {
            disableOtherAccountEditButtons(5);
        }

        if (proceed) {
            mainController.imagePencilSettingsAccount5.setVisible(isShow);
            mainController.imagePencilSettingsAccount6.setVisible(isShow);
            mainController.imagePencilSettingsAccount7.setVisible(isShow);

            mainController.textFieldAccountContact.setDisable(!isShow);
            mainController.textFieldAccountNewPassword.setDisable(!isShow);
            mainController.textFieldAccountConfirmNewPassword.setDisable(!isShow);

            mainController.passwordFieldAccountNewPassword.setDisable(!isShow);
            mainController.passwordFieldAccountConfirmNewPassword.setDisable(!isShow);

            mainController.imageHideShowNewPasswordAccountSettings.setVisible(isShow);
            mainController.imageHideShowConfirmNewPasswordAccountSettings.setVisible(isShow);

            if (!isShow)
                mainController.labelSettingsAccountEditFinishAccountDetails.setText("EDIT ACCOUNT DETAILS");
            else
                mainController.labelSettingsAccountEditFinishAccountDetails.setText("SAVE CHANGES");

            if (isShow)
                resetPasswordFields();

            editingAccountDetails();
        }
    }

    private boolean checkPane2Changes() {
        if (mainController.detectChangesAccountDetails) {
            if (!isAccountDetailsFieldsEmpty()) {
                if (!isAccountDetailsRequiredFieldsNotValid()) {
                    return false;
                } else {
                    if (saveChanges(2)) {
                        accountEditingProceed = false;

                        getAccountDetailsChanges();

                        if (updateAccountToAccountCSV(oldAccountReference, accountReference) && updatePhotosCSV(oldAccountReference, accountReference)) {
                            editAccountDetailsSuccess();
                            return true;
                        } else {
                            editAccountDetailsFailedCSV();
                            return false;
                        }
                    } else {
                        editAccountDetailsFailedIncorrectPassword();
                        return true;
                    }
                }
            } else {
                setAccountDetailsValue();
                return true;
            }
        } else {
            return true;
        }
    }

    private void editAccountDetailsSuccess() {
        dataTasks();
        updateDirectoryFilePaths();
        disableEditAccountDetails();
        Platform.runLater(() -> {
            dataTasks();
            clearFieldsPasswordTexts();
            setAccountDetailsEditSuccessful();
            mainController.mainModel.generateNotification();
            disableOtherAccountEditButtons(5);
            mainController.accountDetailsSubmittedOnce = false;
            updateDirectoryFilePaths();
            mainController.textFieldAccountContact.setDisable(true);
            mainController.textFieldAccountNewPassword.setDisable(true);
            mainController.textFieldAccountConfirmNewPassword.setDisable(true);
            mainController.imagePencilSettingsAccount5.setVisible(false);
            mainController.imagePencilSettingsAccount6.setVisible(false);
            mainController.imagePencilSettingsAccount7.setVisible(false);
            mainController.imageHideShowConfirmNewPasswordAccountSettings.setVisible(false);
            mainController.imageHideShowNewPasswordAccountSettings.setVisible(false);
            mainController.anchorPanePasswordIndicator.setVisible(true);
            mainController.labelSettingsAccountEditFinishAccountDetails.setText("EDIT ACCOUNT DETAILS");
        });
        updateDirectoryFilePaths();
        clearFieldsPasswordTexts();
        disableEditAccountDetails();
        Platform.runLater(this::passwordIndicator);
        Platform.runLater(this::editingAccountDetails);
    }

    private void disableEditAccountDetails() {
        clearFieldsPasswordTexts();
        disableOtherAccountEditButtons(5);
        mainController.accountDetailsSubmittedOnce = false;
        mainController.textFieldAccountContact.setDisable(true);
        mainController.textFieldAccountNewPassword.setDisable(true);
        mainController.textFieldAccountConfirmNewPassword.setDisable(true);
        mainController.imagePencilSettingsAccount5.setVisible(false);
        mainController.imagePencilSettingsAccount6.setVisible(false);
        mainController.imagePencilSettingsAccount7.setVisible(false);
        mainController.imageHideShowConfirmNewPasswordAccountSettings.setVisible(false);
        mainController.imageHideShowNewPasswordAccountSettings.setVisible(false);
        mainController.anchorPanePasswordIndicator.setVisible(true);
        mainController.labelSettingsAccountEditFinishAccountDetails.setText("EDIT ACCOUNT DETAILS");

        Platform.runLater(this::resetPasswordFields);
    }

    private void editAccountDetailsFailedCSV() {
        setErrorFailedToUpdateAccountToCSV();
        mainController.mainModel.openPrompt();
        setAccountDetailsEditFailed();
        mainController.mainModel.generateNotification();
        Platform.runLater(() -> {
            clearFieldsPasswordTexts();
            revertToOldValuesPane2();
            disableEditAccountDetails();
            mainController.labelSettingsAccountEditFinishAccountDetails.setText("EDIT ACCOUNT DETAILS");
        });
    }

    private void editAccountDetailsFailedIncorrectPassword() {
        if (!maxAttemptLimitReached) {
            setErrorFailedToUpdateAccountUserCancelled();
            mainController.mainModel.openPrompt();
            Platform.runLater(() -> {
                setAccountDetailsEditFailedCancelled();
                mainController.mainModel.generateNotification();
                clearFieldsPasswordTexts();
                revertToOldValuesPane2();
                disableEditAccountDetails();
                mainController.labelSettingsAccountEditFinishAccountDetails.setText("EDIT ACCOUNT DETAILS");
                maxAttemptLimitReached = false;
                editingAccountDetails();
            });
            maxAttemptLimitReached = false;
        } else {
            setAccountDetailsEditFailedMaxLimitReached();
            mainController.mainModel.generateNotification();
            Platform.runLater(() -> {
                clearFieldsPasswordTexts();
                revertToOldValuesPane2();
                disableEditAccountDetails();
                mainController.labelSettingsAccountEditFinishAccountDetails.setText("EDIT ACCOUNT DETAILS");
                maxAttemptLimitReached = false;
                editingAccountDetails();
            });
            maxAttemptLimitReached = false;
        }
        editingAccountDetails();
        Platform.runLater(this::editingAccountDetails);
        passwordIndicator();
        Platform.runLater(this::passwordIndicator);
    }

    private void setAccountDetailsValue() {
        mainController.textFieldAccountContact.setText(accountReference.getContact());
        resetPasswordFields();
        editingAccountDetails();
    }

    private void resetPasswordFields() {
        mainController.showNewPassword = false;
        mainController.textFieldAccountNewPassword.setText("");
        mainController.textFieldAccountNewPassword.setVisible(false);
        mainController.passwordFieldAccountNewPassword.setText("");
        mainController.passwordFieldAccountNewPassword.setVisible(true);

        mainController.showConfirmNewPassword = false;
        mainController.textFieldAccountConfirmNewPassword.setText("");
        mainController.textFieldAccountConfirmNewPassword.setVisible(false);
        mainController.passwordFieldAccountConfirmNewPassword.setText("");
        mainController.passwordFieldAccountConfirmNewPassword.setVisible(true);
        Platform.runLater(this::passwordIndicator);
    }

    private void editingAccountDetails() {
        Platform.runLater(() -> {
            mainController.detectChangesAccountDetails = false;
            mainController.accountDetailsSubmittedOnce = false;

            Platform.runLater(this::resetPasswordFields);
        });
    }

    private void updateDirectoryFilePaths() {
        if (!oldAccountReference.getContact().equals(accountReference.getContact())) {
            accountContactReference = accountReference.getContact();
            setPaths();

            reloadOrderHistoryAndOrderQueueObservableList();
            reloadProductsObservableList();
        }
    }

    private void reloadOrderHistoryAndOrderQueueObservableList() {
        orderHistoryObservableList.clear();
        orderQueueObservableList.clear();
        importOrdersFromCSV(true);
        importOrdersFromCSV(false);
    }

    private void reloadProductsObservableList() {
        allProductObservableList.clear();
        availableAllProductObservableList.clear();
        availableMilkTeaObservableList.clear();
        availableCoolersObservableList.clear();
        availableCoffeeObservableList.clear();
        availableIceCandyCupsObservableList.clear();
        availableAppetizerObservableList.clear();
        unavailableAllProductObservableList.clear();
        unavailableMilkTeaObservableList.clear();
        unavailableCoolersObservableList.clear();
        unavailableCoffeeObservableList.clear();
        unavailableIceCandyCupsObservableList.clear();
        unavailableAppetizerObservableList.clear();

        isAddingProductsFromImport = true;
        importProductsFromCSV(CSV_FILE_PATH_PRODUCTS, false);
        isAddingProductsFromImport = false;
    }

    private boolean isAccountDetailsFieldsEmpty() {
        String account = mainController.textFieldAccountContact.getText().trim();
        String newPassword = mainController.showNewPassword ? mainController.textFieldAccountNewPassword.getText().trim() : mainController.passwordFieldAccountNewPassword.getText().trim();
        String confirmNewPassword = mainController.showConfirmNewPassword ? mainController.textFieldAccountConfirmNewPassword.getText().trim() : mainController.passwordFieldAccountConfirmNewPassword.getText().trim();

        return account.isEmpty() && newPassword.isEmpty() && confirmNewPassword.isEmpty();
    }

    private boolean isAccountDetailsRequiredFieldsNotValid() {
        String account = mainController.textFieldAccountContact.getText().trim();
        String newPassword = mainController.showNewPassword ? mainController.textFieldAccountNewPassword.getText().trim() : mainController.passwordFieldAccountNewPassword.getText().trim();
        String confirmNewPassword = mainController.showConfirmNewPassword ? mainController.textFieldAccountConfirmNewPassword.getText().trim() : mainController.passwordFieldAccountConfirmNewPassword.getText().trim();

        boolean willChangeAccount = !oldAccountReference.getContact().equals(account);
        boolean willChangePass = !newPassword.isEmpty() || !confirmNewPassword.isEmpty();

        boolean isValidAccount = account.matches(REGEX_PHONE_NUMBER_ELEVEN_DIGIT) || account.matches(REGEX_EMAIL);
        boolean isValidPassword = newPassword.equals(confirmNewPassword);

        boolean proceed = true;
        if (willChangePass && willChangeAccount) {
            proceed = isValidAccount && isValidPassword;
        } else if (willChangeAccount) {
            proceed = isValidAccount;
        } else if (willChangePass) {
            proceed = isValidPassword;
        }

        setVisibilitiesPane2();
        return proceed && newAccountNotExists() && newPasswordNotTheSameAsOldOne();
    }

    private void setVisibilitiesPane2() {
        if (mainController.accountDetailsSubmittedOnce) {
            String account = mainController.textFieldAccountContact.getText().trim();
            String newPassword = mainController.showNewPassword ? mainController.textFieldAccountNewPassword.getText().trim() : mainController.passwordFieldAccountNewPassword.getText().trim();
            String confirmNewPassword = mainController.showConfirmNewPassword ? mainController.textFieldAccountConfirmNewPassword.getText().trim() : mainController.passwordFieldAccountConfirmNewPassword.getText().trim();

            boolean willChangeAccount = !oldAccountReference.getContact().equals(account);
            boolean willChangePass = !newPassword.isEmpty() || !confirmNewPassword.isEmpty();

            mainController.labelSettingsFillUpThisForm8.setText("*password does not match");

            if (willChangePass && willChangeAccount) {
                mainController.labelSettingsFillUpThisForm6.setVisible(newPassword.isEmpty());
                mainController.labelSettingsFillUpThisForm7.setVisible(confirmNewPassword.isEmpty());

                if (!mainController.labelSettingsFillUpThisForm7.isVisible() && !mainController.labelSettingsFillUpThisForm11.isVisible())
                    mainController.labelSettingsFillUpThisForm8.setVisible(!newPassword.equals(confirmNewPassword));

                if (account.isEmpty()) {
                    mainController.labelSettingsFillUpThisForm3.setText("*please fill up this form");
                    mainController.labelSettingsFillUpThisForm3.setVisible(true);
                }
                else if (!account.matches(REGEX_PHONE_NUMBER_ELEVEN_DIGIT) || !account.matches(REGEX_EMAIL)) {
                    mainController.labelSettingsFillUpThisForm3.setText("*invalid email or phone number");
                    mainController.labelSettingsFillUpThisForm3.setVisible(true);
                }
                mainController.labelSettingsFillUpThisForm11.setVisible(!newPasswordNotTheSameAsOldOne());

                if (mainController.labelSettingsFillUpThisForm11.isVisible())
                    mainController.anchorPanePasswordIndicator.setVisible(false);
            } else if (willChangeAccount) {
                if (account.isEmpty()) {
                    mainController.labelSettingsFillUpThisForm3.setText("*please fill up this form");
                    mainController.labelSettingsFillUpThisForm3.setVisible(true);
                }
                else if (!account.matches(REGEX_PHONE_NUMBER_ELEVEN_DIGIT) || !account.matches(REGEX_EMAIL)) {
                    mainController.labelSettingsFillUpThisForm3.setText("*invalid email or phone number");
                    mainController.labelSettingsFillUpThisForm3.setVisible(true);
                }
            } else if (willChangePass) {
                mainController.labelSettingsFillUpThisForm6.setVisible(newPassword.isEmpty());
                mainController.labelSettingsFillUpThisForm7.setVisible(confirmNewPassword.isEmpty());

                if (!mainController.labelSettingsFillUpThisForm7.isVisible() && !mainController.labelSettingsFillUpThisForm11.isVisible())
                    mainController.labelSettingsFillUpThisForm8.setVisible(!newPassword.equals(confirmNewPassword));

                mainController.labelSettingsFillUpThisForm11.setVisible(!newPasswordNotTheSameAsOldOne());

                if (mainController.labelSettingsFillUpThisForm11.isVisible())
                    mainController.anchorPanePasswordIndicator.setVisible(false);
            }

            if (!willChangePass) {
                mainController.labelSettingsFillUpThisForm6.setVisible(false);
                mainController.labelSettingsFillUpThisForm7.setVisible(false);
                mainController.labelSettingsFillUpThisForm8.setVisible(false);
                mainController.labelSettingsFillUpThisForm11.setVisible(false);
                mainController.anchorPanePasswordIndicator.setVisible(true);
            }

            if (!willChangeAccount) {
                mainController.labelSettingsFillUpThisForm3.setVisible(false);
                mainController.labelSettingsFillUpThisForm10.setVisible(false);
            }

            if (!willChangeAccount && !willChangePass) {
                mainController.detectChangesAccountDetails = false;
                mainController.anchorPanePasswordIndicator.setVisible(true);
            }

            mainController.labelSettingsFillUpThisForm10.setVisible(!newAccountNotExists());
        }
    }

    public void accountDetailsTyping() {
        String contact = mainController.textFieldAccountContact.getText().trim();
        String newPassword = mainController.showNewPassword ? mainController.textFieldAccountNewPassword.getText().trim() : mainController.passwordFieldAccountNewPassword.getText().trim();
        String confirmNewPassword = mainController.showConfirmNewPassword ? mainController.textFieldAccountConfirmNewPassword.getText().trim() : mainController.passwordFieldAccountConfirmNewPassword.getText().trim();

        mainController.detectChangesAccountDetails = !oldAccountReference.getContact().equals(contact) || !newPassword.isEmpty() || !confirmNewPassword.isEmpty();
        setVisibilitiesPane2();
    }

    private boolean newAccountNotExists() {
        String contact = mainController.textFieldAccountContact.getText().trim();

        List<Account> accountCopyList = new ArrayList<>(accountSet);
        accountCopyList.remove(accountReference);

        for (Account account : accountCopyList) {
            if (account.getContact().equals(contact))
                return false;
        }

        return true;
    }

    private boolean newPasswordNotTheSameAsOldOne() {
        String newPassword = mainController.showNewPassword ? mainController.textFieldAccountNewPassword.getText().trim() : mainController.passwordFieldAccountNewPassword.getText().trim();

        return !accountReference.getPassword().equals(newPassword);
    }

    private void dataTasks() {
        loggedOutSoDeleteStayLoggedInDetails();
        inputIntoSecondRow(accountReference);

        String newFolderName = accountReference.getContact();
        File oldFolder = new File(DIRECTORY_PATH_ACCOUNTS);
        File newFolder = new File(oldFolder.getParent(), newFolderName);
        if (oldFolder.exists() && oldFolder.isDirectory())
            oldFolder.renameTo(newFolder);
    }

    private void clearFieldsPasswordTexts() {
        mainController.textFieldAccountNewPassword.setText("");
        mainController.textFieldAccountConfirmNewPassword.setText("");
        mainController.passwordFieldAccountNewPassword.setText("");
        mainController.passwordFieldAccountConfirmNewPassword.setText("");
    }

    private void revertToOldValuesPane2() {
        mainController.accountDetailsSubmittedOnce = false;

        accountReference.setContact(oldAccountReference.getContact());
        accountReference.setPassword(oldAccountReference.getPassword());

        mainController.textFieldAccountNewPassword.setVisible(false);
        mainController.textFieldAccountConfirmNewPassword.setVisible(false);

        mainController.passwordFieldAccountNewPassword.setVisible(true);
        mainController.passwordFieldAccountConfirmNewPassword.setVisible(true);

        mainController.textFieldAccountContact.setText(accountReference.getContact());
        mainController.passwordFieldAccountNewPassword.setText("");
        mainController.passwordFieldAccountConfirmNewPassword.setText("");

        mainController.imageHideShowNewPasswordAccountSettings.setImage(HIDE_IMAGE);
        mainController.imageHideShowConfirmNewPasswordAccountSettings.setImage(HIDE_IMAGE);

        mainController.textFieldAccountContact.setDisable(true);
        mainController.passwordFieldAccountNewPassword.setDisable(true);
        mainController.passwordFieldAccountConfirmNewPassword.setDisable(true);

        mainController.labelSettingsAccountEditFinishAccountDetails.setText("EDIT ACCOUNT DETAILS");
        disableOtherAccountEditButtons(5);
    }

    private void getAccountDetailsChanges() {
        String contact = mainController.textFieldAccountContact.getText().trim();
        String newPassword = mainController.showNewPassword ? mainController.textFieldAccountNewPassword.getText() : mainController.passwordFieldAccountNewPassword.getText();

        if (newPassword.isEmpty())
            newPassword = accountReference.getPassword();

        accountReference.setContact(contact);
        accountReference.setPassword(newPassword);
    }


    public void toggleNewPasswordField() {
        mainController.showNewPassword = !mainController.showNewPassword;

        mainController.imageHideShowNewPasswordAccountSettings.setImage(mainController.showNewPassword ? HIDE_IMAGE : SHOW_IMAGE);
        mainController.textFieldAccountNewPassword.setVisible(mainController.showNewPassword);
        mainController.passwordFieldAccountNewPassword.setVisible(!mainController.showNewPassword);

        if (mainController.showNewPassword) {
            mainController.textFieldAccountNewPassword.setText(mainController.passwordFieldAccountNewPassword.getText());
            Tooltip.uninstall(mainController.imageHideShowNewPasswordAccountSettings, showPasswordToolTip);
            Tooltip.install(mainController.imageHideShowNewPasswordAccountSettings, hidePasswordToolTip);
        } else {
            mainController.passwordFieldAccountNewPassword.setText(mainController.textFieldAccountNewPassword.getText());
            Tooltip.uninstall(mainController.imageHideShowNewPasswordAccountSettings, hidePasswordToolTip);
            Tooltip.install(mainController.imageHideShowNewPasswordAccountSettings, showPasswordToolTip);
        }
    }

    public void toggleConfirmNewPasswordField() {
        mainController.showConfirmNewPassword = !mainController.showConfirmNewPassword;

        mainController.imageHideShowConfirmNewPasswordAccountSettings.setImage(mainController.showConfirmNewPassword ? HIDE_IMAGE : SHOW_IMAGE);
        mainController.textFieldAccountConfirmNewPassword.setVisible(mainController.showConfirmNewPassword);
        mainController.passwordFieldAccountConfirmNewPassword.setVisible(!mainController.showConfirmNewPassword);

        if (mainController.showConfirmNewPassword) {
            mainController.textFieldAccountConfirmNewPassword.setText(mainController.passwordFieldAccountConfirmNewPassword.getText());
            Tooltip.uninstall(mainController.imageHideShowConfirmNewPasswordAccountSettings, showPasswordToolTip);
            Tooltip.install(mainController.imageHideShowConfirmNewPasswordAccountSettings, hidePasswordToolTip);
        } else {
            mainController.passwordFieldAccountConfirmNewPassword.setText(mainController.textFieldAccountConfirmNewPassword.getText());
            Tooltip.uninstall(mainController.imageHideShowConfirmNewPasswordAccountSettings, hidePasswordToolTip);
            Tooltip.install(mainController.imageHideShowConfirmNewPasswordAccountSettings, showPasswordToolTip);
        }
    }


    /**
     * Password Indicator
     */
    public String textPasswordIndicator = "";

    public void passwordIndicator() {
        if (!mainController.labelSettingsFillUpThisForm11.isVisible())
            mainController.anchorPanePasswordIndicator.setVisible(true);

        textPasswordIndicator = mainController.showNewPassword ? mainController.textFieldAccountNewPassword.getText().trim() : mainController.passwordFieldAccountNewPassword.getText().trim();
        
        if (textPasswordIndicator.isBlank()) {
            emptyPassword();

        } else {
            int result = checkWeak();
            if (result == 1) {
                weakPassword();
            } else {

                result = checkFair();
                if (result == 2) {
                    fairPassword();
                } else if (result == -1) {
                    weakPassword();
                } else {

                    result = checkGood();
                    if (result == 1) {
                        weakPassword();
                    } else if (result == 2) {
                        fairPassword();
                    } else if (result == 3) {
                        goodPassword();
                    } else {
                        strongPassword();
                    }
                }
            }

        }
    }

    private int checkWeak() {
        return (textPasswordIndicator.length() < 8) ? 1 : -1;
    }

    private int checkFair() {
        if (textPasswordIndicator.length() < 8) return -1;

        int criteriaMet = 0;
        if (textPasswordIndicator.matches(".*[A-Z].*")) criteriaMet++;
        if (textPasswordIndicator.matches(".*\\d.*")) criteriaMet++;
        if (textPasswordIndicator.matches(".*[^A-Za-z0-9].*")) criteriaMet++;

        return (criteriaMet == 1) ? 2 : 3;
    }

    private int checkGood() {
        int criteriaMet = 0;
        if (textPasswordIndicator.length() >= 8) {
            criteriaMet++;
        }
        if (textPasswordIndicator.matches(".*[A-Z].*")) criteriaMet++;
        if (textPasswordIndicator.matches(".*\\d.*")) criteriaMet++;
        if (textPasswordIndicator.matches(".*[^A-Za-z0-9].*")) criteriaMet++;
        return criteriaMet;
    }

    public void emptyPassword() {
        mainController.isWeakPassword = true;
        mainController.passwordRectangle1.setFill(WHITE_ENUM.getColor());
        mainController.passwordRectangle2.setFill(WHITE_ENUM.getColor());
        mainController.passwordRectangle3.setFill(WHITE_ENUM.getColor());
        mainController.passwordRectangle4.setFill(WHITE_ENUM.getColor());
        mainController.passwordIndicator.setVisible(false);
    }

    private void weakPassword() {
        mainController.isWeakPassword = true;
        mainController.passwordRectangle1.setFill(WEAK_ENUM.getColor());
        mainController.passwordRectangle2.setFill(WHITE_ENUM.getColor());
        mainController.passwordRectangle3.setFill(WHITE_ENUM.getColor());
        mainController.passwordRectangle4.setFill(WHITE_ENUM.getColor());
        mainController.passwordIndicator.setText(WEAK_ENUM.getText());
        mainController.passwordIndicator.setTextFill(WEAK_ENUM.getColor());
        mainController.passwordIndicator.setVisible(true);
    }

    private void fairPassword() {
        mainController.isWeakPassword = false;
        mainController.passwordRectangle1.setFill(FAIR_ENUM.getColor());
        mainController.passwordRectangle2.setFill(FAIR_ENUM.getColor());
        mainController.passwordRectangle3.setFill(WHITE_ENUM.getColor());
        mainController.passwordRectangle4.setFill(WHITE_ENUM.getColor());
        mainController.passwordIndicator.setText(FAIR_ENUM.getText());
        mainController.passwordIndicator.setTextFill(FAIR_ENUM.getColor());
        mainController.passwordIndicator.setVisible(true);
    }

    private void goodPassword() {
        mainController.isWeakPassword = false;
        mainController.passwordRectangle1.setFill(GOOD_ENUM.getColor());
        mainController.passwordRectangle2.setFill(GOOD_ENUM.getColor());
        mainController.passwordRectangle3.setFill(GOOD_ENUM.getColor());
        mainController.passwordRectangle4.setFill(WHITE_ENUM.getColor());
        mainController.passwordIndicator.setText(GOOD_ENUM.getText());
        mainController.passwordIndicator.setTextFill(GOOD_ENUM.getColor());
        mainController.passwordIndicator.setVisible(true);
    }

    private void strongPassword() {
        mainController.isWeakPassword = false;
        mainController.passwordRectangle1.setFill(STRONG_ENUM.getColor());
        mainController.passwordRectangle2.setFill(STRONG_ENUM.getColor());
        mainController.passwordRectangle3.setFill(STRONG_ENUM.getColor());
        mainController.passwordRectangle4.setFill(STRONG_ENUM.getColor());
        mainController.passwordIndicator.setText(STRONG_ENUM.getText());
        mainController.passwordIndicator.setTextFill(STRONG_ENUM.getColor());
        mainController.passwordIndicator.setVisible(true);
    }


    /**
     * Account - Recovery Questions
     */
    public void setSettingsAccountPane3(boolean isShow) {
        if (mainController.comboBoxSettingsQuestionOne.isDisabled()) {
            if (isShow) {
                disableOtherAccountEditButtons(3);
                oldAccountReference = accountReference.copy();
            } else {
                disableOtherAccountEditButtons(5);
            }
        }

        boolean proceed = checkPane3Changes();

        if (proceed && !mainController.comboBoxSettingsQuestionOne.isDisabled()) {
            disableOtherAccountEditButtons(5);
        }

        if (proceed) {
            mainController.imagePencilSettingsAccount8.setVisible(isShow);
            mainController.imagePencilSettingsAccount9.setVisible(isShow);
            mainController.imagePencilSettingsAccount10.setVisible(isShow);
            mainController.imagePencilSettingsAccount11.setVisible(isShow);

            mainController.comboBoxSettingsQuestionOne.setDisable(!isShow);
            mainController.textFieldAccountQuestionOne.setDisable(!isShow);
            mainController.comboBoxSettingsQuestionTwo.setDisable(!isShow);
            mainController.textFieldAccountQuestionTwo.setDisable(!isShow);

            if (!isShow)
                mainController.labelSettingsAccountEditFinishSecurityQuestions.setText("EDIT SECURITY QUESTIONS");
            else
                mainController.labelSettingsAccountEditFinishSecurityQuestions.setText("SAVE CHANGES");

            mainController.textFieldAccountQuestionOne.setText(accountReference.getSecurityQuestionOneAnswer());
            mainController.textFieldAccountQuestionTwo.setText(accountReference.getSecurityQuestionTwoAnswer());

            mainController.detectChangesRecoveryQuestion = false;
        }
    }

    private boolean checkPane3Changes() {
        if (mainController.detectChangesRecoveryQuestion) {
            if (changesAreValidRecoveryQuestions()) {
                if (saveChanges(3)) {
                    accountEditingProceed = false;

                    getRecoveryQuestionsChanges();

                    if (updateAccountToAccountCSV(oldAccountReference, accountReference)) {
                        setRecoveryQuestionsNewValue();
                        setRecoveryQuestionEditSuccessful();
                        mainController.mainModel.generateNotification();
                        disableOtherAccountEditButtons(5);
                        return true;
                    } else {
                        setErrorFailedToUpdateAccountToCSV();
                        mainController.mainModel.openPrompt();
                        setRecoveryQuestionEditFailed();
                        mainController.mainModel.generateNotification();
                        revertBackRecoveryQuestions();
                        Platform.runLater(this::revertBackRecoveryQuestions);
                        return false;
                    }
                } else {
                    if (!maxAttemptLimitReached) {
                        setErrorFailedToUpdateAccountUserCancelled();
                        mainController.mainModel.openPrompt();
                        maxAttemptLimitReached = false;
                    }
                    setRecoveryQuestionEditFailed();
                    mainController.mainModel.generateNotification();
                    revertBackRecoveryQuestions();
                    Platform.runLater(this::revertBackRecoveryQuestions);
                    return true;
                }
            } else {
                // invalid answers
                return false;
            }
        } else {
            return true;
        }
    }

    private void getRecoveryQuestionsChanges() {
        accountReference.setSecurityQuestionOne(mainController.comboBoxSettingsQuestionOne.getValue());
        accountReference.setSecurityQuestionOneAnswer(mainController.textFieldAccountQuestionOne.getText().trim());
        accountReference.setSecurityQuestionTwo(mainController.comboBoxSettingsQuestionTwo.getValue());
        accountReference.setSecurityQuestionTwoAnswer(mainController.textFieldAccountQuestionTwo.getText().trim());
    }

    private void revertBackRecoveryQuestions() {
        accountReference.setSecurityQuestionOne(oldAccountReference.getSecurityQuestionOne());
        accountReference.setSecurityQuestionOneAnswer(oldAccountReference.getSecurityQuestionOneAnswer());
        accountReference.setSecurityQuestionTwo(oldAccountReference.getSecurityQuestionTwo());
        accountReference.setSecurityQuestionTwoAnswer(oldAccountReference.getSecurityQuestionTwoAnswer());

        mainController.comboBoxSettingsQuestionOne.setValue("");
        mainController.comboBoxSettingsQuestionTwo.setValue("");

        mainController.comboBoxSettingsQuestionOne.setValue(accountReference.getSecurityQuestionOne());
        mainController.textFieldAccountQuestionOne.setText(accountReference.getSecurityQuestionOneAnswer());
        mainController.comboBoxSettingsQuestionTwo.setValue(accountReference.getSecurityQuestionTwo());
        mainController.textFieldAccountQuestionTwo.setText(accountReference.getSecurityQuestionTwoAnswer());

        disableOtherAccountEditButtons(5);
    }

    private boolean changesAreValidRecoveryQuestions() {
        boolean changedQuestionOne = !oldAccountReference.getSecurityQuestionOne().equals(mainController.comboBoxSettingsQuestionOne.getValue());
        boolean changedQuestionTwo = !oldAccountReference.getSecurityQuestionTwo().equals(mainController.comboBoxSettingsQuestionTwo.getValue());
        boolean changedAnswerOne = !oldAccountReference.getSecurityQuestionOneAnswer().equals(mainController.textFieldAccountQuestionOne.getText().trim());
        boolean changedAnswerTwo = !oldAccountReference.getSecurityQuestionTwoAnswer().equals(mainController.textFieldAccountQuestionTwo.getText().trim());
        boolean firstAnswerNotBlank = !mainController.textFieldAccountQuestionOne.getText().trim().isEmpty();
        boolean secondAnswerNotBlank = !mainController.textFieldAccountQuestionTwo.getText().trim().isEmpty();

        mainController.labelSettingsFillUpThisForm4.setVisible(!firstAnswerNotBlank);
        mainController.labelSettingsFillUpThisForm5.setVisible(!secondAnswerNotBlank);

        return changedQuestionOne || changedQuestionTwo || changedAnswerOne || changedAnswerTwo || (firstAnswerNotBlank && secondAnswerNotBlank);
    }

    private void setRecoveryQuestionsNewValue() {
        mainController.comboBoxSettingsQuestionOne.setValue(accountReference.getSecurityQuestionOne());
        mainController.textFieldAccountQuestionOne.setText(accountReference.getSecurityQuestionOneAnswer());
        mainController.comboBoxSettingsQuestionTwo.setValue(accountReference.getSecurityQuestionTwo());
        mainController.textFieldAccountQuestionTwo.setText(accountReference.getSecurityQuestionTwoAnswer());
    }

    public void comboBoxQuestionOneOnAction() {
        if (!mainController.isUpdatingComboBox) {
            mainController.isUpdatingComboBox = true;
            String selectedItem = mainController.comboBoxSettingsQuestionOne.getValue();
            if (selectedItem != null) {
                ObservableList<String> filteredList = recoveryQuestionsObservableList.stream()
                        .filter(question -> !question.equals(selectedItem))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
                mainController.comboBoxSettingsQuestionTwo.setItems(filteredList);
            }
            mainController.isUpdatingComboBox = false;
        }

        if (oldAccountReference != null)
            mainController.detectChangesRecoveryQuestion = !oldAccountReference.getSecurityQuestionOne().equals(mainController.comboBoxSettingsQuestionOne.getValue());
    }

    public void comboBoxQuestionTwoOnAction() {
        if (!mainController.isUpdatingComboBox) {
            mainController.isUpdatingComboBox = true;
            String selectedItem = mainController.comboBoxSettingsQuestionTwo.getValue();
            if (selectedItem != null) {
                ObservableList<String> filteredList = recoveryQuestionsObservableList.stream()
                        .filter(question -> !question.equals(selectedItem))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
                mainController.comboBoxSettingsQuestionOne.setItems(filteredList);
            }
            mainController.isUpdatingComboBox = false;
        }

        if (oldAccountReference != null)
            mainController.detectChangesRecoveryQuestion = !oldAccountReference.getSecurityQuestionTwo().equals(mainController.comboBoxSettingsQuestionTwo.getValue());
    }

    public void accountRecoveryQuestionsTyping() {
        boolean firstAnswerNotBlank = !mainController.textFieldAccountQuestionOne.getText().trim().isEmpty();
        boolean secondAnswerNotBlank = !mainController.textFieldAccountQuestionTwo.getText().trim().isEmpty();

        mainController.labelSettingsFillUpThisForm4.setVisible(!firstAnswerNotBlank);
        mainController.labelSettingsFillUpThisForm5.setVisible(!secondAnswerNotBlank);

        mainController.detectChangesRecoveryQuestion = firstAnswerNotBlank || secondAnswerNotBlank;
    }

    private boolean saveChanges(int operationNumber) {
        // 1 - users, 2 - account, 3 - recovery questions, 4 account deletion, 5 - select user, 6 - delete product(s)
        switch (operationNumber) {
            case 1:
                headerTitle = USERS_ENUM.getHeaderTitle();
                break;
            case 2:
                headerTitle = ACCOUNT_DETAILS_ENUM.getHeaderTitle();
                break;
            case 3:
                headerTitle = RECOVERY_QUESTIONS_ENUM.getHeaderTitle();
                break;
            case 4:
                headerTitle = DELETE_ACCOUNT_ENUM.getHeaderTitle();
                break;
            case 5:
                headerTitle = USERS_SELECTION_ENUM.getHeaderTitle();
                break;
            case 6:
                headerTitle = DELETE_PRODUCT_ENUM.getHeaderTitle();
                break;
        }
        return openAskForPasswordFXML();
    }

    private boolean openAskForPasswordFXML() {
        accountEditingProceed = false;
        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ASK_FOR_PASSWORD.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        askForPasswordStage = new Stage();

        askForPasswordStage.initModality(Modality.WINDOW_MODAL);
        askForPasswordStage.initOwner(mainController.anchorPaneMenu.getScene().getWindow());

        askForPasswordStage.setTitle(ASK_FOR_PASSWORD.getTITLE());
        askForPasswordStage.setResizable(false);
        askForPasswordStage.getIcons().add(SYSTEM_LOGO);
        askForPasswordStage.setScene(new Scene(root));
        askForPasswordStage.showAndWait();
        mainController.mainModel.hideRectangleModal();
        return accountEditingProceed;
    }

    public void deleteAccountProcess() {
        boolean isCancelled = true;

        disableOtherAccountEditButtons(4);
        setDeleteAccount1();
        if (mainController.mainModel.openPrompt()) {
            if (saveChanges(4)) {
                setDeleteAccount2();
                if (mainController.mainModel.openPrompt()) {
                    if (deleteAccountFromCSV(accountReference) && deleteAccountFolder()) {
                        setDeleteAccount3AccountDeleted();
                        openPromptAccountDeleted();
                        isCancelled = false;
                    } else {
                        setErrorAccountDeletion();
                        mainController.mainModel.openPrompt();
                    }
                }
            }
        }

        if (isCancelled) {
            setAccountDeletionCancelled();
            mainController.mainModel.generateNotification();

            disableOtherAccountEditButtons(5);
        }
    }

    private void openPromptAccountDeleted() {
        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        promptStage = new Stage();

        promptStage.initModality(Modality.WINDOW_MODAL);
        promptStage.initOwner(mainController.anchorPaneMenu.getScene().getWindow());

        promptStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        promptStage.setResizable(false);
        promptStage.getIcons().add(SYSTEM_LOGO);
        promptStage.setScene(new Scene(root));
        PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
        delay.setOnFinished(event -> {
            mainController.mainModel.logOutAccountDeleted();
            accountSet.remove(accountReference);
        });
        delay.play();
        promptStage.showAndWait();
        mainController.mainModel.hideRectangleModal();
    }

    private boolean deleteAccountFolder() {
        Path folderPathToDelete = Paths.get(DIRECTORY_PATH_ACCOUNTS);

        try {
            Files.walkFileTree(folderPathToDelete, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.deleteIfExists(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.deleteIfExists(dir);
                    return FileVisitResult.CONTINUE;
                }
            });

            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }

    /**
     * Appearance
     */

    public void setStyles(String styles, boolean isNotFromImport) {
        String cssURL;

        Account oldAccount = accountReference.copy();
        accountReference.setDisplayColor(styles);

        boolean proceed = true;

        if (isNotFromImport)
            proceed = updateAccountToAccountCSV(oldAccount, accountReference);

        if (proceed) {
            switch (styles) {
                case "light":
                    cssURL = LIGHT_ENUM.getCssURL();
                    break;
                case "dark":
                    cssURL = DARK_ENUM.getCssURL();
                    break;
                case "beige":
                    cssURL = BEIGE_ENUM.getCssURL();
                    break;
                case "blue":
                    cssURL = BLUE_ENUM.getCssURL();
                    break;
                case "cream":
                    cssURL = CREAM_ENUM.getCssURL();
                    break;
                case "green":
                    cssURL = GREEN_ENUM.getCssURL();
                    break;
                default:
                    setErrorSettingStylesUnknownColor();
                    mainController.mainModel.openPrompt();
                    cssURL = LIGHT_ENUM.getCssURL();
                    break;
            }

            cssUsing = cssURL;

            mainStage.getScene().getStylesheets().clear();

            mainStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssUsing)).toExternalForm());

            hideNotInUseLabelInAppearance(styles);
        } else {
            accountReference.setDisplayColor(oldAccount.getDisplayColor());
            setErrorSettingStyles();
            mainController.mainModel.openPrompt();
        }
    }

    private void hideNotInUseLabelInAppearance(String colorInUse) {
        switch (colorInUse) {
            case "light":
                mainController.labelAppearanceInUse1.setVisible(true);
                mainController.labelAppearanceInUse2.setVisible(false);
                mainController.labelAppearanceInUse3.setVisible(false);
                mainController.labelAppearanceInUse4.setVisible(false);
                mainController.labelAppearanceInUse5.setVisible(false);
                mainController.labelAppearanceInUse6.setVisible(false);
                break;
            case "dark":
                mainController.labelAppearanceInUse1.setVisible(false);
                mainController.labelAppearanceInUse2.setVisible(true);
                mainController.labelAppearanceInUse3.setVisible(false);
                mainController.labelAppearanceInUse4.setVisible(false);
                mainController.labelAppearanceInUse5.setVisible(false);
                mainController.labelAppearanceInUse6.setVisible(false);
                break;
            case "beige":
                mainController.labelAppearanceInUse1.setVisible(false);
                mainController.labelAppearanceInUse2.setVisible(false);
                mainController.labelAppearanceInUse3.setVisible(true);
                mainController.labelAppearanceInUse4.setVisible(false);
                mainController.labelAppearanceInUse5.setVisible(false);
                mainController.labelAppearanceInUse6.setVisible(false);
                break;
            case "blue":
                mainController.labelAppearanceInUse1.setVisible(false);
                mainController.labelAppearanceInUse2.setVisible(false);
                mainController.labelAppearanceInUse3.setVisible(false);
                mainController.labelAppearanceInUse4.setVisible(true);
                mainController.labelAppearanceInUse5.setVisible(false);
                mainController.labelAppearanceInUse6.setVisible(false);
                break;
            case "cream":
                mainController.labelAppearanceInUse1.setVisible(false);
                mainController.labelAppearanceInUse2.setVisible(false);
                mainController.labelAppearanceInUse3.setVisible(false);
                mainController.labelAppearanceInUse4.setVisible(false);
                mainController.labelAppearanceInUse5.setVisible(true);
                mainController.labelAppearanceInUse6.setVisible(false);
                break;
            case "green":
                mainController.labelAppearanceInUse1.setVisible(false);
                mainController.labelAppearanceInUse2.setVisible(false);
                mainController.labelAppearanceInUse3.setVisible(false);
                mainController.labelAppearanceInUse4.setVisible(false);
                mainController.labelAppearanceInUse5.setVisible(false);
                mainController.labelAppearanceInUse6.setVisible(true);
                break;
            default:
                mainController.labelAppearanceInUse1.setVisible(true);
                mainController.labelAppearanceInUse2.setVisible(false);
                mainController.labelAppearanceInUse3.setVisible(false);
                mainController.labelAppearanceInUse4.setVisible(false);
                mainController.labelAppearanceInUse5.setVisible(false);
                mainController.labelAppearanceInUse6.setVisible(false);
                break;
        }
    }

    public void notificationOnAction() {
        mainController.flowPaneNotification.setVisible(mainController.checkBoxSettingNotification.isSelected());
    }

    public void guideMessagesOnAction() {
        showGuideMessagesReference = mainController.checkBoxSettingGuideMessages.isSelected();
    }

    /**
     * System Manual
     */

    public void openContactDeveloper() {
        pauseVideo();

        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(CONTACT_A_DEVELOPER.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        promptStage = new Stage();

        promptStage.initModality(Modality.WINDOW_MODAL);
        promptStage.initOwner(mainController.anchorPaneMenu.getScene().getWindow());

        promptStage.setTitle(CONTACT_A_DEVELOPER.getTITLE());
        promptStage.setResizable(false);
        promptStage.getIcons().add(SYSTEM_LOGO);
        promptStage.setScene(new Scene(root));
        promptStage.showAndWait();

        mainController.mainModel.hideRectangleModal();
    }

    public void openInPDF() {
        pauseVideo();
        File systemManualPDF = new File(DIRECTORY_SYSTEM_MANUAL_PDF);

        if (!systemManualPDF.exists()) {
            setSystemManualPDFNotFound();
            mainController.mainModel.openPrompt();
            return;
        }

        try {
            Desktop.getDesktop().open(systemManualPDF);
        } catch (IOException e) {
            setSystemManualPDFErrorReading();
            mainController.mainModel.openPrompt();
            errorMessage = e.getMessage();
            logError(false);
        }
    }

    public void pauseVideo() {
        mainController.isPlayed = true;
        mainController.playMedia();
    }

    public void setVideo() {
        InputStream inputStream = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/play.png");
        Image play = new Image(Objects.requireNonNull(inputStream));
        mainController.playPauseBtn.setImage(play);
        mainController.playPauseBtn.getStyleClass().add("control-btn");

        mainController.playPauseVid.setImage(play);
        mainController.playPauseVid.setVisible(false);
        ColorAdjust colorAdjust1 = new ColorAdjust();
        colorAdjust1.setBrightness(1);
        colorAdjust1.setSaturation(0);
        mainController.playPauseVid.setEffect(colorAdjust1);
        mainController.playPauseVid.getStyleClass().add("control-vid");

        InputStream inputStream2 = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/volume.png");
        Image volume = new Image(Objects.requireNonNull(inputStream2));
        mainController.volumeBtn.setImage(volume);
        mainController.volumeBtn.setVisible(true);
        mainController.playPauseVid.getStyleClass().add("control-btn");

        InputStream inputStream1 = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/reset.png");
        Image reset = new Image(Objects.requireNonNull(inputStream1));
        mainController.resetBtn.setImage(reset);
        mainController.resetBtn.getStyleClass().add("control-btn");

        Platform.runLater(() -> {
            String resourcePath = "/com/example/postearevised/Medias/Video/System Manual.mp4";
            URL resourceUrl = getClass().getResource(resourcePath);
            if (resourceUrl != null) {
                mainController.media = new Media(resourceUrl.toString());
                mainController.mediaPlayer = new MediaPlayer(mainController.media);

                mainController.mediaView.setMediaPlayer(mainController.mediaPlayer);

                mainController.volumeSlider.setVisible(false);

                mainController.volumeBtn.setOnMouseEntered(event -> mainController.volumeBtn.setStyle("-fx-opacity: 0.4;"));
                mainController.volumeBtn.setOnMouseExited(event -> mainController.volumeBtn.setStyle("-fx-opacity: 1.0;"));

                mainController.volumeBtn.setOnMouseClicked(event -> {
                    if (mainController.volumeSlider.isVisible()) {
                        FadeTransition fadeOut = new FadeTransition(Duration.millis(800), mainController.volumeSlider);
                        fadeOut.setFromValue(1);
                        fadeOut.setToValue(0);
                        fadeOut.setOnFinished(e -> mainController.volumeSlider.setVisible(false));
                        fadeOut.play();
                    } else {
                        mainController.volumeSlider.setVisible(true);
                        mainController.volumeSlider.setOpacity(0);
                        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), mainController.volumeSlider);
                        fadeIn.setFromValue(0);
                        fadeIn.setToValue(1);
                        fadeIn.play();
                    }
                });

                mainController.volumeBtn.setOnTouchPressed(event -> {
                    if (mainController.volumeSlider.isVisible()) {
                        FadeTransition fadeOut = new FadeTransition(Duration.millis(800), mainController.volumeSlider);
                        fadeOut.setFromValue(1);
                        fadeOut.setToValue(0);
                        fadeOut.setOnFinished(e -> mainController.volumeSlider.setVisible(false));
                        fadeOut.play();
                    } else {
                        mainController.volumeSlider.setVisible(true);
                        mainController.volumeSlider.setOpacity(0);
                        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), mainController.volumeSlider);
                        fadeIn.setFromValue(0);
                        fadeIn.setToValue(1);
                        fadeIn.play();
                    }
                });

                mainController.volumeSlider.setMin(0);
                mainController.volumeSlider.setMax(100);
                mainController.volumeSlider.setValue(75);

                mainController.volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                    double volumeMedia = newValue.doubleValue() / 100.0;
                    mainController.mediaPlayer.setVolume(volumeMedia);
                });

                mainController.mediaPlayer.currentTimeProperty().addListener(((ObservableValue, oldValue, newValue) -> {
                    mainController.slider.setValue(newValue.toSeconds());

                    int currentTimeSeconds = (int) newValue.toSeconds();
                    int currentMinutes = currentTimeSeconds / 60;
                    int currentSecs = currentTimeSeconds % 60;

                    int totalDurationSeconds = (int) mainController.media.getDuration().toSeconds();
                    int totalMinutes = totalDurationSeconds / 60;
                    int totalSecs = totalDurationSeconds % 60;

                    String formattedCurrentTime = String.format("%02d:%02d", currentMinutes, currentSecs);
                    String formattedTotalDuration = String.format("%02d:%02d", totalMinutes, totalSecs);

                    mainController.timeDuration.setText( formattedCurrentTime + " / " + formattedTotalDuration);
                }));

                mainController.mediaPlayer.setOnReady(() -> {
                    Duration totalDuration = mainController.media.getDuration();
                    mainController.slider.setMax(totalDuration.toSeconds());

                    int totalSeconds = (int) Math.round(totalDuration.toSeconds());
                    int minutes = totalSeconds / 60;
                    int secs = totalSeconds % 60;

                    String formattedDuration = String.format("%02d:%02d", minutes, secs);
                    mainController.timeDuration.setText("00:00 / " + formattedDuration);
                });

                mainController.mediaPlayer.setOnEndOfMedia(() -> {
                    mainController.playPauseVid.setImage(reset);
                    mainController.videoEnded = true;
                    mainController.resetBtn.setFitWidth(28);
                    mainController.resetBtn.setFitHeight(28);
                });

                mainController.mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                    if (!mainController.mediaPlayer.getMedia().getDuration().equals(Duration.UNKNOWN) && newValue.equals(Duration.ZERO)) {
                        mainController.videoEnded = false;
                        mainController.playMedia();
                        mainController.resetBtn.setFitWidth(24);
                        mainController.resetBtn.setFitHeight(24);
                    }
                });

                mainController.slider.setOnMousePressed(event -> mainController.mediaPlayer.seek(Duration.seconds(mainController.slider.getValue())));

                mainController.slider.setOnMouseDragged(event -> mainController.mediaPlayer.seek(Duration.seconds(mainController.slider.getValue())));

                mainController.slider.setOnTouchPressed(event -> mainController.mediaPlayer.seek(Duration.seconds(mainController.slider.getValue())));

                mainController.slider.setOnTouchMoved(event -> mainController.mediaPlayer.seek(Duration.seconds(mainController.slider.getValue())));

                mainController.slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.doubleValue() != mainController.slider.getMax()) {
                        mainController.resetBtn.setFitWidth(24);
                        mainController.resetBtn.setFitHeight(24);
                        mainController.videoEnded = false;
                        InputStream inputStream3 = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/pause.png");
                        Image pause = new Image(Objects.requireNonNull(inputStream3));
                        mainController.playPauseVid.setImage(pause);
                    }
                });
            } else {
                mainController.playPauseBtn.setOpacity(0.4);
                mainController.volumeBtn.setOpacity(0.4);
                mainController.resetBtn.setOpacity(0.4);
                mainController.volumeSlider.setVisible(false);
                setSystemManualVideoNotFound();
                mainController.mainModel.openPrompt();
                mainController.anchorPaneSystemManualVideo.setDisable(true);
            }
        });

        mainController.anchorPaneSettingsSystemManualInner.getStylesheets().clear();
        mainController.anchorPaneSettingsSystemManualInner.getStylesheets().add(Objects.requireNonNull(getClass().getResource(
                "/com/example/postearevised/Styles/SystemManual/Style.css"
        )).toExternalForm());

        updateManual();
    }

    public void playPauseDuration(){
        if (mainController.timeline != null) {
            mainController.timeline.stop();
        }

        mainController.timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            mainController.playPauseVid.setVisible(false);
            mainController.timeline = null;
        }));

        mainController.timeline.play();
    }


    //TEXT MANUAL

    public void updateInstallationGuide() {
        Text guide1 = new Text("1. ");
        guide1.setStyle("-fx-font-weight: bold;");
        Text guide2 = new Text("Download the program from the website.\n");
        Text guide3 = new Text("-Users will need to download the application from ");
        Hyperlink guide3a = new Hyperlink(WEBSITE);
        guide3a.setStyle("-fx-underline: true;");
        guide3a.setOnAction(event -> {
            openWebsite();
            guide3a.setVisited(false);
        });
        Text guide3b = new Text(".\n\n");

        Text guide4 = new Text("2. ");
        guide4.setStyle("-fx-font-weight: bold;");
        Text guide5 = new Text("Extract the files.\n");
        Text guide6 = new Text("-To use the application, users must first download and extract the file. To proceed, open the extracted folder.\n\n");

        Text guide7 = new Text("3. ");
        guide7.setStyle("-fx-font-weight: bold;");
        Text guide8 = new Text("Run the JDK 21.\n");
        Text guide9 = new Text("- After the file has been extracted, a pop-up window will open. To proceed, click the 'Yes' button continuously until setup is finished.\n\n");

        Text guide10 = new Text("4. ");
        guide10.setStyle("-fx-font-weight: bold;");
        Text guide11 = new Text("Open the POS-tea Jar File. \n");
        Text guide12 = new Text("-After successfully running the JDK, user(s) can now access the system by selecting the previously downloaded POS-Tea Jar file.\n");

        mainController.guideText1.getChildren().addAll(guide1,guide2,guide3,guide3a,guide3b,guide4,guide5,guide6,guide7,guide8,guide9,guide10,guide11,guide12);
        mainController.guideText1.setStyle("-fx-font-size: 16; -fx-cursor: text; -fx-padding: 0 0 15 0;");
    }

    private void openWebsite() {
        try {
            if (checkConnectivity()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.BROWSE)) {
                    URI uri = new URI(WEBSITE);
                    desktop.browse(uri);
                } else {
                    setOpenWebsiteNotSupported();
                    mainController.mainModel.openPrompt();
                }
            } else {
                setInternetRequired();
                mainController.mainModel.openPrompt();
            }
        } catch (Exception e) {
            setUnableToOpenWebsite();
            mainController.mainModel.openPrompt();
            errorMessage = e.getMessage();
            logError(false);
        }
    }

    //DASHBOARD

    public void updateManualDashboard() {
        Text dashboard1 = new Text("Click the ");
        Text dashboard2 = new Text("“Dashboard” ");
        dashboard2.setStyle("-fx-font-weight: bold;");
        Text dashboard3 = new Text("button if you want to view the ");
        Text dashboard4 = new Text("“All Time Favorites” ");
        dashboard4.setStyle("-fx-font-weight: bold;");
        Text dashboard5 = new Text("of the shop and the statistics of the whole summary of:\n\n");

        Text dashboard6 = new Text("""
                TOTAL REVENUE
                TOTAL CUSTOMER
                TOTAL ORDER
                TOP SELLING FOOD CATEGORIES
                ALL TIME FAVORITES
                """);
        dashboard6.setStyle("-fx-font-weight: bold;");
        Text dashboard7 = new Text("\n");

        mainController.dashboardText1.getChildren().addAll(dashboard1,dashboard2,dashboard3,dashboard4,dashboard5,dashboard6,dashboard7);
        mainController.dashboardText1.setStyle("-fx-font-size: 16;");
    }

    //MENU

    public void howToMakeOrder(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Click a product.");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Customize your order by selecting appropriate ");
        Text menu22 = new Text("sizes ");
        menu22.setStyle("-fx-font-weight: bold;");
        Text menu23 = new Text("(for Milk Tea/Coolers), ");
        Text menu24 = new Text("liquid bases ");
        menu24.setStyle("-fx-font-weight: bold;");
        Text menu25 = new Text("(for Milk Tea/Coolers), ");
        Text menu26 = new Text("add-ons ");
        menu26.setStyle("-fx-font-weight: bold;");
        Text menu27 = new Text("(for Milk Tea/Coolers), ");
        Text menu28 = new Text("and temperatures ");
        menu28.setStyle("-fx-font-weight: bold;");
        Text menu29 = new Text("(Coffee).");

        Text note = new Text("\n\nNOTE: ");
        note.setStyle("-fx-font-weight: bold;");
        Text note1 = new Text(" The product(s) in the ");
        Text note2 = new Text("Ice Candy Cups ");
        note.setStyle("-fx-font-weight: bold;");
        Text note3 = new Text("and ");
        Text note4 = new Text("Appetizer Category ");
        note4.setStyle("-fx-font-weight: bold;");
        Text note5 = new Text("have no choices like the other categories mentioned in number 2.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Proceed by clicking the ");
        Text menu32 = new Text("“Add Order” ");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("button.");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Navigate to the right panel, where you will find a ");
        Text menu42 = new Text("“Bills” ");
        menu42.setStyle("-fx-font-weight: bold;");
        Text menu43 = new Text("section displaying the list of orders placed by your customers.");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("Adjust the quantity of the selected product by utilizing the ");
        Text menu52 = new Text("plus sign (+) ");
        menu52.setStyle("-fx-font-weight: bold;");
        Text menu53 = new Text("or ");
        Text menu54 = new Text("minus sign (-)");
        menu54.setStyle("-fx-font-weight: bold;");
        Text menu55 = new Text(".");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Enter customer's name and the amount paid.");

        Text num7 = new Text("\n\n7. ");
        num7.setStyle("-fx-font-weight: bold;");
        Text menu71 = new Text("Select the preferred ");
        Text menu72 = new Text("Payment Method");
        menu72.setStyle("-fx-font-weight: bold;");
        Text menu73 = new Text(".");

        Text num8 = new Text("\n\n8. ");
        num8.setStyle("-fx-font-weight: bold;");
        Text menu81 = new Text("Complete the transaction by clicking the ");
        Text menu82 = new Text("“Pay” ");
        menu82.setStyle("-fx-font-weight: bold;");
        Text menu83 = new Text("button.\n");

        mainController.menuText5.getChildren().addAll(num1,menu11,
                num2,menu21,menu22,menu23,menu24,menu25,menu26,menu27,menu28,menu29,
                note,note1,note2,note3,note4,note5,
                num3,menu31,menu32,menu33,
                num4,menu41,menu42,menu43,
                num5,menu51,menu52,menu53, menu54, menu55,
                num6,menu61,
                num7,menu71,menu72,menu73,
                num8,menu81,menu82,menu83);
        mainController.menuText5.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToUsePaymentMethod() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("On the right, there is a ");
        Text menu12 = new Text("“Bills” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("panel.");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("On the bottom there is a ");
        Text menu22 = new Text("“Payment Method”.");
        menu22.setStyle("-fx-font-weight: bold;");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Choose whether the customer prefers ");
        Text menu32 = new Text("Cash ");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("or ");
        Text menu34 = new Text("GCash ");
        menu34.setStyle("-fx-font-weight: bold;");
        Text menu35 = new Text("or ");
        Text menu36 = new Text("Others ");
        menu36.setStyle("-fx-font-weight: bold;");
        Text menu37 = new Text("as a payment method.");

        mainController.menuText7.getChildren().addAll(num1,menu11,menu12,menu13,
                num2,menu21,menu22,
                num3,menu31,menu32,menu33,menu34,menu35, menu36, menu37);
        mainController.menuText7.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void updateManualMenu() {
        howToMakeOrder();
        howToUsePaymentMethod();
    }

    //ORDER QUEUE

    public void updateManualOrderQueue(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text queue11 = new Text("Go to ");
        Text queue12 = new Text("“Menu Page” ");
        queue12.setStyle("-fx-font-weight: bold;");
        Text queue13 = new Text("and add and order.");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text queue21 = new Text("Make an order.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text queue31 = new Text("Go to ");
        Text queue32 = new Text("“Order Queue” ");
        queue32.setStyle("-fx-font-weight: bold;");
        Text queue33 = new Text("page and you will see the order you made.");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text queue41 = new Text("Click the ");
        Text queue42 = new Text("“Done” ");
        queue42.setStyle("-fx-font-weight: bold;");
        Text queue43 = new Text("button if the order is already served.");

        Text note = new Text("\n\nNOTE: ");
        note.setStyle("-fx-font-weight: bold;");
        Text note1 = new Text("The ");
        Text note2 = new Text("Order Queue ");
        note2.setStyle("-fx-font-weight: bold;");
        Text note3 = new Text("is just a guide for preparing orders. It doesn't change or add orders for customers.");

        mainController.orderQueueText1.getChildren().addAll(num1,queue11,queue12,queue13,
                num2,queue21,
                num3,queue31,queue32,queue33,
                num4,queue41,queue42,queue43,
                note,note1,note2,note3);
        mainController.orderQueueText1.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    //ORDER HISTORY

    public void updateManualOrderHistory() {
        updateManualOrderHistoryText();
        updateManualOrderHistoryHowToViewFullOrderDetails();
        updateManualOrderHistoryHowToDeleteOrderHistoryRecord();
    }

    private void updateManualOrderHistoryText() {
        Text order1 = new Text("After the order is completed, the customer's information and order details wil be moved to the Order History. In the Order History, you can view the following information:\n\n");

        Text order2 = new Text("""
                TRANSACTION ID
                CUSTOMER NAME
                TOTAL PRICE
                FOOD CATEGORIES
                PRODUCT NAME
                PRODUCT QUANTITY
                PRODUCT PRICE
                DATE AND TIME\s
                """);
        order2.setStyle("-fx-font-weight: bold;");

        mainController.orderHistoryText1.getChildren().addAll(order1,order2);
        mainController.orderHistoryText1.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    private void updateManualOrderHistoryHowToViewFullOrderDetails() {
        Text order1 = new Text("To view the full details of an order, ");
        Text order2 = new Text("double-click");
        order2.setStyle("-fx-font-weight: bold;");
        Text order3 = new Text(" a row that you want to select.\n");

        mainController.orderHistoryText2.getChildren().addAll(order1,order2,order3);
        mainController.orderHistoryText2.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    private void updateManualOrderHistoryHowToDeleteOrderHistoryRecord() {
        Text order1 = new Text("1. ");
        order1.setStyle("-fx-font-weight: bold;");
        Text order2 = new Text("To delete a history record, click the ");
        Text order3 = new Text("“Delete year” ");
        order3.setStyle("-fx-font-weight: bold;");
        Text order4 = new Text("button. This will display transaction records for each year.");

        Text order5 = new Text("\n\n2. ");
        order5.setStyle("-fx-font-weight: bold;");
        Text order6 = new Text("Select the year you wish to delete.");

        Text order7 = new Text("\n\n3. ");
        order7.setStyle("-fx-font-weight: bold;");
        Text order8 = new Text("Click the ");
        Text order9 = new Text("“Delete Record” ");
        order9.setStyle("-fx-font-weight: bold;");
        Text order10 = new Text("button.");

        Text order11 = new Text("\n\n4. ");
        order11.setStyle("-fx-font-weight: bold;");
        Text order12 = new Text("Type in your account password and click done.");

        Text order13 = new Text("\n\nNOTE: ");
        order13.setStyle("-fx-font-weight: bold;");
        Text order14 = new Text("You can only delete a record by year and you cannot delete current year records.\n");

        mainController.orderHistoryText3.getChildren().addAll(order1,order2,order3,order4,order5,order6,order7,order8,order9,order10,order11,order12,order13,order14);
        mainController.orderHistoryText3.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void updateAccountSettings(){
        Text account1 = new Text("NOTE: ");
        Text account2 = new Text("This feature is only available for the admin.");

        mainController.accountText1.getChildren().addAll(account1,account2);
        mainController.accountText1.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 15 0 15 0;");
        account1.setStyle("-fx-font-weight: bold; -fx-font-size: 25;");
        account2.setStyle("-fx-font-size: 25;");

        Text account21 = new Text("1. ");
        account21.setStyle("-fx-font-weight: bold;");
        Text account21a = new Text("Click the ");
        Text account21b = new Text("“Edit Account Details” ");
        account21b.setStyle("-fx-font-weight: bold;");
        Text account21c = new Text("button.\n\n");
        Text account22 = new Text("2. ");
        account22.setStyle("-fx-font-weight: bold;");
        Text account22a = new Text("Fill up your new contact information or your new password to new password and confirm new password\n\n");
        Text account23 = new Text("3. ");
        account23.setStyle("-fx-font-weight: bold;");
        Text account23a = new Text("After filling up your new account details, click save changes.\n\n");
        Text account24 = new Text("4. ");
        account24.setStyle("-fx-font-weight: bold;");
        Text account24a = new Text("Type in your account password and click done.");

        mainController.accountText2.getChildren().addAll(account21,account21a,account21b,account21c,account22,account22a,account23,account23a,account24,account24a);
        mainController.accountText2.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 0 0 15 0;");

        Text account31 = new Text("1. ");
        account31.setStyle("-fx-font-weight: bold;");
        Text account31a = new Text("Click the ");
        Text account31b = new Text("“Edit Security Question” ");
        account31b.setStyle("-fx-font-weight: bold;");
        Text account31c = new Text("button.\n\n");
        Text account32 = new Text("2. ");
        account32.setStyle("-fx-font-weight: bold;");
        Text account32a = new Text("Select a new recovery question or fill up your new answer in one of the question in the text field.\n\n");
        Text account33 = new Text("3. ");
        account33.setStyle("-fx-font-weight: bold;");
        Text account33a = new Text("After filling up your new account details, click the save changes.\n\n");
        Text account34 = new Text("4. ");
        account34.setStyle("-fx-font-weight: bold;");
        Text account34a = new Text("Type in your account password and click done.");

        mainController.accountText3.getChildren().addAll(account31,account31a,account31b,account31c,account32,account32a,account33,account33a,account34,account34a);
        mainController.accountText3.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 0 0 15 0;");

        Text account41 = new Text("1. ");
        account41.setStyle("-fx-font-weight: bold;");
        Text account41a = new Text("Click the ");
        Text account41b = new Text("“Edit Users” ");
        account41b.setStyle("-fx-font-weight: bold;");
        Text account41c = new Text("button.\n\n");
        Text account42 = new Text("2. ");
        account42.setStyle("-fx-font-weight: bold;");
        Text account42a = new Text("Fill up the necessary details such as given name, middle name (optional), surname, and password.\n\n");
        Text account43 = new Text("3. ");
        account43.setStyle("-fx-font-weight: bold;");
        Text account43a = new Text("After filling up your new account details, click the save changes.\n\n");
        Text account44 = new Text("4. ");
        account44.setStyle("-fx-font-weight: bold;");
        Text account44a = new Text("Type in your account password and click done.");

        mainController.accountText4.getChildren().addAll(account41,account41a,account41b,account41c,account42,account42a,account43,account43a,account44,account44a);
        mainController.accountText4.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 0 0 15 0;");

        Text account51 = new Text("1. ");
        account51.setStyle("-fx-font-weight: bold;");
        Text account51a = new Text("Click the store's photo or the username on the upper left.\n\n");
        Text account52 = new Text("2. ");
        account52.setStyle("-fx-font-weight: bold;");
        Text account52a = new Text("Select a user you want to switch to.\n\n");
        Text account53 = new Text("3. ");
        account53.setStyle("-fx-font-weight: bold;");
        Text account53a = new Text("Input user's password.");

        mainController.accountText5.getChildren().addAll(account51,account51a,account52,account52a,account53,account53a);
        mainController.accountText5.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 0 0 15 0;");

        Text account61 = new Text("1. ");
        account61.setStyle("-fx-font-weight: bold;");
        Text account61a = new Text("Click delete button and click ");
        Text account61b = new Text("“Yes” ");
        account61b.setStyle("-fx-font-weight: bold;");
        Text account61c = new Text(".\n\n");
        Text account62 = new Text("2. ");
        account62.setStyle("-fx-font-weight: bold;");
        Text account62a = new Text("Type the account password and click ");
        Text account62b = new Text("“Continue” ");
        account61b.setStyle("-fx-font-weight: bold;");
        Text account62c = new Text(".\n\n");
        Text account63 = new Text("3. ");
        account63.setStyle("-fx-font-weight: bold;");
        Text account63a = new Text("Click delete if you want to proceed deleting your account, otherwise press ");
        Text account63b = new Text("“Cancel” ");
        account61b.setStyle("-fx-font-weight: bold;");
        Text account63c = new Text(".\n\n");
        Text account64 = new Text("NOTE: ");
        account64.setStyle("-fx-font-weight: bold;");
        Text account64a = new Text("After deleting your account, all records will be permanently deleted.");

        mainController.accountText6.getChildren().addAll(account61,account61a,account61b,account61c,
                account62,account62a,account62b,account62c,
                account63,account63a,account63b,account63c,
                account64,account64a);
        mainController.accountText6.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 0 0 15 0;");
    }

    public void updateAppearanceSettings() {
        Text app1 = new Text("1. ");
        app1.setStyle("-fx-font-weight: bold;");
        Text app1a = new Text("Go to settings and click Appearance.\n\n");
        Text app2 = new Text("2. ");
        app2.setStyle("-fx-font-weight: bold;");
        Text app2a = new Text("Under display choose one of the background themes such as light, dark, beige, blue, cream, or green.");

        mainController.appearanceText1.getChildren().addAll(app1,app1a,app2,app2a);
        mainController.appearanceText1.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 0 0 15 0;");

        Text app3 = new Text("1. ");
        app3.setStyle("-fx-font-weight: bold;");
        Text app3a = new Text("Go to settings and click Appearance.\n\n");
        Text app4 = new Text("2. ");
        app4.setStyle("-fx-font-weight: bold;");
        Text app4a = new Text("Under notification and guide message click the check box if you want to hide it.");

        mainController.appearanceText2.getChildren().addAll(app3,app3a,app4,app4a);
        mainController.appearanceText2.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 0 0 15 0;");
    }

    private void updateAccountEditProduct() {
        addNoteToAccountEditProduct();
        howToAddProduct();
        howToEditTheExistingProduct();
        howToDisableAndEnableProducts();
        howToDeleteProduct();
        howToManuallyTransferLocalDatabase();
        howToExportMenu();
        howToImportMenu();
    }

    private void addNoteToAccountEditProduct() {
        Text account1 = new Text("NOTE: ");
        Text account2 = new Text("This feature is only available for the admin.");

        mainController.accountText7.getChildren().addAll(account1,account2);
        mainController.accountText7.setStyle("-fx-font-size: 16; -fx-cursor: text;-fx-padding: 15 0 15 0;");
        account1.setStyle("-fx-font-weight: bold; -fx-font-size: 25;");
        account2.setStyle("-fx-font-size: 25;");
    }


    public void howToAddProduct(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Click ");
        Text menu12 = new Text("Add Products to Get Started");
        menu12.setStyle("-fx-underline: true; -fx-fill: blue;");
        Text menu13 = new Text(". It only appears on the menu page if you don't have any products added. \nOr go to ");
        Text menu14 = new Text('"' + "Settings" + '"');
        menu14.setStyle("-fx-font-weight: bold;");
        Text menu15 = new Text(" and click ");
        Text menu16 = new Text('"' + "Edit Products" + '"');
        menu16.setStyle("-fx-font-weight: bold;");
        Text menu17 = new Text(".");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Click the ");
        Text menu22 = new Text('"' + "Add Product" + '"');
        menu22.setStyle("-fx-font-weight: bold;");
        Text menu23 = new Text(" button below.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Choose which category the product belongs to.");

        Text note = new Text("\n\nNOTE: ");
        note.setStyle("-fx-font-weight: bold;");
        Text note1 = new Text("You cannot proceed to add ");
        Text note2 = new Text("Name, Description, Price, and Photo ");
        note2.setStyle("-fx-font-weight: bold;");
        Text note3 = new Text("of the product if you don’t choose which category the product belongs to.");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Add the ");
        Text menu42 = new Text("Name, Description, Price, and Photo ");
        menu42.setStyle("-fx-font-weight: bold");
        Text menu43 = new Text("of the product.");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("Add Sizes ");
        menu51.setStyle("-fx-font-weight: bold;");
        Text menu52 = new Text("(Milk Tea/Coolers), ");
        Text menu53 = new Text("Liquid Base ");
        menu53.setStyle("-fx-font-weight: bold;");
        Text menu54 = new Text("(Milk Tea/Coolers), ");
        Text menu55 = new Text("Add-ons ");
        menu55.setStyle("-fx-font-weight: bold;");
        Text menu56 = new Text("(Milk Tea/Coolers), ");
        Text menu57 = new Text("Temperature ");
        menu57.setStyle("-fx-font-weight: bold;");
        Text menu58 = new Text("(Coffee), ");
        Text menu59 = new Text("and their ");
        Text menu510 = new Text("Price ");
        menu510.setStyle("-fx-font-weight: bold;");
        Text menu511 = new Text("(All Categories), ");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Click ");
        Text menu62 = new Text('"' + "ADD" + '"');
        menu62.setStyle("-fx-font-weight: bold;");
        Text menu63 = new Text(" button to add product.\n");

        mainController.editProductsText1.getChildren().addAll(num1,menu11,menu12,menu13,menu14,menu15,menu16,menu17,
                num2,menu21,menu22,menu23,
                num3,menu31,
                note, note1,note2,note3,
                num4,menu41,menu42,menu43,
                num5,menu51,menu52,menu53,menu54,menu55,menu56,menu57,menu58,menu59,menu510,menu511,
                num6,menu61,menu62,menu63);
        mainController.editProductsText1.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToEditTheExistingProduct() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("On the table, ");
        Text menu22 = new Text("double-click ");
        menu22.setStyle("-fx-font-weight: bold;");
        Text menu23 = new Text("the product you want to edit.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("When you are done editing the product, click the ");
        Text menu32 = new Text("“EDIT” ");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("button.\n");

        mainController.editProductsText2.getChildren().addAll(num1, menu11, menu12, menu13,menu14,
                num2,menu21,menu22,menu23,
                num3,menu31, menu32,menu33);
        mainController.editProductsText2.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToDisableAndEnableProducts() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Toggle the availability of the product by selecting or deselecting the availability checkbox.\n");

        mainController.editProductsText3.getChildren().addAll(num1,menu11,menu12,menu13,menu14,
                num2,menu21);
        mainController.editProductsText3.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToDeleteProduct(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Select the product/s you want to delete.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Click the ");
        Text menu32 = new Text("“Delete” ");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("button on the bottom left of the panel.");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Type in your account password and click done.\n");

        mainController.editProductsText4.getChildren().addAll(num1,menu11,menu12,menu13,menu14,
                num2,menu21,
                num3,menu31,menu32,menu33,
                num4, menu41);
        mainController.editProductsText4.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }


    private void windows() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        num1.setTextAlignment(TextAlignment.LEFT);
        Text menu11 = new Text("Press ");
        menu11.setTextAlignment(TextAlignment.LEFT);
        Text menu12 = new Text("(   Windows Key");
        menu12.setStyle("-fx-font-weight: bold;");
        menu12.setTextAlignment(TextAlignment.LEFT);
        ImageView windowsKey = new ImageView();
        InputStream inputStream = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/windows-key-logo.png");
        Image windowsLogo = new Image(Objects.requireNonNull(inputStream));
        windowsKey.setImage(windowsLogo);
        Text menu13 = new Text("+   R   )");
        menu13.setStyle("-fx-font-weight: bold;");
        Text menu13a = new Text(" on your keyboard.");

        Text num2 = new Text("\n\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Run ");
        menu21.setStyle("-fx-font-weight: bold;");
        Text menu22 = new Text("dialog box will open, then type ");
        Text menu23 = new Text("“%APPDATA%.");
        menu23.setStyle("-fx-font-weight: bold;");
        Text menu23a = new Text(" (without quotation marks).");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Find the folder named ");
        Text menu32 = new Text("“POS_Tea”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Copy the ");
        Text menu42 = new Text("POS_Tea ");
        menu42.setStyle("-fx-font-weight: bold;");
        Text menu43 = new Text("folder to your ");
        Text menu44 = new Text("flash drive.");
        menu44.setStyle("-fx-font-weight: bold;");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("If you are going to transfer the ");
        Text menu52 = new Text("POS_Tea ");
        menu52.setStyle("-fx-font-weight: bold;");
        Text menu53 = new Text("folder to different device, repeat the ");
        Text menu54 = new Text("step 1.");
        menu54.setStyle("-fx-font-weight: bold;");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Copy the ");
        Text menu62 = new Text("POS_Tea ");
        menu62.setStyle("-fx-font-weight: bold;");
        Text menu63 = new Text("folder to your ");
        Text menu64 = new Text("flash drive ");
        menu64.setStyle("-fx-font-weight: bold;");
        Text menu65 = new Text("then paste it.\n");

        mainController.menuHBox1.getChildren().addAll(num1,menu11,menu12,windowsKey,menu13,menu13a);
        mainController.menuHBox1.setStyle("-fx-font-size: 16; -fx-cursor: text; -fx-pref-height: 10;");
        mainController.menuHBox1.setAlignment(Pos.CENTER_LEFT);
        mainController.editProductsText10.getChildren().addAll(num2,menu21,menu22,menu23,menu23a,
                num3,menu31,menu32,
                num4,menu41,menu42,menu43,menu44,
                num5,menu51,menu52,menu53,menu54,
                num6,menu61,menu62,menu63,menu64,menu65);
        mainController.editProductsText10.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void macOS() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Press ");
        Text menu12 = new Text("(    Command + Spacebar   ).");
        menu12.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Spotlight Search ");
        menu21.setStyle("-fx-font-weight: bold;");
        Text menu22 = new Text("will open, then type ");
        Text menu23 = new Text("“~/Library/Application Support/”");
        menu23.setStyle("-fx-font-weight: bold;");
        Text menu23a = new Text(" (without quotation marks).");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Find the folder named ");
        Text menu32 = new Text("“POS_Tea”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Copy the ");
        Text menu42 = new Text("POS_Tea  ");
        menu42.setStyle("-fx-font-weight: bold;");
        Text menu43 = new Text("folder to your ");
        Text menu44 = new Text("flash drive.");
        menu44.setStyle("-fx-font-weight: bold;");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("If you are going to transfer the ");
        Text menu52 = new Text("POS_Tea ");
        menu52.setStyle("-fx-font-weight: bold;");
        Text menu53 = new Text("folder to different device, repeat the ");
        Text menu54 = new Text("step 1.");
        menu54.setStyle("-fx-font-weight: bold;");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Copy the ");
        Text menu62 = new Text("POS_Tea ");
        menu62.setStyle("-fx-font-weight: bold;");
        Text menu63 = new Text("folder to your ");
        Text menu64 = new Text("flash drive ");
        menu64.setStyle("-fx-font-weight: bold;");
        Text menu65 = new Text("the then paste it.\n");

        mainController.editProductsText11.getChildren().addAll(num1,menu11,menu12,
                num2,menu21,menu22,menu23,menu23a,
                num3,menu31,menu32,
                num4,menu41,menu42,menu43,menu44,
                num5,menu51,menu52,menu53,menu54,
                num6,menu61,menu62,menu63,menu64,menu65);
        mainController.editProductsText11.setStyle("-fx-font-size: 16; -fx-cursor: text; -fx-pref-height: 10;");
    }

    public void linux() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Press ");
        Text menu12 = new Text("(    Alt + F2   ).");
        menu12.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Run Application ");
        menu21.setStyle("-fx-font-weight: bold;");
        Text menu22 = new Text("will open, then type ");
        Text menu23 = new Text("“~/.config/”");
        menu23.setStyle("-fx-font-weight: bold;");
        Text menu23a = new Text(" (without quotation marks).");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Find the folder named ");
        Text menu32 = new Text("“POS_Tea”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Copy the ");
        Text menu42 = new Text("POS_Tea ");
        menu42.setStyle("-fx-font-weight: bold;");
        Text menu43 = new Text("folder to your ");
        Text menu44 = new Text("flash drive.");
        menu44.setStyle("-fx-font-weight: bold;");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("If you are going to transfer the ");
        Text menu52 = new Text("POS_Tea ");
        menu52.setStyle("-fx-font-weight: bold;");
        Text menu53 = new Text("folder to different device, repeat the ");
        Text menu54 = new Text("step 1.");
        menu54.setStyle("-fx-font-weight: bold;");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Copy the ");
        Text menu62 = new Text("POS_Tea ");
        menu62.setStyle("-fx-font-weight: bold;");
        Text menu63 = new Text("folder to your ");
        Text menu64 = new Text("flash drive ");
        menu64.setStyle("-fx-font-weight: bold;");
        Text menu65 = new Text("the then paste it.\n");

        mainController.editProductsText12.getChildren().addAll(num1,menu11,menu12,
                num2,menu21,menu22,menu23,menu23a,
                num3,menu31,menu32,
                num4,menu41,menu42,menu43,menu44,
                num5,menu51,menu52,menu53,menu54,
                num6,menu61,menu62,menu63,menu64,menu65);
        mainController.editProductsText12.setStyle("-fx-font-size: 16; -fx-cursor: text; -fx-pref-height: 10;");
    }

    public void howToManuallyTransferLocalDatabase() {
        windows();
        macOS();
        linux();
    }

    public void howToExportMenu() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("On the left panel, there is a drop bar.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Click the drop bar and select ");
        Text menu32 = new Text("“Export Menu”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("File Explorer ");
        menu41.setStyle("-fx-font-weight: bold;");
        Text menu42 = new Text("will pop-up and save it to your desired file path.");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("Save the file.\n");

        mainController.editProductsText8.getChildren().addAll(num1,menu11,menu12,menu13,menu14,
                num2,menu21,
                num3,menu31,menu32,
                num4,menu41,menu42,
                num5, menu51);
        mainController.editProductsText8.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToImportMenu(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("On the left panel, there is a drop bar.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Click the drop bar and select ");
        Text menu32 = new Text("“Import Menu”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("File Explorer ");
        menu41.setStyle("-fx-font-weight: bold;");
        Text menu42 = new Text("wil pop-up.");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("Find the file and select the file to import.");

        Text note = new Text("\n\nNOTE: ");
        note.setStyle("-fx-font-weight: bold;");
        Text note1 = new Text("The file must be an ");
        Text note2 = new Text("Excel File.\n");
        note2.setStyle("-fx-font-weight: bold;");

        mainController.editProductsText9.getChildren().addAll(num1,menu11,menu12,menu13,menu14,
                num2,menu21,
                num3,menu31,menu32,
                num4,menu41,menu42,
                num5, menu51,
                note,note1,note2);
        mainController.editProductsText9.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void updateSettings() {
        updateAccountSettings();
        updateAppearanceSettings();
        updateAccountEditProduct();
    }

    //UPDATE MANUAL

    public void updateManual(){
        updateInstallationGuide();
        updateManualDashboard();
        updateManualMenu();
        updateManualOrderQueue();
        updateManualOrderHistory();
        updateSettings();
    }
}