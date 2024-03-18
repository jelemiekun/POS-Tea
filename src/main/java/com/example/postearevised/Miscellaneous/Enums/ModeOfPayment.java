package com.example.postearevised.Miscellaneous.Enums;

public enum ModeOfPayment {
    CashEnum("Cash"),
    GCashEnum("GCash");

    private final String modeOfPayment;

    ModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }
}
