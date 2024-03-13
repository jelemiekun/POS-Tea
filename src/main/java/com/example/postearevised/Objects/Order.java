package com.example.postearevised.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Order {
    private ObservableList<ProductOrder> productOrderObservableList = FXCollections.observableArrayList();

    private String customerName;
    private int orderNumber;
    private ObservableList<Integer> productQuantity = FXCollections.observableArrayList();
    private double totalPrice;
    private double amountPaid;
    private double change;
    private String modeOfPayment;
    private LocalDateTime dateAndTime;

    public Order() {
        for (ProductOrder productOrder : productOrderObservableList) {
            productQuantity.add(1);
        }
    }

    public ObservableList<ProductOrder> getProductOrderObservableList() {
        return productOrderObservableList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ObservableList<Integer> getProductQuantity() {
        return productQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
