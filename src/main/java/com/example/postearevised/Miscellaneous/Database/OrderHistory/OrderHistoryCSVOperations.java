package com.example.postearevised.Miscellaneous.Database.OrderHistory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
