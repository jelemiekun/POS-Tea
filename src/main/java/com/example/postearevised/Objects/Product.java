package com.example.postearevised.Objects;

import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public abstract class Product {
    private String productName;
    private String productDescription;
    private String category;
    private ImageView imageView;
    private double imageViewPositionX;
    private double imageViewPositionY;
    private Image image;
    private String imagePath;
    private CheckBox checkBox;
    private ImageView imageViewSmall;
    private CheckBox checkBoxDelete;

    public Product(String productName, String productDescription, String imagePath, String category) {
        if (!category.isBlank() && !category.isEmpty()) {
            this.productName = (productName.isBlank() || productName.isEmpty()) ? "" : productName;
            this.productDescription = (productDescription.isBlank() || productDescription.isEmpty()) ? "" : productDescription;
            this.category = category;
            this.imagePath = imagePath.isEmpty() ? "/com/example/postearevised/Product Media/no image/no image.png" : imagePath;
            this.image = new Image(this.imagePath);
            this.imageView = new ImageView();
            this.imageView.setImage(this.image);
            this.imageViewSmall = new ImageView();
            this.imageViewSmall.setImage(this.image);
            this.imageViewSmall.setFitWidth(IMAGE_VIEW_SMALL_WIDTH);
            this.imageViewSmall.setFitHeight(IMAGE_VIEW_SMALL_HEIGHT);
            this.checkBox = new CheckBox();
            this.checkBox.setSelected(true);
            this.checkBoxDelete = new CheckBox();
        } else {
            // prommpt can't add product, no category
        }
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getImageViewPositionX() {
        return imageViewPositionX;
    }

    public void setImageViewPositionX(double imageViewPositionX) {
        this.imageViewPositionX = imageViewPositionX;
    }

    public double getImageViewPositionY() {
        return imageViewPositionY;
    }

    public void setImageViewPositionY(double imageViewPositionY) {
        this.imageViewPositionY = imageViewPositionY;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Image getImage() {
        return image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath.isEmpty() ? "/com/example/postearevised/Product Media/no image/no image.png" : imagePath;
        this.image = new Image(this.imagePath);
        this.imageView.setImage(this.image);
        this.imageViewSmall.setImage(this.image);
        this.imageViewSmall.setFitWidth(IMAGE_VIEW_SMALL_WIDTH);
        this.imageViewSmall.setFitHeight(IMAGE_VIEW_SMALL_HEIGHT);
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
    public void setCheckBoxDelete(CheckBox checkBoxDelete) {
        this.checkBoxDelete = checkBoxDelete;
    }

}
