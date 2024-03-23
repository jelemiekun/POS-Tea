package com.example.postearevised.Miscellaneous.Enums;

public enum ProductEnum {
    PRODUCT_ENUM("Product");

    private final String title;

    ProductEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
