package com.example.postearevised.Miscellaneous.Enums;

public enum OrderEnum {
    ORDER_ENUM("Order Summary");

    private final String title;

    OrderEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
