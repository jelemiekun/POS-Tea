package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.MainPane.*;
import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Others.InternetAndResolution.*;
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
                try {
                    openSelectedPane(MENU_ENUM.getPaneNumber());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void openSelectedPane(int selectedPane) throws IOException {
        boolean logout = false;

        switch (selectedPane) {
            case 1: // Menu
                mainStage.setTitle(MENU_ENUM.getName());
                mainController.menuModel.checkIfIsMenuEmpty();
                mainController.anchorPaneMenu.setVisible(true);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                mainController.anchorPaneMenu.requestFocus();

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
                mainController.orderHistoryModel.setOrderHistory();

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
            if (openPrompt()) {
                setConfirmLogout();
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


    public boolean openPrompt() throws IOException {
        showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = loader.load();
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

    private void logout() throws IOException {
        mainController.loader = new FXMLLoader(getClass().getResource(LOGIN_ENUM.getURL()));
        mainController.root = mainController.loader.load();
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
