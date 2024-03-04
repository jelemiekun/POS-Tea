package com.example.postearevised.Objects;

public abstract class Product {
    private String productName;
    private String productDescription;
    private String productImageFilePath;

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(String productName, String productDescription) {
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public Product(String productName, String productDescription, String productImageFilePath) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImageFilePath = productImageFilePath;
    }
}
