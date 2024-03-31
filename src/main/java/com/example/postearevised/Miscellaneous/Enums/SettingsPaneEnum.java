package com.example.postearevised.Miscellaneous.Enums;

public enum SettingsPaneEnum {
    SETTINGS_PANE_ACCOUNT_ENUM(1),
    SETTINGS_PANE_APPEARANCE_ENUM(2),
    SETTINGS_PANE_EDIT_PRODUCT_ENUM(3),
    SETTINGS_PANE_TAC_ENUM(4),
    SETTINGS_PANE_SYSTEM_MANUAL_ENUM(5);

    private final int paneNumber;

    SettingsPaneEnum(int paneNumber) {
        this.paneNumber = paneNumber;
    }

    public int getPaneNumber() {
        return paneNumber;
    }
}
