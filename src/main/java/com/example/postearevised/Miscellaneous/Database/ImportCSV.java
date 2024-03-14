package com.example.postearevised.Miscellaneous.Database;

import com.example.postearevised.Objects.*;
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

import static com.example.postearevised.Miscellaneous.References.ProductReference.allProductObservableList;

public class ImportCSV {
//    public static void importProductsFromCSV() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Select CSV File to Import");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
//        File selectedFile = fileChooser.showOpenDialog(new Stage());
//
//        if (selectedFile != null) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
//                String line;
//                // Skip the header line
//                reader.readLine();
//                while ((line = reader.readLine()) != null) {
//                    String[] parts = line.split(",");
//                    String className = parts[0];
//                    String productName = parts[1];
//                    String productDescription = parts[2];
//                    String category = parts[3];
//                    String priceString = parts[4];
//                    String imageBase64 = parts[5];
//
//                    Product product = null;
//
//                    switch (className) {
//                        case "Appetizer":
//                            double appetizerPrice = Double.parseDouble(priceString);
//                            product = new Appetizer(productName, productDescription, "", category, appetizerPrice);
//                            break;
//                        case "Coffee":
//                            double coffeePrice = Double.parseDouble(priceString);
//                            product = new Coffee(productName, productDescription, "", category, coffeePrice);
//                            break;
//                        case "Coolers":
//                            String[] coolerPrices = priceString.split(",");
//                            double smallPrice = Double.parseDouble(coolerPrices[0]);
//                            double mediumPrice = Double.parseDouble(coolerPrices[1]);
//                            double largePrice = Double.parseDouble(coolerPrices[2]);
//                            String addOnsOne = coolerPrices[3];
//                            double addOnsOnePrice = Double.parseDouble(coolerPrices[4]);
//                            String addOnsTwo = coolerPrices[5];
//                            double addOnsTwoPrice = Double.parseDouble(coolerPrices[6]);
//                            product = new Coolers(productName, productDescription, "", category, smallPrice, mediumPrice, largePrice, addOnsOne, addOnsOnePrice, addOnsTwo, addOnsTwoPrice);
//                            break;
//                        case "IceCandyCups":
//                            double iceCandyCupsPrice = Double.parseDouble(priceString);
//                            product = new IceCandyCups(productName, productDescription, "", category, iceCandyCupsPrice);
//                            break;
//                        case "MilkTea":
//                            String[] milkTeaPrices = priceString.split(",");
//                            double milkTeaSmallPrice = Double.parseDouble(milkTeaPrices[0]);
//                            double milkTeaMediumPrice = Double.parseDouble(milkTeaPrices[1]);
//                            double milkTeaLargePrice = Double.parseDouble(milkTeaPrices[2]);
//                            String milkTeaAddOnsOne = milkTeaPrices[3];
//                            double milkTeaAddOnsOnePrice = Double.parseDouble(milkTeaPrices[4]);
//                            String milkTeaAddOnsTwo = milkTeaPrices[5];
//                            double milkTeaAddOnsTwoPrice = Double.parseDouble(milkTeaPrices[6]);
//                            product = new MilkTea(productName, productDescription, "", category, milkTeaSmallPrice, milkTeaMediumPrice, milkTeaLargePrice, milkTeaAddOnsOne, milkTeaAddOnsOnePrice, milkTeaAddOnsTwo, milkTeaAddOnsTwoPrice);
//                            break;
//                        default:
//                            // Handle unrecognized product type
//                            break;
//                    }
//
//                    // Decode the image from Base64
//                    if (!imageBase64.isEmpty()) {
//                        byte[] decodedImage = Base64.getDecoder().decode(imageBase64);
//                        // Convert byte array to JavaFX Image
//                        ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedImage);
//                        Image image = new Image(inputStream);
//                        // Set the image for the product
//                        product.setImage(image);
//                    }
//
//
//                    // Add the product to your list or database
//                    if (product != null) {
//                        // Assuming allProductObservableList is accessible here
//                        allProductObservableList.add(product);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
