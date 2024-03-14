package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order;
import com.example.postearevised.Objects.ProductOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class ProductOrderReference {
    public static Order orderReference;
    public static ProductOrder productOrderReference;
    public static boolean isProductOrderAdded = false;


    public static ObservableList<ProductOrder> referenceProductOrderObservableList = FXCollections.observableArrayList();
    public static String referenceCustomerName;
    public static int referenceOrderNumber;
    public static int referenceTotalPrice;
    public static double referenceAmountPaid;
    public static double referenceChange;
    public static String referenceModeOfPayment;
    public static LocalDateTime referenceDateAndTime;
}
