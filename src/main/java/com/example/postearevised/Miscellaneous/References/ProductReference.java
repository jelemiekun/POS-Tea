package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Products.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;

public class ProductReference {
    public static final int PRODUCT_DESCRIPTION_MAX_CHARACTERS = 200;
    public static final double IMAGE_VIEW_SMALL_WIDTH = 150;
    public static final double IMAGE_VIEW_SMALL_HEIGHT = 95;
    public static ObservableList<Product> allProductObservableList = FXCollections.observableArrayList();
    public static ObservableList<Product> availableAllProductObservableList = FXCollections.observableArrayList();
    public static ObservableList<MilkTea> availableMilkTeaObservableList = FXCollections.observableArrayList();
    public static ObservableList<Coolers> availableCoolersObservableList = FXCollections.observableArrayList();
    public static ObservableList<Coffee> availableCoffeeObservableList = FXCollections.observableArrayList();
    public static ObservableList<IceCandyCups> availableIceCandyCupsObservableList = FXCollections.observableArrayList();
    public static ObservableList<Appetizer> availableAppetizerObservableList = FXCollections.observableArrayList();
    public static ObservableList<Product> unavailableAllProductObservableList = FXCollections.observableArrayList();
    public static ObservableList<MilkTea> unavailableMilkTeaObservableList = FXCollections.observableArrayList();
    public static ObservableList<Coolers> unavailableCoolersObservableList = FXCollections.observableArrayList();
    public static ObservableList<Coffee> unavailableCoffeeObservableList = FXCollections.observableArrayList();
    public static ObservableList<IceCandyCups> unavailableIceCandyCupsObservableList = FXCollections.observableArrayList();
    public static ObservableList<Appetizer> unavailableAppetizerObservableList = FXCollections.observableArrayList();
    public static final List<String> productCategories = new ArrayList<>(Arrays.asList(
            MILK_TEA_ENUM.getCategory(),
            COOLERS_ENUM.getCategory(),
            COFFEE_ENUM.getCategory(),
            ICE_CANDY_CUPS_ENUM.getCategory(),
            APPETIZERS_ENUM.getCategory()));

    public static boolean addingProductSuccess = false;
    public static boolean editingProductSuccess = false;
    public static boolean deletingProductSuccess = false;

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
    public static String referenceMilkTeaAddOnsOneName = "";
    public static double referenceMilkTeaAddOnsOnePrice = 0.0;
    public static String referenceMilkTeaAddOnsTwoName = "";
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
        referenceMilkTeaAddOnsOneName = "";
        referenceMilkTeaAddOnsOnePrice = 0.0;
        referenceMilkTeaAddOnsTwoName = "";
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

        unavailableAllProductObservableList.clear();
        unavailableMilkTeaObservableList.clear();
        unavailableCoolersObservableList.clear();
        unavailableCoffeeObservableList.clear();
        unavailableIceCandyCupsObservableList.clear();
        unavailableAppetizerObservableList.clear();

        referenceProductName = "";
        referenceProductDescription = "";
        referenceCategory = "";
        referenceImagePath = "";

        referenceMilkTeaSmallPrice = 0.0;
        referenceMilkTeaMediumPrice = 0.0;
        referenceMilkTeaLargePrice = 0.0;
        referenceMilkTeaAddOnsOneName = "";
        referenceMilkTeaAddOnsOnePrice = 0.0;
        referenceMilkTeaAddOnsTwoName = "";
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


    public static boolean openPrompt() {
        FXMLLoader loader = new FXMLLoader(ProductReference.class.getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainStage.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
        return isConfirmed;
    }
}