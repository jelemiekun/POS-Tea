package com.example.postearevised.Objects.Order;

import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Order {
    private ObservableList<ProductOrder> productOrderObservableList;
    private final String customerName;
    private final int orderNumber;
    private final int totalPrice;
    private final int amountPaid;
    private int change;
    private final String modeOfPayment;
    private LocalDateTime dateAndTime;

    public Order(ObservableList<ProductOrder> productOrderObservableList, String customerName, int orderNumber,
                 int totalPrice, int amountPaid, int change, String modeOfPayment, LocalDateTime dateAndTime) {
        this.productOrderObservableList = productOrderObservableList;
        this.customerName = customerName;
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.amountPaid = amountPaid;
        this.change = change;
        this.modeOfPayment = modeOfPayment;
        this.dateAndTime = dateAndTime;
    }

    public ObservableList<ProductOrder> getProductOrderObservableList() {
        return productOrderObservableList;
    }

    public void setProductOrderObservableList(ObservableList<ProductOrder> productOrderObservableList) {
        this.productOrderObservableList = productOrderObservableList;
        System.out.println("Bagong pinasang productOrderObsableList: " + productOrderObservableList.size());
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public int getChange() {
        return change;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setChange(int change) {
        this.change = change;
    }
}
