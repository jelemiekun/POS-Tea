package com.example.postearevised.Miscellaneous.Enums;

public enum OrderEnum {
    OrderEnum("Order Summary");

    private final String title;

    OrderEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
