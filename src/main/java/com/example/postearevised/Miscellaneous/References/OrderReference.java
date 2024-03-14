package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.Product;
import com.example.postearevised.Objects.ProductOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class OrderReference {
    public static Order orderReference;
    public static ProductOrder productOrderReference;
    public static boolean isProductOrderAdded = false;
    public static ObservableList<Order> orderObservableList;


    public static ObservableList<ProductOrder> referenceProductOrderObservableList = FXCollections.observableArrayList();
    public static String referenceCustomerName;
    public static int referenceOrderNumber;
    public static double referenceTotalPrice;
    public static double referenceAmountPaid;
    public static double referenceChange;
    public static String referenceModeOfPayment;
    public static LocalDateTime referenceDateAndTime;
}
