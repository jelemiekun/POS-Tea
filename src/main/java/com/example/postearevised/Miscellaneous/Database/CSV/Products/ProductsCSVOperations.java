package com.example.postearevised.Miscellaneous.Database.CSV.Products;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import com.example.postearevised.Objects.Products.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ExportCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ImportCSV.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setErrorCreatingCSVFile;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setExportSuccessful;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;

public class ProductsCSVOperations {
    public static void doesProductCSVExist() {
        createDirectoryIfNotExists(DIRECTORY_PATH);
        createDirectoryIfNotExists(DIRECTORY_PATH_ACCOUNTS);
        createDirectoryIfNotExists(DIRECTORY_PATH_CSV);
        createDirectoryIfNotExists(DIRECTORY_PATH_PRODUCT_IMAGES);
        createCSVFileIfNotExists(CSV_FILE_PATH_PRODUCTS);
        createCSVFileIfNotExists(CSV_FILE_PATH_ORDER_QUEUE);
    }

    private static void createDirectoryIfNotExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Created directory: " + path);
            } else {
                System.out.println("Failed to create directory: " + path);
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
    }

    private static void createCSVFileIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Directory exists but no CSV file, will now create CSV: " + filePath);
            if (filePath.contains("products")) {
                createProductsCSVFile(filePath);
            } else if (filePath.contains("orderQueue")) {
                createOrderQueueCSVFile(filePath);
            }
        } else {
            if (filePath.contains("products")) {
                System.out.println("CSV products file already exists: " + filePath);
                int i = importProductsFromCSV(filePath, false);
                System.out.println("line 35: " + i);
            } else if (filePath.contains("orderQueue")) {
                System.out.println("CSV products file already exists: " + filePath);
                //int i = importProductsFromCSV(filePath, false); // import order list DAPAT TO
            }
        }
    }



    public static void createOrderQueueCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write column headers to the CSV file
            writer.write("customerName,orderNumber,foodCategories,productName,firstAttribute,secondAttribute,thirdAttribute,productQuantity,productPrice,totalPrice,amountPaid,change,modeOfPayment,dateAndTime,imagePath\n");
            System.out.println("Creating order history csv file: " + filePath);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
    }



    public static void createProductsCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write column headers to the CSV file
            writer.write("productName," +
                    "productDescription," +
                    "productCategory," +
                    "imagePath," +
                    "milkTeaSmallPrice," +
                    "milkTeaMediumPrice," +
                    "milkTeaLargePrice," +
                    "milkTeaAddOnsOne," +
                    "milkTeaAddOnsOnePrice," +
                    "milkTeaAddOnsTwo," +
                    "milkTeaAddOnsTwoPrice," +
                    "coolersSmallPrice," +
                    "coolersMediumPrice," +
                    "coolersLargePrice," +
                    "coolersAddOnsOne," +
                    "coolersAddOnsOnePrice," +
                    "coolersAddOnsTwo," +
                    "coolersAddOnsTwoPrice," +
                    "coffeePrice," +
                    "iceCandyCupsPrice," +
                    "appetizerPrice\n");
            System.out.println("Creating products csv file: " + filePath);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
    }

    public static boolean addProductToCSV(Product product) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_PRODUCTS, true)) {
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
            writer.write(sb.toString());

            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }

    public static boolean editProductInCSV(Product oldProduct, Product newProduct) {
        try {
            File inputFile = new File(CSV_FILE_PATH_PRODUCTS);
            File tempFile = new File("temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            String oldProductDetails = oldProduct.getProductName() + "," +
                    oldProduct.getProductDescription() + "," +
                    oldProduct.getCategory() + "," +
                    oldProduct.getImagePath();

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String productDetails = fields[0] + "," + fields[1] + "," + fields[2] + "," + fields[3];

                if (productDetails.equals(oldProductDetails)) {
                    // Update the line with new product details
                    StringBuilder sb = new StringBuilder();
                    switch (newProduct.getCategory()) {
                        case "Milk Tea":
                            MilkTea milkTea = (MilkTea) newProduct;
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
                            sb.append(milkTea.getAddOnsTwoPrice());

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
                            break;
                        case "Coolers":
                            Coolers coolers = (Coolers) newProduct;
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
                            sb.append(coolers.getAddOnsTwoPrice()).append(",");

                            sb.append(",");
                            sb.append(",");
                            sb.append(",");
                            break;
                        case "Coffee":
                            Coffee coffee = (Coffee) newProduct;
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

                            sb.append(coffee.getPrice()).append(",");

                            sb.append(",");
                            sb.append(",");
                            break;
                        case "Ice Candy Cups":
                            IceCandyCups iceCandyCups = (IceCandyCups) newProduct;
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

                            sb.append(iceCandyCups.getPrice()).append(",");

                            sb.append(",");
                            break;
                        case "Appetizers":
                            Appetizer appetizer = (Appetizer) newProduct;
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
                    }
                    writer.write(sb.toString());
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile); // Rename temp file to original file name
            System.out.println("Gumagana");
            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }

    public static boolean deleteProductInCSV(List<Product> productListToDelete) {
        boolean success = false;
        File tempFile = null;

        try {
            // Create a temporary file to write the updated content
            tempFile = new File(DIRECTORY_PATH_CSV + File.separator + "temp.csv");
            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter writer = new BufferedWriter(fw);

            // Read the CSV file
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH_PRODUCTS));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    String[] fields = line.split(",");

                    boolean deleteRow = false;

                    // Check if any product in productListToDelete matches the criteria
                    for (Product product : productListToDelete) {
                        if (fields.length >= 4 && fields[0].equals(product.getProductName()) &&
                                fields[1].equals(product.getProductDescription()) &&
                                fields[2].equals(product.getCategory()) &&
                                fields[3].equals(product.getImagePath())) {
                            deleteRow = true;
                            break; // No need to check further, delete this row
                        }
                    }

                    if (!deleteRow) {
                        // Write the row to the temporary file if it doesn't match the criteria
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } finally {
                // Close the reader and writer
                reader.close();
                writer.close();
            }

            // Remove the current products.csv file
            File currentFile = new File(CSV_FILE_PATH_PRODUCTS);

            try {
                if (!currentFile.delete()) {
                    throw new IOException("Failed to delete current products.csv file");
                }

                // Rename the temporary file to the original file name
                File originalFile = new File(CSV_FILE_PATH_PRODUCTS);
                if (!tempFile.renameTo(originalFile)) {
                    throw new IOException("Failed to rename temporary file to original file");
                }
            } catch (IOException e) {
                errorMessage = e.getMessage();
                logError(false);
            }


            success = true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        } finally {
            // Delete the temporary file if there was an error
            if (!success && tempFile != null && tempFile.exists() && !tempFile.delete()) {
                System.err.println("Failed to delete temporary file.");
            }
        }
        return success;
    }



//    public static boolean addOrderToCSV(Order order) {
//        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_ORDER_QUEUE, true)) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(order.getCustomerName()).append(",");
//            sb.append(order.getOrderNumber()).append(",");
//            List<ProductOrder> productOrders = order.getProductOrderObservableList();
//
//            if (!productOrders.isEmpty()) {
//                StringBuilder categoryBuilder = new StringBuilder();
//                StringBuilder nameBuilder = new StringBuilder();
//                StringBuilder firstAttributeBuilder = new StringBuilder();
//                StringBuilder secondAttributeBuilder = new StringBuilder();
//                StringBuilder thirdAttributeBuilder = new StringBuilder();
//                StringBuilder quantityBuilder = new StringBuilder();
//                StringBuilder totalAmountBuilder = new StringBuilder();
//
//                for (ProductOrder productOrder : productOrders) {
//                    if (productOrder.getProductName().isEmpty()) {
//                        productOrder.setProductName(".");
//                    }
//                    if (productOrder.getFirstAttribute().isEmpty()) {
//                        productOrder.setFirstAttribute(".");
//                    }
//                    if (productOrder.getSecondAttribute().isEmpty()) {
//                        productOrder.setSecondAttribute(".");
//                    }
//                    if (productOrder.getThirdAttribute().isEmpty()) {
//                        productOrder.setThirdAttribute(".");
//                    }
//
//                    categoryBuilder.append(productOrder.getProductCategory()).append("/");
//                    nameBuilder.append(productOrder.getProductName()).append("/");
//                    firstAttributeBuilder.append(productOrder.getFirstAttribute()).append("/");
//                    secondAttributeBuilder.append(productOrder.getSecondAttribute()).append("/");
//                    thirdAttributeBuilder.append(productOrder.getThirdAttribute()).append("/");
//                    quantityBuilder.append(productOrder.getQuantity()).append("/");
//                    totalAmountBuilder.append(productOrder.getTotalAmount()).append("/");
//                }
//
//                sb.append(categoryBuilder.toString()).append(",");
//                sb.append(nameBuilder.toString()).append(",");
//                sb.append(firstAttributeBuilder.toString()).append(",");
//                sb.append(secondAttributeBuilder.toString()).append(",");
//                sb.append(thirdAttributeBuilder.toString()).append(",");
//                sb.append(quantityBuilder.toString()).append(",");
//                sb.append(totalAmountBuilder.toString()).append(",");
//
//                int totalPrice = order.getTotalPrice();
//                int amountPaid = order.getAmountPaid();
//                int change = order.getChange();
//                String modeOfPayment = order.getModeOfPayment();
//                LocalDateTime dateAndTime = order.getDateAndTime();
//
//                sb.append(totalPrice).append(",");
//                sb.append(amountPaid).append(",");
//                sb.append(change).append(",");
//                sb.append(modeOfPayment).append(",");
//                sb.append(dateAndTime).append(",");
//
//                StringBuilder imagePathBuilder = new StringBuilder();
//                for (ProductOrder productOrder : productOrders) {
//                    imagePathBuilder.append(productOrder.getImagePath()).append("/");
//                }
//                sb.append(imagePathBuilder.toString()).append(",");
//            }
//
//            sb.append("\n");
//
//            writer.write(sb.toString());
//            System.out.println("Order added to CSV file: " + CSV_FILE_PATH_ORDER_QUEUE);
//            return true;
//        } catch (IOException e) {
//            errorMessage = e.getMessage();
//            logError(false);
//            return false;
//        }
//    }






    public static int chooseFilePath(Stage stage, boolean isImport) {
        FileChooser fileChooser = new FileChooser();
        File file;

        if (isImport) {
            fileChooser.setTitle("Import Products CSV File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            file = fileChooser.showOpenDialog(stage);
        } else {
            fileChooser.setTitle("Save Products CSV File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            fileChooser.setInitialFileName("products.csv");
            file = fileChooser.showSaveDialog(stage);
        }

        if (file != null) {
            String filePath = file.getAbsolutePath();

            if (isImport) {
                return importProductsFromCSV(filePath, true);
            } else {
                setExportSuccessful(filePath);
                return exportProductsToCSV(filePath);
            }
        }
        return -1;
    }
}
