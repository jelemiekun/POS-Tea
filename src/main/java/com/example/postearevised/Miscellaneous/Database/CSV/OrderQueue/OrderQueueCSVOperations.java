package com.example.postearevised.Miscellaneous.Database.CSV.OrderQueue;

public class OrderQueueCSVOperations {
    //    public static boolean addOrderToCSV(Order order) {
//        try (FileWriter writer = new FileWriter(CSV_FILE_PATH_ORDER_QUEUE, true)) {
//            StringBuilder sb = new StringBuilder();
//            sb.append(order.getCustomerName()).append(",");
//            sb.append(order.getOrderNumber()).append(",");
//            List<ProductOrder> productOrders = order.getProductOrderObservableList();
//
//            if (!productOrders.isEmpty()) {
//                StringBuilder categoryBuilder = new StringBuilder();
//                StringBuilder nameBuilder = new StringBuilder();
//                StringBuilder firstAttributeBuilder = new StringBuilder();
//                StringBuilder secondAttributeBuilder = new StringBuilder();
//                StringBuilder thirdAttributeBuilder = new StringBuilder();
//                StringBuilder quantityBuilder = new StringBuilder();
//                StringBuilder totalAmountBuilder = new StringBuilder();
//
//                for (ProductOrder productOrder : productOrders) {
//                    if (productOrder.getProductName().isEmpty()) {
//                        productOrder.setProductName(".");
//                    }
//                    if (productOrder.getFirstAttribute().isEmpty()) {
//                        productOrder.setFirstAttribute(".");
//                    }
//                    if (productOrder.getSecondAttribute().isEmpty()) {
//                        productOrder.setSecondAttribute(".");
//                    }
//                    if (productOrder.getThirdAttribute().isEmpty()) {
//                        productOrder.setThirdAttribute(".");
//                    }
//
//                    categoryBuilder.append(productOrder.getProductCategory()).append("/");
//                    nameBuilder.append(productOrder.getProductName()).append("/");
//                    firstAttributeBuilder.append(productOrder.getFirstAttribute()).append("/");
//                    secondAttributeBuilder.append(productOrder.getSecondAttribute()).append("/");
//                    thirdAttributeBuilder.append(productOrder.getThirdAttribute()).append("/");
//                    quantityBuilder.append(productOrder.getQuantity()).append("/");
//                    totalAmountBuilder.append(productOrder.getTotalAmount()).append("/");
//                }
//
//                sb.append(categoryBuilder.toString()).append(",");
//                sb.append(nameBuilder.toString()).append(",");
//                sb.append(firstAttributeBuilder.toString()).append(",");
//                sb.append(secondAttributeBuilder.toString()).append(",");
//                sb.append(thirdAttributeBuilder.toString()).append(",");
//                sb.append(quantityBuilder.toString()).append(",");
//                sb.append(totalAmountBuilder.toString()).append(",");
//
//                int totalPrice = order.getTotalPrice();
//                int amountPaid = order.getAmountPaid();
//                int change = order.getChange();
//                String modeOfPayment = order.getModeOfPayment();
//                LocalDateTime dateAndTime = order.getDateAndTime();
//
//                sb.append(totalPrice).append(",");
//                sb.append(amountPaid).append(",");
//                sb.append(change).append(",");
//                sb.append(modeOfPayment).append(",");
//                sb.append(dateAndTime).append(",");
//
//                StringBuilder imagePathBuilder = new StringBuilder();
//                for (ProductOrder productOrder : productOrders) {
//                    imagePathBuilder.append(productOrder.getImagePath()).append("/");
//                }
//                sb.append(imagePathBuilder.toString()).append(",");
//            }
//
//            sb.append("\n");
//
//            writer.write(sb.toString());
//            System.out.println("Order added to CSV file: " + CSV_FILE_PATH_ORDER_QUEUE);
//            return true;
//        } catch (IOException e) {
//            errorMessage = e.getMessage();
//            logError(false);
//            return false;
//        }
//    }
}
