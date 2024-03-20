package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Products.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;

public class ProductReference {
    public static final double IMAGE_VIEW_SMALL_WIDTH = 150;
    public static final double IMAGE_VIEW_SMALL_HEIGHT = 95;
    public static ObservableList<Product> allProductObservableList = FXCollections.observableArrayList();
    public static ObservableList<Product> availableAllProductObservableList = FXCollections.observableArrayList();
    public static ObservableList<MilkTea> availableMilkTeaObservableList = FXCollections.observableArrayList();
    public static ObservableList<Coolers> availableCoolersObservableList = FXCollections.observableArrayList();
    public static ObservableList<Coffee> availableCoffeeObservableList = FXCollections.observableArrayList();
    public static ObservableList<IceCandyCups> availableIceCandyCupsObservableList = FXCollections.observableArrayList();
    public static ObservableList<Appetizer> availableAppetizerObservableList = FXCollections.observableArrayList();
    public static final List<String> productCategories = new ArrayList<>(Arrays.asList(
            MILK_TEA_ENUM.getCategory(),
            COOLERS_ENUM.getCategory(),
            COFFEE_ENUM.getCategory(),
            ICE_CANDY_CUPS_ENUM.getCategory(),
            APPETIZERS_ENUM.getCategory()));

    public static boolean addedProductSuccess = false;
    /**
     * General
     */
    public static String referenceProductName = "";
    public static String referenceProductDescription = "";
    public static String referenceCategory = "";
    public static String referenceImagePath = "";

    /**
     * MilkTea
     */
    public static double referenceMilkTeaSmallPrice = 0.0;
    public static double referenceMilkTeaMediumPrice = 0.0;
    public static double referenceMilkTeaLargePrice = 0.0;
    public static String referenceMilkTeaAddOnsOne = "";
    public static double referenceMilkTeaAddOnsOnePrice = 0.0;
    public static String referenceMilkTeaAddOnsTwo = "";
    public static double referenceMilkTeaAddOnsTwoPrice = 0.0;

    /**
     * Coolers
     */
    public static double referenceCoolersSmallPrice = 0.0;
    public static double referenceCoolersMediumPrice = 0.0;
    public static double referenceCoolersLargePrice = 0.0;
    public static String referenceCoolersAddOnsOneName = "";
    public static double referenceCoolersAddOnsOnePrice = 0.0;
    public static String referenceCoolersAddOnsTwoName = "";
    public static double referenceCoolersAddOnsTwoPrice = 0.0;

    /**
     * Coffee
     */
    public static double referenceCoffeePrice = 0.0;

    /**
     * Ice Candy Cups
     */
    public static double referenceIceCandyCupsPrice = 0.0;

    /**
     * Appetizers
     */
    public static double referenceAppetizersPrice = 0.0;

    public static void clearProductReferenceValues() {
        referenceProductName = "";
        referenceProductDescription = "";
        referenceCategory = "";
        referenceImagePath = "";

        referenceMilkTeaSmallPrice = 0.0;
        referenceMilkTeaMediumPrice = 0.0;
        referenceMilkTeaLargePrice = 0.0;
        referenceMilkTeaAddOnsOne = "";
        referenceMilkTeaAddOnsOnePrice = 0.0;
        referenceMilkTeaAddOnsTwo = "";
        referenceMilkTeaAddOnsTwoPrice = 0.0;

        referenceCoolersSmallPrice = 0.0;
        referenceCoolersMediumPrice = 0.0;
        referenceCoolersLargePrice = 0.0;
        referenceCoolersAddOnsOneName = "";
        referenceCoolersAddOnsOnePrice = 0.0;
        referenceCoolersAddOnsTwoName = "";
        referenceCoolersAddOnsTwoPrice = 0.0;

        referenceCoffeePrice = 0.0;

        referenceIceCandyCupsPrice = 0.0;

        referenceAppetizersPrice = 0.0;

        addedProductSuccess = false;
    }

    public static void clearAllProductReferences() {
        allProductObservableList.clear();
        availableAllProductObservableList.clear();
        availableMilkTeaObservableList.clear();
        availableCoolersObservableList.clear();
        availableCoffeeObservableList.clear();
        availableIceCandyCupsObservableList.clear();
        availableAppetizerObservableList.clear();

        referenceProductName = "";
        referenceProductDescription = "";
        referenceCategory = "";
        referenceImagePath = "";

        referenceMilkTeaSmallPrice = 0.0;
        referenceMilkTeaMediumPrice = 0.0;
        referenceMilkTeaLargePrice = 0.0;
        referenceMilkTeaAddOnsOne = "";
        referenceMilkTeaAddOnsOnePrice = 0.0;
        referenceMilkTeaAddOnsTwo = "";
        referenceMilkTeaAddOnsTwoPrice = 0.0;

        referenceCoolersSmallPrice = 0.0;
        referenceCoolersMediumPrice = 0.0;
        referenceCoolersLargePrice = 0.0;
        referenceCoolersAddOnsOneName = "";
        referenceCoolersAddOnsOnePrice = 0.0;
        referenceCoolersAddOnsTwoName = "";
        referenceCoolersAddOnsTwoPrice = 0.0;

        referenceCoffeePrice = 0.0;

        referenceIceCandyCupsPrice = 0.0;

        referenceAppetizersPrice = 0.0;

        addedProductSuccess = false;
    }
}