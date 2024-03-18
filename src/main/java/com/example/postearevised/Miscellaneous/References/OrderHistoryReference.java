package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Deque;
import java.util.LinkedList;

public class OrderHistoryReference {
    public static ObservableList<Order> orderHistoryObservableList = FXCollections.observableArrayList();

    public static Deque<Order> orderHistoryDeque = new LinkedList<>();
}
