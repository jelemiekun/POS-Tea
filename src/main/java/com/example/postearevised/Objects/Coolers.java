package com.example.postearevised.Objects;

public class Coolers extends Product {
    private double smallPrice;
    private double mediumPrice;
    private double largePrice;
    public Coolers(String productName, String productDescription, String imagePath, String category,
                   double smallPrice, double mediumPrice, double largePrice) {
        super(productName, productDescription, imagePath, category);
        this.smallPrice = (String.valueOf(smallPrice).isBlank()) ? 0 : smallPrice;
        this.mediumPrice = (String.valueOf(mediumPrice).isBlank()) ? 0 : mediumPrice;
        this.largePrice = (String.valueOf(largePrice).isBlank()) ? 0 : largePrice;
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
}
