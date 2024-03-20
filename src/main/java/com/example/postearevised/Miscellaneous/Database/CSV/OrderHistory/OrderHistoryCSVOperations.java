package com.example.postearevised.Miscellaneous.Database.CSV.OrderHistory;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Enums.Scenes.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;

public class OrderHistoryCSVOperations {
    public static void doesOrderHistoryCSVExist() {
        File file = new File(ORDER_HISTORY_CSV_FILE_PATH);

        if (!file.exists()) {
            System.out.println("Directory exists but no csv file, will now create order history csv...");
            createCSVFile(ORDER_HISTORY_CSV_FILE_PATH);
        } else {
            System.out.println("CSV order history file already exists: " + ORDER_HISTORY_CSV_FILE_PATH);
            readOrdersFromCSV();
        }
    }

    private static void createCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write column headers to the CSV file
            writer.write("customerName, orderNumber, foodCategories,productName,productQuantity,productPrice,totalPrice,amountPaid,change,modeOfPayment,dateAndTime,imagePath\n");
            System.out.println("Creating order history csv file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            setErrorCreatingCSVFile();
            openPrompt();
        }
    }

    public static void readOrdersFromCSV() {
        List<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_HISTORY_CSV_FILE_PATH))) {
            // Read and ignore the header
            String headerLine = reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 12) { // Ensure there are at least 12 elements (assuming all fields are present)
                    String customerName = parts[0];
                    int orderNumber = Integer.parseInt(parts[1]);
                    ObservableList<ProductOrder> productOrders = FXCollections.observableArrayList();
                    String[] categories = parts[2].split("/");
                    String[] names = parts[3].split("/");
                    String[] quantities = parts[4].split("/");
                    String[] totalAmounts = parts[5].split("/");
                    String[] imagePaths = parts[11].split("/");

                    // Assuming all arrays have the same length
                    for (int i = 0; i < categories.length; i++) {
                        // Assuming you have a constructor for ProductOrder that takes necessary arguments
                        ProductOrder productOrder = new ProductOrder(names[i], categories[i], null, imagePaths[i], "", "", "", Integer.parseInt(totalAmounts[i]), Integer.parseInt(quantities[i]));
                        productOrders.add(productOrder);
                    }

                    double totalPrice = Double.parseDouble(parts[6]);
                    double amountPaid = Double.parseDouble(parts[7]);
                    double change = Double.parseDouble(parts[8]);
                    String modeOfPayment = parts[9];
                    LocalDateTime dateAndTime = LocalDateTime.parse(parts[10]);

                    Order order = new Order(productOrders, customerName, orderNumber, (int) totalPrice, (int) amountPaid, (int) change, modeOfPayment, dateAndTime);
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            setErrorReadingOrderHistoryFromCSV();
            openPrompt();
        }

        // Add imported history
        orderHistoryObservableList.addAll(orders);
    }


    public static boolean writeOrderToCSV(Order order) {
        try (FileWriter writer = new FileWriter(ORDER_HISTORY_CSV_FILE_PATH, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append(order.getCustomerName()).append(",");
            sb.append(order.getOrderNumber()).append(",");
            List<ProductOrder> productOrders = order.getProductOrderObservableList();

            StringBuilder categoryBuilder = new StringBuilder();
            StringBuilder nameBuilder = new StringBuilder();
            StringBuilder quantityBuilder = new StringBuilder();
            StringBuilder totalAmountBuilder = new StringBuilder();

            for (ProductOrder productOrder : productOrders) {
                categoryBuilder.append(productOrder.getProductCategory()).append("/");
                nameBuilder.append(productOrder.getProductName()).append("/");
                quantityBuilder.append(productOrder.getQuantity()).append("/");
                totalAmountBuilder.append(productOrder.getTotalAmount()).append("/");
            }
            sb.append(categoryBuilder.append(","));
            sb.append(nameBuilder.append(","));
            sb.append(quantityBuilder.append(","));
            sb.append(totalAmountBuilder.append(","));

            sb.append(order.getTotalPrice()).append(",");
            sb.append(order.getAmountPaid()).append(",");
            sb.append(order.getChange()).append(",");
            sb.append(order.getModeOfPayment()).append(",");
            sb.append(order.getDateAndTime()).append(",");

            StringBuilder imagePathBuilder = new StringBuilder();
            for (ProductOrder productOrder : productOrders) {
                imagePathBuilder.append(productOrder.getImagePath()).append("/");
            }
            sb.append(imagePathBuilder.toString()).append(",");

            sb.append("\n");

            writer.write(sb.toString());
            System.out.println("Order added to CSV file: " + ORDER_HISTORY_CSV_FILE_PATH);
            return true;
        } catch (IOException e) {
             setErrorAddingOrderToCSV();
            openPrompt();
            return false;
        }
    }

    private static void openPrompt() {
        FXMLLoader loader = new FXMLLoader(OrderHistoryCSVOperations.class.getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage newStage = new Stage();

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(mainStage.getScene().getWindow());

        newStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        newStage.setResizable(false);
        newStage.getIcons().add(SYSTEM_LOGO);
        newStage.setScene(new Scene(root));
        newStage.showAndWait();
    }
}
