package com.example.postearevised.Miscellaneous.Database.CSV.OrderHistoryAndOrderQueue;

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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.example.postearevised.Miscellaneous.Database.CSV.CSVUtility.*;
import static com.example.postearevised.Miscellaneous.Enums.ScenesEnum.*;
import static com.example.postearevised.Miscellaneous.Others.LogFile.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.FileReference.*;
import static com.example.postearevised.Miscellaneous.References.StageReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderHistoryReference.*;
import static com.example.postearevised.Miscellaneous.References.OrderQueueReference.*;

public class OrderHistoryAndOrderQueueCSVOperations {

    public static void createOrderHistoryCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("customerName,orderNumber,foodCategories,productName,firstAttribute,secondAttribute,thirdAttribute,productQuantity,productPrice,totalPrice,amountPaid,change,modeOfPayment,dateAndTime,imagePath,transactionID,cashierName,invoice\n");
            System.out.println("Creating order history csv file: " + filePath);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorCreatingCSVFile();
            openPrompt();
        }
    }

    public static void createOrderQueueCSVFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("customerName,orderNumber,foodCategories,productName,firstAttribute,secondAttribute,thirdAttribute,productQuantity,productPrice,totalPrice,amountPaid,change,modeOfPayment,dateAndTime,imagePath,transactionID,cashierName,invoice\n");
            System.out.println("Creating order history csv file: " + filePath);
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorCreatingCSVFile();
            openPrompt();
        }
    }


    public static void openPrompt() {
        FXMLLoader loader = new FXMLLoader(OrderHistoryAndOrderQueueCSVOperations.class.getResource(EXIT_CONFIRMATION_ENUM.getURL()));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
        }
        promptStage = new Stage();

        promptStage.initModality(Modality.WINDOW_MODAL);
        promptStage.initOwner(mainStage.getScene().getWindow());

        promptStage.setTitle(EXIT_CONFIRMATION_ENUM.getTITLE());
        promptStage.setResizable(false);
        promptStage.getIcons().add(SYSTEM_LOGO);
        promptStage.setScene(new Scene(root));
        promptStage.showAndWait();
    }

    public static void importOrdersFromCSV(boolean isOrderQueue) {
        List<Order> orders = new ArrayList<>();

        String filePath = isOrderQueue ? CSV_FILE_PATH_ORDER_QUEUE : CSV_FILE_PATH_ORDER_HISTORY;

        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 18) {
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
                            imagePaths[i] = imagePaths[i].isEmpty() ? "/com/example/postearevised/Product Media/no image/no image.png" : imagePaths[i];
                            System.out.println("line 101 image path: " + imagePaths[i]);
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
                    String temporaryLocalDateAndTime = parts[13];
                    temporaryLocalDateAndTime = deFormatString(temporaryLocalDateAndTime);
                    LocalDateTime dateAndTime = LocalDateTime.parse(temporaryLocalDateAndTime);
                    String transactionID = parts[15];
                    transactionID = deFormatString(transactionID);
                    String cashierName = parts[16];
                    StringBuilder invoiceBuilder = new StringBuilder();
                    if (scanner.hasNextLine()) {
                        invoiceBuilder.append(scanner.nextLine()).append("\n");
                    }
                    while (scanner.hasNextLine()) {
                        String invoiceLine = scanner.nextLine();
                        if (invoiceLine.trim().equals("*EndOfInvoice*")) {
                            break;
                        }
                        invoiceBuilder.append(invoiceLine).append("\n");
                    }

                    String invoice = invoiceBuilder.toString().trim();




                    Order order = new Order(productOrders, customerName, orderNumber, totalPrice, amountPaid, change, modeOfPayment, dateAndTime, transactionID, cashierName, invoice);
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            setErrorReadingOrderHistoryFromCSV();
            OrderHistoryAndOrderQueueCSVOperations.openPrompt();
        }

        if (isOrderQueue) {
            orderQueueObservableList.addAll(orders);
        } else {
            orderHistoryObservableList.addAll(orders);
        }
    }

    public static boolean addOrderToCSV(Order order, boolean isOrderQueue) {
        String filePath = isOrderQueue ? CSV_FILE_PATH_ORDER_QUEUE : CSV_FILE_PATH_ORDER_HISTORY;


        try (FileWriter writer = new FileWriter(filePath, true)) {
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

                sb.append(categoryBuilder).append(",");
                sb.append(nameBuilder).append(",");
                sb.append(firstAttributeBuilder).append(",");
                sb.append(secondAttributeBuilder).append(",");
                sb.append(thirdAttributeBuilder).append(",");
                sb.append(quantityBuilder).append(",");
                sb.append(totalAmountBuilder).append(",");

                int totalPrice = order.getTotalPrice();
                int amountPaid = order.getAmountPaid();
                int change = order.getChange();
                String modeOfPayment = order.getModeOfPayment();
                LocalDateTime dateAndTime = order.getDateAndTime();

                sb.append(totalPrice).append(",");
                sb.append(amountPaid).append(",");
                sb.append(change).append(",");
                sb.append(modeOfPayment).append(",");
                sb.append(formatString(String.valueOf(dateAndTime))).append(",");

                StringBuilder imagePathBuilder = new StringBuilder();
                for (ProductOrder productOrder : productOrders) {
                    imagePathBuilder.append(productOrder.getImagePath()).append("/");
                }
                sb.append(imagePathBuilder).append(",");
            }

            System.out.println("line 229 " + order.getTransactionID());
            sb.append(formatString(order.getTransactionID())).append(",");
            sb.append(order.getCashierName()).append(",");

            String[] invoiceLines = order.getInvoice().split("\n");
            for (int i = 0; i < invoiceLines.length; i++) {
                sb.append(invoiceLines[i]);
                if (i < invoiceLines.length - 1) {
                    sb.append("\n");
                }
            }

            sb.append("\n");

            writer.write(sb.toString());
            System.out.println("Order added to CSV file: " + filePath);
            return true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            return false;
        }
    }

    public static boolean deleteOrdersInCSV(List<Order> orderListToDelete, boolean isOrderQueue) {
        boolean success = false;
        File tempFile = null;

        String filePath = isOrderQueue ? CSV_FILE_PATH_ORDER_QUEUE : CSV_FILE_PATH_ORDER_HISTORY;

        try {
            tempFile = new File(DIRECTORY_PATH_CSV + File.separator + "temp.csv");
            FileWriter fw = new FileWriter(tempFile);
            BufferedWriter writer = new BufferedWriter(fw);

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            boolean deleting = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("*EndOfInvoice*"))
                    break;

                if (deleting)
                    continue;

                String[] fields = line.split(",");
                boolean deleteRow = false;

                for (Order order : orderListToDelete) {
                    if (fields.length >= 4 &&
                            fields[0].equals(order.getCustomerName()) &&
                            fields[13].equals(formatString(String.valueOf(order.getDateAndTime())))) {
                        deleteRow = true;
                        break;
                    }
                }

                if (deleteRow) {
                    deleting = true;
                    continue;
                }

                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            File currentFile = new File(filePath);

            if (!currentFile.delete()) {
                System.out.println("Failed to delete current csv file");
                if (tempFile.exists() && !tempFile.delete()) {
                    System.err.println("Failed to delete temporary file.");
                }
                return false;
            }

            File originalFile = new File(filePath);
            if (!tempFile.renameTo(originalFile)) {
                System.out.println("Failed to rename temporary file to original file");
                if (tempFile.exists() && !tempFile.delete())
                    System.err.println("Failed to delete temporary file.");
                return false;
            }

            success = true;
        } catch (IOException e) {
            errorMessage = e.getMessage();
            logError(false);
            if (tempFile.exists() && !tempFile.delete())
                System.err.println("Failed to delete temporary file.");
        }
        return success;
    }

}
