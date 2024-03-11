package com.example.postearevised.Controllers.Main;

import com.example.postearevised.Models.Main.*;
import com.example.postearevised.Objects.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.MainPane.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPane.*;

public class MainController implements Initializable {
    /**
     * Initialize
     */
    public MainModel mainModel;
    public MenuModel menuModel;
    public DashboardModel dashboardModel;
    public OrderListModel orderListModel;
    public OrderHistoryModel orderHistoryModel;
    public SettingsModel settingsModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainModel = new MainModel();
        mainModel.setMainController(this);
        mainModel.setDropShadow();
        mainModel.setMainMenuIconSelected();
        mainModel.setAnchorPane();

        menuModel = new MenuModel();
        menuModel.setMainController(this);
        menuModel.setDropShadow();

        dashboardModel = new DashboardModel();
        dashboardModel.setMainController(this);

        orderListModel = new OrderListModel();
        orderListModel.setMainController(this);

        orderHistoryModel = new OrderHistoryModel();
        orderHistoryModel.setMainController(this);

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
    public ImageView mainMenuIcon;

    @FXML
    public ImageView mainDashboardIcon;

    @FXML
    public ImageView mainOrderListIcon;

    @FXML
    public ImageView mainOrderHistoryIcon;

    @FXML
    public ImageView mainSettingsIcon;

    @FXML
    public ImageView mainLogoutIcon;

    @FXML
    void anchorPaneMenuClicked() throws IOException {
        mainModel.openSelectedPane(Menu.getPaneNumber());
    }

    @FXML
    void anchorPaneMenuTouched() throws IOException {
        mainModel.openSelectedPane(Menu.getPaneNumber());
    }

    @FXML
    void anchorPaneDashBoardClicked() throws IOException {
        mainModel.openSelectedPane(Dashboard.getPaneNumber());
    }

    @FXML
    void anchorPaneDashboardTouched() throws IOException {
        mainModel.openSelectedPane(Dashboard.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderListClicked() throws IOException {
        mainModel.openSelectedPane(OrderList.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderListTouched() throws IOException {
        mainModel.openSelectedPane(OrderList.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderHistoryClicked() throws IOException {
        mainModel.openSelectedPane(OrderHistory.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderHistoryTouched() throws IOException {
        mainModel.openSelectedPane(OrderHistory.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsClicked() throws IOException {
        mainModel.openSelectedPane(Settings.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsTouched() throws IOException {
        mainModel.openSelectedPane(Settings.getPaneNumber());
    }

    @FXML
    void anchorPaneLogoutClicked() throws IOException {
        mainModel.openSelectedPane(Logout.getPaneNumber());
    }

    @FXML
    void anchorPaneLogoutTouched() throws IOException {
        mainModel.openSelectedPane(Logout.getPaneNumber());
    }

    /**
     * Menu
     */

    @FXML
    public AnchorPane anchorPaneRightPanel;

    /**
     * Settings
     */

    @FXML
    public AnchorPane anchorPaneSettingsDropShadow;
    @FXML
    public AnchorPane anchorPaneSettingsAccount;
    @FXML
    public AnchorPane anchorPaneSettingsDisplay;
    @FXML
    public AnchorPane anchorPaneSettingsEditProducts;
    @FXML
    public AnchorPane anchorPaneSettingsTAC;
    @FXML
    public AnchorPane anchorPaneSettingsSystemManual;
    @FXML
    public AnchorPane anchorPaneSettingsAccountLeftPanel;
    @FXML
    public AnchorPane anchorPaneSettingsDisplayLeftPanel;
    @FXML
    public AnchorPane anchorPaneSettingsEditProductsLeftPanel;
    @FXML
    public AnchorPane anchorPaneSettingsTACLeftPanel;
    @FXML
    public AnchorPane anchorPaneSettingsSystemManualLeftPanel;
    @FXML
    public Rectangle rectangleAccount;
    @FXML
    public Rectangle rectangleDisplay;
    @FXML
    public Rectangle rectangleEditProducts;
    @FXML
    public Rectangle rectangleTAC;
    @FXML
    public Rectangle rectangleSystemManual;
    @FXML
    public AnchorPane anchorPaneAccountTopPanel;
    @FXML
    public AnchorPane anchorPaneDisplayTopPanel;
    @FXML
    public AnchorPane anchorPaneProductsTopPanel;
    @FXML
    public AnchorPane anchorPaneTACTopPanel;
    @FXML
    public AnchorPane anchorPaneSystemManualTopPanel;

    @FXML
    void anchorPaneSettingsAccountClicked() {
        settingsModel.openSelectedPane(Account.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsAccountTouched() {
        settingsModel.openSelectedPane(Account.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsDisplayClicked() {
        settingsModel.openSelectedPane(Display.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsDisplayTouched() {
        settingsModel.openSelectedPane(Display.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsTACClicked() {
        settingsModel.openSelectedPane(TAC.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsTACTouched() {
        settingsModel.openSelectedPane(TAC.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsEditProductsClicked() {
        settingsModel.openSelectedPane(EditProducts.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsEditProductsTouched() {
        settingsModel.openSelectedPane(EditProducts.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsSystemManualClicked() {
        settingsModel.openSelectedPane(SystemManual.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsSystemManualTouched() {
        settingsModel.openSelectedPane(SystemManual.getPaneNumber());
    }

    /**
     * Settings - Products
     */

    @FXML
    public TableView<Product> tableProducts;
    @FXML
    public TableColumn<Product, ImageView> tableProductsColImage;
    @FXML
    public TableColumn<Product, String> tableProductsColProductName;
    @FXML
    public TableColumn<Product, String> tableProductsColCategory;
    @FXML
    public TableColumn<Product, CheckBox> tableProductsColAvailable;

    @FXML
    public void settingsAddProductClicked() throws IOException{
        settingsModel.openAddProductsFXML();
    }

    @FXML
    public void settingsAddProductTouched() throws IOException {
        settingsModel.openAddProductsFXML();
    }
}