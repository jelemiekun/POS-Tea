package com.example.postearevised.Miscellaneous.Enums;

public enum StartPane {
    Login("Login", 1),
    Register("Register", 2),
    ForgotPassword("Forgot PasswordColors", 3),
    ForgotPassword1(ForgotPassword.getName(), 4),
    ForgotPassword2(ForgotPassword.getName(), 5),
    ForgotPassword3(ForgotPassword.getName(), 6);

    private final String name;
    private final int paneNumber;

    StartPane(String name, int paneNumber) {
        this.name = name;
        this.paneNumber = paneNumber;
    }

    public String getName() {
        return name;
    }

    public int getPaneNumber() {
        return paneNumber;
    }
}
