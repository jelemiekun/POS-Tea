package com.example.postearevised.Models;

import com.example.postearevised.Controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.MainPane.*;
import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Others.*;
import static com.example.postearevised.Miscellaneous.Prompt.*;
import static com.example.postearevised.Miscellaneous.Reference.*;

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

    public void openSelectedPane(int selectedPane) throws IOException {
        boolean logout = false;

        switch (selectedPane) {
            case 1:
                mainStage.setTitle(Menu.getName());
                mainController.anchorPaneMenu.setVisible(true);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneAboutUs.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                break;
            case 2:
                mainStage.setTitle(Dashboard.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(true);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneAboutUs.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                break;
            case 3:
                mainStage.setTitle(OrderList.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(true);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneAboutUs.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                break;
            case 4:
                mainStage.setTitle(OrderHistory.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(true);
                mainController.anchorPaneAboutUs.setVisible(false);
                mainController.anchorPaneSettings.setVisible(false);
                break;
            case 5:
                mainStage.setTitle(AboutUs.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneAboutUs.setVisible(true);
                mainController.anchorPaneSettings.setVisible(false);
                break;
            case 6:
                mainStage.setTitle(Settings.getName());
                mainController.anchorPaneMenu.setVisible(false);
                mainController.anchorPaneDashboard.setVisible(false);
                mainController.anchorPaneOrderList.setVisible(false);
                mainController.anchorPaneOrderHistory.setVisible(false);
                mainController.anchorPaneAboutUs.setVisible(false);
                mainController.anchorPaneSettings.setVisible(true);
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
        newStage.initOwner(mainController.anchorPaneAboutUs.getScene().getWindow());

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
        mainController.newStage.show();
        closeThisStage();
    }


    private void closeThisStage() {
        Stage stage = (Stage) mainController.iconAnchorPaneAboutUs.getScene().getWindow();
        stage.close();
    }
}
