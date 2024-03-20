package com.example.postearevised.Controllers.Main;

import com.example.postearevised.Models.Main.*;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Products.Product;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.MainPane.*;
import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPane.*;
import static com.example.postearevised.Miscellaneous.References.DashboardReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductOrderReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

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
        menuModel.setTextFieldListeners();

        orderListModel = new OrderListModel();
        orderListModel.setMainController(this);
        orderListModel.createAndStartDaemonThreadForDateAndTime();

        orderHistoryModel = new OrderHistoryModel();
        orderHistoryModel.setMainController(this);
        orderHistoryModel.setOrderHistoryTable();
        orderHistoryModel.setTextFieldSearch();

        dashboardModel = new DashboardModel();
        dashboardModel.setMainController(this);

        settingsModel = new SettingsModel();
        settingsModel.setMainController(this);

        clearAllReferences();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                menuModel.setCustomerNumber();
                settingsModel.setVideo();
            }
        });
    }

    private void clearAllReferences() {
        clearAllProductReferences();
        clearProductOrderReferences();
        clearOrderHistoryReferences();
        clearOrderQueueReferences();
        clearDashBoardReferences();
    }

    /**
     * Main
     */

    public FXMLLoader loader;
    public Parent root;
    public Stage newStage;
    @FXML
    public Rectangle rectangleModal;
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
        mainModel.openSelectedPane(MENU_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneMenuTouched() throws IOException {
        mainModel.openSelectedPane(MENU_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneDashBoardClicked() throws IOException {
        mainModel.openSelectedPane(DASHBOARD_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneDashboardTouched() throws IOException {
        mainModel.openSelectedPane(DASHBOARD_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderListClicked() throws IOException {
        mainModel.openSelectedPane(ORDER_LIST_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderListTouched() throws IOException {
        mainModel.openSelectedPane(ORDER_LIST_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderHistoryClicked() throws IOException {
        mainModel.openSelectedPane(ORDER_HISTORY_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderHistoryTouched() throws IOException {
        mainModel.openSelectedPane(ORDER_HISTORY_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsClicked() throws IOException {
        mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsTouched() throws IOException {
        mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneLogoutClicked() throws IOException {
        mainModel.openSelectedPane(LOGOUT_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneLogoutTouched() throws IOException {
        mainModel.openSelectedPane(LOGOUT_ENUM.getPaneNumber());
    }

    /**
     * Menu
     */
    @FXML
    public Label labelMenuNoName;
    @FXML
    public Label labelMenuNoModeOfPayment;
    @FXML
    public Label labelMenuNoAmount;
    @FXML
    public ImageView imageCash;
    @FXML
    public ImageView imageGCash;
    @FXML
    public Label labelCash;
    @FXML
    public Label labelGCash;
    @FXML
    public TextField textFieldMenuCustomerName;
    @FXML
    public FlowPane flowPaneOrdersSelected;
    @FXML
    public Label labelNoOrdersSelected;
    @FXML
    public AnchorPane anchorPaneHideHalfRightPanel;
    @FXML
    public TextField textFieldMenuEnterAmount;
    @FXML
    public Label labelMenuTotalPrice;
    @FXML
    public Label labelCustomerNumber;

    @FXML
    public Label labelMenuCategorySelected;
    @FXML
    public Label labelMenuCategoryResultCounter;
    @FXML
    public FlowPane flowPaneMenu;
    @FXML
    public ImageView imageViewMenuAll;
    @FXML
    public ImageView imageViewMenuMilkTea;
    @FXML
    public ImageView imageViewMenuCoolers;
    @FXML
    public ImageView imageViewMenuCoffee;
    @FXML
    public ImageView imageViewMenuIceCandyCups;
    @FXML
    public ImageView imageViewMenuAppetizers;
    @FXML
    public AnchorPane anchorPaneRightPanel;
    @FXML
    public AnchorPane anchorPaneMenuIsEmpty;

    @FXML
    public void menuNeedHelpClicked(MouseEvent event) throws IOException {
        menuModel.goToSystemManual();
    }

    @FXML
    public void menuNeedHelpTouched(TouchEvent event) throws IOException {
        menuModel.goToSystemManual();
    }

    @FXML
    public void textFieldCustomerNameTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (anchorPaneHideHalfRightPanel.isVisible())
                menuModel.payClicked();
        } else {
            menuModel.customerNameTyping();
        }
    }

    @FXML
    public void textFieldAmountTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            menuModel.payClicked();
        } else {
            menuModel.amountTyping();
        }
    }
    @FXML
    public void imageViewMenuAllClicked(MouseEvent event) {
        menuModel.switchCategory(ALL_PRODUCT_CATEGORY_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuAllTouched(TouchEvent event) {
        menuModel.switchCategory(ALL_PRODUCT_CATEGORY_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuMilkTeaClicked(MouseEvent event) {
        menuModel.switchCategory(MILK_TEA_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuMilkTeaTouched(TouchEvent event) {
        menuModel.switchCategory(MILK_TEA_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuCoolersClicked(MouseEvent event) {
        menuModel.switchCategory(COOLERS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuCoolersTouched(TouchEvent event) {
        menuModel.switchCategory(COOLERS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuCoffeeClicked(MouseEvent event) {
        menuModel.switchCategory(COFFEE_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuCoffeeTouched(TouchEvent event) {
        menuModel.switchCategory(COFFEE_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuIceCandyCupsClicked(MouseEvent event) {
        menuModel.switchCategory(ICE_CANDY_CUPS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuIceCandyCupsTouched(TouchEvent event) {
        menuModel.switchCategory(ICE_CANDY_CUPS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuAppetizersClicked(MouseEvent event) {
        menuModel.switchCategory(APPETIZERS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuAppetizersTouched(TouchEvent event) {
        menuModel.switchCategory(APPETIZERS_ENUM.getNumber());
    }

    @FXML
    public void menuPaymentCashClicked(MouseEvent event) {
        menuModel.updateModeOfPayment(true);
    }

    @FXML
    public void menuPaymentCashTouched(TouchEvent event) {
        menuModel.updateModeOfPayment(true);
    }

    @FXML
    public void menuPaymentGCashClicked(MouseEvent event) {
        menuModel.updateModeOfPayment(false);
    }

    @FXML
    public void menuPaymentGCashTouched(TouchEvent event) {
        menuModel.updateModeOfPayment(false);
    }

    @FXML
    public void menuPaymentCancelClicked(MouseEvent event) {
        menuModel.orderCancelledOrAddedToQueue();
    }

    @FXML
    public void menuPaymentCancelTouched(TouchEvent event) {
        menuModel.orderCancelledOrAddedToQueue();
    }

    @FXML
    public void menuPaymentPayClicked(MouseEvent event) {
        menuModel.payClicked();
    }

    @FXML
    public void menuPaymentPayTouched(TouchEvent event) {
        menuModel.payClicked();
    }

    @FXML
    public void labelMenuIsEmptyClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
        settingsModel.openSelectedPane(EditProducts.getPaneNumber());
    }
    @FXML
    public void labelMenuIsEmptyTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
        settingsModel.openSelectedPane(EditProducts.getPaneNumber());
    }

    /**
     * Order queue
     */

    @FXML
    public FlowPane flowPaneOrderQueue;
    @FXML
    public Label labelOrderQueueDateAndTIme;
    @FXML
    public Label labelOrderQueueOrderInQueue;
    @FXML
    public Label labelOrderQueueTotalOrder;
    @FXML
    public AnchorPane anchorPaneOrderListNoOrders;

    /**
     *  Order History
     */

    @FXML
    public ImageView btnHistoryTableRefresh;
    @FXML
    public TextField textFieldOrderHistorySearch;
    @FXML
    public ComboBox<String> comboBoxOrderHistory;
    @FXML
    public TableView<Order> tableViewOrderHistory;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColCustomerName;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColProductCategory;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColProductName;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColQuantity;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColPrice;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColTotalPrice;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColAmountPaid;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColChange;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColModeOfPayment;
    @FXML
    public TableColumn<Order, LocalDateTime> tableViewOrderHistoryColDateAndTime;

    @FXML
    public void btnHistoryTableRefreshClicked(MouseEvent event) {
        orderHistoryModel.refreshOrderHistoryTable();
    }

    @FXML
    public void btnHistoryTableRefreshTouched(TouchEvent event) {
        orderHistoryModel.refreshOrderHistoryTable();
    }

    @FXML
    void textFieldOrderHistorySearchTyping(KeyEvent event) {

    }

    @FXML
    void comboBoxOrderHistoryOnAction(ActionEvent event) {
        orderHistoryModel.getComboBoxValue();
    }

    @FXML
    void btnOrderHistoryDeleteClicked(MouseEvent event) {
        orderHistoryModel.orderHistoryDeleteBin();
    }

    @FXML
    void btnOrderHistoryDeleteTouched(TouchEvent event) {
        orderHistoryModel.orderHistoryDeleteBin();
    }

    /**
     * Dashboard
     */

    @FXML
    public Label labelDashBoardNoSalesPieChart;
    @FXML
    public Label labelDashBoardNoSalesBestSeller;
    @FXML
    public Label labelDashboardTotalRevenue;
    @FXML
    public Label labelDashboardTotalCustomer;
    @FXML
    public Label labelDashboardTotalOrder;
    @FXML
    public PieChart pieChartDashboard;
    @FXML
    public FlowPane flowPaneBestSeller;

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
    public boolean orderIsOngoing = false;
    @FXML
    public ComboBox<String> importExportComboBox;
    @FXML
    public Label labelSettingsEditProductsUnavailable;
    @FXML
    public ImageView btnProductTableRefresh;
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

    @FXML
    public void settingsDeleteProductClicked(MouseEvent event) throws IOException {
        if (!orderIsOngoing)
            settingsModel.deleteSelectedProductsProcess();
    }

    @FXML
    public void settingsDeleteProductTouched(TouchEvent event) throws IOException {
        if (!orderIsOngoing)
            settingsModel.deleteSelectedProductsProcess();
    }

    @FXML
    public void tableProductsClicked(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            if (!orderIsOngoing)
                settingsModel.editAProduct();
        }
    }

    @FXML
    public void tableProductsTouched(TouchEvent event) throws IOException {
        if (event.getTouchCount() == 2) {
            if (!orderIsOngoing)
                settingsModel.editAProduct();
        }
    }

    @FXML
    public void btnProductTableRefreshTouched(TouchEvent event) {
        settingsModel.refreshProductTable();
    }

    @FXML
    public void btnProductTableRefreshClicked(MouseEvent event) {
        settingsModel.refreshProductTable();
    }

    @FXML
    void importExportOnAction(ActionEvent event) {
        settingsModel.comboBoxValueSelected();
    }

    /**
     * Settings - System Manual
     */

    @FXML
    public MediaView systemVideoPlayer;
}