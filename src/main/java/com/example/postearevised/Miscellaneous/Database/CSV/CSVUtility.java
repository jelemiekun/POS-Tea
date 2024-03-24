package com.example.postearevised.Miscellaneous.Database.CSV;

import com.example.postearevised.Miscellaneous.Database.CSV.OrderHistory.OrderHistoryCSVOperations;
import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Database.CSV.OrderHistory.OrderHistoryCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.OrderQueue.OrderQueueCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ExportCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ImportCSV.*;
import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;

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
            importOrdersFromCSV(false);
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
                importOrdersFromCSV(true);
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

    public static void importOrdersFromCSV(boolean isOrderQueue) {
        List<Order> orders = new ArrayList<>();

        String filePath = "";

        if (isOrderQueue)
            filePath = CSV_FILE_PATH_ORDER_QUEUE;
        else
            filePath = CSV_FILE_PATH_ORDER_HISTORY;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read and ignore the header
            String headerLine = reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 15) {
                    String customerName = parts[0];
                    int orderNumber = Integer.parseInt(parts[1]);
                    ObservableList<ProductOrder> productOrders = FXCollections.observableArrayList();
                    String[] categories = parts[2].split("/");
                    String[] names = parts[3].split("/");
                    String[] firstAttribute = parts[4].split("/");
                    String[] secondAttribute = parts[5].split("/");
                    String[] thirdAttribute = parts[6].split("/");
                    String[] quantities = parts[7].split("/");
                    String[] totalAmounts = parts[8].split("/");
                    String[] imagePaths = parts[14].split("/");

                    try {
                        for (int i = 0; i < categories.length; i++) {
                            ProductOrder productOrder = new ProductOrder(names[i], categories[i], null, imagePaths[i], firstAttribute[i], secondAttribute[i], thirdAttribute[i], Integer.parseInt(totalAmounts[i]), Integer.parseInt(quantities[i]));
                            productOrders.add(productOrder);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        errorMessage = e.getMessage();
                        logError(false);
                    }

                    int totalPrice = Integer.parseInt(parts[9]);
                    int amountPaid = Integer.parseInt(parts[10]);
                    int change = Integer.parseInt(parts[11]);
                    String modeOfPayment = parts[12];
                    LocalDateTime dateAndTime = LocalDateTime.parse(parts[13]);

                    Order order = new Order(productOrders, customerName, orderNumber, totalPrice, amountPaid, change, modeOfPayment, dateAndTime);
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorReadingOrderHistoryFromCSV();
            OrderHistoryCSVOperations.openPrompt();
        }

        // Add imported orders to the appropriate list
        if (isOrderQueue) {
            orderQueueObservableList.addAll(orders);
        } else {
            orderHistoryObservableList.addAll(orders);
        }
    }

}
