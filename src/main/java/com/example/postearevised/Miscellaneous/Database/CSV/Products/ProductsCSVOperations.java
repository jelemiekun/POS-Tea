package com.example.postearevised.Miscellaneous.Database.CSV.Products;

import com.example.postearevised.Objects.Products.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ExportCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ImportCSV.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.setExportSuccessful;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;

public class ProductsCSVOperations {
    public static void doesProductCSVExist() {
        createDirectoryIfNotExists(DIRECTORY_PATH);
        createDirectoryIfNotExists(DIRECTORY_CSV_PATH);
        createCSVFileIfNotExists(PRODUCTS_CSV_FILE_PATH);
        createDirectoryIfNotExists(DIRECTORY_PRODUCT_IMAGES_PATH);
        createDirectoryIfNotExists(DIRECTORY_ACCOUNTS_PATH);
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
            System.out.println("Directory exists but no CSV file, will now create products CSV: " + filePath);
            createCSVFile(filePath);
        } else {
            System.out.println("CSV products file already exists: " + filePath);
            int i = importProductsFromCSV(filePath, false);
            System.out.println("line 35: " + i);
        }
    }


    public static void createCSVFile(String filePath) {
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
        try (FileWriter writer = new FileWriter(PRODUCTS_CSV_FILE_PATH, true)) {
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
            File inputFile = new File(PRODUCTS_CSV_FILE_PATH);
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
            tempFile = new File(DIRECTORY_CSV_PATH + File.separator + "temp.csv");
            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter writer = new BufferedWriter(fw);

            // Read the CSV file
            BufferedReader reader = new BufferedReader(new FileReader(PRODUCTS_CSV_FILE_PATH));
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
            File currentFile = new File(PRODUCTS_CSV_FILE_PATH);

            try {
                if (!currentFile.delete()) {
                    throw new IOException("Failed to delete current products.csv file");
                }

                // Rename the temporary file to the original file name
                File originalFile = new File(PRODUCTS_CSV_FILE_PATH);
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
