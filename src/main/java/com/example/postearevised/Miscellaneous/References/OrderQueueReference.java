package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderQueueReference {
    public static Order orderReference;
    public static int orderNumberReference = 1;
    public static ObservableList<Order> orderQueueObservableList = FXCollections.observableArrayList();
}