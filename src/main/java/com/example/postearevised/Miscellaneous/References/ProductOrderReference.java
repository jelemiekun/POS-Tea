package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.ProductOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class ProductOrderReference {
    public static ProductOrder referenceProductOrder;
    public static boolean isProductOrderAdded = false;

    public static ObservableList<ProductOrder> referenceProductOrderObservableList = FXCollections.observableArrayList();
    public static ObservableList<ProductOrder> synchronizedReferenceProductORderObservableList = FXCollections.synchronizedObservableList(referenceProductOrderObservableList);

    public static String referenceCustomerName = "";
    public static int referenceOrderNumber;
    public static int referenceTotalPrice;
    public static int referenceAmountPaid;
    public static int referenceChange;
    public static String referenceModeOfPayment = "";
    public static LocalDateTime referenceDateAndTime;

    public static void clearProductOrderReferences() {
        referenceProductOrder = null;
        isProductOrderAdded = false;
        referenceProductOrderObservableList.clear();
        synchronizedReferenceProductORderObservableList.clear();
        referenceCustomerName = "";
        referenceTotalPrice = 0;
        referenceAmountPaid = 0;
        referenceChange = 0;
        referenceModeOfPayment = "";
        referenceDateAndTime = null;
    }
}
