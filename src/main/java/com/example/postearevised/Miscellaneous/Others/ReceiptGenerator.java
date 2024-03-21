package com.example.postearevised.Miscellaneous.Others;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;

public class ReceiptGenerator {
    public static void generateReceipt(Order order) {

        // Prepare the receipt content
        StringBuilder receiptContentBuilder = new StringBuilder();
        receiptContentBuilder.append("Customer: ").append(order.getCustomerName()).append("\n");
        receiptContentBuilder.append("Date and Time: ").append(order.getDateAndTime()).append("\n\n");
        receiptContentBuilder.append("Products:\n");

        List<ProductOrder> productOrders = order.getProductOrderObservableList();
        for (ProductOrder productOrder : productOrders) {
            receiptContentBuilder.append(productOrder.getProductName()).append("\n");
            receiptContentBuilder.append(productOrder.getThirdAttribute())
                    .append(", ").append(productOrder.getFirstAttribute())
                    .append(", ").append(productOrder.getSecondAttribute()).append("\n");
            receiptContentBuilder.append("Price: ").append(productOrder.getTotalAmount()).append("\n\n");
        }

        receiptContentBuilder.append("\nTotal Price: ₱").append(order.getTotalPrice()).append("\n");
        receiptContentBuilder.append("Amount Paid: ₱").append(order.getAmountPaid()).append("\n");
        receiptContentBuilder.append("Change: ₱").append(order.getChange()).append("\n");
        receiptContentBuilder.append("Mode of Payment: ").append(order.getModeOfPayment()).append("\n");

        String receiptContent = receiptContentBuilder.toString();

        // Write the receipt content to a file
        try (FileWriter writer = new FileWriter(ORDER_RECEIPT_PATH)) {
            writer.write(receiptContent);
            System.out.println("Receipt generated successfully.");
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
    }

}
