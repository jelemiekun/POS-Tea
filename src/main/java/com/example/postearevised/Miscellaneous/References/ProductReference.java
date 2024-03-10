package com.example.postearevised.Miscellaneous.References;

import com.example.postearevised.Objects.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;

public class ProductReference {
    public static ObservableList<Product> productObservableList = FXCollections.observableArrayList();
    public static final List<String> productCategories = new ArrayList<>(Arrays.asList(
            MilkTeaEnum.getCategory(),
            CoolersEnum.getCategory(),
            CoffeeEnum.getCategory(),
            IceCandyCupsEnum.getCategory(),
            AppetizersEnum.getCategory()));
}
