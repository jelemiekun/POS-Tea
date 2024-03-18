package com.example.postearevised.Miscellaneous.Enums;

public enum ModeOfPayment {
    CASH_ENUM("Cash"),
    G_CASH_ENUM("GCash");

    private final String modeOfPayment;

    ModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }
}
