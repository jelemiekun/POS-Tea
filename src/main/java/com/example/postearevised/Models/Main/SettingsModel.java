package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.Enums.ProductEnum;
import com.example.postearevised.Miscellaneous.References.ProductOrderReference;
import com.example.postearevised.Objects.Account.Account;
import com.example.postearevised.Objects.Products.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.postearevised.Miscellaneous.Database.CSV.Accounts.AccountCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.CSVUtility.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.AskForPasswordHeaderTitlesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.DisplayColorsEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ImportExportEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.NotificationContents.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.DIRECTORY_PATH_ACCOUNTS;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.LoginForgotRegisterReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;
import static com.example.postearevised.Miscellaneous.References.SettingsReference.*;
import static com.example.postearevised.Miscellaneous.References.StylesReference.*;

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
        Stage newStage = new Stage();
        newStage.setTitle(ProductEnum.PRODUCT_ENUM.getTitle());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        productStage = newStage;

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setAddProduct();

        newStage.showAndWait();

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
            removePhotoFromResources();
            clearProductReferenceValues();
        }
        mainController.mainModel.hideRectangleModal();
    }

    private void removePhotoFromResources() {
        if (!referenceImagePath.isBlank() && !referenceImagePath.isEmpty()) {
            Path photoPath = Path.of(referenceImagePath);
            try {
                // Attempt to delete the photo
                Files.delete(photoPath);
                System.out.println("Photo deleted successfully!");
            } catch (IOException e) {
                errorMessage = e.getMessage();
                logError(false);
                System.out.println("Failed to delete the photo: " + e.getMessage());
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
        Stage newStage = new Stage();
        newStage.setTitle(ProductEnum.PRODUCT_ENUM.getTitle());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        productStage = newStage;

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        ProductController productController = loader.getController();
        productController.productModel.setEditProduct();

        newStage.showAndWait();

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
     * Delete
     */

    public void deleteSelectedProductsProcess() {
        ObservableList<Product> selectedItemsToDelete = mainController.tableProducts.getSelectionModel().getSelectedItems();

        if (selectedItemsToDelete != null && !selectedItemsToDelete.isEmpty()) {
            setDeleteProduct();
            if (mainController.mainModel.openPrompt()) {
                if (deleteProductInCSV(selectedItemsToDelete)) {
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
                } else {
                    setErrorDeleteProduct();
                    mainController.mainModel.openPrompt();
                }
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
        switch (notToDisable) {
            case 1:
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(false);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(true);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(true);
                mainController.anchorPaneSettingsBtnDelete.setDisable(true);
                break;
            case 2:
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(true);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(false);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(true);
                mainController.anchorPaneSettingsBtnDelete.setDisable(true);
                break;
            case 3:
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(true);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(true);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(false);
                mainController.anchorPaneSettingsBtnDelete.setDisable(true);
                break;
            case 4:
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(true);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(true);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(true);
                mainController.anchorPaneSettingsBtnDelete.setDisable(false);
                break;
            case 5:
                mainController.anchorPaneSettingsBtnEditUsers.setDisable(false);
                mainController.anchorPaneSettingsBtnEditAccountDetails.setDisable(false);
                mainController.anchorPaneSettingsBtnEditSecurityQuestions.setDisable(false);
                mainController.anchorPaneSettingsBtnDelete.setDisable(false);
                break;
        }
        mainController.anchorPaneSettingsAccount.requestFocus();
    }

    /**
     * Account - USERS
     */
    public void comboBoxNameOnAction() {
        setOthersSettingsAccountPane();
        if (mainController.comboBoxAccountName.getValue() != null) {
            if (!mainController.textFieldAccountGivenName.isDisabled())
                mainController.anchorPaneSettingsBtnDeleteUser.setVisible(!mainController.comboBoxAccountName.getValue().endsWith("(Default)"));

            if (!mainController.comboBoxAccountName.getValue().equals(addUser)) {
                String selectedName = getFirstWord(mainController.comboBoxAccountName.getValue());
                int index = -1;

                for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
                    String firstName = getFirstWord(accountReference.getFirstNames().get(i));
                    if (selectedName.equals(firstName))
                        index = i;
                }

                if (index != -1) {
                    setLeftPanelProfileName(index);
                    setFirstMiddleLastNameTextFields(index);
                }
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

        boolean proceed = checkPane1Changes(mainController.comboBoxAccountName.getValue().equals(addUser));

        if (proceed && !mainController.textFieldAccountGivenName.isDisabled()) {
            disableOtherAccountEditButtons(5);
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

            if (isShow)
                fullNames.add(addUser);
            else
                fullNames.remove(addUser);

            if (!mainController.comboBoxAccountName.getValue().contains("(Default)"))
                mainController.anchorPaneSettingsBtnDeleteUser.setVisible(isShow);

            if (!isShow)
                mainController.labelSettingsAccountEditFinishUsers.setText("EDIT USERS");
            else
                mainController.labelSettingsAccountEditFinishUsers.setText("FINISH EDITING");

            mainController.detectChangesUsers = false;

            mainController.anchorPaneSettingsAccount.requestFocus();
        }
    }

    public void deleteSelectedName() {
        int index = getComboBoxNameIndex();

        accountReference.getFirstNames().remove(index);
        accountReference.getMiddleNames().remove(index);
        accountReference.getLastNames().remove(index);

        mainController.mainModel.populateFullNamesObservableList();
        setComboBoxToDefault();

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
    }

    private String getFirstWord(String value) {
        String[] words = value.split("\\s+");
        return words[0];
    }

    private void setFirstMiddleLastNameTextFields(int index) {
        mainController.textFieldAccountGivenName.setText(accountReference.getFirstNames().get(index));
        mainController.textFieldAccountMiddleName.setText(accountReference.getMiddleNames().get(index));
        mainController.textFieldAccountLastName.setText(accountReference.getLastNames().get(index));
    }

    private void setLeftPanelProfileName(int index) {
        mainController.labelProfileName.setText(usersNames.get(index));
    }

    public void setSettingsAccountStyle() {
        mainController.comboBoxAccountName.setStyle(settingsAccountNameComboBoxStyle);
        mainController.comboBoxSettingsQuestionOne.setStyle(settingsAccountQuestionsComboBoxStyle);
        mainController.comboBoxSettingsQuestionTwo.setStyle(settingsAccountQuestionsComboBoxStyle);
    }

    private boolean checkPane1Changes(boolean isAdd) {
        if (mainController.detectChangesUsers) {
            if (!usersRequiredFieldsNotBlank()) {
                return false;
            } else {

                if (saveChanges(1)) {
                    accountEditingProceed = false;

                    getUsersChanges();

                    if (updateAccountToAccountCSV(oldAccountReference, accountReference)) {
                        if (isAdd) {
                            mainController.mainModel.populateFullNamesObservableList();
                            setNameToNewlyAddedName();
                        } else {
                            int index = getComboBoxNameIndex();
                            mainController.mainModel.populateFullNamesObservableList();
                            setNameToEditedName(index);
                        }
                        setAccountUsersSuccessful();
                        mainController.mainModel.generateNotification();
                        disableOtherAccountEditButtons(5);
                        return true;
                    } else {
                        setErrorFailedToUpdateAccountToCSV();
                        mainController.mainModel.openPrompt();
                        setAccountUsersUpdateFailed();
                        mainController.mainModel.generateNotification();
                        revertBackUsers();
                        return false;
                    }
                } else {
                    if (!maxAttemptLimitReached) {
                        setErrorFailedToUpdateAccountUserCancelled();
                        mainController.mainModel.openPrompt();
                        maxAttemptLimitReached = false;
                    }
                    setAccountUsersUpdateFailed();
                    mainController.mainModel.generateNotification();
                    revertBackUsers();
                    return true;
                }
            }
        } else {
            return true;
        }
    }

    private void revertBackUsers() {
        setComboBoxToDefault();
        setNameToEditedName(getComboBoxNameIndex());
        int index = revertToOldValuesPane1();
        mainController.mainModel.populateFullNamesObservableList();
        setComboBoxNameToOldValue(index);
        disableOtherAccountEditButtons(5);
    }

    private void setComboBoxToDefault() {
        mainController.comboBoxAccountName.setValue(fullNames.get(0));
        mainController.comboBoxLeftPanelUsers.setValue(usersNames.get(0));
    }

    private boolean usersRequiredFieldsNotBlank() {
        return !mainController.textFieldAccountGivenName.getText().trim().isEmpty() && !mainController.textFieldAccountLastName.getText().trim().isEmpty() && nameNotExist();
    }

    private boolean nameNotExist() {
        String fullName = mainController.textFieldAccountGivenName.getText().trim() + " " + mainController.textFieldAccountMiddleName.getText().trim() + " " + mainController.textFieldAccountLastName.getText().trim();

        for (String existingName : fullNames) {
            if (existingName.contains(" (Default)"))
                existingName = existingName.replace(" (Default)", "").trim();

            System.out.println("line 790 " + fullName + ", " + existingName);
            if (existingName.equalsIgnoreCase(fullName))
                return false;
        }
        return true;
    }

    public void accountUsersTyping() {
        String givenName = mainController.textFieldAccountGivenName.getText().trim();
        String lastName = mainController.textFieldAccountLastName.getText().trim();

        mainController.labelSettingsFillUpThisForm1.setVisible(givenName.isEmpty());
        mainController.labelSettingsFillUpThisForm2.setVisible(lastName.isEmpty());

        mainController.labelSettingsFillUpThisForm9.setVisible(!nameNotExist());
    }

    private int revertToOldValuesPane1() {
        accountReference.getFirstNames().setAll(oldAccountReference.getFirstNames());
        accountReference.getMiddleNames().setAll(oldAccountReference.getMiddleNames());
        accountReference.getLastNames().setAll(oldAccountReference.getLastNames());

        String selectedName = getFirstWord(mainController.comboBoxAccountName.getValue());
        int index = -1;

        for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
            String firstName = getFirstWord(accountReference.getFirstNames().get(i));
            if (selectedName.equals(firstName))
                index = i;
        }

        mainController.textFieldAccountGivenName.setText(oldAccountReference.getFirstNames().get(index));
        mainController.textFieldAccountMiddleName.setText(oldAccountReference.getMiddleNames().get(index));
        mainController.textFieldAccountLastName.setText(oldAccountReference.getLastNames().get(index));

        return index;
    }

    private void setComboBoxNameToOldValue(int index) {
        mainController.comboBoxAccountName.setValue(fullNames.get(index));
        mainController.comboBoxLeftPanelUsers.setValue(usersNames.get(index));
    }

    private void getUsersChanges() {
        String textFieldFirstName = mainController.textFieldAccountGivenName.getText().trim();
        String textFieldMiddleName = mainController.textFieldAccountMiddleName.getText().trim().isEmpty() ? "" : mainController.textFieldAccountMiddleName.getText().trim();
        String textFieldLastName = mainController.textFieldAccountLastName.getText().trim();

        if (mainController.comboBoxAccountName.getValue().equals(addUser)) {
            accountReference.getFirstNames().add(textFieldFirstName);
            accountReference.getMiddleNames().add(textFieldMiddleName);
            accountReference.getLastNames().add(textFieldLastName);
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
        mainController.comboBoxLeftPanelUsers.setValue(usersNames.get(usersNames.size() - 1));
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

    private void setNameToEditedName(int index) {
        mainController.comboBoxAccountName.setValue(fullNames.get(index));
        mainController.comboBoxLeftPanelUsers.setValue(usersNames.get(index));
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

        if (mainController.labelSettingsAccountEditFinishAccountDetails.getText().equals("FINISH EDITING"))
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
                mainController.labelSettingsAccountEditFinishAccountDetails.setText("FINISH EDITING");

            mainController.detectChangesAccountDetails = false;
            mainController.accountDetailsSubmittedOnce = false;
        }
    }

    private boolean checkPane2Changes() {
        if (mainController.detectChangesAccountDetails) {
            if (!accountDetailsRequiredFieldsNotBlank()) {
                return false;
            } else {
                if (saveChanges(2)) {
                    accountEditingProceed = false;

                    getAccountDetailsChanges();

                    if (updateAccountToAccountCSV(oldAccountReference, accountReference)) {
                        dataTasks();
                        clearFieldsPasswordTexts();
                        setAccountDetailsEditSuccessful();
                        mainController.mainModel.generateNotification();
                        disableOtherAccountEditButtons(5);
                        mainController.accountDetailsSubmittedOnce = false;
                        Platform.runLater(() -> {
                            dataTasks();
                            clearFieldsPasswordTexts();
                            setAccountDetailsEditSuccessful();
                            mainController.mainModel.generateNotification();
                            disableOtherAccountEditButtons(5);
                            mainController.accountDetailsSubmittedOnce = false;
                        });
                        return true;
                    } else {
                        setErrorFailedToUpdateAccountToCSV();
                        mainController.mainModel.openPrompt();
                        setAccountDetailsEditFailed();
                        mainController.mainModel.generateNotification();
                        clearFieldsPasswordTexts();
                        revertToOldValuesPane2();
                        return false;
                    }
                } else {
                    if (!maxAttemptLimitReached) {
                        setErrorFailedToUpdateAccountUserCancelled();
                        mainController.mainModel.openPrompt();
                        clearFieldsPasswordTexts();
                        revertToOldValuesPane2();
                        maxAttemptLimitReached = false;
                    }
                    setAccountDetailsEditFailed();
                    mainController.mainModel.generateNotification();
                    clearFieldsPasswordTexts();
                    revertToOldValuesPane2();
                    return true;
                }
            }
        } else {
            return true;
        }
    }

    private boolean accountDetailsRequiredFieldsNotBlank() {
        boolean proceed = true;

        String account = mainController.textFieldAccountContact.getText().trim();
        String newPassword = mainController.showNewPassword ? mainController.textFieldAccountNewPassword.getText().trim() : mainController.passwordFieldAccountNewPassword.getText().trim();
        String confirmNewPassword = mainController.showConfirmNewPassword ? mainController.textFieldAccountConfirmNewPassword.getText().trim() : mainController.passwordFieldAccountConfirmNewPassword.getText().trim();

        boolean willChangeAccount = !oldAccountReference.getContact().equals(account);
        boolean willChangePass = !newPassword.isEmpty() || !confirmNewPassword.isEmpty();

        if (willChangePass && willChangeAccount) {
            proceed = newPassword.equals(confirmNewPassword) && (account.matches(REGEX_PHONE_NUMBER_ELEVEN_DIGIT) || account.matches(REGEX_EMAIL));
        } else if (willChangeAccount) {
            proceed = account.matches(REGEX_PHONE_NUMBER_ELEVEN_DIGIT) || account.matches(REGEX_EMAIL);
        } else if (willChangePass) {
            proceed = newPassword.equals(confirmNewPassword);
        }

        setVisibilitiesPane2();
        System.out.println("line 994 " + proceed + ", " + newAccountNotExists() + ", " + newPasswordNotTheSameAsOldOne());
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
            }

            if (!willChangePass) {
                mainController.labelSettingsFillUpThisForm6.setVisible(false);
                mainController.labelSettingsFillUpThisForm7.setVisible(false);
                mainController.labelSettingsFillUpThisForm8.setVisible(false);
                mainController.labelSettingsFillUpThisForm11.setVisible(false);
            }

            if (!willChangeAccount) {
                mainController.labelSettingsFillUpThisForm3.setVisible(false);
                mainController.labelSettingsFillUpThisForm10.setVisible(false);
            }

            if (!willChangeAccount && !willChangePass) {
                mainController.detectChangesAccountDetails = false;
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
                mainController.labelSettingsAccountEditFinishSecurityQuestions.setText("FINISH EDITING");

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

        System.out.println("line 1132 " + changedQuestionOne + ", " + changedQuestionTwo + ", " + changedAnswerOne + ", " + changedAnswerTwo + ", " + firstAnswerNotBlank + ", " + secondAnswerNotBlank);
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

    private boolean saveChanges(int operationNumber) { // 1 - users, 2 - account, 3 - recovery questions, 4 account deletion
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
        }
        return openAskForPasswordFXML();
    }

    private boolean openAskForPasswordFXML() {
        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ASK_FOR_PASSWORD.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneMenu.getScene().getWindow());

        newStage.setTitle(ASK_FOR_PASSWORD.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        mainController.mainModel.hideRectangleModal();
        return accountEditingProceed;
    }

    public void deleteAccountProcess() {
        disableOtherAccountEditButtons(4);
        setDeleteAccount1();
        if (mainController.mainModel.openPrompt()) {
            if (saveChanges(4)) {
                setDeleteAccount2();
                if (mainController.mainModel.openPrompt()) {
                    if (deleteAccountFromCSV(accountReference)) {
                        setDeleteAccount3AccountDeleted();
                        mainController.mainModel.openPrompt();
                        mainController.mainModel.logOutAccountDeleted(); // LOGOUT
                        accountSet.remove(accountReference);
                    } else {
                        setErrorAccountDeletion();
                        mainController.mainModel.openPrompt();
                    }
                }
            }
        }

        disableOtherAccountEditButtons(5);
    }

    /**
     * Appearance
     */

    public void setStyles(String styles, boolean isNotFromImport) {
        String cssURL = "";

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
                case "red":
                    cssURL = RED_ENUM.getCssURL();
                    break;
                case "orange":
                    cssURL = ORANGE_ENUM.getCssURL();
                    break;
                case "yellow":
                    cssURL = YELLOW_ENUM.getCssURL();
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

            mainStage.getScene().getStylesheets().clear();

            mainStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(cssURL)).toExternalForm());
            System.out.println("abang ng css line 1287 : " + styles);
        } else {
            accountReference.setDisplayColor(oldAccount.getDisplayColor());
            setErrorSettingStyles();
            mainController.mainModel.openPrompt();
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