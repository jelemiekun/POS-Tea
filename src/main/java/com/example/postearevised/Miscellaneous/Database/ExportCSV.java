package com.example.postearevised.Miscellaneous.Database;

import com.example.postearevised.Objects.Products.*;
import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.List;

public class ExportCSV {
    public static void exportProductsToCSV(List<Product> products, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write column headers to the CSV file
            writer.println("productName,productDescription,productCategory,image,imagePath,milkTeaSmallPrice,milkTeaMediumPrice,milkTeaLargePrice,milkTeaAddOnsOne,milkTeaAddOnsOnePrice,milkTeaAddOnsTwo,milkTeaAddOnsTwoPrice,coolersSmallPrice,coolersMediumPrice,coolersLargePrice,coolersAddOnsOne,coolersAddOnsOnePrice,coolersAddOnsTwo,coolersAddOnsTwoPrice,coffeePrice,iceCandyCupsPrice,appetizerPrice");

            // Write each product to the CSV file
            for (Product product : products) {
                StringBuilder sb = new StringBuilder();
                sb.append(product.getProductName()).append(",");
                sb.append(product.getProductDescription()).append(",");
                sb.append(product.getCategory()).append(",");

                // Encode the image as Base64 string
                String imageBase64 = encodeImageToBase64(product.getImage());
                sb.append(imageBase64).append(",");

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
                    sb.append(",,,,,,");
                } else if (product instanceof Coolers) {
                    Coolers coolers = (Coolers) product;
                    sb.append(",,,,,,,"); // For Milk Tea columns
                    sb.append(coolers.getSmallPrice()).append(",");
                    sb.append(coolers.getMediumPrice()).append(",");
                    sb.append(coolers.getLargePrice()).append(",");
                    sb.append(coolers.getAddOnsOne()).append(",");
                    sb.append(coolers.getAddOnsOnePrice()).append(",");
                    sb.append(coolers.getAddOnsTwo()).append(",");
                    sb.append(coolers.getAddOnsTwoPrice()).append(",");
                    sb.append(",,,,,");
                } else if (product instanceof Coffee) {
                    Coffee coffee = (Coffee) product;
                    sb.append(",,,,,,,,,"); // For Milk Tea and Coolers columns
                    sb.append(coffee.getPrice()).append(",");
                    sb.append(",,,,,,,");
                } else if (product instanceof IceCandyCups) {
                    IceCandyCups iceCandyCups = (IceCandyCups) product;
                    sb.append(",,,,,,,,,,,"); // For Milk Tea, Coolers, and Coffee columns
                    sb.append(iceCandyCups.getPrice()).append(",");
                    sb.append(",,,,,,");
                } else if (product instanceof Appetizer) {
                    Appetizer appetizer = (Appetizer) product;
                    sb.append(",,,,,,,,,,,,"); // For Milk Tea, Coolers, Coffee, and Ice Candy Cups columns
                    sb.append(appetizer.getPrice());
                }

                writer.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String encodeImageToBase64(Image image) {
        // Convert the Image to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = baos.toByteArray();

        // Encode the byte array as Base64 string
        return Base64.getEncoder().encodeToString(imageData);
    }

}
