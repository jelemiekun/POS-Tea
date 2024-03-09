package com.example.postearevised.Objects;

import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Objects;

public abstract class Product {
    private String productName;
    private String productDescription;
    private String category;
    private Image image;
    private String imagePath;
    private CheckBox checkBox;
    private boolean isChecked;

    public Product() {
        this.productName = "N/A";
        this.productDescription = "N/A";
        this.imagePath = "/com/example/postearevised/Product Media/no photo";
    }

    public Product(String productName, String productDescription, String imagePath, String category) {
        this.productName = (productName.isBlank() || productName.isEmpty()) ? "N/A" : productName;
        this.productDescription = (productDescription.isBlank() || productDescription.isEmpty()) ? "N/A" : productDescription;
        this.category = category;
        this.imagePath = (imagePath.isBlank() || imagePath.isEmpty()) ? "/com/example/postearevised/Product Media/no photo.png": imagePath;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        this.checkBox = new CheckBox();
        this.isChecked = true;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCategory() {
        return category;
    }

//    public void setCategory(String category) {
//        this.category = category;
//    }

    public Image getImage() {
        return image;
    }

//    public void setImage(Image image) {
//        this.image = image;
//    }
//
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;

        checkBox.setSelected(isChecked);
        checkBox.setOnAction(event -> isChecked = checkBox.isSelected());
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
