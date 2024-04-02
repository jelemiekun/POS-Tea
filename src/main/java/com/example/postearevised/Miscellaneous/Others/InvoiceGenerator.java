package com.example.postearevised.Miscellaneous.Others;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;

public class InvoiceGenerator {
    public static void doesInvoicePathExist() {
        File receiptPath = new File(DIRECTORY_PATH_RECEIPT);

        if (!receiptPath.exists()) {
            if (receiptPath.mkdirs()) {
                System.out.println("Created directory: " + DIRECTORY_PATH_RECEIPT);
            } else {
                System.out.println("Failed to create directory: " + DIRECTORY_PATH_RECEIPT);
            }
        } else {
            System.out.println("Directory already exists: " + DIRECTORY_PATH_RECEIPT);
        }
    }

    public static String generateInvoice(Order order, int invocationCount) {
        if (invocationCount == 1 || invocationCount == 2) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            List<ProductOrder> productOrders = order.getProductOrderObservableList();

            String formattedDateTime = order.getDateAndTime().format(formatter);

            StringBuilder receiptContentBuilder = new StringBuilder();

            if (invocationCount == 1) {
                receiptContentBuilder.append("                                        \n");
                receiptContentBuilder.append("*Store copy*                            \n");
            }

            receiptContentBuilder.append("                                        \n");
            receiptContentBuilder.append("                                        \n");
            receiptContentBuilder.append("              EATS BRUDAYS              \n");
            receiptContentBuilder.append("                                        \n");
            receiptContentBuilder.append(" 31 E Santos Ave., Sumilang 1600 Pasig, \n");
            receiptContentBuilder.append("              Philippines               \n");
            receiptContentBuilder.append("                                        \n");
            receiptContentBuilder.append("DATE: ").append(formatDate(order.getDateAndTime())).append("       ").append("TIME: ").append(formatTime(order.getDateAndTime())).append("\n");
            receiptContentBuilder.append("========================================\n");
            receiptContentBuilder.append("Product              QTY           Price\n");
            receiptContentBuilder.append("----------------------------------------\n");
            for (ProductOrder productOrder : productOrders) {
                receiptContentBuilder.append(productOrder.getProductName()).append(calculateSpacesProductName(productOrder.getProductName()));
                receiptContentBuilder.append("x").append(productOrder.getQuantity()).append(calculateSpacesProductQuantity(String.valueOf(productOrder.getQuantity())));
                receiptContentBuilder.append("  ").append(formatAmount(productOrder.getTotalAmount())).append("\n");

                if (!productOrder.getThirdAttribute().isEmpty())
                    receiptContentBuilder.append("  -").append(productOrder.getThirdAttribute()).append(calculateSpacesAttributes(productOrder.getThirdAttribute())).append("\n");

                if (!productOrder.getFirstAttribute().isEmpty())
                    receiptContentBuilder.append("  -").append(productOrder.getFirstAttribute()).append(calculateSpacesAttributes(productOrder.getFirstAttribute())).append("\n");

                if (!productOrder.getSecondAttribute().isEmpty())
                    receiptContentBuilder.append("  -").append(productOrder.getSecondAttribute()).append(calculateSpacesAttributes(productOrder.getSecondAttribute())).append("\n");
            }
            receiptContentBuilder.append("----------------------------------------\n");
            receiptContentBuilder.append("TOTAL AMOUNT DUE").append(formatTotalAmount(order.getTotalPrice())).append("\n");
            receiptContentBuilder.append("CASH ENTERED").append(formatAmountEntered(order.getAmountPaid())).append("\n");
            receiptContentBuilder.append("CHANGE").append(formatAmountChange(order.getChange())).append("\n");
            receiptContentBuilder.append("----------------------------------------\n");
            //receiptContentBuilder.append("CASHIER: ").append("SHET DI KO NASTORE NAME NG CASHIER SA ORDER").append("\n");
            receiptContentBuilder.append("CUSTOMER NO. ").append(order.getOrderNumber()).append(calculateSpacesCustomerNumber(String.valueOf(order.getOrderNumber()))).append("\n");
            receiptContentBuilder.append("CUSTOMER NAME: ").append(order.getCustomerName()).append(calculateSpacesCustomerName(order.getCustomerName())).append("\n");
            receiptContentBuilder.append("Mode of payment: ").append(order.getModeOfPayment()).append(calculateSpacesModeOfPayment(order.getModeOfPayment())).append("\n");
            receiptContentBuilder.append("Transaction ID: ").append(order.getTransactionID()).append(calculateSpacesTransactionID(order.getTransactionID())).append("\n");
            receiptContentBuilder.append("========================================\n");
            receiptContentBuilder.append("                                        \n");
            receiptContentBuilder.append("           This serves as your          \n");
            receiptContentBuilder.append("         ACKNOWLEDGEMENT Receipt        \n");
            receiptContentBuilder.append("                                        \n");
            receiptContentBuilder.append("        THANK YOU FOR PURCHASING        \n");
            receiptContentBuilder.append("                                        \n");
            receiptContentBuilder.append("*EndOfInvoice*").append("\n");

            String receiptContent = receiptContentBuilder.toString();

            try (FileWriter writer = new FileWriter(invocationCount == 1 ? TEXT_PATH_ORDER_RECEIPT_STORE_COPY : TEXT_PATH_ORDER_RECEIPT_CUSTOMER_COPY, true)) {
                writer.write(receiptContent);

                if (invocationCount == 1)
                    return receiptContent;

                generateInvoice(order, ++invocationCount);
                System.out.println("Invoice generated successfully.");
            } catch (IOException e) {
                errorMessage = e.getMessage();
                logError(false);
            }
        }
        return "";
    }

    public static String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return dateTime.format(formatter);
    }
    public static String formatTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return dateTime.format(formatter);
    }

    public static String calculateSpacesProductName(String productName) {
        int spacesNeeded = Math.max(21 - productName.length(), 6);
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            spaces.append(" ");
        }

        return spaces.toString();
    }


    public static String calculateSpacesProductQuantity(String productQuantity) {
        int spacesNeeded = Math.max(7 - productQuantity.length(), 1);

        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            spaces.append(" ");
        }

        return spaces.toString();
    }

    public static String formatAmount(int number) {
        String numberString = "₱" + number + ".00";
        int spacesNeeded = Math.max(9 - numberString.length(), 0);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            result.append(" ");
        }
        result.append(numberString);
        return result.toString();
    }

    public static String calculateSpacesAttributes(String input) {
        int spacesNeeded = Math.max(37 - input.length(), 0);
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public static String formatTotalAmount(int number) {
        String numberString = "₱" + number + ".00";
        int spacesNeeded = Math.max(24 - numberString.length(), 0);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            result.append(" ");
        }
        result.append(numberString);
        return result.toString();
    }

    public static String formatAmountEntered(int number) {
        String numberString = "₱" + number + ".00";
        int spacesNeeded = Math.max(28 - numberString.length(), 0);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            result.append(" ");
        }
        result.append(numberString);
        return result.toString();
    }

    public static String formatAmountChange(int number) {
        String numberString = "₱" + number + ".00";
        int spacesNeeded = Math.max(34 - numberString.length(), 0);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            result.append(" ");
        }
        result.append(numberString);
        return result.toString();
    }

    public static String calculateSpacesCashier(String input) {
        int spacesNeeded = Math.max(31 - input.length(), 0);
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public static String calculateSpacesCustomerNumber(String input) {
        int spacesNeeded = Math.max(27 - input.length(), 0);
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public static String calculateSpacesCustomerName(String input) {
        int spacesNeeded = Math.max(25 - input.length(), 0);
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public static String calculateSpacesModeOfPayment(String input) {
        int spacesNeeded = Math.max(23 - input.length(), 0);
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public static String calculateSpacesTransactionID(String input) {
        int spacesNeeded = Math.max(24 - input.length(), 0);
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spacesNeeded; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }
}
