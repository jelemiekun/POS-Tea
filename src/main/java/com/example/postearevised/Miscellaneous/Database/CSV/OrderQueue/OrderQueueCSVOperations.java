package com.example.postearevised.Miscellaneous.Database.CSV.OrderQueue;

import com.example.postearevised.Objects.Order.Order;
import com.example.postearevised.Objects.Order.ProductOrder;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;

public class OrderQueueCSVOperations {

    public static boolean addOrderToOrderQueueCSV(Order order) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_ORDER_QUEUE, true)) {
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
            System.out.println("Order added to CSV file: " + CSV_FILE_PATH_ORDER_QUEUE);
            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }


    public static boolean deleteOrderInOrderQueueCSV(List<Order> orders) {
        boolean success = false;
        File tempFile = null;

        try {
            // Create a temporary file to write the updated content
            tempFile = new File(DIRECTORY_PATH_CSV + File.separator + "temp.csv");
            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter writer = new BufferedWriter(fw);

            // Read the CSV file
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH_ORDER_QUEUE));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                boolean deleteRow = false;

                // Check if any product in productListToDelete matches the criteria
                for (Order order : orders) {
                    if (fields[0].equals(order.getCustomerName()) &&
                            fields[13].equals(String.valueOf(order.getDateAndTime()))) {
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
            reader.close();
            writer.close();

            // Remove the current products.csv file
            File currentFile = new File(CSV_FILE_PATH_ORDER_QUEUE);

            if (!currentFile.delete()) {
                System.out.println("Failed to delete current orderQueue.csv file");
                if (tempFile.exists() && !tempFile.delete()) {
                    System.err.println("Failed to delete temporary file.");
                }
                return false;
            }

            // Rename the temporary file to the original file name
            File originalFile = new File(CSV_FILE_PATH_ORDER_QUEUE);
            if (!tempFile.renameTo(originalFile)) {
                if (tempFile.exists() && !tempFile.delete()) {
                    System.err.println("Failed to delete temporary file.");
                }
                System.out.println("Failed to rename temporary file to original file");
                return false;
            }


            success = true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            if (tempFile.exists() && !tempFile.delete()) {
                System.err.println("Failed to delete temporary file.");
            }
        }
        return success;
    }
}
