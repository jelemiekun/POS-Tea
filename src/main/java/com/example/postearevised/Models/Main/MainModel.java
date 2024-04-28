package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.example.postearevised.Miscellaneous.Database.CSV.Accounts.AccountCSV.*;
import static com.example.postearevised.Miscellaneous.Enums.AskForPasswordHeaderTitlesEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.MainPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.SettingsReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.*;
import static com.example.postearevised.Miscellaneous.Others.NotificationContents.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.Others.Resolution.setScreenResolution;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.DateTimeFormatterReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.StylesReference.*;

public class MainModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setAccountDetails() {
        mainController.textFieldAccountContact.setText(accountReference.getContact());

        mainController.comboBoxSettingsQuestionOne.setValue(accountReference.getSecurityQuestionOne());
        mainController.textFieldAccountQuestionOne.setText(accountReference.getSecurityQuestionOneAnswer());

        mainController.comboBoxSettingsQuestionTwo.setValue(accountReference.getSecurityQuestionTwo());
        mainController.textFieldAccountQuestionTwo.setText(accountReference.getSecurityQuestionTwoAnswer());

        mainController.checkBoxSettingNotification.setSelected(accountReference.isShowNotification());
        mainController.checkBoxSettingGuideMessages.setSelected(accountReference.isShowGuideMessages());
    }

    public void checkIfNewAccount() {
        if (accountReference.getUserPasswords() == null) {
            inputPasswordForThisUser();
        } else {
            selectUser();
        }

        if (mainController.loggedIn) {
            if (mainController.comboBoxAccountName.getValue() != null)
                mainController.settingsModel.setSettingsAccount();
        }
    }

    private void inputPasswordForThisUser() {
        accountEditingProceed = false;
        isInputPasswordExistingUser = false;
        registerAdminPassword = true;
        do {
            mainController.mainModel.showRectangleModal();
            headerTitle = USERS_SELECTION_ENUM.getHeaderTitle();
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

            askForPasswordStage.setTitle("Input Password");
            askForPasswordStage.setResizable(false);
            askForPasswordStage.getIcons().add(SYSTEM_LOGO);
            askForPasswordStage.setScene(new Scene(root));
            askForPasswordStage.showAndWait();
            mainController.mainModel.hideRectangleModal();

        } while (!accountEditingProceed);

        doneFirstUserRegistration();
    }

    private void doneFirstUserRegistration() {
        populateFullNamesObservableList();
        mainController.labelProfileName.setText(usersNames.get(userIndex));
        mainController.leftPanelCounter++;
        mainController.comboBoxLeftPanelUsers.setValue(usersNames.get(userIndex));
        mainController.comboBoxAccountName.setValue(fullNames.get(0));
        mainController.labelSettingsOnlyAdmin.setVisible(false);
        mainController.labelSettingsOnlyAdmin1.setVisible(false);

        setWelcomeAdmin();
        mainController.mainModel.generateNotification();

        startTour();
    }

    public void startTour() {
        System.out.println("Tour");
    }

    private void selectUser() {
        do {
            mainController.selectUserCounter++;
            mainController.mainModel.showRectangleModal();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(SELECT_USER_ENUM.getURL()));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                errorMessage = e.getMessage();
                logError(false);
            }
            selectUserStage = new Stage();

            selectUserStage.initModality(Modality.WINDOW_MODAL);
            selectUserStage.initOwner(mainController.anchorPaneMenu.getScene().getWindow());

            selectUserStage.setTitle(SELECT_USER_ENUM.getTITLE());
            selectUserStage.setResizable(false);
            selectUserStage.getIcons().add(SYSTEM_LOGO);
            selectUserStage.setScene(new Scene(root));
            selectUserStage.showAndWait();
            mainController.mainModel.hideRectangleModal();

            if (!userSelectedSuccess && mainController.selectUserCounter % MAXIMUM_ATTEMPTS_FOR_CRITICAL_INPUTS == 0) {
                if (logOutFromUserSelect()) {
                    userSelectedSuccess = true;
                    mainController.loggedIn = false;
                }
            }
        } while (!userSelectedSuccess);

        if (mainController.loggedIn) {
            populateFullNamesObservableList();
            mainController.labelProfileName.setText(usersNames.get(userIndex));
            mainController.comboBoxAccountName.setValue(fullNames.get(userIndex));

            setAccountAndProductAnchorPaneIfAdmin();
            mainController.leftPanelCounter++;

            setWelcomeUser();
            mainController.mainModel.generateNotification();

            userSelectedSuccess = false;
            mainController.loggedIn = true;
        } else {
            userSelectedSuccess = false;
            mainController.loggedIn = true;
            logout();
        }
    }

    private boolean logOutFromUserSelect() {
        setLogOutFromUserSelect();
        return openPrompt();
    }

    public void setAccountAndProductAnchorPaneIfAdmin() {
        String userName = mainController.comboBoxAccountName.getValue().trim();

        mainController.labelSettingsOnlyAdmin.setVisible(!userName.contains("Admin"));
        mainController.labelSettingsOnlyAdmin1.setVisible(!userName.contains("Admin"));
        mainController.anchorPaneSettingsAccountInner.setDisable(!userName.contains("Admin"));
        mainController.anchorPaneSettingsEditProductsInner.setDisable(!userName.contains("Admin"));
        mainController.importExportComboBox.setDisable(!userName.contains("Admin"));
        mainController.btnOrderHistoryDelete.setVisible(userName.contains("Admin"));
        Platform.runLater(() -> mainController.comboBoxLeftPanelUsers.setValue(usersNames.get(userIndex)));
    }

    public void populateFullNamesObservableList() {
        fullNames.clear();

        for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
            String firstName = accountReference.getFirstNames().get(i);
            String middleName = accountReference.getMiddleNames().get(i).isEmpty() || accountReference.getMiddleNames().get(i).equals(".") ? "" : accountReference.getMiddleNames().get(i);
            String lastName = accountReference.getLastNames().get(i);

            String isDefault = i == 0 ? " (Admin)" : "";
            fullNames.add(firstName + " " + middleName + " " + lastName + isDefault);

            mainController.comboBoxAccountName.setItems(fullNames);
        }



        usersNames.clear();

        for (int i = 0; i < accountReference.getFirstNames().size(); i++) {
            String firstPart = getFirstWord(accountReference.getFirstNames().get(i));
            String secondPart = getFirstCharacter(accountReference.getLastNames().get(i));

            usersNames.add(firstPart + " " + secondPart + ".");

            mainController.comboBoxLeftPanelUsers.setItems(usersNames);
        }
    }

    public String getFirstWord(String input) {
        String[] words = input.split("\\s+");
        return words[0];
    }
    public static String getFirstCharacter(String input) {
        if (input != null && !input.isEmpty()) {
            return input.substring(0, 1);
        } else {
            return "";
        }
    }


    public void setDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(7);
        dropShadow.setOffsetY(0);
        dropShadow.setColor(dropShadowColor);

        mainController.anchorPaneLeftPanel.setEffect(dropShadow);
    }

    public void setMainMenuIconSelected() {
        mainController.mainDashboardIcon.setImage(mainDashboardSelectedIcon);
    }

    public void setAnchorPane() {
        Platform.runLater(() -> openSelectedPane(DASHBOARD_ENUM.getPaneNumber()));
    }

    public void createAndStartDaemonThreadForDateAndTime() {
        Thread daemonThreadForDateAndTime = new Thread(() -> {
            while (true) {
                localDateTime = LocalDateTime.now();
                String formattedDateTime = localDateTime.format(formatter);

                Platform.runLater(() -> {
                    mainController.labelDashboardDateAndTIme.setText(formattedDateTime);
                    mainController.labelMenuDateAndTIme.setText(formattedDateTime);
                    mainController.labelOrderQueueDateAndTIme.setText(formattedDateTime);
                    mainController.labelOrderHistoryDateAndTIme.setText(formattedDateTime);
                });
                try {
                    Thread.sleep(ONE_SECOND);
                } catch (InterruptedException e) {
                    errorMessage = e.getMessage();
                    logError(false);
                }
            }
        });

        daemonThreadForDateAndTime.setDaemon(true);

        daemonThreadForDateAndTime.start();
    }

    public void openSelectedPane(int selectedPane) {
        boolean logout = false;

        mainController.orderListModel.updateOrderQueueLabelsAndPane();

        switch (selectedPane) {
            case 1: // Menu
                mainStage.setTitle(MENU_ENUM.getName());
                mainController.anchorPaneMenu.setVisible(true);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                mainController.anchorPaneMenu.requestFocus();
                mainController.menuModel.checkIfIsMenuEmpty();
                mainController.menuModel.setHalfRightPanel();

                mainController.mainMenuIcon.setImage(mainMenuSelectedIcon);
                mainController.mainDashboardIcon.setImage(mainDashboardIcon);
                mainController.mainOrderListIcon.setImage(mainOrderListIcon);
                mainController.mainOrderHistoryIcon.setImage(mainOrderHistoryIcon);
                mainController.mainSettingsIcon.setImage(mainSettingsIcon);
                mainController.mainLogoutIcon.setImage(mainLogoutIcon);
                break;
            case 2: // Dashboard
                mainStage.setTitle(DASHBOARD_ENUM.getName());
                mainController.anchorPaneDashboard.requestFocus();
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(true);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                mainController.dashboardModel.updateAllTimeFavorites();
                if (!isAddingProductsFromImport)
                    mainController.dashboardModel.firstChoiceBoxOnAction();

                mainController.mainMenuIcon.setImage(mainMenuIcon);
                mainController.mainDashboardIcon.setImage(mainDashboardSelectedIcon);
                mainController.mainOrderListIcon.setImage(mainOrderListIcon);
                mainController.mainOrderHistoryIcon.setImage(mainOrderHistoryIcon);
                mainController.mainSettingsIcon.setImage(mainSettingsIcon);
                mainController.mainLogoutIcon.setImage(mainLogoutIcon);
                break;
            case 3: // Order List
                mainStage.setTitle(ORDER_LIST_ENUM.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(true);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                mainController.orderListModel.updateOrderQueueLabelsAndPane();


                mainController.mainMenuIcon.setImage(mainMenuIcon);
                mainController.mainDashboardIcon.setImage(mainDashboardIcon);
                mainController.mainOrderListIcon.setImage(mainOrderListSelectedIcon);
                mainController.mainOrderHistoryIcon.setImage(mainOrderHistoryIcon);
                mainController.mainSettingsIcon.setImage(mainSettingsIcon);
                mainController.mainLogoutIcon.setImage(mainLogoutIcon);
                break;
            case 4: // Order History
                mainStage.setTitle(ORDER_HISTORY_ENUM.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(true);
                mainController.anchorPaneSettings.setVisible(false);
                //mainController.orderHistoryModel.setOrderHistory();

                mainController.mainMenuIcon.setImage(mainMenuIcon);
                mainController.mainDashboardIcon.setImage(mainDashboardIcon);
                mainController.mainOrderListIcon.setImage(mainOrderListIcon);
                mainController.mainOrderHistoryIcon.setImage(mainOrderHistorySelectedIcon);
                mainController.mainSettingsIcon.setImage(mainSettingsIcon);
                mainController.mainLogoutIcon.setImage(mainLogoutIcon);
                break;
            case 5: // Settings
                mainStage.setTitle(SETTINGS_ENUM.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneSettings.setVisible(true);
                mainController.settingsModel.setDropShadow();
                mainController.settingsModel.setPane();

                mainController.mainMenuIcon.setImage(mainMenuIcon);
                mainController.mainDashboardIcon.setImage(mainDashboardIcon);
                mainController.mainOrderListIcon.setImage(mainOrderListIcon);
                mainController.mainOrderHistoryIcon.setImage(mainOrderHistoryIcon);
                mainController.mainSettingsIcon.setImage(mainSettingsSelectedIcon);
                mainController.mainLogoutIcon.setImage(mainLogoutIcon);
                break;
            default:
                logout = true;
                break;
        }

        mainController.isPlayed = true;
        mainController.playMedia();

        if (logout) {
            setConfirmLogout();
            if (openPrompt()) {
                logout();
            }
        }

        setGuideMessages(selectedPane);
    }

    private void setGuideMessages(int selectedPane) {
        switch (selectedPane) {
            case 1: // Menu
                mainController.menuGuideMessageBillsProductOrder.setVisible(mainController.checkBoxSettingGuideMessages.isSelected());
                break;
            case 2: // Dashboard
                mainController.anchorDashboardPaneNeedHelpGettingStarted.setVisible(mainController.checkBoxSettingGuideMessages.isSelected());
                break;
            case 3: // Order List
                mainController.orderQueueGuideMessageClickTable.setVisible(mainController.checkBoxSettingGuideMessages.isSelected());
                break;
            case 4: // Order History
                mainController.orderHistoryGuideMessageDoubleClickTable.setVisible(mainController.checkBoxSettingGuideMessages.isSelected());
                break;
                // settings ay nasa settingsModel openSelectedPane() na
        }
    }

    public void showRectangleModal() {
        mainController.rectangleModal.setVisible(true);
    }

    public void hideRectangleModal() {
        mainController.rectangleModal.setVisible(false);
    }


    public void generateNotification() {
        AnchorPane anchorPaneNotification = new AnchorPane();
        anchorPaneNotification.setPrefSize(550, 120);

        Rectangle overlay = new Rectangle(550, 120);
        overlay.setArcHeight(1500);
        overlay.setArcWidth(5);
        overlay.setFill(Color.rgb(0, 0, 0, 0.85));

        ImageView imageViewNotification = new ImageView(imageViewNotificationReference);
        imageViewNotification.setFitWidth(70);
        imageViewNotification.setFitHeight(70);
        imageViewNotification.setLayoutX(25);
        imageViewNotification.setLayoutY(25);

        AnchorPane contentPane = new AnchorPane();
        contentPane.setLayoutX(120);
        contentPane.setPrefSize(430, 120);

        Label labelNotificationHeader = new Label(notificationHeaderReference);
        labelNotificationHeader.setLayoutY(18);
        labelNotificationHeader.setTextFill(Color.WHITE);
        labelNotificationHeader.setStyle("-fx-font-size: 28;");

        Label labelNotificationContent = getLabelNotificationContent();

        contentPane.getChildren().addAll(labelNotificationHeader, labelNotificationContent);

        anchorPaneNotification.getChildren().addAll(overlay, imageViewNotification, contentPane);

        mainController.flowPaneNotification.getChildren().add(anchorPaneNotification);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), anchorPaneNotification);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), anchorPaneNotification);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        fadeIn.setOnFinished(event -> new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        fadeOut.play();
                    }
                },
                2500
        ));

        fadeOut.setOnFinished(event -> mainController.flowPaneNotification.getChildren().remove(anchorPaneNotification));

        fadeIn.play();
    }

    private static Label getLabelNotificationContent() {
        Label labelNotificationContent = new Label(notificationContentReference);
        labelNotificationContent.setLayoutX(1);
        labelNotificationContent.setLayoutY(46);
        labelNotificationContent.setTextFill(Color.WHITE);
        labelNotificationContent.setWrapText(true);
        labelNotificationContent.setPrefWidth(420);
        labelNotificationContent.setPrefHeight(70);
        labelNotificationContent.setContentDisplay(ContentDisplay.LEFT);
        labelNotificationContent.setTextAlignment(TextAlignment.LEFT);
        labelNotificationContent.setAlignment(Pos.CENTER_LEFT);
        labelNotificationContent.setStyle("-fx-font-size: 18;");
        return labelNotificationContent;
    }

    public void showScreenToSmall() {

    }


    public void setToolTips() {
        // dashboard
        Tooltip dashboardNoSalesRecordedGraphAndAllTimeFavorites = new Tooltip("No Sales Recorded");
        Tooltip dashboardNeedHelpGettingStartedToolTip = new Tooltip("Go to system manual for guide");
        Tooltip dashboardFirstChoiceBox = new Tooltip("Select intervals");
        Tooltip dashboardSecondChoiceBox = new Tooltip("Select year");
        Tooltip dashboardThirdChoiceBox = new Tooltip("Select month");
        Tooltip dashboardFourthChoiceBox = new Tooltip("Select day");
        Tooltip dashboardResetToToday = new Tooltip("Reset the selection to current day");

        dashboardNoSalesRecordedGraphAndAllTimeFavorites.setStyle(toolTipStyle);
        dashboardNeedHelpGettingStartedToolTip.setStyle(toolTipStyle);
        dashboardFirstChoiceBox.setStyle(toolTipStyle);
        dashboardSecondChoiceBox.setStyle(toolTipStyle);
        dashboardThirdChoiceBox.setStyle(toolTipStyle);
        dashboardFourthChoiceBox.setStyle(toolTipStyle);
        dashboardResetToToday.setStyle(toolTipStyle);

        Tooltip.install(mainController.labelDashBoardNoSalesRecordedGraph, dashboardNoSalesRecordedGraphAndAllTimeFavorites);
        Tooltip.install(mainController.labelDashBoardNoSalesBestSeller, dashboardNoSalesRecordedGraphAndAllTimeFavorites);
        Tooltip.install(mainController.anchorDashboardPaneNeedHelpGettingStarted, dashboardNeedHelpGettingStartedToolTip);
        Tooltip.install(mainController.dashboardComboBoxFirstSelection, dashboardFirstChoiceBox);
        Tooltip.install(mainController.dashboardComboBoxSecondSelection, dashboardSecondChoiceBox);
        Tooltip.install(mainController.dashboardComboBoxThirdSelection, dashboardThirdChoiceBox);
        Tooltip.install(mainController.dashboardComboBoxFourthSelection, dashboardFourthChoiceBox);
        Tooltip.install(mainController.anchorPaneResetToToday, dashboardResetToToday);

        // menu
        Tooltip menuGoToEditProducts = new Tooltip("Go to edit products");

        menuGoToEditProducts.setStyle(toolTipStyle);

        Tooltip.install(mainController.anchorPaneMenuGoToEditProducts, menuGoToEditProducts);
        Tooltip.install(mainController.anchorPaneMenuIsEmptyInner, menuGoToEditProducts);

        // order queue
        Tooltip orderQueueNoOrders = new Tooltip("No order");

        orderQueueNoOrders.setStyle(toolTipStyle);

        Tooltip.install(mainController.orderQueueAnchorPaneNoOrder, orderQueueNoOrders);

        // order history
        Tooltip orderHistoryComboBoxFilterBy = new Tooltip("Filter search");

        orderHistoryComboBoxFilterBy.setStyle(toolTipStyle);

        Tooltip.install(mainController.comboBoxOrderHistory, orderHistoryComboBoxFilterBy);

        // settings
        Tooltip settingsEditProductImportExportMenu = new Tooltip("Import/Export Menu");
        Tooltip settingsAppearanceLight = new Tooltip("Switch color light");
        Tooltip settingsAppearanceDark = new Tooltip("Switch color dark");
        Tooltip settingsAppearanceRed = new Tooltip("Switch color red");
        Tooltip settingsAppearanceOrange = new Tooltip("Switch color orange");
        Tooltip settingsAppearanceYellow = new Tooltip("Switch color yellow");
        Tooltip settingsAppearanceGreen = new Tooltip("Switch color green");

        settingsEditProductImportExportMenu.setStyle(toolTipStyle);
        settingsAppearanceLight.setStyle(toolTipStyle);
        settingsAppearanceDark.setStyle(toolTipStyle);
        settingsAppearanceRed.setStyle(toolTipStyle);
        settingsAppearanceOrange.setStyle(toolTipStyle);
        settingsAppearanceYellow.setStyle(toolTipStyle);
        settingsAppearanceGreen.setStyle(toolTipStyle);

        Tooltip.install(mainController.importExportComboBox, settingsEditProductImportExportMenu);
        Tooltip.install(mainController.settingsAppearanceRectangleLight, settingsAppearanceLight);
        Tooltip.install(mainController.settingsAppearanceRectangleDark, settingsAppearanceDark);
        Tooltip.install(mainController.settingsAppearanceRectangleRed, settingsAppearanceRed);
        Tooltip.install(mainController.settingsAppearanceRectangleOrange, settingsAppearanceOrange);
        Tooltip.install(mainController.settingsAppearanceRectangleYellow, settingsAppearanceYellow);
        Tooltip.install(mainController.settingsAppearanceRectangleGreen, settingsAppearanceGreen);

        Tooltip.install(mainController.imageHideShowNewPasswordAccountSettings, showPasswordToolTip);
        Tooltip.install(mainController.imageHideShowConfirmNewPasswordAccountSettings, showPasswordToolTip);

        // left panel
        Tooltip leftPanelProfileChangeUser = new Tooltip("Change user");

        leftPanelProfileChangeUser.setStyle(toolTipStyle);

        Tooltip.install(mainController.anchorPaneLeftProfile, leftPanelProfileChangeUser);
    }

    public void setComboBoxUsers() {
        mainController.comboBoxLeftPanelUsers.setStyle("-fx-combo-box-popup-button-visible: false;" + dashboardComboBoxStyle);
    }

    public void setTermsAndConditionsLastUpdated() {
        mainController.labelLastUpdated.setText("Last updated " + TERMS_AND_CONDITIONS_LAST_UPDATE);
    }

    public void toggleComboBoxUsers() {
        if (mainController.comboBoxLeftPanelUsers.isShowing())
            mainController.comboBoxLeftPanelUsers.hide();
        else
            mainController.comboBoxLeftPanelUsers.show();
    }

    public void comboBoxLeftPanelUsersOnAction() {
        String firstName = "";
        int index = -1;

        if (mainController.comboBoxLeftPanelUsers.getValue() != null)
            firstName = mainController.settingsModel.getFirstWord(mainController.comboBoxLeftPanelUsers.getValue());

        for (int i = 0; i < usersNames.size(); i++) {
            if (usersNames.get(i).startsWith(firstName))
                index = i;
        }

        if (index != -1) {
            userIndexCopy = userIndex;
            userIndex = index;
            accountEditingProceed = false;
            if (mainController.settingsModel.setLeftPanelProfileNameConfirmPassword()) {
                mainController.settingsModel.setLeftPanelProfileName(userIndex);
                setSwitchUserSuccess();
                mainController.mainModel.generateNotification();
            } else {
                setSwitchUserFailed();
                mainController.mainModel.generateNotification();
                userIndex = userIndexCopy;
                mainController.leftPanelCounter++;
                mainController.settingsModel.setLeftPanelProfileName(userIndex);
                //mainController.comboBoxLeftPanelUsers.setValue(usersNames.get(userIndex));
            }
            mainController.leftPanelCounter++;
            setAccountAndProductAnchorPaneIfAdmin();
        }
    }

    public boolean openPrompt() {
        showRectangleModal();
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
        promptStage.showAndWait();
        hideRectangleModal();
        return isConfirmed;
    }

    public void logOutAccountDeleted() {
        logout();
    }

    private void logout() {
        deleteStayLoggedInData();

        mainController.loader = new FXMLLoader(getClass().getResource(LOGIN_ENUM.getURL()));
        try {
            mainController.root = mainController.loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        mainController.newStage = new Stage();
        loginFromMainSceneStage = mainController.newStage;
        mainController.newStage.setTitle(LOGIN_ENUM.getTITLE());
        mainController.newStage.setResizable(false);
        mainController.newStage.setScene(new Scene(mainController.root));
        setScreenResolution(false, true);
        mainController.newStage.getIcons().add(SYSTEM_LOGO);
        mainController.newStage.show();
        closeThisStage();
        System.gc();
    }


    private void deleteStayLoggedInData() {
        loggedOutSoDeleteStayLoggedInDetails();
    }


    private void closeThisStage() {
        Stage stage = (Stage) mainController.anchorPaneMenu.getScene().getWindow();
        stage.close();
    }
}
