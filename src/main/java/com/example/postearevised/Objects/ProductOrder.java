package com.example.postearevised.Objects;

import javafx.scene.image.Image;

public class ProductOrder {
    private String productName;
    private Image productImage;
    private String firstAttribute;
    private String secondAttribute;
    private int totalAmount;
    private int quantity;

    public ProductOrder(String productName, Image productImage, String firstAttribute, String secondAttribute, int totalAmount, int quantity) {
        this.productName = productName;
        this.productImage = productImage;
        this.firstAttribute = firstAttribute;
        this.secondAttribute = secondAttribute;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Image getProductImage() {
        return productImage;
    }

    public void setProductImage(Image productImage) {
        this.productImage = productImage;
    }

    public String getFirstAttribute() {
        return firstAttribute;
    }

    public void setFirstAttribute(String firstAttribute) {
        this.firstAttribute = firstAttribute;
    }

    public String getSecondAttribute() {
        return secondAttribute;
    }

    public void setSecondAttribute(String secondAttribute) {
        this.secondAttribute = secondAttribute;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
