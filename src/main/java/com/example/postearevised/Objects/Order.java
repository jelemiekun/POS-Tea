package com.example.postearevised.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Order {
    private ObservableList<Product> productObservableList = FXCollections.observableArrayList();

    private String customerName;
    private int orderNumber;
    private int productQuantity;
    private double totalPrice;
    private double amountPaid;
    private double change;
    private String modeOfPayment;
    private LocalDateTime dateAndTime;

    public Order() {
        this.productObservableList = FXCollections.observableArrayList();
        this.productQuantity = 1;
    }

    public ObservableList<Product> getProductObservableList() {
        return productObservableList;
    }

    public void setProductObservableList(ObservableList<Product> productObservableList) {
        this.productObservableList = productObservableList;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
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
