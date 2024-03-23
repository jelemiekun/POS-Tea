package com.example.postearevised.Miscellaneous.Others;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;

public class ReceiptGenerator {
    public static void generateReceipt(Order order, int invocationCount) {
        if (invocationCount == 1 || invocationCount == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

            String formattedDateTime = order.getDateAndTime().format(formatter);

            StringBuilder receiptContentBuilder = new StringBuilder();

            if (invocationCount == 1)
                receiptContentBuilder.append("*Store Copy*").append("\n\n");

            receiptContentBuilder.append("ACKNOWLEDGEMENT RECEIPT\n\n");

            receiptContentBuilder.append("Customer: ").append(order.getCustomerName()).append("\n");
            receiptContentBuilder.append("Date and Time: ").append(formattedDateTime).append("\n\n");
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

            try (FileWriter writer = new FileWriter(invocationCount == 1 ? ORDER_RECEIPT_STORE_COPY_PATH : ORDER_RECEIPT_CUSTOMER_COPY_PATH)) {
                writer.write(receiptContent);
                generateReceipt(order, ++invocationCount);
                System.out.println("Receipt generated successfully.");
            } catch (IOException e) {
                errorMessage = e.getMessage();
                logError(false);
            }
        }
    }

}