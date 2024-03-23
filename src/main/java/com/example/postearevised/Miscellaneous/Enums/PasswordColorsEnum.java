package com.example.postearevised.Miscellaneous.Enums;

import javafx.scene.paint.Color;

public enum PasswordColorsEnum {
    WHITE_ENUM("", Color.web("#ffffff")),
    WEAK_ENUM("Weak", Color.web("#ff2b2b")),
    FAIR_ENUM("Fair", Color.web("#ffc629")),
    GOOD_ENUM("Good", Color.web("#28ff19")),
    STRONG_ENUM("Strong", Color.web("#098d00"));

    private final String text;
    private final Color color;

    PasswordColorsEnum(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }
}
