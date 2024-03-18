package com.example.postearevised.Miscellaneous.Enums;

public enum ProductCategories {
    MILK_TEA_ENUM("Milk Tea", 1),
    COOLERS_ENUM("Coolers", 2),
    COFFEE_ENUM("Coffee", 3),
    ICE_CANDY_CUPS_ENUM("Ice Candy Cups", 4),
    APPETIZERS_ENUM("Appetizers", 5),
    ALL_PRODUCT_CATEGORY_ENUM("All", 6);

    private final String category;
    private final int number;

    ProductCategories(String category, int number) {
        this.category = category;
        this.number = number;
    }

    public int getNumber() { return number; }

    public String getCategory() {
        return category;
    }
}
