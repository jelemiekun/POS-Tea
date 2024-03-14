package com.example.postearevised.Miscellaneous.Database;

import com.example.postearevised.Objects.*;

import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import static com.example.postearevised.Miscellaneous.References.ProductReference.allProductObservableList;

public class ExportCSV {
//    public static void exportProductsToCSV() {
//        List<Product> products = allProductObservableList;
//
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save CSV File");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
//        File file = fileChooser.showSaveDialog(null);
//
//        if (file != null) {
//            try (FileWriter writer = new FileWriter(file)) {
//                // Write the header
//                writer.append("Class,Product Name,Description,Category,Price,Image\n");
//
//                // Write each product to the CSV file
//                for (Product product : products) {
//                    if (product instanceof Appetizer) {
//                        writeProduct((Appetizer) product, writer);
//                    } else if (product instanceof Coffee) {
//                        writeProduct((Coffee) product, writer);
//                    } else if (product instanceof Coolers) {
//                        writeProduct((Coolers) product, writer);
//                    } else if (product instanceof IceCandyCups) {
//                        writeProduct((IceCandyCups) product, writer);
//                    } else if (product instanceof MilkTea) {
//                        writeProduct((MilkTea) product, writer);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static void writeProduct(Product product, FileWriter writer) throws IOException {
//        writer.append(product.getClass().getSimpleName())
//                .append(",")
//                .append(product.getProductName())
//                .append(",")
//                .append(product.getProductDescription())
//                .append(",")
//                .append(product.getCategory())
//                .append(",")
//                .append(getPriceString(product))
//                .append(",")
//                .append(getImageBase64String(product.getImage()))
//                .append("\n");
//    }
//
//    private static String getPriceString(Product product) {
//        if (product instanceof Appetizer) {
//            return String.valueOf(((Appetizer) product).getPrice());
//        } else if (product instanceof Coffee) {
//            return String.valueOf(((Coffee) product).getPrice());
//        } else if (product instanceof Coolers) {
//            Coolers coolers = (Coolers) product;
//            return String.valueOf(coolers.getSmallPrice()) + "," +
//                    String.valueOf(coolers.getMediumPrice()) + "," +
//                    String.valueOf(coolers.getLargePrice()) + "," +
//                    coolers.getAddOnsOne() + "," +
//                    String.valueOf(coolers.getAddOnsOnePrice()) + "," +
//                    coolers.getAddOnsTwo() + "," +
//                    String.valueOf(coolers.getAddOnsTwoPrice());
//        } else if (product instanceof IceCandyCups) {
//            return String.valueOf(((IceCandyCups) product).getPrice());
//        } else if (product instanceof MilkTea) {
//            MilkTea milkTea = (MilkTea) product;
//            return String.valueOf(milkTea.getSmallPrice()) + "," +
//                    String.valueOf(milkTea.getMediumPrice()) + "," +
//                    String.valueOf(milkTea.getLargePrice()) + "," +
//                    milkTea.getAddOnsOne() + "," +
//                    String.valueOf(milkTea.getAddOnsOnePrice()) + "," +
//                    milkTea.getAddOnsTwo() + "," +
//                    String.valueOf(milkTea.getAddOnsTwoPrice());
//        }
//        return "";
//    }
//
//    private static String getImageBase64String(Image image) throws IOException {
//        if (image != null) {
//            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ImageIO.write(bufferedImage, "png", outputStream);
//            byte[] imageBytes = outputStream.toByteArray();
//            return Base64.getEncoder().encodeToString(imageBytes);
//        } else {
//            return "";
//        }
//    }


}
