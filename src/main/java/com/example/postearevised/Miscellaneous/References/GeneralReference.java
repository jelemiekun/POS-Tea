package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Products.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class GeneralReference {
    public static final ObservableList<String> modeOfPaymentChoices = FXCollections.observableArrayList("Cash", "GCash", "Maya", "Others");
    public static final int AES_LENGTH = 256;
    public static final int MAXIMUM_ATTEMPTS_FOR_CRITICAL_INPUTS = 5;
    public static final int INPUT_LIMIT_TO_ELEVEN = 11;
    public static final int PASSWORD_LIMIT = 128;
    public static final int NAME_LIMIT = 16;
    public static final int ONE_SECOND = 1000;
    public static int countdownTimer; //ms
    public static int[] screenResolution = new int[2];
    public static Thread countdown;
    public static Product editOrShowSelectedProduct;
    public static boolean isAddingProductsFromImport = true;
    public static Tooltip showPasswordToolTip = new Tooltip("Show password");
    public static Tooltip hidePasswordToolTip = new Tooltip("Hide password");
}
