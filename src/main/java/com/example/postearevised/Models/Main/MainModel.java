package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Enums.MainPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.NotificationContents.*;
import static com.example.postearevised.Miscellaneous.Others.Resolution.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;

public class MainModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
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
        mainController.mainMenuIcon.setImage(mainMenuSelectedIcon);
    }

    public void setAnchorPane() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                openSelectedPane(MENU_ENUM.getPaneNumber());
            }
        });
    }

    public void openSelectedPane(int selectedPane) {
        boolean logout = false;

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
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(true);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                mainController.dashboardModel.updateContents();

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
                mainController.settingsModel.populateComboBoxImportExport();

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

        if (logout) {
            setConfirmLogout();
            if (openPrompt()) {
                logout();
            }
        }
    }

    public void showRectangleModal() {
        mainController.rectangleModal.setVisible(true);
    }

    public void hideRectangleModal() {
        mainController.rectangleModal.setVisible(false);
    }


    public void showNotification() {
        mainController.imageViewNotification.setImage(imageViewNotificationReference);
        mainController.labelNotificationHeader.setText(notificationHeaderReference);
        mainController.labelNotificationContent.setText(notificationContentReference);

        mainController.anchorPaneNotification.setVisible(false);
        mainController.anchorPaneNotification.setVisible(true);
        mainController.anchorPaneNotification.setOpacity(0);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(.2), mainController.anchorPaneNotification);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        Timeline timeline = new Timeline();
        KeyFrame key = new KeyFrame(Duration.seconds(3), event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), mainController.anchorPaneNotification);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(fadeFinishedEvent -> {
                mainController.anchorPaneNotification.setVisible(false);
            });
            fadeOut.play();
        });
        timeline.getKeyFrames().add(key);
        fadeIn.play();
        timeline.play();
    }

    public void showNotificationLoader(boolean isShow) {
        mainController.anchorPaneNotification.setVisible(true);
        mainController.anchorPaneNotification.setOpacity(isShow ? 0.0 : 1.0); // Set opacity based on isShow

        FadeTransition fade = new FadeTransition(Duration.seconds(.2), mainController.anchorPaneNotification);
        fade.setFromValue(isShow ? 0.0 : 1.0);
        fade.setToValue(isShow ? 1.0 : 0.0);
        if (!isShow) {
            fade.setOnFinished(event -> mainController.anchorPaneNotification.setVisible(false));
        }

        fade.play();
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
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneMenu.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        hideRectangleModal();
        return isConfirmed;
    }

    private void logout() {
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
    }


    private void closeThisStage() {
        Stage stage = (Stage) mainController.anchorPaneMenu.getScene().getWindow();
        stage.close();
    }
}
