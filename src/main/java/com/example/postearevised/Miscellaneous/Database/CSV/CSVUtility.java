package com.example.postearevised.Miscellaneous.Database.CSV;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.example.postearevised.Miscellaneous.Database.CSV.OrderHistory.OrderHistoryCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ExportCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ImportCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;

public class CSVUtility {
    public static void doesProductCSVExist() {
        createDirectoryIfNotExists(DIRECTORY_PATH);
        createDirectoryIfNotExists(DIRECTORY_PATH_ACCOUNTS);
        createDirectoryIfNotExists(DIRECTORY_PATH_CSV);
        createDirectoryIfNotExists(DIRECTORY_PATH_PRODUCT_IMAGES);
        createCSVFileIfNotExists(CSV_FILE_PATH_PRODUCTS);
        createCSVFileIfNotExists(CSV_FILE_PATH_ORDER_QUEUE);
    }

    public static void doesOrderHistoryCSVExist() {
        File file = new File(CSV_FILE_PATH_ORDER_HISTORY);

        if (!file.exists()) {
            System.out.println("Directory exists but no csv file, will now create order history csv...");
            createCSVFile(CSV_FILE_PATH_ORDER_HISTORY);
        } else {
            System.out.println("CSV order history file already exists: " + CSV_FILE_PATH_ORDER_HISTORY);
            readOrdersFromOrderHistoryCSV();
        }
    }

    private static void createCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write column headers to the CSV file
            writer.write("customerName,orderNumber,foodCategories,productName,firstAttribute,secondAttribute,thirdAttribute,productQuantity,productPrice,totalPrice,amountPaid,change,modeOfPayment,dateAndTime,imagePath\n");
            System.out.println("Creating order history csv file: " + filePath);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorCreatingCSVFile();
            openPrompt();
        }
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
