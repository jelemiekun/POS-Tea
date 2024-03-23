package com.example.postearevised.Objects.Order;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class ProductOrder {
    private String productName;
    private final String productCategory;
    private final Image productImage;
    private final String imagePath;
    private String firstAttribute;
    private String secondAttribute;
    private String thirdAttribute;
    private int totalAmount;
    private int quantity;
    private final Label labelPrice;
    private final Label labelQuantity;

    public ProductOrder(String productName, String productCategory, Image productImage, String imagePath, String firstAttribute, String secondAttribute, String thirdAttribute, int totalAmount, int quantity) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productImage = productImage;
        this.imagePath = imagePath;
        this.firstAttribute = firstAttribute;
        this.secondAttribute = secondAttribute;
        this.thirdAttribute = thirdAttribute;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.labelPrice = new Label();
        this.labelQuantity = new Label();
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getThirdAttribute() {
        return thirdAttribute;
    }

    public Label getLabelQuantity() {
        return labelQuantity;
    }

    public Label getLabelPrice() {
        return labelPrice;
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

    public String getFirstAttribute() {
        return firstAttribute;
    }

    public String getSecondAttribute() {
        return secondAttribute;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setFirstAttribute(String firstAttribute) {
        this.firstAttribute = firstAttribute;
    }

    public void setSecondAttribute(String secondAttribute) {
        this.secondAttribute = secondAttribute;
    }

    public void setThirdAttribute(String thirdAttribute) {
        this.thirdAttribute = thirdAttribute;
    }
}
