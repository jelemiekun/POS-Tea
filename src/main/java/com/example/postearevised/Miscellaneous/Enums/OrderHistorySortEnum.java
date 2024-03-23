package com.example.postearevised.Miscellaneous.Enums;

public enum OrderHistorySortEnum {
    CUSTOMER_NAME_ENUM("Customer Name"),
    FOOD_CATEGORY_ENUM("Food Category"),
    PRODUCT_NAME_ENUM("Product Name"),
    DAY_ENUM("Day"),
    MONTH_ENUM("Month"),
    YEAR_ENUM("Year");

    private final String title;

    OrderHistorySortEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
