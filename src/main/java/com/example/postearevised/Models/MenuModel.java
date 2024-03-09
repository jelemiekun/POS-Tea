package com.example.postearevised.Models;

import com.example.postearevised.Controllers.MainController;
import javafx.scene.effect.DropShadow;

import static com.example.postearevised.Miscellaneous.References.GeneralReference.dropShadowColor;

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
}
