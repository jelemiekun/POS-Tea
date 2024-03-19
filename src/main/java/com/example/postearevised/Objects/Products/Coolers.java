package com.example.postearevised.Objects.Products;

public class Coolers extends Product {
    private double smallPrice;
    private double mediumPrice;
    private double largePrice;
    private String addOnsOne;
    private double addOnsOnePrice;
    private String addOnsTwo;
    private double addOnsTwoPrice;
    public Coolers(String productName, String productDescription, String imagePath, String category,
                   double smallPrice, double mediumPrice, double largePrice,
                   String addOnsOne, double addOnsOnePrice, String addOnsTwo, double addOnsTwoPrice) {
        super(productName, productDescription, imagePath, category);
        this.smallPrice = (String.valueOf(smallPrice).isBlank()) ? 0 : smallPrice;
        this.mediumPrice = (String.valueOf(mediumPrice).isBlank()) ? 0 : mediumPrice;
        this.largePrice = (String.valueOf(largePrice).isBlank()) ? 0 : largePrice;
        this.addOnsOne = addOnsOne;
        this.addOnsOnePrice = (String.valueOf(addOnsOnePrice).isBlank()) ? 0 : addOnsOnePrice;
        this.addOnsTwo = addOnsTwo;
        this.addOnsTwoPrice = (String.valueOf(addOnsTwoPrice).isBlank()) ? 0 : addOnsTwoPrice;
    }

    public double getSmallPrice() {
        return smallPrice;
    }

    public void setSmallPrice(double smallPrice) {
        this.smallPrice = smallPrice;
    }

    public double getMediumPrice() {
        return mediumPrice;
    }

    public void setMediumPrice(double mediumPrice) {
        this.mediumPrice = mediumPrice;
    }

    public double getLargePrice() {
        return largePrice;
    }

    public void setLargePrice(double largePrice) {
        this.largePrice = largePrice;
    }

    public String getAddOnsOne() {
        return addOnsOne;
    }

    public void setAddOnsOne(String addOnsOne) {
        this.addOnsOne = addOnsOne;
    }

    public double getAddOnsOnePrice() {
        return addOnsOnePrice;
    }

    public void setAddOnsOnePrice(double addOnsOnePrice) {
        this.addOnsOnePrice = addOnsOnePrice;
    }

    public String getAddOnsTwo() {
        return addOnsTwo;
    }

    public void setAddOnsTwo(String addOnsTwo) {
        this.addOnsTwo = addOnsTwo;
    }

    public double getAddOnsTwoPrice() {
        return addOnsTwoPrice;
    }

    public void setAddOnsTwoPrice(double addOnsTwoPrice) {
        this.addOnsTwoPrice = addOnsTwoPrice;
    }
}
