package com.example.postearevised.Miscellaneous.Enums;

public enum EnumProduct {
    ProductEnum("Product");

    private final String title;

    EnumProduct(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
