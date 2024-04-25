package com.example.postearevised.Objects.Products;

import com.example.postearevised.Miscellaneous.References.GeneralReference;
import com.example.postearevised.Miscellaneous.References.ProductReference;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Objects;

import static com.example.postearevised.Miscellaneous.Database.CSV.Products.ProductsCSVOperations.*;
import static com.example.postearevised.Miscellaneous.Others.PromptContents.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public class Product {
    private String productName;
    private String productDescription;
    private String category;
    private ImageView imageView;
    private Image image;
    private String imagePath;
    private CheckBox checkBox;
    private ImageView imageViewSmall;

    public Product(String productName, String productDescription, String imagePath, String category) {
        if (!category.isBlank() && !category.isEmpty()) {
            this.productName = (productName.isBlank() || productName.isEmpty()) ? "" : productName;
            this.productDescription = (productDescription.isBlank() || productDescription.isEmpty()) ? "" : productDescription;
            this.category = category;
            this.imagePath = imagePath.isEmpty() ? "/com/example/postearevised/Product Media/no image/no image.png" : imagePath;
            if (imagePath.isEmpty()) {
                this.image = new Image(Objects.requireNonNull(GeneralReference.class.getResourceAsStream("/com/example/postearevised/Product Media/no image/no image.png")));
            } else if (imagePath.equals("/com/example/postearevised/Product Media/no image/no image.png")) {
                this.image = new Image(Objects.requireNonNull(GeneralReference.class.getResourceAsStream("/com/example/postearevised/Product Media/no image/no image.png")));
            } else if (imagePath.startsWith("/com/example/postearevised")) {
                this.image = new Image(Objects.requireNonNull(GeneralReference.class.getResourceAsStream("/com/example/postearevised/Product Media/no image/no image.png")));
            } else if (imagePath.equals("example")) {
                this.image = new Image(Objects.requireNonNull(GeneralReference.class.getResourceAsStream("/com/example/postearevised/Product Media/no image/no image.png")));
            } else {
                String imageUrl = new File(this.imagePath).toURI().toString();
                this.image = new Image(imageUrl);
            }
            this.imageView = new ImageView();
            this.imageView.setImage(this.image);
            this.imageViewSmall = new ImageView();
            this.imageViewSmall.setImage(this.image);
            this.imageViewSmall.setFitWidth(IMAGE_VIEW_SMALL_WIDTH);
            this.imageViewSmall.setFitHeight(IMAGE_VIEW_SMALL_HEIGHT);
            this.checkBox = new CheckBox();
            this.checkBox.setSelected(true);

            this.checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (!isAddingProductsFromImport) {
                    if (!orderIsOngoing) {
                        if (editProductAvailabilityInCSV(Product.this)) {
                            this.checkBox.setSelected(newValue);
                            if (this.checkBox.isSelected()) {
                                availableAllProductObservableList.add(this);
                                unavailableAllProductObservableList.remove(this);

                                if (this instanceof MilkTea milkTea) {
                                    availableMilkTeaObservableList.add(milkTea);
                                    unavailableMilkTeaObservableList.remove(milkTea);
                                } else if (this instanceof Coolers coolers) {
                                    availableCoolersObservableList.add(coolers);
                                    unavailableCoolersObservableList.remove(coolers);
                                } else if (this instanceof Coffee coffee) {
                                    availableCoffeeObservableList.add(coffee);
                                    unavailableCoffeeObservableList.remove(coffee);
                                } else if (this instanceof IceCandyCups iceCandyCups) {
                                    availableIceCandyCupsObservableList.add(iceCandyCups);
                                    unavailableIceCandyCupsObservableList.remove(iceCandyCups);
                                } else if (this instanceof Appetizer appetizer) {
                                    availableAppetizerObservableList.add(appetizer);
                                    unavailableAppetizerObservableList.remove(appetizer);
                                }
                            } else {
                                availableAllProductObservableList.remove(this);
                                unavailableAllProductObservableList.add(this);

                                if (this instanceof MilkTea milkTea) {
                                    unavailableMilkTeaObservableList.add(milkTea);
                                    availableMilkTeaObservableList.remove(milkTea);
                                } else if (this instanceof Coolers coolers) {
                                    unavailableCoolersObservableList.add(coolers);
                                    availableCoolersObservableList.remove(coolers);
                                } else if (this instanceof Coffee coffee) {
                                    unavailableCoffeeObservableList.add(coffee);
                                    availableCoffeeObservableList.remove(coffee);
                                } else if (this instanceof IceCandyCups iceCandyCups) {
                                    unavailableIceCandyCupsObservableList.add(iceCandyCups);
                                    availableIceCandyCupsObservableList.remove(iceCandyCups);
                                } else if (this instanceof Appetizer appetizer) {
                                    unavailableAppetizerObservableList.add(appetizer);
                                    availableAppetizerObservableList.remove(appetizer);
                                }
                            }
                        } else {
                            isAddingProductsFromImport = true;
                            this.checkBox.setSelected(oldValue);
                            setErrorEditProduct();
                            ProductReference.openPrompt();
                            isAddingProductsFromImport = false;
                        }
                    } else {
                        isAddingProductsFromImport = true;
                        this.checkBox.setSelected(oldValue);
                        isAddingProductsFromImport = false;
                    }
                }
            });
        } else {
            // prommpt can't add product, no category
        }
    }


    public void setImage(Image image) {
        this.image = image;
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
        if (imagePath.isEmpty()) {
            this.image = new Image(Objects.requireNonNull(GeneralReference.class.getResourceAsStream("/com/example/postearevised/Product Media/no image/no image.png")));
        } else if (imagePath.equals("/com/example/postearevised/Product Media/no image/no image.png")) {
            this.image = new Image(Objects.requireNonNull(GeneralReference.class.getResourceAsStream("/com/example/postearevised/Product Media/no image/no image.png")));
        } else if (imagePath.startsWith("/com/example/postearevised")) {
            this.image = new Image(Objects.requireNonNull(GeneralReference.class.getResourceAsStream("/com/example/postearevised/Product Media/no image/no image.png")));
        } else if (imagePath.equals("example")) {
            this.image = new Image(Objects.requireNonNull(GeneralReference.class.getResourceAsStream("/com/example/postearevised/Product Media/no image/no image.png")));
        } else {
            String imageUrl = new File(this.imagePath).toURI().toString();
            this.image = new Image(imageUrl);
        }
        this.imageView = new ImageView();
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

}
