package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class OrderQueueReference {
    public static Order orderReference;
    public static boolean orderDone = false;
    public static ObservableList<Order> orderQueueObservableList = FXCollections.observableArrayList();

    public static void clearOrderQueueReferences() {
        orderReference = null;
        orderDone = false;
        orderQueueObservableList.clear();
    }
}
