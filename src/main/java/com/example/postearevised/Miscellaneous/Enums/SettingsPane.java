package com.example.postearevised.Miscellaneous.Enums;

public enum SettingsPane {
    Account(1),
    Display(2),
    EditProducts(3),
    TAC(4),
    SystemManual(5);

    private final int paneNumber;

    SettingsPane(int paneNumber) {
        this.paneNumber = paneNumber;
    }

    public int getPaneNumber() {
        return paneNumber;
    }
}
