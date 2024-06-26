package com.example.postearevised.Miscellaneous.Database.CSV.Products;

import com.example.postearevised.Objects.Products.*;

import java.io.*;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public class ExportCSV {

    public static int exportProductsToCSV(String filePath) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("productName,productDescription,productCategory,imagePath,isAvailable,milkTeaSmallPrice,milkTeaMediumPrice,milkTeaLargePrice,milkTeaAddOnsOne,milkTeaAddOnsOnePrice,milkTeaAddOnsTwo,milkTeaAddOnsTwoPrice,coolersSmallPrice,coolersMediumPrice,coolersLargePrice,coolersAddOnsOne,coolersAddOnsOnePrice,coolersAddOnsTwo,coolersAddOnsTwoPrice,coffeePrice,iceCandyCupsPrice,appetizerPrice");

            for (Product product : allProductObservableList) {
                StringBuilder sb = new StringBuilder();
                sb.append(product.getProductName()).append(",");
                sb.append(product.getProductDescription()).append(",");
                sb.append(product.getCategory()).append(",");
                sb.append(product.getImagePath()).append(",");
                sb.append(product.getCheckBox().isSelected()).append(",");

                if (product instanceof MilkTea milkTea) {
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
                } else if (product instanceof Coolers coolers) {
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
                } else if (product instanceof Coffee coffee) {
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
                } else if (product instanceof IceCandyCups iceCandyCups) {
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
                } else if (product instanceof Appetizer appetizer) {
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

                writer.println(sb);
            }
            return 4; // success
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return 0; // error, do nothing
        }
    }

}
