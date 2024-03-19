package com.example.postearevised.Miscellaneous.Database;

import com.example.postearevised.Objects.Products.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import static com.example.postearevised.Miscellaneous.Database.ImportCSV.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;

public class CSVOperations {
    public static void doesCSVExist() {
        File directory = new File(DIRECTORY_PATH);
        File file = new File(FILE_PATH);

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Created POS_Tea directory: " + DIRECTORY_PATH);
                createCSVFile(FILE_PATH);
            } else {
                System.out.println("Failed to create POS_Tea directory: " + DIRECTORY_PATH);
            }
        } else {
            if (!file.exists()) {
                System.out.println("Directory exists but no csv file, will now create csv...");
                createCSVFile(FILE_PATH);
            } else {
                System.out.println("CSV file already exists: " + FILE_PATH);
                // Import the products that are in csv
                importProductsFromCSV(FILE_PATH);
            }
        }

        // Check if "product images" folder exists in POS_Tea and create if it doesn't
        File productImagesDir = new File(PRODUCT_IMAGES_PATH);
        if (!productImagesDir.exists()) {
            if (productImagesDir.mkdirs()) {
                System.out.println("Created 'product images' folder in POS_Tea.");
            } else {
                System.out.println("Failed to create 'product images' folder in POS_Tea.");
            }
        }
    }

    public static void createCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write column headers to the CSV file
            writer.write("productName,productDescription,productCategory,imagePath,milkTeaSmallPrice,milkTeaMediumPrice,milkTeaLargePrice,milkTeaAddOnsOne,milkTeaAddOnsOnePrice,milkTeaAddOnsTwo,milkTeaAddOnsTwoPrice,coolersSmallPrice,coolersMediumPrice,coolersLargePrice,coolersAddOnsOne,coolersAddOnsOnePrice,coolersAddOnsTwo,coolersAddOnsTwoPrice,coffeePrice,iceCandyCupsPrice,appetizerPrice\n");
            System.out.println("Creating csv file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addProductToCSV(Product product) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            StringBuilder sb = new StringBuilder();

            switch (product.getCategory()) {
                case "Milk Tea":
                    MilkTea milkTea = (MilkTea) product;
                    sb.append(milkTea.getProductName()).append(",");
                    sb.append(milkTea.getProductDescription()).append(",");
                    sb.append(milkTea.getCategory()).append(",");
                    sb.append(milkTea.getImagePath()).append(",");

                    
                    sb.append(milkTea.getSmallPrice()).append(",");
                    sb.append(milkTea.getMediumPrice()).append(",");
                    sb.append(milkTea.getLargePrice()).append(",");
                    sb.append(milkTea.getAddOnsOne()).append(",");
                    sb.append(milkTea.getAddOnsOnePrice()).append(",");
                    sb.append(milkTea.getAddOnsTwo()).append(",");
                    sb.append(milkTea.getAddOnsTwoPrice()).append("\n");
                    break;
                case "Coolers":
                    Coolers coolers = (Coolers) product;
                    sb.append(coolers.getProductName()).append(",");
                    sb.append(coolers.getProductDescription()).append(",");
                    sb.append(coolers.getCategory()).append(",");
                    sb.append(coolers.getImagePath()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");

                    sb.append(coolers.getSmallPrice()).append(",");
                    sb.append(coolers.getMediumPrice()).append(",");
                    sb.append(coolers.getLargePrice()).append(",");
                    sb.append(coolers.getAddOnsOne()).append(",");
                    sb.append(coolers.getAddOnsOnePrice()).append(",");
                    sb.append(coolers.getAddOnsTwo()).append(",");
                    sb.append(coolers.getAddOnsTwoPrice()).append("\n");
                    break;
                case "Coffee":
                    Coffee coffee = (Coffee) product;
                    sb.append(coffee.getProductName()).append(",");
                    sb.append(coffee.getProductDescription()).append(",");
                    sb.append(coffee.getCategory()).append(",");
                    sb.append(coffee.getImagePath()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");

                    sb.append(coffee.getPrice()).append("\n");
                    break;
                case "Ice Candy Cups":
                    IceCandyCups iceCandyCups = (IceCandyCups) product;
                    sb.append(iceCandyCups.getProductName()).append(",");
                    sb.append(iceCandyCups.getProductDescription()).append(",");
                    sb.append(iceCandyCups.getCategory()).append(",");
                    sb.append(iceCandyCups.getImagePath()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");

                    sb.append(iceCandyCups.getPrice()).append("\n");
                    break;
                case "Appetizers":
                    Appetizer appetizer = (Appetizer) product;
                    sb.append(appetizer.getProductName()).append(",");
                    sb.append(appetizer.getProductDescription()).append(",");
                    sb.append(appetizer.getCategory()).append(",");
                    sb.append(appetizer.getImagePath()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");

                    sb.append(appetizer.getPrice()).append("\n");
                    break;
                default:
                    // Handle if category is not recognized
                    break;
            }

            // Write the product details to the CSV file
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
