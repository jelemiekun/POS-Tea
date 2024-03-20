package com.example.postearevised.Miscellaneous.Database.CSV.Products;

import com.example.postearevised.Objects.Products.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public class ImportCSV {
    public static void importProductsFromCSV(String filePath, boolean fromImport) {
        ObservableList<Product> importedProducts = FXCollections.observableArrayList();
        ObservableList<Product> importedMilkTeas = FXCollections.observableArrayList();
        ObservableList<Product> importedCoolers = FXCollections.observableArrayList();
        ObservableList<Product> importedCoffees = FXCollections.observableArrayList();
        ObservableList<Product> importedIceCandyCups = FXCollections.observableArrayList();
        ObservableList<Product> importedAppetizers = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String productName = fields[0];
                String productDescription = fields[1];
                String productCategory = fields[2];
                String imagePath = fields[3];

                Product product = null;

                switch (productCategory) {
                    case "Milk Tea":
                        double milkTeaSmallPrice = Double.parseDouble(fields[4]);
                        double milkTeaMediumPrice = Double.parseDouble(fields[5]);
                        double milkTeaLargePrice = Double.parseDouble(fields[6]);
                        String milkTeaAddOnsOne = fields[7];
                        double milkTeaAddOnsOnePrice = Double.parseDouble(fields[8]);
                        String milkTeaAddOnsTwo = fields[9];
                        double milkTeaAddOnsTwoPrice = Double.parseDouble(fields[10]);
                        product = new MilkTea(productName, productDescription, imagePath, productCategory,
                                milkTeaSmallPrice, milkTeaMediumPrice, milkTeaLargePrice,
                                milkTeaAddOnsOne, milkTeaAddOnsOnePrice, milkTeaAddOnsTwo, milkTeaAddOnsTwoPrice);
                        importedMilkTeas.add(product);
                        break;
                    case "Coolers":
                        double coolersSmallPrice = Double.parseDouble(fields[11]);
                        double coolersMediumPrice = Double.parseDouble(fields[12]);
                        double coolersLargePrice = Double.parseDouble(fields[13]);
                        String coolersAddOnsOne = fields[14];
                        double coolersAddOnsOnePrice = Double.parseDouble(fields[15]);
                        String coolersAddOnsTwo = fields[16];
                        double coolersAddOnsTwoPrice = Double.parseDouble(fields[17]);
                        product = new Coolers(productName, productDescription, imagePath, productCategory,
                                coolersSmallPrice, coolersMediumPrice, coolersLargePrice,
                                coolersAddOnsOne, coolersAddOnsOnePrice, coolersAddOnsTwo, coolersAddOnsTwoPrice);
                        importedCoolers.add(product);
                        break;
                    case "Coffee":
                        double coffeePrice = Double.parseDouble(fields[18]);
                        product = new Coffee(productName, productDescription, imagePath, productCategory, coffeePrice);
                        importedCoffees.add(product);
                        break;
                    case "Ice Candy Cups":
                        double iceCandyCupsPrice = Double.parseDouble(fields[19]);
                        product = new IceCandyCups(productName, productDescription, imagePath, productCategory, iceCandyCupsPrice);
                        importedIceCandyCups.add(product);
                        break;
                    case "Appetizers":
                        double appetizerPrice = Double.parseDouble(fields[20]);
                        product = new Appetizer(productName, productDescription, imagePath, productCategory, appetizerPrice);
                        importedAppetizers.add(product);
                        break;
                    default:
                        // invalid product category dito
                        System.err.println("Invalid product category: " + productCategory);
                        break;
                }

                if (product != null) {
                    importedProducts.add(product);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        addImportedListsToSystemLists(importedProducts, importedMilkTeas, importedCoolers, importedCoffees, importedIceCandyCups, importedAppetizers, fromImport);
    }

    private static void addImportedListsToSystemLists(ObservableList<Product> importedProducts,
                                                      ObservableList<Product> importedMilkTeas,
                                                      ObservableList<Product> importedCoolers,
                                                      ObservableList<Product> importedCoffees,
                                                      ObservableList<Product> importedIceCandyCups,
                                                      ObservableList<Product> importedAppetizers,
                                                      boolean fromImport) {
        allProductObservableList.addAll(importedProducts);

        for (Product product : importedMilkTeas) {
            if (product instanceof MilkTea milkTea) {
                availableMilkTeaObservableList.add(milkTea);
            }
        }

        for (Product product : importedCoolers) {
            if (product instanceof Coolers coolers) {
                availableCoolersObservableList.add(coolers);
            }
        }

        for (Product product : importedCoffees) {
            if (product instanceof Coffee coffee) {
                availableCoffeeObservableList.add(coffee);
            }
        }

        for (Product product : importedIceCandyCups) {
            if (product instanceof IceCandyCups iceCandyCups) {
                availableIceCandyCupsObservableList.add(iceCandyCups);
            }
        }

        for (Product product : importedAppetizers) {
            if (product instanceof Appetizer appetizer) {
                availableAppetizerObservableList.add(appetizer);
            }
        }

        if (fromImport) {
            for (Product product : importedProducts) {
                addProductToCSV(product);
            }
        }
    }


}