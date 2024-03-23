package com.example.postearevised.Miscellaneous.Enums;

public enum OrderHistorySortEnum {
    CUSTOMER_NAME_ENUM("Customer Name"),
    FOOD_CATEGORY_ENUM("Food Category"),
    PRODUCT_NAME_ENUM("Product Name"),
    PRODUCT_QUANTITY_ENUM("Quantity"),
    PRODUCT_TOTAL_PRICE_ENUM("Total Price"),
    DATE_AND_TIME_ENUM("Date and Time");

    private final String title;

    OrderHistorySortEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
