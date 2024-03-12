package com.example.postearevised.Miscellaneous.Enums;

public enum ProductCategories {
    MilkTeaEnum("Milk Tea", 1),
    CoolersEnum("Coolers", 2),
    CoffeeEnum("Coffee", 3),
    IceCandyCupsEnum("Ice Candy Cups", 4),
    AppetizersEnum("Appetizers", 5),
    AllProductCategoryEnum("All", 6);

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
