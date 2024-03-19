package com.example.postearevised.Miscellaneous.Database.CSV.OrderHistory;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;

public class OrderHistoryCSVOperations {
    public static void doesOrderHistoryCSVExist() {
        File file = new File(ORDER_HISTORY_CSV_FILE_PATH);

        if (!file.exists()) {
            System.out.println("Directory exists but no csv file, will now create order history csv...");
            createCSVFile(ORDER_HISTORY_CSV_FILE_PATH);
        } else {
            System.out.println("CSV order history file already exists: " + ORDER_HISTORY_CSV_FILE_PATH);
            // Import the order history if exists
        }
    }

    private static void createCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write column headers to the CSV file
            writer.write("customerName,foodCategories,productName,productQuantity,productPrice,totalPrice,amountPaid,change,modeOfPayment,dateAndTime\n");
            System.out.println("Creating order history csv file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeOrderToCSV(Order order) {
        try (FileWriter writer = new FileWriter(ORDER_HISTORY_CSV_FILE_PATH, true)) {
            // Append order data to the CSV file
            List<ProductOrder> productOrders = order.getProductOrderObservableList();
            for (ProductOrder productOrder : productOrders) {
                StringBuilder sb = new StringBuilder();
                sb.append(order.getCustomerName()).append(",");
                sb.append(productOrder.getProductCategory()).append(",");
                sb.append(productOrder.getProductName()).append(",");
                sb.append(productOrder.getQuantity()).append(",");
                sb.append(productOrder.getTotalAmount()).append(",");
                sb.append(order.getTotalPrice()).append(",");
                sb.append(order.getAmountPaid()).append(",");
                sb.append(order.getChange()).append(",");
                sb.append(order.getModeOfPayment()).append(",");
                sb.append(order.getDateAndTime()).append("\n");
                writer.write(sb.toString());
            }
            System.out.println("Order added to CSV file: " + ORDER_HISTORY_CSV_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
