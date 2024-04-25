package com.example.postearevised.Controllers.Main;

import com.example.postearevised.Models.Main.*;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Products.Product;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static com.example.postearevised.Miscellaneous.Database.CSV.CSVUtility.*;
import static com.example.postearevised.Miscellaneous.Enums.DisplayColorsEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.MainPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.Enums.SettingsPaneEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.InvoiceGenerator.*;
import static com.example.postearevised.Miscellaneous.References.AccountReference.*;
import static com.example.postearevised.Miscellaneous.References.DashboardReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductOrderReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

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
        isAddingProductsFromImport = true;

        System.gc();

        anchorPaneLoading.setVisible(true);
        loading();

        new Thread(() -> {
            clearAllReferences();

            Thread t1 = new Thread(() -> {
                doesProductCSVExist();
                doesOrderHistoryCSVExist();
                doesInvoicePathExist();
            });

            t1.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                errorMessage = e.getMessage();
                logError(false);
            }

            mainModel = new MainModel();
            mainModel.setMainController(this);

            menuModel = new MenuModel();
            menuModel.setMainController(this);

            orderListModel = new OrderListModel();
            orderListModel.setMainController(this);

            orderHistoryModel = new OrderHistoryModel();
            orderHistoryModel.setMainController(this);

            dashboardModel = new DashboardModel();
            dashboardModel.setMainController(this);

            settingsModel = new SettingsModel();
            settingsModel.setMainController(this);



            Platform.runLater(() -> {
                settingsModel.setStyles(accountReference.getDisplayColor(), false);

                anchorPaneLeftPanel.setVisible(true);
                anchorPaneDashboard.setVisible(true);

                mainModel.setAccountDetails();
                mainModel.setDropShadow();
                mainModel.createAndStartDaemonThreadForDateAndTime();
                mainModel.setMainMenuIconSelected();
                mainModel.setAnchorPane();
                mainModel.setToolTips();
                mainModel.setComboBoxUsers();

                dashboardModel.setupDashboard();

                menuModel.setDropShadow();
                menuModel.setTextFieldListeners();
                menuModel.setComboBoxModEOfPaymentItems();
                menuModel.setCustomerNumber();

                orderListModel.readImportedOrders();

                orderHistoryModel.setOrderHistoryTable();
                
                settingsModel.setVideo();
                settingsModel.populateComboBoxImportExport();
                settingsModel.setSettingsAccountStyle();

                isAddingProductsFromImport = false;


                fadeOutLoading();

                dashboardModel.firstChoiceBoxOnAction();

                mainModel.checkIfNewAccount();
            });
        }).start();
    }

    private void clearAllReferences() {
        clearAllProductReferences();
        clearProductOrderReferences();
        clearOrderHistoryReferences();
        clearOrderQueueReferences();
        clearDashBoardReferences();
        fullNames.clear();
        usersNames.clear();
    }

    /**
     * Loading
     */
    @FXML
    public AnchorPane anchorPaneLoading;
    @FXML
    public Label labelLoading;
    private Timeline loadingTimeline;
    private void loading() {
        loadingTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> labelLoading.setText("Loading.")),
                new KeyFrame(Duration.seconds(1), event -> labelLoading.setText("Loading..")),
                new KeyFrame(Duration.seconds(1.5), event -> labelLoading.setText("Loading..."))
        );
        loadingTimeline.setCycleCount(Timeline.INDEFINITE);
        loadingTimeline.play();
    }

    private void fadeOutLoading() {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(1200), anchorPaneLoading);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            anchorPaneLoading.setVisible(false);
            loadingTimeline.stop();
        });
        fadeOut.play();
    }

    /**
     * Main
     */

    public FXMLLoader loader;
    public Parent root;
    public Stage newStage;
    @FXML
    public ComboBox<String> comboBoxLeftPanelUsers;
    @FXML
    public FlowPane flowPaneNotification;
    @FXML
    public Label labelProfileName;
    @FXML
    public AnchorPane anchorPaneLeftProfile;
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
    void anchorPaneMenuClicked() {
        mainModel.openSelectedPane(MENU_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneMenuTouched() {
        mainModel.openSelectedPane(MENU_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneDashBoardClicked() {
        mainModel.openSelectedPane(DASHBOARD_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneDashboardTouched() {
        mainModel.openSelectedPane(DASHBOARD_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderListClicked() {
        mainModel.openSelectedPane(ORDER_LIST_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderListTouched() {
        mainModel.openSelectedPane(ORDER_LIST_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderHistoryClicked() {
        mainModel.openSelectedPane(ORDER_HISTORY_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneOrderHistoryTouched() {
        mainModel.openSelectedPane(ORDER_HISTORY_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsClicked() {
        mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsTouched() {
        mainModel.openSelectedPane(SETTINGS_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneLogoutClicked() {
        mainModel.openSelectedPane(LOGOUT_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneLogoutTouched() {
        mainModel.openSelectedPane(LOGOUT_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneLeftPanelRequestFocus() { anchorPaneLeftPanel.requestFocus(); }

    @FXML
    void anchorPaneLeftProfileClickedTouched() {
        mainModel.toggleComboBoxUsers();
    }

    @FXML
    void comboBoxLeftPanelUsersOnAction() {
        mainModel.comboBoxLeftPanelUsersOnAction();
    }

    /**
     * Dashboard
     */
    @FXML
    public AnchorPane anchorDashboardPaneNeedHelpGettingStarted;
    @FXML
    public Rectangle rectangleDashboardLegends1;
    @FXML
    public Rectangle rectangleDashboardLegends2;
    @FXML
    public Rectangle rectangleDashboardLegends3;
    @FXML
    public Rectangle rectangleDashboardLegends4;
    @FXML
    public Rectangle rectangleDashboardLegends5;
    @FXML
    public FlowPane flowPaneDashboardLegends;
    @FXML
    public AnchorPane anchorPaneResetToToday;
    @FXML
    public Label labelDashBoardNoSalesRecordedGraph;
    @FXML
    public Label labelDashboardTotalRevenueTitle;
    @FXML
    public Label labelDashboardTotalCustomerTitle;
    @FXML
    public Label labelDashboardTotalOrderTitle;
    @FXML
    public Label labelDashboardBarChartTitle;
    @FXML
    public ComboBox<String> dashboardComboBoxFirstSelection;
    @FXML
    public ComboBox<String> dashboardComboBoxSecondSelection;
    @FXML
    public ComboBox<String> dashboardComboBoxThirdSelection;
    @FXML
    public ComboBox<String> dashboardComboBoxFourthSelection;
    @FXML
    public BarChart<String, Number> dashboardBarChart;
    @FXML
    public Label labelDashboardDateAndTIme;
    @FXML
    public Label labelDashBoardNoSalesBestSeller;
    @FXML
    public Label labelDashboardTotalRevenue;
    @FXML
    public Label labelDashboardTotalCustomer;
    @FXML
    public Label labelDashboardTotalOrder;
    @FXML
    public FlowPane flowPaneBestSeller;


    @FXML
    public void anchorPaneResetToTodayPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            dashboardModel.resetToToday();
    }
    @FXML
    public void anchorPaneDashboardRequestFocus() { anchorPaneDashboard.requestFocus(); }

    @FXML
    public void dashboardComboBoxFirstSelectionOnAction() { dashboardModel.firstChoiceBoxOnAction(); }

    @FXML
    public void dashboardComboBoxSecondSelectionOnAction() { dashboardModel.firstChoiceBoxOnAction(); }

    @FXML
    public void dashboardComboBoxThirdSelectionOnAction() { dashboardModel.firstChoiceBoxOnAction(); }

    @FXML
    public void dashboardComboBoxFourthSelectionOnAction() { dashboardModel.firstChoiceBoxOnAction(); }

    @FXML
    public void dashboardNeedHelpClickedTouched() {
        dashboardModel.goToSystemManual();
    }

    @FXML
    public void dashboardNeedHelpPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            dashboardModel.goToSystemManual();
    }

    @FXML
    public void anchorPaneDashboardResetToTodayClickedTouched() { dashboardModel.resetToToday(); }

    /**
     * Menu
     */
    @FXML
    public Label menuGuideMessageBillsProductOrder;
    @FXML
    public AnchorPane anchorPaneMenuIsEmptyInner;
    @FXML
    public AnchorPane anchorPaneMenuGoToEditProducts;
    @FXML
    public Label labelMenuCategoryUnavailableProductCounter;
    @FXML
    public Label labelMenuDateAndTIme;
    @FXML
    public Label labelSelectModeOfPayment;
    @FXML
    public ComboBox<String> comboBoxModeOfPayment;
    @FXML
    public TextField textFieldModeOfPaymentOthers;
    @FXML
    public Label labelMenuNoName;
    @FXML
    public Label labelMenuNoModeOfPayment;
    @FXML
    public Label labelMenuNoAmount;
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
    public void menuEditProductClickedTouched() {
        menuModel.goToEditProducts();
    }

    @FXML
    public void comboBoxModeOfPaymentOnAction() {
        menuModel.modeOfPaymentSelected();
    }

    @FXML
    public void menuEditProductPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            menuModel.goToEditProducts();
    }

    @FXML
    public void comboBoxModeOfPaymentPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            menuModel.payClicked();
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
    public void anchorPaneMenuIsEmptyPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            menuModel.goToEditProducts();
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
    public void menuPaymentCancelClicked(MouseEvent event) {
        menuModel.orderCancelledOrAddedToQueue(true);
    }

    @FXML
    public void menuPaymentCancelTouched(TouchEvent event) {
        menuModel.orderCancelledOrAddedToQueue(true);
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
    public void labelMenuIsEmptyClickedTouched() {
        menuModel.goToEditProducts();
    }

    /**
     * Order queue
     */
    @FXML
    public Label orderQueueGuideMessageClickTable;
    @FXML
    public AnchorPane orderQueueAnchorPaneNoOrder;
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
    public Label orderHistoryGuideMessageDoubleClickTable;
    @FXML
    public Label labelOrderHistoryDateAndTIme;
    @FXML
    public ImageView btnHistoryTableRefresh;
    @FXML
    public TextField textFieldOrderHistorySearch;
    @FXML
    public ComboBox<String> comboBoxOrderHistory;
    @FXML
    public TableView<Order> tableViewOrderHistory;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColTransactionID;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColCustomerName;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColProductCategory;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColProductName;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColQuantity;
    @FXML
    public TableColumn<Order, String> tableViewOrderHistoryColTotalPrice;
    @FXML
    public TableColumn<Order, LocalDateTime> tableViewOrderHistoryColDateAndTime;

    public ChangeListener<String> textFieldChangeListenerDigitsOnly = (observable, oldValue, newValue) -> {
        if (newValue.isEmpty()) {
            textFieldOrderHistorySearch.setText(newValue);
            orderHistoryModel.searchTheText(newValue.trim());
        } else if (!newValue.matches(REGEX_DIGITS_ONLY)) {
            textFieldOrderHistorySearch.setText(oldValue);
        } else {
            orderHistoryModel.searchTheText(newValue.trim());
        }
    };

    public ChangeListener<String> textFieldChangeListenerCharactersOnly = (observable, oldValue, newValue) -> {
        if (newValue.isEmpty()) {
            textFieldOrderHistorySearch.setText(newValue);
            orderHistoryModel.searchTheText(newValue.trim());
        } else if (!newValue.matches(REGEX_ORDER_HISTORY_SEARCH_256_LIMIT_NO_SPACE_IN_FRONT_NO_SPECIAL_CHARACTERS)) {
            textFieldOrderHistorySearch.setText(oldValue);
        } else {
            orderHistoryModel.searchTheText(newValue.trim());
        }
    };

    @FXML
    public void btnHistoryTableRefreshClicked(MouseEvent event) {
        orderHistoryModel.refreshOrderHistoryBtn();
    }

    @FXML
    public void btnHistoryTableRefreshTouched(TouchEvent event) {
        orderHistoryModel.refreshOrderHistoryBtn();
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

    @FXML
    public void tableViewOrderHistoryClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            orderHistoryModel.openOrderDetails();
        }
    }

    @FXML
    public void tableViewOrderHistoryPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            orderHistoryModel.openOrderDetails();
    }

    @FXML
    public void tableViewOrderHistoryTouched(TouchEvent event) {
        if (event.getTouchCount() == 2) {
            orderHistoryModel.openOrderDetails();
        }
    }

    @FXML
    public void anchorPaneOrderHistoryRequestFocus() {
        anchorPaneOrderHistory.requestFocus();
        tableViewOrderHistory.getSelectionModel().clearSelection();
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
        settingsModel.openSelectedPane(SETTINGS_PANE_ACCOUNT_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsAccountTouched() {
        settingsModel.openSelectedPane(SETTINGS_PANE_ACCOUNT_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsDisplayClicked() {
        settingsModel.openSelectedPane(SETTINGS_PANE_APPEARANCE_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsDisplayTouched() {
        settingsModel.openSelectedPane(SETTINGS_PANE_APPEARANCE_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsTACClicked() {
        settingsModel.openSelectedPane(SETTINGS_PANE_TAC_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsTACTouched() {
        settingsModel.openSelectedPane(SETTINGS_PANE_TAC_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsEditProductsClicked() {
        settingsModel.openSelectedPane(SETTINGS_PANE_EDIT_PRODUCT_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsEditProductsTouched() {
        settingsModel.openSelectedPane(SETTINGS_PANE_EDIT_PRODUCT_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsSystemManualClicked() {
        settingsModel.openSelectedPane(SETTINGS_PANE_SYSTEM_MANUAL_ENUM.getPaneNumber());
    }

    @FXML
    void anchorPaneSettingsSystemManualTouched() {
        settingsModel.openSelectedPane(SETTINGS_PANE_SYSTEM_MANUAL_ENUM.getPaneNumber());
    }

    /**
     * Settings - Accounts
     */

    // Boolean Reference

    public boolean editUsers = false;
    public boolean editAccount = false;
    public boolean editSecurityQuestions = false;
    public boolean isUpdatingComboBox = false;
    public boolean detectChangesUsers = false;
    public boolean detectChangesAccountDetails = false;
    public boolean detectChangesRecoveryQuestion = false;
    public boolean showNewPassword = false;
    public boolean showConfirmNewPassword = false;
    public boolean accountDetailsSubmittedOnce = false;


    @FXML
    public Rectangle passwordRectangle1;
    @FXML
    public Rectangle passwordRectangle2;
    @FXML
    public Rectangle passwordRectangle3;
    @FXML
    public Rectangle passwordRectangle4;


    // Labels
    @FXML
    public Label labelSettingsAccountEditFinishUsers;
    @FXML
    public Label labelSettingsAccountEditFinishAccountDetails;
    @FXML
    public Label labelSettingsAccountEditFinishSecurityQuestions;
    @FXML
    public Label passwordIndicator;

    // Buttons and ComboBoxes
    @FXML
    public ComboBox<String> comboBoxAccountName;
    @FXML
    public TextField textFieldAccountGivenName;
    @FXML
    public TextField textFieldAccountMiddleName;
    @FXML
    public TextField textFieldAccountLastName;
    @FXML
    public TextField textFieldAccountContact;
    @FXML
    public TextField textFieldAccountNewPassword;
    @FXML
    public TextField textFieldAccountConfirmNewPassword;
    @FXML
    public PasswordField passwordFieldAccountNewPassword;
    @FXML
    public PasswordField passwordFieldAccountConfirmNewPassword;
    @FXML
    public ComboBox<String> comboBoxSettingsQuestionOne;
    @FXML
    public TextField textFieldAccountQuestionOne;
    @FXML
    public ComboBox<String> comboBoxSettingsQuestionTwo;
    @FXML
    public TextField textFieldAccountQuestionTwo;
    public PasswordField passwordFieldAccountNewUser;

    // AnchorPanes
    @FXML
    public AnchorPane anchorPaneSettingsAccountUsers;
    @FXML
    public AnchorPane anchorPaneSettingsBtnDeleteUser;
    @FXML
    public AnchorPane anchorPaneSettingsBtnEditUsers;
    @FXML
    public AnchorPane anchorPaneSettingsBtnEditSecurityQuestions;
    @FXML
    public AnchorPane anchorPaneSettingsBtnEditAccountDetails;
    @FXML
    public AnchorPane anchorPaneSettingsBtnDelete;
    @FXML
    public AnchorPane anchorPanePasswordIndicator;

    @FXML
    public FlowPane anchorPaneSettingsAccountInner;

    // ImageViews
    @FXML
    public ImageView imagePencilSettingsAccount1;
    @FXML
    public ImageView imagePencilSettingsAccount2;
    @FXML
    public ImageView imagePencilSettingsAccount3;
    @FXML
    public ImageView imagePencilSettingsAccount4;
    @FXML
    public ImageView imagePencilSettingsAccount5;
    @FXML
    public ImageView imagePencilSettingsAccount6;
    @FXML
    public ImageView imagePencilSettingsAccount7;
    @FXML
    public ImageView imagePencilSettingsAccount8;
    @FXML
    public ImageView imagePencilSettingsAccount9;
    @FXML
    public ImageView imagePencilSettingsAccount10;
    @FXML
    public ImageView imagePencilSettingsAccount11;
    @FXML
    public ImageView imagePencilSettingsAccount12;
    @FXML
    public ImageView passwordToolTip;
    @FXML
    public Label labelSettingsFillUpThisForm1;
    @FXML
    public Label labelSettingsFillUpThisForm2;
    @FXML
    public Label labelSettingsFillUpThisForm3;
    @FXML
    public Label labelSettingsFillUpThisForm4;
    @FXML
    public Label labelSettingsFillUpThisForm5;
    @FXML
    public Label labelSettingsFillUpThisForm6;
    @FXML
    public Label labelSettingsFillUpThisForm7;
    @FXML
    public Label labelSettingsFillUpThisForm8;
    @FXML
    public Label labelSettingsFillUpThisForm9;
    @FXML
    public Label labelSettingsFillUpThisForm10;
    @FXML
    public Label labelSettingsFillUpThisForm11;
    @FXML
    public Label labelSettingsOnlyAdmin;
    @FXML
    public Label labelMiddleNameOptional;
    public Label labelNewUserPassword;


    @FXML
    public ImageView imageHideShowNewPasswordAccountSettings;
    @FXML
    public ImageView imageHideShowConfirmNewPasswordAccountSettings;

    @FXML
    void passwordToolTipClickedTouched() {

    }

    @FXML
    void passwordToolTipEntered() {

    }

    @FXML
    void passwordToolTipExited() {

    }

    @FXML
    public void settingsUsersTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            anchorPaneSettingsBtnEditUsersClickedTouched();
        } else {
            detectChangesUsers = true;
            settingsModel.accountUsersTyping();
        }
    }

    @FXML
    void settingsAccountDetailsTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            anchorPaneSettingsBtnEditAccountDetailsClickedTouched();
        } else {
            settingsModel.accountDetailsTyping();
        }
    }

    @FXML
    void comboBoxAccountNamePressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            anchorPaneSettingsBtnEditUsersClickedTouched();
    }

    @FXML
    public void anchorPaneSettingsBtnDeleteUserClickedTouched() {
        settingsModel.deleteSelectedName();
    }

    @FXML
    public void anchorPaneSettingsBtnEditUsersClickedTouched() {
        editUsers = !editUsers;
        settingsModel.setSettingsAccountPane1(editUsers);
    }

    @FXML
    public void anchorPaneSettingsBtnEditAccountDetailsClickedTouched() {
        editAccount = !editAccount;
        settingsModel.setSettingsAccountPane2(editAccount);
    }

    @FXML
    public void anchorPaneSettingsBtnEditSecurityQuestionsClickedTouched() {
        editSecurityQuestions = !editSecurityQuestions;
        settingsModel.setSettingsAccountPane3(editSecurityQuestions);
    }

    @FXML
    public void comboBoxAccountNameOnAction() {
        settingsModel.comboBoxNameOnAction();
    }

    @FXML
    public void comboBoxSettingsQuestionOneOnAction() {
        settingsModel.comboBoxQuestionOneOnAction();
    }

    @FXML
    public void comboBoxSettingsQuestionTwoOnAction() {
        settingsModel.comboBoxQuestionTwoOnAction();
    }

    @FXML
    public void imageHideShowNewPasswordAccountSettingsClickedTouched() {
        settingsModel.toggleNewPasswordField();
    }

    @FXML
    public void imageHideShowConfirmNewPasswordAccountSettingsClickedTouched() {
        settingsModel.toggleConfirmNewPasswordField();
    }

    @FXML
    public void anchorPaneSettingsBtnDeleteClickedTouched() {
        settingsModel.deleteAccountProcess();
    }

    @FXML
    void textFieldRecoveryQuestionTyping(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            anchorPaneSettingsBtnEditSecurityQuestionsClickedTouched();
        } else {
            settingsModel.accountRecoveryQuestionsTyping();
        }
    }

    /**
     * Settings - Appearance
     */
    @FXML
    public CheckBox checkBoxSettingNotification;
    @FXML
    public CheckBox checkBoxSettingGuideMessages;
    @FXML
    public Rectangle settingsAppearanceRectangleLight;
    @FXML
    public Rectangle settingsAppearanceRectangleDark;
    @FXML
    public Rectangle settingsAppearanceRectangleRed;
    @FXML
    public Rectangle settingsAppearanceRectangleOrange;
    @FXML
    public Rectangle settingsAppearanceRectangleYellow;
    @FXML
    public Rectangle settingsAppearanceRectangleGreen;
    @FXML
    public Label labelAppearanceInUse1;
    @FXML
    public Label labelAppearanceInUse2;
    @FXML
    public Label labelAppearanceInUse3;
    @FXML
    public Label labelAppearanceInUse4;
    @FXML
    public Label labelAppearanceInUse5;
    @FXML
    public Label labelAppearanceInUse6;


    @FXML
    void settingsAppearanceRectangleClickedTouched1() { settingsModel.setStyles(LIGHT_ENUM.getColor(), true); }

    @FXML
    void settingsAppearanceRectangleClickedTouched2() { settingsModel.setStyles(DARK_ENUM.getColor(), true); }

    @FXML
    void settingsAppearanceRectangleClickedTouched3() { settingsModel.setStyles(BEIGE_ENUM.getColor(), true); }

    @FXML
    void settingsAppearanceRectangleClickedTouched4() { settingsModel.setStyles(BLUE_ENUM.getColor(), true); }

    @FXML
    void settingsAppearanceRectangleClickedTouched5() { settingsModel.setStyles(CREAM_ENUM.getColor(), true); }

    @FXML
    void settingsAppearanceRectangleClickedTouched6() { settingsModel.setStyles(GREEN_ENUM.getColor(), true); }

    @FXML
    public void checkBoxSettingNotificationOnAction() {
        settingsModel.notificationOnAction();
    }

    @FXML
    public void checkBoxSettingGuideMessagesOnAction() {
        settingsModel.guideMessagesOnAction();
    }


    /**
     * Settings - Products
     */
    @FXML
    public AnchorPane anchorPaneEditProduct;
    @FXML
    public AnchorPane anchorPaneAddProduct;
    @FXML
    public Label settingsEditProductGuideMessageClickTable;
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
    public void settingsAddProductClicked() {
        if (!orderIsOngoing)
            settingsModel.openAddProductsFXML();
    }
    @FXML
    public void settingsAddProductTouched() {
        if (!orderIsOngoing)
            settingsModel.openAddProductsFXML();
    }

    @FXML
    public void settingsDeleteProductClicked(MouseEvent event) {
        if (!orderIsOngoing)
            settingsModel.deleteSelectedProductsProcess();
    }

    @FXML
    public void settingsDeleteProductTouched(TouchEvent event) {
        if (!orderIsOngoing)
            settingsModel.deleteSelectedProductsProcess();
    }

    @FXML
    public void tableProductsPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (!orderIsOngoing)
                settingsModel.editAProduct();
        }
    }

    @FXML
    public void tableViewProductsClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            if (!orderIsOngoing)
                settingsModel.editAProduct();
        }
    }

    @FXML
    public void tableViewProductsTouched(TouchEvent event) {
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
    public AnchorPane ap1;
    @FXML
    public TextFlow menuText1;
    @FXML
    public TextFlow menuText2;
    @FXML
    public TextFlow menuText3;
    @FXML
    public TextFlow menuText4;
    @FXML
    public TextFlow menuText5;
    @FXML
    public TextFlow menuText6;
    @FXML
    public TextFlow menuText7;
    @FXML
    public TextFlow menuText8;
    @FXML
    public TextFlow menuText9;
    @FXML
    public TextFlow menuText10;
    @FXML
    public TextFlow menuText11;
    @FXML
    public TextFlow menuText12;
    @FXML
    public TextFlow orderQueueText1;
    @FXML
    public TextFlow orderHistoryText1;
    @FXML
    public TextFlow dashboardText1;

    @FXML
    public HBox menuHbox1;
    @FXML
    public MediaView mediaView;
    @FXML
    public Slider slider;
    @FXML
    public ImageView playPauseBtn;
    @FXML
    public ImageView playPauseVid;
    @FXML
    public ImageView resetBtn;
    @FXML
    public Label timeDuration;

    public Media media;
    public MediaPlayer mediaPlayer;
    public boolean isPlayed = false;
    public Timeline timeline;
    public boolean videoEnded = false;

    public void playPauseDuration(){
        if ( timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            playPauseVid.setVisible(false);
            timeline = null;
        }));

        timeline.play();
    }

    @FXML
    private void sliderPressed()  {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }

    public void vidControl() {
        if (videoEnded) {
            resetMedia();
        }
        else {
            playMedia();
        }
    }

    public void playMedia() {
        if (!isPlayed) {
            InputStream inputStream = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/pause.png");
            Image pause = new Image(inputStream);
            playPauseBtn.setImage(pause);
            playPauseVid.setImage(pause);
            playPauseVid.setVisible(true);
            playPauseDuration();
            mediaPlayer.play();
            isPlayed = true;
        } else {
            InputStream inputStream = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/play.png");
            Image play = new Image(inputStream);
            playPauseBtn.setImage(play);
            playPauseVid.setImage(play);
            playPauseVid.setVisible(true);
            playPauseDuration();
            mediaPlayer.pause();
            isPlayed = false;
        }
    }

    public void resetMedia() {
        mediaPlayer.seek(Duration.seconds(0.0));
        mediaPlayer.play();
    }

    public void vidHoverEnt(MouseEvent event) {
        playPauseVid.setVisible(true);
    }

    public void vidHoverExt(MouseEvent event) {
        playPauseVid.setVisible(false);
    }


    //TEXT MANUAL

    //DASHBOARD

    public void updateManualDashboard() {
        Text dashboard1 = new Text("Click the ");
        Text dashboard2 = new Text("“Dashboard” ");
        dashboard2.setStyle("-fx-font-weight: bold;");
        Text dashboard3 = new Text("button if you want to view the ");
        Text dashboard4 = new Text("“Best Seller” ");
        dashboard4.setStyle("-fx-font-weight: bold;");
        Text dashboard5 = new Text("of your shop and the statistics of the whole summary of:\n\n");

        Text dashboard6 = new Text("""
                TOTAL REVENUE
                TOTAL CUSTOMER
                TOTAL ORDER
                TOP SELLING MENU FOOD CATEGORIES
                """);
        dashboard6.setStyle("-fx-font-weight: bold;");

        dashboardText1.getChildren().addAll(dashboard1,dashboard2,dashboard3,dashboard4,dashboard5,dashboard6);
        dashboardText1.setStyle("-fx-font-size: 16;");
    }

    //MENU

    public void howToAddProduct(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Click ");
        Text menu12 = new Text("Add Products to Get Started");
        menu12.setStyle("-fx-underline: true; -fx-fill: blue;");
        Text menu13 = new Text(". It only appears on the menu page if you don't have any products added. \nOr go to ");
        Text menu14 = new Text('"' + "Settings" + '"');
        menu14.setStyle("-fx-font-weight: bold;");
        Text menu15 = new Text(" and click ");
        Text menu16 = new Text('"' + "Edit Products" + '"');
        menu16.setStyle("-fx-font-weight: bold;");
        Text menu17 = new Text(".");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Click the ");
        Text menu22 = new Text('"' + "Add Product" + '"');
        menu22.setStyle("-fx-font-weight: bold;");
        Text menu23 = new Text(" button below.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Choose which category the product belongs to.");

        Text note = new Text("\nNote: ");
        note.setStyle("-fx-font-weight: bold;");
        Text note1 = new Text("You cannot proceed to add ");
        Text note2 = new Text("Name, Description, Price, and Photo ");
        note2.setStyle("-fx-font-weight: bold;");
        Text note3 = new Text("of the product if you don’t choose which category the product belongs to.");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Add the ");
        Text menu42 = new Text("Name, Description, Price, and Photo ");
        menu42.setStyle("-fx-font-weight: bold");
        Text menu43 = new Text("of the product.");

        Text num5 = new Text("\n\n5. ");
        Text menu51 = new Text("Add Sizes ");
        menu51.setStyle("-fx-font-weight: bold;");
        Text menu52 = new Text("(Milk Tea/Coolers), ");
        Text menu53 = new Text("Liquid Base ");
        menu53.setStyle("-fx-font-weight: bold;");
        Text menu54 = new Text("(Milk Tea/Coolers), ");
        Text menu55 = new Text("Add-ons ");
        menu55.setStyle("-fx-font-weight: bold;");
        Text menu56 = new Text("(Milk Tea/Coolers), ");
        Text menu57 = new Text("Temperature ");
        menu57.setStyle("-fx-font-weight: bold;");
        Text menu58 = new Text("(Coffee), ");
        Text menu59 = new Text("and their ");
        Text menu510 = new Text("Price ");
        menu510.setStyle("-fx-font-weight: bold;");
        Text menu511 = new Text("(All Categories), ");

        Text num6 = new Text("\n\n6. ");
        Text menu61 = new Text("Click ");
        Text menu62 = new Text('"' + "ADD" + '"');
        menu62.setStyle("-fx-font-weight: bold;");
        Text menu63 = new Text(" button to add product.");

        menuText1.getChildren().addAll(num1,menu11,menu12,menu13,menu14,menu15,menu16,menu17,
                num2,menu21,menu22,menu23,
                num3,menu31,
                note, note1,note2,note3,
                num4,menu41,menu42,menu43,
                num5,menu51,menu52,menu53,menu54,menu55,menu56,menu57,menu58,menu59,menu510,menu511,
                num6,menu61,menu62,menu63);
        menuText1.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToEditTheExistingProduct() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("On the table, ");
        Text menu22 = new Text("double-click ");
        menu22.setStyle("-fx-font-weight: bold;");
        Text menu23 = new Text("the product you want to edit.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("When you are done editing the product, click the ");
        Text menu32 = new Text("“EDIT” ");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("button.");

        menuText2.getChildren().addAll(num1, menu11, menu12, menu13,menu14,
                num2,menu21,menu22,menu23,
                num3,menu31, menu32,menu33);
        menuText2.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToDisableAndEnableProducts() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("  On the table, there is a Box on the column ");
        Text menu22 = new Text("“Available”.");
        menu22.setStyle("-fx-font-weight: bold;");

        Text menu31 = new Text("\n\n• Select the box of the product that you want to  ");
        Text menu32 = new Text("DISABLE.");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("\n• If you want to ");
        Text menu34 = new Text("ENABLE ");
        menu34.setStyle("-fx-font-weight: bold;");
        Text menu35 = new Text("it, unselect the box of the product disabled.");

        menuText3.getChildren().addAll(num1,menu11,menu12,menu13,menu14,
                num2,menu21,menu22,
                menu31,menu32,menu33,menu34,menu35);
        menuText3.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToDeleteProduct(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Select the product/s you want to delete.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Click the ");
        Text menu32 = new Text("“Delete” ");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("button on the bottom left of the panel.");

        menuText4.getChildren().addAll(num1,menu11,menu12,menu13,menu14,
                num2,menu21,
                num3,menu31,menu32,menu33);
        menuText4.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToMakeOrder(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Click the product.");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Choose the ");
        Text menu22 = new Text("Sizes ");
        menu22.setStyle("-fx-font-weight: bold;");
        Text menu23 = new Text("(Milk Tea/ Coolers, ");
        Text menu24 = new Text("Liquid Base ");
        menu24.setStyle("-fx-font-weight: bold;");
        Text menu25 = new Text("(Milk Tea/ Coolers, ");
        Text menu26 = new Text("Add-ons ");
        menu26.setStyle("-fx-font-weight: bold;");
        Text menu27 = new Text("(Milk Tea/Coolers, ");
        Text menu28 = new Text("Temperature ");
        menu28.setStyle("-fx-font-weight: bold;");
        Text menu29 = new Text("(Coffee) of the product.");

        Text note = new Text("\nNote: ");
        note.setStyle("-fx-font-weight: bold;");
        Text note1 = new Text(" The product/s in the ");
        Text note2 = new Text("Ice Candy Cups ");
        note.setStyle("-fx-font-weight: bold;");
        Text note3 = new Text("and ");
        Text note4 = new Text("Appetizer Category ");
        note4.setStyle("-fx-font-weight: bold;");
        Text note5 = new Text("have no choices like the other categories mentioned in number 2.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Click the ");
        Text menu32 = new Text("“Add Order” ");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("button.");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("On the right panel, there is a ");
        Text menu42 = new Text("“Bills” ");
        menu42.setStyle("-fx-font-weight: bold;");
        Text menu43 = new Text("section where you can see the order/s queue of your customer.");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("Increase the number of the quantity of the product by clicking the ");
        Text menu52 = new Text("Plus sign (+) ");
        menu52.setStyle("-fx-font-weight: bold;");
        Text menu53 = new Text("if the customer orders the same product twice or more.");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Enter the amount paid by the customer.");

        Text num7 = new Text("\n\n7. ");
        num7.setStyle("-fx-font-weight: bold;");
        Text menu71 = new Text("Choose which ");
        Text menu72 = new Text("Payment Method ");
        menu72.setStyle("-fx-font-weight: bold;");
        Text menu73 = new Text("does the customer prefers to pay with.");

        Text num8 = new Text("\n\n8. ");
        num8.setStyle("-fx-font-weight: bold;");
        Text menu81 = new Text("Click ");
        Text menu82 = new Text("“Pay” ");
        menu82.setStyle("-fx-font-weight: bold;");
        Text menu83 = new Text("button.");

        menuText5.getChildren().addAll(num1,menu11,
                num2,menu21,menu22,menu23,menu24,menu25,menu26,menu27,menu28,menu29,
                note,note1,note2,note3,note4,note5,
                num3,menu31,menu32,menu33,
                num4,menu41,menu42,menu43,
                num5,menu51,menu52,menu53,
                num6,menu61,
                num7,menu71,menu72,menu73,
                num8,menu81,menu82,menu83);
        menuText5.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToAddCustomerName(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("On the right there is a panel, on the top of that panel there is a ");
        Text menu12 = new Text("”Customer Name:“.");
        menu12.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Click on the box and type the name of the customer.");

        menuText6.getChildren().addAll(num1,menu11,menu12,
                num2,menu21);
        menuText6.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToUsePaymentMethod() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("On the right, there is a ");
        Text menu12 = new Text("“Bills” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("panel.");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("On the bottom there is a ");
        Text menu22 = new Text("“Payment Method”.");
        menu22.setStyle("-fx-font-weight: bold;");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Choose whether the customer prefers ");
        Text menu32 = new Text("Cash ");
        menu32.setStyle("-fx-font-weight: bold;");
        Text menu33 = new Text("or ");
        Text menu34 = new Text("Gcash ");
        menu34.setStyle("-fx-font-weight: bold;");
        Text menu35 = new Text("as a payment method.");

        menuText7.getChildren().addAll(num1,menu11,menu12,menu13,
                num2,menu21,menu22,
                num3,menu31,menu32,menu33,menu34,menu35);
        menuText7.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    private void windows() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        num1.setTextAlignment(TextAlignment.LEFT);
        Text menu11 = new Text("Press ");
        menu11.setTextAlignment(TextAlignment.LEFT);
        Text menu12 = new Text("(   Windows Key");
        menu12.setStyle("-fx-font-weight: bold;");
        menu12.setTextAlignment(TextAlignment.LEFT);
        ImageView windowsKey = new ImageView();
        InputStream inputStream = getClass().getResourceAsStream("/com/example/accordion/photos/windows-key-logo.png");
        Image windowsLogo = new Image(inputStream);
        windowsKey.setImage(windowsLogo);
        Text menu13 = new Text("+   R   ).");
        menu13.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Run ");
        menu21.setStyle("-fx-font-weight: bold;");
        Text menu22 = new Text("dialog box will open, then type ");
        Text menu23 = new Text("“%APPDATA%”.");
        menu23.setStyle("-fx-font-weight: bold;");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Find the folder named ");
        Text menu32 = new Text("“POS_Tea”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Copy the ");
        Text menu42 = new Text("POS_Tea ");
        menu42.setStyle("-fx-font-weight: bold;");
        Text menu43 = new Text("folder to your ");
        Text menu44 = new Text("flashdrive.");
        menu44.setStyle("-fx-font-weight: bold;");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("If you are going to transfer the ");
        Text menu52 = new Text("POS_Tea ");
        menu52.setStyle("-fx-font-weight: bold;");
        Text menu53 = new Text("folder to different device, repeat the ");
        Text menu54 = new Text("step 1.");
        menu54.setStyle("-fx-font-weight: bold;");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Copy the ");
        Text menu62 = new Text("POS_Tea ");
        menu62.setStyle("-fx-font-weight: bold;");
        Text menu63 = new Text("folder to your ");
        Text menu64 = new Text("flashdrive ");
        menu64.setStyle("-fx-font-weight: bold;");
        Text menu65 = new Text("the then paste it.");

        menuHbox1.getChildren().addAll(num1,menu11,menu12,windowsKey,menu13);
        menuHbox1.setStyle("-fx-font-size: 16; -fx-cursor: text; -fx-pref-height: 10;");
        menuHbox1.setAlignment(Pos.CENTER_LEFT);
        menuText10.getChildren().addAll(num2,menu21,menu22,menu23,
                num3,menu31,menu32,
                num4,menu41,menu42,menu43,menu44,
                num5,menu51,menu52,menu53,menu54,
                num6,menu61,menu62,menu63,menu64,menu65);
        menuText10.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void macOS() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Press ");
        Text menu12 = new Text("(    Command + Spacebar   ).");
        menu12.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Spotlight Search ");
        menu21.setStyle("-fx-font-weight: bold;");
        Text menu22 = new Text("will open, then type ");
        Text menu23 = new Text("“~/Library/Application Support/”.");
        menu23.setStyle("-fx-font-weight: bold;");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Find the folder named ");
        Text menu32 = new Text("“POS_Tea”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Copy the ");
        Text menu42 = new Text("POS_Tea  ");
        menu42.setStyle("-fx-font-weight: bold;");
        Text menu43 = new Text("folder to your ");
        Text menu44 = new Text("flashdrive.");
        menu44.setStyle("-fx-font-weight: bold;");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("If you are going to transfer the ");
        Text menu52 = new Text("POS_Tea ");
        menu52.setStyle("-fx-font-weight: bold;");
        Text menu53 = new Text("folder to different device, repeat the ");
        Text menu54 = new Text("step 1.");
        menu54.setStyle("-fx-font-weight: bold;");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Copy the ");
        Text menu62 = new Text("POS_Tea ");
        menu62.setStyle("-fx-font-weight: bold;");
        Text menu63 = new Text("folder to your ");
        Text menu64 = new Text("flashdrive ");
        menu64.setStyle("-fx-font-weight: bold;");
        Text menu65 = new Text("the then paste it.");

        menuText11.getChildren().addAll(num1,menu11,menu12,
                num2,menu21,menu22,menu23,
                num3,menu31,menu32,
                num4,menu41,menu42,menu43,menu44,
                num5,menu51,menu52,menu53,menu54,
                num6,menu61,menu62,menu63,menu64,menu65);
        menuText11.setStyle("-fx-font-size: 16; -fx-cursor: text; -fx-pref-height: 10;");
    }

    public void linux() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Press ");
        Text menu12 = new Text("(    Alt + F2   ).");
        menu12.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("Run Application ");
        menu21.setStyle("-fx-font-weight: bold;");
        Text menu22 = new Text("will open, then type ");
        Text menu23 = new Text("“~/.config/”.");
        menu23.setStyle("-fx-font-weight: bold;");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Find the folder named ");
        Text menu32 = new Text("“POS_Tea”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("Copy the ");
        Text menu42 = new Text("POS_Tea ");
        menu42.setStyle("-fx-font-weight: bold;");
        Text menu43 = new Text("folder to your ");
        Text menu44 = new Text("flashdrive.");
        menu44.setStyle("-fx-font-weight: bold;");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("If you are going to transfer the ");
        Text menu52 = new Text("POS_Tea ");
        menu52.setStyle("-fx-font-weight: bold;");
        Text menu53 = new Text("folder to different device, repeat the ");
        Text menu54 = new Text("step 1.");
        menu54.setStyle("-fx-font-weight: bold;");

        Text num6 = new Text("\n\n6. ");
        num6.setStyle("-fx-font-weight: bold;");
        Text menu61 = new Text("Copy the ");
        Text menu62 = new Text("POS_Tea ");
        menu62.setStyle("-fx-font-weight: bold;");
        Text menu63 = new Text("folder to your ");
        Text menu64 = new Text("flashdrive ");
        menu64.setStyle("-fx-font-weight: bold;");
        Text menu65 = new Text("the then paste it.");

        menuText12.getChildren().addAll(num1,menu11,menu12,
                num2,menu21,menu22,menu23,
                num3,menu31,menu32,
                num4,menu41,menu42,menu43,menu44,
                num5,menu51,menu52,menu53,menu54,
                num6,menu61,menu62,menu63,menu64,menu65);
        menuText12.setStyle("-fx-font-size: 16; -fx-cursor: text; -fx-pref-height: 10;");
    }

    public void howToManuallyTransferLocalDatabase() {
        windows();
        macOS();
        linux();
    }

    public void howToExportMenu() {
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("On the left panel, there is a drop bar.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Click the drop bar and select ");
        Text menu32 = new Text("“Export Menu”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("File Explorer ");
        menu41.setStyle("-fx-font-weight: bold;");
        Text menu42 = new Text("will pop-up.");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("Save the file.");

        menuText8.getChildren().addAll(num1,menu11,menu12,menu13,menu14,
                num2,menu21,
                num3,menu31,menu32,
                num4,menu41,menu42,
                num5, menu51);
        menuText8.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void howToImportMenu(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text menu11 = new Text("Go to ");
        Text menu12 = new Text("“Settings” ");
        menu12.setStyle("-fx-font-weight: bold;");
        Text menu13 = new Text("and click ");
        Text menu14 = new Text("“Edit Products”.");
        menu14.setStyle("-fx-font-weight: bold;");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text menu21 = new Text("On the left panel, there is a drop bar.");

        Text num3 = new Text("\n\n3. ");
        num3.setStyle("-fx-font-weight: bold;");
        Text menu31 = new Text("Click the drop bar and select ");
        Text menu32 = new Text("“Import Menu”.");
        menu32.setStyle("-fx-font-weight: bold;");

        Text num4 = new Text("\n\n4. ");
        num4.setStyle("-fx-font-weight: bold;");
        Text menu41 = new Text("File Explorer ");
        menu41.setStyle("-fx-font-weight: bold;");
        Text menu42 = new Text("wil pop-up.");

        Text num5 = new Text("\n\n5. ");
        num5.setStyle("-fx-font-weight: bold;");
        Text menu51 = new Text("Find the file and select the file to import.");

        Text note = new Text("\nNote: ");
        note.setStyle("-fx-font-weight: bold;");
        Text note1 = new Text("The file must be an ");
        Text note2 = new Text("Excel File.");
        note2.setStyle("-fx-font-weight: bold;");

        menuText9.getChildren().addAll(num1,menu11,menu12,menu13,menu14,
                num2,menu21,
                num3,menu31,menu32,
                num4,menu41,menu42,
                num5, menu51,
                note,note1,note2);
        menuText9.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    public void updateManualMenu() {
        howToAddProduct();
        howToEditTheExistingProduct();
        howToDisableAndEnableProducts();
        howToDeleteProduct();
        howToMakeOrder();
        howToAddCustomerName();
        howToUsePaymentMethod();
        howToManuallyTransferLocalDatabase();
        howToExportMenu();
        howToImportMenu();
    }

    //ORDER QUEUE

    public void updateManualOrderQueue(){
        Text num1 = new Text("1. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text queue11 = new Text("Go to ");
        Text queue12 = new Text("“Menu Page” ");
        queue12.setStyle("-fx-font-weight: bold;");
        Text queue13 = new Text("and add and order.");

        Text num2 = new Text("\n\n2. ");
        num2.setStyle("-fx-font-weight: bold;");
        Text queue21 = new Text(" Make an order.");

        Text num3 = new Text("\n\n3. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text queue31 = new Text("Go to ");
        Text queue32 = new Text("“Order Queue Page” ");
        queue32.setStyle("-fx-font-weight: bold;");
        Text queue33 = new Text("and you will see the order you made.");

        Text num4 = new Text("\n\n4. ");
        num1.setStyle("-fx-font-weight: bold;");
        Text queue41 = new Text("Click ");
        Text queue42 = new Text("“Done” ");
        queue42.setStyle("-fx-font-weight: bold;");
        Text queue43 = new Text("button if the order is done preparing/making.");

        Text note = new Text("\nNote: ");
        note.setStyle("-fx-font-weight: bold;");
        Text note1 = new Text("The ");
        Text note2 = new Text("Order Queue ");
        note2.setStyle("-fx-font-weight: bold;");
        Text note3 = new Text("only serves as a guide for the user when preparing the order. It does not edit the customer's order, which means it cannot delete the order or add an order.");

        orderQueueText1.getChildren().addAll(num1,queue11,queue12,queue13,
                num2,queue21,
                num3,queue31,queue32,queue33,
                num4,queue41,queue42,queue43,
                note,note1,note2,note3);
        orderQueueText1.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    //ORDER HISTORY

    public void updateManualOrderHistory() {
        Text order1 = new Text("Once the order is done, the customer's data/order will be transferred to the Order History.\nThe ");
        Text order2 = new Text("Order History ");
        order2.setStyle("-fx-font-weight: bold;");
        Text order3 = new Text("show the following:\n\n");

        Text order4 = new Text("""
                CUSTOMER
                FOOD CATEGORIES
                PRODUCT NAME
                PRODUCT QUANTITY
                PRODUCT PRICE
                TOTAL PRICE
                AMOUNT PAID
                CHANGE
                MODE OF PAYMENT
                DATE AND TIME\s

                """);
        order4.setStyle("-fx-font-weight: bold;");

        Text order5 = new Text("Other features:");

        Text order6 = new Text("\n\nSORT DATE");
        order6.setStyle("-fx-font-weight: bold;");
        Text order7 = new Text("\nIf you want to sort the date of your customer's transactions, just click ");
        Text order8 = new Text("“Sort Date”");
        order8.setStyle("-fx-font-weight: bold;");
        Text order9 = new Text(", and it will show the following categories:\n\n");
        Text order10 = new Text("""
                NOW
                TODAY
                THIS WEEK
                THIS MONTH
                THIS YEAR\s
                ALL TIME

                """);
        order10.setStyle("-fx-font-weight: bold;");

        Text order11 = new Text("Choose one category, and it will show the results of the history of your customer's transactions.\n\n");

        Text order12 = new Text("SEARCH");
        order12.setStyle("-fx-font-weight: bold;");
        Text order13 = new Text("\nIf you want to know or find the transaction of the customer or the specific product name, use the ");
        Text order14 = new Text("Search Box.");
        order14.setStyle("-fx-font-weight: bold;");

        Text order15 = new Text("\n\nDELETE");
        order15.setStyle("-fx-font-weight: bold;");
        Text order16 = new Text("\nWhen you click the ");
        Text order17 = new Text("“Delete” ");
        order17.setStyle("-fx-font-weight: bold;");
        Text order18 = new Text("button, it will show the records of customer’s transactions history by year.\n\n");

        Text order19 = new Text("Select the year of the transaction you want to delete, and click the ");
        Text order20 = new Text("“Delete Record” ");
        order20.setStyle("-fx-font-weight: bold;");
        Text order21 = new Text("button it will delete the records.");

        orderHistoryText1.getChildren().addAll(order1,order2,order3,order4,order5,order6,order7,order8,order9,order10,order11,order12,order13,order14,order15,order16,order17,order18,order19,order20,order21);
        orderHistoryText1.setStyle("-fx-font-size: 16; -fx-cursor: text;");
    }

    //UPDATE MANUAL

    public void updateManual(){
        updateManualDashboard();
        updateManualMenu();
        updateManualOrderQueue();
        updateManualOrderHistory();

    }
}