package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import javafx.scene.effect.DropShadow;

import static com.example.postearevised.Miscellaneous.Enums.ProductCategories.*;
import static com.example.postearevised.Miscellaneous.References.GeneralReference.*;
import static com.example.postearevised.Miscellaneous.References.ImagesReference.*;
import static com.example.postearevised.Miscellaneous.References.ProductReference.*;

public class MenuModel {
    private MainController mainController;
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setOffsetX(-7);
        dropShadow.setOffsetY(0);
        dropShadow.setColor(dropShadowColor);

        mainController.anchorPaneRightPanel.setEffect(dropShadow);
    }

    public void checkIfIsMenuEmpty() {
        if (allProductObservableList.isEmpty()) {
            updateElementsIfEmpty();
        } else {
            updateElementsIfNotEmpty();
        }
    }

    private void updateElementsIfEmpty() {
        mainController.anchorPaneMenuIsEmpty.setVisible(true);

        mainController.imageViewMenuAll.setImage(allCategory);
        mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
        mainController.imageViewMenuCoolers.setImage(coolersCategory);
        mainController.imageViewMenuCoffee.setImage(coffeeCategory);
        mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
        mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
    }

    private void updateElementsIfNotEmpty() {
        mainController.anchorPaneMenuIsEmpty.setVisible(false);

        switchCategory(AllProductCategoryEnum.getNumber());
    }

    public void switchCategory(int categoryNumber) {
        if (!allProductObservableList.isEmpty()) {
            switch (categoryNumber) {
                case 1:
                    mainController.imageViewMenuMilkTea.setImage(milkTeasCategorySelected);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 2:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategorySelected);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 3:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategorySelected);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 4:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategorySelected);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 5:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategorySelected);
                    mainController.imageViewMenuAll.setImage(allCategory);
                    break;
                case 6:
                    mainController.imageViewMenuMilkTea.setImage(milkTeaCategory);
                    mainController.imageViewMenuCoolers.setImage(coolersCategory);
                    mainController.imageViewMenuCoffee.setImage(coffeeCategory);
                    mainController.imageViewMenuIceCandyCups.setImage(iceCandyCupsCategory);
                    mainController.imageViewMenuAppetizers.setImage(appetizersCategory);
                    mainController.imageViewMenuAll.setImage(allCategorySelected);
                    break;
            }
        }
    }
}
