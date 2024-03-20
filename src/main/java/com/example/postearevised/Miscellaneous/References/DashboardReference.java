package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;

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

    public static final String[] pieChartColors = {"#ff0000", "#ffa500", "#ffff00", "#008000", "#0000ff"};
}
