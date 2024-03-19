package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.example.postearevised.Miscellaneous.Enums.OrderHistorySortEnum.*;

public class OrderHistoryReference {
    public static ObservableList<Order> orderHistoryObservableList = FXCollections.observableArrayList();
    public static final ObservableList<String> orderHistorySortByChoices = FXCollections.observableArrayList(
            TODAY_ENUM.getTitle(),
            THIS_WEEK_ENUM.getTitle(),
            THIS_MONTH_ENUM.getTitle(),
            THIS_YEAR_ENUM.getTitle(),
            ALL_TIME_ENUM.getTitle());
}
