package com.example.postearevised.Models;

import com.example.postearevised.Controllers.MainController;
import com.example.postearevised.Miscellaneous.Enums.Scenes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Enums.Pane.*;
import static com.example.postearevised.Miscellaneous.Enums.Scenes.Login;
import static com.example.postearevised.Miscellaneous.Reference.mainStage;

public class MainModel {
    private MainController mainController;
    public void setDashboardController(MainController mainController) {
        this.mainController = mainController;
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
            mainController.loader = new FXMLLoader(getClass().getResource(Login.getURL()));
            mainController.root = mainController.loader.load();
            mainController.newStage = new Stage();
            mainController.newStage.setTitle(Login.getTITLE());
            mainController.newStage.setScene(new Scene(mainController.root));
            mainController.newStage.show();
            closeThisStage();
        }
    }



    private void closeThisStage() {
        Stage stage = (Stage) mainController.iconAnchorPaneAboutUs.getScene().getWindow();
        stage.close();
    }
}
