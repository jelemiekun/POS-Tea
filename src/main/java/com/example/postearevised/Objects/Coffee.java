package com.example.postearevised.Objects;

public class Coffee extends Product {
    private double price;
    public Coffee(String productName, String productDescription, String imagePath, String category,
                  double price) {
        super(productName, productDescription, imagePath, category);
        this.price = (String.valueOf(price).isBlank()) ? 0 : price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
