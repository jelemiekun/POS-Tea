package com.example.postearevised.Miscellaneous.Enums;

public enum ProductCategories {
    MilkTeaEnum("Milk Tea"),
    CoolersEnum("Coolers"),
    CoffeeEnum("Coffee"),
    CandyCupsEnum("Candy Cups"),
    AppetizersEnum("Appetizers");

    private final String category;

    ProductCategories(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
