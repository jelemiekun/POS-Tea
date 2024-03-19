package com.example.postearevised.Objects.Order;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class ProductOrder {
    private final String productName;
    private final String productCategory;
    private final Image productImage;
    private final String firstAttribute;
    private final String secondAttribute;
    private final String thirdAttribute;
    private int totalAmount;
    private int quantity;
    private final Label labelPrice;
    private final Label labelQuantity;

    public ProductOrder(String productName, String productCategory, Image productImage, String firstAttribute, String secondAttribute, String thirdAttribute, int totalAmount, int quantity) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productImage = productImage;
        this.firstAttribute = firstAttribute;
        this.secondAttribute = secondAttribute;
        this.thirdAttribute = thirdAttribute;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.labelPrice = new Label();
        this.labelQuantity = new Label();
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
}
