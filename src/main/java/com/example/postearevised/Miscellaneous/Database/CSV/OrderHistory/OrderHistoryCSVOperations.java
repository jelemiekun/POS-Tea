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

import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
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
            writer.write("customerName,orderNumber,foodCategories,productName,firstAttribute,secondAttribute,thirdAttribute,productQuantity,productPrice,totalPrice,amountPaid,change,modeOfPayment,dateAndTime,imagePath\n");
            System.out.println("Creating order history csv file: " + filePath);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
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
            openPrompt();
        }

        // Add imported history
        orderHistoryObservableList.addAll(orders);
    }


    public static boolean addOrderToCSV(Order order) {
        try (FileWriter writer = new FileWriter(ORDER_HISTORY_CSV_FILE_PATH, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append(order.getCustomerName()).append(",");
            sb.append(order.getOrderNumber()).append(",");
            List<ProductOrder> productOrders = order.getProductOrderObservableList();

            if (!productOrders.isEmpty()) {
                StringBuilder categoryBuilder = new StringBuilder();
                StringBuilder nameBuilder = new StringBuilder();
                StringBuilder firstAttributeBuilder = new StringBuilder();
                StringBuilder secondAttributeBuilder = new StringBuilder();
                StringBuilder thirdAttributeBuilder = new StringBuilder();
                StringBuilder quantityBuilder = new StringBuilder();
                StringBuilder totalAmountBuilder = new StringBuilder();

                for (ProductOrder productOrder : productOrders) {
                    if (productOrder.getProductName().isEmpty()) {
                        productOrder.setProductName(".");
                    }
                    if (productOrder.getFirstAttribute().isEmpty()) {
                        productOrder.setFirstAttribute(".");
                    }
                    if (productOrder.getSecondAttribute().isEmpty()) {
                        productOrder.setSecondAttribute(".");
                    }
                    if (productOrder.getThirdAttribute().isEmpty()) {
                        productOrder.setThirdAttribute(".");
                    }

                    categoryBuilder.append(productOrder.getProductCategory()).append("/");
                    nameBuilder.append(productOrder.getProductName()).append("/");
                    firstAttributeBuilder.append(productOrder.getFirstAttribute()).append("/");
                    secondAttributeBuilder.append(productOrder.getSecondAttribute()).append("/");
                    thirdAttributeBuilder.append(productOrder.getThirdAttribute()).append("/");
                    quantityBuilder.append(productOrder.getQuantity()).append("/");
                    totalAmountBuilder.append(productOrder.getTotalAmount()).append("/");
                }

                sb.append(categoryBuilder.toString()).append(",");
                sb.append(nameBuilder.toString()).append(",");
                sb.append(firstAttributeBuilder.toString()).append(",");
                sb.append(secondAttributeBuilder.toString()).append(",");
                sb.append(thirdAttributeBuilder.toString()).append(",");
                sb.append(quantityBuilder.toString()).append(",");
                sb.append(totalAmountBuilder.toString()).append(",");

                int totalPrice = order.getTotalPrice();
                int amountPaid = order.getAmountPaid();
                int change = order.getChange();
                String modeOfPayment = order.getModeOfPayment();
                LocalDateTime dateAndTime = order.getDateAndTime();

                sb.append(totalPrice).append(",");
                sb.append(amountPaid).append(",");
                sb.append(change).append(",");
                sb.append(modeOfPayment).append(",");
                sb.append(dateAndTime).append(",");

                StringBuilder imagePathBuilder = new StringBuilder();
                for (ProductOrder productOrder : productOrders) {
                    imagePathBuilder.append(productOrder.getImagePath()).append("/");
                }
                sb.append(imagePathBuilder.toString()).append(",");
            }

            sb.append("\n");

            writer.write(sb.toString());
            System.out.println("Order added to CSV file: " + ORDER_HISTORY_CSV_FILE_PATH);
            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }



    private static void openPrompt() {
        FXMLLoader loader = new FXMLLoader(OrderHistoryCSVOperations.class.getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
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
