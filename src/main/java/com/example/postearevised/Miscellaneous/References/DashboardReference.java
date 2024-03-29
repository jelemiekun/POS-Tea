package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class DashboardReference {
    public static int referenceTotalRevenue;
    public static int referenceTotalCustomer;
    public static int referenceTotalOrder;

    public static int referenceMilkTeaCounter;
    public static int referenceCoolersCounter;
    public static int referenceCoffeeCounter;
    public static int referenceIceCandyCupsCounter;
    public static int referenceAppetizerCounter;

    public static List<ProductOrder> topTenProducts = new ArrayList<>();

    public static void clearDashBoardReferences() {
        topTenProducts.clear();
    }

    public static ObservableList<String> firstChoiceBoxObservableList = FXCollections.observableArrayList(
            "Daily", "Monthly", "Annually"
    );

    public static ObservableList<String> secondChoiceBoxObservableList = FXCollections.observableArrayList();
    public static ObservableList<String> thirdChoiceBoxObservableList = FXCollections.observableArrayList();
    public static ObservableList<String> fourthChoiceBoxObservableList = FXCollections.observableArrayList();


    public static ObservableList<Order> dashboardOrderObservableListReference = FXCollections.observableArrayList();


    public static String[] dashboardGraphColors = {
            "#0000FF",  // Blue
            "#FFFF00",  // Yellow
            "#FFA500",  // Orange
            "#008000",  // Green
            "#FF0000"   // Red
    };

}
