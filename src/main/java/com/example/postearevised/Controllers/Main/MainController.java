package com.example.postearevised.Controllers.Main;

import com.example.postearevised.Models.Main.*;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Products.Product;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
                anchorPaneLeftPanel.setVisible(true);
                anchorPaneDashboard.setVisible(true);

                mainModel.setAccountDetails();
                mainModel.setDropShadow();
                mainModel.createAndStartDaemonThreadForDateAndTime();
                mainModel.setMainMenuIconSelected();
                mainModel.setAnchorPane();
                mainModel.setToolTips();
                mainModel.setComboBoxUsers();
                mainModel.setTermsAndConditionsLastUpdated();

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

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            if (!loadingCompleted) {
                System.out.println("Loading not completed after 10 seconds.");
            }
        }, 10, TimeUnit.SECONDS);
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

    private boolean loadingCompleted = false;


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
        loadingCompleted = true;

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

    public boolean loggedIn = true;
    public int selectUserCounter = 0;
    public int leftPanelCounter = 1;
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
        leftPanelCounter++;

        if (leftPanelCounter % 2 == 0)
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
    public void imageViewMenuAllClicked() {
        menuModel.switchCategory(ALL_PRODUCT_CATEGORY_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuAllTouched() {
        menuModel.switchCategory(ALL_PRODUCT_CATEGORY_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuMilkTeaClicked() {
        menuModel.switchCategory(MILK_TEA_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuMilkTeaTouched() {
        menuModel.switchCategory(MILK_TEA_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuCoolersClicked() {
        menuModel.switchCategory(COOLERS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuCoolersTouched() {
        menuModel.switchCategory(COOLERS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuCoffeeClicked() {
        menuModel.switchCategory(COFFEE_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuCoffeeTouched() {
        menuModel.switchCategory(COFFEE_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuIceCandyCupsClicked() {
        menuModel.switchCategory(ICE_CANDY_CUPS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuIceCandyCupsTouched() {
        menuModel.switchCategory(ICE_CANDY_CUPS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuAppetizersClicked() {
        menuModel.switchCategory(APPETIZERS_ENUM.getNumber());
    }

    @FXML
    public void imageViewMenuAppetizersTouched() {
        menuModel.switchCategory(APPETIZERS_ENUM.getNumber());
    }

    @FXML
    public void menuPaymentCancelClicked() {
        menuModel.orderCancelledOrAddedToQueue(true);
    }

    @FXML
    public void menuPaymentCancelTouched() {
        menuModel.orderCancelledOrAddedToQueue(true);
    }

    @FXML
    public void menuPaymentPayClicked() {
        menuModel.payClicked();
    }

    @FXML
    public void menuPaymentPayTouched() {
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
    public ImageView btnOrderHistoryDelete;
    @FXML
    public Label orderHistoryLabelTotalResultCounter;
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

        orderHistoryModel.setTotalResult();
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
    public void btnHistoryTableRefreshClicked() {
        orderHistoryModel.refreshOrderHistoryBtn();
    }

    @FXML
    public void btnHistoryTableRefreshTouched() {
        orderHistoryModel.refreshOrderHistoryBtn();
    }

    @FXML
    void textFieldOrderHistorySearchTyping() {

    }

    @FXML
    void comboBoxOrderHistoryOnAction() {
        orderHistoryModel.getComboBoxValue();
    }

    @FXML
    void btnOrderHistoryDeleteClicked() {
        orderHistoryModel.orderHistoryDeleteBin();
    }

    @FXML
    void btnOrderHistoryDeleteTouched() {
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
    public AnchorPane anchorPaneSettingsEditProductsInner;
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
    public boolean passwordToolTipClicked = false;


    public boolean isWeakPassword;
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
    public ImageView passwordToolTipImage;
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
    public Label labelSettingsFillUpThisForm12;
    @FXML
    public Label labelSettingsOnlyAdmin;
    @FXML
    public Label labelSettingsOnlyAdmin1;
    @FXML
    public Label labelMiddleNameOptional;
    public Label labelNewUserPassword;
    public Label labelNewUserPasswordImportant;


    @FXML
    public ImageView imageHideShowNewPasswordAccountSettings;
    @FXML
    public ImageView imageHideShowConfirmNewPasswordAccountSettings;

    @FXML
    void passwordToolTipClickedTouched() {
        passwordToolTipClicked = !passwordToolTipClicked;
        passwordToolTipImage.setVisible(passwordToolTipClicked);
    }

    @FXML
    void passwordToolTipEntered() {
        if (!passwordToolTipClicked)
            passwordToolTipImage.setVisible(true);
    }

    @FXML
    void passwordToolTipExited() {
        if (!passwordToolTipClicked)
            passwordToolTipImage.setVisible(false);
    }

    @FXML
    void onScrollTouchMoved() {
        settingsModel.hidePasswordToolTipImage();
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
    void settingsAccountNewPasswordTyping() {
        settingsModel.passwordIndicator();
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
    //TODO lagyan search sa edit products, wala nang filter by, rekta search by product name na agad
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
    public void settingsDeleteProductClicked() {
        if (!orderIsOngoing)
            settingsModel.deleteSelectedProductsProcess();
    }

    @FXML
    public void settingsDeleteProductTouched() {
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
    public void btnProductTableRefreshTouched() {
        settingsModel.refreshProductTable();
    }

    @FXML
    public void btnProductTableRefreshClicked() {
        settingsModel.refreshProductTable();
    }

    @FXML
    void importExportOnAction() {
        settingsModel.comboBoxValueSelected();
    }

    /**
     * Settings - System Manual
     */
    @FXML
    public AnchorPane anchorPaneSystemManualVideo;
    @FXML
    public AnchorPane anchorPaneSettingsSystemManualInner;
    @FXML
    public TextFlow guideText1;
    @FXML
    public TextFlow dashboardText1;
    @FXML
    public TextFlow editProductsText1;
    @FXML
    public TextFlow editProductsText2;
    @FXML
    public TextFlow editProductsText3;
    @FXML
    public TextFlow editProductsText4;
    @FXML
    public TextFlow menuText5;
    @FXML
    public TextFlow menuText7;
    @FXML
    public TextFlow editProductsText8;
    @FXML
    public TextFlow editProductsText9;
    @FXML
    public TextFlow editProductsText10;
    @FXML
    public TextFlow editProductsText11;
    @FXML
    public TextFlow editProductsText12;
    @FXML
    public TextFlow orderQueueText1;
    @FXML
    public TextFlow orderHistoryText1;
    @FXML
    public TextFlow orderHistoryText2;
    @FXML
    public TextFlow orderHistoryText3;
    @FXML
    public TextFlow accountText1;
    @FXML
    public TextFlow accountText2;
    @FXML
    public TextFlow accountText3;
    @FXML
    public TextFlow accountText4;
    @FXML
    public TextFlow accountText5;
    @FXML
    public TextFlow accountText6;
    @FXML
    public TextFlow accountText7;
    @FXML
    public TextFlow appearanceText1;
    @FXML
    public TextFlow appearanceText2;

    @FXML
    public HBox menuHBox1;
    @FXML
    public MediaView mediaView;
    @FXML
    public Slider slider;
    @FXML
    public Slider volumeSlider;
    @FXML
    public ImageView playPauseBtn;
    @FXML
    public ImageView playPauseVid;
    @FXML
    public ImageView resetBtn;
    @FXML
    public ImageView volumeBtn;
    @FXML
    public Label timeDuration;

    public Media media;
    public MediaPlayer mediaPlayer;
    public boolean isPlayed = false;
    public Timeline timeline;
    public boolean videoEnded = false;


    @FXML
    private void sliderPressed()  {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }

    @FXML
    public void playMedia() {
        if (mediaPlayer != null) {
            if (!isPlayed) {
                InputStream inputStream = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/pause.png");
                Image pause = new Image(Objects.requireNonNull(inputStream));
                playPauseBtn.setImage(pause);
                playPauseVid.setImage(pause);
                playPauseVid.setVisible(true);
                settingsModel.playPauseDuration();
                mediaPlayer.play();
                isPlayed = true;
            } else {
                InputStream inputStream = getClass().getResourceAsStream("/com/example/postearevised/Medias/System Manual/play.png");
                Image play = new Image(Objects.requireNonNull(inputStream));
                playPauseBtn.setImage(play);
                playPauseVid.setImage(play);
                playPauseVid.setVisible(true);
                settingsModel.playPauseDuration();
                mediaPlayer.pause();
                isPlayed = false;
            }
        }
    }

    @FXML
    public void resetMedia() {
        mediaPlayer.seek(Duration.seconds(0.0));
        Platform.runLater(() -> {
            isPlayed = false;
            playMedia();
            isPlayed = false;
        });
    }

    @FXML
    public void vidHoverEnt() {
        playPauseVid.setVisible(true);
    }

    @FXML
    public void vidHoverExt() {
        playPauseVid.setVisible(false);
    }

    @FXML
    public void vidControl() {
        if (videoEnded) {
            resetMedia();
        }
        else {
            playMedia();
        }
    }

    @FXML
    public void anchorPaneOpenInPDFPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            anchorPaneOpenInPDFClickedTouched();
    }

    @FXML
    public void anchorPaneOpenInPDFClickedTouched() {
        settingsModel.openInPDF();
    }

    @FXML
    public void anchorPaneRetakeTourPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            anchorPaneRetakeTourClickedTouched();
    }

    @FXML
    public void anchorPaneRetakeTourClickedTouched() {
        mainModel.startTour();
    }

    @FXML
    void labelContactADeveloperPressedEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            labelContactADeveloperClickedTouched();
    }

    @FXML
    void labelContactADeveloperClickedTouched() {
        settingsModel.openContactDeveloper();
    }

    /**
     * Settings - Terms And Conditions
     */

    @FXML
    public Label labelLastUpdated;
}