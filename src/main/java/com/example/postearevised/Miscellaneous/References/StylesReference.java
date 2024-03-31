package com.example.postearevised.Miscellaneous.References;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class StylesReference {
    public static DropShadow setDropShadowRightDown() {

        DropShadow labelDropShadowRightDown = new DropShadow();
        labelDropShadowRightDown.setRadius(20);
        labelDropShadowRightDown.setOffsetX(0);
        labelDropShadowRightDown.setOffsetY(0);
        labelDropShadowRightDown.setColor(Color.rgb(0, 0, 0, 1));

        return labelDropShadowRightDown;
    }
    public static final Color dropShadowColor = Color.rgb(150,150,150);
    public static final String toolTipStyle = "-fx-font-family: Arial; -fx-font-size: 14px;";
    public static final String dashboardComboBoxStyle = "-fx-font-family: Arial; -fx-font-size: 18px;";
    public static final String orderHistoryComboBoxStyle = "-fx-font-family: Arial; -fx-font-size: 30px;";
    public static final String settingEditProductImportExportComboBoxStyle = "-fx-font-family: Arial; -fx-font-size: 18px;";
    public static final String registerRecoveryQuestionStyle = "-fx-font-size: 22px; -fx-font-family: Arial;";
    public static final String settingsAccountNameComboBoxStyle = "-fx-font-size: 20px; -fx-font-family: Arial;";
    public static final String settingsAccountQuestionsComboBoxStyle = "-fx-font-size: 24px; -fx-font-family: Arial;";
}
