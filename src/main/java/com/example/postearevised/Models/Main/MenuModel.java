package com.example.postearevised.Models.Main;

import com.example.postearevised.Controllers.Main.MainController;
import javafx.scene.effect.DropShadow;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.dropShadowColor;
import static com.example.postearevised.Miscellaneous.References.ProductReference.productObservableList;

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
        if (productObservableList.isEmpty()) {
            mainController.anchorPaneMenuIsEmpty.setVisible(true);
        } else {
            mainController.anchorPaneMenuIsEmpty.setVisible(false);
        }
    }
}
