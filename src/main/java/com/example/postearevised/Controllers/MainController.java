package com.example.postearevised.Controllers;

import com.example.postearevised.Models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.MainPane.*;

public class MainController implements Initializable {
    /**
     * Initialize
     */
    public MainModel mainModel;
    public MenuModel menuModel;
    public DashboardModel dashboardModel;
    public OrderListModel orderListModel;
    public OrderHistoryModel orderHistoryModel;
    public AboutUsModel aboutUsModel;
    public SettingsModel settingsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainModel = new MainModel();
        mainModel.setMainController(this);
        mainModel.setDropShadow();

        menuModel = new MenuModel();
        menuModel.setMainController(this);
        menuModel.setDropShadow();

        dashboardModel = new DashboardModel();
        dashboardModel.setMainController(this);

        orderListModel = new OrderListModel();
        orderListModel.setMainController(this);

        orderHistoryModel = new OrderHistoryModel();
        orderHistoryModel.setMainController(this);

        aboutUsModel = new AboutUsModel();
        aboutUsModel.setMainController(this);

        settingsModel = new SettingsModel();
        settingsModel.setMainController(this);
    }

    /**
     * Main
     */

    public FXMLLoader loader;
    public Parent root;
    public Stage newStage;
    @FXML
    public AnchorPane anchorPaneDashboard;

    @FXML
    public AnchorPane anchorPaneMenu;

    @FXML
    public AnchorPane anchorPaneOrderHistory;

    @FXML
    public AnchorPane anchorPaneOrderList;

    @FXML
    public AnchorPane anchorPaneSettings;
    @FXML
    public AnchorPane anchorPaneAboutUs;

    @FXML
    public AnchorPane iconAnchorPaneAboutUs;

    @FXML
    public AnchorPane iconAnchorPaneDashboard;

    @FXML
    public AnchorPane iconAnchorPaneLogout;

    @FXML
    public AnchorPane iconAnchorPaneMenu;

    @FXML
    public AnchorPane iconAnchorPaneOrderHistory;

    @FXML
    public AnchorPane iconAnchorPaneOrderList;

    @FXML
    public AnchorPane iconAnchorPaneSettings;

    @FXML
    public AnchorPane anchorPaneLeftPanel;

    @FXML
    void anchorPaneMenuClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(Menu.getPaneNumber());
    }

    @FXML
    void anchorPaneMenuTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(Menu.getPaneNumber());
    }

    @FXML
    void anchorPaneDashBoardClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(Dashboard.getPaneNumber());
    }

    @FXML
    void anchorPaneDashboardTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(Dashboard.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderListClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(OrderList.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderListTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(OrderList.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderHistoryClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(OrderHistory.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderHistoryTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(OrderHistory.getPaneNumber());
    }

    @FXML
    void anchorPaneAboutUsClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(AboutUs.getPaneNumber());
    }

    @FXML
    void anchorPaneAboutUsTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(AboutUs.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(Settings.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(Settings.getPaneNumber());
    }

    @FXML
    void anchorPaneLogoutClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(Logout.getPaneNumber());
    }

    @FXML
    void anchorPaneLogoutTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(Logout.getPaneNumber());
    }

    /**
     * Menu
     */

    @FXML
    public AnchorPane anchorPaneRightPanel;
}