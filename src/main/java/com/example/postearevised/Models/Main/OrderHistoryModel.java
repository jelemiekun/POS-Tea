package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.example.postearevised.Miscellaneous.Enums.OrderHistorySortEnum.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.NotificationContents.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.RegexReference.*;

public class OrderHistoryModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setOrderHistoryTable() {
        setComboBox();
        setCellValueFactories();
        setReorderToFalse();
        setReverseItem();
        refreshOrderHistory();
        setTextFieldSearch();
        refreshOrderHistoryTable();
    }

//    public void setOrderHistory() { // unused kasi if clicked yung order history left panel, maiiwanan lang yung nilalaman "AS IS"
//        refreshOrderHistory(); // go to MainModel openSelectedPane() case 4, uncomment yun para magamit to
//        setComboBox(false);
//        mainController.anchorPaneOrderHistory.requestFocus();
//        refreshOrderHistoryTable();
//        setTextFieldSearch();
//        refreshOrderHistory();
//    }

    public void refreshOrderHistory() {
        mainController.tableViewOrderHistoryColDateAndTime.setSortable(true);
        mainController.tableViewOrderHistory.refresh();
        mainController.tableViewOrderHistory.getSortOrder().clear();
        mainController.tableViewOrderHistory.getSortOrder().add(mainController.tableViewOrderHistoryColDateAndTime);
        mainController.tableViewOrderHistoryColDateAndTime.setSortType(TableColumn.SortType.DESCENDING);
        mainController.tableViewOrderHistory.refresh();
        mainController.tableViewOrderHistoryColDateAndTime.setSortable(false);
    }

    public void refreshOrderHistoryBtn() {
        mainController.textFieldOrderHistorySearch.setText("");
        mainController.tableViewOrderHistory.setItems(orderHistoryObservableList);
        mainController.tableViewOrderHistory.refresh();
        mainController.tableViewOrderHistory.getSortOrder().clear();
        mainController.tableViewOrderHistory.getSortOrder().add(mainController.tableViewOrderHistoryColDateAndTime);
        mainController.tableViewOrderHistoryColDateAndTime.setSortType(TableColumn.SortType.DESCENDING);
        mainController.tableViewOrderHistory.refresh();
        mainController.anchorPaneOrderHistory.requestFocus();
    }

    private void setComboBox() {
        mainController.comboBoxOrderHistory.getItems().addAll(orderHistorySortByChoices);
        mainController.comboBoxOrderHistory.setStyle("-fx-font-family: Arial; -fx-font-size: 30px;");

        mainController.comboBoxOrderHistory.setValue(TRANSACTION_ID_ENUM.getTitle());
        getComboBoxValue();
    }

    public void getComboBoxValue() {
        mainController.textFieldOrderHistorySearch.setText("");
        switch (mainController.comboBoxOrderHistory.getValue()) {
            case "Customer Name":
                mainController.textFieldOrderHistorySearch.setPromptText("Customer Name");
                break;
            case "Food Category":
                mainController.textFieldOrderHistorySearch.setPromptText("Food Category");
                break;
            case "Product Name":
                mainController.textFieldOrderHistorySearch.setPromptText("Product Name");
                break;
            case "Day":
                mainController.textFieldOrderHistorySearch.setPromptText("Day");
                break;
            case "Month":
                mainController.textFieldOrderHistorySearch.setPromptText("Month");
                break;
            case "Year":
                mainController.textFieldOrderHistorySearch.setPromptText("Year");
                break;
        }
        mainController.tableViewOrderHistory.setItems(orderHistoryObservableList);

        setTextFieldSearch();
    }

    private void setCellValueFactories() {
        mainController.tableViewOrderHistoryColTransactionID.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        mainController.tableViewOrderHistoryColCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        mainController.tableViewOrderHistoryColProductCategory.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder categories = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                categories.append(productOrder.getProductCategory());

                if (i < productOrders.size() - 1) {
                    categories.append("\n");
                }
            }
            return new SimpleStringProperty(categories.toString());
        });
        mainController.tableViewOrderHistoryColProductName.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder names = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                names.append(productOrder.getProductName());

                if (i < productOrders.size() - 1) {
                    names.append("\n");
                }
            }
            return new SimpleStringProperty(names.toString());
        });
        mainController.tableViewOrderHistoryColQuantity.setCellValueFactory(cellData -> {
            ObservableList<ProductOrder> productOrders = cellData.getValue().getProductOrderObservableList();
            StringBuilder quantities = new StringBuilder();
            for (int i = 0; i < productOrders.size(); i++) {
                ProductOrder productOrder = productOrders.get(i);
                quantities.append(productOrder.getQuantity()).append("x");

                if (i < productOrders.size() - 1) {
                    quantities.append("\n");
                }
            }
            return new SimpleStringProperty(quantities.toString());
        });
        mainController.tableViewOrderHistoryColTotalPrice.setCellValueFactory(cellData -> {
            String totalPrice = "₱ " + cellData.getValue().getTotalPrice();
            return new SimpleStringProperty(totalPrice);
        });
        mainController.tableViewOrderHistoryColDateAndTime.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateAndTime()));

        mainController.tableViewOrderHistoryColDateAndTime.setCellFactory(column -> {
            return new TableCell<Order, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy, hh:mm:ss a", Locale.ENGLISH);
                        String formattedDateTime = item.format(formatter);
                        setText(formattedDateTime);
                    }
                }
            };
        });

    }

    private void setReorderToFalse() {
        mainController.tableViewOrderHistoryColCustomerName.setReorderable(false);
        mainController.tableViewOrderHistoryColProductName.setReorderable(false);
        mainController.tableViewOrderHistoryColQuantity.setReorderable(false);
        mainController.tableViewOrderHistoryColTotalPrice.setReorderable(false);
        mainController.tableViewOrderHistoryColDateAndTime.setReorderable(false);
        mainController.tableViewOrderHistoryColProductCategory.setReorderable(false);
        mainController.tableViewOrderHistoryColTransactionID.setReorderable(false);
    }

    private void setReverseItem() {
        mainController.textFieldOrderHistorySearch.setText("");
        mainController.tableViewOrderHistory.setItems(orderHistoryObservableList);
        mainController.tableViewOrderHistory.refresh();
        mainController.anchorPaneOrderHistory.requestFocus();
        mainController.tableViewOrderHistory.getSortOrder().clear();
        mainController.tableViewOrderHistory.getSortOrder().add(mainController.tableViewOrderHistoryColDateAndTime);
        mainController.tableViewOrderHistoryColDateAndTime.setSortType(TableColumn.SortType.DESCENDING);
        mainController.tableViewOrderHistory.sort();
    }

    public void setTextFieldSearch() {
        mainController.textFieldOrderHistorySearch.setText("");
        String searchBy = mainController.comboBoxOrderHistory.getValue();

        if (searchBy.equals(CUSTOMER_NAME_ENUM.getTitle()) || searchBy.equals(FOOD_CATEGORY_ENUM.getTitle()) ||
                searchBy.equals(PRODUCT_NAME_ENUM.getTitle()) || searchBy.equals(MONTH_ENUM.getTitle())) {

            mainController.textFieldOrderHistorySearch.textProperty().removeListener(mainController.textFieldChangeListenerDigitsOnly);
            mainController.textFieldOrderHistorySearch.textProperty().removeListener(mainController.textFieldChangeListenerCharactersOnly);
            mainController.textFieldOrderHistorySearch.textProperty().addListener(mainController.textFieldChangeListenerCharactersOnly);

        } else if (searchBy.equals(DAY_ENUM.getTitle()) || searchBy.equals(YEAR_ENUM.getTitle()) || searchBy.equals(TRANSACTION_ID_ENUM.getTitle())){

            mainController.textFieldOrderHistorySearch.textProperty().removeListener(mainController.textFieldChangeListenerDigitsOnly);
            mainController.textFieldOrderHistorySearch.textProperty().removeListener(mainController.textFieldChangeListenerCharactersOnly);
            mainController.textFieldOrderHistorySearch.textProperty().addListener(mainController.textFieldChangeListenerDigitsOnly);

        }

    }

    public void searchTheText(String stringToSearch) {
        if (!orderHistoryObservableList.isEmpty()) {
            if (!stringToSearch.isBlank()) {
                ObservableList<Order> filteredOrders = FXCollections.observableArrayList();
                for (Order order : orderHistoryObservableList) {
                    switch (mainController.comboBoxOrderHistory.getValue()) {
                        case "Transaction ID":
                            if (order.getTransactionID().contains(stringToSearch.toLowerCase())) {
                                filteredOrders.add(order);
                            }
                            break;
                        case "Customer Name":
                            if (order.getCustomerName().toLowerCase().contains(stringToSearch.toLowerCase())) {
                                filteredOrders.add(order);
                            }
                            break;
                        case "Food Category":
                            for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                                if (productOrder.getProductCategory().toLowerCase().contains(stringToSearch.toLowerCase())) {
                                    filteredOrders.add(order);
                                    break;
                                }
                            }
                            break;
                        case "Product Name":
                            for (ProductOrder productOrder : order.getProductOrderObservableList()) {
                                if (productOrder.getProductName().toLowerCase().contains(stringToSearch.toLowerCase())) {
                                    filteredOrders.add(order);
                                    break;
                                }
                            }
                            break;
                        case "Day":
                            DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                            String dayFormattedDate = order.getDateAndTime().format(dayFormatter);
                            String[] dayParts = dayFormattedDate.split(" ");
                            String day = dayParts[1].replace(",", "");
                            if (day.contains(stringToSearch)) {
                                filteredOrders.add(order);
                            }
                            break;
                        case "Month":
                            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                            String monthFormattedDate = order.getDateAndTime().format(monthFormatter);
                            String[] monthParts = monthFormattedDate.split(" ");
                            String month = monthParts[0].toLowerCase();
                            String search = stringToSearch.toLowerCase();
                            if (month.contains(search)) {
                                filteredOrders.add(order);
                            }
                            break;
                        case "Year":
                            DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                            String yearFormattedDate = order.getDateAndTime().format(yearFormatter);
                            String[] yearParts = yearFormattedDate.split(", ");
                            String year = yearParts[1];
                            if (year.contains(stringToSearch)) {
                                filteredOrders.add(order);
                            }
                            break;
                    }
                }

                Platform.runLater(() -> {
                    if (filteredOrders.isEmpty()) {
                        mainController.tableViewOrderHistory.setItems(FXCollections.observableArrayList());
                        Label placeholderLabel = new Label("No matching records found.");
                        placeholderLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 28));
                        placeholderLabel.setAlignment(Pos.CENTER);
                        placeholderLabel.setContentDisplay(ContentDisplay.CENTER);
                        placeholderLabel.setTextAlignment(TextAlignment.CENTER);
                        mainController.tableViewOrderHistory.setPlaceholder(placeholderLabel);
                    } else {
                        mainController.tableViewOrderHistory.setItems(filteredOrders);
                    }
                });
            } else {
                mainController.tableViewOrderHistory.setItems(orderHistoryObservableList);
            }
        }
    }





    public void refreshOrderHistoryTable() {
        if (orderHistoryObservableList.isEmpty()) {
            Label placeholderLabel = new Label("Your order history is empty.\nMake a new order to see transactions.");
            placeholderLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 28));
            placeholderLabel.setAlignment(Pos.CENTER);
            placeholderLabel.setContentDisplay(ContentDisplay.CENTER);
            placeholderLabel.setTextAlignment(TextAlignment.CENTER);
            mainController.tableViewOrderHistory.setPlaceholder(placeholderLabel);
        }
        //setReverseItem(); wag to i uncomment, mag w-wierd as fuck
    }

    public void orderHistoryDeleteBin() {
        openDeleteHistoryFXML();
    }

    private void openDeleteHistoryFXML() {
        int holdCurrentOrderHistorySize = orderHistoryObservableList.size();

        mainController.mainModel.showRectangleModal();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(DELETE_HISTORY.getURL()));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }

        Stage newStage = new Stage();
        newStage.setTitle(DELETE_HISTORY.getTITLE());
        newStage.setScene(new Scene(root));
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setResizable(false);

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainController.anchorPaneSettings.getScene().getWindow());

        newStage.showAndWait();
        mainController.mainModel.hideRectangleModal();

        if (holdCurrentOrderHistorySize != orderHistoryObservableList.size()) {
            setDeleteHistorySuccess();
            mainController.mainModel.showNotification();
        }

        refreshOrderHistoryTable();
        refreshOrderHistoryBtn();
    }

    public void openOrderDetails() {
        selectedOrderDetails = mainController.tableViewOrderHistory.getSelectionModel().getSelectedItem();
        if (selectedOrderDetails != null) {
            mainController.mainModel.showRectangleModal();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ORDER_DETAILS.getURL()));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                errorMessage = e.getMessage();
                logError(false);
            }
            Stage newStage = new Stage();

            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(mainController.anchorPaneMenu.getScene().getWindow());

            newStage.setTitle(ORDER_DETAILS.getTITLE());
            newStage.setResizable(false);
            newStage.getIcons().add(SYSTEM_LOGO);
            newStage.setScene(new Scene(root));
            newStage.showAndWait();

            mainController.mainModel.hideRectangleModal();

            if (!orderHistoryObservableList.isEmpty())
                mainController.tableViewOrderHistory.getSelectionModel().clearSelection();
        }
    }
}
