package com.example.postearevised.Objects;

import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public abstract class Product {
    private String productName;
    private String productDescription;
    private String category;
    private ImageView imageView;
    private Image image;
    private String imagePath;
    private CheckBox checkBox;
    private boolean isChecked;
    private ImageView imageViewSmall;

    public Product(String category) {
        if (!category.isBlank() && !category.isEmpty()) {
            this.productName = "N/A";
            this.productDescription = "N/A";
            this.category = category;
            this.imagePath = "/com/example/postearevised/Product Media/no image.png";
            this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            this.imageView = new ImageView();
            this.imageView.setImage(image);
            this.imageViewSmall = new ImageView();
            this.imageViewSmall.setImage(image);
            this.imageViewSmall.setFitWidth(150);
            this.imageViewSmall.setFitHeight(80);
            this.checkBox = new CheckBox();
            this.isChecked = true;
        } else {
            // prompt can't add product, no category
        }
    }

    public Product(String productName, String productDescription, String imagePath, String category) {
        if (!category.isBlank() && !category.isEmpty()) {
            this.productName = (productName.isBlank() || productName.isEmpty()) ? "N/A" : productName;
            this.productDescription = (productDescription.isBlank() || productDescription.isEmpty()) ? "N/A" : productDescription;
            this.category = category;
            this.imagePath = (imagePath.isBlank() || imagePath.isEmpty()) ? "/com/example/postearevised/Product Media/no image.png": imagePath;
            this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            this.imageView = new ImageView();
            this.imageView.setImage(image);
            this.imageViewSmall = new ImageView();
            this.imageViewSmall.setImage(image);
            this.imageViewSmall.setFitWidth(150);
            this.imageViewSmall.setFitHeight(80);
            this.checkBox = new CheckBox();
            this.isChecked = true;
        } else {
            // prommpt can't add product, no category
        }
    }

    public ImageView getImageViewSmall() {

        return imageViewSmall;
    }

//    public void setImageViewSmall(ImageView imageViewSmall) {
//        this.imageViewSmall = imageViewSmall;
//    }

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


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

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
