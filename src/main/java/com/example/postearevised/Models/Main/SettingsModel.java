package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Additional.ProductController;
import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Miscellaneous.Enums.ProductEnum;
import com.example.postearevised.Miscellaneous.References.ProductOrderReference;
import com.example.postearevised.Objects.Account.Account;
import com.example.postearevised.Objects.Products.*;
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

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.postearevised.Miscellaneous.Database.CSV.Accounts.AccountCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.CSVUtility.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Enums.AskForPasswordHeaderTitlesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ImportExportEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.NotificationContents.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.LoginForgotRegisterReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
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
            } else if (selected.equals(IMPORT_ENUM.getImportOperation())) {
                switch (chooseFilePath(mainStage, true)) {
                    // -1 - some product already exists, 0 - do nothing, 1 - successful, 2 - invalid file format, 3 - other unexpected errors, open notepad contains error message
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
                        logError(false);
                        break;
                }
            } else if (selected.equals(EXPORT_ENUM.getImportOperation())){
                // 4 - export successful
                if (chooseFilePath(mainStage, false) == 4) {
                    mainController.mainModel.openPrompt();
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
            mainController.mainModel.showNotification();

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
            mainController.mainModel.showNotification();

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
                            mainController.mainModel.showNotification();

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
    public void comboBoxNameOnAction() {
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
    }

    public void deleteSelectedName() {
        
    }

    private void addAUser() {
        mainController.comboBoxAccountName.setValue(addUser);

        mainController.textFieldAccountGivenName.setText("");
        mainController.textFieldAccountMiddleName.setText("");
        mainController.textFieldAccountLastName.setText("");

        mainController.textFieldAccountGivenName.setPromptText("Enter First Name");
        mainController.textFieldAccountMiddleName.setPromptText("Enter Middle Name");
        mainController.textFieldAccountLastName.setPromptText("Enter Surname");
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
        mainController.labelProfileName.setText(getFirstWord(accountReference.getFirstNames().get(index)));
    }

    public void setSettingsAccountStyle() {
        mainController.comboBoxAccountName.setStyle(settingsAccountNameComboBoxStyle);
        mainController.comboBoxSettingsQuestionOne.setStyle(settingsAccountQuestionsComboBoxStyle);
        mainController.comboBoxSettingsQuestionTwo.setStyle(settingsAccountQuestionsComboBoxStyle);
    }

    public void setSettingsAccount() {
        setSettingsAccountPane1(false);
        setSettingsAccountPane2(false);
        setSettingsAccountPane3(false);
        mainController.anchorPaneSettingsBtnDeleteUser.setVisible(false);
    }

    public void setSettingsAccountPane1(boolean isShow) {
        boolean proceed = checkPane1Changes();

        if (proceed) {
            mainController.imagePencilSettingsAccount1.setVisible(isShow);
            mainController.imagePencilSettingsAccount2.setVisible(isShow);
            mainController.imagePencilSettingsAccount3.setVisible(isShow);
            mainController.imagePencilSettingsAccount4.setVisible(isShow);

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
        }
    }

    private boolean checkPane1Changes() {
        if (mainController.detectChangesUsers) {
            Account oldAccount = accountReference.copy();

            if (saveChanges(1)) {
                accountEditingProceed = false;

                getChanges();

                if (updateAccountToAccountCSV(oldAccount, accountReference)) {
                    mainController.mainModel.populateFullNamesObservableList();
                    setNameToNewlyAddedName();
                    return true;
                } else {
                    setErrorFailedToUpdateAccountToCSV();
                    mainController.mainModel.openPrompt();
                    return false;
                }
            } else {
                if (!maxAttemptLimitReached) {
                    setErrorFailedToUpdateAccountUserCancelled();
                    mainController.mainModel.openPrompt();
                    maxAttemptLimitReached = false;
                }
                int index = revertToOldValuesPane1(oldAccount);
                mainController.mainModel.populateFullNamesObservableList();
                setComboBoxNameToOldValue(index);
                return true;
            }
        } else {
            return true;
        }
    }

    private int revertToOldValuesPane1(Account oldAccount) {
        String selectedName = getFirstWord(mainController.comboBoxAccountName.getValue());
        int index = -1;

        for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
            String firstName = getFirstWord(accountReference.getFirstNames().get(i));
            if (selectedName.equals(firstName))
                index = i;
        }

        mainController.textFieldAccountGivenName.setText(oldAccount.getFirstNames().get(index));
        mainController.textFieldAccountMiddleName.setText(oldAccount.getMiddleNames().get(index));
        mainController.textFieldAccountLastName.setText(oldAccount.getLastNames().get(index));

        return index;
    }

    private void setComboBoxNameToOldValue(int index) {
        mainController.comboBoxAccountName.setValue(fullNames.get(index));
    }

    private void getChanges() {
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
    }

    public void setSettingsAccountPane2(boolean isShow) {
        mainController.imagePencilSettingsAccount5.setVisible(isShow);
        mainController.imagePencilSettingsAccount6.setVisible(isShow);
        mainController.imagePencilSettingsAccount7.setVisible(isShow);

        mainController.textFieldAccountContact.setDisable(!isShow);
        mainController.textFieldAccountNewPassword.setDisable(!isShow);
        mainController.textFieldAccountConfirmNewPassword.setDisable(!isShow);

        mainController.imageHideShowNewPasswordAccountSettings.setVisible(isShow);
        mainController.imageHideShowConfirmNewPasswordAccountSettings.setVisible(isShow);

        if (!isShow)
            mainController.labelSettingsAccountEditFinishAccountDetails.setText("EDIT ACCOUNT DETAILS");
        else
            mainController.labelSettingsAccountEditFinishAccountDetails.setText("FINISH EDITING");
    }

    public void setSettingsAccountPane3(boolean isShow) {
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
    }

    /**
     * Appearance
     */

    public void notificationOnAction() {
        showNotificationsReference = mainController.checkBoxSettingNotification.isSelected();
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