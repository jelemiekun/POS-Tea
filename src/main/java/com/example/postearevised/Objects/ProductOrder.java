package com.example.postearevised.Objects;

import javafx.scene.image.Image;

public class ProductOrder {
    private String productName;
    private Image productImage;
    private String firstAttribute;
    private String secondAttribute;
    private double totalAmount;

    public ProductOrder(String productName, Image productImage, String firstAttribute, String secondAttribute, double totalAmount) {
        this.productName = productName;
        this.productImage = productImage;
        this.firstAttribute = firstAttribute;
        this.secondAttribute = secondAttribute;
        this.totalAmount = totalAmount;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
