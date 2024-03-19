package com.example.postearevised.Miscellaneous.Database;

import com.example.postearevised.Objects.Products.*;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.List;

import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public class ExportCSV {
    public static void chooseFilePath(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Products CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        fileChooser.setInitialFileName("products.csv");

        // Show the save file dialog
        java.io.File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            String filePath = file.getAbsolutePath();
            exportProductsToCSV(filePath);
        }
    }

    public static void exportProductsToCSV(String filePath) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write column headers to the CSV file
            writer.println("productName,productDescription,productCategory,imagePath,milkTeaSmallPrice,milkTeaMediumPrice,milkTeaLargePrice,milkTeaAddOnsOne,milkTeaAddOnsOnePrice,milkTeaAddOnsTwo,milkTeaAddOnsTwoPrice,coolersSmallPrice,coolersMediumPrice,coolersLargePrice,coolersAddOnsOne,coolersAddOnsOnePrice,coolersAddOnsTwo,coolersAddOnsTwoPrice,coffeePrice,iceCandyCupsPrice,appetizerPrice");

            // Write each product to the CSV file
            for (Product product : allProductObservableList) {
                StringBuilder sb = new StringBuilder();
                sb.append(product.getProductName()).append(",");
                sb.append(product.getProductDescription()).append(",");
                sb.append(product.getCategory()).append(",");
                sb.append(product.getImagePath()).append(",");

                if (product instanceof MilkTea) {
                    MilkTea milkTea = (MilkTea) product;
                    sb.append(milkTea.getSmallPrice()).append(",");
                    sb.append(milkTea.getMediumPrice()).append(",");
                    sb.append(milkTea.getLargePrice()).append(",");
                    sb.append(milkTea.getAddOnsOne()).append(",");
                    sb.append(milkTea.getAddOnsOnePrice()).append(",");
                    sb.append(milkTea.getAddOnsTwo()).append(",");
                    sb.append(milkTea.getAddOnsTwoPrice()).append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                } else if (product instanceof Coolers) {
                    Coolers coolers = (Coolers) product;
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
                    sb.append(coolers.getAddOnsTwoPrice()).append(",");

                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                    sb.append(",");
                } else if (product instanceof Coffee) {
                    Coffee coffee = (Coffee) product;
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

                    sb.append(coffee.getPrice()).append(",");
                    sb.append(",");
                    sb.append(",");
                } else if (product instanceof IceCandyCups) {
                    IceCandyCups iceCandyCups = (IceCandyCups) product;
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

                    sb.append(iceCandyCups.getPrice()).append(",");
                    sb.append(",");
                } else if (product instanceof Appetizer) {
                    Appetizer appetizer = (Appetizer) product;
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
                    sb.append(appetizer.getPrice());
                }

                writer.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
