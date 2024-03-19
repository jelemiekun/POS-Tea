package com.example.postearevised.Miscellaneous.Database;

import com.example.postearevised.Objects.Products.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public class ImportCSV {
    public static void importProductsFromCSV(String filePath) {
        ObservableList<Product> importedProducts = FXCollections.observableArrayList();
        ObservableList<Product> importedMilkTeas = FXCollections.observableArrayList();
        ObservableList<Product> importedCoolers = FXCollections.observableArrayList();
        ObservableList<Product> importedCoffees = FXCollections.observableArrayList();
        ObservableList<Product> importedIceCandyCups = FXCollections.observableArrayList();
        ObservableList<Product> importedAppetizers = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
//                if (fields.length < 22) {
//                    System.err.println("Invalid CSV format: Insufficient fields in line: " + line);
//                    continue;
//                }
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

        addImportedListsToSystemLists(importedProducts, importedMilkTeas, importedCoolers, importedCoffees, importedIceCandyCups, importedAppetizers);
    }

    private static void addImportedListsToSystemLists(ObservableList<Product> importedProducts,
                                                      ObservableList<Product> importedMilkTeas,
                                                      ObservableList<Product> importedCoolers,
                                                      ObservableList<Product> importedCoffees,
                                                      ObservableList<Product> importedIceCandyCups,
                                                      ObservableList<Product> importedAppetizers) {
        allProductObservableList.addAll(importedProducts);

        for (Product product : importedMilkTeas) {
            if (product instanceof MilkTea milkTea) {
                availableMilkTeaObservableList.add(milkTea);
            }
        }

        for (Product product : importedCoolers) {
            if (product instanceof Coolers coolers) {
                availableCoolersObservableList.add(coolers);
            } else {
                // Handle invalid elements
            }
        }

        for (Product product : importedCoffees) {
            if (product instanceof Coffee coffee) {
                availableCoffeeObservableList.add(coffee);
            } else {
                // Handle invalid elements
            }
        }

        for (Product product : importedIceCandyCups) {
            if (product instanceof IceCandyCups iceCandyCups) {
                availableIceCandyCupsObservableList.add(iceCandyCups);
            } else {
                // Handle invalid elements
            }
        }

        for (Product product : importedAppetizers) {
            if (product instanceof Appetizer appetizer) {
                availableAppetizerObservableList.add(appetizer);
            } else {
                // Handle invalid elements
            }
        }
    }


}
