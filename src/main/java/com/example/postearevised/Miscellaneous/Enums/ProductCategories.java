package com.example.postearevised.Miscellaneous.Enums;

public enum ProductCategories {
    MilkTea("Milk Tea"),
    Coolers("Coolers"),
    Coffee("Coffee"),
    CandyCups("Candy Cups"),
    Appetizers("Appetizers");

    private final String category;

    ProductCategories(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
