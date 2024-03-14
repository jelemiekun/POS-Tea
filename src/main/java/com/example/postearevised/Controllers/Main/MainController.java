package com.example.postearevised.Controllers.Main;

import com.example.postearevised.Models.Main.*;
import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Enums.MainPane.*;
import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPane.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.trueAddProductFalseDeleteProduct;

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

        orderListModel = new OrderListModel();
        orderListModel.setMainController(this);
        orderListModel.createAndStartDaemonThreadForDateAndTime();

        orderHistoryModel = new OrderHistoryModel();
        orderHistoryModel.setMainController(this);
        orderHistoryModel.setOrderHistoryTable();

        dashboardModel = new DashboardModel();
        dashboardModel.setMainController(this);

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
    public void imageViewMenuAllClicked(MouseEvent event) {
        menuModel.switchCategory(AllProductCategoryEnum.getNumber());
    }

    @FXML
    public void imageViewMenuAllTouched(TouchEvent event) {
        menuModel.switchCategory(AllProductCategoryEnum.getNumber());
    }

    @FXML
    public void imageViewMenuMilkTeaClicked(MouseEvent event) {
        menuModel.switchCategory(MilkTeaEnum.getNumber());
    }

    @FXML
    public void imageViewMenuMilkTeaTouched(TouchEvent event) {
        menuModel.switchCategory(MilkTeaEnum.getNumber());
    }

    @FXML
    public void imageViewMenuCoolersClicked(MouseEvent event) {
        menuModel.switchCategory(CoolersEnum.getNumber());
    }

    @FXML
    public void imageViewMenuCoolersTouched(TouchEvent event) {
        menuModel.switchCategory(CoolersEnum.getNumber());
    }

    @FXML
    public void imageViewMenuCoffeeClicked(MouseEvent event) {
        menuModel.switchCategory(CoffeeEnum.getNumber());
    }

    @FXML
    public void imageViewMenuCoffeeTouched(TouchEvent event) {
        menuModel.switchCategory(CoffeeEnum.getNumber());
    }

    @FXML
    public void imageViewMenuIceCandyCupsClicked(MouseEvent event) {
        menuModel.switchCategory(IceCandyCupsEnum.getNumber());
    }

    @FXML
    public void imageViewMenuIceCandyCupsTouched(TouchEvent event) {
        menuModel.switchCategory(IceCandyCupsEnum.getNumber());
    }

    @FXML
    public void imageViewMenuAppetizersClicked(MouseEvent event) {
        menuModel.switchCategory(AppetizersEnum.getNumber());
    }

    @FXML
    public void imageViewMenuAppetizersTouched(TouchEvent event) {
        menuModel.switchCategory(AppetizersEnum.getNumber());
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
    public AnchorPane anchorPaneRightPanel;
    @FXML
    public AnchorPane anchorPaneMenuIsEmpty;

    @FXML
    public void labelMenuIsEmptyClicked(MouseEvent event) throws IOException {
        mainModel.openSelectedPane(Settings.getPaneNumber());
        settingsModel.openSelectedPane(EditProducts.getPaneNumber());
    }
    @FXML
    public void labelMenuIsEmptyTouched(TouchEvent event) throws IOException {
        mainModel.openSelectedPane(Settings.getPaneNumber());
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
    public TextField textFieldOrderHistorySearch;
    @FXML
    public ComboBox<String> comboBoxOrderHistory;
    @FXML
    public TableView<Order> tableViewOrderHistory;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColCustomerName;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColProductName;
    @FXML
    public TableColumn<Order, Integer> tableViewOrderHistoryColQuantity;
    @FXML
    public TableColumn<Order, Integer> tableViewOrderHistoryColPrice;
    @FXML
    public TableColumn<Order, Integer> tableViewOrderHistoryColTotalPrice;
    @FXML
    public TableColumn<Order, Integer> tableViewOrderHistoryColAmountPaid;
    @FXML
    public TableColumn<Order, Integer> tableViewOrderHistoryColChange;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColModeOfPayment;
    @FXML
    public TableColumn<Order, LocalDateTime> tableViewOrderHistoryColDateAndTime;

    @FXML
    void textFieldOrderHistorySearchTyping(KeyEvent event) {

    }

    @FXML
    void comboBoxOrderHistoryOnAction(ActionEvent event) {

    }

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
    public Label labelAddDeleteProductBtn;
    @FXML
    public ImageView imageViewAddDeleteProductBtn;
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
    public TableColumn<Product, CheckBox> tableProductsColDelete;

    @FXML
    public void settingsAddDeleteProductClicked() throws IOException{
        if (!trueAddProductFalseDeleteProduct)
            settingsModel.openAddProductsFXML();
        else {
            if (!orderIsOngoing)
                settingsModel.deleteSelectedProductsProcess();
        }
    }
    @FXML
    public void settingsAddDeleteProductTouched() throws IOException {
        if (!trueAddProductFalseDeleteProduct)
            settingsModel.openAddProductsFXML();
        else {
            if (!orderIsOngoing)
                settingsModel.deleteSelectedProductsProcess();
        }
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
}