package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.example.postearevised.Miscellaneous.Enums.OrderHistorySortEnum.*;

public class OrderHistoryReference {
    public static Order selectedOrderDetails;
    public static ObservableList<Order> orderHistoryObservableList = FXCollections.observableArrayList();
    public static final ObservableList<String> orderHistorySortByChoices = FXCollections.observableArrayList(
            CUSTOMER_NAME_ENUM.getTitle(),
            FOOD_CATEGORY_ENUM.getTitle(),
            PRODUCT_NAME_ENUM.getTitle(),
            PRODUCT_QUANTITY_ENUM.getTitle(),
            PRODUCT_TOTAL_PRICE_ENUM.getTitle(),
            DATE_AND_TIME_ENUM.getTitle());

    public static void clearOrderHistoryReferences() {
        orderHistoryObservableList.clear();
    }
}
