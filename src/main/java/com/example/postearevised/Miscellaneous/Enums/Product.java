package com.example.postearevised.Miscellaneous.Enums;

public enum Product {
    Product("Product");

    private final String title;

    Product(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
