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
import static com.example.postearevised.Miscellaneous.InternetAndResolution.*;
import static com.example.postearevised.Miscellaneous.PromptContents.*;
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
                    openSelectedPane(Menu.getPaneNumber());
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
                mainStage.setTitle(Menu.getName());
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
                mainController.menuModel.checkIfIsMenuEmpty();
                break;
            case 2: // Dashboard
                mainStage.setTitle(Dashboard.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(true);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);

                mainController.mainMenuIcon.setImage(mainMenuIcon);
                mainController.mainDashboardIcon.setImage(mainDashboardSelectedIcon);
                mainController.mainOrderListIcon.setImage(mainOrderListIcon);
                mainController.mainOrderHistoryIcon.setImage(mainOrderHistoryIcon);
                mainController.mainSettingsIcon.setImage(mainSettingsIcon);
                mainController.mainLogoutIcon.setImage(mainLogoutIcon);
                break;
            case 3: // Order List
                mainStage.setTitle(OrderList.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(true);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);

                mainController.mainMenuIcon.setImage(mainMenuIcon);
                mainController.mainDashboardIcon.setImage(mainDashboardIcon);
                mainController.mainOrderListIcon.setImage(mainOrderListSelectedIcon);
                mainController.mainOrderHistoryIcon.setImage(mainOrderHistoryIcon);
                mainController.mainSettingsIcon.setImage(mainSettingsIcon);
                mainController.mainLogoutIcon.setImage(mainLogoutIcon);
                break;
            case 4: // Order History
                mainStage.setTitle(OrderHistory.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(true);
                mainController.anchorPaneSettings.setVisible(false);

                mainController.mainMenuIcon.setImage(mainMenuIcon);
                mainController.mainDashboardIcon.setImage(mainDashboardIcon);
                mainController.mainOrderListIcon.setImage(mainOrderListIcon);
                mainController.mainOrderHistoryIcon.setImage(mainOrderHistorySelectedIcon);
                mainController.mainSettingsIcon.setImage(mainSettingsIcon);
                mainController.mainLogoutIcon.setImage(mainLogoutIcon);
                break;
            case 5: // Settings
                mainStage.setTitle(Settings.getName());
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

        if (logout) {
            if (confirmLogout())
                logout();
        }
    }


    private boolean confirmLogout() throws IOException {
        setConfirmLogout();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ExitConfirmation.getURL()));
        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneMenu.getScene().getWindow());

        newStage.setTitle(ExitConfirmation.getTITLE());
        newStage.setResizable(false);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }

    private void logout() throws IOException {
        mainController.loader = new FXMLLoader(getClass().getResource(Login.getURL()));
        mainController.root = mainController.loader.load();
        mainController.newStage = new Stage();
        loginFromMainSceneStage = mainController.newStage;
        mainController.newStage.setTitle(Login.getTITLE());
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
