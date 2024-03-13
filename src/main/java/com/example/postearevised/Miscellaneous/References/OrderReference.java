package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.Product;
import com.example.postearevised.Objects.ProductOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class OrderReference {
    public static Order orderReference;
    public static ProductOrder productOrderReference;
    public static boolean isProductOrderAdded = false;
    public static ObservableList<Order> orderObservableList = FXCollections.observableArrayList();
    public static int totalPrice = 0;
}
